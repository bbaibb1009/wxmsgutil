package cn.pudding.weichat.message.response;

import java.util.List;

import cn.pudding.weichat.Constant;

public class WcNewsMsgResp extends WcWeiBaseMsgResp {
	
	public WcNewsMsgResp(WcWeiBaseMsgResp base)
	{
		this.setFromUserName(base.getFromUserName());
		this.setToUserName(base.getToUserName());
		this.setMsgType(Constant.RESP_MESSAGE_TYPE_NEWS);
		this.setCreateTime(base.getCreateTime());
		this.setFuncFlag(base.getFuncFlag());
	}
	
	// 图文消息个数，限制为10条以内  
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个item为大图  
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
