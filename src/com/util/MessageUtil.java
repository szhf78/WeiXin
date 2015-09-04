package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.message.req.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * ��Ϣ��������
 * 
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 * 
 */
public class MessageUtil {

	// ��������Ϣ���ͣ���
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * �����н�����ÿ���ڵ������
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		// ���������л�ȡ������
		InputStream in = request.getInputStream();

		// ����SAX�Ķ�������
		SAXReader reader = new SAXReader();

		try {
			// �����л���ĵ�����
			Document doc = reader.read(in);
			// ��ø��ڵ�
			Element root = doc.getRootElement();
			// ��ȡ���ڵ��µ������ӽڵ�
			List<Element> children = root.elements();

			for (Element e : children) {
				// ѭ������ÿһ���ڵ㣬�����սڵ������ڵ�ֵ����map��
				map.put(e.getName(), e.getText());
			}
			in.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * ������չ�ڵ����ݰ���<ToUserName><![CDATA[toUser]]></ToUserName>,�м����CDATA
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = true;

				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	/**
	 * ���ı���Ϣת����XML��ʽ
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(TextMessage textMessage){
		xstream.alias("xml", textMessage.getClass());
		
		return xstream.toXML(textMessage);
	}
}
