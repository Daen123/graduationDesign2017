package com.tuition.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *JDBCͨ��dao 
 * 1.��ȡ����
 * 2.ͨ�õ���ɾ�Ĳ鷽��
 * 3.�ͷ�����
 * @author	ZhangXiaoLe
* */
public class BaseDao {
	//mysql���Ӳ���
	private static final String URL="jdbc:mysql://localhost:3306/db_collegeTuitionManageSys";
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String USER="root";
	private static final String PASSWORD="root";
	
	//��ȡ���Ӷ���
	public static Connection getConn(){
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} 
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		return conn;
	}
	
	//�ر����Ӷ���
	public static void closeAll(Connection conn,Statement stmt,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	//�ر����Ӷ���
	public static void closeAll(Connection conn,Statement stmt){
		try {
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	/*
	 * ͨ�õ���ɾ�ķ���
	 * return ��Ӱ�������
	 * */
	public static int executeUpdate(String sql,Object[] params){
		//�õ����Ӷ���
		Connection conn=null;
		PreparedStatement stmt=null;
		
		//��Ӱ�������
		int rows=0;
		try {
			conn=getConn();
			stmt=conn.prepareStatement(sql);
			if(stmt!=null){
				for(int i=0;i<params.length;i++){
					stmt.setObject(i+1, params[i]);
				}
				
			}
			System.out.println(params.toString());
			rows=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt);
		}
		return rows;
	}
	
	/*
	 * ͨ�õĲ�ѯ��������װ��List<T>
	 * return ��ѯ�Ľ��
	 * */
	public static <T> List<T> find(String sql,Object[] params,Class<T> clazz){
		//�õ����Ӷ���
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		//��������
		List<T> list=new ArrayList<T>();
		try {
			conn=getConn();
			stmt=conn.prepareStatement(sql);
			//System.out.println(stmt);
			if(stmt!=null){
				for(int i=0;i<params.length;i++){
					stmt.setObject(i+1, params[i]);
				}
			}
			//�õ������
			//System.out.println(stmt);
			rs=stmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			//���������
			while(rs.next()){
				T obj=clazz.newInstance();
				
				//�õ������������
				for(int i=0;i<rsmd.getColumnCount();i++){
					//�õ�����
					String colName=rsmd.getColumnName(i+1);
					//System.out.println(colName);
					//����Ŵ�1��ʼ���õ�ÿһ�е�ֵ
					Object value=rs.getObject(colName);
					//�����ֺ�ֵ��ֵ��ֵ��������
					Field field=clazz.getDeclaredField(colName);
					field.setAccessible(true);
					field.set(obj, value);
				}
				//��ӵ�������
				list.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}
		return list;
		
	}
}
