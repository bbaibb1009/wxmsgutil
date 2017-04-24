//:LzWeiTextMsg.java
package cn.pudding.weichat.message.response;

import cn.pudding.weichat.Constant;


public class WcWeiTextMsgResp extends WcWeiBaseMsgResp {

	public WcWeiTextMsgResp(WcWeiBaseMsgResp base)
	{
		this.setFromUserName(base.getFromUserName());
		this.setToUserName(base.getToUserName());
		this.setCreateTime(base.getCreateTime());
		this.setFuncFlag(base.getFuncFlag());
		this.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
	}
	//消息内容  
    private String Content;  
    
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
    
}//:~
