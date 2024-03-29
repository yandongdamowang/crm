package com.linksame.crm.erp.admin.entity.base;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.SqlPara;
import com.linksame.crm.erp.admin.entity.AdminFile;

import java.util.List;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdminFile<M extends BaseAdminFile<M>> extends Model<M> implements IBean {

	public void setFileId(java.lang.Integer fileId) {
		set("file_id", fileId);
	}
	
	public java.lang.Integer getFileId() {
		return getInt("file_id");
	}

	public void setBucketName(java.lang.String bucketName) {
		set("bucket_name", bucketName);
	}

	public java.lang.String getBucketName() {
		return getStr("bucket_name");
	}

	public void setFileName(java.lang.String fileName) {
		set("file_name", fileName);
	}
	
	public java.lang.String getFileName() {
		return getStr("file_name");
	}

	public void setCompositionName(java.lang.String compositionName) {
		set("composition_name", compositionName);
	}

	public java.lang.String getCompositionName() {
		return getStr("composition_name");
	}

	public void setOldName(java.lang.String oldName) {
		set("old_name", oldName);
	}

	public java.lang.String getOldName() {
		return getStr("old_name");
	}

	public void setSize(java.lang.Long size) {
		set("size", size);
	}
	
	public java.lang.Long getSize() {
		return getLong("size");
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

	public void setPath(java.lang.String path) {
		set("path", path);
	}
	
	public java.lang.String getPath() {
		return getStr("path");
	}

	public void setSuffix(java.lang.String suffix) {
		set("suffix", suffix);
	}

	public java.lang.String getSuffix() {
		return getStr("suffix");
	}

	public void setFileType(java.lang.String fileType) {
		set("file_type", fileType);
	}
	
	public java.lang.String getFileType() {
		return getStr("file_type");
	}

	public void setFolderId(java.lang.Integer folderId) {
		set("folder_id", folderId);
	}

	public java.lang.Integer getFolderId() {
		return getInt("folder_id");
	}

	public void setLabelId(java.lang.String labelId) {
		set("label_id", labelId);
	}

	public java.lang.String getLabelId() {
		return getStr("label_id");
	}

	public void setWorkId(java.lang.Integer workId) {
		set("work_id", workId);
	}

	public java.lang.Integer getWorkId() {
		return getInt("work_id");
	}

	public void setTypeId(java.lang.Integer typeId) {
		set("type_id", typeId);
	}

	public java.lang.Integer getTypeId() {
		return getInt("type_id");
	}

	public void setBatchId(java.lang.String batchId) {
		set("batch_id", batchId);
	}
	
	public java.lang.String getBatchId() {
		return getStr("batch_id");
	}

	public void setIsMainVersion(java.lang.Integer isMainVersion) {
		set("is_main_version", isMainVersion);
	}

	public java.lang.Integer getIsMainVersion() {
		return getInt("is_main_version");
	}

	public void setHistoryFileId(java.lang.Integer historyFileId) {
		set("history_file_id", historyFileId);
	}

	public java.lang.Integer getHistoryFileId() {
		return getInt("history_file_id");
	}

	public void setFileVersion(java.lang.Integer fileVersion) {
		set("file_version", fileVersion);
	}

	public java.lang.Integer getFileVersion() {
		return getInt("file_version");
	}

	public void setFileRemark(java.lang.String fileRemark) {
		set("file_remark", fileRemark);
	}

	public java.lang.String getFileRemark() {
		return getStr("file_remark");
	}

	public void setDelFlag(java.lang.Integer delFlag) {
		set("del_flag", delFlag);
	}

	public java.lang.Integer getDelFlag() {
		return getInt("del_flag");
	}

	public void setDelUserId(java.lang.Long delUserId) {
		set("del_user_id", delUserId);
	}

	public java.lang.Long getDelUserId() {
		return getLong("del_user_id");
	}

	public void setDelTime(java.util.Date delTime) {
		set("del_time", delTime);
	}

	public java.util.Date getDelTime() {
		return get("del_time");
	}
}
