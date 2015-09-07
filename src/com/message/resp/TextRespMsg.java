package com.message.resp;

/**
 * 文本消息 
 * @author Franco.Han
 * @date 2015-9-7
 * @version
 */
public class TextRespMsg extends BaseRespMsg {
	private String Content;//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
