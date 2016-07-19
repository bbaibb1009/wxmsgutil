package cn.pudding.weichat.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


public class HttpUtil {

	private static Logger log = Logger.getLogger(HttpUtil.class);
	
	/** 
     * ����https���󲢻�ȡ��� 
     * @param  requestUrl 		�����ַ 
     * @param  requestMethod 	����ʽ��GET��POST�� 
     * @param  outputStr 		�ύ������ 
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ) 
     */
	public static JSONObject httpRequestJson(String requestUrl,String requestMethod,String outputStr)
	{
		try
		{
			JSONObject jsonObj = JSONObject.fromObject(httpRequest(requestUrl,requestMethod,outputStr));
			return  jsonObj;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/** 
     * ����https���󲢻�ȡ��� 
     * @param  requestUrl 		�����ַ 
     * @param  requestMethod 	����ʽ��GET��POST�� 
     * @param  outputStr 		�ύ������ 
     * @return strObject 		�����ַ���
     */
	public static String httpRequest(String requestUrl,String requestMethod,String outputStr)
	{
		String strObj = null;
		StringBuffer buffer = new StringBuffer();
		try
		{
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // ������SSLContext�����еõ�SSLSocketFactory����  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // ��������ʽ��GET/POST��  
            httpUrlConn.setRequestMethod(requestMethod);  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // ����������Ҫ�ύʱ  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // ע������ʽ����ֹ��������  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
            // �����ص�������ת�����ַ���  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) 
            {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            //�ͷ���Դ  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            strObj = buffer.toString();  
		}
		catch (ConnectException ce) 
		{  
	        log.error("Weixin server connection timed out.");  
	    } 
		catch (Exception e) {  
	        log.error("https request error:"+e.getMessage());  
	    }
		return strObj;
	}
}