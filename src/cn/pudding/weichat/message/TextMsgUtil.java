package cn.pudding.weichat.message;

import cn.pudding.weichat.message.response.WcWeiBaseMsgResp;
import cn.pudding.weichat.message.response.WcWeiTextMsgResp;

public class TextMsgUtil {

	public static WcWeiBaseMsgResp getDefualtTextMsg(WcWeiBaseMsgResp contentReq)
	{
		WcWeiTextMsgResp txtMsg = new WcWeiTextMsgResp(contentReq);
		String content= "�����Զ���˵��еĹ���ע����ߵ�¼�󣬼��ɷ������߲鿴���¹�����Ϣ���������������ṩ�ͻ���";
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
