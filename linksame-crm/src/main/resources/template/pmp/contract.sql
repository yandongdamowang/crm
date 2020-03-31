#namespace("pmp.contract")
    #sql("queryById")
    select pc.*
    from pmp_contract as pc
    where pc.contract_id = ?
    #end
#end
