#namespace("pmp.contract")
    #sql("queryById")
        select pc.*,cc.contractor_name,ccs.name
        from pmp_contract as pc LEFT JOIN crm_customer AS cc ON pc.customer_id = cc.customer_id
        LEFT JOIN crm_contacts AS ccs ON pc.contacts_id = ccs.contacts_id
        where pc.contract_id = ?
    #end

    #sql("queryList")
        select
            pc.contract_id,
            pc.contract_number,
            pc.file_number,
            pc.purchase_order_number,
            pc.milestone_nodes,
            pc.contract_name,
            pc.status,
            pc.project_id,
            pc.required_signing_time,
            pc.contract_purchase_end_time,
            pc.batch_id,
            pc.create_user_id,
            pc.owner_user_id,
            pc.customer_id,
            pc.contacts_id,
            pc.company_user_id,
            pc.proprietor,
            au.realname,
            cc.contractor_name,
            ccs.name
        from pmp_contract as pc
        LEFT JOIN crm_customer AS cc ON pc.customer_id = cc.customer_id
        LEFT JOIN crm_contacts AS ccs ON pc.contacts_id = ccs.contacts_id
        LEFT JOIN admin_user AS au ON pc.company_user_id = au.user_id

            where 1=1 and pc.is_deleted = '0' and pc.status = '1'
            #if(contractNumber)
                and pc.contract_number like concat('%', #para(contractNumber), '%')
            #end
            #if(customerId)
                and pc.customer_id = #para(customerId)
            #end
            #if(orderBy =='1')
               order by pc.required_signing_time DESC,pc.actual_signing_time DESC
            #else
                order by pc.actual_signing_time DESC,pc.required_signing_time DESC
            #end
    #end
    #sql("contractDashboard")
        select
            pc.contract_id,
            pc.contract_number,
            pc.file_number,
            pc.purchase_order_number,
            pc.milestone_nodes,
            pc.contract_name,
            pc.drp_number,
            pc.project_id,
            pc.required_signing_time,
            pc.actual_signing_time,
            pc.purchase_time,
            pc.begin_time,
            pc.end_time,
            pc.creation_time,
            pc.update_time,
            pc.money,
            pc.contract_purchase_start_time,
            pc.contract_purchase_end_time,
            pc.batch_id,
            pc.create_user_id,
            pc.owner_user_id,
            pc.customer_id,
            pc.contacts_id,
            pc.company_user_id
        from pmp_contract AS pc
         where pc.is_deleted = '0' and pc.status = '1' and
         pc.begin_time between DATE_FORMAT(#para(startTime), '%Y-%m-%d') and DATE_FORMAT(#para(endTime), '%Y-%m-%d')
         and
         pc.end_time between DATE_FORMAT(#para(startTime), '%Y-%m-%d') and DATE_FORMAT(#para(endTime), '%Y-%m-%d')

    #end

    #sql("deleteByIds")
      delete from pmp_contract where contract_id = ?
    #end

#end
