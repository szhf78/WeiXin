package com.message.req;

/**
 * 图片实体类
 * @author Franco.Han
 * @date 2015-9-4
 * @version 1.0
 *
 */
public class Image{
	private String PicUrl;//图片链接
	private String MediaId;//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MsgId;//消息id，64位整型
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
