#namespace("admin.message")
  #sql("queryMessagesByUser")
    select am.*,au.realname as realName,t.name as taskName from admin_message am
        left join admin_user au on am.recipient_user = au.user_id
        left join task t on am.type_id = t.task_id
        where au.user_id = ?
  #end
#end
