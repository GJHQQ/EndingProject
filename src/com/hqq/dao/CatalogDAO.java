package com.hqq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hqq.demo.Catalog;
import com.hqq.demo.Manager;
import com.hqq.demo.PageBean;
import com.hqq.utils.JdbcUtils;

public class CatalogDAO {
	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	public CatalogDAO(){
		conn=new JdbcUtils().conn;
	}
	/**
	 * �������Ա����������
	 * @return	int���͵�count
	 */
	public int count(){
		int cou = 0;
		String sql = "select count(*) from catalog";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			cou = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cou;
	}
	
	/**
	 * ģ����ѯ���µ���������
	 * @return	int���͵�count
	 */
	public int countsearch(String name){
		int cou = 0;
		String sql = "select count(*) from catalog where ca_name like '%"+name+"%'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			cou = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cou;
	}
	
	/**
	 * �����б�
	 * @return	���ؽ����
	 */
	public ArrayList<Catalog> findallcatalog(PageBean pg){
		String sql = "select * from catalog order by ca_number+0 desc limit "+(pg.getCurrentpage()-1)*pg.getPagesize()+","+pg.getPagesize();
		
		ArrayList<Catalog> catalist=new ArrayList<Catalog>();

		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Catalog cata=new Catalog();
				cata.setCa_id(rs.getInt(1));
				cata.setCa_name(rs.getString(2));
				cata.setCa_number(rs.getInt(3));
				cata.setCa_state(rs.getString(4));
				catalist.add(cata);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return catalist;
	}
	
	/**
	 * ģ����ѯ
	 * @return	���ؽ����
	 */
	public ArrayList<Catalog> search(PageBean pg,String name){
		String sql = "select * from catalog where ca_name like '%"+name+"%' order by ca_number+0 desc limit "+(pg.getCurrentpage()-1)*pg.getPagesize()+","+pg.getPagesize();
		
		ArrayList<Catalog> catalist=new ArrayList<Catalog>();

		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Catalog cata=new Catalog();
				cata.setCa_id(rs.getInt(1));
				cata.setCa_name(rs.getString(2));
				cata.setCa_number(rs.getInt(3));
				cata.setCa_state(rs.getString(4));
				catalist.add(cata);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return catalist;
	}
	
	/**
	 * ������Ŀ
	 * @param manager ��Ŀ��-��Ŀ���-��Ŀ״̬
	 * @return	boolean����
	 */
	public boolean register(Catalog cata){
		
		String sql="insert into catalog(ca_name,ca_number,ca_state) value ('"+cata.getCa_name()+"','"+cata.getCa_number()+"','"+cata.getCa_state()+"' )";
			
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
	 * ��ѯca_number�е����ֵ
	 * @return	���ֵ
	 */
	public int maxvalue(){
		int max = 0;
		String sql = "select max(ca_number+0) from catalog";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			max = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max;
	}
	
	/**
	 * ɾ���û�
	 * @param catalog �û�id
	 * @return	�ɹ�����true ʧ�ܷ���false
	 */
	public boolean delete(Catalog cata){
		String sql="delete from catalog where ca_id="+cata.getCa_id();
		
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
	 * ��дҳ����Ϣ
	 * @param id
	 * @return
	 */
	public Catalog catalogedit(int id){
		Catalog catalog = new Catalog();
		String sql="select * from catalog where ca_id="+id;
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				
				catalog.setCa_id(rs.getInt(1));
				catalog.setCa_name(rs.getString(2));
				catalog.setCa_number(rs.getInt(3));
				catalog.setCa_state(rs.getString(4));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catalog;
	}
	
	/**
	 * �ύ���µı�
	 * @param catalog 
	 * @return	����true �� false
	 */
	public boolean catalogupdate(Catalog catalog){
		String sql="update catalog set ca_name='"+
		   catalog.getCa_name()+"',ca_number='"+
		   catalog.getCa_number()+"',ca_state='"+
		   catalog.getCa_state()+"'  where  ca_id="+
		   catalog.getCa_id();
		
		try {
			ps=conn.prepareStatement(sql);
			int a = ps.executeUpdate();
			if(a>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * �ҵ��������ca_number����С��
	 * @param ca_number
	 * @return	��ѯ���
	 */
	public String renumber(String num){
		String sql = "select ca_number from catalog where (ca_number+0)>'"+num+"' order by ca_number+0 asc limit 1";
		try {
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ������Ŀ
	 * @param catalog  ��Ŀ���	
	 * @return	����boolean
	 */
	public boolean moveup(int number,int renumber){
				String sql = "update   catalog as cata1 join catalog as cata2 on (cata1.ca_number="+renumber+" and cata2.ca_number="+number+") or(cata1.ca_number="+number+" and cata2.ca_number="+renumber+") set cata2.ca_number=cata1.ca_number;";
				try {
					ps=conn.prepareStatement(sql);
					int a = ps.executeUpdate();
					if(a>0){
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return false;
	}
	
	/**
	 * �ҵ�С�����ca_number�������
	 * @param ca_number
	 * @return	��ѯ���
	 */
	public String nenumber(String num){
		String sql = "select ca_number from catalog where (ca_number+0)<'"+num+"' order by ca_number+0 desc limit 1";
		try {
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ������Ŀ
	 * @param catalog  ��Ŀ���	
	 * @return	����boolean
	 */
	public boolean movedown(int number,int nenumber){
				String sql = "update   catalog as cata1 join catalog as cata2 on (cata1.ca_number="+nenumber+" and cata2.ca_number="+number+") or(cata1.ca_number="+number+" and cata2.ca_number="+nenumber+") set cata2.ca_number=cata1.ca_number;";
				try {
					ps=conn.prepareStatement(sql);
					int a = ps.executeUpdate();
					if(a>0){
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return false;
	}
	
}





