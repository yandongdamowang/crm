#namespace("admin.folder")
    #sql("queryFolderList")
        select aff.*
        from `admin_file_folder` AS aff
        where 1=1
        #if(createUserId)
            and aff.create_user_id = #para(createUserId)
        #end
    #end
    #sql("queryFolderByPid")
        select * from admin_file_folder where folder_pid = #para(folderId)
    #end
#end
