package com.menu;

/**
 * 复合类型的按钮
 * @author Franco.Han
 * @date 2015-9-8
 * @version
 */
public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
