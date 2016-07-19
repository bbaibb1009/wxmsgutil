package cn.pudding.weichat.qrcode;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.pudding.weichat.http.HttpUtil;

public class WxQrCodeUtil {
	private static 	Logger log = Logger.getLogger(WxQrCodeUtil.class);
	private static 	Integer expireSeconds = 604800;
	private static  String action_name_linshi = "QR_SCENE";
	// ������ά��ticket (��ʱ)
    public static String qrcode_ticket_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";  
    // ��ȡ��ά��ĵ�ַ
    public static String show_qrcode_url   = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    
    /**
     * 
     * {
     * 		"expire_seconds": 604800, 
     * 		"action_name": "QR_SCENE", 
     * 		"action_info": 
     * 		{
     * 			"scene": 
     * 			{"scene_id": 123}
     * 		}
     * }
     * ��ȡ����ά���ticket
     * */
    public static String getQrCodeTicket(String accessToken,Integer scene)
    {
    	String requestUrl = qrcode_ticket_url.replace("TOKEN", accessToken);
    	String outputStr = "";
    	JSONObject outJson = new JSONObject();
    	outJson.put("expire_seconds", expireSeconds);
    	outJson.put("action_name", action_name_linshi);
    	JSONObject sceneId = new JSONObject();
    	sceneId.put("scene_id", scene);
    	JSONObject actionInfo = new JSONObject();
    	actionInfo.put("scene", sceneId);
    	outJson.put("action_info", actionInfo);
    	outputStr = outJson.toString();
    	String result = HttpUtil.httpRequest(requestUrl, "POST", outputStr);
    	JSONObject resObj = JSONObject.fromObject(result);
    	if(resObj!=null)
    	{
    		return resObj.getString("ticket");
    	}
    	else
    	{
    		return null;
    	}
    }
   
    public static void main(String[] args)
    {
    	String ticket = getQrCodeTicket("n-RYoskoqqxN6tjwrWw8OVSnGbtZxv6V6GMgg99Mu-4dqlMdWQL5u3oQEtvyRnb2Urrv2IEOH1DX5t2CmSac81rVMbA_nnumw9MH61jn6SU",1234);
    	System.out.println(ticket);
    }
   
}
