package com.linksame.crm.erp.admin.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author Ivan
 * @date 2020/4/21 10:47
 * @Description 附件日志
 */
public abstract class BaseAdminFileLog<M extends BaseAdminFileLog<M>> extends Model<M> implements IBean {
    public void setLogId(Integer logId) {
        set("log_id", logId);
    }

    public Integer getLogId() {
        return getInt("log_id");
    }

    public void setUserId(Long userId) {
        set("user_id", userId);
    }

    public Long getUserId() {
        return getLong("user_id");
    }

    public void setContent(String content) {
        set("content", content);
    }

    public String getContent() {
        return getStr("content");
    }

    public void setRemake(String remake) {
        set("remake", remake);
    }

    public String getRemake() {
        return getStr("remake");
    }

    public void setCreateTime(java.util.Date createTime) {
        set("create_time", createTime);
    }

    public java.util.Date getCreateTime() {
        return get("create_time");
    }

    public void setStatus(Integer status) {
        set("status", status);
    }

    public Integer getStatus() {
        return getInt("status");
    }

    public void setFileId(Integer fileId) {
        set("file_id", fileId);
    }

    public Integer getFileId() {
        return getInt("file_id");
    }
}
