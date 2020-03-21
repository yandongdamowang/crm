package com.linksame.crm.erp.work.cron;


import com.jfinal.plugin.activerecord.Db;

/**
 * 定时扫描延期任务
 */
public class TaskCron implements Runnable {

    @Override
    public void run() {
        Db.update("update task set status = 2 where stop_time < now() and status = 1");
    }
}
