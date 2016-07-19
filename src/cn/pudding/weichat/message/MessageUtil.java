package cn.pudding.weichat.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.pudding.weichat.Constant;
import cn.pudding.weichat.message.response.Article;
import cn.pudding.weichat.message.response.LzWeiBaseMsgResp;
import cn.pudding.weichat.message.response.LzWeiQiyehaoMsgResp;
import cn.pudding.weichat.message.response.LzWeiTextMsgResp;
import cn.pudding.weichat.mp.aes.AesException;
import cn.pudding.weichat.mp.aes.WXBizMsgCrypt;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {
 
    private static Logger log = Logger.getLogger(MessageUtil.class);

    /** 
     * 解析微信发来的明文请求（XML） InputStream -> map
     * @author dingjie
     * @param request 
     * @return Map<String, String>
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXmlRaw(InputStream inputStream,String encrypt_type,String msg_signature,String timestamp,String nonce,String token,String EncodingAESKey,String appId) {  
    	 
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
        try
    	{
	        // 读取输入流  
	        SAXReader reader = new SAXReader();  
	        Document document = reader.read(inputStream);
	        // 从request中取得输入流  
	        // 得到xml根元素  
	        Element root = document.getRootElement();  
	        // 得到根元素的所有子节点  
	        List<Element> elementList = root.elements();  
	        // 遍历所有子节点  
	        for (Element e : elementList)  
	            map.put(e.getName(),e.getText());  
	        // 释放资源  
	        inputStream.close();  
	        inputStream = null;  
    	}
    	catch(DocumentException e)
    	{
    		e.printStackTrace();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
        return map;  
    } 
    
    
    /** 
     * 解析微信发来的密文请求（XML） InputStream -> map
     * @author dingjie
     * @param request 
     * @return Map<String, String>
     * @throws DocumentException 
     * @throws AesException 
     * @throws IOException 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXmlAes(InputStream inputStream,String encrypt_type,String msg_signature,String timestamp,String nonce,String token,String EncodingAESKey,String appId)  {  
    	// 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
    	try
    	{
    		// 读取输入流  
    	    SAXReader reader = new SAXReader();  
    	    Document document = reader.read(inputStream);
    	    // 从request中取得输入流  
    	    String encryptStr = document.asXML();
    	    // 得到根元素的所有子节点  
    	    WXBizMsgCrypt pc = new WXBizMsgCrypt(token,EncodingAESKey, appId);
    	    encryptStr = pc.decryptMsg(msg_signature,timestamp,nonce,encryptStr);
    		document = DocumentHelper.parseText(encryptStr);
    		// 得到xml根元素  
    	    Element root = document.getRootElement();  
    	    // 得到根元素的所有子节点  
    		List elements = root.elements();
    		List<Element> elementList =  elements;
    	    for (Element e : elementList)  
    	    	map.put(e.getName(), e.getText());  
    	    // 释放资源  
    	    inputStream.close();  
    	    inputStream = null;  
    	}
    	catch(DocumentException e)
    	{
    		e.printStackTrace();
    	}
    	catch(AesException e)
    	{
    		e.printStackTrace();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
	    return map; 
    }
    
    /**
     * 加密后构成新的XML String->xml
     * @author dingjie
     * @throws DocumentException 
     * @throws AesException 
     * **/
    public static String getAesXml(String returnMsg,String username,String token,String encodingAesKey,
    		String appId,String MsgSignature,String timeStamp,String nonce) throws DocumentException, AesException
    {

    	WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		returnMsg = pc.encryptMsg(returnMsg, timeStamp, nonce);
    	return returnMsg;
    }
    

    /** 
     * 文本消息对象转换成xml 
     * @author dingjie 
     * @param  textMessage 文本消息对象 
     * @return xml 
     */  
    public static String textMessageToXml(LzWeiTextMsgResp textMessage) {  
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }
    
    
    /** 
     * 企业号文本消息对象转换成xml 
     * @author dingjie 
     * @param  textMessage 文本消息对象 
     * @return xml 
     */  
    public static String textMessageToXml(LzWeiQiyehaoMsgResp textMessage) {  
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }
    
    
    /** 
     * Object对象转换成xml 
     *  
     * @param objMessage Object对象 
     * @return xml 
     */  
    public static String objToXml(Object obj) {  
        xstream.alias("xml", obj.getClass());  
        return xstream.toXML(obj);  
    }
    
    /**
     * LzWeiBaseMsgResp 对象转换成 xml
     * @author dingjie
     * @param  baseMessage resp基础类消息对象
     * @return xml
     * @throws AesException 
     * @throws DocumentException 
     * */
    public static String baseMessageToXml(
    		LzWeiBaseMsgResp baseMessage,String encrypt_type,String token,
    		String encodingAesKey,String appId,
    		String MsgSignature,String TimeStamp ,String Nonce) 
    {  
    	String returnMsg = "";
    	try
    	{
        	xstream.alias("xml", baseMessage.getClass());  
           	if(baseMessage.getMsgType().equals(Constant.RESP_MESSAGE_TYPE_NEWS))
        	{
        		xstream.alias("item", new Article().getClass());
        	}
           	returnMsg = xstream.toXML(baseMessage);
            if(encrypt_type!=null&&encrypt_type.equals("raw"))
            {
            	returnMsg = getAesXml(returnMsg,baseMessage.getToUserName(),token,encodingAesKey,appId,MsgSignature,TimeStamp,Nonce);
            }
            return returnMsg; 
    	}
    	catch(DocumentException e)
    	{
    		LzWeiTextMsgResp textMsg = new LzWeiTextMsgResp(baseMessage);
    		textMsg.setContent("XML解析格式错误:"+e.getMessage());
    		returnMsg = xstream.toXML(textMsg);
    		return returnMsg;
    	}
    	catch(AesException e)
    	{
    		LzWeiTextMsgResp textMsg = new LzWeiTextMsgResp(baseMessage);
    		textMsg.setContent("微信解密/加密错误:"+e.getMessage()+"错误代码:"+e.getCode());
    		returnMsg = xstream.toXML(textMsg);
    		return returnMsg;
    	}
    }
        
  
    /** 
     * 扩展xstream，使其支持CDATA块 
     * @author dingjie 
     * @date 2013-05-19 
     */  
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
                @SuppressWarnings("unchecked")  
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text); 

                    }  
                }  
            };  
        }  
    }); 
    
    
    public static Map<String,String> encryptToMap(String encrypt,String token,String EncodingAESKey,String appId)
    {
    	// 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
    	try
    	{
    		WXBizMsgCrypt pc = new WXBizMsgCrypt(token,EncodingAESKey, appId);
    		String deCrypt = pc.decryptMsg(encrypt);
    		// 读取输入流  
            Document document = DocumentHelper.parseText(deCrypt);   
    		// 得到xml根元素  
    	    Element root = document.getRootElement();  
    	    // 得到根元素的所有子节点  
    		List elements = root.elements();
    		List<Element> elementList =  elements;
    	    for (Element e : elementList)  
    	    {
    	    	map.put(e.getName(), e.getText());  
    	    }
    	}
    	catch(DocumentException e)
    	{
    		e.printStackTrace();
    	}
    	catch(AesException e)
    	{
    		e.printStackTrace();
    	}
	    return map; 
    }
    
    public static void main (String[] args)
    {
    	try
    	{
    		Prepayid p = new Prepayid("1", "2", "3","4", "5", "6", "7","8", "9");
    		System.out.println(objToXml(p));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}

class Prepayid
{
	
	String return_code  ;
	String return_msg ;
	String appid ;
	String mch_id ;
	String nonce_str ;
	String prepay_id ;
	String result_code ;
	String err_code_des ;
	String sign ;
	
	public Prepayid(String returnCode1, String returnMsg, String appid,
			String mchId, String nonceStr, String prepayId, String resultCode,
			String errCodeDes, String sign) 
	{
		super();
		return_code = returnCode1;
		return_msg = returnMsg;
		this.appid = appid;
		mch_id = mchId;
		nonce_str = nonceStr;
		prepay_id = prepayId;
		result_code = resultCode;
		err_code_des = errCodeDes;
		this.sign = sign;
	}

}
