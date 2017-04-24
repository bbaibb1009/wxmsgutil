package cn.pudding.weichat.message;

import cn.pudding.weichat.message.response.WcWeiBaseMsgResp;
import cn.pudding.weichat.message.response.WcWeiTextMsgResp;

public class TextMsgUtil {

	public static WcWeiBaseMsgResp getDefualtTextMsg(WcWeiBaseMsgResp contentReq)
	{
		WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(contentReq);
		String content= "请点击自定义菜单中的供求，注册或者登录后，即可发布或者查看最新供求信息！您发布供求，我提供客户。";
		txtMsg.setContent(content);
		return txtMsg;
	}
	
	public static WcWeiBaseMsgResp getTextMsg(WcWeiBaseMsgResp contentReq,String respContent)
	{
		WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(contentReq);
		txtMsg.setContent(respContent);
		return txtMsg;
	}

}
