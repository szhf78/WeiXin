package com.message.requ;


/**
 * 语音消息
 * @author Franco.Han
 * @date 2015-9-7
 * @version 1.0
 */
public class VoiceMessage extends BaseMessage {
		// 语音消息媒体id
		private String MediaId;
		// 语音格式
		private String Format;

		public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}

		public String getFormat() {
			return Format;
		}

		public void setFormat(String format) {
			Format = format;
		}

}
