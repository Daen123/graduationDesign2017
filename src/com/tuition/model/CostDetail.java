package com.tuition.model;

public class CostDetail {
	private int flag;//���ñ��
	private String feeName;//��������
	private String feeNum;//���ý��
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public String getFeeNum() {
		return feeNum;
	}
	public void setFeeNum(String feeNum) {
		this.feeNum = feeNum;
	}
	@Override
	public String toString() {
		return "CostDetail [flag=" + flag + ", feeName=" + feeName
				+ ", feeNum=" + feeNum + "]";
	}
	
	
}
