package cn.pudding.weichat.jsapi;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.pudding.weichat.http.HttpUtil;

public class JsApiTicketUtil {

	// 获取jsapi_ticket的接口地址（GET） 
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";  
	
	private static Logger log = Logger.getLogger(JsApiTicketUtil.class);
	
	/** 
	 * 获取access_token JSONObject
	 * @param appid 凭证 
	 * @param appsecret 密钥 
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
	    // 如果请求成功  
	    if (null != jsonJsApiTicket) {  
	        try 
	        {
	        	jsonJsApiTicket.getString("ticket");
	        } 
	        catch (JSONException e) 
	        {  
	            // 获取token失败  
	            log.error("获取ticket失败 errcode:{"+jsonJsApiTicket.getInt("errcode")+"} errmsg:{"+jsonJsApiTicket.getString("errmsg")+"}");  
	            jsonJsApiTicket = null;  
	        }  
	    }  
	    
	    //log.error("当前的ticket:"+jsonJsApiTicket);
	    return jsonJsApiTicket;
	}
	
	/** 
	 * 获取access_token 对象
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return AccessToken
	 */  
	public static JsApiTicket getJsApiTicket(String accessToken) {  
		JsApiTicket jsApiTicket = null;  
	    String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);  
	    JSONObject jsonJsApiTicket = HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // 如果请求成功  
	    if (null != jsonJsApiTicket) {  
	        try 
	        {  
	        	jsApiTicket = new JsApiTicket();  
	        	jsApiTicket.setJsapi_ticket(jsonJsApiTicket.getString("ticket"));  
	        	jsApiTicket.setExpires_in(jsonJsApiTicket.getInt("expires_in"));  
	        } 
	        catch (JSONException e) 
	        {  
	            // 获取token失败  
	        	 log.error("获取ticket失败 errcode:{"+jsonJsApiTicket.getInt("errcode")+"} errmsg:{"+jsonJsApiTicket.getString("errmsg")+"}");  
	        	 jsApiTicket = null; 
	        }  
	    }  
	    return jsApiTicket;  
	}
	
	/** 
	 * 获取access_token 对象
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return Map<String,Object>
	 */  
	public static Map<String, Object> getJsApiTicketMap(String accessToken) {  
		Map<String, Object> map = null;  
	    String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);  
	    JSONObject jsonJsApiTicket = HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // 如果请求成功  
	    if (null != jsonJsApiTicket) {  
	        try 
	        {  
	        	map = new HashMap<String,Object>();  
	        	map.put("ticket", jsonJsApiTicket.getString("ticket"));  
	        	map.put("expires_in", jsonJsApiTicket.getInt("expires_in"));  
	        } 
	        catch (JSONException e) 
	        {  
	            // 获取token失败  
	        	log.error("获取ticket失败 errcode:{"+jsonJsApiTicket.getInt("errcode")+"} errmsg:{"+jsonJsApiTicket.getString("errmsg")+"}");  
	        	map = null;  
	        }  
	    }  
	    return map;  
	}
	
	/** 
	 * 获取access_token字符串 
	 * @param appid 凭证 
	 * @param appsecret 密钥 
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
