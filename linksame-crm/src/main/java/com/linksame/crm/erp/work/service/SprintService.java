package com.linksame.crm.erp.work.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.erp.work.entity.Task;
import com.linksame.crm.erp.work.entity.TaskSprint;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import org.elasticsearch.common.inject.Inject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            });
            return R.ok().put("data", recordList);
        } else {
            Page<Record> pageList = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("task.sprint.queryList", kv));
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
}
