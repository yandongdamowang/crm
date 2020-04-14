package com.linksame.crm.erp.admin.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.linksame.crm.common.minio.service.MinioServicce;
import com.linksame.crm.erp.admin.entity.AdminFile;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ivan
 * @date 2020/4/14 14:42
 * @Description 附件服务----整合Minio服务
 */
@SuppressWarnings("all")
public class AdminFileService {

    /**
     * 上传附件
     *
     * @param file    文件
     * @param batchId 批次ID
     */
    @Before(Tx.class)
    public R upload(UploadFile file, String batchId) {
        if (batchId == null || "".equals(batchId)) {
            batchId = IdUtil.simpleUUID();
        }
        try{
            //上传文件至minio服务器
            Map<String, String> map = MinioServicce.uploadFile(file);
            String bucketName = map.get("bucketName");
            String newfileName = map.get("newfileName");
            String oldfileName = map.get("oldfileName");
            //插入数据到文件表中
            AdminFile adminFile = new AdminFile();
            adminFile.setBucketName(bucketName);
            adminFile.setOldName(oldfileName);
            adminFile.setBatchId(batchId);
            adminFile.setCreateTime(new Date());
            adminFile.setCreateUserId(BaseUtil.getUser().getUserId());
            //如果是图片类型,通过文件名请求minio,获取访问路径
            String suffix = FileUtil.extName(newfileName);
            if(suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("png")
                    || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")) {
                String path = MinioServicce.getImgUrl(newfileName, suffix);
                adminFile.setPath(path);
                adminFile.setFileType("img");
            }
            adminFile.setFileName(newfileName);
            adminFile.setSize(file.getFile().length());
            return adminFile.save() ? R.ok().put("batchId", batchId).put("fileName",adminFile.getFileName()).put("oldName",adminFile.getOldName()).put("path", adminFile.getPath()).put("size",file.getFile().length()/1000+"KB").put("fileId",adminFile.getFileId()) : R.error();
        } catch (RuntimeException e) {
            throw new RuntimeException("上传文件至Minio服务器出现异常");
        }
    }

    /**
     * 通过批次ID查询(暂未使用)
     *
     * @param batchId 批次ID
     */
    public void queryByBatchId(String batchId,Record record) {
        if (batchId == null||"".equals(batchId)) {
            record.set("img",new ArrayList<>()).set("file",new ArrayList<>());
            return;
        }
        List<AdminFile> adminFiles = AdminFile.dao.find(Db.getSql("admin.file.queryByBatchId"), batchId);

        Map<String, List<AdminFile>> collect = adminFiles.stream().collect(Collectors.groupingBy(AdminFile::getFileType));
        collect.forEach(record::set);
        if(!record.getColumns().containsKey("img")||record.get("img") == null){
            record.set("img",new ArrayList<>());
        }
        if(!record.getColumns().containsKey("file")||record.get("file") == null){
            record.set("file",new ArrayList<>());
        }

    }

    /**
     * 通过批次ID查询
     *
     * @param batchId   批次ID
     * @return
     */
    public List<AdminFile> queryByBatchId(String batchId) {
        if (batchId == null) {
            return new ArrayList<>();
        }
        return AdminFile.dao.find(Db.getSql("admin.file.queryByBatchId"), batchId);
    }

    /**
     * 通过ID查询
     *
     * @param id 文件ID
     */
    public R queryById(String id) {
        if (id == null) {
            return R.error("id参数为空");
        }
        return R.ok().put("data", AdminFile.dao.findById(id));
    }

    /**
     * 通过ID删除
     *
     * @param id 文件ID
     */
    @Before(Tx.class)
    public R removeById(String id) throws Exception {
        if (id == null) {
            return R.error("id参数为空");
        }
        AdminFile adminFile = AdminFile.dao.findById(id);
        if(adminFile!=null){
            //数据库文件删除
            adminFile.delete();
            //minio服务器文件删除
            MinioServicce.removeFile(adminFile.getFileName());
        }
        return R.ok();
    }

    /**
     * 通过批次ID删除
     *
     * @param batchId 批次ID
     */
    @Before(Tx.class)
    public void removeByBatchId(String batchId) {
        if(StrUtil.isEmpty(batchId)){
            return;
        }
        //查询该批次的所有文件的文件名称
        List<String> fileNames = Db.query(Db.getSql("admin.file.queryPathByBatchId"), batchId);
        //数据库根据批次ID删除文件数据
        Db.deleteById("admin_file","batch_id",batchId);
        //Minio服务器根据文件名称删除文件数据
        fileNames.forEach(ivan->{
            try {
                MinioServicce.removeFile(ivan);
            } catch (Exception e) {
                throw new RuntimeException("通过批次ID删除文件出现异常");
            }
        });
    }

    /**
     * 重命名文件
     */
    public boolean renameFileById(AdminFile file){
        return Db.update("admin_file","file_id",file.toRecord());
    }
}
