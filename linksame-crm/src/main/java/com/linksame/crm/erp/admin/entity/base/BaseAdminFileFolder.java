package com.linksame.crm.erp.admin.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * @author Ivan
 * @date 2020/4/17 12:34
 * @Description 附件文件夹实体类
 */
public abstract class BaseAdminFileFolder<M extends BaseAdminFileFolder<M>> extends Model<M> implements IBean {

    public void setFolderId(java.lang.Integer folderId) {
        set("folder_id", folderId);
    }

    public java.lang.Integer getFolderId() {
        return getInt("folder_id");
    }

    public void setFolderName(java.lang.String folderName) {
        set("folder_name", folderName);
    }

    public java.lang.String getFolderName() {
        return getStr("folder_name");
    }

    public void setFolderCode(java.lang.String folderCode) {
        set("folder_code", folderCode);
    }

    public java.lang.String getFolderCode() {
        return getStr("folder_code");
    }

    public void setFolderPid(java.lang.Integer folderPid) {
        set("folder_pid", folderPid);
    }

    public java.lang.Integer getFolderPid() {
        return getInt("folder_pid");
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

    public void setBatchId(java.lang.String batchId) {
        set("batch_id", batchId);
    }

    public java.lang.String getBatchId() {
        return getStr("batch_id");
    }

}
