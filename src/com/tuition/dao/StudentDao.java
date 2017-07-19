package com.tuition.dao;

import java.util.ArrayList;
import java.util.List;

import com.tuition.model.Condition;
import com.tuition.model.CostItem;
import com.tuition.model.User;

public class StudentDao {

	private static final List<Object> StringBuilder = null;

	//��ѯ�ɷ���ϸ
	public List<CostItem> getCostList(User user,Condition condition){
		StringBuilder sb=new StringBuilder();
		sb.append("select id,payDur,feeName,shouldFee,alreadyFee,reduceFee,returnFee,oweFee,payTime ");
		sb.append("from cost_detail ");
		sb.append(" where stuNum=? ");
		Object[] params=null;
		
		//��ѯ����
		if(condition.getConName().equals("feeName")){
			sb.append(" and feeName=?");
			params=new Object[]{user.getJobNum(),condition.getConValue()};
		}else if(condition.getConName().equals("payDur")){
			sb.append(" and payDur=?");
			params=new Object[]{user.getJobNum(),condition.getConValue()};
		}else if(condition.getConName().equals("oweFee")){
			sb.append(" and oweFee!='0'");
			params=new Object[]{user.getJobNum()};
		}else{
			params=new Object[]{user.getJobNum()};
		}
		
		Class<?> clazz=null;
		try {
			clazz=Class.forName("com.tuition.model.CostItem");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (List<CostItem>) BaseDao.find(sb.toString(), params, clazz);
	}
	
	//�ɷ�
	public int pay(CostItem costItem){
		//Ӧ�ɷ��úͼ���
		
		CostItem ci=getFeeNum(costItem);
		int currentAlreadyFee=Integer.parseInt(ci.getAlreadyFee())+Integer.parseInt(costItem.getAlreadyFee());
		int result=Integer.parseInt(ci.getShouldFee())-Integer.parseInt(ci.getReduceFee())-currentAlreadyFee;
		
		List<Object> paramsList=new ArrayList<Object>();
		StringBuilder sb=new StringBuilder();
		sb.append("update cost_detail set alreadyFee=?,payTime=?");
		paramsList.add(String.valueOf(currentAlreadyFee));
		paramsList.add(new java.sql.Date(new java.util.Date().getTime()));
		
		//���������Ӧ�ɷ���<��Ҫ����
		if(result<0){
			sb.append(",returnFee=?,oweFee='0'");
			paramsList.add(Math.abs(result));
		}else if(result>0){
			sb.append(",returnFee='0',oweFee=?");
			paramsList.add(result);
		}else{
			sb.append(",returnFee='0',oweFee='0'");
		}
		sb.append(" where stuNum=? and feeName=? and payDur=?");
		paramsList.add(costItem.getStuNum());
		paramsList.add(costItem.getFeeName());
		paramsList.add(costItem.getPayDur());
		Object[] params=paramsList.toArray();
		
		//��ӽɷѼ�¼
		String sql="insert into cost_record(jobNum,feeName,feeNum,createTime) values(?,?,?,?)";
		Object[] params2=new Object[]{costItem.getStuNum(),costItem.getFeeName(),costItem.getAlreadyFee(),new java.sql.Date(new java.util.Date().getTime())};
		BaseDao.executeUpdate(sql, params2);
		
		return BaseDao.executeUpdate(sb.toString(), params);
	}
	
	//�Ȳ�ѯӦ�ɷ��úͼ������
	public CostItem getFeeNum(CostItem costItem){
		
		String sql="select shouldFee,alreadyFee,reduceFee from cost_detail where stuNum=? and feeName=? and payDur=?";
		Object[] params =new Object[]{costItem.getStuNum(),costItem.getFeeName(),costItem.getPayDur()};
		Class<?> clazz=null;
		try {
			clazz=Class.forName("com.tuition.model.CostItem");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (CostItem) BaseDao.find(sql, params, clazz).get(0);
	}
}
