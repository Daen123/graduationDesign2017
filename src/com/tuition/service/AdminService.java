package com.tuition.service;

import java.util.ArrayList;
import java.util.List;

import com.tuition.dao.AdminDao;
import com.tuition.model.Condition;
import com.tuition.model.CostDetail;
import com.tuition.model.CostItem;
import com.tuition.model.StatisticsItem;
import com.tuition.model.User;

public class AdminService {
	AdminDao adminDao=new AdminDao();
	
	//��ӷ�������
	public int addCost(User user,List<CostDetail> feeList,String amin,String payDur){
		try {
			//���ѧ��ѧ�Ų�Ϊ0
			if(!user.getJobNum().equals("0")){
				//ֱ�Ӳ���ѧ��
				addSingleStu(user, feeList,amin,payDur);
			}else{
				//System.out.println("ѧ��ѧ��Ϊ0");
				//����༶��Ϊ0
				if(!user.getClassName().equals("0")){
					//ִ�в��������༶���ѧ��
					List<User> userList=adminDao.getClassUser(user);
					for(User userItem:userList){
						addSingleStu(userItem, feeList,amin,payDur);
					}
					
				}else{
					//���רҵ��Ϊ0
					if(!user.getMajorName().equals("0")){
						//ִ�в�������רҵ��ѧ��
						List<User> userList=adminDao.getMajorUser(user);
						for(User userItem:userList){
							addSingleStu(userItem, feeList,amin,payDur);
						}
					}else{
						//���ѧԺ��Ϊ0
						if(!user.getAcademyName().equals("0")){
							//ִ�в�������ѧԺ��ѧ��
							List<User> userList=adminDao.getAcademyUser(user);
							for(User userItem:userList){
								addSingleStu(userItem, feeList,amin,payDur);
							}
						}else{
							//ִ�в�������ѧУ��ѧ��
							List<User> userList=adminDao.getGradeUser(user);
							for(User userItem:userList){
								addSingleStu(userItem, feeList,amin,payDur);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
		return 1;
	}
	
	//��ӵ���ѧ��
	public void addSingleStu(User user,List<CostDetail> feeList,String amin,String payDur){
		for(CostDetail cd:feeList){
			adminDao.addSingleStu(user, cd,amin,payDur);
		}
	}
	
	//�޸�
	public int edit(String jobNum,int id,String reduceFee,java.sql.Date date){
		return adminDao.edit(jobNum,id, reduceFee,date);
	}
	//ɾ��
	public int delete(String jobNum,String stuNum,java.sql.Date date){
		return adminDao.delete(jobNum,stuNum,date);
	}
	//ͳ��
	public List<StatisticsItem> statistics(Condition condition,String flag){
		List<CostItem> list=adminDao.statistics(condition);
		List<StatisticsItem> returnList=null;
		if(flag.equals("1")){
			returnList=toStatistics(list);
		}
		if(flag.equals("2")){
			returnList=toStatistics2(list);
		}
		return returnList;
	}
	
	//�������ݸ�ʽ---���շ���
	public List<StatisticsItem> toStatistics(List<CostItem> list){
		StatisticsItem item1=new StatisticsItem("ѧ��");
		StatisticsItem item2=new StatisticsItem("ס�޷�");
		StatisticsItem item3=new StatisticsItem("����");
		StatisticsItem item4=new StatisticsItem("ҽ�Ʊ���");
		StatisticsItem item5=new StatisticsItem("���޷�");
		StatisticsItem item6=new StatisticsItem("������");
		StatisticsItem item7=new StatisticsItem("����");
		List<StatisticsItem> list2=new ArrayList<StatisticsItem>();
		list2.add(item1);list2.add(item2);list2.add(item3);list2.add(item4);
		list2.add(item5);list2.add(item6);list2.add(item7);
		//����ѭ��
		for(CostItem item:list){
			switch(item.getFeeName()){
				case "ѧ��":
					if(item.getOweFee().equals("0")){
						//System.out.println("Ƿ��");
						item1.getOwer().add(item);
					}else{
						//System.out.println("��Ƿ��");
						item1.getUnOwer().add(item);	
					}
					break;
				case "ס�޷�":
					if(item.getOweFee().equals("0")){
						
						item2.getOwer().add(item);
					}else{
						item2.getUnOwer().add(item);
					}
					break;
				case "����":
					if(item.getOweFee().equals("0")){
						item3.getOwer().add(item);
					}else{
						item3.getUnOwer().add(item);
					}
					break;
				case "ҽ�Ʊ���":
					if(item.getOweFee().equals("0")){
						item4.getOwer().add(item);
					}else{
						item4.getUnOwer().add(item);
					}
					break;
				case "���޷�":
					if(item.getOweFee().equals("0")){
						item5.getOwer().add(item);
					}else{
						item5.getUnOwer().add(item);
					}
					break;
				case "������":
					if(item.getOweFee().equals("0")){
						item6.getOwer().add(item);
					}else{
						item6.getUnOwer().add(item);
					}
					break;
				default:
					if(item.getOweFee().equals("0")){
						item7.getOwer().add(item);
					}else{
						item7.getUnOwer().add(item);
					}
					break;
			}
		}
		return list2;
	}
	
	//�������ݸ�ʽ---���շ���
		public List<StatisticsItem> toStatistics2(List<CostItem> list){
			StatisticsItem item1=new StatisticsItem("���Ͽ�ѧ�빤��ѧԺ");
			StatisticsItem item2=new StatisticsItem("�������ͨ��ѧԺ");
			StatisticsItem item3=new StatisticsItem("������������Ϣ����ѧԺ");
			StatisticsItem item4=new StatisticsItem("���繤��ѧԺ");
			StatisticsItem item5=new StatisticsItem("��ѧԺ");
			StatisticsItem item6=new StatisticsItem("������ѧ�빤��ѧԺ");
			StatisticsItem item7=new StatisticsItem("�������ѧԺ");
			StatisticsItem item8=new StatisticsItem("��ѧԺ����ʽ���ѧԺ");
			StatisticsItem item9=new StatisticsItem("��Դ�붯������ѧԺ");
			StatisticsItem item10=new StatisticsItem("ʯ�ͻ���ѧԺ");
			StatisticsItem item11=new StatisticsItem("��ľ����ѧԺ");
			StatisticsItem item12=new StatisticsItem("���ù���ѧԺ");
			StatisticsItem item13=new StatisticsItem("�����ѧԺ");
			List<StatisticsItem> list2=new ArrayList<StatisticsItem>();
			list2.add(item1);list2.add(item2);list2.add(item3);list2.add(item4);
			list2.add(item5);list2.add(item6);list2.add(item7);list2.add(item8);
			list2.add(item9);list2.add(item10);list2.add(item11);list2.add(item12);
			list2.add(item13);
			
			//����ѭ��
			for(CostItem item:list){
				switch(item.getAcademyName()){
					case "���Ͽ�ѧ�빤��ѧԺ":
						if(item.getOweFee().equals("0")){
							item1.getOwer().add(item);
						}else{
							item1.getUnOwer().add(item);	
						}
						break;
					case "�������ͨ��ѧԺ":
						if(item.getOweFee().equals("0")){
							
							item2.getOwer().add(item);
						}else{
							item2.getUnOwer().add(item);
						}
						break;
					case "������������Ϣ����ѧԺ":
						if(item.getOweFee().equals("0")){
							item3.getOwer().add(item);
						}else{
							item3.getUnOwer().add(item);
						}
						break;
					case "���繤��ѧԺ":
						if(item.getOweFee().equals("0")){
							item4.getOwer().add(item);
						}else{
							item4.getUnOwer().add(item);
						}
						break;
					case "��ѧԺ":
						if(item.getOweFee().equals("0")){
							item5.getOwer().add(item);
						}else{
							item5.getUnOwer().add(item);
						}
						break;
					case "������ѧ�빤��ѧԺ":
						if(item.getOweFee().equals("0")){
							item6.getOwer().add(item);
						}else{
							item6.getUnOwer().add(item);
						}
						break;
					case "�������ѧԺ":
						if(item.getOweFee().equals("0")){
							item7.getOwer().add(item);
						}else{
							item7.getUnOwer().add(item);	
						}
						break;
					case "��ѧԺ����ʽ���ѧԺ":
						if(item.getOweFee().equals("0")){
							
							item8.getOwer().add(item);
						}else{
							item8.getUnOwer().add(item);
						}
						break;
					case "��Դ�붯������ѧԺ":
						if(item.getOweFee().equals("0")){
							item9.getOwer().add(item);
						}else{
							item9.getUnOwer().add(item);
						}
						break;
					case "���ù���ѧԺ":
						if(item.getOweFee().equals("0")){
							item10.getOwer().add(item);
						}else{
							item10.getUnOwer().add(item);
						}
						break;
					case "��ľ����ѧԺ":
						if(item.getOweFee().equals("0")){
							item11.getOwer().add(item);
						}else{
							item11.getUnOwer().add(item);
						}
						break;
					case "ʯ�ͻ���ѧԺ":
						if(item.getOweFee().equals("0")){
							item12.getOwer().add(item);
						}else{
							item12.getUnOwer().add(item);
						}
						break;
					case "�����ѧԺ":
						if(item.getOweFee().equals("0")){
							item13.getOwer().add(item);
						}else{
							item13.getUnOwer().add(item);
						}
						break;
				}
			}
			return list2;
		}
	//����������ѯѧ���ɷ����
	public List<CostItem> getCostList(List<Condition> conditionList){
		return adminDao.getCostList(conditionList);
	}
	
	//���Ӳ���
	public int addPlay(String jobNum,String content,java.sql.Date date){
		return adminDao.addPlay(jobNum,content,date);
	}
}
