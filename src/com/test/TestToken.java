package com.test;

import net.sf.json.JSONObject;

import com.util.WeixinUtil;
import com.vo.Token;

public class TestToken {
	
	public static void main(String[] args) {
		//应用ID
		String appId="wx0a6e11ce9b1a3417";
		//应用密钥 
		String appSecret="c654fc6285e19f821cf4539d0ac6ba24";
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
