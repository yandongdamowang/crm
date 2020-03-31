#namespace("pmp.accessory")
    #sql("queryByContractId")
    select pa.*
    from pmp_accessory as pa
    where pa.is_deleted = '0' and pa.contract_id = ?
    #end
#end
