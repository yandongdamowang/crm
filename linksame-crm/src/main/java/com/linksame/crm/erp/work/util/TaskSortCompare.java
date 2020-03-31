package com.linksame.crm.erp.work.util;

import com.linksame.crm.erp.work.entity.Task;
import java.util.Comparator;

/**
 * @author ivan
 * @date 2020/3/31 14:03
 * @Description 比较时间进行排序(任务)
 */
public class TaskSortCompare implements Comparator {
    public int compare(Object arg0,Object arg1){
        Task task0 = (Task)arg0;
        Task task1 = (Task)arg1;
        int flag = task1.getStopTime().compareTo(task0.getStopTime());
        return flag;
    }
}
