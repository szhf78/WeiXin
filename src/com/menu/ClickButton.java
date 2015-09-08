package com.menu;

/**
 * click类型的按钮
 * @author Franco.Han
 * @date 2015-9-8
 * @version
 */
public class ClickButton extends Button {
	private String type;
	private String key;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
