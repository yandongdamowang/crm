#namespace("admin.type")
    #sql("queryList")
        select a.*,b.realname as realname from admin_file_type a
        inner join admin_user b on a.create_user_id = user_id
        where a.create_user_id = #para(createUserId) and is_del = 0
        #if(typeName)
            and a.type_name like concat('%', #para(typeName), '%')
        #end
        #if(typeCode)
            and a.type_code = #para(typeCode)
        #end
    #end
#end