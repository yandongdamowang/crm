package com.linksame.crm.erp.admin.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
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
     * 附件分页列表
     * @param basePageRequest   分页对象
     * @param batchId           批次ID
     * @param oldName           附件原始名称
     * @return                  分页列表
     */
    public R getFilePage(BasePageRequest basePageRequest, String batchId, String oldName){
        Page<Record> recordList = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("admin.file.queryPageList", Kv.by("createUserId", BaseUtil.getUser().getUserId()).set("batchId", batchId).set("oldName", oldName)));
        return R.ok().put("data", recordList);
    }

    /**
     * 上传附件
     * @param file    文件
     * @param batchId 批次ID
     */
    @Before(Tx.class)
    public R upload(UploadFile file, String batchId) {
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
     * @param batchId 批次ID
     */
    public void queryByBatchId(String batchId,Record record) {
        if (batchId == null || "".equals(batchId)) {
            record.set("img",new ArrayList<>()).set("file",new ArrayList<>());
            return;
        }
        List<AdminFile> adminFiles = AdminFile.dao.find(Db.getSql("admin.file.queryByBatchId"), batchId);
        Map<String, List<AdminFile>> collect = adminFiles.stream().collect(Collectors.groupingBy(AdminFile::getFileType));
        collect.forEach(record::set);
        if(!record.getColumns().containsKey("img") || record.get("img") == null){
            record.set("img",new ArrayList<>());
        }
        if(!record.getColumns().containsKey("file") || record.get("file") == null){
            record.set("file",new ArrayList<>());
        }
    }

    /**
     * 通过批次ID查询
     * @param batchId   批次ID
     * @return  附件集合
     */
    public R queryByBatchId(String batchId) {
        if (batchId == null) {
            return R.error("batchId参数为空");
        }
        return R.ok().put("data",AdminFile.dao.find(Db.getSql("admin.file.queryByBatchId"), batchId));
    }

    /**
     * 通过ID查询
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
     * @param id 文件ID
     */
    @Before(Tx.class)
    public R removeById(String id) throws Exception {
        if (id == null) {
            return R.error("id参数为空");
        }
        AdminFile adminFile = AdminFile.dao.findById(id);
        if(adminFile!=null){
            //minio服务器文件删除
            MinioServicce.removeFile(adminFile.getFileName());
            //数据库文件删除
            adminFile.delete();
        }
        return R.ok();
    }

    /**
     * 通过批次ID删除
     * @param batchId 批次ID
     */
    @Before(Tx.class)
    public R removeByBatchId(String batchId) {
        if(StrUtil.isEmpty(batchId)){
            return R.error("batchId参数为空");
        }
        //查询该批次的所有文件的文件名称
        List<String> fileNames = Db.query(Db.getSql("admin.file.queryPathByBatchId"), batchId);
        //数据库根据批次ID删除文件数据
        Db.deleteById("admin_file", "batch_id", batchId);
        //Minio服务器根据文件名称删除文件数据
        fileNames.forEach(ivan->{
            try {
                MinioServicce.removeFile(ivan);
            } catch (Exception e) {
                throw new RuntimeException("通过批次ID删除文件出现异常");
            }
        });
        return R.ok();
    }

    /**
     * 重命名文件
     */
    public R renameFileById(AdminFile file){
        return Db.update("admin_file","file_id",file.toRecord()) ? R.ok() : R.error();
    }

    /**
     * 通过用户编号查询附件(查询用户网盘附件)
     * @param createUserId  创建用户ID
     * @return  附件集合
     */
    public R queryByUserId(Integer createUserId){
        return R.ok().put("data", AdminFile.dao.find("select * from admin_file where create_user_id = ?", createUserId));
    }

    /**
     * 网盘上传
     * @param fileIds   附件ID数组字符串
     * @param batchId   批次ID
     */
    public R netdiskUpload(String fileIds, String batchId){
        if(StringUtils.isEmpty(fileIds)){
            return R.error("fileIds参数为空");
        }
        if(StringUtils.isEmpty(batchId)){
            return R.error("batchId参数为空");
        }
        //拆解
        String[] fileIdArr = fileIds.split(",");
        for(String id : fileIdArr){
            AdminFile adminFile = AdminFile.dao.findById(id);
            adminFile.remove("file_id");
            adminFile.setCreateTime(new Date());
            adminFile.setBatchId(batchId);
            adminFile.save();
        }
        return R.ok();
    }
}
