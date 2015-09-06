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
 * ΢��������֤
 * 
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get���󡣡���");

		// 1,���΢��ǩ���ļ����ַ���
		String signature = request.getParameter("signature");

		// 2,���ʱ���
		String timestamp = request.getParameter("timestamp");

		// 3,��������
		String nonce = request.getParameter("nonce");

		// 4,�������ַ���
		String echostr = request.getParameter("echostr");

		System.out.println("���΢��ǩ���ļ����ַ���=" + signature);
		System.out.println("���ʱ���=" + timestamp);
		System.out.println("��������=" + nonce);
		System.out.println("�������ַ���=" + echostr);

		PrintWriter out = response.getWriter();

		// ��֤����ȷ�ϳɹ�ԭ������echostr�������ݣ��������Ч����Ϊ�����߳ɹ����������ʧ��
		if (ValidationUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}

		out.close();

	}

	/**
	 * ����΢�ŷ�������������XML���ݰ���ͨ��post�����͹����ģ�
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String respXml="";//Ҫ��Ӧ��XML��
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 1,���΢��ǩ���ļ����ַ���
		String signature = request.getParameter("signature");

		// 2,���ʱ���
		String timestamp = request.getParameter("timestamp");

		// 3,��������
		String nonce = request.getParameter("nonce");
		
		PrintWriter out = response.getWriter();

		// ��֤����ȷ�ϳɹ�ԭ������echostr�������ݣ��������Ч����Ϊ�����߳ɹ����������ʧ��
		if (ValidationUtil.checkSignature(signature, timestamp, nonce)) {
			
			//���ղ����������û���XML���ݰ��е�����
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
			
			//��ʼ��Ӧ��Ϣ���û�
			
			String respContent="";//Ҫ��Ӧ���ı�����
			
			//����һ���ı���Ϣ
			TextMessage textMsg=new TextMessage();
			textMsg.setToUserName(FromUserName);
			textMsg.setFromUserName(ToUserName);
			textMsg.setCreateTime(new Date().getTime());
			textMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			if(MsgType.equals(MessageUtil.RESP_MESSAGE_TYPE_TEXT)){
				respContent="��ӭ������������";
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
