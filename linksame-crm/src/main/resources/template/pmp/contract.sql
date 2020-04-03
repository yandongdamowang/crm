#namespace("pmp.contract")
    #sql("queryById")
        select pc.*
        from pmp_contract as pc
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
            pc.supplier_id,
            pc.project_id,
            pc.required_signing_time,
            pc.actual_signing_time,
            pc.purchase_time,
            pc.begin_time,
            pc.end_time,
            pc.agent,
            pc.creation_time,
            pc.update_time,
            pc.money,
            pc.contract_purchase_start_time,
            pc.contract_purchase_end_time
        from pmp_contract as pc
            where 1=1 and pc.is_deleted = '0' and pc.status = '1'
            #if(contractNumber)
                and pc.contract_number like concat('%', #para(contractNumber), '%')
            #end
            #if(supplierId)
                and pc.supplier_id = #para(supplierId)
            #end
            #if(orderBy =="1")
               order by pc.required_signing_time DESC,pc.actual_signing_time DESC
            #else {
                order by pc.actual_signing_time DESC,pc.required_signing_time DESC
            }
            #end
    #end
#end
