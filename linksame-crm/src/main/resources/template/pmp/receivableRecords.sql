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
            prr.collecting_time,
            prr.agent
        from pmp_receivable_records as prr
            where 1 = 1 and
            #if(paymentMethod)
                and prr.payment_method like concat('%', #para(paymentMethod), '%')
            #end
            #if(contractId)
                and prr.contract_id = #para(contractId)
            #end
            #if(collectingStarttime)
                prr.collecting_time between DATE_FORMAT(#para(collectingStarttime), '%Y-%m-%d') and DATE_FORMAT(#para(collectingEndtime), '%Y-%m-%d')
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
#end