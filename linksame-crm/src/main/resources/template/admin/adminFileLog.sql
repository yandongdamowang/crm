#namespace("admin.file")
    #sql("queryFileLog")
        select a.*,b.realname as realname
            from admin_file_log as a
            left join admin_user as b on a.user_id = b.user_id
            where a.file_id = #para(fileId)
            order by a.create_time desc
    #end
#end