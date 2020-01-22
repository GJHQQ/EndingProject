package com.hqq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqq.dao.CatalogDAO;
import com.hqq.dao.IndexDAO;
import com.hqq.demo.Article;
import com.hqq.demo.Catalog;
import com.hqq.utils.changetype;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String type = request.getParameter("type");
		if(type == null)type="";
		
		if(type == ""){
			index(request,response);
		}
		if(type.endsWith("articlecontent")){
			articlecontent(request,response);			
		}
	
		
	}
	
	
	//首页显示
	public void index(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int length = 101;
		String str = null;
		//实例化类
		IndexDAO indexDao = new IndexDAO();
		Article art = new Article();
		changetype types = new changetype();
		
		
		ArrayList<Article> article =indexDao.findarticle();
		ArrayList<Article> arclicks =indexDao.maxclicks();
		ArrayList<Catalog> cata =indexDao.findallcatalog();
		
		
		
		
		for(Article artlist : article){
			//去除样式
			String content = types.delHTMLTag(artlist.getAr_content());
			//获取标题
			String title = artlist.getAr_title();
			//获取文章去除样式之后的长度
			int maxlength = content.length();
			//获取标题长度
			int titlelength = title.length();
			
			if(titlelength>20){
				titlelength = 20;
				artlist.setAr_title(title.substring(0,20));
			}
			
			if(maxlength<length){
				for(int i=0;i<(length-maxlength-1);i++){
					content+="   ";
				}
				artlist.setAr_content(content.substring(0, length));
			}
			
			//判断文章长度是否>length  (100)
			if(maxlength>length){
				maxlength = length;
				//截取文章长度
				artlist.setAr_content(content.substring(0, length));
			}
		}
		
		request.setAttribute("cata", cata);
		request.setAttribute("article", article);
		//点击排行
		request.setAttribute("arclicks", arclicks);
		
		String path = "index_boke.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//查看文章,根据ID 查找文章
	public void articlecontent(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			String arid = request.getParameter("arid");
			
			IndexDAO indexDao = new IndexDAO();
			
			Article article = indexDao.lookup(arid);
			ArrayList<Catalog> catalog = indexDao.findallcatalog();
			ArrayList<Article> arclicks =indexDao.maxclicks();
			ArrayList<Article> articles =indexDao.findarticle();
			int click = article.getClicks()+1;
			
			
			
			
			String path = "background/articles.jsp";
			if(article == null&indexDao.clicks(click, arid)){
				String bool = "0";
				request.setAttribute("bool", bool);
			}
			
			request.setAttribute("arclicks", arclicks);
			request.setAttribute("catalog", catalog);
			request.setAttribute("article", article);
			
			request.setAttribute("articles", articles);
			
			request.getRequestDispatcher(path).forward(request, response);
			
			
			
		
	}
	
	
	
	
	

}
