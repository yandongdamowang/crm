package com.linksame.crm.erp.admin.service;

import cn.hutool.core.collection.CollectionUtil;
import com.jfinal.aop.Before;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.annotation.NotBlank;
import com.linksame.crm.erp.admin.entity.AdminFileFolder;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/17 12:43
 * @Description 附件文件夹业务层
 */
public class AdminFileFolderService {

    /**
     * 创建文件夹
     * @param adminFileFolder   文件对象
     * @return
     */
    @NotBlank({ "folderName" })
    public R createFolder(AdminFileFolder adminFileFolder){
        adminFileFolder.setCreateUserId(BaseUtil.getUser().getUserId());
        adminFileFolder.setCreateTime(new Date());
        return adminFileFolder.save() ? R.ok() : R.error();
    }

    /**
     * 查询文件夹(层级展示)
     * @param ifUser    是否根据当前用户查询文件目录树(0=是, 不传或非0=否)
     * @return          文件夹列表
     */
    public R queryFolder(Integer ifUser){
        Kv kv = Kv.by("ifUser", ifUser);
        if(ifUser != null){
            if(ifUser == 0){
                kv.set("createUserId", BaseUtil.getUser().getUserId());
            }
        }
        List<Record> folderList = Db.find(Db.getSqlPara("admin.folder.queryFolderList", kv));
        //有父元素的数据需要从原list中抹除
        Iterator<Record> it = folderList.iterator();
        while(it.hasNext()){
            Record x = it.next();
            if(x.getInt("folder_pid") != 0 && x.getInt("folder_pid") != null){
                it.remove();
            }
        }
        recData(folderList);

        return R.ok().put("data", folderList);
    }
    //递归
    private void recData(List<Record> itemList){
        //遍历出父id等于参数的id，add进子节点集合
        for (Record mu : itemList) {
            Kv kv = Kv.by("folderId", mu.getInt("folder_id"));
            List<Record> childList = Db.find(Db.getSqlPara("admin.folder.queryFolderByPid", kv));
            if(CollectionUtil.isNotEmpty(childList)){
                mu.set("list", childList);
                recData(childList);
            }
        }
    }

    /**
     * 删除文件夹
     * @param folderId  文件夹ID
     */
    @Before(Tx.class)
    public void deleteFolder(Integer folderId){
        AdminFileFolder.dao.deleteById(folderId);
        recDeleteData(folderId);
    }

    //递归删除子文件夹
    private void recDeleteData(Integer folderId){
        List<Record> childList = Db.find(Db.getSqlPara("admin.folder.queryFolderByPid", Kv.by("folderId", folderId)));
        for (Record child : childList) {
            Integer childFolderId = child.getInt("folder_id");
            Integer childFolderPid = child.getInt("folder_pid");
            Db.delete("delete from admin_file_folder where folder_id = ?", childFolderId);
            if(childFolderPid != 0){
                recDeleteData(childFolderId);
            }
        }
    }

}
