#namespace("admin.examineLog")
  #sql("queryNowadayExamineLogByRecordId")
    select * from admin_examine_log where record_id = ? and examine_user = ?
  #end
  #sql("queryExamineLogByRecordId")
    select sael.* ,(SELECT realname from admin_user WHERE user_id = sael.create_user) createUserName,
    (SELECT realname from admin_user WHERE user_id = sael.examine_user) examineUserName
    from admin_examine_log as sael where record_id = ? order by create_time
  #end
  #sql("queryExamineLogByStepId")
    select sael.* ,(SELECT realname from admin_user WHERE user_id = sael.create_user) createUserName,
    (SELECT realname from admin_user WHERE user_id = sael.examine_user) examineUserName
    from admin_examine_log as sael where examine_step_id = ? order by create_time
  #end
  #sql("queryNowadayExamineLogByRecordIdAndStepId")
    select * from admin_examine_log where record_id = ? and examine_step_id = ? and examine_user = ? and is_recheck = 0
  #end
  #sql("queryExamineLogByRecordIdAndStepId")
    select sael.* ,
         (SELECT realname from admin_user WHERE user_id = sael.create_user) createUserName,
         (SELECT realname from admin_user WHERE user_id = sael.examine_user) examineUserName
    from admin_examine_log as sael
    where record_id = ? and examine_step_id = ? order by create_time
  #end
  #sql("queryUserByUserId")
    SELECT DISTINCT saud.user_id, saud.realname , 0 as examine_status from admin_user as sau
    LEFT JOIN admin_user as saud on saud.user_id = sau.parent_id WHERE sau.user_id = ?
  #end
  #sql("queryExamineLogByRecordIdAndStepIdAndStatus")
    select sael.* ,(SELECT realname from admin_user WHERE user_id = sael.create_user) as createUserName,
    (SELECT realname from admin_user WHERE user_id = sael.examine_user) as examineUserName
    from admin_examine_log as sael where record_id = ? and examine_step_id = ?  order by create_time
  #end
  #sql("queryUserByUserIdAndStatus")
    SELECT DISTINCT user_id, realname  , 0 as examine_status , img from admin_user WHERE user_id = ?
  #end
  #sql("queryNowadayExamineLogByRecordIdAndStepIdAndStatus")
    select * from admin_examine_log where record_id = ? and examine_step_id = ? and examine_user = ? and examine_status != 0
  #end
  #sql("queryUserByRecordIdAndStepIdAndStatus")
    select sael.examine_time  as examineTime , sael.examine_status,sau.realname as realname , sau.user_id , sau.img
    from admin_examine_log as sael
    LEFT JOIN admin_user as sau on sau.user_id = sael.examine_user where record_id = ? and examine_step_id = ? and sael.is_recheck != 1
    order by sael.create_time
  #end
  #sql("queryUserByRecordId")
    select sael.examine_time  as examineTime ,
    sael.examine_status,sau.realname ,sau.user_id,sau.img
    from admin_examine_log as sael
    LEFT JOIN admin_user as sau on sau.user_id = sael.examine_user
    where sael.record_id = ? and  sael.examine_status = ? and sael.is_recheck != 1
  #end
  #sql("queryExamineLogAndUserByRecordId")
    select sael.examine_time  as examineTime , sael.examine_status,sau.realname ,sau.img , sael.log_id
    from admin_examine_log as sael
    LEFT JOIN admin_user as sau on sau.user_id = sael.examine_user where sael.record_id = ? and sael.is_recheck != 1
    order by sael.create_time
  #end
  #sql("queryExamineLogAndUserByLogId")
    select sael.examine_time  as examineTime , sael.examine_status,sau.realname ,sau.img , sael.log_id
    from admin_examine_log as sael
    LEFT JOIN admin_user as sau on sau.user_id = sael.examine_user where sael.log_id = ? and sael.is_recheck != 1
    order by sael.create_time
  #end
  #sql("queryExamineLog")
    SELECT * FROM admin_examine_log
    WHERE examine_status = 0 and is_recheck != 1
    and record_id = #para(recordId)
    and  examine_user = #para(auditUserId)
    #if(stepId)
      and examine_step_id = #para(stepId)
    #end
  #end
  #sql("queryRecordAndId")
    select create_user, sau.realname as examineUserName , sau.user_id , sau.img, aser.create_time as examineTime, 5 as examine_status
    from admin_examine_record  as aser
    LEFT JOIN admin_user as sau on sau.user_id = aser.create_user where record_id = ?
  #end
  #sql("queryUserByUserIdAnd")
    SELECT DISTINCT user_id, realname ,img, 0 as examine_status from admin_user WHERE user_id = ?
  #end
  #sql("queryRecordByUserIdAndStatus")
    SELECT DISTINCT user_id, realname  ,img, 5 as examine_status, 5 as stepType, #para(examineTime) as examineTime
    from admin_user WHERE user_id = #para(create_user)
  #end
  #sql("queryExamineLogByRecordIdByStep")
    select sael.order_id as order_id , sau.user_id , sau.realname , sau.img ,sael.examine_status,sael.examine_time,sael.remarks
    from admin_examine_log as sael
    LEFT JOIN admin_user as sau on sau.user_id = sael.examine_user
    where sael.record_id = ? AND sael.examine_status != 0 order by sael.create_time
  #end
  #sql("queryExamineLogByRecordIdByStep1")
    select sael.order_id as order_id, sau.user_id , sau.realname , sau.img,sael.examine_status,sael.examine_time,sael.remarks
    from admin_examine_log as sael
    LEFT JOIN admin_user as sau on sau.user_id = sael.examine_user where sael.record_id = ? AND sael.examine_status != 0
    order by sael.create_time
  #end
  #sql("updateExamineLogIsRecheckByRecordId")
    UPDATE admin_examine_log SET is_recheck = 1 WHERE record_id = ?
  #end
  #sql("queryNowadayExamineLogByRecordIdAndStatus")
    select * from admin_examine_log
    where record_id = ? and examine_status = 0 and examine_user = ? and is_recheck = 0
  #end
  #sql("queryCountByStepId")
    SELECT  DISTINCT ((SELECT COUNT(log_id) FROM admin_examine_log
    WHERE record_id = #para(recordId) and examine_step_id = #para(stepId))- (SELECT COUNT(log_id) FROM admin_examine_log
    WHERE record_id = #para(recordId) and examine_step_id = #para(stepId) and examine_status = 2 )) as toCount FROM admin_examine_log
  #end
    #sql("queryExamineUserByExamineStepId")
    SELECT * FROM admin_examine_log
    WHERE record_id = #para(recordId) and examine_step_id = #para(examineStepId)
    and (is_recheck = 0 or is_recheck is null)
     #if(examineStatus)
      and examine_status =  #para(examineStatus)
    #end
    ORDER BY log_id desc LIMIT 0 , 1
  #end
#end
