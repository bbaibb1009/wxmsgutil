package cn.pudding.weichat.message.response;
/**
 * 	<xml>
   		<Encrypt><![CDATA[msg_encrypt]]></Encrypt>
   		<MsgSignature><![CDATA[msg_signature]]></MsgSignature>
   		<TimeStamp>timestamp</TimeStamp>
   		<Nonce><![CDATA[nonce]]></Nonce>
	</xml>
 * 
 * */
public class WcWeiQiyehaoMsgResp {

	// 接收方帐号（收到的OpenID）  
    private String Encrypt;  
    // 开发者微信号  
    private String MsgSignature;  
    // 消息创建时间 （整型）  
    private long TimeStamp;  
    // 消息类型（text/music/news）  
    private String Nonce;
    
	public String getEncrypt() {
		return Encrypt;
	}
	public void setEncrypt(String encrypt) {
		Encrypt = encrypt;
	}
	public String getMsgSignature() {
		return MsgSignature;
	}
	public void setMsgSignature(String msgSignature) {
		MsgSignature = msgSignature;
	}
	public long getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		TimeStamp = timeStamp;
	}
	public String getNonce() {
		return Nonce;
	}
	public void setNonce(String nonce) {
		Nonce = nonce;
	} 
    
    

}
