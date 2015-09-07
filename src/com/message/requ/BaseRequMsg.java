package com.message.requ;

/**
 * 基础消息类 (用户-->公众号)
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 *
 */
public class BaseRequMsg {
	
	private String ToUserName;//开发者微信号 接收方
	private String FromUserName;//发送方帐号（一个OpenID）
	private long CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型
	private long MsgId;//消息id，64位整型
	
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
	
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

}
