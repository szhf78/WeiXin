package com.message.resp;

/**
 * 基础消息类 (公众号-->用户)
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 *
 */
public class BaseRespMsg {
	private String ToUserName;//接收方帐号（一个OpenID）
	private String FromUserName;//开发者微信号
	private long CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型
	
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
