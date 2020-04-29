package com.linksame.crm.erp.pmp.service;

import cn.hutool.core.util.IdUtil;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PmpReceivableRecordsServer {
    public R queryList(BasePageRequest<PmpReceivableRecords> basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
        LocalDate startTime = LocalDate.parse(jsonObject.getString("collectingStarttime"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endTime = LocalDate.parse(jsonObject.getString("collectingEndtime"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //客户   支付方式   回款时间 开始结束
        Kv kv = Kv.by("customerId", jsonObject.getString("customerId"))
                .set("paymentMethod", jsonObject.getLong("paymentMethod"))
                .set("collectingStarttime", startTime)
                .set("collectingEndtime", endTime)
                .set("orderBy", jsonObject.get("orderBy"));
        List<Record> payment_method = Db.find(Db.getSqlPara("SELECT prr.payment_method FROM pmp_receivable_records AS prr WHERE 1=1 GROUP BY prr.payment_method"));
        List<Record> customer_id = Db.find(Db.getSqlPara("SELECT prr.customer_id FROM pmp_receivable_records AS prr WHERE 1=1 GROUP BY prr.customer_id"));
        customer_id.forEach(record -> record.set("customerName","客户名称"));
        if (basePageRequest.getPageType() == 0){
            List<Record> records = Db.find(Db.getSqlPara("pmp.receivableRecords.queryList", kv));
            records.forEach(record -> record.set("customerName","客户名称"));
            return R.ok().put("data",records).put("paymentMethod",payment_method).put("customerId",customer_id);
        }else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.receivableRecords.queryList", kv));
            paginate.getList().forEach(record -> record.set("customerName","客户名称"));
            return R.ok().put("data", paginate).put("paymentMethod",payment_method).put("customerId",customer_id);
        }
    }

    public R queryById(Long receivableRecordsId) {
        if (receivableRecordsId == null) {
            return R.error("id参数为空");
        }
        PmpReceivableRecords byId = PmpReceivableRecords.dao.findById(receivableRecordsId);
        byId.setCollectingTime(byId.getCollectingTime());
        return R.ok().put("data", byId);
    }

    @Before(Tx.class)
    public R addOrUpdate(PmpReceivableRecords pmpReceivableRecords) {
        boolean saveOrUpdate;
        if (pmpReceivableRecords.getReceivableRecordsId() != null){
            pmpReceivableRecords.setUpdateTime(new Date(System.currentTimeMillis()));
            saveOrUpdate = pmpReceivableRecords.update();
        }else {
            pmpReceivableRecords.setCreatedTime(new Date(System.currentTimeMillis()));
            pmpReceivableRecords.setBatchId(IdUtil.simpleUUID());
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

    public R queryByContractId(Long contractId) {
        Kv kv = Kv.by("contractId", contractId).set("orderBy", "1");
        List<Record> records = Db.find(Db.getSqlPara("pmp.receivableRecords.queryList", kv));
        return R.ok().put("data",records);
    }
}
