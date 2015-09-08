package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vo.Token;

/**
 * 定义线程定时获取微信access_token
 * @author Franco.Han
 * @date 2015-9-8
 * @version
 */
public class TokenThread implements Runnable {
	
	private static Logger log=LoggerFactory.getLogger(TokenThread.class);
	
	//第三方用户唯一凭证
	public static String appId=Parameter.appId;
	
	public static String appSecret=Parameter.appSecret;
	
	public static Token accessToken=null;
	
	@Override
	public void run() {
		while(true){
			try {
				accessToken=WeixinUtil.getToken(Parameter.appId, Parameter.appSecret);
				if(null!=accessToken){
					log.info("获取access_token成功，有效时长{}秒 token:{}",accessToken.getExpiresIn(),accessToken.getAccessToken());
					//休眠7000秒
					Thread.sleep((accessToken.getExpiresIn()-200)*1000);
				}else{
					//如果access_token为null,60秒后再获取
					Thread.sleep(60*1000);
				}
			} catch (Exception e) {
				try {
					Thread.sleep(60*1000);
				} catch (InterruptedException e1) {
					log.error("{}",e1);
				}
				log.error("{}",e);
			}
		}

	}

}
