package com.linksame.crm.erp.work.service;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.erp.work.entity.TaskRemind;
import com.linksame.crm.utils.R;

import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/1 12:48
 * @Description 任务提醒业务层
 */
public class RemindService {

    /**
     * 新增/修改任务提醒数据
     * @param jsonObject    json对象
     */
    @Before(Tx.class)
    public R setRemind(JSONObject jsonObject) {
        JSONArray remindJsonArr = jsonObject.getJSONArray("remindList");
        if(remindJsonArr.size() == 0 || CollectionUtil.isEmpty(remindJsonArr)){
            return R.error("remindList参数不允许为空");
        }
        for(int i=0;i<remindJsonArr.size();i++){
            ObjectMapper objectMapper = new ObjectMapper();
            TaskRemind taskRemind = objectMapper.convertValue(remindJsonArr.get(i), TaskRemind.class);
            if (taskRemind.getRemindId() == null) {
                taskRemind.save();
            } else {
                taskRemind.update();
            }
        }
        return R.ok();
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
