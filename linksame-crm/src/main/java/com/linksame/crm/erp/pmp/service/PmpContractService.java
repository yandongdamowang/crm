package com.linksame.crm.erp.pmp.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
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
import com.linksame.crm.erp.crm.entity.CrmContract;
import com.linksame.crm.erp.crm.service.CrmRecordService;
import com.linksame.crm.erp.pmp.common.PmpInterface;
import com.linksame.crm.erp.pmp.entity.PmpBusiness;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.work.entity.Task;
import com.linksame.crm.erp.work.service.TaskService;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ZhangJie
 * @ClassName PmpContractService
 * @date2020/3/28 12:51
 * @Description TODO
 **/
public class PmpContractService {

    @Inject
    private CrmRecordService crmRecordService;
    @Inject
    private TaskService taskService;
    @Inject
    private PmpContractPaymentService pmpContractPaymentService;
    @Inject
    private AdminExamineRecordService examineRecordService;

    @Before(Tx.class)
    public R add(PmpContract pmpContract,List<PmpContractPayment> pmpContractPayments,List<Task> tasks) {
        if (!Db.tx(() -> {
            pmpContract.setCreationTime(new Date(System.currentTimeMillis()));
            pmpContract.setUpdateTime(new Date(System.currentTimeMillis()));
            pmpContract.setCheckStatus(0);
            pmpContract.setCreateUserId(BaseUtil.getUserId());
            pmpContract.setRwUserId(",");
            pmpContract.setRoUserId(",");
            pmpContract.save();
            Long contractId = pmpContract.getLong("contract_id");
            int i = contractId.intValue();
            crmRecordService.addRecord(i  , CrmEnum.CRM_CONTRACT);
            BigDecimal money = pmpContract.getMoney();
            BigDecimal money1 = new BigDecimal(0);
            //保存 合同
            //保存付款单
            for (PmpContractPayment contractPayment : pmpContractPayments) {
                if (contractPayment.getMoney() != null
                        && contractPayment.getPaymentNode() != null
                        && contractPayment.getPaymentName() != null
                        && contractPayment.getCostPercentage() != null) {
                    tasks.forEach(task -> {
                        if (contractPayment.getPaymentClause() != null && contractPayment.getPaymentClause().equals(task.getTaskId())){
                            task.setTaskId(null);
                            R r = taskService.setTask(task, null);
                            System.out.println(r);
                            Kv data = (Kv)r.get("data");
                            Integer taskId = data.getInt("task_id");
                            task.setTaskId(taskId);
                            contractPayment.setPaymentClause(taskId);
                        }
                    });
                    money1 = money1.add(contractPayment.getMoney());
                    contractPayment.setContractId(pmpContract.getLong("contract_id"));
                    contractPayment.setProjectId(pmpContract.getInt("project_id"));
                    contractPayment.setTradeForm(PmpInterface.contractPayment.trade.form.EXPENF);
                    contractPayment.setTradeStatus(PmpInterface.contractPayment.trade.stats.TRADING);
                    contractPayment.setCreationTime(new Date(System.currentTimeMillis()));
                    contractPayment.setpracticalMoney(new BigDecimal(0));
                    contractPayment.setPracticalCostPercentage(0);
                    contractPayment.save();
                    Long billId = contractPayment.getLong("bill_id");
                    crmRecordService.addRecord(billId.intValue()  , CrmEnum.PMP_PAYMENT);
                } else {
                    return false;
                }
            }
            if (money.compareTo(money1) != 0) {
                return false;
            }
            tasks.forEach(task -> {
                if (task.getTaskId() != null){
                    R r = taskService.setTask(task, null);
                }
            });
            return true;
        })) return R.error();
        return R.ok();
    }

    public R queryById(Long contractId) {
        PmpContract pmpContract = PmpContract.dao.findFirst(Db.getSql("pmp.contract.queryById"), contractId);
        //供应商名称
        if (pmpContract != null){
            pmpContract.remove("status","is_deleted");
        }

//        List<PmpContractPayment> contractPayment = pmpContractPaymentService.findByContractId(contractId,null);
        return R.ok().put("pmpContract",pmpContract);
    }

    public R queryList(BasePageRequest basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv= Kv.by("contractNumber", jsonObject.getString("contractNumber"))
                .set("customerId", jsonObject.getLong("customerId"))
                .set("orderBy", jsonObject.get("orderBy"));
        List<Record> groupBy = Db.find("SELECT pc.customer_id,cc.contractor_name FROM pmp_contract AS pc LEFT JOIN crm_customer AS cc ON pc.customer_id = cc.customer_id WHERE 1=1 GROUP BY pc.customer_id");
        if (basePageRequest.getPageType() == 0){
            List<Record> records = Db.find(Db.getSqlPara("pmp.contract.queryList", kv));
            billLoading(records);
            return R.ok().put("data",records).put("groupBy",groupBy);
        }else {
            Page<Record> records = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.contract.queryList", kv));
            billLoading(records.getList());
            return R.ok().put("data",records).put("groupBy",groupBy);
        }
    }

    private final String PAYMENTRATION = "paymentRatio"; //支付比例
    private final String PAYMENTAMOUNT = "paymentAmount";//支付金额
    private final String CUMULATIVEPAYMENTRATION = "cumulativePaymentRatio";//累计比例
    private final String CUMULATIVEPAYMENT = "cumulativepayment";//累计金额
    /**
     * 装载账单
     * @param records 合同集合
     */
    private void billLoading(List<Record> records){
        Set<String> set = new HashSet<>();
        records.forEach(record -> {
            Map<String,Map<String,Object>> billDetails = new HashMap();
            List<PmpContractPayment> contractPayments = pmpContractPaymentService.findByContractId(record.getLong("contract_id"),null);
            contractPayments.forEach(contractPayment -> {
                LocalDate paymentNode = contractPayment.getPaymentNode().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int year = paymentNode.getYear();
                int monthValue = paymentNode.getMonthValue();
                if (billDetails.get(year+"年"+monthValue+"月")!=null){
                    Map<String, Object> bill = billDetails.get(year + "年" + monthValue + "月");
                    Integer paymentRatio = (Integer)bill.get(PAYMENTRATION);
                    BigDecimal paymentAmount = (BigDecimal)bill.get(PAYMENTAMOUNT);
                    Integer cumulativepaymentRatio = (Integer)bill.get(CUMULATIVEPAYMENTRATION);
                    BigDecimal cumulativepayment = (BigDecimal)bill.get(CUMULATIVEPAYMENT);
                    bill.put(PAYMENTRATION,contractPayment.getCostPercentage()+paymentRatio);
                    bill.put(PAYMENTAMOUNT,contractPayment.getMoney().add(paymentAmount));
                    bill.put(CUMULATIVEPAYMENTRATION,contractPayment.getCostPercentage()+cumulativepaymentRatio);
                    bill.put(CUMULATIVEPAYMENT,contractPayment.getMoney().add(cumulativepayment));
                }else {
                    Map<String, Object> bill = new HashMap();
                    bill.put(PAYMENTRATION,contractPayment.getCostPercentage());
                    bill.put(PAYMENTAMOUNT,contractPayment.getMoney());
                    bill.put(CUMULATIVEPAYMENTRATION,contractPayment.getCostPercentage());
                    bill.put(CUMULATIVEPAYMENT,contractPayment.getMoney());
                    String s = year + "年" + monthValue + "月";
                    set.add(s);
                    billDetails.put(s,bill);
                }
            });
            record.set("billList",billDetails);
        });
        List<String> keys = new ArrayList<>(set);
        Collections.sort(keys, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int len1 = o1.length();
                int len2 = o2.length();
                if(len1!=len2)
                    return len1-len2;
                else
                    return o1.compareTo(o2);

            }
        });
        System.out.println(keys);
        for (Record record : records) {
            Map<String, Map<String, Object>> billLists = record.get("billList");
            Map<String,Map<String,Object>> map  = new LinkedHashMap<>();
            Integer cumulativepaymentRatios = 0;
            BigDecimal cumulativepayments = new BigDecimal(0);
            for (String s : keys) {
                Map<String, Object> stringObjectMap1 = billLists.get(s);

                if (stringObjectMap1 == null) {
                    Map<String, Object> bill = new HashMap();
                    bill.put(PAYMENTRATION, 0);
                    bill.put(PAYMENTAMOUNT, new BigDecimal(0));
                    bill.put(CUMULATIVEPAYMENTRATION, cumulativepaymentRatios);
                    bill.put(CUMULATIVEPAYMENT, cumulativepayments);
                    map.put(s, bill);
                } else {
                    Integer paymentRatio = (Integer) stringObjectMap1.get(PAYMENTRATION);
                    BigDecimal paymentAmount = (BigDecimal) stringObjectMap1.get(PAYMENTAMOUNT);
                    cumulativepaymentRatios = cumulativepaymentRatios += paymentRatio;
                    cumulativepayments = paymentAmount.add(cumulativepayments);
                    Map<String, Object> bill = new HashMap();
                    bill.put(PAYMENTRATION, paymentRatio);
                    bill.put(PAYMENTAMOUNT, paymentAmount);
                    bill.put(CUMULATIVEPAYMENTRATION, cumulativepaymentRatios);
                    bill.put(CUMULATIVEPAYMENT, cumulativepayments);
                    map.put(s, bill);
                }
            }
            record.set("billList", map);
        }

//        records.forEach(record -> {
//            Map<String, Object> billList = record.get("billList");
//            Map<String, Object> collect = billList.entrySet().stream()
//                    .sorted(Map.Entry.comparingByKey())
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
//                            , (oldValue, newValue) -> oldValue, LinkedHashMap::new));
//            record.set("billList",collect);
//        });
        System.out.println(records);
    }


    @Before(Tx.class)
    public R update(JSONObject object) {
        PmpContract pmpContract = object.getObject("entity", PmpContract.class);
        pmpContract.setUpdateTime(new Date(System.currentTimeMillis()));
        PmpContract byId = PmpContract.dao.findById(pmpContract.getContractId());
        crmRecordService.updateRecord(byId,pmpContract,CrmEnum.CRM_CONTRACT);
        return pmpContract.update() ? R.ok():R.error();
    }

    public R contractDashboard(LocalDate startTime, LocalDate endTime) {
        Kv kv= Kv.by("startTime", startTime)
                .set("endTime", endTime);
        List<Record> records = Db.find(Db.getSqlPara("pmp.contract.contractDashboard", kv));
        //总支付累计付款金额
        BigDecimal okBigDecimal = new BigDecimal(0);
        //代付款金额
        BigDecimal awaitBigDecimal = new BigDecimal(0);
        int contractCountOk = 0;
        for (Record record : records) {
            Date end_time = record.get("end_time");
            LocalDate localDate = end_time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(localDate.isBefore(LocalDate.now())){
                contractCountOk = contractCountOk +1;
            }
            List<PmpContractPayment> pmpContractPayments = pmpContractPaymentService.findByContractId(record.getLong("contract_id"), null);
            for (PmpContractPayment contractPayment : pmpContractPayments) {
                BigDecimal money = contractPayment.getMoney();
                if (PmpInterface.contractPayment.trade.stats.OK.equals(contractPayment.getTradeStatus())) {
                    okBigDecimal = okBigDecimal.add(money);
                }
                if (PmpInterface.contractPayment.trade.stats.TRADING.equals(contractPayment.getTradeStatus())) {
                    awaitBigDecimal = awaitBigDecimal.add(money);
                }
            }
        }
        return R.ok().put("contractCount", records.size()).put("contractCountOk", contractCountOk).put("waitPayment",awaitBigDecimal).put("cumulativePayment",okBigDecimal);

    }

    public R delete(String contractIds) {
        String[] ids = contractIds.split(",");
        List<Record> idsList = new ArrayList<>();
        for (String id : ids) {
            Record record = new Record();
            idsList.add(record.set("contract_id", Integer.valueOf(id)));
        }
        return Db.tx(()->{
            Db.batch(Db.getSql("pmp.contract.deleteByIds"), "contract_id", idsList, 100);
            Db.batch(Db.getSql("pmp.contractPayment.deleteBycontractIds"), "contract_id", idsList, 100);
            return true;
        }) ? R.ok() : R.error();
    }

    public R contractBill(Long contractId) {
        Long money = Db.queryLong("SELECT SUM(pcp.money) FROM pmp_contract AS pc LEFT JOIN pmp_contract_payment AS pcp ON pcp.contract_id = pc.contract_id where pc.contract_id = ?",  contractId);
        Long practicaAdvanced = Db.queryLong("SELECT SUM(pcpr.practica_advanced) FROM pmp_contract AS pc LEFT JOIN pmp_contract_payment_record AS pcpr ON pcpr.contract_id = pc.contract_id where pc.contract_id = ?", contractId);
        return R.ok().put("advanceMoney",money).put("accountPaid",practicaAdvanced);
    }
}
