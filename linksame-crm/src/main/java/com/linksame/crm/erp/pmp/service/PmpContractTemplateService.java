package com.linksame.crm.erp.pmp.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContractTemplate;
import com.linksame.crm.utils.R;

import java.util.ArrayList;
import java.util.List;

public class PmpContractTemplateService {
    public R addOrUpdate(PmpContractTemplate pmpContractTemplate) {
        boolean result = false;
        if (pmpContractTemplate.getContractType() != null){
            result = pmpContractTemplate.update();
        }else {
            result = pmpContractTemplate.save();
        }
        return result ? R.ok() :R.error();
    }

    public R delete(String templateIds) {
        String[] ids = templateIds.split(",");
        List<Record> idsList = new ArrayList<>();
        for (String id : ids) {
            Record record = new Record();
            idsList.add(record.set("contract_type", Integer.valueOf(id)));
        }
        return Db.tx(()->{
            Db.batch(Db.getSql("pmp.contractTemplate.deleteByIds"), "contract_type", idsList, 100);
            return true;
        }) ? R.ok() : R.error();
    }

    public R queryList(BasePageRequest<PmpContractTemplate> basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
//        Kv kv= Kv.by("contractNumber", jsonObject.getString("contractNumber"))
//                .set("customerId", jsonObject.getLong("customerId"))
//                .set("orderBy", jsonObject.get("orderBy"));
//        List<Record> groupBy = Db.find("SELECT pc.customer_id,cc.contractor_name FROM pmp_contract AS pc LEFT JOIN crm_customer AS cc ON pc.customer_id = cc.customer_id WHERE 1=1 GROUP BY pc.customer_id");
        if (basePageRequest.getPageType() == 0){
//            List<Record> records = Db.find(Db.getSqlPara("pmp.contract.queryList", kv));
//            return R.ok().put("data",records).put("groupBy",groupBy);
            List<Record> records = Db.find(Db.getSqlPara("pmp.contractTemplate.queryList"));
            return R.ok().put("data",records);
        }else {
//            Page<Record> records = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.contract.queryList", kv));
//            return R.ok().put("data",records).put("groupBy",groupBy);
            Page<Record> records = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.contractTemplate.queryList"));
            return R.ok().put("data",records);
        }
    }
}
