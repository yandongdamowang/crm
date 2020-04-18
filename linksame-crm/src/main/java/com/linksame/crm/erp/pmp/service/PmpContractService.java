package com.linksame.crm.erp.pmp.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.common.PmpInterface;
import com.linksame.crm.erp.pmp.entity.PmpAccessory;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author ZhangJie
 * @ClassName PmpContractService
 * @date2020/3/28 12:51
 * @Description TODO
 **/
public class PmpContractService {

    @Inject
    private PmpContractPaymentService pmpContractPaymentService;
    @Inject
    private PmpAccessoryService pmpAccessoryService;

    @Before(Tx.class)
    public R add(PmpContract pmpContract) {
//        pmpContract.setContractId(BaseUtil.getUserId());
        return Db.tx(() ->{
            BigDecimal money = pmpContract.getMoney();
            BigDecimal money1 = new BigDecimal(0);
            //保存 合同
            pmpContract.save();
            //保存付款单
            for (PmpContractPayment contractPayment : pmpContract.getPmpContractPayment()) {
                if (contractPayment.getMoney() != null
                        && contractPayment.getPaymentNode() != null
                        && contractPayment.getPaymentName() != null
                        && contractPayment.getCostPercentage() != null) {
                    money1 = money1.add(contractPayment.getMoney());
                    contractPayment.setContractId(pmpContract.getLong("contract_id"));
                    contractPayment.setProjectId(pmpContract.getLong("project_id"));
                    contractPayment.setTradeForm(PmpInterface.contractPayment.trade.form.EXPENF);
                    contractPayment.setTradeStatus(PmpInterface.contractPayment.trade.stats.OK);
                    contractPayment.setCreationTime(LocalDateTime.now());
                    contractPayment.save();
                }else {
                    return false;
                }
            }
            if (money.compareTo(money1) != 0){
                return false;
            }
            //保存 附件
            String realname = BaseUtil.getUser().getRealname();
            if (pmpContract.getPmpAccessories() != null) {
                pmpContract.getPmpAccessories().forEach(pmpAccessory -> {
                    pmpAccessory.setContractId(pmpContract.getLong("contract_id"));
                    pmpAccessory.setProjectId(pmpContract.getLong("project_id"));
                    pmpAccessory.setMilestoneNodes(pmpContract.getLong("milestone_nodes"));
                    pmpAccessory.setCreationName(realname);
                    pmpAccessory.save();
                });
            }
            return true;
        }) ? R.ok() : R.error();
    }

    public R queryById(Long contractId) {
        PmpContract pmpContract = PmpContract.dao.findFirst(Db.getSql("pmp.contract.queryById"), contractId);
        //供应商名称
        pmpContract.setSupplierName("此处添加供应商名称");
        pmpContract.setAgentName("参与人名称");
        pmpContract.remove("status","is_deleted");
        List<PmpContractPayment> contractPayment = pmpContractPaymentService.findByContractId(contractId,null);
        List<PmpAccessory> pmpAccessories = pmpAccessoryService.findByContractId(contractId);
        return R.ok().put("pmpContract",pmpContract).put("pmpContractPayment",contractPayment).put("pmpAccessories",pmpAccessories);
    }

    private final String PAYMENTRATION = "paymentRatio";
    private final String PAYMENTAMOUNT = "paymentAmount";
    private final String CUMULATIVEPAYMENTRATION = "cumulativePaymentRatio";
    private final String CUMULATIVEPAYMENT = "cumulativepayment";
    public R queryList(BasePageRequest basePageRequest) {
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv= Kv.by("contractNumber", jsonObject.getString("contractNumber"))
                .set("supplierId", jsonObject.getLong("supplierId"))
                .set("orderBy", jsonObject.get("orderBy"));
        if (basePageRequest.getPageType() == 0){
            List<Record> records = Db.find(Db.getSqlPara("pmp.contract.queryList", kv));
            billLoading(records);
            return R.ok().put("data",records);
        }else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("pmp.contract.queryList", kv));
            billLoading(paginate.getList());
            return R.ok().put("data", paginate);
        }
    }

    /**
     * 装载账单
     * @param records 合同集合
     */
    private void billLoading(List<Record> records){
        records.forEach(record -> {
            List<Map<String,Map<String,Object>>> billList = new ArrayList<>();
            Map<String,Map<String,Object>> billDetails = new HashMap();
            List<PmpContractPayment> contractPayments = pmpContractPaymentService.findByContractId(record.getLong("contract_id"),null);
            contractPayments.forEach(contractPayment -> {
                LocalDate paymentNode = contractPayment.getPaymentNode().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int year = paymentNode.getYear();
                int monthValue = paymentNode.getMonthValue();
                if (billDetails.get(year+"年"+monthValue+"月")!=null){
                    Map<String, Object> bill = billDetails.get(year + "年" + monthValue + "月");
                    Long paymentRatio = (Long)bill.get(PAYMENTRATION);
                    BigDecimal paymentAmount = (BigDecimal)bill.get(PAYMENTAMOUNT);
                    Long cumulativepaymentRatio = (Long)bill.get(CUMULATIVEPAYMENTRATION);
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
                    billDetails.put(year+"年"+monthValue+"月",bill);
                    billList.add(billDetails);
                }
            });
            record.set("billList",billList);
        });
    }

    @Before(Tx.class)
    public R update(JSONObject object) {
        PmpContract pmpContract = object.getObject("entity", PmpContract.class);
        pmpContract.setUpdateTime(LocalDateTime.now());
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
        for (Record record : records) {
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
        return R.ok().put("contractCount", records.size()).put("waitPayment",awaitBigDecimal).put("cumulativePayment",okBigDecimal);

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
            Db.batch("delete from pmp_contract_payment where contract_id = ?", "batch_id", idsList, 100);
            return true;
        }) ? R.ok() : R.error();
    }
}
