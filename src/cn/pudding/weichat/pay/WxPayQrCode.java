package cn.pudding.weichat.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.pudding.weichat.qrcode.QrCodeUtil;

public class WxPayQrCode {

	private String appid;		//公众账号ID wx8888888888888888 	微信分配的公众账号ID
	private String mch_id;		//商户号 微信支付分配的商户号
	private String time_stamp;	//时间戳 标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。 
	private String nonce_str;	//随机字符串  随机字符串，不长于32位。 
	private String product_id;	//商品ID 商户定义的商品id 或者订单号
	private String sign;		//签名  详见签名生成算法
	private String key;			//密钥
	private String codeUrl;		//二维码内容 url
	private byte[] codeBytes; 	//用于输出的付款二维码
	
	private static String wxQrCodeUrl =  "weixin://wxpay/bizpayurl?sign=SIGN&appid=APPID&mch_id=MCHID&product_id=PRODUCTID&time_stamp=TIMESTAMP&nonce_str=NONCE";
	private static Logger log = Logger.getLogger(WxPayQrCode.class);
	
	public WxPayQrCode(String appid, String mchId, String timeStamp,String nonceStr, String productId, String sign) 
	{
		super();
		this.appid = appid;
		mch_id = mchId;
		time_stamp = timeStamp;
		nonce_str = nonceStr;
		product_id = productId;
		this.sign = sign;
	}
	
	public WxPayQrCode() {
		super();
	}
	
	public WxPayQrCode(String appid, String mchId,String key,String productId) {
		super();
		this.appid = appid;
		mch_id = mchId;
		this.key = key;
		time_stamp = this.getTimeStamp();
		nonce_str = this.getNonceStr();
		product_id = productId;
		sign = this.createSign();
	}
	
	public String getTimeStamp()
	{
		Date now = new Date();
		long timestampMillSecond = now.getTime();
		Integer timestampSecond = (int)(timestampMillSecond/1000);
		return timestampSecond.toString();
	}
	
	public String getNonceStr()
	{
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/** 
     * 微信支付签名算法sign 
     * @param characterEncoding 
     * @param parameters 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public String createSign()
    {  
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("appid", appid);  
    	map.put("mch_id", mch_id);  
    	map.put("product_id", product_id);  
    	map.put("time_stamp", time_stamp);  
    	map.put("nonce_str", nonce_str);
    	sign = wxPayUtil.getSignByMap(map, key);
        return sign;  
    }  
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mchId) {
		mch_id = mchId;
	}
	
	public String getTime_stamp() {
		return time_stamp;
	}
	
	public void setTime_stamp(String timeStamp) {
		time_stamp = timeStamp;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	
	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}
	
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String productId) {
		product_id = productId;
	}
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

	public byte[] getCodeBytes() {
		return codeBytes;
	}

	public void setCodeBytes(byte[] codeBytes) {
		this.codeBytes = codeBytes;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCodeUrl() {
		
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	
	public void setCodeUrl()
	{
		StringBuilder message = new StringBuilder();
		//判断各参数的非空状态
		if( null == this.sign || 0 == this.sign.length())
		{
			message.append("sign为空! ");
		}
		if(null == this.appid || 0 == this.appid.length())
		{
			message.append(" appid为空!");
		}
		if(null == this.mch_id || 0 == this.mch_id.length())	
		{
			message.append(" mch_id为空!");
		}
		if(null == this.product_id || 0 == this.product_id.length())	
		{
			message.append(" product_id为空!");
		}
		if(	null == this.time_stamp || 0 == this.time_stamp.length())
		{
			message.append(" time_stamp为空!");
		}
		if(	null == this.nonce_str || 0 == this.nonce_str.length())
		{
			message.append(" nonce_str为空!");
		}
		if(message.length()>0)
		{
			this.codeUrl = null;
			log.error(message);
		}
		else
		{
			String url = wxQrCodeUrl
			.replace("SIGN", 		this.sign)
			.replace("APPID", 		this.appid)
			.replace("MCHID", 		this.mch_id)
			.replace("PRODUCTID", 	this.product_id)
			.replace("TIMESTAMP", 	this.time_stamp)
			.replace("NONCE", 		this.nonce_str);
			this.codeUrl = url;
		}
		
	}

	public void buildBytes()
	{
		setCodeUrl();
		String codeUrl = getCodeUrl();
		if(null==codeUrl || 0 == codeUrl.length())
		{
			this.codeBytes = null;
		}
		else
		{
			this.codeBytes = QrCodeUtil.createQRCode(codeUrl);
		}
		
	}
	
	
	public static void main(String[] args)
	{
		String appid		="wx2ba0ce17184b93b7";
		String mchId		="1237186802";
		String key			="abcdefghigklmn";
		String productId 	="100010";
		WxPayQrCode code 	= new WxPayQrCode(appid,mchId,key,productId);
		code.setCodeUrl();
		System.out.println(code.getCodeUrl());
		
	}
		 	
}
