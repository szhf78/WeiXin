package com.vo;

/**
 * 生成带参数的二维码类
 * @author Franco.Han
 * @date 2015-9-10
 * @version
 */
public class WeixinQRCode2 {
	//该二维码有效时间，以秒为单位。 最大不超过604800（即7天）。
	private int expire_seconds;
	//二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
	private String action_name;
	//二维码详细信息
	private String action_info;
	//场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	private int scene_id;
	// 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
	private String scene_str;
	//获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	private String ticket;
	public int getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	public String getAction_info() {
		return action_info;
	}
	public void setAction_info(String action_info) {
		this.action_info = action_info;
	}
	public int getScene_id() {
		return scene_id;
	}
	public void setScene_id(int scene_id) {
		this.scene_id = scene_id;
	}
	public String getScene_str() {
		return scene_str;
	}
	public void setScene_str(String scene_str) {
		this.scene_str = scene_str;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
