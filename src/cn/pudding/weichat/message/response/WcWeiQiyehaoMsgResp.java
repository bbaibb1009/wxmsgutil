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

	// ���շ��ʺţ��յ���OpenID��  
    private String Encrypt;  
    // ������΢�ź�  
    private String MsgSignature;  
    // ��Ϣ����ʱ�� �����ͣ�  
    private long TimeStamp;  
    // ��Ϣ���ͣ�text/music/news��  
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
