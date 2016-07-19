package cn.pudding.weichat.mp.aes;
/**
 * �Թ���ƽ̨���͸������˺ŵ���Ϣ�ӽ���ʾ������.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;

/**
 * SHA1 class
 *
 * ���㹫��ƽ̨����Ϣǩ���ӿ�.
 */
public class SHA1 {

	/**
	 * ��SHA1�㷨���ɰ�ȫǩ��
	 * @param token Ʊ��
	 * @param timestamp ʱ���
	 * @param nonce ����ַ���
	 * @param encrypt ����
	 * @return ��ȫǩ��
	 * @throws AesException 
	 */
	public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException
			  {
		try {
			String[] array = new String[] { token, timestamp, nonce, encrypt };
			StringBuffer sb = new StringBuffer();
			// �ַ�������
			Arrays.sort(array);
			for (int i = 0; i < 4; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			
			// SHA1ǩ������
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ComputeSignatureError);
		}
	}
	
	
	public static String getJsSignatureString1(String jsApiTicketStr,String nonceStr,String timestamp,String url)
	{
		String string1 = "jsapi_ticket=TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
		return string1.replace("TICKET", jsApiTicketStr).replace("NONCESTR", nonceStr).replace("TIMESTAMP", timestamp).replace("URL", url);
	}
	
	/**
	 * ��SHA1�㷨���ɰ�ȫǩ��
	 * @param token Ʊ��
	 * @param timestamp ʱ���
	 * @param nonce ����ַ���
	 * @param encrypt ����
	 * @return ��ȫǩ��
	 * @throws AesException 
	 */
	public static String getJsSignatureSHA1(String string1) throws AesException
	{
		try 
		{
			
			//System.out.println("�ֵ����:"+str);
			String str = string1.toString();
			// SHA1ǩ������
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();
			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ComputeSignatureError);
		}
	}
	
	/**
	 * ��SHA1�㷨���ɰ�ȫǩ��
	 * @param array ����
	 * @return ��ȫǩ��
	 * @throws AesException 
	 */
	public static String getSHA1(String[] array) throws AesException
	{
		try {
			StringBuffer sb = new StringBuffer();
			// �ַ�������
			Arrays.sort(array);
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1ǩ������
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ComputeSignatureError);
		}
	}
	
	public static void  main(String[] args)
	{
		try
		{
			
			///��ҵ�Ŵ�������signature:a9b8996b5666ca26860126fc7820dba4fd5de552
			//��ҵ�Ŵ�������echostr:a9b8996b5666ca26860126fc7820dba4fd5de552
			//��ҵ�Ŵ�������timestamp:1443513184
			//��ҵ�Ŵ�������nonce:1365193601
			//�ص��������������signature(digest):cf309213032afc46bba03124f0a0d2b3afcc2b15
			/**
			��ҵ�Ŵ�������signature:81d0775203d10c10193cb05fb7991e3733a82d0c
			��ҵ�Ŵ�������echostr:IJ5/KDXWBXe3Fs58h19hAw3fbXcq9u5zUs9ek//rM7/uCF+zypXkHRdUAR0OGVLHPbENMMKQFh3z8XldkfTxog==
			��ҵ�Ŵ�������timestamp:1443513853
			��ҵ�Ŵ�������nonce:1134471514
			�ص��������������signature(digest):447aeb85d69a3f05ad394b5fbf9905245ac1c8fd
			**/
			
			System.out.println(getSHA1("sdlongzhong", "1443513853", "1134471514", "nGGjEl0Ta4KPVmqA92MyNBGKQU9tDxQlOBIE5sZqYJJ91xvqDXHF2/lMCbTAsKJ7WAdJfgWKpVYAGiPWWA485Q=="));//�����������AccessToken 
			
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
