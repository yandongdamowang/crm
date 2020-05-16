package com.linksame.crm.erp.pmp.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public abstract class BasePmpContractTemplate<M extends BasePmpContractTemplate<M>> extends Model<M> implements IBean {

    public void setContractType(java.lang.Long contractType) {
        set("contract_type", contractType);
    }

    public java.lang.Long getContractType() {
        return getLong("contract_type");
    }

    public void setTemplateName(java.lang.String templateName) {
        set("template_name", templateName);
    }

    public java.lang.String getTemplateName() {
        return getStr("template_name");
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

}
