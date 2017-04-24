package cn.pudding.weichat.qrcode;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.pudding.weichat.http.HttpUtil;

public class WxQrCodeUtil {
	private static 	Logger log = Logger.getLogger(WxQrCodeUtil.class);
	private static 	Integer expireSeconds = 604800;
	private static  String action_name_linshi = "QR_SCENE";
	// 创建二维码ticket (临时)
    public static String qrcode_ticket_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";  
    
    // 换取二维码的地址
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
     * 获取换二维码的ticket
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
    
    /**
	 * 获取二维码
	 * 临时二维码:{"expire_seconds": 604800, 	"action_name": "QR_SCENE", 			"action_info": {"scene": {"scene_id": 123}}}
	 * 永久二维码:{								"action_name": "QR_LIMIT_SCENE", 	"action_info": {"scene": {"scene_id": 123}}}
	 * */
	public static JSONObject getQrUrlJson(String accessToken,long expireSeconds,String actionName,String sceneId)
	{
		String requestUrl = qrcode_ticket_url.replace("TOKEN", accessToken);
		String requestMethod = "POST";
		JSONObject jsonObj  = new JSONObject();
		jsonObj.put("action_name", actionName);
		if(actionName.equals("QR_SCENE"))
		{
			jsonObj.put("expire_seconds", expireSeconds);
		}
		jsonObj.put("action_info", new JSONObject().put("scene", new JSONObject().put("scene_id", sceneId)));
		return HttpUtil.httpRequestJson(requestUrl, requestMethod, jsonObj.toString());
	}
   
    public static void main(String[] args)
    {
    	String ticket = getQrCodeTicket("n-RYoskoqqxN6tjwrWw8OVSnGbtZxv6V6GMgg99Mu-4dqlMdWQL5u3oQEtvyRnb2Urrv2IEOH1DX5t2CmSac81rVMbA_nnumw9MH61jn6SU",1234);
    	System.out.println(ticket);
    }
   
}
