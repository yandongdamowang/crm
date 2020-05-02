package com.linksame.crm.erp.admin.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.common.minio.service.MinioServicce;
import com.linksame.crm.erp.admin.entity.*;
import com.linksame.crm.erp.work.entity.Work;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;
import java.text.SimpleDateFormat;
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
     * @param basePageRequest  条件构造对象
     */
    public R getFileList(BasePageRequest basePageRequest){
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv= Kv.by("batchId", jsonObject.getString("batchId"))
                .set("oldName", jsonObject.getString("oldName"))
                .set("folderId", jsonObject.getInteger("folderId"))
                .set("workId", jsonObject.getInteger("workId"))
                .set("ifUser", jsonObject.getInteger("ifUser"));
        if(jsonObject.getInteger("ifUser") != null){
            if(jsonObject.getInteger("ifUser") == 0){
                kv.set("createUserId", BaseUtil.getUser().getUserId());
            }
        }
        if(basePageRequest.getPageType() == 0){
            List<Record> recordList = Db.find(Db.getSqlPara("admin.file.queryList", kv));
            return R.ok().put("data", recordList);
        } else {
            Page<Record> pageList = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("admin.file.queryList", kv));
            return R.ok().put("data", pageList);
        }
    }

    /**
     * 系统文件上传
     * @param file
     * @param adminFile
     * @return
     */
    @Before(Tx.class)
    public R sysUpload(UploadFile file, AdminFile adminFile) {
        try{
            //上传文件至minio服务器
            AdminFile resultFile = MinioServicce.uploadFile(file);
            //插入数据到文件表中
            AdminFile newFile = new AdminFile();
            newFile.setBucketName(resultFile.getBucketName());
            newFile.setOldName(resultFile.getOldName());
            newFile.setBatchId(adminFile.getBatchId());
            newFile.setCreateTime(new Date());
            newFile.setCreateUserId(BaseUtil.getUser().getUserId());
            //如果是图片类型,通过文件名请求minio,获取访问路径
            String suffix = FileUtil.extName(resultFile.getFileName());
            adminFile.setSuffix(suffix);
            //根据文件后缀判断文件类型
            if(suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("png")
                    || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")) {
                String path = MinioServicce.getImgUrl(resultFile.getFileName(), suffix);
                adminFile.setPath(path);
                adminFile.setFileType("img");
            } else if(suffix.equalsIgnoreCase("xlsx") || suffix.equalsIgnoreCase("xls")){
                adminFile.setFileType("excel");
            } else if(suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx")){
                adminFile.setFileType("word");
            } else if(suffix.equalsIgnoreCase("pdf") || suffix.equalsIgnoreCase("ppt")){
                adminFile.setFileType("office");
            } else if(suffix.equalsIgnoreCase("xmind")){
                adminFile.setFileType("xmind");
            } else {
                adminFile.setFileType("file");
            }
            newFile.setFileName(resultFile.getFileName());
            newFile.setSize(file.getFile().length());
            boolean bol = newFile.save();
            return R.ok().put("batchId", adminFile.getBatchId()).put("fileName",newFile.getFileName()).put("oldName",newFile.getOldName()).put("path", newFile.getPath()).put("size",file.getFile().length()/1000+"KB").put("fileId",newFile.getFileId());
        } catch (RuntimeException e) {
            throw new RuntimeException("上传文件至Minio服务器出现异常");
        }
    }

    /**
     * 上传附件
     * @param file    文件
     * @param batchId 批次ID
     */
    @Before(Tx.class)
    public R upload(UploadFile file, AdminFile adminFile) {
        String compositionName;
        if(adminFile.getFolderId() == null){
            return R.error("folderId参数为空");
        }
        try{
            //上传文件至minio服务器
            AdminFile resultFile = MinioServicce.uploadFile(file);
            //插入数据到文件表中
            adminFile.setBucketName(resultFile.getBucketName());
            adminFile.setOldName(resultFile.getOldName());
            adminFile.setBatchId(adminFile.getBatchId());
            adminFile.setCreateTime(new Date());
            adminFile.setCreateUserId(BaseUtil.getUser().getUserId());
            //如果是图片类型,通过文件名请求minio,获取访问路径
            String suffix = FileUtil.extName(resultFile.getFileName());
            adminFile.setSuffix(suffix);
            //根据文件后缀判断文件类型
            if(suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("png")
                    || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")) {
                String path = MinioServicce.getImgUrl(resultFile.getFileName(), suffix);
                adminFile.setPath(path);
                adminFile.setFileType("img");
            } else if(suffix.equalsIgnoreCase("xlsx") || suffix.equalsIgnoreCase("xls")){
                adminFile.setFileType("excel");
            } else if(suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx")){
                adminFile.setFileType("word");
            } else if(suffix.equalsIgnoreCase("pdf") || suffix.equalsIgnoreCase("ppt")){
                adminFile.setFileType("office");
            } else if(suffix.equalsIgnoreCase("xmind")){
                adminFile.setFileType("xmind");
            } else {
                adminFile.setFileType("file");
            }
            adminFile.setFileName(resultFile.getFileName());
            adminFile.setSize(file.getFile().length());
            //生成
            compositionName = compositionFileName(adminFile.getFolderId(),adminFile.getWorkId(),adminFile.getTypeId(), 1, suffix);
            adminFile.setCompositionName(compositionName);
            adminFile.remove("file_id");
            boolean bol = adminFile.save();
            if(bol){
                AdminFile generateFile = AdminFile.dao.findById(adminFile.getFileId());
                generateFile.setHistoryFileId(adminFile.getFileId());
                generateFile.update();
            }

            return R.ok().put("batchId", adminFile.getBatchId()).put("fileName",adminFile.getFileName()).put("oldName",adminFile.getOldName()).put("compositionName",adminFile.getCompositionName()).put("path", adminFile.getPath()).put("size",file.getFile().length()/1000+"KB").put("fileId",adminFile.getFileId());
        } catch (RuntimeException e) {
            throw new RuntimeException("上传文件至Minio服务器出现异常");
        }
    }

    /**
     * 批量上传附件
     * @param files         文件集合
     * @param adminFile     文件对象
     * @param workId        项目ID
     * @return
     */
    @Before(Tx.class)
    public R batchUpload(List<UploadFile> files, AdminFile adminFile){
        if(CollectionUtil.isEmpty(files)){
            return R.error("文件数据为空");
        }
        for(UploadFile file : files){
            upload(file, adminFile);
        }
        return R.ok();
    }

    /**
     * 附件更新版本
     * @param file      文件
     * @param adminFile 附件对象
     * @return
     */
    @Before(Tx.class)
    public R changeVersion(UploadFile file, AdminFile adminFile) {
        String compositionName;
        if(adminFile.getFolderId() == null){
            return R.error("folderId参数为空");
        }
        try{
            //上传文件至minio服务器
            AdminFile resultFile = MinioServicce.uploadFile(file);
            //插入数据到文件表中
            adminFile.setBucketName(resultFile.getBucketName());
            adminFile.setOldName(resultFile.getOldName());
            adminFile.setBatchId(adminFile.getBatchId());
            adminFile.setCreateTime(new Date());
            adminFile.setCreateUserId(BaseUtil.getUser().getUserId());
            //如果是图片类型,通过文件名请求minio,获取访问路径
            String suffix = FileUtil.extName(resultFile.getFileName());
            if(suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("png")
                    || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")) {
                String path = MinioServicce.getImgUrl(resultFile.getFileName(), suffix);
                adminFile.setPath(path);
                adminFile.setFileType("img");
            }
            adminFile.setFileName(resultFile.getFileName());
            adminFile.setSize(file.getFile().length());
            //获取附件数据
            AdminFile historyFile = AdminFile.dao.findById(adminFile.getFileId());
            adminFile.setHistoryFileId(historyFile.getHistoryFileId());
            //获取该附件历史版本号
            AdminFile history = AdminFile.dao.findFirst("select * from admin_file where history_file_id = ? order by file_version desc limit 1", historyFile.getHistoryFileId());
            adminFile.setFileVersion(history.getFileVersion()+1);
            adminFile.setIsMainVersion(1);
            if(adminFile.getFolderId() != 0){
                //生成
                compositionName = compositionFileName(adminFile.getFolderId(),adminFile.getWorkId(),adminFile.getTypeId(), history.getFileVersion()+1, suffix);
                adminFile.setCompositionName(compositionName);
            }
            //删除id,执行插入
            adminFile.remove("file_id");
            adminFile.save();
            //更替版本后自动将当前附件作为主版本
            editVersionToMain(adminFile.getFileId());
            //保存日志
            AdminFileLog adminFileLog = new AdminFileLog();
            adminFileLog.setUserId(BaseUtil.getUser().getUserId());
            adminFileLog.setFileId(adminFile.getFileId());
            adminFileLog.setContent("用户 " + BaseUtil.getUser().getRealname() + " 上传了新版本文件: " + adminFile.getCompositionName());
            adminFileLog.setRemake(adminFile.getFileRemark());
            saveFileLog(adminFileLog);

            return R.ok().put("batchId", adminFile.getBatchId()).put("fileName",adminFile.getFileName()).put("oldName",adminFile.getOldName()).put("compositionName",adminFile.getCompositionName()).put("path", adminFile.getPath()).put("size",file.getFile().length()/1000+"KB").put("fileId",adminFile.getFileId());
        } catch (RuntimeException e) {
            throw new RuntimeException("上传文件至Minio服务器出现异常");
        }
    }

    /**
     * 设置主版本附件
     * @param fileId 附件ID
     * @return
     */
    @Before(Tx.class)
    public R editVersionToMain(Integer fileId){
        //查询附件数据
        AdminFile adminFile = AdminFile.dao.findById(fileId);
        //查询历史附件编号为fileId的非当前附件数据列表
        List<Record> historyFileList = Db.find("select * from admin_file where history_file_id = ? and file_id != ?", adminFile.getInt("history_file_id") ,fileId);
        //批量修改版本状态为非主版本
        Db.batch("update admin_file set is_main_version = 1 where file_id = ?","file_id",historyFileList,historyFileList.size());
        //保存日志
        AdminFileLog adminFileLog = new AdminFileLog();
        adminFileLog.setUserId(BaseUtil.getUser().getUserId());
        adminFileLog.setFileId(fileId);
        adminFileLog.setContent("用户 " + BaseUtil.getUser().getRealname() + " 将附件: " + adminFile.getCompositionName() + " 设为主版本");
        saveFileLog(adminFileLog);
        //修改当前附件为主版本
        return Db.update("update admin_file set is_main_version = 0 where file_id = ?", fileId) > 0 ? R.ok() : R.error();
    }

    /**
     * 查询附件详情
     * @param fileId    附件ID
     * @return          附件信息
     */
    public R queryFileInfo(Integer fileId){
        //获取附件信息(关联用户信息查询)
        Record file = Db.findFirst(Db.getSqlPara("admin.file.queryFile", Kv.by("fileId", fileId)));
        //关联业务-任务列表
        List<Record> taskList = Db.find("select * from task where batch_id = ? and ishidden = 0", file.getStr("batch_id"));
        //关联业务-合同列表
        List<Record> contractList = Db.find("select * from pmp_contract where batch_id = ? and is_deleted = 0", file.getStr("batch_id"));
        //查询附件历史数据
        List<Record> historyFileList = Db.find("select * from admin_file where history_file_id = ?", fileId);
        //TODO 待添加查询标签信息
        //从历史附件中排除当前附件
        Iterator<Record> it = historyFileList.iterator();
        while(it.hasNext()){
            Record x = it.next();
            if(x.getInt("file_id") == fileId) it.remove();
        }

        file.set("taskList", taskList);
        file.set("contractList", contractList);
        file.set("historyFileList", historyFileList);

        return R.ok().put("data",file);
    }

    /**
     * 通过批次ID查询
     * @param batchId 批次ID
     */
    public void queryByBatchId(String batchId, Record record) {
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
        return R.ok().put("data", Db.find(Db.getSqlPara("admin.file.queryPageList", Kv.by("batchId", batchId))));
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
     * 通过附件ID将附件放入回收站
     * @param id    附件ID
     * @return
     */
    @Before(Tx.class)
    public R addRecycleById(String id){
        AdminFile adminFile = AdminFile.dao.findById(id);
        if(adminFile == null){
            return R.error("附件不存在");
        }
        adminFile.setDelFlag(1);
        adminFile.setDelUserId(BaseUtil.getUser().getUserId());
        adminFile.setDelTime(new Date());
        adminFile.update();
        //保存日志
        AdminFileLog adminFileLog = new AdminFileLog();
        adminFileLog.setUserId(BaseUtil.getUser().getUserId());
        adminFileLog.setFileId(adminFile.getFileId());
        adminFileLog.setContent("用户 " + BaseUtil.getUser().getRealname() + " 将附件 " + adminFile.getCompositionName() + " 加入回收站, 历史版本为: " + adminFile.getFileVersion());
        saveFileLog(adminFileLog);

        return R.ok("操作成功");
    }

    /**
     * 通过ID删除
     * @param id 文件ID
     */
    @Before(Tx.class)
    public R removeById(Integer id) throws Exception {
        if (id == null) {
            return R.error("id参数为空");
        }
        AdminFile adminFile = AdminFile.dao.findById(id);
        //保存日志
        AdminFileLog adminFileLog = new AdminFileLog();
        adminFileLog.setUserId(BaseUtil.getUser().getUserId());
        adminFileLog.setFileId(adminFile.getFileId());
        adminFileLog.setContent("用户 " + BaseUtil.getUser().getRealname() + " 删除了附件 " + adminFile.getCompositionName() + " 历史版本为: " + adminFile.getFileVersion());
        saveFileLog(adminFileLog);
        if(adminFile != null){
            //minio服务器文件删除
            MinioServicce.removeFile(adminFile.getFileName());
            //数据库文件删除
            adminFile.delete();
        }

        return R.ok("删除成功");
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
        List<String> fileNames = Db.query(Db.getSql("admin.file.queryFileNameByBatchId"), batchId);
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
    @Before(Tx.class)
    public R renameFileById(AdminFile file){
        if(StringUtils.isEmpty(file.getCompositionName())){
            return R.error("文件名不能为空");
        }
        AdminFile adminFile = AdminFile.dao.findById(file.getFileId());
        //保存日志
        AdminFileLog adminFileLog = new AdminFileLog();
        adminFileLog.setUserId(BaseUtil.getUser().getUserId());
        adminFileLog.setFileId(file.getFileId());
        adminFileLog.setContent("用户 " + BaseUtil.getUser().getRealname() + " 将文件 " + adminFile.getCompositionName() + " 重命名为: " + file.getCompositionName());
        saveFileLog(adminFileLog);
        return file.update() ? R.ok() : R.error();
    }

    /**
     * 通过用户编号查询附件(查询用户网盘附件)
     * @param createUserId  创建用户ID
     * @return  附件集合
     */
    public R queryByUserId(){
        Kv kv = Kv.by("createUserId", BaseUtil.getUser().getUserId());
        return R.ok().put("data", AdminFile.dao.find(Db.getSqlPara("admin.file.queryByUserId",kv)));
    }

    /**
     * 网盘上传
     * @param fileIds   文件ID集合
     * @param batchId   批次ID(关联业务: 任务, 合同等等)
     * @param folderId  文件夹ID
     * @param workId    项目ID
     * @return
     */
    @Before(Tx.class)
    public R netdiskUpload(String fileIds, String batchId, Integer folderId, Integer workId){
        String compositionName;
        if(StringUtils.isEmpty(fileIds)){
            return R.error("fileIds参数为空");
        }
        if(folderId == null){
            return R.error("folderId参数为空");
        }
        //拆解
        String[] fileIdArr = fileIds.split(",");
        for(String id : fileIdArr){
            AdminFile adminFile = AdminFile.dao.findById(id);
            adminFile.remove("file_id").remove("is_main_version").remove("file_version").remove("file_remark");
            adminFile.setCreateTime(new Date());
            if(StringUtils.isNotEmpty(batchId)){
                adminFile.setBatchId(batchId);
            }
            if(adminFile.getFolderId() != 0){
                //生成
                compositionName = compositionFileName(folderId,workId,adminFile.getTypeId(), 1, FileUtil.extName(adminFile.getFileName()));
                adminFile.setCompositionName(compositionName);
                adminFile.setFolderId(folderId);
            }
            boolean bol = adminFile.save();
            if(bol){
                AdminFile generateFile = AdminFile.dao.findById(adminFile.getInt("file_id"));
                generateFile.setHistoryFileId(adminFile.getInt("file_id"));
                generateFile.update();
            }
        }
        return R.ok();
    }

    /**
     * 如果有传入文件夹id,说明是从附件管理进入指定文件夹上传的附件,需要按照一定规则生成文件名
     * @param folderId          文件夹ID
     * @param workId            项目ID
     * @param typeId            附件类型code
     * @param fileFolderCode    附件文件夹code
     * @param version           附件版本
     * @param suffix            附件后缀
     * @return                  按规则生成附件名称
     */
    public String compositionFileName(Integer folderId, Integer workId, Integer typeId, Integer version, String suffix){
        StringBuffer sb = new StringBuffer();
        //A.日期，日期采用 YYMMDD 格式，如 2017 年 1 月 8 日为 170108
        String date = (new SimpleDateFormat("YYMMdd")).format(new Date());
        sb.append(date);
        //B.上传人公司编码
        AdminDept adminDept = AdminDept.dao.findById(BaseUtil.getUser().getDeptId());
        String deptCode = adminDept.getCode();
        if(StringUtils.isNotEmpty(deptCode)){
            sb.append("-" + deptCode);
        }
        //C.项目编码
        if(workId != null){
            Work work = Work.dao.findById(workId);
            String workCode = work.getCode();
            if(StringUtils.isNotEmpty(workCode)){
                sb.append("-" + workCode);
            }
        }
        //D.表示文件类型: fileTypeCode
        if(typeId != null){
            AdminFileType adminFileType = AdminFileType.dao.findById(typeId);
            sb.append("-" + adminFileType.getTypeCode());
        }
        //E.表示主题代码: 文件夹编码
        AdminFileFolder adminFileFolder = AdminFileFolder.dao.findById(folderId);
        String fileFolderCode = adminFileFolder.getFolderCode();
        if(StringUtils.isNotEmpty(fileFolderCode)){
            sb.append("-" + fileFolderCode);
        }
        //F.序列号, 三位编码，从 001 开始
        int count = Db.queryInt("select count(1) from admin_file where folder_id = ?", folderId) + 1;
        String countStr = "";
        if(count <= 10){
            countStr = "00" + count;
        } else if(count <= 100){
            countStr = "0" + count;
        } else {
            countStr = String.valueOf(count);
        }
        sb.append("-" + countStr).append("-" + version).append("." + suffix);

        return sb.toString();
    }

    /**
     * 查看附件日志
     * @param fileId    附件ID
     * @return
     */
    public R queryFileLog(BasePageRequest<AdminFileLog> basePageRequest){
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv= Kv.by("fileId", jsonObject.getInteger("fileId"));
        Page<Record> pageList = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("admin.file.queryFileLog", kv));
        return R.ok().put("data", pageList);
    }

    /**
     * 保存日志
     * @param adminFileLog
     */
    public void saveFileLog(AdminFileLog adminFileLog){
        adminFileLog.setCreateTime(DateUtil.date());
        adminFileLog.setLogId(null);
        adminFileLog.save();
    }

}
