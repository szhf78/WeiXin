package com.message.req;

/**
 * ͼƬʵ����
 * @author Franco.Han
 * @date 2015-9-4
 * @version 1.0
 *
 */
public class Image{
	private String PicUrl;//ͼƬ����
	private String MediaId;//ͼƬ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
	private String MsgId;//��Ϣid��64λ����
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
