package com.linksame.crm.erp.pmp.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContractPaymentRecord;
import com.linksame.crm.utils.R;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
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
        Kv kv = Kv.by("contractNumber", jsonObject.getString("contractNumber"))
                .set("supplierId", jsonObject.getLong("supplierId"))
                .set("orderBy", jsonObject.get("orderBy"))
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

    public R queryById(Long paymentRecordId) {
        Kv kv = Kv.by("paymentRecordId", paymentRecordId);
        Record first = Db.findFirst(Db.getSqlPara("pmp.contractPaymentRecord.queryById", kv));

        return R.ok().put("paymentRecord",first);
    }

    /**
     *  付款记录报表
     * @param parseObject 条件
     * @return 报表
     */
    public R paymentReport(JSONObject parseObject) {
        LocalDate startTime = LocalDate.parse(parseObject.getString("startTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endTime = LocalDate.parse(parseObject.getString("endTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Kv kv = Kv.by("startTime", startTime)
                .set("endTime", endTime)
                .set("supplierId", parseObject.get("supplierId"))
                .set("projectId", parseObject.get("projectId"));
        List<Record> records = null;
        if (endTime.toEpochDay() - startTime.toEpochDay() > 300){
            records = Db.find(Db.getSqlPara("pmp.contractPaymentRecord.paymentDashboard2", kv));
        }else {
            records = Db.find(Db.getSqlPara("pmp.contractPaymentRecord.paymentDashboard1", kv));
        }
        //报表曲线图
        int contractCount = 0;
        for (Record record : records) {
            contractCount = contractCount + record.getInt("ids");
        }
        //预付款金额
        List<Record> contractRecords = Db.find(Db.getSqlPara("pmp.contractPayment.paymentDashboard", kv));
        BigDecimal paymentCountMoney = new BigDecimal(0);
        for (Record contractRecord : contractRecords) {
            BigDecimal paymentCountMoney1 = contractRecord.getBigDecimal("countMoney");
            if (paymentCountMoney1 != null) {
                paymentCountMoney = paymentCountMoney.add(paymentCountMoney1);
            }
        }
        //回款
        List<Record>  agencyFund = Db.find(Db.getSqlPara("pmp.receivableRecords.paymentDashboard", kv));
        BigDecimal receivableAmountMoney = new BigDecimal(0);
        for (Record record : agencyFund) {
            BigDecimal receivableAmountMoney1 = record.getBigDecimal("receivableAmountMoney");
            if (receivableAmountMoney1 != null) {
                receivableAmountMoney = receivableAmountMoney.add(receivableAmountMoney1);
            }
        }
//        BigDecimal bigDecimal = new BigDecimal(0);
        //dashboard 曲线数据
            //practicaAdvanced 实际付款金额
            //paymentTime 付款日期
            //ids 支付笔数
        //contractCount 合同总数
        //paymentCountMoney 预付款金额
        //receivableAmountMoney 预回款金额
        return R.ok().put("dashboard",records).put("contractCount",contractCount).put("paymentCountMoney",paymentCountMoney).put("receivableAmountMoney",receivableAmountMoney);
    }
}
