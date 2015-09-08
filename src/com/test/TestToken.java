package com.test;

import net.sf.json.JSONObject;

import com.util.Parameter;
import com.util.WeixinUtil;
import com.vo.Token;

public class TestToken {
	
	public static void main(String[] args) {
		//应用ID
		String appId=Parameter.appId;
		//应用密钥 
		String appSecret=Parameter.appSecret;
		Token token=WeixinUtil.getToken(appId, appSecret);
		
		System.out.println(token.getAccessToken());
		System.out.println(token.getExpiresIn());
		
		//获取微信服务器IP地址集合
		String ipUrl="https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
		ipUrl=ipUrl.replace("ACCESS_TOKEN", token.getAccessToken());
		JSONObject jsonObject=WeixinUtil.httpsRequest(ipUrl, "GET", null);
		System.out.println("jsonObject="+jsonObject);
	}
}
