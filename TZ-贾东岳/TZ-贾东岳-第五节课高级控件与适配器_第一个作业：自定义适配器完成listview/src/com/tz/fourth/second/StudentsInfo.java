package com.tz.fourth.second;


/**第四节课第二次作业，需要创建一个类似tablelayout的界面，用ListView实现的
 * 这个是ListView里存储每行item里的信息用的结构体；
 * @author JDY
 *
 */
public class StudentsInfo {
//	头像+网名+性别+颜值+爱好
	private int touXiangR;
	private String wangMing;
	
	private String xingBie;
	private String yanZhi;
	private String aiHao;
	
	public StudentsInfo(int touXiangR,String wangMing,
			String xingBie,String yanZhi,String aiHao){
		super();
		this.touXiangR = touXiangR;
		this.wangMing = wangMing;
		this.xingBie = xingBie;
		this.yanZhi = yanZhi;
		this.aiHao = aiHao;
	}

	public int getTouXiangR() {
		return touXiangR;
	}

	public void setTouXiangR(int touXiangR) {
		this.touXiangR = touXiangR;
	}
	public String getWangMing() {
		return wangMing;
	}

	public void setWangMing(String wangMing) {
		this.wangMing = wangMing;
	}

	public String getXingBie() {
		return xingBie;
	}

	public void setXingBie(String xingBie) {
		this.xingBie = xingBie;
	}

	public String getYanZhi() {
		return yanZhi;
	}

	public void setYanZhi(String yanZhi) {
		this.yanZhi = yanZhi;
	}

	public String getAiHao() {
		return aiHao;
	}

	public void setAiHao(String aiHao) {
		this.aiHao = aiHao;
	}

}
