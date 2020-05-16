package com.linksame.crm.erp.oa.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.erp.work.entity.TaskComment;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OaCommentService{

    public R setComment(TaskComment comment) {
        boolean bol;
        if ( comment.getCommentId () == null ){
            comment.setCreateTime(new Date());
            comment.setUserId(BaseUtil.getUser().getUserId());
            bol = comment.save();
        }else {
            bol = comment.update ();
        }

        return bol ? R.ok().put("data",comment) : R.error();
    }

    @Before(Tx.class)
    public R deleteComment(Integer commentId){
        Db.delete("delete from `task_comment` where main_id = ?",commentId);
        return new TaskComment ().dao ().deleteById ( commentId )?R.ok ():R.error ();
    }

    public List<Record> queryCommentList(String typeId, String type) {
        List<Record> recordList = Db.find("select a.comment_id,a.content,a.user_id,a.create_time,a.type_id,a.type,a.favour,a.pid,a.main_id from task_comment a  where a.type_id = ? and a.type = ?", typeId, type);
        if(recordList == null || recordList.size() == 0){
            return new ArrayList<>();
        }
        recordList.forEach(record -> {
            if ( record.getStr ( "user_id" ) != null && !"".equals ( record.getStr ( "user_id" ) )){
                record.set("user",Db.findFirst ( "select  user_id,realname,img from admin_user where user_id = ?" ,record.getStr ( "user_id" )));
            }
            if ( record.getStr ( "pid" ) != null && !"0".equals ( record.getStr ( "pid" ) ) && !"".equals ( record.getStr ( "pid" ) )){
                record.set("replyUser",Db.findFirst ( "select user_id,realname  from admin_user where user_id = ?" ,record.getStr ( "pid" )));
            }
        });
        Map<Integer,List<Record>> pMap = recordList.stream().collect(Collectors.groupingBy(record -> record.getInt("main_id")));
        recordList = pMap.get(0);
        recordList.forEach(record -> {
            Integer commentId = record.getInt("comment_id");
            if(pMap.get(commentId)!= null){
                record.set("childCommentList",pMap.get(commentId));
            }else {
                record.set("childCommentList",new ArrayList<>());
            }
        });
        return recordList;
    }

    @Before(Tx.class)
    public R deleteComment(Integer type,Integer typeId){
        Db.delete("delete from `task_comment` where type = ? and type_id = ?",type,typeId);
        return R.ok ();
    }
}
