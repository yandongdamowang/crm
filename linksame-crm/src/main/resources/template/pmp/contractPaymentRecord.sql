#namespace("pmp.contractPaymentRecord")
    #sql("queryList")
        select
pcpr.payment_record_id,
pcpr.payment_clause,
pcpr.total_money,
pcpr.payment_number,
pcpr.supplier_id,
pcpr.contract_id,
pcpr.advance_ratio,
pcpr.amount_advanced,
pcpr.practical_ratio,
pcpr.practica_advanced,
pcpr.payment_time,
pcpr.payment_type,
pcpr.responsible_person,
pcpr.remark,
pcpr.update_time,
pcpr.create_time
        from pmp_contract_payment_record as pcpr
            where 1 = 1 and
            #if(paymentNumber)
                and pa.payment_number like concat('%', #para(paymentNumber), '%')
            #end
            #if(supplierId)
                and pa.supplier_id = #para(supplierId)
            #end
    #end
#end
