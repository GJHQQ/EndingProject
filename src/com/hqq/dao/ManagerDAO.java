package com.hqq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hqq.demo.Catalog;
import com.hqq.demo.Manager;
import com.hqq.demo.PageBean;
import com.hqq.utils.Example01;
import com.hqq.utils.JdbcUtils;



public class ManagerDAO {
	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	
	
	public ManagerDAO(){
		//conn=new Example01().ds;
		conn=new JdbcUtils().conn;
		}
	
	/**
	 * �������Ա����������
	 * @return	int���͵�count
	 */
	public int count(){
		int cou = 0;
		String sql = "select count(*) from manager where mstate not in('3')";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
			cou = rs.getInt(1);
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cou;
	}
	
	/**
	 * ��Ҫģ����ѯ�Ĺ���Ա����
	 * @return	int���͵�count
	 */
	public int countsearch(String name){
		int cou = 0;
		String sql = "select count(*) from manager where manager_name like '%"+name+"%' and mstate not in('3')";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			cou = rs.getInt(1);
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cou;
	}
	
	
	/**
	 * ����ģ��ɾ������Ա����������
	 * @return	int���͵�count
	 */
	public int countrubbish(){
		int cou = 0;
		String sql = "select count(*) from manager where mstate='3'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			cou = rs.getInt(1);
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cou;
	}
	
	/**
	 * ����ģ��ɾ������Ա����������
	 * @return	int���͵�count
	 */
	public int countsearchrubbish(String name){
		int cou = 0;
		String sql = "select count(*) from manager where manager_name like '%"+name+"%' and mstate='3'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			cou = rs.getInt(1);
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cou;
	}
	
	
	/**
	 * ����Ա�б�
	 * @return	���ؽ����
	 */
	public ArrayList<Manager> findall(PageBean pg){
		String sql = "select * from manager where mstate='1' or mstate='2' limit "+(pg.getCurrentpage()-1)*pg.getPagesize()+","+pg.getPagesize();
		
		ArrayList<Manager> managerlist=new ArrayList<Manager>();

		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager man=new Manager();
				man.setManager_id(rs.getInt(1));
				man.setManager_name(rs.getString(2));
				man.setPasswd(rs.getString(3));
				man.setMstate(rs.getString(4));
				managerlist.add(man);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return managerlist;
	}
	
	/**
	 * ����վ����Ա�б�
	 * @return	���ؽ����
	 */
	public ArrayList<Manager> rubbishlist(PageBean pg){
		String sql = "select * from manager where mstate='3' limit "+(pg.getCurrentpage()-1)*pg.getPagesize()+","+pg.getPagesize();
		
		ArrayList<Manager> managerlist=new ArrayList<Manager>();

		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager man=new Manager();
				man.setManager_id(rs.getInt(1));
				man.setManager_name(rs.getString(2));
				man.setPasswd(rs.getString(3));
				man.setMstate(rs.getString(4));
				managerlist.add(man);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return managerlist;
	}
	
	/**
	 * ģ����ѯ����Ա
	 * @return	���ؽ����
	 */
	public ArrayList<Manager> search(PageBean pg,String name){
		String sql = "select * from manager where manager_name like '%"+name+"%'  and mstate not in('3') limit "+(pg.getCurrentpage()-1)*pg.getPagesize()+","+pg.getPagesize();
		
		ArrayList<Manager> managerlist=new ArrayList<Manager>();

		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager man=new Manager();
				man.setManager_id(rs.getInt(1));
				man.setManager_name(rs.getString(2));
				man.setPasswd(rs.getString(3));
				man.setMstate(rs.getString(4));
				managerlist.add(man);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return managerlist;
	}
	
	
	/**
	 * ģ����ѯģ��ɾ���Ĺ���Ա
	 * @return	���ؽ����
	 */
	public ArrayList<Manager> searchrubbish(PageBean pg,String name){
		String sql = "select * from manager where manager_name like '%"+name+"%'  and mstate='3' limit "+(pg.getCurrentpage()-1)*pg.getPagesize()+","+pg.getPagesize();
		
		ArrayList<Manager> managerlist=new ArrayList<Manager>();

		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Manager man=new Manager();
				man.setManager_id(rs.getInt(1));
				man.setManager_name(rs.getString(2));
				man.setPasswd(rs.getString(3));
				man.setMstate(rs.getString(4));
				managerlist.add(man);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return managerlist;
	}
	
	
	
	
	/**
	 * ģ��ɾ������Ա
	 * @return	���ؽ����
	 */
	public boolean rubbish(String manager_id){
		String sql = "update manager set mstate='3' where manager_id="+manager_id;

		try {
			ps=conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				return true;		
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * �ָ�ģ��ɾ������Ա
	 * @return	���ؽ����
	 */
	public boolean recover(String manager_id){
		String sql = "update manager set mstate='2'  where manager_id="+manager_id;

		try {
			ps=conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				return true;
				
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * ��½ҳ��Servlet
	 * @param manager �û���������
	 * @return	�������ݿ��в�ѯ�Ľ����
	 */
	public Manager login(Manager manager){
		Manager man=new Manager();
		
		String sql="select * from manager where manager_name='"+manager.getManager_name()+"' and passwd='"+manager.getPasswd()+"'";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				man.setManager_id(rs.getInt(1));
				man.setManager_name(rs.getString(2));
				man.setPasswd(rs.getString(3));
				man.setMstate(rs.getString(4));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return man;
	}
	
	
	/**
	 * ��������Ա
	 * @param manager �û���-����-ȷ������
	 * @return	boolean����
	 */
	public boolean register(Manager manager){
		Manager man=new Manager();
		String pwd=manager.getPasswd();
		String oncepwd=manager.getOncepasswd();
		if(pwd.equals(oncepwd) && pwd!="" && oncepwd!="" && manager.getManager_name()!=""){
			String sql="insert into manager(manager_name,passwd,mstate) value ('"+manager.getManager_name()+"','"+manager.getPasswd()+"','"+manager.getMstate()+"' )";
			try {
				ps=conn.prepareStatement(sql);
				int a=ps.executeUpdate();
				ps.close();
				if(a>0)
					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * �༭����Ա
	 * @param id	���룬�����룬״̬
	 * @return	Manager
	 */
	public Manager lookup(int id){
		Manager man=new Manager();
		String sql="select * from manager where manager_id="+id/*man.getManager_id()*/;
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				man.setManager_id(rs.getInt(1));
				man.setManager_name(rs.getString(2));
				man.setPasswd(rs.getString(3));
				man.setMstate(rs.getString(4));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return man;
	}
	
	
	/**
	 * ������ĺ����Ա����Ϣ
	 * @param manager ���롣�����룬״̬
	 * @return  	true ���� false
	 */
	public boolean change(Manager manager){
		String sql="update manager set passwd='"+manager.getPasswd()+"',mstate='"+manager.getMstate()+"' where manager_id='"+manager.getManager_id()+"'";
		try {
			ps=conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0){
				return true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ɾ���û�
	 * @param manager �û�id
	 * @return	�ɹ�����true ʧ�ܷ���false
	 */
	public boolean delete(String ids){
		String sql="delete from manager where manager_id="+ids;
		
		try {
			ps=conn.prepareStatement(sql);
			int a=ps.executeUpdate();
			if(a>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ����û����Ƿ�ʹ��
	 * @param name	input�� ���û�ֵ
	 * @return Booleanֵ  true��ʹ��	falseδ��ʹ��
	 */
	public boolean checkusername(String name){
		
		String sql="select * from manager where manager_name='"+name+"'";
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				return true;
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}









