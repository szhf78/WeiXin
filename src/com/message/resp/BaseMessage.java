package com.message.resp;

/**
 * ������Ϣ��
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 *
 */
public class BaseMessage {
	
	private String ToUserName;//������΢�ź� ���շ�
	private String FromUserName;//���ͷ��ʺţ�һ��OpenID��
	private long CreateTime;//��Ϣ����ʱ�� �����ͣ�
	private String MsgType;//��Ϣ����
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	

}
