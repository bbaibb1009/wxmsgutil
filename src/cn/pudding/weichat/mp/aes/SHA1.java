package cn.pudding.weichat.mp.aes;
/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
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
 * 计算公众平台的消息签名接口.
 */
public class SHA1 {

	/**
	 * 用SHA1算法生成安全签名
	 * @param token 票据
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @param encrypt 密文
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException
			  {
		try {
			String[] array = new String[] { token, timestamp, nonce, encrypt };
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			Arrays.sort(array);
			for (int i = 0; i < 4; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			
			// SHA1签名生成
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
	 * 用SHA1算法生成安全签名
	 * @param token 票据
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @param encrypt 密文
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getJsSignatureSHA1(String string1) throws AesException
	{
		try 
		{
			
			//System.out.println("字典序后:"+str);
			String str = string1.toString();
			// SHA1签名生成
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
	 * 用SHA1算法生成安全签名
	 * @param array 数组
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getSHA1(String[] array) throws AesException
	{
		try {
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			Arrays.sort(array);
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1签名生成
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
			
			///企业号传过来的signature:a9b8996b5666ca26860126fc7820dba4fd5de552
			//企业号传过来的echostr:a9b8996b5666ca26860126fc7820dba4fd5de552
			//企业号传过来的timestamp:1443513184
			//企业号传过来的nonce:1365193601
			//回调函数计算出来的signature(digest):cf309213032afc46bba03124f0a0d2b3afcc2b15
			/**
			企业号传过来的signature:81d0775203d10c10193cb05fb7991e3733a82d0c
			企业号传过来的echostr:IJ5/KDXWBXe3Fs58h19hAw3fbXcq9u5zUs9ek//rM7/uCF+zypXkHRdUAR0OGVLHPbENMMKQFh3z8XldkfTxog==
			企业号传过来的timestamp:1443513853
			企业号传过来的nonce:1134471514
			回调函数计算出来的signature(digest):447aeb85d69a3f05ad394b5fbf9905245ac1c8fd
			**/
			
			System.out.println(getSHA1("sdlongzhong", "1443513853", "1134471514", "nGGjEl0Ta4KPVmqA92MyNBGKQU9tDxQlOBIE5sZqYJJ91xvqDXHF2/lMCbTAsKJ7WAdJfgWKpVYAGiPWWA485Q=="));//这个可以生成AccessToken 
			
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
