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
			System.out.println("-----���ݿ����ӳɹ�-----");
			
			//ִ��SQL���
			String sql="select * from manager "/*where margin_id=1*/;
			PreparedStatement ps=conn.prepareStatement(sql);
			//����SQL���
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
			//rs.next();
			System.out.print("manager_id="+rs.getInt(1));
			System.out.print("\tmanager_name="+rs.getString(2));
			System.out.print("\tmanager_passwd="+rs.getString(3));
			System.out.print("\tmanager_mastate="+rs.getString(4));
			System.out.println();
			}
			
			
			/*//ִ��SQL���
			String sql="insert into manager(manager_name,passwd,mstate) value ('zhagnsan','123456','1' )";
			PreparedStatement ps=conn.prepareStatement(sql);
			//����SQL���
			int a=ps.executeUpdate();
			if(a>0){
				System.out.println("-----�ɹ�����"+a+"������-----");
			}
			else{
				System.out.println("-----δ���в���-----");
			}*/
			
			/*//ִ��SQL���
			String sql="delete from manager where manager_id=2";
			PreparedStatement ps=conn.prepareStatement(sql);
			//����SQL���
			int a=ps.executeUpdate();
			if(a>0){
				System.out.println("-----�ɹ�ɾ��"+a+"����¼-----");
			}
			else{
				System.out.println("-----δ���в���-----");
			}*/
		} catch (Exception e) {
			System.out.println("-----���ݿ�����ʧ��-----");
			e.printStackTrace();
		} 
	}
	

}





////ִ��SQL���
//String sql="select * from manager where manager_id=1";
//PreparedStatement ps=conn.prepareStatement(sql);
////����SQL���
//ResultSet rs=ps.executeQuery();
//rs.next();
//System.out.print("manager_id="+rs.getInt(1));
//System.out.print("��manager_name="+rs.getString(2));
//System.out.print("��passwd="+rs.getString(3));
//System.out.print("��masete="+rs.getString(4));
//rs.close();
//ps.close();

/*//ִ��sql���
String sql="insert into manager(manager_name,passwd,mstate) value ('zhagnsan','123456','1' )";
PreparedStatement ps=conn.prepareStatement(sql);
			
int a=ps.executeUpdate();
if(a>0){
System.out.println("-----�����ɹ�-----");
System.out.println(a+"���ܵ�Ӱ��");
}
else{
	System.out.println("-----����ʧ��-----");
}*/


/*//ִ��SQL���
			String sql="delete from manager where manager_id=8";
			PreparedStatement ps=conn.prepareStatement(sql);

			//����SQL���
			int a=ps.executeUpdate();
			if(a>0){
				System.out.println("�����ɹ�");
			}
			else{
				System.out.println("����δִ��");
			}*/


