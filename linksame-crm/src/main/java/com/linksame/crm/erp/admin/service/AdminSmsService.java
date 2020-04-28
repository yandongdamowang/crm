package com.linksame.crm.erp.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.kit.Kv;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.common.config.redis.Redis;
import com.linksame.crm.common.config.redis.RedisManager;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.erp.admin.constant.SmsConstant;
import com.linksame.crm.erp.admin.entity.AdminSms;
import com.linksame.crm.utils.HttpUtil;
import com.linksame.crm.utils.R;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/23 10:37
 * @Description 短信服务
 */
public class AdminSmsService {

    /**
     * 查询短信列表
     * @param basePageRequest
     * @return
     */
    public R queryList(BasePageRequest<AdminSms> basePageRequest){
        JSONObject jo = basePageRequest.getJsonObject();
        Kv kv= Kv.by("mobile", jo.getString("mobile"))
                .set("content", jo.getString("content"))
                .set("errorcode", jo.getInteger("errorcode"))
                .set("code", jo.getInteger("code"))
                .set("sendTime", jo.getDate("sendTime"));
        if (basePageRequest.getPageType() == 0) {
            List<Record> recordList = Db.find(Db.getSqlPara("admin.sms.queryList", kv));
            return R.ok().put("data", recordList);
        } else {
            Page<Record> pageList = Db.paginate(basePageRequest.getPage(), basePageRequest.getLimit(), Db.getSqlPara("admin.sms.queryList", kv));
            return R.ok().put("data", pageList);
        }
    }

    /**
     * 发送短信(单条)
     * @param adminSms 短信对象
     * @return
     */
    @Before(Tx.class)
    public R sendSms(AdminSms adminSms) {
        if(StringUtils.isEmpty(adminSms.getMobile())){
            return R.error("参数mobile不能为空");
        }
        if(StringUtils.isEmpty(adminSms.getContent())){
            return R.error("参数content不能为空");
        }
        try{
            //查询短信余额
            Integer smsNum = (Integer) (queryBal().get("data"));
            if(smsNum == 0){
                return R.error("短信余额不足，请联系平台续费！");
            }

            StringBuffer sb = new StringBuffer();
            sb.append("username=" + SmsConstant.SMS_USERNAME)
                    .append("&userpwd=" + SmsConstant.SMS_PASSWORD)
                    .append("&mobiles=" + adminSms.getMobile())
                    .append("&content=" + URLEncoder.encode(adminSms.getContent() + SmsConstant.SMS_SIGN, "UTF-8"));

            //发送请求
            String resultXml = HttpUtil.sendPost(SmsConstant.SEND_URL, sb.toString());
            adminSms = parseXml(resultXml, adminSms);
            if(adminSms.getErrorcode() == 1){
                adminSms.setSendTime(new Date());
                adminSms.save();
            }
        } catch (Exception e) {
            Log.getLog(getClass()).error("发送短信异常: ", e);
        }

        return R.ok().put("data", adminSms);
    }

    /**
     * 发送登录验证码
     * @param mobile    手机号
     * @param seconds   有效时长(单位: 秒)
     * @return
     */
    @Before(Tx.class)
    public R sendCaptcha(String mobile, Integer seconds){
        Redis redis= RedisManager.getRedis();
        String key = BaseConstant.PHONE_LOGIN_KEY + mobile;
        //默认有效时长60秒
        Integer effTime = 60;
        if (redis.exists(key)){
            return R.error("验证码仍在有效期");
        }
        if(StringUtils.isEmpty(mobile)){
            return R.error("参数mobile不能为空");
        }
        if(seconds != null){
            effTime = seconds;
        }
        String captcha = "";

        try{
            //查询短信余额
            Integer smsNum = (Integer) (queryBal().get("data"));
            if(smsNum == 0){
                return R.error("短信余额不足，请联系平台续费！");
            }

            StringBuffer sb = new StringBuffer();
            //生成六位随机数
            captcha = String.valueOf((int)((Math.random() * 9 + 1 ) * 100000));
            String content = "您的登录验证码为: " + captcha + "，有效时长" + effTime/60 + "分钟，请尽快完成登录，如非本人操作请忽略此短信！";
            sb.append("username=" + SmsConstant.SMS_USERNAME)
                    .append("&userpwd=" + SmsConstant.SMS_PASSWORD)
                    .append("&mobiles=" + mobile)
                    .append("&content=" + URLEncoder.encode(SmsConstant.SMS_SIGN + content, "UTF-8"));

            //发送请求
            String resultXml = HttpUtil.sendPost(SmsConstant.SEND_URL, sb.toString());
            AdminSms adminSms = new AdminSms();
            adminSms.setMobile(mobile);
            adminSms.setContent(content);
            adminSms = parseXml(resultXml, adminSms);
            if(adminSms.getErrorcode() == 1){
                //存储redis缓存----根据前端传递的有效时长缓存验证码
                redis.setex(key, effTime-1, captcha);
                //存储短信数据
                adminSms.setSendTime(new Date());
                adminSms.save();
            }
        } catch (Exception e) {
            Log.getLog(getClass()).error("发送短信验证码异常: ", e);
        }

        return R.ok().put("data", captcha);
    }

    /**
     * 查询余额
     * @return
     */
    public R queryBal(){
        StringBuffer sb = new StringBuffer();
        sb.append("username=" + SmsConstant.SMS_USERNAME)
                .append("&userpwd=" + SmsConstant.SMS_PASSWORD);
        String result = HttpUtil.sendPost(SmsConstant.QUERY_BAL, sb.toString());

        return R.ok().put("data", Integer.parseInt(result));
    }

    //短信返回码解析
    private static AdminSms parseXml(String xml, AdminSms adminSms) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource((new StringReader(xml))));
            //解析返回码
            NodeList errorcodeNode = doc.getElementsByTagName("errorcode");
            String errorcode = errorcodeNode.item(0).getFirstChild().getNodeValue();
            //解析返回信息
            NodeList messageNode = doc.getElementsByTagName("message");
            String message = messageNode.item(0).getFirstChild().getNodeValue();
            //解析短信ID
            NodeList smsIdNode = doc.getElementsByTagName("SMSID");
            String smsId = smsIdNode.item(0).getFirstChild().getNodeValue();
            adminSms.setErrorcode(Integer.parseInt(errorcode));
            adminSms.setMessage(message);
            adminSms.setCode(Integer.parseInt(smsId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminSms;
    }

}
