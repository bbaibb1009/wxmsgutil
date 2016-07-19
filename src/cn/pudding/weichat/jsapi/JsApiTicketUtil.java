package cn.pudding.weichat.jsapi;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.pudding.weichat.http.HttpUtil;

public class JsApiTicketUtil {

	// ��ȡjsapi_ticket�Ľӿڵ�ַ��GET�� 
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";  
	
	private static Logger log = Logger.getLogger(JsApiTicketUtil.class);
	
	/** 
	 * ��ȡaccess_token JSONObject
	 * @param appid ƾ֤ 
	 * @param appsecret ��Կ 
	 * @return net.sf.json.JSONObject
	 * {
			"errcode":0,
			"errmsg":"ok",
			"ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
			"expires_in":7200
		}
	 */  
	public static JSONObject getJsApiTicketJson(String accessToken) {  
		JSONObject jsonJsApiTicket = null;  
	    String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);  
	    jsonJsApiTicket =  HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // �������ɹ�  
	    if (null != jsonJsApiTicket) {  
	        try 
	        {
	        	jsonJsApiTicket.getString("ticket");
	        } 
	        catch (JSONException e) 
	        {  
	            // ��ȡtokenʧ��  
	            log.error("��ȡticketʧ�� errcode:{"+jsonJsApiTicket.getInt("errcode")+"} errmsg:{"+jsonJsApiTicket.getString("errmsg")+"}");  
	            jsonJsApiTicket = null;  
	        }  
	    }  
	    
	    //log.error("��ǰ��ticket:"+jsonJsApiTicket);
	    return jsonJsApiTicket;
	}
	
	/** 
	 * ��ȡaccess_token ����
	 * @param appid ƾ֤ 
	 * @param appsecret ��Կ 
	 * @return AccessToken
	 */  
	public static JsApiTicket getJsApiTicket(String accessToken) {  
		JsApiTicket jsApiTicket = null;  
	    String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);  
	    JSONObject jsonJsApiTicket = HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // �������ɹ�  
	    if (null != jsonJsApiTicket) {  
	        try 
	        {  
	        	jsApiTicket = new JsApiTicket();  
	        	jsApiTicket.setJsapi_ticket(jsonJsApiTicket.getString("ticket"));  
	        	jsApiTicket.setExpires_in(jsonJsApiTicket.getInt("expires_in"));  
	        } 
	        catch (JSONException e) 
	        {  
	            // ��ȡtokenʧ��  
	        	 log.error("��ȡticketʧ�� errcode:{"+jsonJsApiTicket.getInt("errcode")+"} errmsg:{"+jsonJsApiTicket.getString("errmsg")+"}");  
	        	 jsApiTicket = null; 
	        }  
	    }  
	    return jsApiTicket;  
	}
	
	/** 
	 * ��ȡaccess_token ����
	 * @param appid ƾ֤ 
	 * @param appsecret ��Կ 
	 * @return Map<String,Object>
	 */  
	public static Map<String, Object> getJsApiTicketMap(String accessToken) {  
		Map<String, Object> map = null;  
	    String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);  
	    JSONObject jsonJsApiTicket = HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // �������ɹ�  
	    if (null != jsonJsApiTicket) {  
	        try 
	        {  
	        	map = new HashMap<String,Object>();  
	        	map.put("ticket", jsonJsApiTicket.getString("ticket"));  
	        	map.put("expires_in", jsonJsApiTicket.getInt("expires_in"));  
	        } 
	        catch (JSONException e) 
	        {  
	            // ��ȡtokenʧ��  
	        	log.error("��ȡticketʧ�� errcode:{"+jsonJsApiTicket.getInt("errcode")+"} errmsg:{"+jsonJsApiTicket.getString("errmsg")+"}");  
	        	map = null;  
	        }  
	    }  
	    return map;  
	}
	
	/** 
	 * ��ȡaccess_token�ַ��� 
	 * @param appid ƾ֤ 
	 * @param appsecret ��Կ 
	 * @return String 
	 */
	public static String getJsApiTicketStr(String accessToken)
	{
		String jsApiTicket = null;
		String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);  
		jsApiTicket = HttpUtil.httpRequest(requestUrl, "GET", null);
		return jsApiTicket;
	}
	
}
