package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.resp.TextMessage;
import com.util.MessageUtil;
import com.util.ValidationUtil;

/**
 * 微信请求验证
 * 
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get请求。。。");

		// 1,获得微信签名的加密字符串
		String signature = request.getParameter("signature");

		// 2,获得时间戳
		String timestamp = request.getParameter("timestamp");

		// 3,获得随机数
		String nonce = request.getParameter("nonce");

		// 4,获得随机字符串
		String echostr = request.getParameter("echostr");

		System.out.println("获得微信签名的加密字符串=" + signature);
		System.out.println("获得时间戳=" + timestamp);
		System.out.println("获得随机数=" + nonce);
		System.out.println("获得随机字符串=" + echostr);

		PrintWriter out = response.getWriter();

		// 验证请求确认成功原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
		if (ValidationUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}

		out.close();

	}

	/**
	 * 接收微信服务器发过来的XML数据包（通过post请求发送过来的）
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String respXml="";//要响应的XML串
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 1,获得微信签名的加密字符串
		String signature = request.getParameter("signature");

		// 2,获得时间戳
		String timestamp = request.getParameter("timestamp");

		// 3,获得随机数
		String nonce = request.getParameter("nonce");
		
		PrintWriter out = response.getWriter();

		// 验证请求确认成功原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
		if (ValidationUtil.checkSignature(signature, timestamp, nonce)) {
			
			//接收并解析来自用户的XML数据包中的内容
			Map<String, String> reqMap = null;
			try {
				reqMap = MessageUtil.parseXml(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String ToUserName=reqMap.get("ToUserName");
			String FromUserName=reqMap.get("FromUserName");
			String MsgType=reqMap.get("MsgType");
			String Content=reqMap.get("Content");
			
			//开始响应消息给用户
			
			String respContent="";//要响应的文本内容
			
			//构建一条文本消息
			TextMessage textMsg=new TextMessage();
			textMsg.setToUserName(FromUserName);
			textMsg.setFromUserName(ToUserName);
			textMsg.setCreateTime(new Date().getTime());
			textMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			if(MsgType.equals(MessageUtil.RESP_MESSAGE_TYPE_TEXT)){
				respContent="欢迎来到地狱天堂";
			}
			textMsg.setContent(respContent);
			
			respXml =MessageUtil.textMessageToXml(textMsg);
			System.out.println("respXml="+respXml);
			out.print(respXml);
		}
		
		out.close();
		//out=null;
	
	}

}
