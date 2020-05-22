package com.linksame.crm.erp.work.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import java.util.Date;

/**
 * @author Ivan
 * @date 2020/5/20 11:26
 * @Description
 */
@SuppressWarnings("serial")
public abstract class BaseTaskRepeat<M extends BaseTaskRepeat<M>> extends Model<M> implements IBean {
    public void setRepeatId(Integer repeatId) {
        set("repeat_id", repeatId);
    }

    public Integer getRepeatId() {
        return getInt("repeat_id");
    }

    public void setRepeatType(Integer repeatType) {
        set("repeat_type", repeatType);
    }

    public Integer getRepeatType() {
        return getInt("repeat_type");
    }

    public void setRepeatInterval(Integer repeatInterval) {
        set("repeat_interval", repeatInterval);
    }

    public Integer getRepeatInterval() {
        return getInt("repeat_interval");
    }

    public void setRepeatAttr(String repeatAttr) {
        set("repeat_attr", repeatAttr);
    }

    public String getRepeatAttr() {
        return getStr("repeat_attr");
    }

    public void setCreateTime(Date createTime) {
        set("create_time", createTime);
    }

    public Date getCreateTime() {
        return get("create_time");
    }

    public void setUpdateTime(Date updateTime) {
        set("update_time", updateTime);
    }

    public Date getUpdateTime() {
        return get("update_time");
    }

    public void setRemark(String remark) {
        set("remark", remark);
    }

    public String getRemark() {
        return getStr("remark");
    }

    public void setTaskId(Integer taskId) {
        set("task_id", taskId);
    }

    public Integer getTaskId() {
        return getInt("task_id");
    }

}
