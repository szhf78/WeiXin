package com.message.req;

/**
 * 文本消息类
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 *
 */
public class TextMessage extends BaseMessage {
	
	private String Content;//文本消息内容
	private String MsgId;//消息id，64位整型
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

}
