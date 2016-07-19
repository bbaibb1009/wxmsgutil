package cn.pudding.weichat.user;
import net.sf.json.JSONObject;
import cn.pudding.weichat.http.HttpUtil;
public class UserUtil 
{
	//获取用户基本信息(GET)  
    public static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";  

	public static JSONObject getUserInfo(String accessToken,String openId)
	{
		String url = user_info_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID",openId);  
		JSONObject obj = HttpUtil.httpRequestJson(url,"GET",null);
		return obj;
	}
	
	
	public static void main(String[] args)
	{
		
	 
		JSONObject jsonObj = 	UserUtil.getUserInfo("f7GYW3X5xcxGDbBMp7cBsHf6TdoAv8c9Dt9tlDcn6J4s3s5qTLER23PIS_qGtO4-L0OLJFddM0io2p4O8z8aJUjBACBPkzMC2qZiC5i8MVU", "oFVvzsk82VGxsVDrT8YhWzwaTDXE");
		System.out.println(jsonObj.toString());
	}
	
}
