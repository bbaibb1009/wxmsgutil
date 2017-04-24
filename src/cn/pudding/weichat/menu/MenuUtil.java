package cn.pudding.weichat.menu;
import net.sf.json.JSONObject;
import cn.pudding.weichat.http.HttpUtil;
public class MenuUtil 
{
	//菜单创建(POST)  
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
    //菜单查询(GET)  
    public static String menu_get_url    = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";  
    //菜单删除(GET)  
    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";  
    
	public static String createMenu(String accessToken,String jsonStr)
	{
		String respStr = "";
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
		JSONObject obj = HttpUtil.httpRequestJson(url,"POST",jsonStr);
		if(obj!=null)
		{
			respStr = obj.getString("errcode");
		}
		return respStr;
	}
	
	public static JSONObject getMenu(String accessToken)
	{
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
		JSONObject obj = HttpUtil.httpRequestJson(url,"GET",null);
		return obj;
	}
	
	public static String deleteMenu()
	{
		String respStr = "";
		JSONObject obj = HttpUtil.httpRequestJson(menu_delete_url,"GET",null);
		if(obj!=null)
		{
			respStr = obj.getString("errcode");
		}
		return respStr;
	}
}
