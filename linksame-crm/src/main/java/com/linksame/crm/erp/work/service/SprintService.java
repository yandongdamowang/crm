package com.linksame.crm.erp.work.service;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.erp.work.entity.Task;
import com.linksame.crm.erp.work.entity.TaskSprint;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.DateUtil;
import com.linksame.crm.utils.R;
import org.elasticsearch.common.inject.Inject;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Ivan
 * @date 2020/5/7 15:58
 * @Description 任务冲刺业务层
 */
public class SprintService {

    @Inject
    private TaskService taskService;

    /**
     * 查询活跃冲刺列表数据
     * @param basePageRequest
     * @return
     */
    public R queryList(BasePageRequest<TaskSprint> basePageRequest){
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv= Kv.by("sprintName", jsonObject.getString("sprintName"))
                .set("status", jsonObject.getString("status"));
        //如果有ifUser参数且等于0, 则根据当前用户查询, 否则查询全部
        if(jsonObject.getInteger("ifUser") != null){
            if(jsonObject.getInteger("ifUser") == 0){
                kv.set("createUserId", BaseUtil.getUser().getUserId());
            }
        }
        //根据条件返回数据结构
        if(basePageRequest.getPageType() == 0){
            List<Record> recordList = Db.find(Db.getSqlPara("task.sprint.queryList", kv));
            recordList.forEach(e->{
                List<AdminUser> adminUserList = new ArrayList<>();
                //读取任务冲刺关联的任务数据
                List<Record> taskList = Db.find("select * from task where sprint_id = ? and ishidden = 0", e.getInt("sprint_id"));
                for(Record task : taskList){
                    //读取任务负责人数据
                    AdminUser user = AdminUser.dao.findById(task.getInt("main_user_id"));
                    adminUserList.add(user);
                }
                e.set("mainUserList", adminUserList);
                //查询关联活跃冲刺任务数量
                Integer taskCount = Db.queryInt("select count(*) as count from task where sprint_id = ?", e.getInt("sprint_id"));
                e.set("taskCount", taskCount);
            });
            return R.ok().put("data", recordList);
        } else {
            Page<Record> pageList = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("task.sprint.queryList", kv));
            pageList.getList().forEach(e->{
                List<AdminUser> adminUserList = new ArrayList<>();
                //读取任务冲刺关联的任务数据
                List<Record> taskList = Db.find("select * from task where sprint_id = ? and ishidden = 0", e.getInt("sprint_id"));
                for(Record task : taskList){
                    //读取任务负责人数据
                    AdminUser user = AdminUser.dao.findById(task.getInt("main_user_id"));
                    adminUserList.add(user);
                }
                e.set("mainUserList", adminUserList);
                //查询关联活跃冲刺任务数量
                Integer taskCount = Db.queryInt("select count(*) as count from task where sprint_id = ?", e.getInt("sprint_id"));
                e.set("taskCount", taskCount);
            });

            return R.ok().put("data", pageList);
        }
    }

    /**
     * 添加活跃冲刺
     * @param taskSprint    活跃冲刺对象
     * @return
     */
    public R setSprint(TaskSprint taskSprint){
        Boolean bol;
        //新增
        if(taskSprint.getSprintId() == null){
            //默认生成活跃冲刺任务名称, 根据
            if(StringUtils.isEmpty(taskSprint.getName())){
                taskSprint.setName(defaultSprintName());
            }
            taskSprint.setCreateUserId(BaseUtil.getUserId());
            taskSprint.setCreateTime(new Date());
            bol = taskSprint.save();
        } else {
            //如果设置状态为开启冲刺, 必须其他冲刺任务不为开启状态
            if(taskSprint.getStatus() != null && taskSprint.getStatus() == 1 ){
                Integer count = Db.queryInt("select count(*) from task_sprint where status = 1 and is_del = 0");
                if(count > 0){
                    return R.error("已有其他进行中的冲刺任务, 设置失败");
                }
            }
            //编辑
            bol = taskSprint.update();
        }
        return bol ? R.ok() : R.error();
    }

    //名称默认冲刺规则
    private String defaultSprintName(){
        String newName;
        //获取数据库中最后一条数据
        Record taskSprint = Db.findFirst("select * from task_sprint order by sprint_id DESC limit 1");
        //根据该数据的名称规则生成当前添加数据的名称
        String sprintNmae = taskSprint.getStr("name");
        //截取除最后一位的字符串
        String frontStr = sprintNmae.substring(0, sprintNmae.length() - 1);
        //截取最后一位字符
        String behindStr = sprintNmae.substring(sprintNmae.length() - 1);
        if(isInteger(behindStr)){
            newName = frontStr + (Integer.parseInt(behindStr) + 1);
        } else {
            newName = sprintNmae + 2;
        }

        return newName;
    }

    //判断是否为整数
    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 删除活跃任务
     * @param sprintId  ID
     * @return
     */
    @Before(Tx.class)
    public R delSprint(Integer sprintId){
        TaskSprint sprint = TaskSprint.dao.findById(sprintId);
        if(sprint == null){
            return R.error("活跃任务数据不存在");
        }
        sprint.setIsDel(1);
        boolean bol = sprint.update();
        if(bol){
            List<Task> taskList = Task.dao.find("select * from task where sprint_id = ? and ishidden = 0", sprintId);
            taskList.forEach(ivan->{
                ivan.setStatus(0);
                ivan.setSprintId(0);
                ivan.update();
            });
        }
        return bol ? R.ok() : R.error();
    }

    /**
     * 查询任务冲刺数据详情(根据各状态组装list返回页面)
     * @param sprintId
     * @return
     */
    public R querySprintInfo(Integer sprintId){
        List<Map<String, Object>> resultList = new ArrayList<>();
        //1. 查询冲刺任务数据, 获取该任务开始时间与截止时间
        TaskSprint taskSprint = TaskSprint.dao.findById(sprintId);
        //开始时间
        String startDatetime = DateUtil.dateToStrLong(taskSprint.getStartTime());
        //截止时间
        String endDatetime = DateUtil.dateToStrLong(taskSprint.getEndTime());
        List<String> dayList = DateUtil.getDays(startDatetime, endDatetime);
        //遍历日期节点, 查询与该日期匹配的截止日期任务
        for(String dateStr : dayList){
            Map<String, Object> map = new HashMap<>();
            Integer expectCount = 0;
            Integer remainingCount = 0;
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtil.strToDate(dateStr));
            //获取该日期的起始时间
            Calendar startResult = DateUtil.getStartDate(cal);
            String startDateStr = DateUtil.dateToStrLong(startResult.getTime());
            //获取该日期的结束时间
            Calendar endResult = DateUtil.getEndDate(cal);
            String endDateStr = DateUtil.dateToStrLong(endResult.getTime());
            //获取该时间区间状态为完成的任务----期望值
            expectCount = Db.queryInt("select count(*) as count from task where stop_time BETWEEN ? and ? and status = 5", startDateStr, endDateStr);
            map.put("date", dateStr);
            map.put("expectCount", expectCount);
            //获取剩余值
            remainingCount = Db.queryInt("select count(*) as count from task where stop_time BETWEEN ? and ? and status in (0,1,2)", startDateStr, endDateStr);
            map.put("remainingCount", remainingCount);
            resultList.add(map);
        }

        return R.ok().put("data", resultList);
    }
}
