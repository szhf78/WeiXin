package com.vo;

import java.util.List;

/**
 *  关注的用户列表
 * @author Franco.Han
 * @date 2015-9-3
 * @version 1.0
 *
 */
public class UserList {
	//关注该公众账号的总用户数
	private int total;
	//拉取的OPENID个数，最大值为10000
	private int count;
	//列表数据，OPENID的列表
	private List<String> openIdList;
	//拉取列表的最后一个用户的OPENID
	private String next_openid;
	public List<String> getOpenIdList() {
		return openIdList;
	}
	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
}
