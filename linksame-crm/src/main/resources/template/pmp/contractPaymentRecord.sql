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
            pcpr.*,pc.contract_number,pc.money,pc.customer_id,pc.proprietor , ccr.customer_name
        from pmp_contract as pc join pmp_contract_payment_record as pcpr on pcpr.contract_id = pc.contract_id
				JOIN crm_customer AS ccr ON pc.customer_id = ccr.customer_id
            where 1 = 1
            #if(paymentRecordId)
                and pcpr.payment_record_id = #para(paymentRecordId)
            #end
    #end


    #sql("paymentDashboard1")
        select
            date_format(a.payment_time, '%Y-%m-%d') AS payment_time,
	        SUM(a.practica_advanced) as practicaAdvanced,
            count(a.payment_record_id) as ids
        from pmp_contract b LEFT JOIN pmp_contract_payment_record a ON b.contract_id = a.contract_id WHERE 1=1 and a.payment_time between DATE_FORMAT(#para(startTime), '%Y-%m-%d') and DATE_FORMAT(#para(endTime), '%Y-%m-%d')
            #if(supplierId)
                and a.supplier_id = #para(supplierId)
            #end
            #if(projectId)
                and a.project_id = #para(projectId)
            #end
        group by date_format(a.payment_time, '%Y-%m-%d')
    #end
     #sql("paymentDashboard2")
        select
            date_format(a.payment_time, '%Y-%m') AS payment_time,
	        SUM(a.practica_advanced) as practicaAdvanced,
            count(a.payment_record_id) as ids
        from pmp_contract b LEFT JOIN pmp_contract_payment_record a ON b.contract_id = a.contract_id WHERE 1=1 and a.payment_time between DATE_FORMAT(#para(startTime), '%Y-%m-%d') and DATE_FORMAT(#para(endTime), '%Y-%m-%d')
            #if(supplierId)
                and a.supplier_id = #para(supplierId)
            #end
            #if(projectId)
                and a.project_id = #para(projectId)
            #end
        group by date_format(a.payment_time, '%Y-%m')
    #end
#end
