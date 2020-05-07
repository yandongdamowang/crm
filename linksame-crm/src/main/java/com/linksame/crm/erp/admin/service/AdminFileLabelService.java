package com.linksame.crm.erp.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminFileLabel;
import com.linksame.crm.erp.admin.entity.AdminFileType;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;

import java.util.Date;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/5/4 13:54
 * @Description 附件标签业务层
 */
public class AdminFileLabelService {

    /**
     * 附件标签列表
     * @param basePageRequest   分页对象
     * @return
     */
    public R queryList(BasePageRequest<AdminFileLabel> basePageRequest){
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv = Kv.by("name", jsonObject.getString("name"))
                .set("isCommon", jsonObject.getString("isCommon"));
        //如果有ifUser参数且等于0, 则根据当前用户查询, 否则查询全部
        if(jsonObject.getInteger("ifUser") != null){
            if(jsonObject.getInteger("ifUser") == 0){
                kv.set("createUserId", BaseUtil.getUser().getUserId());
            }
        }
        if (basePageRequest.getPageType() == 0) {
            List<Record> records = Db.find(Db.getSqlPara("admin.label.queryList", kv));
            return R.ok().put("data", records);
        } else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("admin.label.queryList", kv));
            return R.ok().put("data", paginate);
        }
    }

    /**
     * 设置附件标签
     * @param adminFileLabel 文件标签对象
     * @return
     */
    public R setLabel(AdminFileLabel adminFileLabel){
        boolean bol = true;
        if(adminFileLabel.getFileLabelId() == null){
            if(StringUtils.isEmpty(adminFileLabel.getName())){
                return R.error("name参数不允许为空");
            }
            Integer count = Db.queryInt("select count(*) from admin_file_label where name = ?", adminFileLabel.getName().trim());
            if(count > 0){
                return R.error("已存在该名称的标签, 不允许重复添加");
            }
            adminFileLabel.setCreateTime(new Date());
            adminFileLabel.setCreateUserId(BaseUtil.getUser().getUserId());
            bol = adminFileLabel.save();
        } else {
            bol = adminFileLabel.update();
        }
        return R.ok(bol ? R.ok() : R.error());
    }

    /**
     * 删除标签
     * @param labelId   附件标签ID
     * @return
     */
    public R delLabel(Integer labelId){
        Integer count = Db.queryInt("select count(*) from admin_file where label_id = ?", labelId);
        if(count > 0){
            return R.error("该标签有关联中的附件, 不允许删除");
        }
        return AdminFileLabel.dao.deleteById(labelId) ? R.ok() : R.error();
    }

    /**
     * 启用标签
     * @param labelId   附件标签ID
     * @return
     */
    public R enableLabel(Integer labelId){
        AdminFileLabel adminFileLabel = AdminFileLabel.dao.findById(labelId);
        adminFileLabel.setStatus(0);

        return adminFileLabel.update() ? R.ok() : R.error();
    }

    /**
     * 禁用标签
     * @param labelId   附件标签ID
     * @return
     */
    public R disableLabel(Integer labelId){
        Integer count = Db.queryInt("select count(*) from admin_file where label_id = ?", labelId);
        if(count > 0){
            return R.error("该标签有关联中的附件, 不允许禁用");
        }
        AdminFileLabel adminFileLabel = AdminFileLabel.dao.findById(labelId);
        adminFileLabel.setStatus(1);

        return adminFileLabel.update() ? R.ok() : R.error();
    }

}
