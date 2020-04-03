package com.linksame.crm.erp.work.cron;

import cn.hutool.core.bean.BeanUtil;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.kit.LogKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.render.ErrorRender;
import com.jfinal.render.RenderException;
import com.linksame.crm.erp.admin.common.AdminMessageEnum;
import com.linksame.crm.erp.admin.entity.AdminMessage;
import com.linksame.crm.erp.work.entity.TaskRemind;
import com.linksame.crm.utils.R;
import org.apache.poi.ss.formula.functions.T;

import java.util.Date;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/1 14:03
 * @Description 1. 定时查询任务提醒表,
 *              2. 比较提醒时间
 *              3. 执行提醒任务
 */
public class TaskRemindCron implements Runnable {

    private boolean resultFlag;

    @Override
    @Before(Tx.class)
    public void run() {
        //查询定时提醒任务列表
        List<TaskRemind> reminds = TaskRemind.dao.find("select * from task_remind where remind_status = 0 and remind_isstop = 0");
        //遍历任务列表,与当前时间进行比较
        Db.tx(() -> {
            reminds.forEach(ivan -> {
                //比较时间
                Date remindTime = ivan.get("remind_time");
                if (remindTime.getTime() <= new Date().getTime()) {
                    //提醒方式
                    int remindType = ivan.get("remind_type");
                    //提醒用户
                    String userIds = ivan.get("remind_user_id");
                    String[] ids = userIds.split(",");
                    for (String i : ids) {
                        Record user = Db.findFirst("select * from admin_user where user_id = ?", i);

                        switch (remindType) {
                            case 1:
                                LogKit.info("定时任务: 通过站内通信方式提醒用户" + user.get("realname") + "任务即将延期");
                                //插入信息到系统消息表
                                AdminMessage adminMessage = new AdminMessage();
                                adminMessage.setTitle("任务提醒");
                                adminMessage.setContent("你的任务即将延期,请抓紧时间完成!");
                                adminMessage.setLabel(1);                                                   //任务
                                adminMessage.setType(AdminMessageEnum.OA_TASK_ALLOCATION.getType());        //分配给我的任务
                                adminMessage.setTypeId(ivan.getInt("task_id"));
                                adminMessage.setRecipientUser(user.get("user_id"));
                                adminMessage.setCreateTime(new Date());
                                resultFlag = adminMessage.save();
                                break;
                            case 2:
                                String mobile = user.get("mobile");
                                if (StringUtils.isNotEmpty(mobile)) {
                                    //TODO 对接短信SDK
                                    LogKit.info("手机提醒用户: " + user.get("mobile"));
                                } else {
                                    LogKit.info("用户" + user.get("realname") + "没有设置手机号码");
                                }
                                break;
                            case 3:
                                String email = user.get("email");
                                if (StringUtils.isNotEmpty(email)) {
                                    //TODO 接入邮件发送功能
                                    LogKit.info("邮箱提醒用户: " + user.get("email"));
                                } else {
                                    LogKit.info("用户" + user.get("realname") + "没有设置邮箱");
                                }
                                break;
                        }
                    }
                }
                if (resultFlag) {
                    //执行后修改任务提醒状态
                    ivan.setRemindStatus(1);
                    ivan.update();
                }
            });
            return resultFlag;
        });
    }
}
