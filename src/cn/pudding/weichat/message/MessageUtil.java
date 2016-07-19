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
     * ����΢�ŷ�������������XML�� InputStream -> map
     * @author dingjie
     * @param request 
     * @return Map<String, String>
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXmlRaw(InputStream inputStream,String encrypt_type,String msg_signature,String timestamp,String nonce,String token,String EncodingAESKey,String appId) {  
    	 
        // ����������洢��HashMap��  
        Map<String, String> map = new HashMap<String, String>();  
        try
    	{
	        // ��ȡ������  
	        SAXReader reader = new SAXReader();  
	        Document document = reader.read(inputStream);
	        // ��request��ȡ��������  
	        // �õ�xml��Ԫ��  
	        Element root = document.getRootElement();  
	        // �õ���Ԫ�ص������ӽڵ�  
	        List<Element> elementList = root.elements();  
	        // ���������ӽڵ�  
	        for (Element e : elementList)  
	            map.put(e.getName(),e.getText());  
	        // �ͷ���Դ  
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
     * ����΢�ŷ�������������XML�� InputStream -> map
     * @author dingjie
     * @param request 
     * @return Map<String, String>
     * @throws DocumentException 
     * @throws AesException 
     * @throws IOException 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXmlAes(InputStream inputStream,String encrypt_type,String msg_signature,String timestamp,String nonce,String token,String EncodingAESKey,String appId)  {  
    	// ����������洢��HashMap��  
        Map<String, String> map = new HashMap<String, String>();  
    	try
    	{
    		// ��ȡ������  
    	    SAXReader reader = new SAXReader();  
    	    Document document = reader.read(inputStream);
    	    // ��request��ȡ��������  
    	    String encryptStr = document.asXML();
    	    // �õ���Ԫ�ص������ӽڵ�  
    	    WXBizMsgCrypt pc = new WXBizMsgCrypt(token,EncodingAESKey, appId);
    	    encryptStr = pc.decryptMsg(msg_signature,timestamp,nonce,encryptStr);
    		document = DocumentHelper.parseText(encryptStr);
    		// �õ�xml��Ԫ��  
    	    Element root = document.getRootElement();  
    	    // �õ���Ԫ�ص������ӽڵ�  
    		List elements = root.elements();
    		List<Element> elementList =  elements;
    	    for (Element e : elementList)  
    	    	map.put(e.getName(), e.getText());  
    	    // �ͷ���Դ  
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
     * ���ܺ󹹳��µ�XML String->xml
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
     * �ı���Ϣ����ת����xml 
     * @author dingjie 
     * @param  textMessage �ı���Ϣ���� 
     * @return xml 
     */  
    public static String textMessageToXml(LzWeiTextMsgResp textMessage) {  
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }
    
    
    /** 
     * ��ҵ���ı���Ϣ����ת����xml 
     * @author dingjie 
     * @param  textMessage �ı���Ϣ���� 
     * @return xml 
     */  
    public static String textMessageToXml(LzWeiQiyehaoMsgResp textMessage) {  
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }
    
    
    /** 
     * Object����ת����xml 
     *  
     * @param objMessage Object���� 
     * @return xml 
     */  
    public static String objToXml(Object obj) {  
        xstream.alias("xml", obj.getClass());  
        return xstream.toXML(obj);  
    }
    
    /**
     * LzWeiBaseMsgResp ����ת���� xml
     * @author dingjie
     * @param  baseMessage resp��������Ϣ����
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
    		textMsg.setContent("XML������ʽ����:"+e.getMessage());
    		returnMsg = xstream.toXML(textMsg);
    		return returnMsg;
    	}
    	catch(AesException e)
    	{
    		LzWeiTextMsgResp textMsg = new LzWeiTextMsgResp(baseMessage);
    		textMsg.setContent("΢�Ž���/���ܴ���:"+e.getMessage()+"�������:"+e.getCode());
    		returnMsg = xstream.toXML(textMsg);
    		return returnMsg;
    	}
    }
        
  
    /** 
     * ��չxstream��ʹ��֧��CDATA�� 
     * @author dingjie 
     * @date 2013-05-19 
     */  
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // ������xml�ڵ��ת��������CDATA���  
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
    	// ����������洢��HashMap��  
        Map<String, String> map = new HashMap<String, String>();  
    	try
    	{
    		WXBizMsgCrypt pc = new WXBizMsgCrypt(token,EncodingAESKey, appId);
    		String deCrypt = pc.decryptMsg(encrypt);
    		// ��ȡ������  
            Document document = DocumentHelper.parseText(deCrypt);   
    		// �õ�xml��Ԫ��  
    	    Element root = document.getRootElement();  
    	    // �õ���Ԫ�ص������ӽڵ�  
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
