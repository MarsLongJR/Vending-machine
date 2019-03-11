package com.xbz.vpase.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendMsg {
    //产品名称
    public static final String product = "Dysmsapi";
    //产品域名
    public static final String domain = "dysmsapi.aliyuncs.com";
    //开发者Ak
    public static final String  ACCESS_KEY_ID = "LTAImw0gE5UuPwjw";
    public static final String ACCESS_KEY_SECRET = "7AlsUhrRIPM4TijP8eUNXG1iaGopuf";

    //短信模板ID
    public static final String TEMPLATE_CODE = "SMS_125029854";

    //短信签名
    public static final String SIGN_NAME = "微金信息";

    //短信验证码长度
    public static final Integer codeLength = 4;
    public static String createCode(){
        Integer min = (int) Math.pow(10,codeLength-1);
        Integer max = (int) Math.pow(10,codeLength)-1;
        Integer randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum.toString();
    }


    public static SendSmsResponse sendMsgCode(String phone, String code, String company, Integer employeeId)throws ClientException {

        String result = "error";

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode(TEMPLATE_CODE);
//        request.setTemplateParam("{\"company\":\"" + company + "\", \"code\":\"" + code + "\"}");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }

    //发送验证码
    public static SendSmsResponse sendCaptcha(String phone,String code,String company,String password)throws ClientException {
        String result = "error";
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        request.setSignName("");
        request.setTemplateCode("");
        request.setTemplateParam("{\"company\":\"" + company + "\", \"code\":\"" + code + "\", \"password\":\"" + password + "\"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
    //发送通知
    public static SendSmsResponse sendStr(String phone,String link,String company,String facilitator)throws ClientException {
        String result = "error";
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        request.setSignName(SIGN_NAME);
        request.setTemplateCode("SMS_130913608");
        request.setTemplateParam("{\"link\":\"" + link + "\", \"company\":\"" + company + "\", \"facilitator\":\"" + facilitator + "\"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
}
