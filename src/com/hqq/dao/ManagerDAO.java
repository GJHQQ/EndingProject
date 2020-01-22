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
	 * 计算管理员的总数据数
	 * @return	int类型的count
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
	 * 需要模糊查询的管理员总数
	 * @return	int类型的count
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
	 * 计算模拟删除管理员的总数据数
	 * @return	int类型的count
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
	 * 计算模拟删除管理员的总数据数
	 * @return	int类型的count
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
	 * 管理员列表
	 * @return	返回结果集
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
	 * 回收站管理员列表
	 * @return	返回结果集
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
	 * 模糊查询管理员
	 * @return	返回结果集
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
	 * 模糊查询模拟删除的管理员
	 * @return	返回结果集
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
	 * 模拟删除管理员
	 * @return	返回结果集
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
	 * 恢复模拟删除管理员
	 * @return	返回结果集
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
	 * 登陆页面Servlet
	 * @param manager 用户名，密码
	 * @return	返回数据库中查询的结果集
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
	 * 新增管理员
	 * @param manager 用户名-密码-确认密码
	 * @return	boolean类型
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
	 * 编辑管理员
	 * @param id	密码，新密码，状态
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
	 * 保存更改后管理员的信息
	 * @param manager 密码。新密码，状态
	 * @return  	true 或者 false
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
	 * 删除用户
	 * @param manager 用户id
	 * @return	成功返回true 失败返回false
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
	 * 检查用户名是否被使用
	 * @param name	input中 的用户值
	 * @return Boolean值  true被使用	false未被使用
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









