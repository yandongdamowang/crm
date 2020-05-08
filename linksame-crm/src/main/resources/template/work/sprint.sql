#namespace("task.sprint")
  #sql ("queryList")
      select a.*,b.realname as createUsername from task_sprint a
	    left join admin_user b on a.create_user_id = b.user_id
	    where 1=1 and a.is_del = 0
	    #if(sprintName)
            and a.name like concat('%', #para(sprintName),'%')
        #end
        #if(status)
            and a.status = #para(status)
        #end
        #if(createUserId)
            and a.create_user_id = #para(createUserId)
        #end
        #if(orderBy == 1)
            order by a.create_time asc
        #else
            order by a.create_time desc
        #end
  #end
#end