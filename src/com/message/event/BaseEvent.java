package com.message.event;

/**
 * 基础推送类(用户-->公众号)
 * @author Franco.Han
 * @date 2015-9-7
 * @version
 */
public class BaseEvent {
	private String ToUserName;// 开发者微信号 接收方
	private String FromUserName;// 发送方帐号（一个OpenID）
	private long CreateTime;// 消息创建时间 （整型）
	private String MsgType;// 消息类型
	private String Event;// 事件类型

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

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}
}
