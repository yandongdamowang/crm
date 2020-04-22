#namespace("admin.file")
    #sql("queryByBatchId")
      SELECT
        a.file_id,
        a.bucket_name,
        a.old_name,
        a.file_name,
        CONCAT(FLOOR(a.size/1000),"KB") as size,
        a.create_user_id,
        b.realname as create_user_name,
        a.create_time,
        a.path,
        a.file_type,
        a.batch_id
      FROM `admin_file` as a inner join `admin_user` as b on a.create_user_id = b.user_id
      where a.batch_id = ?
    #end
    #sql("queryFileNameByBatchId")
      SELECT file_name FROM `admin_file` as a where a.batch_id = ?
    #end
    #sql("queryPageList")
      select
        a.file_id,
        a.bucket_name,
        a.old_name,
        a.file_name,
        CONCAT(FLOOR(a.size/1000),"KB") as size,
        a.create_user_id,
        b.realname as create_user_name,
        a.create_time,
        a.path,
        a.file_type,
        a.folder_id,
        a.batch_id
      from `admin_file` as a inner join `admin_user` as b on a.create_user_id = b.user_id
      where 1=1
        #if(createUserId)
            and a.create_user_id = #para(createUserId)
        #end
        #if(batchId)
            and a.batch_id = #para(batchId)
        #end
        #if(folderId)
            and a.folder_id = #para(folderId)
        #end
        #if(oldName)
            and a.old_name like concat('%', #para(oldName), '%')
        #end
        #if(orderBy == '1')
           order by a.create_time asc
        #else
            order by a.create_time desc
        #end
    #end
    #sql("queryFile")
        select a.*,b.realname as createName from admin_file a
            inner join admin_user b on a.create_user_id = b.user_id
            where a.file_id = #para(fileId)
    #end
#end
