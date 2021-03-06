package com.linksame.crm.erp.work.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.erp.admin.service.AdminFileService;
import com.linksame.crm.erp.oa.common.OaEnum;
import com.linksame.crm.erp.oa.service.OaActionRecordService;
import com.linksame.crm.erp.work.entity.*;
import com.linksame.crm.utils.AuthUtil;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.linksame.crm.utils.TagUtil;
import java.util.*;

public class TaskService{
    //添加日志
    @Inject
    private OaActionRecordService oaActionRecordService;

    @Inject
    private AdminFileService adminFileService;

    public R setTaskClass(WorkTaskClass taskClass){
        boolean bol;
        if(taskClass.getClassId() == null){
            Work work = new Work().findById(taskClass.getWorkId());
            Integer isOpen = work.getIsOpen();
            if(isOpen == 0 && ! AuthUtil.isWorkAuth(taskClass.getWorkId().toString(), "taskClass:save")){
                return R.noAuth();
            }
            Integer orderNum = Db.queryInt("select max(order_num) from `work_task_class` where work_id = ?", taskClass.getWorkId());
            taskClass.setOrderNum(orderNum+1);
            taskClass.setCreateUserId(BaseUtil.getUser().getUserId());
            taskClass.setCreateTime(new Date());
            bol = taskClass.save();
        }else{
            Integer workId = Db.queryInt("select work_id from `work_task_class` where class_id = ?", taskClass.getClassId());
            if(! AuthUtil.isWorkAuth(workId.toString(), "taskClass:update")){
                return R.noAuth();
            }
            bol = taskClass.update();
        }
        return R.isSuccess(bol);
    }


    public void changeOrderTaskClass(String originalClassId, String targetClassId){
        WorkTaskClass originalClass = WorkTaskClass.dao.findById(originalClassId);
        WorkTaskClass targetClass = WorkTaskClass.dao.findById(targetClassId);
        Integer originalClassOrderId = originalClass.getOrderNum();
        Integer targetClassOrderId = targetClass.getOrderNum();
        Db.update("update work_task_class setUser order_id = ? where class_id = ?", originalClassOrderId, targetClassId);
        Db.update("update work_task_class setUser order_id = ? where class_id = ?", targetClassOrderId, originalClassId);
    }

    /**
     * 设置任务
     * @param task
     * @param taskRelation
     * @return
     */
    @Before(Tx.class)
    public R setTask(Task task, TaskRelation taskRelation){
        AdminUser user = BaseUtil.getUser();
        boolean bol;
        if(task.getLabelId() != null){
            task.setLabelId(TagUtil.fromString(task.getLabelId()));
        }
        if(task.getTaskId() == null){
            if(task.getMainUserId() == null){
                task.setMainUserId(user.getUserId());
            }
            if(task.getOwnerUserId() != null){
                Set<Long> ownerUserId = TagUtil.toLongSet(task.getOwnerUserId());
                ownerUserId.add(user.getUserId());
                task.setOwnerUserId(TagUtil.fromLongSet(ownerUserId));
            }else{
                task.setOwnerUserId("," + user.getUserId() + ",");
            }
            task.setCreateTime(new Date());
            task.setUpdateTime(new Date());
            task.setCreateUserId(user.getUserId());
            if(StringUtils.isEmpty(task.getBatchId())){
                task.setBatchId(IdUtil.simpleUUID());
            }
            bol = task.save();
            WorkTaskLog workTaskLog = new WorkTaskLog();
            workTaskLog.setUserId(user.getUserId());
            workTaskLog.setTaskId(task.getTaskId());
            workTaskLog.setContent("添加了新任务 " + task.getName());
            saveWorkTaskLog(workTaskLog);
        } else {
            task.setUpdateTime(new Date());
            //当设置任务状态为完成时, 判断是否为重复任务
            if(task.getStatus() == 5){
                task.setCoTime(new Date());
                //如果是, 需要根据重复任务的配置, 解析获取下一个重复节点, 生成下一个任务
                if(task.getRepeatId() != 0){
                    //获取任务数据
                    Task newTask = Task.dao.findById(task.getTaskId());
                    if(newTask == null){
                        return R.error("原任务数据不存在");
                    }
                    //获取重复任务
                    TaskRepeat taskRepeat = TaskRepeat.dao.findById(newTask.getRepeatId());
                    if(taskRepeat != null){
                        //按天重复类型
                        if(taskRepeat.getRepeatType() == 1){
                            //下一个任务的执行时间(开始/截止时间) = 原任务开始时间/截止时间 + 间隔天数
                            Date startDate = com.linksame.crm.utils.DateUtil.getNextDay(newTask.getStartTime(), taskRepeat.getRepeatInterval() + 1);
                            String startTime = com.linksame.crm.utils.DateUtil.dateToStrLong(startDate);
                            Date stopDate = com.linksame.crm.utils.DateUtil.getNextDay(newTask.getStopTime(), taskRepeat.getRepeatInterval() + 1);
                            String stopTime = com.linksame.crm.utils.DateUtil.dateToStrLong(stopDate);
                            //设置开始时间与结束时间, 并生成新任务
                            saveRepeatTask(newTask, startTime, stopTime);
                        } else if(taskRepeat.getRepeatType() == 2){
                            //通过范围筛选, 推算出下一个任务开始时间
                            String startTime = getWeekDatetime(newTask.getStartTime(), taskRepeat);
                            //根据原始任务的开始时间与结束时间间隔, 推算新任务的结束时间
                            String stopTime = getStoptime(newTask, startTime);
                            //设置开始时间与结束时间, 并生成新任务
                            saveRepeatTask(newTask, startTime, stopTime);
                        } else if(taskRepeat.getRepeatType() == 3){
                            //通过范围筛选, 推算出下一个任务开始时间
                            String startTime = getMonthDatetime(newTask.getStartTime(), taskRepeat);
                            //根据原始任务的开始时间与结束时间间隔, 推算新任务的结束时间
                            String stopTime = getStoptime(newTask, startTime);
                            //设置开始时间与结束时间, 并生成新任务
                            saveRepeatTask(newTask, startTime, stopTime);
                        }
                    }
                }
            }
            //更新任务及任务日志信息记录
            bol = getWorkTaskLog(task, user.getUserId());
        }
        if (taskRelation != null) {
            //设置任务关联业务
            if (taskRelation.getBusinessIds() != null || taskRelation.getContactsIds() != null || taskRelation.getContractIds() != null || taskRelation.getCustomerIds() != null) {
                Db.deleteById("task_relation", "task_id", task.getTaskId());
                taskRelation.setCreateTime(DateUtil.date());
                taskRelation.setTaskId(task.getTaskId());
                taskRelation.save();
            }
        }
        task.getMainUserId();
        oaActionRecordService.addRecord(task.getTaskId(), OaEnum.TASK_TYPE_KEY.getTypes(), task.getUpdateTime() == null ? 1 : 2, oaActionRecordService.getJoinIds(user.getUserId(), getJoinUserIds(task)), oaActionRecordService.getJoinIds(Long.valueOf(user.getDeptId()), ""));
        return bol ? R.ok().put("data", Kv.by("task_id", task.getTaskId())) : R.error();
    }

    //根据原始任务的开始时间与结束时间间隔, 推算新任务的结束时间
    private String getStoptime(Task newTask, String startTime) {
        //获取原任务开始时间与截止时间的间隔天数
        int days = com.linksame.crm.utils.DateUtil.differentDays(newTask.getStartTime(), newTask.getStopTime());
        //在新任务的开始日期上加入间隔天数, 生成新任务的截止日期
        String stopDate = com.linksame.crm.utils.DateUtil.checkOption(startTime, days);
        //时分秒替换
        return com.linksame.crm.utils.DateUtil.handleDate(com.linksame.crm.utils.DateUtil.dateToStrLong(newTask.getStopTime()), stopDate + " 00:00:00");
    }

    //周类型范围筛选, 根据已知条件推算下一个任务时间
    private String getWeekDatetime(Date datetime, TaskRepeat taskRepeat) {
        String startTime = null;
        //根据日期获取是周几
        Integer beforeStartWeek = com.linksame.crm.utils.DateUtil.getWeek(com.linksame.crm.utils.DateUtil.dateToStrLong(datetime));
        //获取该日期所在周, 与周一之间的间隔天数(-4)
        Integer beforeWeekMonday = com.linksame.crm.utils.DateUtil.getWeekMonday(datetime);
        //重复设置周数值列表(例如3,0  分别对应周三,周日)
        String[] repeatAttr = taskRepeat.getRepeatAttr().split(",");
        //星期对应的日期列表
        List<String> weekDateList = new ArrayList<>();
        //获取该周的周数据对应的具体日期(开始时间)
        String beforeStartDate = com.linksame.crm.utils.DateUtil.dateToStrLong(com.linksame.crm.utils.DateUtil.getNextDay(datetime, beforeWeekMonday));
        String afterStartDate = com.linksame.crm.utils.DateUtil.dateToStrLong(com.linksame.crm.utils.DateUtil.getNextDay(datetime, 7 - beforeStartWeek));
        for(String attr : repeatAttr){
            int week = Integer.parseInt(attr);
            //判断数值是否大于原任务开始时间对应的周几数据, 如果大于则是下一个执行的时间点对应的周几数据, 如果都小于装入list, 后续取list第一个数据, 处理间隔循环
            if(week > beforeStartWeek && beforeStartWeek != 7){
                //获取下一个任务开始时间具体日期
                String startDate = com.linksame.crm.utils.DateUtil.getDayOfWeekWithinDateInterval(beforeStartDate, afterStartDate, Integer.parseInt(attr)).get(0);
                //秒数替换, 生成下一个任务的开始时间
                startTime = com.linksame.crm.utils.DateUtil.handleDate(com.linksame.crm.utils.DateUtil.dateToStrLong(datetime), startDate + " 00:00:00");
                break;
            } else {
                weekDateList.add(com.linksame.crm.utils.DateUtil.getDayOfWeekWithinDateInterval(beforeStartDate, afterStartDate, Integer.parseInt(attr)).get(0));
            }
        }
        //判断返回数值是否有数据, 如果没有数据, 说明遍历的重复值数据都不满足条件, 需进入下一个重复周期, 取出下个重复周期的第一条数据作为下一个任务的开始时间
        if(StringUtils.isEmpty(startTime)){
            //替换任务开始时间的时分秒
            String startDate = com.linksame.crm.utils.DateUtil.handleDate(com.linksame.crm.utils.DateUtil.dateToStrLong(datetime), weekDateList.get(0) + " 00:00:00");
            //获取间隔周期的第一个重复节点日期的开始时间
            startTime = com.linksame.crm.utils.DateUtil.dateToStrLong(com.linksame.crm.utils.DateUtil.getNextDay(com.linksame.crm.utils.DateUtil.strToDateLong(startDate),taskRepeat.getRepeatInterval() * 7 + 7));
        }
        return startTime;
    }

    //月类型范围筛选, 根据已知条件推算下一个任务时间
    private String getMonthDatetime(Date datetime, TaskRepeat taskRepeat) {
        String startTime = null;
        //分别拿到原任务开始日期的年份和月份
        int year = com.linksame.crm.utils.DateUtil.getYear(com.linksame.crm.utils.DateUtil.dateToStr(datetime));
        int month = com.linksame.crm.utils.DateUtil.getMonth(com.linksame.crm.utils.DateUtil.dateToStr(datetime));
        //获取原任务月份的最后一天, 用来判断后续日期如果有31号时, 进行校验
        String lastDate = com.linksame.crm.utils.DateUtil.getLastDayOfMonth(year, month);
        //拿到日期, 作为遍历下一个重复节点的条件
        int lastDay = com.linksame.crm.utils.DateUtil.getDate(lastDate);
        //重复设置周数值列表(例如3,0  分别对应周三,周日)
        String[] repeatAttr = taskRepeat.getRepeatAttr().split(",");
        List<String> monthDateList = new ArrayList<>();
        //获取当前日期的日期数(例如2020=05-22, 拿到22)
        String dateStr = com.linksame.crm.utils.DateUtil.dateToStr(datetime);
        Integer date = Integer.parseInt(dateStr.substring(dateStr.length()-2));
        //遍历设置的日期, 与原任务开始日期比较, 获取到下一个任务的开始时间
        for(String attr : repeatAttr){
            Integer day = Integer.parseInt(attr);
            //获取原始任务开始日期作为数据模板
            String str = com.linksame.crm.utils.DateUtil.dateToStr(datetime);
            if(day > date && date != lastDay){
                //日期替换
                String startDay = com.linksame.crm.utils.DateUtil.setDate(str, day);
                //秒数替换, 生成下一个任务的开始时间
                startTime = com.linksame.crm.utils.DateUtil.handleDate(com.linksame.crm.utils.DateUtil.dateToStrLong(datetime), startDay + " 00:00:00");
                break;
            } else {
                //日期替换
                String newDateTime = com.linksame.crm.utils.DateUtil.setDate(str, Integer.parseInt(attr));
                //月份替换(需算入间隔月份)
                String newDate = com.linksame.crm.utils.DateUtil.setMonth(newDateTime, month + taskRepeat.getRepeatInterval() + 1);
                monthDateList.add(newDate);
            }
        }
        //判断返回数值是否有数据, 如果没有数据, 说明遍历的重复值数据都不满足条件, 需进入下一个重复周期, 取出下个重复周期的第一条数据作为下一个任务的开始时间
        if(StringUtils.isEmpty(startTime)){
            //替换任务开始时间的时分秒
            startTime = com.linksame.crm.utils.DateUtil.handleDate(com.linksame.crm.utils.DateUtil.dateToStrLong(datetime), monthDateList.get(0) + " 00:00:00");
        }

        return startTime;
    }

    //存储重复任务
    private void saveRepeatTask(Task task, String startDate, String endDate){
        task.remove("task_id");
        task.remove("update_time");
        task.remove("co_time");
        task.remove("archive_time");
        task.remove("hidden_time");
        task.remove("sprint_id");
        task.setStatus(0);
        task.setIsArchive(0);
        task.setIshidden(0);
        task.setCreateTime(new Date());
        task.setStartTime(com.linksame.crm.utils.DateUtil.strToDateLong(startDate));
        task.setStopTime(com.linksame.crm.utils.DateUtil.strToDateLong(endDate));
        task.save();
    }

    private String getJoinUserIds(Task task){
        StringBuilder joinUserIds = new StringBuilder(",");
        if(task.getMainUserId() != null){
            joinUserIds.append(task.getMainUserId()).append(",");
        }
        if(StrUtil.isNotEmpty(task.getOwnerUserId())){
            joinUserIds.append(task.getOwnerUserId());
        }
        return joinUserIds.toString();
    }


    public R queryTaskInfo(String taskId){
        Record mainTask = transfer(taskId);
        adminFileService.queryByBatchId(mainTask.get("batch_id"), mainTask);
        List<Record> recordList = Db.find("select task_id from task where pid = ?", taskId);
        List<Record> childTaskList = new ArrayList<>();
        if(recordList != null && recordList.size() > 0){
            recordList.forEach(childTaskRecord -> {
                String childTaskId = childTaskRecord.getStr("task_id");
                Record childTask = transfer(childTaskId);
                adminFileService.queryByBatchId(childTask.getStr("batch_id"), childTask);
                childTaskList.add(childTask);
            });
        }
        mainTask.set("childTask", childTaskList);
        return R.ok().put("data", mainTask);
    }

    private Record transfer(String taskId){
        Record task = Db.findFirst("select a.*,b.name as workName from task a left join `work` b on a.work_id = b.work_id where task_id = ?", taskId);
        task.set("stop_time", DateUtil.formatDate(task.getDate("stop_time")));
        task.set("mainUser", Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", task.getInt("main_user_id")));
        task.set("createUser", Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", task.getInt("create_user_id")));
        ArrayList<Record> labelList = new ArrayList<>();
        ArrayList<Record> ownerUserList = new ArrayList<>();
        if(StrUtil.isNotBlank(task.getStr("label_id"))){
            String[] labelIds = task.getStr("label_id").split(",");
            for(String labelId : labelIds){
                if(StrUtil.isNotBlank(labelId)){
                    Record label = Db.findFirst("select label_id,name as labelName,color from work_task_label where label_id = ?", labelId);
                    labelList.add(label);
                }
            }
        }
        if(StrUtil.isNotBlank(task.getStr("owner_user_id"))){
            String[] ownerUserIds = task.getStr("owner_user_id").split(",");
            for(String ownerUserId : ownerUserIds){
                if(StrUtil.isNotBlank(ownerUserId)){
                    Record ownerUser = Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", ownerUserId);
                    ownerUserList.add(ownerUser);
                }
            }
        }
        Record relation = Db.findFirst("select * FROM task_relation where task_id = ?", taskId);
        List<Record> customerList = new ArrayList<>();
        List<Record> contactsList = new ArrayList<>();
        List<Record> businessList = new ArrayList<>();
        List<Record> contractList = new ArrayList<>();
        if(relation != null){
            if(StrUtil.isNotBlank(relation.getStr("customer_ids"))){
                String[] customerIds = relation.getStr("customer_ids").split(",");
                for(String customerId : customerIds){
                    if(StrUtil.isNotBlank(customerId)){
                        Record customer = Db.findFirst("select customer_id,customer_name  from crm_customer where customer_id = ?", customerId);
                        if(customer != null){
                            customerList.add(customer);
                        }
                    }
                }
            }

            if(StrUtil.isNotBlank(relation.getStr("contacts_ids"))){
                String[] contactsIds = relation.getStr("contacts_ids").split(",");

                for(String contactsId : contactsIds){
                    if(StrUtil.isNotBlank(contactsId)){
                        Record contacts = Db.findFirst("select contacts_id,name from crm_contacts  where contacts_id = ?", contactsId);
                        if(contacts != null){
                            contactsList.add(contacts);
                        }
                    }
                }
            }
            if(StrUtil.isNotBlank(relation.getStr("business_ids"))){
                String[] businessIds = relation.getStr("business_ids").split(",");

                for(String businessId : businessIds){
                    if(StrUtil.isNotBlank(businessId)){
                        Record business = Db.findFirst("select business_id,business_name  from crm_business  where business_id = ?", businessId);
                        if(business != null){
                            businessList.add(business);
                        }
                    }
                }
            }
            if(StrUtil.isNotBlank(relation.getStr("contract_ids"))){
                String[] contractIds = relation.getStr("contract_ids").split(",");
                for(String contractId : contractIds){
                    if(StrUtil.isNotBlank(contractId)){
                        Record contract = Db.findFirst("select contract_id,contract_name from pmp_contract  where contract_id = ?", contractId);
                        if(contract != null){
                            contractList.add(contract);
                        }
                    }
                }
                task.set("contractList", contractList);
            }
        }
        //组装前置任务数据
        List<Record> preList = Db.find("select b.* from task_rely as a inner join task as b on a.pre_task_id = b.task_id where a.task_id = ?", taskId);
        //组装后置任务数据
        List<Record> rearList = Db.find("select b.* from task_rely as a inner join task as b on a.task_id = b.task_id where a.pre_task_id = ?", taskId);
        //组装通用标签数据
        List<Record> commonLabelList = Db.find("select label_id,name as labelName,color from work_task_label where is_common = 1");
        //组装重复任务数据
        Record taskRepeat = Db.findFirst("select a.* from task_repeat a left join task b on a.repeat_id = b.repeat_id where b.task_id = ?", taskId);

        task.set("customerList", customerList)
                .set("contactsList", contactsList)
                .set("businessList", businessList)
                .set("contractList", contractList)
                .set("preTaskList", preList)
                .set("rearTaskList", rearList)
                .set("commonLabelList", commonLabelList)
                .set("labelList", labelList)
                .set("ownerUserList", ownerUserList)
                .set("taskRepeat", taskRepeat);

        return task;
    }


    /**
     * 查询任务列表
     */
    public R getTaskList(Integer type, Integer status, Integer priority, Integer date, List<Long> userIds, BasePageRequest<Task> basePageRequest, String name){
        Page<Record> page = new Page<>();
        if(userIds.size() == 0){
            page.setList(new ArrayList<>());
            return R.ok().put("data", page);
        }
        if(basePageRequest.getPageType() == 0){
            List<Record> recordList = Db.find(Db.getSqlPara("work.task.getTaskList",
                    Kv.by("type",type).set("userIds",userIds).set("status",status).
                            set("priority",priority).set("date",date).set("taskName",name)));
            return R.ok().put("data", queryUser(recordList));
        }else {
            page = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(),
                    Db.getSqlPara("work.task.getTaskList",
                            Kv.by("type",type).set("userIds",userIds).set("status",status).
                                    set("priority",priority).set("date",date).set("taskName",name)));
            page.setList(queryUser(page.getList()));
            return R.ok().put("data", page);
        }

    }

    private List<Record> queryUser(List<Record> tasks){
        ArrayList<Record> labelList;
        ArrayList<Record> ownerUserList;
        for(Record task : tasks){
            Integer mainUserId = task.getInt("main_user_id");
            Record mainUser = Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", mainUserId);
            task.set("mainUser",mainUser);
            labelList = new ArrayList<>();
            ownerUserList = new ArrayList<>();
            if(StrUtil.isNotBlank(task.getStr("label_id"))){
                String[] labelIds = task.getStr("label_id").split(",");
                for(String labelId : labelIds){
                    if(StrUtil.isNotBlank(labelId)){
                        Record label = Db.findFirst("select label_id,name as labelName , color from work_task_label where label_id = ?", labelId);
                        labelList.add(label);
                    }
                }
            }
            if(StrUtil.isNotBlank(task.getStr("owner_user_id"))){
                String[] ownerUserIds = task.getStr("owner_user_id").split(",");
                for(String ownerUserId : ownerUserIds){
                    if(StrUtil.isNotBlank(ownerUserId)){
                        Record ownerUser = Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", ownerUserId);
                        ownerUserList.add(ownerUser);
                    }
                }
            }
            TaskRelation taskRelation = TaskRelation.dao.findFirst(" select * from task_relation where task_id = ?", task.getInt("task_id"));
            Integer start = 0;
            if(taskRelation != null){
                start = queryCount(start, taskRelation.getBusinessIds());
                start = queryCount(start, taskRelation.getContactsIds());
                start = queryCount(start, taskRelation.getContractIds());
                start = queryCount(start, taskRelation.getCustomerIds());
            }
            task.set("relationCount", start);
            if(task.getDate("stop_time") != null){
                Calendar date = Calendar.getInstance();
                date.setTime(DateUtil.date());
                //设置开始时间
                Calendar begin = Calendar.getInstance();
                begin.setTime(task.getDate("stop_time"));
                if(date.after(begin) && task.getInt("status") != 5 && task.getInt("status") != 2){
                    task.set("is_end", 1);
                }else{
                    task.set("is_end", 0);
                }
            }else{
                task.set("is_end", 0);
            }
            task.set("labelList", labelList).set("ownerUserList", ownerUserList);
        }
        return tasks;
    }

    private Integer queryCount(Integer start, String str){
        // start 开始个数
        if(str != null){
            String[] ownerUserIds = str.split(",");
            for(String ownerUserId : ownerUserIds){
                if(StrUtil.isNotBlank(ownerUserId)){
                    ++ start;
                }
            }
        }

        return start;
    }

    public R queryWorkTaskLog(Integer taskId){
        List<Record> recordList = Db.find(Db.getSqlPara("work.task.myWorkLog", Kv.by("taskId", taskId)));
        return R.ok().put("data", recordList);
    }

    private void saveWorkTaskLog(WorkTaskLog workTaskLog){
        workTaskLog.setCreateTime(DateUtil.date());
        workTaskLog.setLogId(null);
        workTaskLog.save();
    }

    @Before(Tx.class)
    private boolean getWorkTaskLog(Task task, Long userId){
        WorkTaskLog workTaskLog = new WorkTaskLog();
        workTaskLog.setUserId(userId);
        workTaskLog.setTaskId(task.getTaskId());

        Task auldTask = Task.dao.findById(task.getTaskId());

        task.update();
        Set<Map.Entry<String,Object>> newEntries = task._getAttrsEntrySet();
        Set<Map.Entry<String,Object>> oldEntries = auldTask._getAttrsEntrySet();
        newEntries.forEach(x -> {
            oldEntries.forEach(y -> {
                Object oldValue = y.getValue();
                Object newValue = x.getValue();
                if(oldValue instanceof Date){
                    oldValue = DateUtil.formatDateTime((Date) oldValue);
                }
                if(newValue instanceof Date){
                    newValue = DateUtil.formatDateTime((Date) newValue);
                }
                if(oldValue == null || "".equals(oldValue)){
                    oldValue = "空";
                }
                if(newValue == null || "".equals(newValue)){
                    newValue = "空";
                }
                if(x.getKey().equals(y.getKey()) && ! oldValue.equals(newValue)){
                    if(! "update_time".equals(y.getKey()) && ! "label_id".equals(y.getKey()) && ! "owner_user_id".equals(y.getKey())){
                        if("priority".equals(y.getKey())){
                            String value = "";
                            if(Integer.valueOf(newValue.toString()) == 1){
                                value = "普通";
                            }else if(Integer.valueOf(newValue.toString()) == 2){
                                value = "紧急";
                            }else if(Integer.valueOf(newValue.toString()) == 3){
                                value = "非常紧急";
                            }else{
                                value = "无";
                            }
                            workTaskLog.setContent("修改 优先级 为：" + value + "");
                        }else{
                            if("main_user_id".equals(y.getKey())){
                                newValue = Db.queryStr("select realname from `admin_user` where user_id = ?", newValue);
                            }
                            //修改状态时显示具体信息,后续有需求时再放开注释
                            /*if("status".equals(y.getKey())){
                                if(Integer.parseInt(newValue.toString()) == 1){
                                    workTaskLog.setContent("修改 完成状态 为: 正在进行");
                                } else if(Integer.parseInt(newValue.toString()) == 2){
                                    workTaskLog.setContent("修改 完成状态 为: 延期");
                                } else if(Integer.parseInt(newValue.toString()) == 3){
                                    workTaskLog.setContent("修改 完成状态 为: 归档");
                                } else {
                                    workTaskLog.setContent("修改 完成状态 为: 结束");
                                }
                            } else {
                                workTaskLog.setContent("修改" + getTaileName(y.getKey()) + "为: " + newValue + "");
                            }*/
                            workTaskLog.setContent("修改" + getTaileName(y.getKey()) + "为：" + newValue + "");
                        }
                        saveWorkTaskLog(workTaskLog);
                    }
                }
            });
        });
        //判断是否修改了标签
        if(task.getLabelId() != null){
            WorkTaskLabel workTaskLabel;

            if(StrUtil.isEmpty(auldTask.getLabelId())){
                //旧数据没有标签 直接添加
                List<String> labelName = Arrays.asList(task.getLabelId().split(","));
                for(String id : labelName){
                    if(StrUtil.isNotBlank(id)){
                        workTaskLabel = WorkTaskLabel.dao.findById(id);
                        workTaskLog.setContent("增加了标签 " + workTaskLabel.getName());
                        saveWorkTaskLog(workTaskLog);
                    }
                }
            }else{
                //旧数据有标签 自动添加或修改
                List<String> labelName = Arrays.asList(task.getLabelId().split(","));
                for(String id : labelName){
                    if(StrUtil.isNotBlank(id)){
                        if(! auldTask.getLabelId().contains("," + id + ",")){
                            workTaskLabel = WorkTaskLabel.dao.findById(id);
                            workTaskLog.setContent("增加了标签 " + workTaskLabel.getName());
                            saveWorkTaskLog(workTaskLog);
                        }
                    }
                }

                List<String> auldLabelName = Arrays.asList(auldTask.getLabelId().split(","));
                for(String id : auldLabelName){
                    if(StrUtil.isNotBlank(id)){
                        if(! task.getLabelId().contains("," + id + ",")){
                            workTaskLabel = WorkTaskLabel.dao.findById(id);
                            workTaskLog.setContent("删除了标签 " + workTaskLabel.getName());
                            saveWorkTaskLog(workTaskLog);
                        }
                    }

                }
            }
        }
        //判断是参与人
        if(task.getOwnerUserId() != null){
            AdminUser adminUser;
            if(StrUtil.isEmpty(auldTask.getOwnerUserId())){
                //判断旧数据没有参与人
                List<String> userIds = Arrays.asList(task.getOwnerUserId().split(","));
                for(String id : userIds){
                    if(StrUtil.isNotBlank(id)){
                        adminUser = AdminUser.dao.findById(id);
                        workTaskLog.setContent("添加 " + adminUser.getRealname() + "参与任务");
                        saveWorkTaskLog(workTaskLog);
                    }
                }
            }else{
                //判断旧数据有参与人
                List<String> userIds = Arrays.asList(task.getOwnerUserId().split(","));
                for(String id : userIds){
                    if(StrUtil.isNotBlank(id)){
                        if(! auldTask.getOwnerUserId().contains("," + id + ",")){
                            adminUser = AdminUser.dao.findById(id);
                            workTaskLog.setContent("添加 " + adminUser.getRealname() + "参与任务");
                            saveWorkTaskLog(workTaskLog);
                        }
                    }
                }
                List<String> ids = Arrays.asList(auldTask.getOwnerUserId().split(","));
                for(String id : ids){
                    if(StrUtil.isNotBlank(id)){
                        if(! task.getOwnerUserId().contains("," + id + ",")){
                            adminUser = AdminUser.dao.findById(id);
                            workTaskLog.setContent("将 " + adminUser.getRealname() + "从任务中移除");
                            saveWorkTaskLog(workTaskLog);
                        }
                    }
                }
            }
        }
        return true;
    }

    private String getTaileName(String key){
        if("name".equals(key)){
            return "任务名称";
        }else if("start_time".equals(key)){
            return "开始时间";
        }else if("stop_time".equals(key)){
            return "结束时间";
        }else if("description".equals(key)){
            return "任务描述";
        }else if("main_user_id".equals(key)){
            return "负责人";
        }
        return "";
    }

    /**
     * @author zxy
     * 添加任务与业务关联
     */
    public R saveTaskRelation(TaskRelation taskRelation, Long userId){
        Db.delete("delete from `task_relation` where task_id = ?", taskRelation.getTaskId());
        taskRelation.setCreateTime(DateUtil.date());
        return taskRelation.save() ? R.ok() : R.error();
    }


    @Before(Tx.class)
    public R deleteTask(Integer taskId){
        Task task = new Task().dao().findById(taskId);
        if(task == null){
            return R.error("任务不存在！");
        }
        boolean bol;
        if(task.getPid() != 0){
            bol = task.delete();
        }else {
            bol = Db.update("update task set ishidden = 1,hidden_time = now() where task_id = ?", taskId) > 0;
        }
        return bol ? R.ok() : R.error();
    }


    /**
     * @author zxy
     * crm查询任务
     */
    public R queryTaskRelation(BasePageRequest<TaskRelation> basePageRequest){
        TaskRelation relation = basePageRequest.getData();
        if(AuthUtil.oaAuth(relation.toRecord())){
            return R.noAuth();
        }
        Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("work.task.queryTaskRelation", Kv.by("businessIds", relation.getBusinessIds()).set("contactsIds", relation.getContactsIds()).set("contractIds", relation.getContractIds()).set("customerIds", relation.getCustomerIds())));
        paginate.getList().forEach(this::composeUser);
        return R.ok().put("data", paginate);
    }

    private void composeUser(Record record){
        Integer createUserId = record.getInt("create_user_id");
        record.set("createUser",Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", createUserId));
        Integer mainUserId = record.getInt("main_user_id");
        record.set("mainUser",Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", mainUserId));
        String ownerUserId = record.getStr("owner_user_id");
        List<Record> ownerUserList = new ArrayList<>();
        TagUtil.toSet(ownerUserId).forEach(userId-> ownerUserList.add(Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", userId)));
        record.set("ownerUserList",ownerUserList);
    }


    public R getTaskList(BasePageRequest basePageRequest, String labelId, String ishidden){
        Page<Record> recordList = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("work.task.myTask", Kv.by("userId", BaseUtil.getUser().getUserId()).set("labelId", labelId).set("ishidden", ishidden)));
        return R.ok().put("data", recordList);
    }

    public R archiveByTaskId(Integer taskId){
        int update = Db.update("update  `task` set is_archive = 1,archive_time = now() where task_id = ?", taskId);
        return update > 0 ? R.ok() : R.error();
    }

    /**
     * 根据条件查询任务列表
     * @param basePageRequest
     * @return
     */
    public R queryList(BasePageRequest<Task> basePageRequest){
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv= Kv.by("taskName", jsonObject.getString("taskName"))
                .set("mainUserId", jsonObject.getString("mainUserId"))
                .set("ownerUserId", jsonObject.getString("ownerUserId"))
                .set("status", jsonObject.getString("status"))
                .set("classId", jsonObject.getString("classId"))
                .set("labelId", jsonObject.getString("labelId"))
                .set("pid", jsonObject.getString("pid"))
                .set("priority", jsonObject.getString("priority"))
                .set("workId", jsonObject.getString("workId"))
                .set("sprintId", jsonObject.getString("sprintId"))
                .set("isArchive", jsonObject.getString("isArchive"))
                .set("batchId", jsonObject.getString("batchId"))
                .set("orderBy", jsonObject.getInteger("orderBy"));

        //如果有ifUser参数且等于0, 则根据当前用户查询, 否则查询全部
        if(jsonObject.getInteger("ifUser") != null){
            if(jsonObject.getInteger("ifUser") == 0){
                kv.set("createUserId", BaseUtil.getUser().getUserId());
            }
        }
        if(basePageRequest.getPageType() == 0){
            List<Record> recordList = Db.find(Db.getSqlPara("work.task.queryList", kv));
            return R.ok().put("data", recordList);
        } else {
            Page<Record> pageList = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("work.task.queryList", kv));
            return R.ok().put("data", pageList);
        }
    }

    /**
     * 根据通用标签查询任务列表 ---- 周报
     * @return
     */
    public R getWeekly(Integer workId, Date startTime, Date stopTime){
        Record record = new Record();
        List<Record> allClassList;
        //查询里程碑节点数据列表
        if(workId != null && workId != 0){
            //根据项目查询关联里程碑
            allClassList = Db.find("select * from work_task_class where status = 0 and work_id = ?", workId);
        } else {
            //查询全部里程碑
            allClassList = Db.find("select * from work_task_class where status = 0");
        }
        //遍历里程碑节点, 组装数据
        allClassList.forEach(ivan->{
            Kv kv= Kv.by("classId", ivan.get("class_id"))
                    .set("startTime", startTime)
                    .set("stopTime", stopTime);
            if(workId != null && workId != 0){
                kv.set("workId", workId);
            }
            //查询该里程碑关联任务列表
            List<Record> taskList = Db.find(Db.getSqlPara("work.task.queryListByWeekly", kv));
            ivan.set("taskList", taskList);
        });

        record.set("classList", allClassList);
        record.set("allCount", Db.queryInt("select count(*) from task where find_in_set(1, label_id) and ishidden = 0"));
        record.set("completedCount", Db.queryInt("select count(*) from task where find_in_set(1, label_id) and ishidden = 0 and `status` = 5"));
        record.set("undoneCount", Db.queryInt("select count(*) from task where find_in_set(1, label_id) and ishidden = 0 and `status` not in (3,5)"));
        record.set("extensionCount", Db.queryInt("select count(*) from task where find_in_set(1, label_id) and ishidden = 0 and `status` = 2"));

        return R.ok().put("data", record);
    }
}
