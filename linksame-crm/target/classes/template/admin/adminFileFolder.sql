#namespace("admin.folder")
    #sql("queryFolderList")
        select aff.*
        from `admin_file_folder` AS aff
        where aff.create_user_id = #para(createUserId) and aff.batch_id = #para(batchId)
    #end
    #sql("queryFolderByPid")
        select * from admin_file_folder where folder_pid = #para(folderId)
    #end
#end
