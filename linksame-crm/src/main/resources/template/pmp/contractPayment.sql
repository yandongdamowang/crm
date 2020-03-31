#namespace("pmp.contractPayment")
    #sql("queryByContractId")
    select pcp.*
    from pmp_contract_payment as pcp
    where pcp.is_deleted = '0' and pcp.contract_id = ?
    #end
#end
