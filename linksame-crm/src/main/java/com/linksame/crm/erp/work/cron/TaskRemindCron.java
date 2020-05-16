package com.linksame.crm.erp.work.cron;

import cn.hutool.core.collection.CollectionUtil;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfplugin.mail.MailKit;
import com.linksame.crm.erp.admin.common.AdminMessageEnum;
import com.linksame.crm.erp.admin.entity.AdminMessage;
import com.linksame.crm.erp.admin.entity.AdminSms;
import com.linksame.crm.erp.admin.service.AdminSmsService;
import com.linksame.crm.erp.work.entity.TaskRemind;
import com.linksame.crm.utils.R;
import java.util.Date;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/1 14:03
 * @Description 1. 定时查询任务提醒表, 对提醒时间>=当前时间的数据进行处理
 *              2. 根据设置的任务提醒方式, 接入对应提醒方案
 *              3. 提醒完成后修改提醒状态
 */
public class TaskRemindCron implements Runnable {

    private final String CONTENT = "您的任务即将延期, 请抓紧完成!";

    @Override
    public void run() {
        //查询定时提醒任务列表
        List<TaskRemind> reminds = TaskRemind.dao.find("select * from task_remind where remind_status = 0 and remind_isstop = 0 and now() >= remind_time");
        if(CollectionUtil.isNotEmpty(reminds)){
            //遍历任务列表,与当前时间进行比较
            reminds.forEach(ivan -> {
                //提醒方式
                int remindType = ivan.getInt("remind_type");
                //提醒用户
                String userIds = ivan.getStr("remind_user_id");
                String[] ids = userIds.split(",");
                for (String id : ids) {
                    Record user = Db.findFirst("select * from admin_user where user_id = ?", id);
                    if(remindType == 1){
                        //站内信提醒
                        sendMessage(ivan, user);
                    } else if(remindType == 2){
                        //短信提醒
                        sendSms(ivan, user);
                    } else if(remindType == 3){
                        //邮件提醒
                        sendEmail(ivan, user);
                    }
                }
            });
        }
    }

    /**
     * 站内信提醒
     * @param taskRemind    任务提醒
     * @param user          提醒对象
     */
    @Before(Tx.class)
    private void sendMessage(TaskRemind taskRemind, Record user){
        AdminMessage adminMessage = new AdminMessage();
        adminMessage.setTitle("任务提醒");
        if(StringUtils.isEmpty(taskRemind.getRemindContent())){
            taskRemind.setRemindContent(CONTENT);
        }
        adminMessage.setContent(taskRemind.getRemindContent());
        //分配给我的任务
        adminMessage.setLabel(1);                                                   //任务
        adminMessage.setType(AdminMessageEnum.OA_TASK_ALLOCATION.getType());        //分配给我的任务
        adminMessage.setTypeId(taskRemind.getTaskId());
        adminMessage.setRecipientUser(user.getLong("user_id"));
        adminMessage.setCreateTime(new Date());
        boolean bol = adminMessage.save();
        if(bol){
            //修改任务提醒状态
            taskRemind.setRemindStatus(1);
            taskRemind.update();
        }
        LogKit.info("定时任务: 通过站内通信方式提醒用户: " + user.getStr("realname"));
        LogKit.info("信息为: " + taskRemind.getRemindContent());
    }

    /**
     * 短信提醒
     * @param taskRemind    任务提醒
     * @param user          提醒用户
     */
    @Before(Tx.class)
    private void sendSms(TaskRemind taskRemind, Record user){
        String mobile = user.getStr("mobile");
        if (StringUtils.isNotEmpty(mobile)) {
            AdminSms adminSms = new AdminSms();
            adminSms.setMobile(mobile);
            if(StringUtils.isEmpty(taskRemind.getRemindContent())){
                taskRemind.setRemindContent(CONTENT);
            }
            adminSms.setContent(taskRemind.getRemindContent());
            R r = new AdminSmsService().sendSms(adminSms);
            String code = r.get("code").toString();
            boolean bol = Integer.parseInt(code) == 0;
            if(bol){
                //修改任务提醒状态
                taskRemind.setRemindStatus(1);
                taskRemind.update();
            }
            LogKit.info("定时任务: 通过短信方式提醒用户: " + user.getStr("realname"));
            LogKit.info("信息为: " + taskRemind.getRemindContent());
        } else {
            LogKit.error("用户" + user.get("realname") + "没有设置手机号码");
        }
    }

    /**
     * 发送邮件
     * @param taskRemind    任务提醒
     * @param user          提醒用户
     */
    @Before(Tx.class)
    private void sendEmail(TaskRemind taskRemind, Record user){
        String email = user.getStr("email");
        if (StringUtils.isNotEmpty(email)) {
            try{
                if(StringUtils.isEmpty(taskRemind.getRemindContent())){
                    taskRemind.setRemindContent(CONTENT);
                }
                MailKit.use("mail").send(email, null, "任务提醒", taskRemind.getRemindContent());
            }catch (Exception e){
                LogKit.error("邮件发送失败: " + e);
            }
            taskRemind.setRemindStatus(1);
            taskRemind.update();
            LogKit.info("定时任务: 通过邮件方式提醒用户: " + user.getStr("realname"));
            LogKit.info("信息为: " + taskRemind.getRemindContent());
        } else {
            LogKit.error("用户" + user.get("realname") + "没有设置邮箱");
        }
    }
}
