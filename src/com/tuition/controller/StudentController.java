package com.tuition.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.tuition.model.Condition;
import com.tuition.model.CostItem;
import com.tuition.model.User;
import com.tuition.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	StudentService studentService=new StudentService();

	//ѧ���ɷ���ϸ
	@RequestMapping("/costList")
	public void costList(HttpServletRequest request,HttpServletResponse response){
		Gson gson=new Gson();
		//System.out.println("����ѧ���Ĳ�ѯ");
		//Ҫ��ѯ���û�
		User currentUser=(User)request.getSession().getAttribute("currentUser");
		//User currentUser=new User();
		currentUser.setJobNum(currentUser.getJobNum());
		currentUser.setFlag(1);
		
		//��ѯ����
		String conditionStr=request.getParameter("conditionStr");
		Condition condition=gson.fromJson(conditionStr,Condition.class );
		
		//���ܲ�ѯ���
		List<CostItem> costItemList = null;
		
		//��ѯ���
		if(currentUser.getFlag()==1){
			costItemList=studentService.getCostList(currentUser,condition);
		}
		try {
			responseJson(response,costItemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ɷ�
	@RequestMapping("/pay")
	public void pay(HttpServletRequest request,HttpServletResponse response){
		//�ɷ���Ϣ
		User user=(User)request.getSession().getAttribute("currentUser");
		CostItem costItem=new CostItem();
		costItem.setAlreadyFee(request.getParameter("payNum"));//���
		costItem.setFeeName(request.getParameter("feeName"));//��������
		costItem.setPayDur(request.getParameter("payDur"));//��һѧ��
		costItem.setStuNum(user.getJobNum());//ѧ��ѧ��
		
		try {
			responseJson(response,studentService.pay(costItem));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//���json
			private void responseJson(HttpServletResponse response,Object result) throws IOException{
				response.setCharacterEncoding("UTF-8");
				Gson gson=new Gson();
				String resultJson=gson.toJson(result);
				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.close();
			}
}
