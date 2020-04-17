package com.linksame.crm.erp.pmp.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.common.PmpInterface;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.utils.R;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentService
 * @date2020/3/28 18:47
 * @Description TODO
 **/
public class PmpContractPaymentService {

    public List<PmpContractPayment> findByContractId(Long contractId,String tradeStatus) {
        Kv kv = Kv.by("contractId", contractId)
                .set("tradeStatus",tradeStatus);
        List<PmpContractPayment> pmpContractPayments;
        if (tradeStatus != null){
            pmpContractPayments = PmpContractPayment.dao.find("select pcp.* from pmp_contract_payment as pcp where pcp.is_deleted = '0' and pcp.trade_status = ? and pcp.contract_id = ?",tradeStatus,contractId);;
        }else {
            pmpContractPayments = PmpContractPayment.dao.find("select pcp.* from pmp_contract_payment as pcp where pcp.is_deleted = '0' and pcp.contract_id = ?",contractId);
        }
        return pmpContractPayments;
    }

    public R update(JSONObject object) {
        return null;
    }

    public R queryPaymentByMonthly(String month) {
        LocalDate localDate = LocalDate.parse(month, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Kv kv = Kv.by("paymentNodeDateYears", localDate.getYear())
                .set("paymentNodeDateMonth", localDate.getMonthValue())
                .set("tradeForm", PmpInterface.contractPayment.trade.form.EXPENF)
                .set("tradeStatus",PmpInterface.contractPayment.trade.stats.TRADING);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        List<Record> records = Db.find(Db.getSqlPara("pmp.contractPayment.queryList", kv));
        Map<Long, BigDecimal> contractMoney = new HashMap<>(records.size());
        for (Record record : records) {
            Long contractId = record.getLong("contract_id");
            if (contractMoney.get(contractId) != null) {
                continue;
            } else {
                contractMoney.put(contractId, record.getBigDecimal("money"));
            }
        }
        BigDecimal bigDecimal = new BigDecimal(0);
        for (Map.Entry<Long, BigDecimal> o : contractMoney.entrySet()) {
            BigDecimal value = o.getValue();
            bigDecimal = bigDecimal.add(value);
        }
        return R.ok().put("list",records).put("money",bigDecimal).put("time",month.split("-01")[0]);
    }
    public R updatePaymentBill(List<PmpContractPayment> pmpContractPayments) {
        return Db.tx(()->{
            pmpContractPayments.forEach(contractPayment -> {
                PmpContractPayment.dao.find(
                        "select pcp.* from pmp_contract_payment AS pcp  where pcp.contract_id = ? " +
                                "and pcp.is_deleted = '0' " +
                                "and pcp.status = '1' " +
                                "and pcp.trade_form = "+PmpInterface.contractPayment.trade.form.EXPENF+" " +
                                "and pcp.trade_status = "+PmpInterface.contractPayment.trade.stats.TRADING, contractPayment.getContractId()).forEach(contractPayment1 -> {
                    if (contractPayment.getPaymentStage().equals(contractPayment1.getPaymentStage()) || (contractPayment.getPaymentStage()+1) == contractPayment1.getPaymentStage()){
                        //修改后的
                        contractPayment1.setCostPercentage(contractPayment.getCostPercentage());
                        contractPayment1.setMoney(contractPayment.getMoney());
                        contractPayment1.setUpdateTime(LocalDateTime.now());
                        contractPayment1.update();
                    }
                });
            });
            return true;
        }) ? R.ok(): R.error();
    }

    public R queryAdvanceList(BasePageRequest basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv = Kv.by("paymentNumber", jsonObject.getString("contractNumber"))
                .set("supplierId", jsonObject.getLong("supplierId"))
                .set("orderBy", jsonObject.get("orderBy"));
        if (basePageRequest.getPageType() == 0){
            List<Record> records = Db.find(Db.getSqlPara("pmp.contractPayment.queryAdvanceList", kv));
            return R.ok().put("data",records);
        }else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.contractPayment.queryAdvanceList", kv));
            return R.ok().put("data", paginate);
        }
    }

    public R queryAdvanceBybillId(String billId) {
        Kv kv = Kv.by("billId", billId);
        Record first = Db.findFirst(Db.getSqlPara("pmp.contractPayment.queryAdvanceList", kv));
        return R.ok().put("data", first);
    }
}
