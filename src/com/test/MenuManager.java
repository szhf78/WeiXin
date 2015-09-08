package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.menu.Button;
import com.menu.ClickButton;
import com.menu.ComplexButton;
import com.menu.Menu;
import com.menu.ViewButton;
import com.util.MenuUtil;
import com.util.Parameter;
import com.util.WeixinUtil;
import com.vo.Token;

public class MenuManager {
	private static Logger log=LoggerFactory.getLogger(MenuManager.class);
	
	/**
	 * 定义菜单结构
	 * @return
	 */
	private static Menu getMenu(){
		ClickButton btn11=new ClickButton();
		btn11.setName("美女");
		btn11.setType("click");
		btn11.setKey("pretty");
		
		ClickButton btn12=new ClickButton();
		btn12.setName("女汉子");
		btn12.setType("click");
		btn12.setKey("hanzhi");
		
		ViewButton btn21=new ViewButton();
		btn21.setName("帅哥");
		btn21.setType("view");
		btn21.setUrl("http://www.duowan.com/");
		
		ViewButton btn22=new ViewButton();
		btn22.setName("屌丝");
		btn22.setType("view");
		btn22.setUrl("http://www.duowan.com/");
		
		ClickButton btn31=new ClickButton();
		btn31.setName("女汉子");
		btn31.setType("click");
		btn31.setKey("hanzhi");
		
		
		ComplexButton mainBtn1=new ComplexButton();
		mainBtn1.setName("男");
		mainBtn1.setSub_button(new Button[]{btn11,btn12});
		
		ComplexButton mainBtn2=new ComplexButton();
		mainBtn2.setName("女");
		mainBtn2.setSub_button(new Button[]{btn21,btn22});
		
		ComplexButton mainBtn3=new ComplexButton();
		mainBtn3.setName("其它");
		mainBtn3.setSub_button(new Button[]{btn31});
		
		
		Menu menu=new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		return menu;
	}
	
	public static void main(String[] args){
		String appId=Parameter.appId;//测试公众号
		
		String appSecret=Parameter.appSecret;//测试公众号
		
		Token token=WeixinUtil.getToken(appId, appSecret);
		
		System.out.println("token="+token.getAccessToken());
		String jsonMenus=MenuUtil.getMenu(token.getAccessToken());
		System.out.println("jsonMenus="+jsonMenus);
		
		if(null!=token){
			//创建菜单
			boolean result=MenuUtil.createMenu(getMenu(), token.getAccessToken());
			
			//判断菜单创建结果
			if(result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
		
	}
}
