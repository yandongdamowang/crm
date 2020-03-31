package com.linksame.crm.erp.pmp.service;

import com.jfinal.plugin.activerecord.Db;
import com.linksame.crm.erp.pmp.entity.PmpAccessory;

import java.util.List;

/**
 * @author ZhangJie
 * @ClassName PmpAccessoryService
 * @date2020/3/28 20:05
 * @Description TODO
 **/
public class PmpAccessoryService {

    public List<PmpAccessory> findByContractId(Long contractId) {
        List<PmpAccessory> pmpAccessories = PmpAccessory.dao.find(Db.getSql("pmp.accessory.queryByContractId"), contractId);
        return pmpAccessories;
    }
}
