package com.tuition.service;

import java.util.List;

import com.tuition.dao.UserDao;
import com.tuition.model.User;

public class UserService {
	//dao��--user
	private UserDao userDao=new UserDao();

	//��¼
	public	List<User> login(User user){
		return userDao.login(user);
	}
	
	
	//���������ж��û��Ƿ����
	public List<User> isExistUser(User user){
		return userDao.isExistUser(user);
	}
	
	//�޸�����
	public int changePWD(User user){
		return userDao.changePWD(user);
	}
	
	//��¼��־
	public void addLog(String jobNum,String ip,java.sql.Date date,int flag){
		userDao.addLog(jobNum,ip,date,flag);
	}
}
