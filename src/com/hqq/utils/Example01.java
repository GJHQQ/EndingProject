package com.hqq.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.activation.DataSource;
import java.sql.DatabaseMetaData;
import org.apache.commons.dbcp2.BasicDataSource;

public class Example01 {
	public static BasicDataSource ds = null;
	static{
		//获取DBCP数据源实现类对象
		BasicDataSource dbs = new BasicDataSource();
		//设置连接数据库需要的配置信息
		dbs.setDriverClassName("com.mysql.jdbc.Driver");
		dbs.setUrl("jdbc:mysql://localhost:3306/endingproject_sql");
		dbs.setUsername("root");
		dbs.setPassword("");
		//设置连接池的参数
		dbs.setInitialSize(5);
		dbs.setMaxTotal(5);
		ds = dbs;
	}
	
	public static void main(String[] args) throws SQLException {
		//获取数据库连接对象
		Connection conn = ds.getConnection();
		//获取数据库连接信息
		DatabaseMetaData metaData = conn.getMetaData();
		//打印数据库信息
		System.out.println(metaData.getURL()+":username="+metaData.getUserName()+":"+metaData.getDriverName());

	}

}
