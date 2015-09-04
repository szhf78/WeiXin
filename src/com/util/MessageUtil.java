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
 * 消息处理工具类
 * 
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 * 
 */
public class MessageUtil {

	// 定义了消息类型（）
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 从流中解析出每个节点的内容
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		// 从输入流中获取流对象
		InputStream in = request.getInputStream();

		// 构建SAX阅读器对象
		SAXReader reader = new SAXReader();

		try {
			// 从流中获得文档对象
			Document doc = reader.read(in);
			// 获得根节点
			Element root = doc.getRootElement();
			// 获取根节点下的所有子节点
			List<Element> children = root.elements();

			for (Element e : children) {
				// 循环遍历每一个节点，并按照节点名－节点值放入map中
				map.put(e.getName(), e.getText());
			}
			in.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 用于扩展节点数据按照<ToUserName><![CDATA[toUser]]></ToUserName>,中间加了CDATA
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
	 * 将文本消息转换成XML格式
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(TextMessage textMessage){
		xstream.alias("xml", textMessage.getClass());
		
		return xstream.toXML(textMessage);
	}
}
