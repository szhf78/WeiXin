package com.message.req;

/**
 * �ı���Ϣ��
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 *
 */
public class TextMessage extends BaseMessage {
	
	private String Content;//�ı���Ϣ����
	private String MsgId;//��Ϣid��64λ����
	
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
