//:LzWeiTextMsg.java
package cn.pudding.weichat.message.response;

import cn.pudding.weichat.Constant;


public class LzWeiTextMsgResp extends LzWeiBaseMsgResp {

	public LzWeiTextMsgResp(LzWeiBaseMsgResp base)
	{
		this.setFromUserName(base.getFromUserName());
		this.setToUserName(base.getToUserName());
		this.setCreateTime(base.getCreateTime());
		this.setFuncFlag(base.getFuncFlag());
		this.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
	}
	//ÏûÏ¢ÄÚÈÝ  
    private String Content;  
    
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
    
}//:~
