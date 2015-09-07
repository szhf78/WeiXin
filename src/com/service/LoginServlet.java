package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.aes.WXBizMsgCrypt;
import com.message.resp.TextRespMsg;
import com.util.MessageUtil;
import com.util.Parameter;
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//msgAndEvent(request, response);
		
		//接收参数微信加密签名，时间戳，随机数
		String msgSignature=request.getParameter("msg_signature");
		String timeStamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		//从请求中读取整个post数据
		InputStream inputStream=request.getInputStream();
		
		//commons.io.jar 方法
		String postData =IOUtils.toString(inputStream,"UTF-8");
		//Post打印结果
		System.out.println("postData="+postData);
		
		String msg="";
		WXBizMsgCrypt wxcpt=null;
		try {
			wxcpt=new WXBizMsgCrypt(Parameter.token, Parameter.encodingAESKey, Parameter.appId);
			//解密消息
			msg=wxcpt.decryptMsg(msgSignature, timeStamp, nonce, postData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("msg="+msg);
		
		//调用核心业务类接收消息，处理消息
		String respMessage=CoreService.processRequest(msg);
		
		System.out.println("respMessage="+respMessage);
				
		String encryptMsg="";
		try {
			encryptMsg=wxcpt.encryptMsg(respMessage, timeStamp, nonce);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out=response.getWriter();
		out.print(encryptMsg);
		out.close();
	}
	
	
	/**
	 * 普通接收消息和事件
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private void msgAndEvent(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		String respXml = "";// 要响应的XML串

		// 1,获得微信签名的加密字符串
		String signature = request.getParameter("signature");

		// 2,获得时间戳
		String timestamp = request.getParameter("timestamp");

		// 3,获得随机数
		String nonce = request.getParameter("nonce");

		PrintWriter out = response.getWriter();

		// 验证请求确认成功原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
		if (ValidationUtil.checkSignature(signature, timestamp, nonce)) {

			// 接收并解析来自用户的XML数据包中的内容
			Map<String, String> reqMap = null;
			try {
				//reqMap = MessageUtil.parseXml(request);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String ToUserName = reqMap.get("ToUserName");
			String FromUserName = reqMap.get("FromUserName");
			String MsgType = reqMap.get("MsgType");
			String Content = reqMap.get("Content");

			// 开始响应消息给用户

			String respContent = "";// 要响应的文本内容

			// 构建一条文本消息
			TextRespMsg textMsg = new TextRespMsg();
			textMsg.setToUserName(FromUserName);
			textMsg.setFromUserName(ToUserName);
			textMsg.setCreateTime(new Date().getTime());
			textMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "欢迎来到地狱天堂,您发送的文本消息!";
				System.out.println("Content=" + Content);
			} else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "欢迎来到地狱天堂,您发送的是图片消息!";
				String PicUrl = reqMap.get("PicUrl");
				String MediaId = reqMap.get("MediaId");
				System.out.println("PicUrl=" + PicUrl + ",MediaId=" + MediaId);
			} else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "欢迎来到地狱天堂,您发送的是语音消息!";
				String MediaId = reqMap.get("MediaId");
				String Format = reqMap.get("Format");
				System.out.println("MediaId=" + MediaId + ",Format=" + Format);
			} else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)
					|| MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
				respContent = "欢迎来到地狱天堂,您发送的是视频消息!";
				String MediaId = reqMap.get("MediaId");
				String ThumbMediaId = reqMap.get("ThumbMediaId");
				System.out.println("MediaId=" + MediaId + ",ThumbMediaId="
						+ ThumbMediaId);
			} else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "欢迎来到地狱天堂,您发送的是位置消息!";
				String Location_X = reqMap.get("Location_X");
				String Location_Y = reqMap.get("Location_Y");
				String Scale = reqMap.get("Scale");
				String Label = reqMap.get("Label");
				System.out.println("Location_X=" + Location_X + ",Location_Y="
						+ Location_Y + ",Scale=" + Scale + ",Label=" + Label);
			}else if(MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
				respContent = "欢迎来到地狱天堂,您发送的是链接消息!";
				String Title =reqMap.get("Title");
				String Description =reqMap.get("Description");
				String Url =reqMap.get("Url");
				System.out.println("Title=" + Title + ",Description="
						+ Description+",Url="+Url);
			}
			// 事件推送
			else if (MsgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = reqMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！回复?可获得功能帮助列表";
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					//  取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
					System.out.println("取消关注!");
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
				}
			}

			textMsg.setContent(respContent);

			respXml = MessageUtil.textMessageToXml(textMsg);
			
			System.out.println("respXml=" + respXml);
			out.print(respXml);
		}

		out.close();
		out = null;
	}

}
