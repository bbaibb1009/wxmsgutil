//:ComplexButton.java
package cn.pudding.weichat.menu;
/** 
 * 按钮组
 * @author dingj 
 * @date 2014-12-06 
 */  
public class ComplexButton extends Button {

	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}  
	
}
//:~
