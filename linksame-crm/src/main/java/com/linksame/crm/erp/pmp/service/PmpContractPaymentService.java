package com.linksame.crm.erp.pmp.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.service.AdminExamineRecordService;
import com.linksame.crm.erp.crm.common.CrmEnum;
import com.linksame.crm.erp.crm.service.CrmRecordService;
import com.linksame.crm.erp.pmp.common.PmpInterface;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.pmp.entity.PmpContractPaymentRecord;
import com.linksame.crm.erp.work.entity.Task;
import com.linksame.crm.utils.R;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentService
 * @date2020/3/28 18:47
 * @Description TODO
 **/
public class PmpContractPaymentService {

    @Inject
    private AdminExamineRecordService examineRecordService;

    @Inject
    private CrmRecordService crmRecordService;

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
                contractMoney.put(contractId, record.getBigDecimal("money").add(contractMoney.get(contractId)));
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
                    //新
                    Integer paymentStage1 = contractPayment.getPaymentStage();
                    //旧
                    Integer paymentStage = contractPayment1.getPaymentStage();
                    boolean equals = paymentStage1.equals(paymentStage);
                    if (equals){
                        //修改后的
                        contractPayment1.setCostPercentage(contractPayment.getCostPercentage());
                        contractPayment1.setMoney(contractPayment.getMoney());
                        contractPayment1.setUpdateTime(new Date(System.currentTimeMillis()));
                        crmRecordService.updateRecord(contractPayment1,contractPayment,CrmEnum.PMP_PAYMENT);
                        contractPayment1.update();
                    }
                });
//                crmRecordService.updateRecord(byId,pmpContract, CrmEnum.PMP_PAYMENT);
            });
            return true;
        }) ? R.ok(): R.error();
    }

    public R queryAdvanceList(BasePageRequest basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv = Kv.by("contractNumber", jsonObject.getString("contractNumber"))
                .set("customerId", jsonObject.getLong("customerId"))
                .set("orderBy", jsonObject.get("orderBy"));

        if (basePageRequest.getPageType() == 0){
            List<Record> records = Db.find(Db.getSqlPara("pmp.contractPayment.queryAdvanceList", kv));
            records.forEach(record -> {
                BigDecimal contract_id = Db.queryBigDecimal("SELECT SUM(pcpr.practica_advanced)  FROM pmp_contract_payment_record pcpr LEFT JOIN pmp_contract pc ON pcpr.contract_id = pc.contract_id WHERE pc.contract_id = ?", record.getLong("contract_id"));
                if (contract_id == null){
                    contract_id = new BigDecimal(0);
                }
                record.set("totalPayment",contract_id);
            });
            return R.ok().put("data",records);
        }else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.contractPayment.queryAdvanceList", kv));
            paginate.getList().forEach(record -> {
                BigDecimal contract_id = Db.queryBigDecimal("SELECT SUM(pcpr.practica_advanced)  FROM pmp_contract_payment_record pcpr LEFT JOIN pmp_contract pc ON pcpr.contract_id = pc.contract_id WHERE pc.contract_id = ?", record.getLong("contract_id"));
                if (contract_id == null){
                    contract_id = new BigDecimal(0);
                }
                record.set("totalPayment",contract_id);
            });
            return R.ok().put("data", paginate);
        }
    }

    public R queryAdvanceBybillId(Long billId) {
        Kv kv = Kv.by("billId", billId).set("orderBy","1");
        Record first = Db.findFirst(Db.getSqlPara("pmp.contractPayment.queryAdvanceList", kv));
        BigDecimal contract_id = Db.queryBigDecimal("SELECT SUM(pcpr.practica_advanced)  FROM pmp_contract_payment_record pcpr LEFT JOIN pmp_contract pc ON pcpr.contract_id = pc.contract_id WHERE pc.contract_id = ?", first.getLong("contract_id"));
        if (contract_id == null){
            contract_id = new BigDecimal(0);
        }
        first.set("totalPayment",contract_id);
        return R.ok().put("data", first);
    }

    public R setPriority(long billId, String priority) {
        PmpContractPayment contractPayment = new PmpContractPayment();
        contractPayment.setBillId(billId);
        contractPayment.setPriority(priority);
        boolean update = contractPayment.update();
        return update? R.ok() : R.error();
    }

    public R queryPaymentDetail(Long contractId) {
        // 预付款
        List<Record> records1 = Db.find("select * from pmp_contract_payment as pcp where pcp.contract_id = ? and pcp.trade_form =" + PmpInterface.contractPayment.trade.form.EXPENF + " and pcp.status =" + PmpInterface.contractPayment.trade.stats.OK, contractId);
        //账单ID  bill_id
        //合同ID  contract_id
        //款项    payment_name
        //预计付款时间    payment_node
        //支付比例      cost_percentage
        //支付金额      money
        //实际支付比例
        //实际支付金额
        //关联任务      ...
        //状态        trade_status
        //备注
        //付款记录
        List<Record> records = Db.find("select * from pmp_contract_payment_record as pcpr where pcpr.contract_id = ? ", contractId);
        //账单ID  payment_record_id
        //合同ID  contract_id
        //款项    payment_clause
        //预计付款时间    payment_time
        //支付比例      advance_ratio
        //支付金额      amount_advanced
        //实际支付比例    practical_ratio
        //实际支付金额    practica_advanced
        //关联任务      ...
        //状态
        //备注        remark
        records.forEach(record -> record.set("tradeStatus","1"));
        for (Record record : records1) {
            Record record1 = new Record();
            record1.set("paymentRecordId", record.getLong("bill_id"));//账单ID
            record1.set("contractId", record.getLong("contract_id"));//合同ID
            record1.set("paymentClause", record.getStr("payment_name"));//款项
            record1.set("payment_time", record.get("payment_node"));//预计付款时间
            record1.set("advance_ratio", record.getInt("cost_percentage"));//支付比例
            record1.set("amount_advanced", record.get("money"));//支付金额
            record1.set("practicalRatio", null);//实际支付比例
            record1.set("practicaAdvanced", null);//实际支付金额
            record1.set("task", "");//关联任务
            record1.set("tradeStatus", record.getStr("trade_status"));//状态
            record1.set("remark", "");//备注
            records.add(record1);

        }
        return R.ok().put("paymentDetails",records);
    }

    /**
     * 预付款报表
     * @param parseObject 条件
     * @return 报表
     */
    public R paymentReport(JSONObject parseObject) {
        LocalDate startTime = LocalDate.parse(parseObject.getString("startTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endTime = LocalDate.parse(parseObject.getString("endTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Kv kv = Kv.by("startTime", startTime)
                .set("endTime", endTime)
                .set("customerId", parseObject.get("customerId"))
                .set("projectId", parseObject.get("projectId"));
        List<Record> records = null;
        if (endTime.toEpochDay() - startTime.toEpochDay() > 300){
            records = Db.find(Db.getSqlPara("pmp.contractPayment.paymentDashboard2", kv));
        }else {
            records = Db.find(Db.getSqlPara("pmp.contractPayment.paymentDashboard1", kv));
        }
        //报表曲线图
        int contractCount = 0;
        for (Record record : records) {
            contractCount = contractCount + record.getInt("ids");
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
        return R.ok().put("dashboard",records).put("contractCount",contractCount).put("receivableAmountMoney",receivableAmountMoney);
    }

    public R update(PmpContractPayment bills) {
        return bills.update() ? R.ok() : R.error();
    }

    public R confirmPayment(Long billId) {

        PmpContractPayment contractPayment = PmpContractPayment.dao.findById(billId);
//        PmpContract byId1 = PmpContract.dao.findById(byId.getContractId());
//        if (byId.getCheckStatus() != 1){
//            return R.error("审批过程中,无法直接付款");
//        }
        Integer paymentClause = contractPayment.getPaymentClause();
        Task byId = Task.dao.findById(paymentClause);
        if (byId.getStatus() != 5){
            return R.error("有任务未完成,请完成任务后付款,或者申请付款!");
        }
        contractPayment.setPracticalMoney(contractPayment.getMoney());
        contractPayment.setTradeStatus(PmpInterface.contractPayment.trade.stats.OK);
        contractPayment.setPracticalCostPercentage(contractPayment.getCostPercentage());
        contractPayment.update();
        PmpContractPaymentRecord pmpContractPaymentRecord = new PmpContractPaymentRecord();
        pmpContractPaymentRecord.setPaymentClause(contractPayment.getPaymentName());//款项名称
        pmpContractPaymentRecord.setCustomerId(contractPayment.getCustomerId()); //供应商
        pmpContractPaymentRecord.setContractId(contractPayment.getContractId());//合同
        pmpContractPaymentRecord.setAdvanceRatio(contractPayment.getCostPercentage());//预付款比例
        pmpContractPaymentRecord.setAmountAdvanced(contractPayment.getMoney());//预付款金额
        pmpContractPaymentRecord.setPracticaAdvanced(contractPayment.getMoney());//实际付款金额
        pmpContractPaymentRecord.setPracticalRatio(contractPayment.getCostPercentage());//实际付款比例
        pmpContractPaymentRecord.setPaymentTime(new Date(System.currentTimeMillis()));//付款时间
        pmpContractPaymentRecord.setPaymentType("支付宝");//付款方式
        pmpContractPaymentRecord.setResponsiblePerson("经办人");//经办人/负责人
        pmpContractPaymentRecord.setRemark(contractPayment.getRemark());//备注
        pmpContractPaymentRecord.setUpdateTime(new Date(System.currentTimeMillis()));//更新时间
        pmpContractPaymentRecord.setCreateTime(new Date(System.currentTimeMillis()));//创建时间
        pmpContractPaymentRecord.setPlannedPaymentTime(contractPayment.getPaymentNode());//计划付款时间
        pmpContractPaymentRecord.setDeduction(contractPayment.getPracticalMoney().subtract(contractPayment.getMoney()));//扣款金额
        pmpContractPaymentRecord.setBatchId(IdUtil.simpleUUID());//批次ID
        pmpContractPaymentRecord.save();
        return R.ok();
    }

    @Before(Tx.class)
    public R requestPayment(PmpContractPayment pmpContractPayment, Long checkUserId) {
        Map<String, Integer> map = examineRecordService.saveExamineRecord(2, checkUserId, pmpContractPayment.getOwnerUserId(), null, null);
        if (map.get("status") == 0) {
            R.error("没有启动的审核步骤，不能添加！");
        } else {
            pmpContractPayment.setExamineRecordId(map.get("id"));
        }
        if (pmpContractPayment.getCheckStatus() != null && pmpContractPayment.getCheckStatus() == 5) {
            pmpContractPayment.setCheckStatus(5);
        } else {
            pmpContractPayment.setCheckStatus(3);
        }
//        crmRecordService.updateRecord(new PmpContractPayment().dao().findById(pmpContractPayment.getBillId()), pmpContractPayment, CrmEnum.PMP_PAYMENT);

        pmpContractPayment.setUpdateTime(new Date(System.currentTimeMillis()));
        boolean update = pmpContractPayment.update();
        return update ?R.ok(map.get("id").toString()):R.error();
    }
}
