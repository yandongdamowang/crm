#namespace("admin.dept")
    #sql("queryByIds")
      select dept_id as id,name from admin_dept where dept_id in (#fori(ids))
    #end
    #sql("deptSql")
     select name,dept_id from admin_dept ORDER BY num
    #end

    #sql ("queryDeptByParentUser")
      SELECT a.dept_id AS id,a.name,a.pid,b.user_id
      FROM admin_dept as a LEFT JOIN admin_user as b on a.dept_id=b.dept_id
      WHERE b.parent_id = ?
    #end
#end
