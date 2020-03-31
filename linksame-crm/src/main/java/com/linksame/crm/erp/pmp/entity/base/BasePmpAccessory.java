package com.linksame.crm.erp.pmp.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author ZhangJie
 * @ClassName BasePmpAccessory
 * @date2020/3/28 20:03
 * @Description TODO
 **/
@SuppressWarnings("serial")
public abstract class BasePmpAccessory<M extends BasePmpAccessory<M>> extends Model<M> implements IBean {

    public void setAccessoryId(java.lang.Long accessoryId) {
        set("accessory_id", accessoryId);
    }

    public java.lang.Long getAccessoryId() {
        return getLong("accessory_id");
    }

    public void setAccessoryType(java.lang.String accessoryType) {
        set("accessory_type", accessoryType);
    }

    public java.lang.String getAccessoryType() {
        return getStr("accessory_type");
    }

    public void setCreationTime(java.util.Date creationTime) {
        set("creation_time", creationTime);
    }

    public java.util.Date getCreationTime() {
        return get("creation_time");
    }

    public void setUpdateTime(java.util.Date updateTime) {
        set("update_time", updateTime);
    }

    public java.util.Date getUpdateTime() {
        return get("update_time");
    }

    public void setContractId(java.lang.Long contractId) {
        set("contract_id", contractId);
    }

    public java.lang.Long getContractId() {
        return getLong("contract_id");
    }

    public void setProjectId(java.lang.Long projectId) {
        set("project_id", projectId);
    }

    public java.lang.Long getProjectId() {
        return getLong("project_id");
    }

    public void setMilestoneNodes(java.lang.Long milestoneNodes) {
        set("milestone_nodes", milestoneNodes);
    }

    public java.lang.Long getMilestoneNodes() {
        return getLong("milestone_nodes");
    }

    public void setStatus(java.lang.String status) {
        set("status", status);
    }

    public java.lang.String getStatus() {
        return getStr("status");
    }

    public void setIsDeleted(java.lang.String isDeleted) {
        set("is_deleted", isDeleted);
    }

    public java.lang.String getIsDeleted() {
        return getStr("is_deleted");
    }

    public void setAccessoryPath(java.lang.String accessoryPath) {
        set("accessory_path", accessoryPath);
    }

    public java.lang.String getAccessoryPath() {
        return getStr("accessory_path");
    }

    public void setAccessoryName(java.lang.String accessoryName) {
        set("accessory_name", accessoryName);
    }

    public java.lang.String getAccessoryName() {
        return getStr("accessory_name");
    }

    public void setCreationName(java.lang.String creationName) {
        set("creation_name", creationName);
    }

    public java.lang.String getCreationName() {
        return getStr("creation_name");
    }

    public void setAccessorySize(java.lang.Double accessorySize) {
        set("accessory_size", accessorySize);
    }

    public java.lang.Double getAccessorySize() {
        return getDouble("accessory_size");
    }

}
