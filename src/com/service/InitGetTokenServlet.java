package com.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.Parameter;
import com.util.TokenThread;
import com.util.WeixinUtil;

/**
 * 初始化获取Token的线程
 * @author Franco.Han
 * @date 2015-9-8
 * @version
 */
public class InitGetTokenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger log=LoggerFactory.getLogger(WeixinUtil.class);
	
	public void init() throws ServletException {
		//获取web.xml中配置的参数
		//TokenThread.appId=getInitParameter("appId");
		//TokenThread.appSecret=getInitParameter("appSecret");
		
		TokenThread.appId=Parameter.appId;
		TokenThread.appSecret=Parameter.appSecret;
		
		log.info("微信api appid:{}",TokenThread.appId);
		log.info("微信 api appsecret:{}",TokenThread.appSecret);
		
		//未配置appid,appsecret时给出提示
		if("".equals(TokenThread.appId)||"".equals(TokenThread.appSecret)){
			log.error("appid and appsecret配置错误，请仔细检查!");
		}else {
			//启动定时获取access_token的线程
			new Thread(new TokenThread()).start();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
