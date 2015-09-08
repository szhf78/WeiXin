package com.test;

import com.util.WeixinUtil;
import com.vo.Token;

public class TestToken {
	
	public static void main(String[] args) {
		String appId="wx0a6e11ce9b1a3417";
		String appSecret="c654fc6285e19f821cf4539d0ac6ba24";
		Token token=WeixinUtil.getToken(appId, appSecret);
		
		System.out.println(token.getAccessToken());
		System.out.println(token.getExpiresIn());
	}
}
