package com.linksame.crm.erp.pmp.entity;

import com.jfinal.plugin.activerecord.Model;
import com.linksame.crm.erp.pmp.entity.base.BasePmpReceivableRecords;


@SuppressWarnings("serial")
public class PmpReceivableRecords extends BasePmpReceivableRecords<PmpReceivableRecords> {
    public static final PmpReceivableRecords dao = new PmpReceivableRecords().dao();
}
