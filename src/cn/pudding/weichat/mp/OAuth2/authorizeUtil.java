package cn.pudding.weichat.mp.OAuth2;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.pudding.weichat.http.HttpUtil;

public class authorizeUtil {

	private static String GET_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	private static Logger log = Logger.getLogger(authorizeUtil.class);

	/**
	 * ͨ��CODE ��ȡ�û��� accessTokenObject
	 * */
	public static JSONObject getAccessTokenObj(String appId,String appSecret,String code)
	{
		JSONObject jsonAccessToken = null;  
	    String requestUrl = GET_OPENID_URL.replace("APPID", appId).replace("SECRET",appSecret).replace("CODE",code);  
	    jsonAccessToken   = HttpUtil.httpRequestJson(requestUrl, "GET", null);
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
		        log.error("��ȡtokenʧ�� errcode:{"+jsonAccessToken.getInt("errcode")+"} errmsg:{"+jsonAccessToken.getString("errmsg")+"}");  
		        jsonAccessToken = null; 
		    } 
    	}
	    return jsonAccessToken;
	}

	/**
	 * ����CODE ��ȡ�û��� openId
	 * */
	public static String getOpenId(String appId,String appSecret,String code)
	{
		String openId = "";
		JSONObject jsonAccessToken = getAccessTokenObj(appId,appSecret,code);  
		if(jsonAccessToken!=null)
		{
			openId = jsonAccessToken.getString("openid");
		}
		return openId;
	}
	
	/**
	 * ����CODE ��ȡ�û��� accesstoken
	 * */
	public static String getAccessToken(String appId,String appSecret,String code)
	{
		String accessToken = "";
		JSONObject jsonAccessToken = getAccessTokenObj(appId,appSecret,code);  
		if(jsonAccessToken!=null)
		{
			accessToken = jsonAccessToken.getString("access_token");
		}
		return accessToken;
	}
	
	/**
	 * ����CODE ��ȡ�û��� refresh_token 
	 * */
	public static String getRefreshToken(String appId,String appSecret,String code)
	{
		String refreshToken = "";
		JSONObject jsonAccessToken = getAccessTokenObj(appId,appSecret,code);  
		if(jsonAccessToken!=null)
		{
			refreshToken = jsonAccessToken.getString("refresh_token");
		}
		return refreshToken;
	}
	
	public static void main(String [] args)
	{
		authorizeUtil.getOpenId("wx699aebd2bc63a9fb", "b217c1c39dddfdbde6f489cc44953400", "");
	}
	
	
}
