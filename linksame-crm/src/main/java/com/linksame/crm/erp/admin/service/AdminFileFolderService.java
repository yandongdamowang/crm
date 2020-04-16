package com.linksame.crm.erp.admin.service;

import cn.hutool.core.collection.CollectionUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.erp.admin.entity.AdminFileFolder;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;

import java.util.Date;
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
    public R createFolder(AdminFileFolder adminFileFolder){
        adminFileFolder.setCreateUserId(BaseUtil.getUser().getUserId());
        adminFileFolder.setCreateTime(new Date());
        return adminFileFolder.save() ? R.ok() : R.error();
    }

    /**
     * 查询文件夹(层级展示)
     * @param batchId   批次ID
     * @return          文件夹列表
     */
    public R queryFolder(String batchId){
        List<Record> folders = Db.find("select * from admin_file_folder where batch_id = ?", batchId);
        folders.forEach(ivan->{
            ivan = assData(ivan);
        });

        return R.ok().put("data", folders);
    }

    //处理子任务
    private Record assData(Record record){
        List<Record> itemList = Db.find("select * from admin_file_folder where folder_pid = ?", record.getInt("folder_id"));
        if(CollectionUtil.isNotEmpty(itemList)){
            recData(itemList);
        }
        record.set("list", itemList);

        return record;
    }

    //递归
    private void recData(List<Record> itemList){
        for (Record mu : itemList) {
            List<Record> childList = Db.find("select * from admin_file_folder where folder_pid = ?", mu.getInt("folder_id"));
            //遍历出父id等于参数的id，add进子节点集合
            if(CollectionUtil.isNotEmpty(childList)){
                mu.set("list", childList);
                recData(childList);
            }
        }
    }

}
