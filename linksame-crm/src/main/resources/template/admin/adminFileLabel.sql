#namespace("admin.label")
    #sql("queryList")
      select
		a.*,
		b.realname as realname
	    from `admin_file_label` as a
	    left join `admin_user` as b on a.create_user_id = b.user_id
        where 1=1
        #if(createUserId)
            and a.create_user_id = #para(createUserId)
        #end
        #if(name)
            and a.name like concat('%', #para(name), '%')
        #end
        #if(isCommon)
            and a.is_common = #para(isCommon)
        #end
        #if(orderBy == 1)
           order by a.create_time asc
        #else
            order by a.create_time desc
        #end
    #end
    #sql("queryLabelByIds")
      select * from admin_file_label where status = 0
      #if(fileLabelList&&fileLabelList.size()>0)
        and file_label_id in (
           #for(i : fileLabelList)
               #(for.index > 0 ? "," : "")#para(i)
           #end
        )
      #end
    #end
#end
