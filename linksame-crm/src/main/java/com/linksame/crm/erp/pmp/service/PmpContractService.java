package com.linksame.crm.erp.pmp.service;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.erp.pmp.common.PmpInterface;
import com.linksame.crm.erp.pmp.entity.PmpAccessory;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;

import java.math.BigDecimal;
import java.util.List;

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
            pmpContract.getPmpContractPayment().forEach(contractPayment -> {
                money1.add(contractPayment.getMoney());
                contractPayment.setContractId(pmpContract.getLong("contract_id"));
                contractPayment.setProjectId(pmpContract.getLong("project_id"));
                contractPayment.setTradeForm(PmpInterface.contractPayment.trade.form.EXPENF);
                contractPayment.setTradeStatus(PmpInterface.contractPayment.trade.stats.OK);
                contractPayment.save();
            });
            if (money.compareTo(money1) != 0){
                return false;
            }
            //保存 附件
            String realname = BaseUtil.getUser().getRealname();
            pmpContract.getPmpAccessories().forEach(pmpAccessory -> {
                pmpAccessory.setContractId(pmpContract.getLong("contract_id"));
                pmpAccessory.setProjectId(pmpContract.getLong("project_id"));
                pmpAccessory.setMilestoneNodes(pmpContract.getLong("milestone_nodes"));
                pmpAccessory.setCreationName(realname);
                pmpAccessory.save();
            });
            return true;
        }) ? R.ok() : R.error();
    }

    public R queryById(Long contractId) {
        PmpContract pmpContract = PmpContract.dao.findFirst(Db.getSql("pmp.contract.queryById"), contractId);
        //供应商名称
        pmpContract.setSupplierName("此处添加供应商名称");
        pmpContract.setAgentName("此处添加供应参与人名称");
        pmpContract.remove("status","is_deleted");
        List<PmpContractPayment> contractPayment = pmpContractPaymentService.findByContractId(contractId);
        List<PmpAccessory> pmpAccessories = pmpAccessoryService.findByContractId(contractId);
        return R.ok().put("pmpContract",pmpContract).put("pmpContractPayment",contractPayment).put("pmpAccessories",pmpAccessories);
    }
}
