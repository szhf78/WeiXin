package com.message.event;

/**
 * 自定义菜单事件
 * @author Franco.Han
 * @date 2015-9-8
 * @version
 */
public class MenuEvent extends BaseEvent {
	//事件KEY值 ,与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
