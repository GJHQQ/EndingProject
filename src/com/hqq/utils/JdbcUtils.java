package com.hqq.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtils {
	String Driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/endingproject_sql";
	String name="root";
	String passwd="";
	public Connection conn;
	public JdbcUtils(){
		try {
			Class.forName(Driver).newInstance();
			conn=DriverManager.getConnection(url, name, passwd);
			System.out.println("-----���ݿ����ӳɹ�-----");
		} catch (Exception e) {
			System.out.println("-----���ݿ�����ʧ��-----");
			e.printStackTrace();
		} 
	}
}
