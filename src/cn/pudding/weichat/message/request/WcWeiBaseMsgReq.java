package cn.pudding.weichat.message.request;

public class WcWeiBaseMsgReq {
	
	// ������΢�ź�   
	private String toUserName;  
	// ���ͷ��ʺţ�һ��OpenID��   
	private String fromUserName;  
	// ��Ϣ����ʱ�� �����ͣ�   
	private long createTime;  
	// ��Ϣ���ͣ�text/image/location/link��   
	private String msgType;  
	// ��Ϣid��64λ����   
	private long msgId;
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	
	
	
	
}
