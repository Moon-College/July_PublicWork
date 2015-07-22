package com.tz.fourth.second;

/**第四节课第二次作业，需要创建一个类似tablelayout的界面，用ListView实现的
 * 这个是ListView里存储每行item里的信息用的结构体；
 * @author JDY
 *
 */
public class ItemInfo {
	private String field1;
	private String field2;
	private String field3;
	public ItemInfo(String field1,String field2,String field3){
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
}
