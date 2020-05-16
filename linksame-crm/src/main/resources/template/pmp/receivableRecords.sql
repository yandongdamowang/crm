#namespace("pmp.receivableRecords")
    #sql("queryList")
        select
            prr.receivable_records_id,
            prr.contract_id,
            prr.receivable_type,
            prr.update_time,
            prr.created_time,
            prr.customer_id,
            prr.receivable_number,
            prr.receivable_amount,
            prr.remark,
            prr.state,
            prr.payment_method,
            prr.collecting_company,
            prr.collection_bank,
            prr.collection_account,
            prr.collection_number,
            prr.collecting_time,
            prr.agent,
            au.realname agentRealname,
            cc.contractor_name
        from pmp_receivable_records as prr LEFT JOIN admin_user as au on prr.agent = au.user_id
            LEFT JOIN crm_customer as cc on cc.customer_id = cc.customer_id
            where 1 = 1
            #if(paymentMethod)
                and prr.payment_method = #para(contractId)
            #end
            #if(contractId)
                and prr.contract_id = #para(contractId)
            #end
            #if(collectingStarttime)
                and prr.collecting_time between DATE_FORMAT(#para(collectingStarttime), '%Y-%m-%d') and DATE_FORMAT(#para(collectingEndtime), '%Y-%m-%d')
            #end
            #if(orderBy =='1')
               order by prr.collecting_time asc
            #else
                order by prr.collecting_time desc
            #end
    #end

     #sql ("queryContractNumber")
      select prr.receivable_records_id
      from pmp_receivable_records as prr left join pmp_contract as pc on prr.contract_id = pc.contract_id
      where prr.receivable_records_id in (?)
    #end

    #sql("deleteByIds")
      delete from pmp_receivable_records where receivable_records_id = ?
    #end


    #sql("paymentDashboard")
         select  a.collecting_time, SUM(a.receivable_amount) as receivableAmountMoney,
            count(a.receivable_records_id) as ids
        from pmp_contract b LEFT JOIN pmp_receivable_records a ON b.contract_id = a.contract_id WHERE 1=1 and a.collecting_time  between DATE_FORMAT(#para(startTime), '%Y-%m-%d') and DATE_FORMAT(#para(endTime), '%Y-%m-%d')
        group by a.collecting_time
    #end
#end
