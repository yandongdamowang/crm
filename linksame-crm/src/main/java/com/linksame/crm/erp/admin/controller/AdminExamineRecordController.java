package com.linksame.crm.erp.admin.controller;

import com.jfinal.plugin.activerecord.Db;
import com.linksame.crm.erp.admin.service.AdminExamineRecordService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.erp.crm.common.CrmEnum;
import com.linksame.crm.erp.crm.service.CrmRecordService;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.pmp.service.PmpContractPaymentService;
import com.linksame.crm.erp.pmp.service.PmpContractService;
import com.linksame.crm.utils.AuthUtil;
import com.linksame.crm.utils.R;

import java.util.Date;
import java.util.Map;

/**
 * 审核合同或回款
 * @author zxy
 */
public class AdminExamineRecordController extends Controller {
    @Inject
    private AdminExamineRecordService examineRecordService;
    @Inject
    private PmpContractService pmpContractService;
    @Inject
    private PmpContractPaymentService pmpContractPaymentService;

    @Inject
    private CrmRecordService crmRecordService;
    /**
     * 审核合同或者付款 recordId:审核记录id status:审批状态：审核状态  1 审核通过 2 审核拒绝 4 已撤回
     * remarks:审核备注 id:审核对象的id（合同或者回款的id）
     */
    public void auditExamine(){
        Integer recordId = getInt("recordId");//recordId:审核记录id
        Integer status = getInt("status");//status:审批状态：审核状态  1 审核通过 2 审核拒绝 4 已撤回
        Integer id = getInt("id");// id:审核对象的id（合同或者回款的id）
        String remarks = get("remarks");//remarks:审核备注
        Long nextUserId = getLong("nextUserId");//下一审批人
        Long ownerUserId = getLong("ownerUserId");//审批负责人
        renderJson(examineRecordService.auditExamine(recordId,status,remarks,id,nextUserId,ownerUserId));
    }
    /**
     * 审核合同或者回款 recordId:审核记录id status:审批状态：审核状态  1 审核通过 2 审核拒绝 4 已撤回
     * remarks:审核备注 id:审核对象的id（合同或者回款的id）
     */
    public void addExamine(){
        Integer type = getInt("type");//审核合同或者付款
        Long checkUserId = getLong("checkUserId");//添加或者审核人
        Long Id = getLong("id");//预付款ID
        /**
         * type 审批类型  1 合同审批 2.付款审批
         * checkUserId 授权审批人
         * ownerUserId  负责人
         * recordId 审批ID
         * status 审批状态 审核状态 0 未审核 1 审核通过 2 审核拒绝 3 审核中 4 已撤回
         */
        PmpContract pmpContract = null;
        PmpContractPayment pmpContractPayment = null;
        if (type != null && type.equals(2)){
            pmpContractPayment = PmpContractPayment.dao.findFirst("select * from pmp_contract_payment  where contract_id = ?",Id);;
            Id = pmpContractPayment.getContractId();
        }
        pmpContract = PmpContract.dao.findById(Id);
        Map<String, Integer> map = examineRecordService.saveExamineRecord(type, checkUserId, pmpContract.getOwnerUserId(), null, null);
        if (type != null && type.equals(1)){
            if (map.get("status") == 0) {
                renderJson(R.error("没有启动的审核步骤，不能添加！"));
            } else {
                pmpContract.setExamineRecordId(map.get("id"));
            }
            if (pmpContract.getCheckStatus() != null && pmpContract.getCheckStatus() == 5) {
                pmpContract.setCheckStatus(5);
            } else {
                pmpContract.setCheckStatus(0);
            }
            crmRecordService.updateRecord(new PmpContract().dao().findById(Id), pmpContract, CrmEnum.CRM_CONTRACT);
            pmpContract.setUpdateTime(new Date(System.currentTimeMillis()));
            boolean save = pmpContract.update();
            renderJson(save ? R.ok() : R.error());
        }else if (type != null && type.equals(2)){
            if (map.get("status") == 0) {
                renderJson(R.error("没有启动的审核步骤，不能添加！"));
            } else {
                pmpContractPayment.setExamineRecordId(map.get("id"));
            }
            if (pmpContractPayment.getCheckStatus() != null && pmpContractPayment.getCheckStatus() == 5) {
                pmpContractPayment.setCheckStatus(5);
            } else {
                pmpContractPayment.setCheckStatus(0);
            }
            crmRecordService.updateRecord(new PmpContractPayment().dao().findById(Id), pmpContractPayment, CrmEnum.PMP_PAYMENT);

            pmpContractPayment.setUpdateTime(new Date(System.currentTimeMillis()));
            boolean update = pmpContractPayment.update();
            renderJson(update ? R.ok() : R.error());
        }
    }
    /**
     * 根据审核记录id，查询审核日志
     * recordId 审核记录id
     */
    public void queryExamineLogList(){
        Integer recordId = getInt("recordId");
        if(getExamineObjIdByRecordId(recordId)){
            renderJson(R.noAuth()); return;
        }
        renderJson(examineRecordService.queryExamineLogList(recordId));
    }
    /**
     * 根据审核记录id，查询审核日志
     * recordId 审核记录id ownerUserId 负责人ID
     */
    public void queryExamineRecordList(){
        Integer recordId = getInt("recordId");
        Long ownerUserId = getLong("ownerUserId");
        if(getExamineObjIdByRecordId(recordId)){
            renderJson(R.noAuth()); return;
        }
        renderJson(examineRecordService.queryExamineRecordList(recordId,ownerUserId));
    }

    /**
     * 根据recordId查询权限
     * @param recordId
     * @return
     */
    private boolean getExamineObjIdByRecordId(Integer recordId){
        boolean auth;
        Integer id = Db.queryInt("select contract_id from `pmp_contract` where examine_record_id = ?",recordId);
        if(id != null){
            auth = AuthUtil.isCrmAuth(CrmEnum.CRM_CONTRACT,id);
        }else {
            id = Db.queryInt("select bill_id from pmp_contract_payment where examine_record_id = ?",recordId);
            auth = AuthUtil.isCrmAuth(CrmEnum.PMP_PAYMENT,id);
        }
        return auth;
    }
}
