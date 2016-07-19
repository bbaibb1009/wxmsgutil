package cn.pudding.weichat.accesstoken;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.pudding.weichat.http.HttpUtil;
public class AccessTokenUtil {
	
	// 获取access_token的接口地址（GET） 限200（次/天）  
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	
	private static Logger log = Logger.getLogger(AccessTokenUtil.class);
	
	/** 
	 * 获取access_token JSONObject
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return net.sf.json.JSONObject
	 */  
	public static JSONObject getAccessTokenJson(String appid, String appsecret) {  
		JSONObject jsonAccessToken = null;  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    jsonAccessToken =  HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // 如果请求成功  
	    if (null != jsonAccessToken) 
	    {  
	        try 
	        {
	        	jsonAccessToken.getString("access_token");
	        } 
	        catch (JSONException e) 
	        {  
	            // 获取token失败  
	            log.error("获取token失败 ");  
	            jsonAccessToken = null;  
	        }  
	    }  
	    return jsonAccessToken;
	}
	
	/** 
	 * 获取access_token 对象
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return AccessToken
	 */  
	public static AccessToken getAccessToken(String appid, String appsecret) {  
		AccessToken accessToken = null;  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonAccessToken = HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // 如果请求成功  
	    if (null != jsonAccessToken) {  
	        try 
	        {  
	            accessToken = new AccessToken();  
	            accessToken.setAccess_token(jsonAccessToken.getString("access_token"));  
	            accessToken.setExpires_in(jsonAccessToken.getInt("expires_in"));  
	        } 
	        catch (JSONException e) 
	        {  
	            // 获取token失败  
	            log.error("获取token失败 errcode:{"+jsonAccessToken.getInt("errcode")+"} errmsg:{"+jsonAccessToken.getString("errmsg")+"}");
	            accessToken = null; 
	        }  
	    }  
	    return accessToken;  
	}
	
	/** 
	 * 获取access_token 对象
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return Map<String,Object>
	 */  
	public static Map<String, Object> getAccessTokenMap(String appid, String appsecret) {  
		Map<String, Object> map = null;  
	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonAccessToken = HttpUtil.httpRequestJson(requestUrl, "GET", null);
	    // 如果请求成功  
	    if (null != jsonAccessToken) {  
	        try 
	        {  
	        	map = new HashMap<String,Object>();  
	        	map.put("access_token", jsonAccessToken.getString("access_token"));  
	        	map.put("expires_in", jsonAccessToken.getInt("expires_in"));  
	        } 
	        catch (JSONException e) 
	        {  
	            // 获取token失败  
	            log.error("获取token失败 errcode:{"+jsonAccessToken.getInt("errcode")+"} errmsg:{"+jsonAccessToken.getString("errmsg")+"}");  
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
	public static String getAccessTokenStr(String appid, String appsecret)
	{
		String accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
		accessToken = HttpUtil.httpRequest(requestUrl, "GET", null);
		return accessToken;
	}
}
