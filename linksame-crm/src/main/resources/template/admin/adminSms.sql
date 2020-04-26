#namespace("admin.sms")
    #sql("queryList")
        select * from admin_sms
        where is_del = 0
            #if(mobile)
                and mobile = #para(mobile)
            #end
            #if(content)
                and content like concat('%', #para(content), '%')
            #end
            #if(errorcode)
                and errorcode = #para(errorcode)
            #end
            #if(code)
                and code = #para(code)
            #end
            #if(sendTime)
                and DATE_FORMAT(sendTime, '%Y-%m-%d') =  DATE_FORMAT(#para(sendTime), '%Y-%m-%d')
            #end
    #end
#end