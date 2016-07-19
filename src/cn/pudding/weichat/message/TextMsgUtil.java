package cn.pudding.weichat.message;

import cn.pudding.weichat.message.response.LzWeiBaseMsgResp;
import cn.pudding.weichat.message.response.LzWeiTextMsgResp;

public class TextMsgUtil {

	public static LzWeiBaseMsgResp getDefualtTextMsg(LzWeiBaseMsgResp contentReq)
	{
		LzWeiTextMsgResp txtMsg = new LzWeiTextMsgResp(contentReq);
		String content= "�����Զ���˵��еĹ���ע����ߵ�¼�󣬼��ɷ������߲鿴���¹�����Ϣ���������������ṩ�ͻ���";
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
