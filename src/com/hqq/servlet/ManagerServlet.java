package com.hqq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hqq.dao.ManagerDAO;
import com.hqq.demo.Catalog;
import com.hqq.demo.Manager;
import com.hqq.demo.PageBean;

public class ManagerServlet extends HttpServlet {

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
		if(type.endsWith("login")){
			login(request,response);
		}
		if(type.endsWith("findall")){
			findall(request,response);
		}
		if(type.endsWith("add")){
			add(request,response);
		}
		if(type.endsWith("delete")){
			delete(request,response);
		}
		if(type.endsWith("register")){
			register(request,response);
		}
		if(type.endsWith("edit")){
			edit(request,response);
		}
		if(type.endsWith("change")){
			change(request,response);
		}
		if(type.endsWith("titlefalse")){
			titlefalse(request,response);
		}
		if(type.endsWith("checkpasswd")){
			checkpasswd(request,response);
		}
		if(type.endsWith("check")){
			check(request,response);
		}
		if(type.endsWith("logout")){
			logout(request,response);
		}
		if(type.endsWith("search")){
			search(request,response);
		}
		if(type.endsWith("searchrubbish")){
			searchrubbish(request,response);
		}
		if(type.endsWith("rubbish")){
			rubbish(request,response);
		}
		if(type.endsWith("rubbishlist")){
			rubbishlist(request,response);
		}
		if(type.endsWith("recover")){
			recover(request,response);
		}
		if(type.endsWith("batchdel")){
			batchdel(request,response);
		}
		if(type.endsWith("�ָ�")){
			batchrecover(request,response);
		}
		if(type.endsWith("ɾ��")){
			batchdeletes(request,response);
		}
		
	}
	
	
	//����
	public void login(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String bool = null;
		
		HttpSession session = request.getSession();
		
		String name=request.getParameter("username");
		String passwd=request.getParameter("pwd");
		String checkCode = request.getParameter("check_code");
		String savedCode = (String)request.getSession().getAttribute("check_code");
		
		
		
		Manager man=new Manager();
		man.setManager_name(name);
		man.setPasswd(passwd);
		
		ManagerDAO managerdao=new ManagerDAO();
		Manager newman=managerdao.login(man);
		int count = managerdao.count();
		
		String path="background/login.jsp";
		if(newman.getManager_name()!=null && newman.getPasswd()!=null && newman.getMstate().equals("1") && checkCode.equals(savedCode)){
			session.setAttribute("name", name);
			path="background/index.jsp";
		}else{
			do{
				if(newman.getMstate()!=null  && newman.getMstate().equals("2")){
					bool = "500";
					request.setAttribute("bool", bool);
					
					break;
				}
				if(newman.getManager_name()==null || newman.getPasswd()==null){
					bool = "503";
					request.setAttribute("bool", bool);
					break;				
				}
				if(checkCode!=savedCode){
					bool = "504";
					request.setAttribute("bool", bool);						
					break;	
				}
			}while(false);
		}
	
		session.setAttribute("count",count);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//�ǳ�
	public void logout(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name") != null){
			session.invalidate();
			String path = "background/login.jsp";
			response.sendRedirect(path);
		}
		
	}
	
	//ȫ��
	public void findall(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pagesize = 5;
		//int currentpage = 1;
		String current = request.getParameter("currentpage");
		if(current == null){
			current = "1";
		}
		int currentpage = Integer.parseInt(current);
		
		ManagerDAO managerDAO = new ManagerDAO();
		
		//����managerDAO�����count()�������õ���������
		int cou = managerDAO.count();
		
		//�õ���������֮��������ҳ��
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//��pagesize��currentpage������ֵ����PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Manager> managerlist=managerDAO.findall(pg);
		
		for (Manager manager : managerlist) {
			if(manager.getMstate().endsWith("1"))
				manager.setMstate("����");
			else
				manager.setMstate("δ����");
		}
		
		request.setAttribute("managerlist", managerlist);
		request.setAttribute("pg", pg);
		
		String path="background/main_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//ģ����ѯ
	public void search(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pagesize = 5;
		//int currentpage = 1;
		
		String name = request.getParameter("manager_name");
		String current = request.getParameter("currentpage");
		if(current == null){
			current = "1";
		}
		int currentpage = Integer.parseInt(current);
		
		ManagerDAO managerDAO = new ManagerDAO();
		
		//����managerDAO�����count()�������õ���������
		int cou = managerDAO.countsearch(name);
		
		//�õ���������֮��������ҳ��
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//��pagesize��currentpage������ֵ����PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Manager> managerlist=managerDAO.search(pg,name);
		
		for (Manager manager : managerlist) {
			if(manager.getMstate().endsWith("1"))
				manager.setMstate("����");
			else
				manager.setMstate("δ����");
		}
		String bool = "0";
		
		request.setAttribute("bool", bool);
		request.setAttribute("name", name);
		request.setAttribute("managerlist", managerlist);
		request.setAttribute("pg", pg);
		
		String path="background/main_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//ģ��ɾ������Ա
	public void rubbish(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String manager_ids = request.getParameter("manid");
		//int manager_id = Integer.parseInt(manager_ids);
		
		ManagerDAO managerDao = new ManagerDAO();
		String path = "ManagerServlet?type=findall";
		if(managerDao.rubbish(manager_ids)){
			path = "ManagerServlet?type=findall";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		//ץȡ�쳣֮��return����ҳ����ת������ģ����ѯ�ٴ��ύ����ִ���
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//����ɾ������Ա
	public void batchdel(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String [] marid = request.getParameterValues("marid");
		
		ManagerDAO managerDao = new ManagerDAO();
		for(int i=0;i<marid.length;i++){
			if(managerDao.rubbish(marid[i])){
				continue;
			}else{
				System.out.println("���Ϊ��"+marid[i]+"�Ĺ���Ա��������ʧ��");
				break;
			}
		}
		String path="ManagerServlet?type=findall";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		//ץȡ�쳣֮��return����ҳ����ת������ģ����ѯ�ٴ��ύ����ִ���
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	//�ָ�ģ��ɾ��
	public void recover(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String ids=request.getParameter("id");
//		int id=Integer.parseInt(ids);
//		
//		Manager manager=new Manager();
//		manager.setManager_id(id);
		
		ManagerDAO managerDAO=new ManagerDAO();
		if(managerDAO.recover(ids)){
			String path="ManagerServlet?type=rubbishlist";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			try {
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	//�����ָ�ģ��ɾ������Ա
	public void batchrecover(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String[] marid = request.getParameterValues("marid");
		ManagerDAO managerDao = new ManagerDAO();
		
		for(int i=0;i<marid.length;i++){
			if(managerDao.recover(marid[i])){
				continue;
			}else{
				System.out.println("�ָ����Ϊ��"+marid[i]+"�Ĺ���Աʧ��");
				break;
			}
		}
		String path="ManagerServlet?type=rubbishlist";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//����վ��ʾ����Ա
	public void rubbishlist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pagesize = 5;
		//int currentpage = 1;
		String current = request.getParameter("currentpage");
		if(current == null){
			current = "1";
		}
		int currentpage = Integer.parseInt(current);
		
		ManagerDAO managerDAO = new ManagerDAO();
		
		//����managerDAO�����count()�������õ���������
		int cou = managerDAO.countrubbish();
		
		//�õ���������֮��������ҳ��
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//��pagesize��currentpage������ֵ����PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Manager> managerlist=managerDAO.rubbishlist(pg);
		
		for (Manager manager : managerlist) {
			if(manager.getMstate().endsWith("3"))
				manager.setMstate("��ɾ��");
//			else
//				manager.setMstate("δ����");
		}
		
		request.setAttribute("managerlist", managerlist);
		request.setAttribute("pg", pg);
		
		String path="background/rubbish.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	//ģ����ѯģ��ɾ���Ĺ���Ա�б�
	public void searchrubbish(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pagesize = 5;
		//int currentpage = 1;
		
		String name = request.getParameter("manager_name");
		String current = request.getParameter("currentpage");
		if(current == null){
			current = "1";
			
		}
		int currentpage = Integer.parseInt(current);
		
		ManagerDAO managerDAO = new ManagerDAO();
		
		//����managerDAO�����count()�������õ���������
		int cou = managerDAO.countsearchrubbish(name);
		
		//�õ���������֮��������ҳ��
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//��pagesize��currentpage������ֵ����PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Manager> managerlist=managerDAO.searchrubbish(pg,name);
		
		for (Manager manager : managerlist) {
			if(manager.getMstate().endsWith("3"))
				manager.setMstate("��ɾ��");
//			else
//				manager.setMstate("δ����");
		}
		
		String bool = "0";
		
		request.setAttribute("bool", bool);
		request.setAttribute("name", name);
		request.setAttribute("managerlist", managerlist);
		request.setAttribute("pg", pg);
		
		String path="background/rubbish.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	//����
	public void add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//1������ת������������Ա����Ӧ��ҳ�治��Ҫ��jspҳ��������
		//2��ʹ������ת�����ж�ҳ��ķ���
		//3������Ҫ���ú�����Ҳ����Ҫ�з���ֵ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String path="background/main_info.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//ע�����Ա�ύ
	public void register(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��jsp��ȡ�û���������
		String name=request.getParameter("username");
		String pwd=request.getParameter("userpasswd");
		String oncepwd=request.getParameter("onceuserpasswd");
		String mstate=request.getParameter("level");
		
		//����ȡ�����ݴ����Manager
		Manager manager=new Manager();
		manager.setManager_name(name);
		manager.setPasswd(pwd);
		manager.setOncepasswd(oncepwd);
		manager.setMstate(mstate);
		
		//����ManagerDAO��register()����
		ManagerDAO managerDAO=new ManagerDAO();
		String path="background/main_info.jsp";
		
		if(!managerDAO.checkusername(name)){
			if(managerDAO.register(manager)){
				path="ManagerServlet?type=findall";
				
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//ɾ������Ա
	public void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String ids=request.getParameter("id");
//		int id=Integer.parseInt(ids);
//		
//		Manager manager=new Manager();
//		manager.setManager_id(id);
		
		ManagerDAO managerDAO=new ManagerDAO();
		if(managerDAO.delete(ids)){
			String path="ManagerServlet?type=rubbishlist";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			try {
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	//��������ɾ��
	public void batchdeletes(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String [] marid = request.getParameterValues("marid");
		ManagerDAO managerDao = new ManagerDAO();
		Manager manager=new Manager();
		
		
		for(int i=0;i<marid.length;i++){
			if(managerDao.delete(marid[i])){
				continue;
			}else{
				System.out.println("ɾ�����Ϊ��"+marid[i]+"�Ĺ���Աʧ��");
				break;
			}	
		}
		String path = "ManagerServlet?type=rubbishlist";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	//�༭����Ա
	public void edit(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
			Manager manager=new Manager();
			ManagerDAO managerDAO=new ManagerDAO();
			
			String ids=request.getParameter("id");
			int id=Integer.parseInt(ids);
			Manager man=managerDAO.lookup(id);
			
			request.setAttribute("man", man);
			
			String path="background/main_edit.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			try {
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	//�ύ���ĵĹ���Ա��Ϣ
	public void change(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String ids=request.getParameter("userid");
		String userpasswd=request.getParameter("userpasswd");
		String onceuserpasswd=request.getParameter("onceuserpasswd");
		String level=request.getParameter("level");
		int id=Integer.parseInt(ids);
		String path="ManagerServlet?type=edit&id="+id;
		
		if((userpasswd).equals(onceuserpasswd) && userpasswd!="" && onceuserpasswd!=""){
			Manager manager=new Manager();
			manager.setManager_id(id);
			manager.setPasswd(userpasswd);
			manager.setMstate(level);
			ManagerDAO managerDAO=new ManagerDAO();
			
			if(managerDAO.change(manager))
				path="ManagerServlet?type=findall";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//��ʾ����
	public void titlefalse(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		response.getWriter().println("���Ĵ������¸���");
		
		response.setHeader("Refresh", "3;url=ManagerServelt?type=findall");
	}
	
	//��������Ƿ���ͬ
	public void checkpasswd(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userpasswd = request.getParameter("userpasswd");
		String onceuserpasswd = request.getParameter("onceuserpasswd");
		String result = null;
		
		if(userpasswd.equals(onceuserpasswd)){
			result = "<font color='green'>����һ��</font>";
		}else{
			result = "<font color='red'>���벻һ��</font>";
		}
		
		response.getWriter().print(result);
		
	}
	
	//����û����Ƿ�ʹ��
	public void check(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("username");
		String result=null;
		
		if(name != ""){
			
			ManagerDAO managerDAO = new ManagerDAO();
			if(managerDAO.checkusername(name)){
				result = "<font color='red'>���û��ѱ�ʹ��</font>";
			}else{
				result = "<font color='green'>���û�����ʹ��</font>";
			}
			response.getWriter().print(result);
		}	

	}
	
	
	


}
