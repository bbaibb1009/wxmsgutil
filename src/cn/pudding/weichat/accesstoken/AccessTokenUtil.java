package cn.pudding.weichat.accesstoken;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.pudding.weichat.http.HttpUtil;
public class AccessTokenUtil {
	
	// ��ȡaccess_token�Ľӿڵ�ַ��GET�� ��200����/�죩  
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	
	private static Logger log = Logger.getLogger(AccessTokenUtil.class);
	
	/** 
	 * ��ȡaccess_token JSONObject
	 * @param appid ƾ֤ 
	 * @param appsecret ��Կ 
	 * @return net.sf.json.JSONObject
	 */  
	public static JSONObject getAccessTokenJson(String appid, String appsecret) {  
		JSONObject jsonAccessToken = null;  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    jsonAccessToken =  HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // �������ɹ�  
	    if (null != jsonAccessToken) 
	    {  
	        try 
	        {
	        	jsonAccessToken.getString("access_token");
	        } 
	        catch (JSONException e) 
	        {  
	            // ��ȡtokenʧ��  
	            log.error("��ȡtokenʧ�� ");  
	            jsonAccessToken = null;  
	        }  
	    }  
	    return jsonAccessToken;
	}
	
	/** 
	 * ��ȡaccess_token ����
	 * @param appid ƾ֤ 
	 * @param appsecret ��Կ 
	 * @return AccessToken
	 */  
	public static AccessToken getAccessToken(String appid, String appsecret) {  
		AccessToken accessToken = null;  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonAccessToken = HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // �������ɹ�  
	    if (null != jsonAccessToken) {  
	        try 
	        {  
	            accessToken = new AccessToken();  
	            accessToken.setAccess_token(jsonAccessToken.getString("access_token"));  
	            accessToken.setExpires_in(jsonAccessToken.getInt("expires_in"));  
	        } 
	        catch (JSONException e) 
	        {  
	            // ��ȡtokenʧ��  
	            log.error("��ȡtokenʧ�� errcode:{"+jsonAccessToken.getInt("errcode")+"} errmsg:{"+jsonAccessToken.getString("errmsg")+"}");
	            accessToken = null; 
	        }  
	    }  
	    return accessToken;  
	}
	
	/** 
	 * ��ȡaccess_token ����
	 * @param appid ƾ֤ 
	 * @param appsecret ��Կ 
	 * @return Map<String,Object>
	 */  
	public static Map<String, Object> getAccessTokenMap(String appid, String appsecret) {  
		Map<String, Object> map = null;  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonAccessToken = HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // �������ɹ�  
	    if (null != jsonAccessToken) {  
	        try 
	        {  
	        	map = new HashMap<String,Object>();  
	        	map.put("access_token", jsonAccessToken.getString("access_token"));  
	        	map.put("expires_in", jsonAccessToken.getInt("expires_in"));  
	        } 
	        catch (JSONException e) 
	        {  
	            // ��ȡtokenʧ��  
	            log.error("��ȡtokenʧ�� errcode:{"+jsonAccessToken.getInt("errcode")+"} errmsg:{"+jsonAccessToken.getString("errmsg")+"}");  
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
	public static String getAccessTokenStr(String appid, String appsecret)
	{
		String accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
		accessToken = HttpUtil.httpRequest(requestUrl, "GET", null);
		return accessToken;
	}
}
