package com.message.requ;

/**
 * 视频消息
 * @author Franco.Han
 * @date 2015-9-7
 * @version
 */
public class VideoRequMsg extends BaseRequMsg {
	//视频消息媒体id
	private String MediaId;
	
	//视频消息缩略图的媒体id
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
