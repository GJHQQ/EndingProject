package com.hqq.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConnectTset {
	public static void main(String[] args) {
		String Driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/endingproject_sql";
		String name="root";
		String passwd="";
		
		try {
			Class.forName(Driver).newInstance();
			Connection conn=DriverManager.getConnection(url, name, passwd);
			System.out.println("-----数据库连接成功-----");
			
			//执行SQL语句
			String sql="select * from manager "/*where margin_id=1*/;
			PreparedStatement ps=conn.prepareStatement(sql);
			//返回SQL语句
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
			//rs.next();
			System.out.print("manager_id="+rs.getInt(1));
			System.out.print("\tmanager_name="+rs.getString(2));
			System.out.print("\tmanager_passwd="+rs.getString(3));
			System.out.print("\tmanager_mastate="+rs.getString(4));
			System.out.println();
			}
			
			
			/*//执行SQL语句
			String sql="insert into manager(manager_name,passwd,mstate) value ('zhagnsan','123456','1' )";
			PreparedStatement ps=conn.prepareStatement(sql);
			//返回SQL语句
			int a=ps.executeUpdate();
			if(a>0){
				System.out.println("-----成功插入"+a+"条数据-----");
			}
			else{
				System.out.println("-----未进行操作-----");
			}*/
			
			/*//执行SQL语句
			String sql="delete from manager where manager_id=2";
			PreparedStatement ps=conn.prepareStatement(sql);
			//返回SQL语句
			int a=ps.executeUpdate();
			if(a>0){
				System.out.println("-----成功删除"+a+"条记录-----");
			}
			else{
				System.out.println("-----未进行操作-----");
			}*/
		} catch (Exception e) {
			System.out.println("-----数据库连接失败-----");
			e.printStackTrace();
		} 
	}
	

}





////执行SQL语句
//String sql="select * from manager where manager_id=1";
//PreparedStatement ps=conn.prepareStatement(sql);
////返回SQL语句
//ResultSet rs=ps.executeQuery();
//rs.next();
//System.out.print("manager_id="+rs.getInt(1));
//System.out.print("、manager_name="+rs.getString(2));
//System.out.print("、passwd="+rs.getString(3));
//System.out.print("、masete="+rs.getString(4));
//rs.close();
//ps.close();

/*//执行sql语句
String sql="insert into manager(manager_name,passwd,mstate) value ('zhagnsan','123456','1' )";
PreparedStatement ps=conn.prepareStatement(sql);
			
int a=ps.executeUpdate();
if(a>0){
System.out.println("-----操作成功-----");
System.out.println(a+"行受到影响");
}
else{
	System.out.println("-----操作失败-----");
}*/


/*//执行SQL语句
			String sql="delete from manager where manager_id=8";
			PreparedStatement ps=conn.prepareStatement(sql);

			//返回SQL语句
			int a=ps.executeUpdate();
			if(a>0){
				System.out.println("操作成功");
			}
			else{
				System.out.println("操作未执行");
			}*/


