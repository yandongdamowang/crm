package com.linksame.crm.erp.pmp.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContractPaymentRecord;
import com.linksame.crm.utils.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentRecordService
 * @date2020/4/2 12:18
 * @Description TODO
 **/
public class PmpContractPaymentRecordService {
    public R queryList(BasePageRequest<PmpContractPaymentRecord> basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startTime = LocalDate.parse(jsonObject.getString("startTime"),dateTimeFormatter );
        LocalDate endTime = LocalDate.parse(jsonObject.getString("endTime"),dateTimeFormatter );
        Kv kv = Kv.by("paymentNumber", jsonObject.getString("paymentNumber"))
                .set("supplierId", jsonObject.getLong("supplierId"))
                .set("startTime", startTime)
                .set("endTime", endTime);
        if (basePageRequest.getPageType() == 0) {
            List<Record> records = Db.find(Db.getSqlPara("pmp.contractPaymentRecord.queryList", kv));
            return R.ok().put("data", records);
        } else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.contractPaymentRecord.queryList", kv));
            return R.ok().put("data", paginate);
        }
    }
}
