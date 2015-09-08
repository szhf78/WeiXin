package com.menu;

/**
 * view类型的按钮
 * @author Franco.Han
 * @date 2015-9-8
 * @version
 */
public class ViewButton extends Button {
	private String type;
	private String url;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
