package cn.pudding.weichat.message;

import cn.pudding.weichat.message.response.LzWeiBaseMsgResp;
import cn.pudding.weichat.message.response.LzWeiTextMsgResp;

public class TextMsgUtil {

	public static LzWeiBaseMsgResp getDefualtTextMsg(LzWeiBaseMsgResp contentReq)
	{
		LzWeiTextMsgResp txtMsg = new LzWeiTextMsgResp(contentReq);
		String content= "请点击自定义菜单中的供求，注册或者登录后，即可发布或者查看最新供求信息！您发布供求，我提供客户。";
		txtMsg.setContent(content);
		return txtMsg;
	}
	
	public static LzWeiBaseMsgResp getTextMsg(LzWeiBaseMsgResp contentReq,String respContent)
	{
		LzWeiTextMsgResp txtMsg = new LzWeiTextMsgResp(contentReq);
		txtMsg.setContent(respContent);
		return txtMsg;
	}

}
