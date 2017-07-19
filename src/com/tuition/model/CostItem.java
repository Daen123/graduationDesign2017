package com.tuition.model;

import java.sql.Timestamp;

public class CostItem {
	private int id;
	private String gradeName;//�꼶
	private String academyName;//ѧԺ
	private String majorName;//רҵ
	private String className;//�༶
	private String userName;//����
	private String stuNum;//ѧ��ѧ��
	private String adminNum;//����Ա����
	private String payDur;//�ɷ����
	private String feeName;//��������
	private String shouldFee;//Ӧ�ɷ���
	private String alreadyFee;//�ѽɷ���
	private String reduceFee;//�������
	private String returnFee;//�˻�����
	private	String oweFee;//Ƿ��
	private Timestamp payTime;//�ɷ�ʱ��
	private Timestamp createTime;//����ʱ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public String getShouldFee() {
		return shouldFee;
	}
	public void setShouldFee(String shouldFee) {
		this.shouldFee = shouldFee;
	}
	public String getAlreadyFee() {
		return alreadyFee;
	}
	public void setAlreadyFee(String alreadyFee) {
		this.alreadyFee = alreadyFee;
	}
	public String getReduceFee() {
		return reduceFee;
	}
	public void setReduceFee(String reduceFee) {
		this.reduceFee = reduceFee;
	}
	public String getReturnFee() {
		return returnFee;
	}
	public void setReturnFee(String returnFee) {
		this.returnFee = returnFee;
	}
	public String getOweFee() {
		return oweFee;
	}
	public void setOweFee(String oweFee) {
		this.oweFee = oweFee;
	}
	public Timestamp getPayTime() {
		return payTime;
	}
	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}
	
	public String getPayDur() {
		return payDur;
	}
	public void setPayDur(String payDur) {
		this.payDur = payDur;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getAcademyName() {
		return academyName;
	}
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "CostItem [id=" + id + ", gradeName=" + gradeName
				+ ", academyName=" + academyName + ", majorName=" + majorName
				+ ", className=" + className + ", userName=" + userName
				+ ", stuNum=" + stuNum + ", adminNum=" + adminNum + ", payDur="
				+ payDur + ", feeName=" + feeName + ", shouldFee=" + shouldFee
				+ ", alreadyFee=" + alreadyFee + ", reduceFee=" + reduceFee
				+ ", returnFee=" + returnFee + ", oweFee=" + oweFee
				+ ", payTime=" + payTime + ", createTime=" + createTime + "]";
	}
	
	
	
	
}
