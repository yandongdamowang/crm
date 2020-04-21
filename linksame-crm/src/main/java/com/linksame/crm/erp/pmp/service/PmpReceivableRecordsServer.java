package com.linksame.crm.erp.pmp.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminFile;
import com.linksame.crm.erp.crm.entity.CrmBusiness;
import com.linksame.crm.erp.crm.entity.CrmBusinessProduct;
import com.linksame.crm.erp.pmp.entity.PmpReceivableRecords;
import com.linksame.crm.utils.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PmpReceivableRecordsServer {
    public R queryList(BasePageRequest<PmpReceivableRecords> basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
        //客户   支付方式   回款时间 开始结束
        Kv kv = Kv.by("customerId", jsonObject.getString("customerId"))
                .set("paymentMethod", jsonObject.getLong("paymentMethod"))
                .set("collectingStarttime", jsonObject.getLong("collectingStarttime"))
                .set("collectingEndtime", jsonObject.getLong("collectingEndtime"))
                .set("orderBy", jsonObject.get("orderBy"));
        if (basePageRequest.getPageType() == 0){
            List<Record> records = Db.find(Db.getSqlPara("pmp.receivableRecords.queryList", kv));
            return R.ok().put("data",records);
        }else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.receivableRecords.queryList", kv));
            return R.ok().put("data", paginate);
        }
    }

    public R queryById(Long receivableRecordsId) {
        if (receivableRecordsId == null) {
            return R.error("id参数为空");
        }
        return R.ok().put("data", PmpReceivableRecords.dao.findById(receivableRecordsId));
    }

    @Before(Tx.class)
    public R addOrUpdate(JSONObject jsonObject) {
        PmpReceivableRecords pmpReceivableRecords = jsonObject.getObject("pmpReceivableRecords", PmpReceivableRecords.class);
        boolean saveOrUpdate;
        if (pmpReceivableRecords.getReceivableRecordsId() != null){
            pmpReceivableRecords.setUpdateTime(LocalDateTime.now());
            saveOrUpdate = pmpReceivableRecords.update();
        }else {
            pmpReceivableRecords.setCreatedTime(LocalDateTime.now());
            saveOrUpdate = pmpReceivableRecords.save();
        }
        return saveOrUpdate ? R.ok() : R.error();
    }

    public R deleteByIds(String receivableRecordsIds) {
        String[] idsArr = receivableRecordsIds.split(",");
        List<Record> records1 = Db.find(Db.getSql("pmp.receivableRecords.queryContractNumber"), receivableRecordsIds);
        if (records1.size() > 0){
            return R.error("有数据与其他数据有必要关联，请勿删除"+records1.toString());
        }else {
            List<Record> idsList = new ArrayList<>();
            for (String id : idsArr) {
                Record record = new Record();
                idsList.add(record.set("receivable_records_id", Integer.valueOf(id)));
            }
            return Db.tx(() ->{
                Db.batch(Db.getSql("crm.receivableRecords.deleteByIds"), "receivable_records_id", idsList, 100);
                return true;
            })? R.ok() : R.error();
        }
    }
}
