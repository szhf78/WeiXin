package com.message.event;

/**
 * 扫描带参数二维码事件
 * @author Franco.Han
 * @date 2015-9-8
 * @version
 */
public class QRCodeEvent extends BaseEvent {

	//事件KEY值
	private String EventKey;
	//用于换取二维码图片
	private String Ticket;
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
}
