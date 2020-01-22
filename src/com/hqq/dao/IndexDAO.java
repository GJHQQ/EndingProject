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

public class IndexDAO {
	public Connection conn=null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public IndexDAO(){
		conn = new JdbcUtils().conn;
	}
	
	/**
	 * 查询文章总数(根据序号排序，最新发表)
	 * @return	结果集
	 */
	public ArrayList<Article> findarticle(){
		ArrayList<Article> articles = new ArrayList<Article>();
		
		String sql = "select * from article	where ar_state='1' and ca_id in (select ca_id from catalog where ca_state='1') order by ar_number+0 desc limit 0,8";
		
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
				articles.add(article);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return articles;
	}
	
	/**
	 * 查询文章总数(根据点击数排序，点击数最多)
	 * @return	结果集
	 */
	public ArrayList<Article> maxclicks(){
		ArrayList<Article> articles = new ArrayList<Article>();
		
		String sql = "select * from article	where ar_state='1' and ca_id in (select ca_id from catalog where ca_state='1') order by clicks desc limit 0,6";
		
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
				articles.add(article);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return articles;
	}
	
	/**
	 * 栏目列表
	 * @param 
	 * @return	返回结果集
	 */
	public ArrayList<Catalog> findallcatalog(){
		String sql = "select * from catalog where ca_state='1'";
		
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
	 * 显示文章
	 * @param arid
	 * @return
	 */
	public Article lookup(String arid){
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
	 * 更新点击数
	 * @param click 点击之前点击数
	 * @param arid	文章id
	 * @return	true or false
	 */
	public boolean clicks(int click,String arid){
		String sql = "update article set clicks="+click+" where ar_id='"+arid+"'";
		
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
