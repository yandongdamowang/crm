package com.linksame.crm.erp.admin.service;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.erp.admin.entity.AdminDept;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.*;
import java.util.stream.Collectors;

public class AdminDeptService {
    public R setDept(AdminDept adminDept) {
        boolean bol;
        if (adminDept.getDeptId() == null) {
            bol = adminDept.save();
        } else {
            if (adminDept.getPid() != null && adminDept.getPid() != 0) {
                List<Record> topDeptList = queryDeptTree("update",adminDept.getDeptId());
                boolean isContain = false;
                for (Record record : topDeptList) {
                    if (record.getInt("id").equals(adminDept.getPid())) {
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    return R.error("该部门的下级部门不能设置为上级部门");
                }
            }
            bol = adminDept.update();
        }
        return R.isSuccess(bol,"设置失败");
    }

    public List<Record> queryDeptTree(String type,Integer id) {
        List<Record> allDeptList = new ArrayList<>();
        List<Record> adminDeptList = Db.find("select dept_id as id,name,pid from admin_dept");
        List<Record> recordList = buildTreeBy2Loop(adminDeptList, 0, allDeptList);
        if (StrUtil.isNotBlank(type) && "tree".equals(type)) {
            return recordList;
        } else if (StrUtil.isBlank(type) || "save".equals(type)) {
            return adminDeptList;
        } else if (StrUtil.isNotBlank(type) && "update".equals(type)){
            return queryTopDeptList(id);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 查询可设置为上级的部门
     */
    private List<Record> queryTopDeptList(Integer deptId) {
        List<Record> recordList = Db.find("select dept_id as id,name,pid from admin_dept");
        AdminUserService adminUserService = new AdminUserService();
        List<Integer> subDeptList = adminUserService.queryChileDeptIds(deptId,BaseConstant.AUTH_DATA_RECURSION_NUM);
        recordList.removeIf(record -> subDeptList.contains(record.getInt("id")));
        recordList.removeIf(record -> record.getInt("id").equals(deptId));
        return recordList;
    }

    /**
     * 通过userId查询权限内部门
     * @param userId 用户ID
     * @return 权限内部门
     * @author zhangzhiwei
     */
    public List<Record> queryDeptByAuth(Long userId) {
        //查询用户数据权限，从高到低排序
        List<Integer> list = Db.query(Db.getSql("admin.role.queryDataTypeByUserId"), userId);
        List<Record> adminDepts=new ArrayList<>();
        if(list.size()==0){
            return adminDepts;
        }
        //拥有最高数据权限
        if(list.contains(5)){
            return Db.find("select dept_id as id,name,pid from admin_dept");
        }else {
            adminDepts.add(Db.findFirst("select dept_id as id,name,pid from admin_dept where dept_id=?", BaseUtil.getUser().getDeptId()));
            if(list.contains(4)){
                adminDepts.addAll(queryDeptByParentDept(BaseUtil.getUser().getDeptId(), BaseConstant.AUTH_DATA_RECURSION_NUM));
            }
            if(list.contains(2)){
                adminDepts.addAll(queryDeptByParentUser(userId, BaseConstant.AUTH_DATA_RECURSION_NUM));
            }
        }
        ArrayList<Record> records = new ArrayList<>();
        adminDepts.stream().collect(Collectors.groupingBy(record -> record.getInt("id"))).forEach((k,v)->records.add(v.get(0)));
        return records;
    }

    public List<Record> queryDeptByParentDept(Integer deptId,Integer deepness){
        List<Record> recordList=new ArrayList<>();
        if(deepness>0){
            List<Record> records=Db.find("select dept_id as id,name,pid from admin_dept where pid=?",deptId);
            recordList.addAll(records);
            records.forEach(record -> {
                recordList.addAll(queryDeptByParentDept(record.getInt("id"),deepness-1));
            });
        }
        return recordList;
    }
    private List<Record> queryDeptByParentUser(Long userId,Integer deepness){
        List<Record> recordList=new ArrayList<>();
        if(deepness>0){
            List<Record> records=Db.find(Db.getSql("admin.dept.queryDeptByParentUser"),userId);
            recordList.addAll(records);
            records.forEach(record -> {
                recordList.addAll(queryDeptByParentUser(record.getLong("user_id"),deepness-1));
            });
        }
        return recordList;
    }
    private List<Record> buildTreeBy2Loop(List<Record> treeNodes, Integer root, List<Record> allDeptList) {
        List<Record> trees = new ArrayList<>();
        for (Record node : treeNodes) {
            if (root.equals(node.getInt("pid"))) {
                node.set("level", 1);
                node.set("label", node.getStr("name"));
                trees.add(node);
                allDeptList.add(node);
            }
            List<Record> childTrees = new ArrayList<>();
            for (Record treeNode : treeNodes) {
                if (node.getInt("id").equals(treeNode.getInt("pid"))) {
                    treeNode.set("level", node.getInt("level") + 1);
                    treeNode.set("label", treeNode.getStr("name"));
                    childTrees.add(treeNode);
                    allDeptList.add(treeNode);
                }
            }
            if (childTrees.size() != 0) {
                node.set("children", childTrees);
            }
        }
        return trees;
    }

    public R deleteDept(String id) {
        Integer userCount = Db.queryInt("select count(*) from admin_user where dept_id = ?", id);
        if(userCount>0){
            return R.error("该部门下有员工，不能删除！");
        }
        Integer childDeptCount = Db.queryInt("select count(*) from admin_dept where pid = ?", id);
        if(childDeptCount>0){
            return R.error("该部门下有下级部门，不能删除！");
        }
        int delete = Db.delete("delete from admin_dept where dept_id = ?", id);
        return delete > 0 ? R.ok() : R.error();
    }

    /**
     * 批量设置员工到某个部门
     * @param deptId    部门ID
     * @param userIds   员工ID数组字符串
     * @return
     */
    @Before(Tx.class)
    public R batchSetUserByDept(Integer deptId, String userIds){
        if(deptId == null){
            return R.error("deptId参数不能为空");
        }
        if(StringUtils.isEmpty(userIds)){
            return R.error("userIds参数没有数据");
        }
        String[] userIdArr = userIds.split(",");
        List<Record> list = new ArrayList();
        for(String userId : userIdArr){
            Record record = new Record()
                    .set("user_id", Long.parseLong(userId))
                    .set("dept_id", deptId);
            list.add(record);
        }
        //批量执行修改
        Db.batchUpdate("admin_user", "user_id", list, list.size());

        return R.ok("设置成功");
    }
}
