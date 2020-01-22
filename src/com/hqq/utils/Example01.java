package com.hqq.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.activation.DataSource;
import java.sql.DatabaseMetaData;
import org.apache.commons.dbcp2.BasicDataSource;

public class Example01 {
	public static BasicDataSource ds = null;
	static{
		//��ȡDBCP����Դʵ�������
		BasicDataSource dbs = new BasicDataSource();
		//�����������ݿ���Ҫ��������Ϣ
		dbs.setDriverClassName("com.mysql.jdbc.Driver");
		dbs.setUrl("jdbc:mysql://localhost:3306/endingproject_sql");
		dbs.setUsername("root");
		dbs.setPassword("");
		//�������ӳصĲ���
		dbs.setInitialSize(5);
		dbs.setMaxTotal(5);
		ds = dbs;
	}
	
	public static void main(String[] args) throws SQLException {
		//��ȡ���ݿ����Ӷ���
		Connection conn = ds.getConnection();
		//��ȡ���ݿ�������Ϣ
		DatabaseMetaData metaData = conn.getMetaData();
		//��ӡ���ݿ���Ϣ
		System.out.println(metaData.getURL()+":username="+metaData.getUserName()+":"+metaData.getDriverName());

	}

}
