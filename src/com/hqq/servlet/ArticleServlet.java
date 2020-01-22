package com.hqq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.hqq.dao.ArticleDAO;
import com.hqq.dao.CatalogDAO;
import com.hqq.demo.Article;
import com.hqq.demo.Catalog;
import com.hqq.demo.PageBean;
import com.hqq.utils.changetype;

public class ArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String type = request.getParameter("type");
		if(type.endsWith("findallArticle")){
			findallArticle(request,response);
		}
		if(type.endsWith("add")){
			add(request,response);
		}
		if(type.endsWith("submit")){
			submit(request,response);
		}
		if(type.endsWith("edit")){
			edit(request,response);
		}
		if(type.endsWith("update")){
			update(request,response);
		}
		if(type.endsWith("delete")){
			delete(request,response);
		}
		if(type.endsWith("search")){
			search(request,response);
		}
	}
	
	//全查文章
	public void findallArticle(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化changetype
		changetype change = new changetype();
		int pagesize = 8;
		int maxlength = 40;
		int maxtitlelength = 10;
		//int currentpage = 1;
		String current = request.getParameter("currentpage");
		if(current == null){
			current = "1";
		}
		int currentpage = Integer.parseInt(current);
		
		ArticleDAO articleDAO = new ArticleDAO();
		
		//调用managerDAO里面的count()方法，得到总数据数
		int cou = articleDAO.count();
		
		//得到总数据数之后计算出总页数
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//将pagesize，currentpage这两个值传给PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		
		ArrayList<Article> articlelist=articleDAO.findall(pg);
		ArrayList<Catalog> cataloglist = new ArrayList<Catalog>();
		
		for (Article arti : articlelist) {
			//去除arti.getAr_content()的便标签	“仅仅”	用于显示
			String content = change.delHTMLTag(arti.getAr_content());
			String title = arti.getAr_title();
			
			//限制文章显示文字数
			if(content.length()<maxlength){
				maxlength = content.length();
			}
			//限制标题显示文字数
			if(title.length()<maxtitlelength){
				maxtitlelength = title.length();
			}
			arti.setAr_title(title.substring(0, maxtitlelength));
			arti.setAr_content(content.substring(0, maxlength));
			if(arti.getAr_state().endsWith("1"))
				arti.setAr_state("启用");
			else
				arti.setAr_state("未启用");
		}
		ArrayList<Catalog> catlist = articleDAO.all();
		request.setAttribute("catlist", catlist);
		request.setAttribute("articlelist", articlelist);
		request.setAttribute("pg", pg);
		
		String path="background/Articlelist.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//模糊查询
	public void search(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化changetype
		changetype change = new changetype();
		int pagesize = 5;
		int maxlength = 30;
		//int currentpage = 1;
		String bool = "0";
		String title = request.getParameter("ar_title");
		String current = request.getParameter("currentpage");
		if(current == null){
			current = "1";
		}
		int currentpage = Integer.parseInt(current);
		
		ArticleDAO articleDAO = new ArticleDAO();
		
		//调用managerDAO里面的count()方法，得到总数据数
		int cou = articleDAO.searchcount(title);
		
		//得到总数据数之后计算出总页数
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//将pagesize，currentpage这两个值传给PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		
		ArrayList<Article> articlelist=articleDAO.search(pg,title);
		ArrayList<Catalog> cataloglist = new ArrayList<Catalog>();
		
		for (Article arti : articlelist) {
			//去除arti.getAr_content()的便标签	“仅仅”	用于显示
			String content = change.delHTMLTag(arti.getAr_content());
			if(content.length()<maxlength){
				maxlength = content.length();
			}
			arti.setAr_content(content.substring(0, maxlength));
			if(arti.getAr_state().endsWith("1"))
				arti.setAr_state("启用");
			else
				arti.setAr_state("未启用");
		}
		ArrayList<Catalog> catlist = articleDAO.all();
		
		request.setAttribute("bool", bool);
		request.setAttribute("title", title);
		request.setAttribute("catlist", catlist);
		request.setAttribute("articlelist", articlelist);
		request.setAttribute("pg", pg);
		
		String path="background/Articlelist.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	
	//增加文章
	public void add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArrayList<Catalog> cataloglist = new ArrayList<Catalog>();
		
		ArticleDAO articleDAO = new ArticleDAO();
		Article article = new Article();
		
		
		ArrayList<Catalog>  list = articleDAO.findname();
		int max = articleDAO.maxvalue();
		if(max == 0){
			max = 0;
		}
		article.setMax(max);
		
		request.setAttribute("article", article);
		request.setAttribute("list", list);
		String path = "background/Articleadd.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//提交文章
	public void submit(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		changetype change = new changetype();
		String  caids = request.getParameter("ca_id");
		int caid = Integer.parseInt(caids);
		String  number = request.getParameter("ar_number");
		String  title = request.getParameter("ar_title");
		String  content = request.getParameter("ar_content");
		String  user = request.getParameter("ar_user");
		String  state = request.getParameter("ar_state");
		
		//获取当前时间
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//生成随机数
		Random r = new Random();
		String rs = String.valueOf(Math.abs(r.nextInt())).substring(0, 4);
		String times=new SimpleDateFormat("yyyMMdd").format(new Date()).substring(2);
		String arid = times+rs;
		Article article = new Article();
		if(title!="" && content!="" && user!=""){
			
			article.setAr_id(arid);
			article.setCa_id(caid);
			article.setAr_number(number);
			article.setAr_state(state);
			article.setAr_title(title);
			article.setAr_content(content);
			article.setAr_time(time);
			article.setAr_user(user);
			
			
		}
		
		ArticleDAO articleDAO = new ArticleDAO();
		String path = "ArticleServlet?type=add";
		if(articleDAO.subadd(article)){
			path = "ArticleServlet?type=findallArticle";
		}else{
			String bools = "0";
			request.setAttribute("bools", bools);
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	
	//编辑文章
	public void edit(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String arids = request.getParameter("arid");
		
		ArticleDAO articleDAO = new ArticleDAO();
		
		
		Article arti = articleDAO.edit(arids);
		ArrayList<Catalog> catal = articleDAO.findname();
		
		request.setAttribute("catal", catal);
		request.setAttribute("arti", arti);
		String path="background/Articleedit.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	//编辑文章
	public void update(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String arid = request.getParameter("arid");
		String ar_title = request.getParameter("ar_title");
		String ar_content = request.getParameter("ar_content");
		String ar_user = request.getParameter("ar_user");
		String ar_state = request.getParameter("ar_state");
		String ca_ids = request.getParameter("ca_id");
		int ca_id = Integer.parseInt(ca_ids);
		
		
		ArticleDAO articleDAO = new ArticleDAO();
		Article article = new Article();
		article.setAr_id(arid);
		article.setAr_title(ar_title);
		article.setAr_content(ar_content);
		article.setAr_user(ar_user);
		article.setAr_state(ar_state);
		article.setCa_id(ca_id);
		
		String path = "ArticleServlet?type=edit&arid="+arid;
		if(articleDAO.update(article)){
			path = "ArticleServlet?type=findallArticle";
		}else{
			String bool = "0";
			request.setAttribute("bool", bool);
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//删除文章
	public void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String arids = request.getParameter("id");
		int arid = Integer.parseInt(arids);
		
		ArticleDAO articleDao = new ArticleDAO();
		String path = "ArticleServlet?type=findallArticle";
		if(articleDao.delete(arid)){
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
	
}

















