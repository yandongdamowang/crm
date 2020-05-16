package com.linksame.crm.erp.pmp.entity;

import com.jfinal.plugin.activerecord.Model;
import com.linksame.crm.erp.pmp.entity.base.BasePmpContractTemplate;

@SuppressWarnings("serial")
public class PmpContractTemplate extends BasePmpContractTemplate<PmpContractTemplate> {
    public static final PmpContractTemplate dao = new PmpContractTemplate().dao();
}