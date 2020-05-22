package com.linksame.crm.erp.work.service;

import com.aliyuncs.utils.StringUtils;
import com.linksame.crm.erp.work.entity.Task;
import com.linksame.crm.erp.work.entity.TaskRepeat;
import com.linksame.crm.utils.DateUtil;
import com.linksame.crm.utils.R;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;

/**
 * @author Ivan
 * @date 2020/5/20 11:37
 * @Description 重复任务
 */
public class TaskRepeatService {

    /**
     * 设置重复任务
     * @return
     */
    public R setRepeat(TaskRepeat taskRepeat){
        //获取任务数据
        Task task = Task.dao.findById(taskRepeat.getTaskId());
        if(task.getStartTime() == null){
            return R.error("任务的开始时间未设置");
        }

        //当类型为1,2,3时, 重复间隔不允许为空
        if(taskRepeat.getRepeatType() == 1 || taskRepeat.getRepeatType() == 2 || taskRepeat.getRepeatType() == 3){
            if(taskRepeat.getRepeatInterval() == null){
                return R.error("repeatInterval参数不允许为空");
            }
        }

        if(taskRepeat.getRepeatType() == 1){    //按天
            //间隔
            taskRepeat.setRemark("间隔 " + taskRepeat.getRepeatInterval() + " 天, 进行重复");
        } else if(taskRepeat.getRepeatType() == 2){     //按周
            //解析重复值, 数组字符串
            if(StringUtils.isEmpty(taskRepeat.getRepeatAttr())){
                return R.error("参数repeatAttr不允许为空");
            }
            String[] repeatAttr = taskRepeat.getRepeatAttr().split(",");
            //执行日期列表
            List<String> repeatDateList = new ArrayList<>();
            //周转义列表
            List<String> cnList = new ArrayList<>();
            //解析存储下一次执行时间
            for(String attr : repeatAttr){
                //获取后面一星期内日期
                String startDate = DateUtil.dateToStr(new Date());
                String endDate = DateUtil.dateToStr(DateUtil.getNextDay(new Date(), 7));
                //通过周几转换成具体日期
                String carriedDate = DateUtil.getDayOfWeekWithinDateInterval(startDate, endDate, Integer.parseInt(attr)).get(0);
                repeatDateList.add(carriedDate);
                //转义
                if(Integer.parseInt(attr) == 1){
                    cnList.add("周一");
                } else if(Integer.parseInt(attr) == 2){
                    cnList.add("周二");
                } else if(Integer.parseInt(attr) == 3){
                    cnList.add("周三");
                } else if(Integer.parseInt(attr) == 4){
                    cnList.add("周四");
                } else if(Integer.parseInt(attr) == 5){
                    cnList.add("周五");
                } else if(Integer.parseInt(attr) == 6){
                    cnList.add("周六");
                } else if(Integer.parseInt(attr) == 7){
                    cnList.add("周日");
                }
            }
            //间隔
            taskRepeat.setRemark("间隔" + taskRepeat.getRepeatInterval() + "周, " + "每" + cnList.toString() + "重复");
        } else if(taskRepeat.getRepeatType() == 3){     //按月
            //解析重复值, 数组字符串
            if(StringUtils.isEmpty(taskRepeat.getRepeatAttr())){
                return R.error("参数repeatAttr不允许为空");
            }
            String[] repeatAttr = taskRepeat.getRepeatAttr().split(",");
            List<String> repeatDateList = new ArrayList<>();
            for(String attr : repeatAttr){
                repeatDateList.add(attr);
            }
            //间隔
            taskRepeat.setRemark("间隔" + taskRepeat.getRepeatInterval() + "月, " + "每月" + repeatDateList.toString() + "日重复");
        }

        //存储
        if(taskRepeat.getRepeatId() == null){
            taskRepeat.setCreateTime(new Date());
            taskRepeat.save();
        } else {
            taskRepeat.setUpdateTime(new Date());
            taskRepeat.update();
        }

        return R.ok().put("repeatId", taskRepeat.getRepeatId());
    }

}
