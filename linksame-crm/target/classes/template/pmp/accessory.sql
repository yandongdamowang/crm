#namespace("pmp.accessory")
    #sql("queryByContractId")
    select pa.*
    from pmp_accessory as pa
    where pa.is_deleted = '0' and pa.contract_id = ?
    #end

    #sql("queryList")
        select
            pa.accessory_id,
            pa.accessory_type,
            pa.creation_time,
            pa.update_time,
            pa.contract_id,
            pa.project_id,
            pa.milestone_nodes,
            pa.status,
            pa.is_deleted,
            pa.accessory_path,
            pa.accessory_name,
            pa.creation_name,
            pa.accessory_size
        from pmp_accessory as pa
            where pa.status = 1 and  pa.is_deleted = 0
            #if(accessoryName)
                and pa.accessory_name like concat('%', #para(accessoryName), '%')
            #end
            #if(accessoryType)
                and pa.accessory_type = #para(accessoryType)
            #end
    #end
#end
