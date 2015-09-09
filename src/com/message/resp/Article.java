package com.message.resp;

/**
 * 图文实体类
 * @author Franco.Han
 * @date 2015-9-9
 * @version 1.0
 */
public class Article {
	
	//图文标题
	private String Title;
	//图文描述
	private String Description;
	//图片连接
	private String PicUrl;
	//图文消息跳转连接
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
}
