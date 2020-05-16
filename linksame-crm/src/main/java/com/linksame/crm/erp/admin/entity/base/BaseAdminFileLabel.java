package com.linksame.crm.erp.admin.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author Ivan
 * @date 2020/5/4 12:34
 * @Description 附件标签实体类
 */
public abstract class BaseAdminFileLabel<M extends BaseAdminFileLabel<M>> extends Model<M> implements IBean {

    public void setFileLabelId(Integer fileLabelId) {
        set("file_label_id", fileLabelId);
    }

    public Integer getFileLabelId() {
        return getInt("file_label_id");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getName() {
        return getStr("name");
    }

    public void setCreateUserId(Long createUserId) {
        set("create_user_id", createUserId);
    }

    public Long getCreateUserId() {
        return getLong("create_user_id");
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

    public void setColor(String color) {
        set("color", color);
    }

    public String getColor() {
        return getStr("color");
    }

    public void setIsCommon(Integer isCommon) {
        set("is_common", isCommon);
    }

    public Integer getIsCommon() {
        return getInt("is_common");
    }
}
