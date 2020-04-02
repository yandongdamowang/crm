package com.linksame.crm.erp.work.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.erp.work.entity.TaskRemind;
import com.linksame.crm.utils.R;

/**
 * @author Ivan
 * @date 2020/4/1 12:48
 * @Description 任务提醒业务层
 */
public class RemindService {

    /**
     * 新增/修改任务提醒数据
     * @param taskRemind    任务提醒对象
     * @return  true/false
     */
    public R setRemind(TaskRemind taskRemind) {
        boolean bol;
        if (taskRemind.getRemindId() == null) {
            bol = taskRemind.save();
        } else {
            bol = taskRemind.update();
        }
        return bol ? R.ok() : R.error();
    }

    /**
     * 根据任务id查询任务提醒数据
     * @param taskId   任务提醒编号
     * @return  任务提醒对象
     */
    public R queryByTaskId(Integer taskId){
        Record taskRemind = Db.findFirst("select * from task_remind where task_id = ?", taskId);
        String remindIds = taskRemind.get("remind_user_id");
        String[] ids = remindIds.split(",");
        StringBuffer sb = new StringBuffer();
        for(String item : ids){
            AdminUser user = AdminUser.dao.findById(item);
            if(user != null){
                sb.append("," + user.getRealname());
            }
        }
        taskRemind.set("remindRealnames",sb.substring(1));

        return R.ok().put("data", taskRemind);
    }
}
