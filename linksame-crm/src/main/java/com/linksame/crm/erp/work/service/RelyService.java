package com.linksame.crm.erp.work.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.erp.work.entity.Task;
import com.linksame.crm.erp.work.entity.TaskRely;
import com.linksame.crm.utils.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ivan
 * @date 2020/4/17 10:40
 * @Description 任务依赖业务层
 */
public class RelyService {

    /**
     * 设置任务依赖
     * @param taskId        任务ID
     * @param preTaskIds    前置任务ID数组字符串
     * @param relyFlag      0前置任务 非0后置任务
     */
    @Before(Tx.class)
    public R setRely(Integer taskId, String preTaskIds, Integer relyFlag){
        if(relyFlag == 0){
            //设置前置任务前,删除该taskId关联的前置任务数据
            Db.delete("delete from task_rely where task_id = ?", taskId);
            for(String preTaskId : preTaskIds.split(",")) {
                TaskRely taskRely = new TaskRely();
                taskRely.setPreTaskId(Integer.parseInt(preTaskId));
                taskRely.setTaskId(taskId);
                taskRely.save();
            }
        } else {
            //设置后置任务前,删除该taskId作为前置任务的数据
            Db.delete("delete from task_rely where pre_task_id = ?", taskId);
            for(String preTaskId : preTaskIds.split(",")) {
                TaskRely taskRely = new TaskRely();
                taskRely.setPreTaskId(taskId);
                taskRely.setTaskId(Integer.parseInt(preTaskId));
                taskRely.save();
            }
        }
        return R.ok();
    }

    /**
     * 校验该任务前置任务是否已完成
     * @param taskId    当前任务ID
     * @return          true/false
     */
    public R checkRely(Integer taskId){
        boolean bol = true;
        //获取该taskId的前置任务
        List<Record> preTaskList = Db.find("select b.* from task_rely a inner join task b on a.pre_task_id = b.task_id where a.task_id = ?", taskId);
        //校验前置任务是否全部已完成
        for(Record r : preTaskList){
            if(r.getInt("status") != 5){
                bol = false;
            }
        }
        return R.ok().put("data", bol);
    }
}
