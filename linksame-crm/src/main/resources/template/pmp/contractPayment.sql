#namespace("pmp.contractPayment")
    #sql("queryByContractId")
        select pcp.*
        from pmp_contract_payment as pcp
        where pcp.is_deleted = '0'
            #if(tradeStatus)
                and pcp.trade_status = #para(tradeStatus)
            #end
            #if(contractId)
                and pcp.contract_id = #para(contractId)
            #end
    #end

    #sql("queryList")
        select pcp.*,pc.contract_id,pc.contract_name,pc.contract_name,pc.money as contractMoney,pc.proprietor
        from  pmp_contract as pc left join pmp_contract_payment as pcp on pcp.contract_id = pc.contract_id
        where pcp.is_deleted = '0'
        and pcp.status = '1'
            #if(tradeForm)
                and pcp.trade_form = #para(tradeForm)
            #end
            #if(tradeStatus)
                and pcp.trade_status = #para(tradeStatus)
            #end
            #if(paymentNodeDateYears)
                and  year(pcp.payment_node)=#para(paymentNodeDateYears)
            #end
            #if(paymentNodeDateMonth)
                and month(pcp.payment_node)=#para(paymentNodeDateMonth)
            #end
    #end

    #sql("queryAdvanceList")
        select pcp.*,pc.contract_number,pc.supplier_id,pc.money as contractMoney,pc.proprietor
        from  pmp_contract as pc left join pmp_contract_payment AS pcp on pcp.contract_id = pc.contract_id
        where pcp.is_deleted = '0'
            and pcp.trade_status <> '1'
            #if(billId)
                and pcp.bill_id = #para(billId)
            #end
            #if(contractNumber)
                and pc.contract_number like concat('%', #para(contractNumber), '%')
            #end
            #if(supplierId)
                and pc.supplier_id = #para(supplierId)
            #end
            #if(orderBy =='1')
               order by pcp.priority asc
            #else
                order by pcp.priority desc
            #end
    #end
#end
