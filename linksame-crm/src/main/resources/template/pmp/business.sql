#namespace("pmp.business")

    #sql("queryList")
        select
            pc.business_id,
            pc.object_id,
            pc.correlation_id,
            pc.object_type,
        from pmp_business as pb
            where 1=1
            #if(businessId)
                and pb.business_id = #para(businessId)
            #end
            #if(objectType)
                and pb.object_type = #para(objectType)
            #end
            #if(objectId)
                and pb.object_id = #para(objectId)
            #end
    #end

    #sql("deleteByIds")
      delete from pmp_business where contract_type = ?
    #end
#end
