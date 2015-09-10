package com.vo;

/**
 * 生成带参数的二维码类
 * @author Franco.Han
 * @date 2015-9-10
 * @version
 */
public class WeixinQRCode {
	//该二维码有效时间，以秒为单位。 最大不超过1800。
	private int expire_seconds;
	//获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	private String ticket;
	public int getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
}
