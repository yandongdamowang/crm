package com.linksame.crm.erp.pmp.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpAccessory;
import com.linksame.crm.utils.R;

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

    public R queryList(BasePageRequest<PmpAccessory> basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv= Kv.by("accessoryName", jsonObject.getString("accessoryName"))
                .set("accessoryType", jsonObject.getLong("accessoryType"));
        if (basePageRequest.getPageType() == 0){
            List<Record> records = Db.find(Db.getSqlPara("pmp.accessory.queryList", kv));
            return R.ok().put("data",records);
        }else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.contract.queryList", kv));
            return R.ok().put("data", paginate);
        }
    }
}
