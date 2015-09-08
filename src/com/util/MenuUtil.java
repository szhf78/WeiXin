package com.util;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.menu.Menu;

/**
 * 自定义菜单工具类
 * @author Franco.Han
 * @date 2015-9-8
 * @version
 */
public class MenuUtil {
	private static Logger log=LoggerFactory.getLogger(MenuUtil.class);
	
	public final static String menu_create_url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public final static String menu_get_url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public final static String menu_delete_url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	public static boolean createMenu(Menu menu,String accessToken){
		boolean result=false;
		String url=menu_create_url.replace("ACCESS_TOKEN", accessToken);
		
		//将菜单对象转换成json字符串
		String jsonMenu=JSONObject.fromObject(menu).toString();
		System.out.println("jsonMenu="+jsonMenu);
		//发起POST请求创建菜单
		JSONObject jsonObject=WeixinUtil.httpsRequest(url, "POST", jsonMenu);
		
		if(null!=jsonObject){
			int errorCode=jsonObject.getInt("errcode");
			String errorMsg=jsonObject.getString("errmsg");
			if(0==errorCode){
				result=true;
			}else{
				result=false;
				log.error("创建菜单失败 errcode:{} errmsg:{}",errorCode,errorMsg);
			}
			
		}
		return result;
	}
	
	/**
	 * 查询菜单
	 * @param accessToken
	 * @return
	 */
	public static String getMenu(String accessToken){
		String result=null;
		String requestUrl=menu_get_url.replace("ACCESS_TOKEN", accessToken);
		
		//发起GET请求查询菜单
		JSONObject jsonObject=WeixinUtil.httpsRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			result=jsonObject.toString();
		}
		return result;
	}
	
	/**
	 * 删除菜单
	 * @param accessToken
	 * @return true 成功 false失败
	 */
	public static boolean deleteMenu(String accessToken){
		boolean result=false;
		String requestUrl=menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		
		//发起GET请求删除菜单
		JSONObject jsonObject=WeixinUtil.httpsRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			int errorCode=jsonObject.getInt("errcode");
			String errorMsg=jsonObject.getString("errmsg");
			if(0==errorCode){
				result=true;
			}else{
				result=false;
				log.error("删除菜单失败 errcode:{} errmsg:{}",errorCode,errorMsg);
			}
		}
		return result;
	}
}
