package com.message.resp;

import java.util.List;

/**
 * 图文消息类
 * @author Franco.Han
 * @date 2015-9-9
 * @version
 */
public class NewsRespMsg extends BaseRespMsg { 
	
	//图文条数
	private int ArticleCount;
	//多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
