package cn.pudding.weichat.pay;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public class wxPayUtil {

	private static Logger log = Logger.getLogger(wxPayUtil.class);
	
	public static String getUnifiedOrderSign(String appid,String attach,String body,String mch_id,String nonce_str,String notify_url,String openid,String out_trade_no,String spbill_create_ip,int total_fee,String trade_type,String key)
	{
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();  
        parameters.put("appid", appid);  
        parameters.put("attach", attach);  
        parameters.put("body", body);  
        parameters.put("mch_id", mch_id);  
        parameters.put("nonce_str", nonce_str); 
        parameters.put("notify_url", notify_url);  
        parameters.put("openid", openid);  
        parameters.put("out_trade_no", out_trade_no);  
        parameters.put("spbill_create_ip", spbill_create_ip);  
        parameters.put("total_fee", total_fee);  
        parameters.put("trade_type", trade_type);  
        
        StringBuffer sb = new StringBuffer();  
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            Object v = entry.getValue();  
            if(null != v && !"".equals(v)&& !"sign".equals(k) && !"key".equals(k)) 
            {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + key); 
        String sign="";
		try 
		{
			sign = DigestUtils.md5Hex(sb.toString().getBytes("utf-8")).toUpperCase();
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			sign = "编码错误";
		}  
		log.info("算出来的sign:"+sign);
        return sign;  
	}
	
	public static String getReturnPrePayidSign(String return_code,String return_msg,String appid,String mch_id,String nonce_str,String prepay_id,String result_code,String err_code_des,String code_url, String trade_type,String key)
	{
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();  
        parameters.put("appid", appid);  
        parameters.put("return_code", return_code);  
        parameters.put("return_msg", return_msg);  
        parameters.put("mch_id", mch_id);  
        parameters.put("nonce_str", nonce_str); 
        parameters.put("prepay_id", prepay_id);  
        parameters.put("result_code", result_code);  
        parameters.put("err_code_des", err_code_des);  
        parameters.put("code_url", code_url);  
        parameters.put("trade_type", trade_type);  
        
        StringBuffer sb = new StringBuffer();  
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            Object v = entry.getValue();  
            if(null != v && !"".equals(v)&& !"sign".equals(k) && !"key".equals(k)) 
            {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + key); 
      //  System.out.println(sb);
        
        String sign="";
		try 
		{
			sign = DigestUtils.md5Hex(sb.toString().getBytes("utf-8")).toUpperCase();
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			sign = "编码错误";
		}  
        log.info("算出来的sign:"+sign);
        return sign;  
	}
	
	public static String getSignByMap(Map<String,Object> map,String key)
	{
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();  
        
		Set es1 = map.entrySet();//所有参与传参的参数按照accsii排序（升序）  
	    Iterator it1 = es1.iterator();  
	    while(it1.hasNext()) {  
	    	Map.Entry entry1 = (Map.Entry)it1.next();  
            String k = (String)entry1.getKey();  
            Object v = entry1.getValue();  
	    	parameters.put(k, v);  
	    }
	    
        StringBuffer sb = new StringBuffer();  
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            Object v = entry.getValue();  
            if(null != v && !"".equals(v)&& !"sign".equals(k) && !"key".equals(k)) 
            {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + key); 
        log.info("url串:"+sb);
        String sign="";
		try 
		{
			sign = DigestUtils.md5Hex(sb.toString().getBytes("utf-8")).toUpperCase();
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			sign = "编码错误";
		}  
        log.info("算出来的sign:"+sign);
        return sign;  
		
	}
	
	public static void main(String [] args)
	{
		/**
		 <appid><![CDATA[]]></appid>
		 <><![CDATA[]]></attach>
		 <></body>
		 <><![CDATA[]]></mch_id>
		 <><![CDATA[]]></nonce_str>
		 <><![CDATA[]]></notify_url>
		 <><![CDATA[]]></openid>
		 <><![CDATA[]]></out_trade_no>
		 <><![CDATA[]]></spbill_create_ip>
		 <><![CDATA[1]]></total_fee>
		 <><![CDATA[]]></trade_type>
		  
		 * */
		
//		String sign="";
//		try 
//		{
//			sign = DigestUtils.md5Hex("appid=wx2ba0ce17184b93b7&code_url=weixin://wxpay/bizpayurl?pr=Pi8Xi4I&err_code_des=1&mch_id=1237186802&nonce_str=ZhM6zgNckhudVCcF&prepay_id=wx20160608145953207d651d410625952531&result_code=SUCCESS&return_code=SUCCESS&return_msg=OK&trade_type=NATIVE&key=HdJYXfrd7tWNPxnoaxLBRf9eB3JYEtVU".toString().getBytes("utf-8")).toUpperCase();
//		} 
//		catch (UnsupportedEncodingException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			sign = "编码错误";
//		}  
//        System.out.println("加密后的:"+sign);
        
        
//        <result_code></result_code><sign>2318663E8F55B1BBE9DDACD8F9371ECF</sign>
//        <mch_id></mch_id>
//        <err_code_des>1</err_code_des>
//        <prepay_id></prepay_id>
//        <return_msg></return_msg>
//        <appid></appid>
//        <></code_url>
//        <nonce_str></nonce_str>
//        <return_code></return_code>
//        <></trade_type>
		String return_code = "SUCCESS";
        String return_msg= "OK";
        String appid= "wx2ba0ce17184b93b7";
        String mch_id= "1237186802";
        String nonce_str= "ZhM6zgNckhudVCcF";
        String prepay_id= "wx20160608145953207d651d410625952531";
        String result_code= "SUCCESS";
        String err_code_des= "1";
        String code_url= "weixin://wxpay/bizpayurl?pr=Pi8Xi4I";
        String trade_type= "NATIVE";
        String key= "HdJYXfrd7tWNPxnoaxLBRf9eB3JYEtVU";
      //  wxPayUtil. getReturnPrePayidSign(return_code,return_msg,appid,mch_id,nonce_str,prepay_id,result_code,err_code_des,code_url,trade_type,key);
		
        Map<String,Object> map1 = new HashMap<String,Object>();
        
        map1.put("appid", appid);  
        map1.put("return_code", return_code);  
        map1.put("return_msg", return_msg);  
        map1.put("mch_id", mch_id);  
        map1.put("nonce_str", nonce_str); 
        map1.put("prepay_id", prepay_id);  
        map1.put("result_code", result_code);  
        map1.put("err_code_des", err_code_des);  
        map1.put("code_url", code_url);  
        map1.put("trade_type", trade_type);  
       // wxPayUtil.getSignByMap(map1, key);
        
        Map<String,Object> map2 = new HashMap<String,Object>();
        String appid1 			=	"wx2ba0ce17184b93b7";
        String attach1			=	"测试";
        String body1				=	"测试接口";
        String mch_id1			=	"1237186802";
        String nonce_str1		=	"4gpKmn5yIJ4eTfyE";
        String notify_url1		=	"http://info.oilchem.net/prowxpay/notify";
        String openid1			=	"odlQFt_VTLGgtf-cwTUVv8QYCvkA";
        String out_trade_no1		=	"1234";
        String spbill_create_ip1	= 	"211.155.91.136";
        int total_fee1			=	1;
        String trade_type1		= 	"NATIVE";
        String key1				=	"HdJYXfrd7tWNPxnoaxLBRf9eB3JYEtVU";
        
        map2.put("appid", appid1);  
        map2.put("attach", attach1);  
        map2.put("body", body1);  
        map2.put("mch_id", mch_id1);  
        map2.put("nonce_str", nonce_str1); 
        map2.put("notify_url", notify_url1);  
        map2.put("openid", openid1);  
        map2.put("out_trade_no", out_trade_no1);  
        map2.put("spbill_create_ip", spbill_create_ip1);  
        map2.put("total_fee", total_fee1);  
        map2.put("trade_type", trade_type1);  
        wxPayUtil.getUnifiedOrderSign(appid1,attach1, body1, mch_id1, nonce_str1, notify_url1,openid1,out_trade_no1, spbill_create_ip1, total_fee1,trade_type1, key1);
        wxPayUtil.getSignByMap(map2, key);
	}
}
