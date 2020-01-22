package com.hqq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hqq.dao.CatalogDAO;
import com.hqq.dao.ManagerDAO;
import com.hqq.demo.Catalog;
import com.hqq.demo.Manager;
import com.hqq.demo.PageBean;

public class CatalogServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		String xmlpath = "com/hqq/utils/Beans.xml";
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);
		
		String type=request.getParameter("type");
		if(type.endsWith("findallcatalog")){
			findallcatalog(request,response);
		}
		if(type.endsWith("register")){
			register(request,response);
		}
		if(type.endsWith("max")){
			max(request,response);
		}
		if(type.endsWith("delete")){
			delete(request,response);
		}
		if(type.endsWith("catalogedit")){
			catalogedit(request,response);
		}
		if(type.endsWith("catalogupdate")){
			catalogupdate(request,response);
		}
		if(type.endsWith("moveup")){
			moveup(request,response);
		}
		if(type.endsWith("movedown")){
			movedown(request,response);
		}
		if(type.endsWith("search")){
			search(request,response);
		}
	}
	
	//��Ŀȫ��
	public void findallcatalog(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pagesize = 5;
		//int currentpage = 1;
		String current = request.getParameter("currentpage");
		if(current == null){
			current = "1";
		}
		int currentpage = Integer.parseInt(current);
		
		CatalogDAO catalogDAO = new CatalogDAO();
		
		//����managerDAO�����count()�������õ���������
		int cou = catalogDAO.count();
		
		//�õ���������֮��������ҳ��
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//��pagesize��currentpage������ֵ����PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Catalog> cataloglist=catalogDAO.findallcatalog(pg);
		
		for (Catalog cata : cataloglist) {
			if(cata.getCa_state().endsWith("1"))
				cata.setCa_state("����");
			else
				cata.setCa_state("δ����");
		}
		
		request.setAttribute("cataloglist", cataloglist);
		request.setAttribute("pg", pg);
		
		String path="background/cataloglist.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//ģ����ѯ
	public void search(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pagesize = 5;
		//int currentpage = 1;
		String names = request.getParameter("ca_name");
		String current = request.getParameter("currentpage");
		if(current == null){
			current = "1";
		}
		int currentpage = Integer.parseInt(current);
		
		CatalogDAO catalogDAO = new CatalogDAO();
		
		//����managerDAO�����count()�������õ���������
		int cou = catalogDAO.countsearch(names);
		
		//�õ���������֮��������ҳ��
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//��pagesize��currentpage������ֵ����PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Catalog> cataloglist=catalogDAO.search(pg,names);
		
		for (Catalog cata : cataloglist) {
			if(cata.getCa_state().endsWith("1"))
				cata.setCa_state("����");
			else
				cata.setCa_state("δ����");
		}
		
		String bool = "0";
		
		request.setAttribute("bool", bool);
		//���ش�input��������������ڷ�ҳ
		request.setAttribute("names", names);
		request.setAttribute("cataloglist", cataloglist);
		request.setAttribute("pg", pg);
		
		String path="background/cataloglist.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//�õ����ca_number�е����ֵ
	public void max(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		CatalogDAO  catalogDAO = new CatalogDAO();
		int max = catalogDAO.maxvalue();
		if(max == 0){
			max = 0;
		}
		Catalog cata = new Catalog();
		cata.setMax(max);
		
		request.setAttribute("cata", cata);
		
		String path = "background/catalogadd.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//������Ŀ
	public void register(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��jsp��ȡ�û���������
		String name=request.getParameter("cataname");
		String nums=request.getParameter("catanum");
		String state=request.getParameter("catastate");
		Catalog cata=new Catalog();
		
		
		if(name != "" && nums != ""){
			int num = Integer.parseInt(nums);
			cata.setCa_name(name);
			cata.setCa_number(num);
			cata.setCa_state(state);
		}
	
		String path="CatalogServlet?type=max";
		
		CatalogDAO catalogDAO=new CatalogDAO();
		if(catalogDAO.register(cata)){
			path="CatalogServlet?type=findallcatalog";
			
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String ids=request.getParameter("id");
		int id=Integer.parseInt(ids);
		
		Catalog cata = new Catalog();
		cata.setCa_id(id);
		
		CatalogDAO catalogDAO = new CatalogDAO();
		if(catalogDAO.delete(cata)){
			String path="CatalogServlet?type=findallcatalog";
			request.getRequestDispatcher(path).forward(request, response);
		}
		
	}
	
	//�༭��Ŀ������
	public void catalogedit(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String ids = request.getParameter("id");
		int id = Integer.parseInt(ids);
		
		
		CatalogDAO catalogDAO = new CatalogDAO();
		Catalog catalog = catalogDAO.catalogedit(id);
		
		request.setAttribute("catalog", catalog);
		
		String path = "background/catalog_edit.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//��������
	public void catalogupdate(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String ids = request.getParameter("caid");
		String state = request.getParameter("mstate");
		String name = request.getParameter("caname");
		String numbers = request.getParameter("canumber");
		int id = Integer.parseInt(ids);
		int number = Integer.parseInt(numbers);
		
		Catalog catalog = new Catalog();
		catalog.setCa_id(id);
		catalog.setCa_state(state);
		catalog.setCa_name(name);
		catalog.setCa_number(number);
		
		CatalogDAO catalogDAO = new CatalogDAO();
		
		String path = "CatalogServlet?type=catalogedit&id="+id;
		if(catalogDAO.catalogupdate(catalog)){
			path = "CatalogServlet?type=findallcatalog";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//��Ŀ����
	public void moveup(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int renumber = 0;
		String numbers = request.getParameter("number");
		CatalogDAO catalogDAO = new CatalogDAO();
		String num = catalogDAO.renumber(numbers);
		int number = Integer.parseInt(numbers);
		if(num != null){
			renumber = Integer.parseInt(num);
		}
		
		
		
		String path="CatalogServlet?type=findallcatalog";
		
		Catalog catalog = new Catalog();
			
		catalog.setCa_number(number);
		if(catalogDAO.moveup(number,renumber)){
			request.getRequestDispatcher(path).forward(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//��Ŀ����
	public void movedown(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int nenumber = 0;
		String numbers = request.getParameter("number");
		CatalogDAO catalogDAO = new CatalogDAO();
		String num = catalogDAO.nenumber(numbers);
		int number = Integer.parseInt(numbers);
		if(num != null){
			nenumber = Integer.parseInt(num);
		}
		
		
		
		String path="CatalogServlet?type=findallcatalog";
		
		Catalog catalog = new Catalog();
			
		catalog.setCa_number(number);
		if(catalogDAO.movedown(number,nenumber)){
			request.getRequestDispatcher(path).forward(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}






