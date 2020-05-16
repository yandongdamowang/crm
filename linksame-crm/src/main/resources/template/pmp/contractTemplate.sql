#namespace("pmp.contractTemplate")

    #sql("deleteByIds")
      delete from pmp_contract_template where contract_type = ?
    #end

    #sql("queryList")
        select
            pct.contract_type,
            pct.template_name,
            pct.creation_time,
            pct.update_time
        from pmp_contract_template as pct
    #end
#end
