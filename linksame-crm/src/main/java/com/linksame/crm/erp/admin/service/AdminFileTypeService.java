package com.linksame.crm.erp.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.annotation.NotBlank;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminFileType;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/20 10:54
 * @Description 附件类型业务层
 */
public class AdminFileTypeService {

    /**
     * 附件类型列表
     * @param basePageRequest   分页对象
     * @return
     */
    public R queryList(BasePageRequest<AdminFileType> basePageRequest){
        JSONObject jsonObject = basePageRequest.getJsonObject();
        Kv kv = Kv.by("typeName", jsonObject.getString("typeName"))
                .set("typeCode", jsonObject.getString("typeCode"));
        if (basePageRequest.getPageType() == 0) {
            List<Record> records = Db.find(Db.getSqlPara("admin.type.queryList", kv));
            return R.ok().put("data", records);
        } else {
            Page<Record> paginate = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("admin.type.queryList", kv));
            return R.ok().put("data", paginate);
        }
    }

    /**
     * 设置文件类型
     * @param adminFileType 文件类型对象
     * @return
     */
    @NotBlank({ "typeName", "typeCode" })
    public R setType(AdminFileType adminFileType){
        boolean bol = true;
        if(adminFileType.getTypeId() == null){
            adminFileType.setCreateTime(new Date());
            adminFileType.setCreateUserId(BaseUtil.getUser().getUserId());
            bol = adminFileType.save();
        } else {
            adminFileType.setUpdateTime(new Date());
            bol = adminFileType.update();
        }
        return R.ok(bol ? R.ok() : R.error());
    }


}
