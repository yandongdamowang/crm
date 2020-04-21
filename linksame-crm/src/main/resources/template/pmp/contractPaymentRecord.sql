#namespace("pmp.contractPaymentRecord")
    #sql("queryList")
        select
            pcpr.* ,pc.contract_number
        from pmp_contract_payment_record as pcpr left join pmp_contract as pc on pcpr.contract_id = pc.contract_id
            where 1 = 1 and
            #if(contractNumber)
                and pc.contract_number like concat('%', #para(contractNumber), '%')
            #end
            #if(paymentNumber)
                and pcpr.payment_number like concat('%', #para(paymentNumber), '%')
            #end
            #if(supplierId)
                and pcpr.supplier_id = #para(supplierId)
            #end
            #if(startTime)
                pcpr.payment_time between DATE_FORMAT(#para(startTime), '%Y-%m-%d') and DATE_FORMAT(#para(endTime), '%Y-%m-%d')
            #end
            #if(orderBy =='1')
               order by pcpr.payment_time asc
            #else
                order by pcpr.payment_time desc
            #end
    #end


    #sql("queryById")
        select
            pcpr.*
        from pmp_contract as pc join pmp_contract_payment_record as pcpr on pcpr.contract_id = pc.contract_id
            where 1 = 1
            #if(paymentRecordId)
                and pcpr.payment_record_id = #para(paymentRecordId)
            #end
    #end
#end
