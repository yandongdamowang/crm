package com.linksame.crm.erp.oa.controller;

import com.linksame.crm.erp.oa.common.OaEnum;
import com.linksame.crm.erp.oa.service.OaCommentService;
import com.linksame.crm.erp.work.entity.TaskComment;
import com.linksame.crm.utils.AuthUtil;
import com.linksame.crm.utils.R;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;

public class OaCommentController extends Controller{

    @Inject
    private OaCommentService commentService;
    /**
     * @author hmb
     * 添加评论或者修改
     * @param comment 评论对象
     */
    public void setComment(@Para("") TaskComment comment){
        if(comment.getType() == 1){
            boolean oaAuth = AuthUtil.isOaAuth(OaEnum.TASK_TYPE_KEY.getTypes(), comment.getTypeId());
            if(oaAuth){renderJson(R.noAuth());return;}
        }
        renderJson(commentService.setComment(comment));
    }

    /**
     * 删除评论
     */
    public void deleteComment(){
        Integer commentId = getParaToInt ( "commentId" );
        boolean oaAuth = AuthUtil.isOaAuth(OaEnum.COMMENT_TYPE_KEY.getTypes(), commentId);
        if(oaAuth){renderJson(R.noAuth());return;}
        renderJson(commentService.deleteComment (commentId));
    }

    /**
     * @author hmb
     * 查询评论列表
     */
    public void queryCommentList(){
        String typeId = getPara("typeId");
        String type = getPara("type");
        if("1".equals(type)){
            boolean oaAuth = AuthUtil.isOaAuth(OaEnum.TASK_TYPE_KEY.getTypes(), Integer.valueOf(typeId));
            if(oaAuth){renderJson(R.noAuth());return;}
        }
        renderJson(R.ok().put("data",commentService.queryCommentList(typeId,type)));
    }
}
