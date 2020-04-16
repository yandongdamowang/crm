#namespace("admin.file")
    #sql("queryByBatchId")
      SELECT a.file_id,a.file_name, CONCAT(FLOOR(a.size/1000),"KB") as size,a.create_user_id,b.realname as create_user_name,a.create_time,a.path,a.file_type,a.batch_id
      FROM `admin_file` as a inner join `admin_user` as b on a.create_user_id = b.user_id
      where a.batch_id=?
    #end
    #sql("queryPathByBatchId")
      SELECT file_name FROM `admin_file` as a where a.batch_id = ?
    #end
    #sql("queryPageList")
      select af.*
      from `admin_file` AS af
      where af.create_user_id = #para(createUserId) and af.batch_id = #para(batchId)
        #if(oldName)
            and af.old_name like concat('%', #para(oldName), '%')
        #end
        #if(orderBy == '1')
           order by af.create_time asc
        #else
            order by af.create_time desc
        #end
    #end
#end
