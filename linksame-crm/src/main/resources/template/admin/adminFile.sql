#namespace("admin.file")
    #sql("queryByBatchId")
      select
		a.file_id,
		a.bucket_name,
		a.old_name,
		a.composition_name,
		a.file_name,
		CONCAT(FLOOR(a.size/1000),"KB") as size,
		a.path,
		a.suffix,
		a.file_type,
		a.folder_id,
		c.folder_name as folder_name,
		a.label_id,
		d.name as label_name,
		a.work_id,
		e.name as work_name,
		a.type_id,
		f.type_name as type_name,
		a.create_user_id,
		b.realname as create_user_name,
		a.create_time,
		a.batch_id,
		a.is_main_version,
		a.history_file_id,
		a.file_version,
		a.file_remark,
		a.del_flag,
		a.del_user_id,
		g.realname as del_user_name,
		a.del_time
	from `admin_file` as a
	left join `admin_user` as b on a.create_user_id = b.user_id
	left join `admin_file_folder` as c on a.folder_id = c.folder_id
	left join `admin_file_label` as d on a.label_id = d.file_label_id
	left join `work` as e on a.work_id = e.work_id
	left join `admin_file_type` as f on a.type_id = f.type_id
	left join `admin_user` as g on a.del_user_id = g.user_id
      where a.batch_id = ? and a.del_flag = 0
    #end
    #sql("queryFileNameByBatchId")
      SELECT file_name FROM `admin_file` as a where a.batch_id = ?
    #end
    #sql("queryList")
      select
		a.file_id,
		a.bucket_name,
		a.old_name,
		a.composition_name,
		a.file_name,
		CONCAT(FLOOR(a.size/1000),"KB") as size,
		a.path,
		a.suffix,
		a.file_type,
		a.folder_id,
		c.folder_name as folder_name,
		a.label_id,
		d.name as label_name,
		a.work_id,
		e.name as work_name,
		a.type_id,
		f.type_name as type_name,
		a.create_user_id,
		b.realname as create_user_name,
		a.create_time,
		a.batch_id,
		a.is_main_version,
		a.history_file_id,
		a.file_version,
		a.file_remark,
		a.del_flag,
		a.del_user_id,
		g.realname as del_user_name,
		a.del_time
	from `admin_file` as a
	left join `admin_user` as b on a.create_user_id = b.user_id
	left join `admin_file_folder` as c on a.folder_id = c.folder_id
	left join `admin_file_label` as d on a.label_id = d.file_label_id
	left join `work` as e on a.work_id = e.work_id
	left join `admin_file_type` as f on a.type_id = f.type_id
	left join `admin_user` as g on a.del_user_id = g.user_id
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
        #if(workId)
            and a.work_id = #para(workId)
        #end
        #if(oldName)
            and a.old_name like concat('%', #para(oldName), '%')
        #end
        #if(delFlag)
            and a.del_flag = #para(delFlag)
        #end
        #if(orderBy == 1)
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
    #sql("queryByUserId")
      select
		a.file_id,
		a.bucket_name,
		a.old_name,
		a.composition_name,
		a.file_name,
		CONCAT(FLOOR(a.size/1000),"KB") as size,
		a.path,
		a.suffix,
		a.file_type,
		a.folder_id,
		c.folder_name as folder_name,
		a.label_id,
		d.name as label_name,
		a.work_id,
		e.name as work_name,
		a.type_id,
		f.type_name as type_name,
		a.create_user_id,
		b.realname as create_user_name,
		a.create_time,
		a.batch_id,
		a.is_main_version,
		a.history_file_id,
		a.file_version,
		a.file_remark,
		a.del_flag,
		a.del_user_id,
		g.realname as del_user_name,
		a.del_time
	from `admin_file` as a
	left join `admin_user` as b on a.create_user_id = b.user_id
	left join `admin_file_folder` as c on a.folder_id = c.folder_id
	left join `admin_file_label` as d on a.label_id = d.file_label_id
	left join `work` as e on a.work_id = e.work_id
	left join `admin_file_type` as f on a.type_id = f.type_id
	left join `admin_user` as g on a.del_user_id = g.user_id
      where a.create_user_id = #para(createUserId) and a.del_flag = 0
    #end
#end
