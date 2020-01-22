package com.hqq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hqq.demo.Article;
import com.hqq.demo.Catalog;
import com.hqq.demo.PageBean;
import com.hqq.utils.JdbcUtils;

public class ArticleDAO {
	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	public ArticleDAO(){
		conn=new JdbcUtils().conn;
	}
	
	/**
	 * 文章的总数据数
	 * @return	int类型的count
	 */
	public int count(){
		int cou = 0;
		String sql = "select count(*) from article";
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
	 * 模糊查询文章的总数据数
	 * @return	int类型的count
	 */
	public int searchcount(String title){
		int cou = 0;
		String sql = "select count(*) from article where ar_title like '%"+title+"%'";
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
	 * 文章列表
	 * @param pg
	 * @return	返回结果集
	 */
	public ArrayList<Article> findall(PageBean pg){
		ArrayList<Article> art = new ArrayList<Article>();
		String sql = "select * from article order by ar_number+0 desc limit "+(pg.getCurrentpage()-1)*pg.getPagesize()+","+pg.getPagesize();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Article article = new Article();
				article.setAr_id(rs.getString(1));
				article.setCa_id(rs.getInt(2));
				article.setAr_number(rs.getString(3));
				article.setAr_title(rs.getString(4));
				article.setAr_image(rs.getString(5));
				article.setAr_content(rs.getString(6));
				article.setAr_user(rs.getString(7));
				article.setAr_time(rs.getString(8));
				article.setAr_state(rs.getString(9));
				article.setClicks(rs.getInt(10));
				art.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return art;
	}
	
	/**
	 * 模糊查询文章列表
	 * @param pg
	 * @return	返回结果集
	 */
	public ArrayList<Article> search(PageBean pg,String title){
		ArrayList<Article> art = new ArrayList<Article>();
		String sql = "select * from article where ar_title like '%"+title+"%' order by ar_number+0 desc limit "+(pg.getCurrentpage()-1)*pg.getPagesize()+","+pg.getPagesize();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Article article = new Article();
				article.setAr_id(rs.getString(1));
				article.setCa_id(rs.getInt(2));
				article.setAr_number(rs.getString(3));
				article.setAr_title(rs.getString(4));
				article.setAr_image(rs.getString(5));
				article.setAr_content(rs.getString(6));
				article.setAr_user(rs.getString(7));
				article.setAr_time(rs.getString(8));
				article.setAr_state(rs.getString(9));
				article.setClicks(rs.getInt(10));
				art.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return art;
	}
	
	/**
	 * 
	 * @return	返回结果集
	 */
	public ArrayList<Catalog> all(){
		String sql = "select * from catalog ";
		
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
	 * 搜索栏目名字
	 * @return	返回结果集
	 */
	public ArrayList<Catalog> findname(){
		ArrayList<Catalog> cataloglist = new ArrayList<Catalog>();
		String sql = "select * from catalog where ca_state='1'";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Catalog cata = new Catalog();
				cata.setCa_id(rs.getInt(1));
				cata.setCa_name(rs.getString(2));
				cata.setCa_number(rs.getInt(3));
				cata.setCa_state(rs.getString(4));
				cataloglist.add(cata);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cataloglist;
	}
	
	/**
	 * 查询ar_number列的最大值
	 * @return	最大值
	 */
	public int maxvalue(){
		int max = 0;
		String sql = "select max(ar_number+0) from article";
		
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
	 * 添加文章
	 * @param article
	 * @return	返回布尔值
	 */
	public boolean subadd(Article article){
		String sql = "insert into article(ar_id,ca_id,ar_number,ar_title,ar_image,ar_content,ar_user,ar_time,ar_state,clicks) value ('"+
										  article.getAr_id()+"',"+
										  article.getCa_id()+",'"+
										  article.getAr_number()+"','"+
										  article.getAr_title()+"',"+
										  article.getAr_image()+",'"+
										  article.getAr_content()+"','"+
										  article.getAr_user()+"','"+
										  article.getAr_time()+"','"+
										  article.getAr_state()+"',"+
										  article.getClicks()+")";
		try {
			ps = conn.prepareStatement(sql);
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
	 * 编辑文章
	 * @param arid
	 * @return
	 */
	public Article edit(String arid){
		Article article = new Article();
		String sql = "select * from article where ar_id="+arid;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				article.setAr_id(rs.getString(1));
				article.setCa_id(rs.getInt(2));
				article.setAr_number(rs.getString(3));
				article.setAr_title(rs.getString(4));
				article.setAr_image(rs.getString(5));
				article.setAr_content(rs.getString(6));
				article.setAr_user(rs.getString(7));
				article.setAr_time(rs.getString(8));
				article.setAr_state(rs.getString(9));
				article.setClicks(rs.getInt(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}
	
	/**
	 * 提交编辑
	 * @param article类
	 * @return	true or false
	 */
	public boolean update(Article article){
		String sql = "update article set ar_title='"+article.getAr_title()+
					 "',ar_content='"+article.getAr_content()+
					 "',ar_user='"+article.getAr_user()+
					 "',ar_state='"+article.getAr_state()+
					 "',ca_id='"+article.getCa_id()+
					 "' where ar_id='"+article.getAr_id()+"'";
		try {
			ps = conn.prepareStatement(sql);
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
	 * 删除文章
	 * @param arid文章ID
	 * @return	返回true or false
	 */
	public boolean delete(int arid){
		String sql="delete from article where ar_id="+arid;
		try {
			ps = conn.prepareStatement(sql);
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











