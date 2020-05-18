package com.linksame.crm.erp.work.service;

import java.util.Date;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.erp.admin.entity.AdminMenu;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.erp.admin.service.AdminFileService;
import com.linksame.crm.erp.admin.service.AdminMenuService;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.work.entity.Task;
import com.linksame.crm.erp.work.entity.Work;
import com.linksame.crm.erp.work.entity.WorkTaskClass;
import com.linksame.crm.erp.work.entity.WorkUser;
import com.linksame.crm.utils.AuthUtil;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.utils.TagUtil;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class WorkService{

    @Inject
    private AdminFileService adminFileService;
    @Inject
    private WorkbenchService workbenchService;
    @Inject
    private AdminMenuService adminMenuService;

    @Before(Tx.class)
    public R setWork(Work work){
        if(Arrays.asList(work._getAttrNames()).contains("name")&&StrUtil.isEmpty(work.getName())){
            return R.error("项目名称不能为空！");
        }
        Long userId = BaseUtil.getUser().getUserId();
        boolean bol;
        if(work.getWorkId() == null){
            Set<Long> ownerUserIds = new HashSet<>();
            ownerUserIds.add(userId);
            if(work.getOwnerUserId() != null){
                ownerUserIds.addAll(TagUtil.toLongSet(work.getOwnerUserId()));
            }
            if(work.getIsOpen() == 1){
                //公开项目删除负责人
                ownerUserIds.clear();
            }
            work.setOwnerUserId(TagUtil.fromLongSet(ownerUserIds));
            work.setCreateUserId(userId);
            work.setCreateTime(new Date());
            bol = work.save();
            WorkTaskClass workTaskClass = new WorkTaskClass();
            workTaskClass.setClassId(0);
            workTaskClass.setName("要做");
            workTaskClass.setCreateTime(new Date());
            workTaskClass.setCreateUserId(userId);
            workTaskClass.setWorkId(work.getWorkId());
            workTaskClass.setOrderNum(1);
            workTaskClass.save();
            workTaskClass.setName("在做");
            workTaskClass.setOrderNum(2);
            workTaskClass.save();
            workTaskClass.setName("待定");
            workTaskClass.setOrderNum(3);
            workTaskClass.save();
            ownerUserIds.forEach(ownerUserId -> {
                WorkUser workUser = new WorkUser();
                workUser.setWorkId(work.getWorkId());
                workUser.setUserId(ownerUserId);
                if(ownerUserId.equals(userId)){
                    workUser.setRoleId(BaseConstant.SMALL_WORK_ADMIN_ROLE_ID);
                    workUser.save();
                }else{
                    workUser.setRoleId(BaseConstant.SMALL_WORK_EDIT_ROLE_ID);
                    workUser.save();
                }
            });
        }else{
            if(! AuthUtil.isWorkAuth(work.getWorkId().toString(), "work:update")){
                return R.noAuth();
            }
            Integer workId = work.getWorkId();
            Map<String,Object> columns = work.toRecord().getColumns();
            if(columns.containsKey("owner_user_id")){
                if(! ObjectUtil.isNull(columns.get("owner_user_id"))){
                    Work oldWork = new Work().findById(workId);
                    work.setOwnerUserId(TagUtil.fromString(work.getOwnerUserId()));
                    Set<Long> oldOwnerUserIds = TagUtil.toLongSet(oldWork.getOwnerUserId());
                    Set<Long> ownerUserIds = TagUtil.toLongSet(work.getOwnerUserId());
                    Collection<Long> intersection = CollectionUtil.intersection(oldOwnerUserIds, ownerUserIds);
                    oldOwnerUserIds.removeAll(intersection);
                    ownerUserIds.removeAll(intersection);
                    for(Long next : oldOwnerUserIds){
                        leave(work.getWorkId().toString(), next);
                        Db.delete("delete from `work_user` where work_id = ? and user_id = ?", workId, next);
                    }
                    for(Long ownerUserId : ownerUserIds){
                        WorkUser workUser = new WorkUser();
                        workUser.setWorkId(work.getWorkId());
                        workUser.setUserId(ownerUserId);
                        workUser.setRoleId(BaseConstant.SMALL_WORK_EDIT_ROLE_ID);
                        workUser.save();
                    }
                }else{
                    Db.delete("delete from `work_user` where work_id = ?", workId);
                    work.setOwnerUserId("," + userId + ",");
                    WorkUser workUser = new WorkUser();
                    workUser.setWorkId(work.getWorkId());
                    workUser.setUserId(userId);
                    workUser.setRoleId(BaseConstant.SMALL_WORK_ADMIN_ROLE_ID);
                    workUser.save();
                }
            }
            if(work.getIsOpen() != null){
                if(work.getIsOpen() == 1){
                    //公开项目删除负责人
                    work.setOwnerUserId(null);
                    Db.delete("delete from `work_user` where work_id = ?", workId);
                }else if(work.getIsOpen() == 0){
                    List<Long> userList = Db.query("select user_id from `admin_user` where status != 0");
                    userList.remove(Long.valueOf(userId));
                    List<WorkUser> workUserList = new ArrayList<>();
                    WorkUser nowWorkUser = new WorkUser();
                    nowWorkUser.setWorkId(work.getWorkId());
                    nowWorkUser.setUserId(userId);
                    nowWorkUser.setRoleId(BaseConstant.SMALL_WORK_ADMIN_ROLE_ID);
                    workUserList.add(nowWorkUser);
                    userList.forEach(id -> {
                        WorkUser workUser = new WorkUser();
                        workUser.setWorkId(work.getWorkId());
                        workUser.setUserId(id);
                        workUser.setRoleId(BaseConstant.SMALL_WORK_EDIT_ROLE_ID);
                        workUserList.add(workUser);
                    });
                    Db.batchSave(workUserList, 500);
                    userList.add(Long.valueOf(userId));
                    work.setOwnerUserId(TagUtil.fromSet(userList.stream().map(Long::intValue).collect(Collectors.toList())));
                }
            }
            if(work.getStatus() != null && work.getStatus() == 3){
                work.setArchiveTime(new Date());
            }
            bol = work.update();
        }
        return bol ? queryOwnerRoleList(work.getWorkId()).put("work", work) : R.error();
    }

    public R deleteWork(String workId){
        Db.delete("delete from `task_relation` where task_id in (select `task`.task_id from `task` where work_id = ?)", workId);
        Db.delete("delete from `task` where work_id = ?", workId);
        Db.delete("delete from `work_user` where work_id = ?", workId);
        int update = Db.delete("delete from `work` where work_id = ?", workId);
        return update > 0 ? R.ok() : R.error();
    }

    public R queryWorkNameList(){
        List<Record> recordList;
        if(AuthUtil.isWorkAdmin()){
            recordList = Db.find(Db.getSqlPara("work.queryWorkNameList"));
        }else{
            recordList = Db.find(Db.getSqlPara("work.queryWorkNameList", Kv.by("userId", BaseUtil.getUser().getUserId())));
        }
        return R.ok().put("data", recordList);
    }

    /**
     * 根据项目id查询任务板
     * @param jsonObject    项目编号----workId
     * @return  返回任务列表及任务内无限层级的子任务
     *
     * Ivan修改于 2020-03-31
     * 修改原接口,新增时间轴任务板查询功能以及修改原里程碑任务板功能,修改内容如下:
     * 1.根据任务大类直属任务节点的最靠后截止日期,作为任务时间轴横向大类所展示的截止日期,附带该日期对应的任务id,便于后续可直接对该任务进行修改
     * 2.根据客户需求,递归查询任务的所有子任务,无限层级全部展示
     */
    public R queryTaskByWorkId(JSONObject jsonObject){
        Integer workId = jsonObject.getInteger("workId");
        List<Record> classList = Db.find("select class_id as classId, name as className from `work_task_class` where work_id = ? order by order_num", workId);
        LinkedList<Record> linkedList = new LinkedList<>(classList);
        Record item = new Record();
        item.set("className", "(未分组)");
        item.set("classId", - 1);
        linkedList.addFirst(item);
        List<Record> finalClassList = new CopyOnWriteArrayList<>(linkedList);

        finalClassList.forEach(workClass -> {
            //查询条件设置(项目id,截止时间状态,负责人id,标签id,任务分类id)
            Kv kv = Kv.by("workId", workId).set("stopTimeType", jsonObject.getInteger("stopTimeType"))
                    .set("userIds", jsonObject.getJSONArray("mainUserId"))
                    .set("labelIds", jsonObject.getJSONArray("labelId"))
                    .set("classId", workClass.getInt("classId"));
            //设置排序规则
            Integer orderType = jsonObject.getInteger("orderType");
            switch (orderType) {
                case 1:
                    //根据排序字段升序
                    kv.set("order","order by b.order_num , a.order_num");
                    break;
                case 2:
                    //根据排序字段倒序
                    kv.set("order","order by b.order_num , a.order_num desc");
                    break;
                case 3:
                    //优先级最高
                    kv.set("order","order by b.order_num , a.priority desc");
                    break;
                case 4:
                    //优先级最低
                    kv.set("order","order by b.order_num , a.priority");
                    break;
                case 5:
                    //截止时间最早
                    kv.set("order","order by b.order_num , a.stop_time");
                    break;
                case 6:
                    //截止时间最晚
                    kv.set("order","order by b.order_num , a.stop_time desc");
                    break;
                case 7:
                    //开始时间最早
                    kv.set("order","order by b.order_num , a.start_time");
                    break;
                case 8:
                    //开始时间最晚
                    kv.set("order","order by b.order_num , a.start_time desc");
                    break;
                case 9:
                    //创建时间最早
                    kv.set("order","order by b.order_num , a.create_time");
                    break;
                case 10:
                    //创建时间最晚
                    kv.set("order","order by b.order_num , a.create_time desc");
                    break;
                case 11:
                    //更新时间最早
                    kv.set("order","order by b.order_num , a.update_time");
                    break;
                case 12:
                    //更新时间最晚
                    kv.set("order","order by b.order_num , a.update_time desc");
                    break;
                default:
                    kv.set("order","order by b.order_num");
            }
            List<Record> recordList = Db.find(Db.getSqlPara("work.queryTaskByWorkId", kv));
            workClass.set("count", recordList.size());
            if(recordList.size() == 0){
                if(workClass.getInt("classId") != - 1){
                    workClass.set("list", new ArrayList<>());
                }else{
                    finalClassList.remove(workClass);
                }
            }else{
                //设置截止时间最靠后的任务数据
                Record stopTask = Db.findFirst("select task_id as stopTaskId,max(stop_time) as stopTime from task where class_id = ? group by task_id order by stop_time desc limit 1", workClass.getInt("classId"));
                workClass.set("stopTaskId", stopTask.get("stopTaskId"));
                workClass.set("stopTime", stopTask.get("stopTime"));
                workbenchService.taskListTransfer(recordList);
                //有父元素的数据需要从原list中抹除
                Iterator<Record> it = recordList.iterator();
                while(it.hasNext()){
                    Record x = it.next();
                    if(x.getInt("pid") != 0) it.remove();
                }
                //递归
                recData(recordList);
                workClass.set("list", recordList);
            }
        });
        return R.ok().put("data", finalClassList);
    }
    //递归
    private void recData(List<Record> itemList) {
        //遍历出父id等于参数的id，add进子节点集合
        for (Record mu : itemList) {
            //查询预付款表, 是否存在关联该任务的数据, 并set关联数据
            Record contractPayment = Db.findFirst("select * from pmp_contract_payment where payment_clause = ? and status = 1 and is_deleted = 0", mu.getInt("task_id"));
            if(contractPayment != null){
                mu.set("contractPayment", contractPayment);
            } else {
                mu.set("contractPayment", new PmpContractPayment());
            }

            List<Record> childList = Db.find("select * from task where pid = ?", mu.getInt("task_id"));
            if(CollectionUtil.isNotEmpty(childList)){
                mu.set("list", childList);
                recData(childList);
            }
        }
    }

    public R queryTaskFileByWorkId(BasePageRequest<JSONObject> data){
        Page<Record> workFile = Db.paginate(data.getPage(), data.getLimit(), Db.getSqlPara("work.queryTaskFileByWorkId", Kv.by("workId", data.getData().getInteger("workId"))));
        return R.ok().put("data", workFile);
    }

    public R queryArchiveWorkList(BasePageRequest request){
        Page<Record> recordPage = Db.paginate(request.getPage(), request.getLimit(), "select work_id,archive_time,name,color ", "from work where status = 3");
        return R.ok().put("data", recordPage);
    }

    public R workStatistics(String workId){
        Long userId1 = BaseUtil.getUserId();
        if("all".equals(workId)){
            Record taskStatistics = new Record();
            List<Record> memberTaskStatistics = new ArrayList<>();
            if(AuthUtil.isWorkAdmin()){
                taskStatistics = Db.findFirst(Db.getSqlPara("work.workStatistics"));
                memberTaskStatistics = Db.find("select user_id,img,realname from `admin_user` where user_id in (select main_user_id from `task` where ishidden = 0 and work_id > 0)");
                memberTaskStatistics.forEach(record -> {
                    Record first = Db.findFirst(Db.getSqlPara("work.workStatistics", Kv.by("mainUserId", record.getInt("user_id"))));
                    record.setColumns(first);
                });
            }else{
                List<Record> recordList = Db.find(Db.getSqlPara("work.queryOwnerWorkIdList", Kv.by("userId", userId1)));
                List<Integer> workIds = recordList.stream().map(record -> record.getInt("work_id")).collect(Collectors.toList());
                if(workIds.size() == 0){
                    taskStatistics.set("unfinished", 0).set("overdue", 0).set("complete", 0).set("archive", 0).set("completionRate", 0).set("overdueRate", 0);
                }else{
                    taskStatistics = Db.findFirst(Db.getSqlPara("work.workStatistics", Kv.by("workIds", CollectionUtil.join(workIds, ","))));
                    memberTaskStatistics = Db.find(Db.getSql("work.getTaskOwnerOnWork"), CollectionUtil.join(workIds, ","));
                    memberTaskStatistics.forEach(record -> {
                        Record first = Db.findFirst(Db.getSqlPara("work.workStatistics", Kv.by("mainUserId", record.getInt("user_id")).set("workIds", CollectionUtil.join(workIds, ","))));
                        record.setColumns(first);
                    });
                }
            }

            return R.ok().put("data", Kv.by("taskStatistics", taskStatistics).set("memberTaskStatistics", memberTaskStatistics));
        }
        Record taskStatistics = Db.findFirst(Db.getSqlPara("work.workStatistics", Kv.by("workId", workId)));
        String ownerUserId = Db.queryStr("select owner_user_id from `work` where work_id = ?", workId);
        List<Record> ownerList = new ArrayList<>();
        for(Integer userId : TagUtil.toSet(ownerUserId)){
            Record ownerUser = Db.findFirst("select b.user_id,realname,img from `work_user` a left join `admin_user` b on a.user_id = b.user_id where a.work_id = ? and a.user_id = ? and a.role_id = ?", workId, userId, BaseConstant.SMALL_WORK_ADMIN_ROLE_ID);
            if(ownerUser != null){
                ownerList.add(ownerUser);
            }
        }
        List<Record> userList = new ArrayList<>();
        for(Integer userId : TagUtil.toSet(ownerUserId)){
            userList.add(Db.findFirst("select user_id,realname,username,img from `admin_user` where user_id = ?", userId));
        }
        List<Record> classStatistics = new ArrayList<>();
        List<Record> labelStatistics = new ArrayList<>();
        List<Record> recordList = Db.find("select class_id classId,name className from work_task_class a  where a.work_id = ?", workId);
        Map<String,Object> classMap = new HashMap<>();
        recordList.forEach(record -> classMap.put(record.getStr("classId"), record.getStr("className")));
        classMap.forEach((classId, name) -> {
            Record first = Db.findFirst("select count(status = 5 or null) as complete,count(status != 5 or null) as undone from task where class_id = ? and work_id = ? and ishidden = 0 and (is_archive = 0 or (is_archive = 1 and status = 5))", classId, workId);
            first.set("className", classMap.get(classId));
            classStatistics.add(first);
        });
        List<Record> labelList = Db.find("select label_id,status from `task` where work_id  = ? and label_id is not null and ishidden = 0 and (is_archive = 0 or (is_archive = 1 and status = 5))", workId);
        List<String> labelIdList = labelList.stream().map(record -> record.getStr("label_id")).collect(Collectors.toList());
        Set<Integer> labelIdSet = new HashSet<>(toList(labelIdList));
        Map<Integer,Record> labelMap = new HashMap<>();
        labelIdSet.forEach(id -> {
            Record record = Db.findFirst("select label_id,name,color from work_task_label where label_id = ?", id);
            labelMap.put(record.getInt("label_id"), record);
        });
        labelMap.forEach((id, record) -> {
            AtomicReference<Integer> complete = new AtomicReference<>(0);
            AtomicReference<Integer> undone = new AtomicReference<>(0);
            labelList.forEach(label -> {
                if(label.getStr("label_id").contains(id.toString())){
                    if(label.getInt("status") == 1){
                        undone.getAndSet(undone.get() + 1);
                    }else if(label.getInt("status") == 5){
                        complete.getAndSet(complete.get() + 1);
                    }
                }
            });
            record.set("complete", complete.get());
            record.set("undone", undone.get());
            labelStatistics.add(record);
        });
        List<Record> memberTaskStatistics = memberTaskStatistics(workId);
        Kv result = Kv.by("taskStatistics", taskStatistics).set("classStatistics", classStatistics).set("labelStatistics", labelStatistics).set("memberTaskStatistics", memberTaskStatistics).set("userList", userList).set("ownerList", ownerList);
        return R.ok().put("data", result);
    }

    public List<Integer> toList(List<String> labelList){
        List<Integer> list = new ArrayList<>();
        if(labelList == null || labelList.size() == 0){
            return list;
        }
        labelList.forEach(ids -> {
            if(StrUtil.isNotEmpty(ids)){
                for(String id : ids.split(",")){
                    if(StrUtil.isNotEmpty(id)){
                        list.add(Integer.valueOf(id));
                    }
                }
            }
        });
        return list;
    }


    /**
     * 项目成员任务统计
     */
    public List<Record> memberTaskStatistics(String workId){
        List<Record> list = new ArrayList<>();
        Work work = new Work().findById(workId);
        if(work.getIsOpen() == 1){
            list = Db.find(Db.getSql("work.getTaskOwnerOnWork"), workId);
            list.forEach(record -> {
                Record first = Db.findFirst(Db.getSqlPara("work.workStatistics", Kv.by("mainUserId", record.getInt("user_id")).set("workIds", workId)));
                record.setColumns(first);
            });
        }else{
            String ownerUserIds = Db.queryStr("select owner_user_id from work where work_id = ?", workId);
            if(StrUtil.isEmpty(ownerUserIds)){
                return list;
            }
            for(String userId : ownerUserIds.split(",")){
                if(StrUtil.isEmpty(userId)){
                    continue;
                }
                Record user = Db.findFirst("select user_id,realname,img from admin_user where user_id = ?", userId);
                Record first = Db.findFirst(Db.getSqlPara("work.workStatistics", Kv.by("workId", workId).set("userId", userId)));
                user.setColumns(first);
                list.add(user);
            }
        }
        return list;
    }

    public R queryWorkOwnerList(String workId){
        String ownerUserId = Db.queryStr("select owner_user_id from `work` where work_id = ?", workId);
        List<Record> userList = new ArrayList<>();
        for(Integer userId : TagUtil.toSet(ownerUserId)){
            userList.add(Db.findFirst("select user_id,realname,username,img from `admin_user` where user_id = ?", userId));
        }
        return R.ok().put("data", userList);
    }

    public R updateOrder(JSONObject jsonObject){
        String updateSql = "update `task` set class_id = ?,order_num = ? where task_id = ?";
        if(jsonObject.containsKey("toList")){
            JSONArray tolist = jsonObject.getJSONArray("toList");
            Integer toId = jsonObject.getInteger("toId");
            for(int i = 1; i <= tolist.size(); i++){
                Db.update(updateSql, toId, i, tolist.get(i - 1));
            }
        }
        if(jsonObject.containsKey("fromList")){
            JSONArray fromList = jsonObject.getJSONArray("fromList");
            Integer fromId = jsonObject.getInteger("fromId");
            for(int i = 1; i <= fromList.size(); i++){
                Db.update(updateSql, fromId, i, fromList.get(i - 1));
            }
        }
        return R.ok();

    }

    public R leave(String workId, Long userId){
        Work work = new Work().findById(workId);
        if(work.getCreateUserId().equals(userId)){
            return R.error("项目创建人不可以退出");
        }
        Db.update(Db.getSqlPara("work.leave", Kv.by("workId", workId).set("userId", userId)));
        Db.update(Db.getSqlPara("work.leave1", Kv.by("workId", workId).set("userId", userId)));
        Set<Long> ownerUserIds = TagUtil.toLongSet(work.getOwnerUserId());
        ownerUserIds.remove(userId);
        work.setOwnerUserId(TagUtil.fromLongSet(ownerUserIds));
        boolean update = work.update();
        Db.update("delete from `work_user` where work_id = ? and user_id = ?", workId, userId);
        return update ? R.ok() : R.error();
    }

    public R getWorkById(String workId){
        Work work = new Work().findById(workId);
        if(work == null){
            return R.error("项目不存在！");
        }
        int isUpdate = 0;
        if(AuthUtil.isWorkAdmin() || BaseUtil.getUser().getRoles().contains(BaseConstant.SMALL_WORK_ADMIN_ROLE_ID)){
            isUpdate = 1;
        }
        AdminUser user = BaseUtil.getUser();
        Long userId = BaseUtil.getUserId();
        Integer roleId = Db.queryInt("select role_id from `work_user` where work_id = ? and user_id = ?", workId, userId);
        JSONObject root = new JSONObject();
        List<Record> menuRecords = Db.find(Db.getSql("admin.menu.queryWorkMenuByRoleId"), roleId);
        Integer workMenuId = Db.queryInt("select menu_id from `admin_menu` where parent_id = 0 and realm = 'work'");
        List<AdminMenu> adminMenus = adminMenuService.queryMenuByParentId(workMenuId);
        JSONObject object = new JSONObject();
        adminMenus.forEach(menu -> {
            JSONObject authObject = new JSONObject();
            List<AdminMenu> chlidMenus = adminMenuService.queryMenuByParentId(menu.getMenuId());
            if((roleId != null && roleId.equals(BaseConstant.SMALL_WORK_ADMIN_ROLE_ID)) || userId.equals(BaseConstant.SUPER_ADMIN_USER_ID) || user.getRoles().contains(BaseConstant.WORK_ADMIN_ROLE_ID) ||  user.getRoles().contains(BaseConstant.SUPER_ADMIN_ROLE_ID)){
                chlidMenus.forEach(child -> {
                    authObject.put(child.getRealm(), true);
                });
            }else{
                if(work.getIsOpen() == 1){
                    chlidMenus.forEach(child -> {
                        if("update".equals(child.getRealm()) && ! work.getCreateUserId().equals(userId)){
                            return;
                        }
                        authObject.put(child.getRealm(), true);
                    });
                }else{
                    menuRecords.forEach(record -> {
                        if(menu.getMenuId().equals(record.getInt("parent_id"))){
                            authObject.put(record.getStr("realm"), true);
                        }
                    });
                }
            }
            if(! authObject.isEmpty()){
                object.put(menu.getRealm(), authObject);
            }
        });
        if(! object.isEmpty()){
            root.put("work", object);
        }
        work.put("permission", Kv.by("isUpdate", isUpdate)).put("authList", root);
        return R.ok().put("data", work);
    }

    /**
     * 查询项目角色类型列表
     *
     * @author wyq
     */
    public R queryRoleList(){
        return R.ok().put("data", Db.find(Db.getSql("work.queryRoleList")));
    }

    /**
     * @author wyq
     * 查询项目成员所属角色列表
     */
    public R queryOwnerRoleList(Integer workId){
        Integer isOpen = Db.queryInt("select is_open from work where work_id = ?", workId);
        if(workId == 0 || isOpen == 1){
            return R.ok().put("data", Db.find("sel" +
                    "ect user_id,realname from admin_user"));
        }
        return R.ok().put("data", Db.find(Db.getSql("work.queryOwnerRoleList"), workId));
    }

    /**
     * @author wyq
     * 保存项目成员角色设置
     */
    @Before(Tx.class)
    public R setOwnerRole(JSONObject jsonObject){
        List<WorkUser> workUserList = jsonObject.getJSONArray("list").toJavaList(WorkUser.class);
        Integer workId = jsonObject.getInteger("workId");
        workUserList.forEach(workUser -> workUser.setWorkId(workId));
        Db.delete("delete from work_user where work_id = ?", workId);
        Db.batchSave(workUserList, 100);
        List<Record> recordList = Db.find(Db.getSql("work.queryOwnerRoleList"), workId);
        return R.ok().put("data", recordList);
    }

    @Before(Tx.class)
    public R deleteTaskList(String workId, String classId){
        Db.update("update  `task` set ishidden = 1,class_id = null,hidden_time = now() where class_id = ? and work_id = ? and is_archive != 1", classId, workId);
        boolean delete = new WorkTaskClass().deleteById(classId);
        return delete ? R.ok() : R.error();
    }

    public R archiveTask(String classId){
        Integer count = Db.queryInt("select count(*) from `task` where class_id = ? and status = 5 and ishidden = 0", classId);
        if(count == 0){
            return R.error("暂无已完成任务，归档失败!");
        }
        int update = Db.update("update  `task` set is_archive = 1,archive_time = now() where class_id = ? and status = 5 and ishidden = 0", classId);
        return update > 0 ? R.ok() : R.error();
    }

    public R archList(String workId){
        List<Record> recordList = Db.find(Db.getSql("work.archList"), workId);
        workbenchService.taskListTransfer(recordList);
        return R.ok().put("data", recordList);
    }

    public R remove(Integer userId, Integer workId){
        Integer roleId = Db.queryInt("select role_id from `work_user` where work_id = ? and user_id = ?", workId, userId);
        if(roleId.equals(BaseConstant.SMALL_WORK_ADMIN_ROLE_ID)){
            return R.error("管理员不能被删除！");
        }
        Work work = new Work().findById(workId);
        Set<Integer> userIds = TagUtil.toSet(work.getOwnerUserId());
        userIds.remove(userId);
        work.setOwnerUserId(TagUtil.fromSet(userIds));
        boolean update = work.update();
        Db.delete("delete from `work_user` where work_id = ? and user_id = ?", workId, userId);
        return update ? R.ok() : R.error();
    }

    public R updateClassOrder(JSONObject jsonObject){
        String sql = "update `work_task_class` set order_num = ? where work_id = ? and class_id = ?";
        Integer workId = jsonObject.getInteger("workId");
        JSONArray classIds = jsonObject.getJSONArray("classIds");
        for(int i = 0; i < classIds.size(); i++){
            Db.update(sql, i, workId, classIds.get(i));
        }
        return R.ok();
    }

    public R activation(Integer taskId){
        Task task = new Task().findById(taskId);
        Integer count = Db.queryInt("select count(*) from `work_task_class` where class_id = ?", task.getClassId());
        int update;
        if(count>0){
            update = Db.update("update  `task` set is_archive = 0,archive_time = null where task_id = ?", taskId);
        }else {
            update = Db.update("update  `task` set is_archive = 0,archive_time = null,class_id = null where task_id = ?", taskId);
        }
        return update > 0 ? R.ok() : R.error();
    }

    /**
     * 项目启动时初始化项目角色
     */
    public void initialization(){
        BaseConstant.WORK_ADMIN_ROLE_ID = Db.queryInt("select role_id from `admin_role` where label = 1");
        BaseConstant.SMALL_WORK_ADMIN_ROLE_ID = Db.queryInt("select role_id from `admin_role` where label = 2");
        BaseConstant.SMALL_WORK_EDIT_ROLE_ID = Db.queryInt("select role_id from `admin_role` where label = 3");
    }

    /**
     * @author wyq
     * 查询有项目模块查看权限的员工
     */
    public R queryProjectUser(){
        List<Record> recordList = Db.find(Db.getSql("work.queryProjectUser"),152);
        return R.ok().put("data",recordList);
    }
}
