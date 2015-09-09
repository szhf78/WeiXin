package com.util;

import java.awt.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vo.UserInfo;
import com.vo.UserList;

/**
 * 高级接口实用工具类
 * 
 * @author Franco.Han
 * @date 2015-9-9
 * @version
 */
public class AdvancedUtil {
	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);

	/**
	 * 获取用户信息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return
	 */
	public static UserInfo getUserInfo(String accessToken, String openId) {
		UserInfo weixinUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = WeixinUtil
				.httpsRequest(requestUrl, "GET", null);
		System.out.println("jsonObject=" + jsonObject);
		if (null != jsonObject) {
			try {
				weixinUserInfo = new UserInfo();

				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				weixinUserInfo.setCity(jsonObject.getString("city"));
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				weixinUserInfo
						.setHeadimgurl(jsonObject.getString("headimgurl"));
				weixinUserInfo.setSubscribe_time(jsonObject
						.getString("subscribe_time"));
				// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
				// weixinUserInfo.setUnionid(jsonObject.getString("unionid"));
				weixinUserInfo.setRemark(jsonObject.getString("remark"));
				weixinUserInfo.setGroupid(jsonObject.getString("groupid"));

			} catch (Exception e) {
				if (0 == weixinUserInfo.getSubscribe()) {
					log.error("用户{}已取消关注", weixinUserInfo.getOpenId());
				} else {
					String errorCode = jsonObject.getString("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode,
							errorMsg);
				}
			}

		}
		return weixinUserInfo;
	}

	/**
	 * 获取关注者列表
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 * @param nextOpenId
	 *            　每一个拉取换openId,不填默认从头开始拉取
	 * @return
	 */
	public static UserList getUserList(String accessToken, String nextOpenId) {
		UserList weixinUserList = null;

		if (null == nextOpenId)
			nextOpenId = "";

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"NEXT_OPENID", nextOpenId);

		// 获取关注者列表
		JSONObject jsonObject = WeixinUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinUserList = new UserList();
				weixinUserList.setTotal(jsonObject.getInt("total"));
				weixinUserList.setCount(jsonObject.getInt("count"));
				weixinUserList.setNext_openid(jsonObject
						.getString("next_openid"));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
				weixinUserList.setOpenIdList(JSONArray.toList(
						dataObject.getJSONArray("openid"), List.class));

			} catch (JSONException e) {
				weixinUserList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinUserList;
	}

	public static void main(String args[]) {
		String accessToken = WeixinUtil.getToken(Parameter.appId,
				Parameter.appSecret).getAccessToken();

		System.out.println("accessToken=" + accessToken);
		// 获取关注者列表
		UserList weixingUserList = getUserList(accessToken, null);
		System.out.println("总关注用户数:" + weixingUserList.getTotal());
		System.out.println("本次获取用户数:" + weixingUserList.getCount());
		System.out.println("OpenID列表"
				+ weixingUserList.getOpenIdList().toString());
		System.out.println("next_openid:" + weixingUserList.getNext_openid());

		// 获取某一用户的信息
		UserInfo user = getUserInfo(accessToken, "oyb8KxJqM5AUtJVwi-X33qUC7eu0");
		System.out.println("OpenID:" + user.getOpenId());
		System.out.println("关注状态:" + user.getSubscribe());
		System.out.println("关注时间:" + user.getSubscribe_time());
		System.out.println("昵称:" + user.getNickname());
		System.out.println("性别:" + user.getSex());
		System.out.println("国家:" + user.getCountry());
		System.out.println("省份:" + user.getProvince());
		System.out.println("城市:" + user.getCity());
		System.out.println("语言:" + user.getLanguage());
		System.out.println("头像" + user.getHeadimgurl());
	}

}
