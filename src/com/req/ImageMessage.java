package com.req;

/**
 * 图片消息类
 * @author Franco.Han
 * @date 2015-9-4
 * @version 1.0
 *
 */
public class ImageMessage extends BaseMessage {
	//图片对象
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
