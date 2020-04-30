package com.linksame.crm.erp.admin.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author Ivan
 * @date 2020/4/20 10:47
 * @Description 附件类型
 */
public abstract class BaseAdminFileType<M extends BaseAdminFileType<M>> extends Model<M> implements IBean {
    public void setTypeId(java.lang.Integer typeId) {
        set("type_id", typeId);
    }

    public java.lang.Integer getTypeId() {
        return getInt("type_id");
    }

    public void setTypeName(java.lang.String typeName) {
        set("type_name", typeName);
    }

    public java.lang.String getTypeName() {
        return getStr("type_name");
    }

    public void setTypeCode(java.lang.String typeCode) {
        set("type_code", typeCode);
    }

    public java.lang.String getTypeCode() {
        return getStr("type_code");
    }

    public void setCreateUserId(java.lang.Long createUserId) {
        set("create_user_id", createUserId);
    }

    public java.lang.Long getCreateUserId() {
        return getLong("create_user_id");
    }

    public void setCreateTime(java.util.Date createTime) {
        set("create_time", createTime);
    }

    public java.util.Date getCreateTime() {
        return get("create_time");
    }

    public void setUpdateTime(java.util.Date updateTime) {
        set("update_time", updateTime);
    }

    public java.util.Date getUpdateTime() {
        return get("update_time");
    }

    public void setIsDisable(java.lang.Integer isDisable) {
        set("is_disable", isDisable);
    }

    public java.lang.Integer getIsDisable() {
        return getInt("is_disable");
    }
}
