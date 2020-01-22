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
		if(type.endsWith("恢复")){
			batchrecover(request,response);
		}
		if(type.endsWith("删除")){
			batchdeletes(request,response);
		}
		
	}
	
	
	//登入
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
	
	//登出
	public void logout(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name") != null){
			session.invalidate();
			String path = "background/login.jsp";
			response.sendRedirect(path);
		}
		
	}
	
	//全查
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
		
		//调用managerDAO里面的count()方法，得到总数据数
		int cou = managerDAO.count();
		
		//得到总数据数之后计算出总页数
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//将pagesize，currentpage这两个值传给PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Manager> managerlist=managerDAO.findall(pg);
		
		for (Manager manager : managerlist) {
			if(manager.getMstate().endsWith("1"))
				manager.setMstate("启用");
			else
				manager.setMstate("未启用");
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
	
	//模糊查询
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
		
		//调用managerDAO里面的count()方法，得到总数据数
		int cou = managerDAO.countsearch(name);
		
		//得到总数据数之后计算出总页数
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//将pagesize，currentpage这两个值传给PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Manager> managerlist=managerDAO.search(pg,name);
		
		for (Manager manager : managerlist) {
			if(manager.getMstate().endsWith("1"))
				manager.setMstate("启用");
			else
				manager.setMstate("未启用");
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
	
	//模拟删除管理员
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
		//抓取异常之后return结束页面跳转，否则模糊查询再次提交会出现错误
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//批量删除管理员
	public void batchdel(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String [] marid = request.getParameterValues("marid");
		
		ManagerDAO managerDao = new ManagerDAO();
		for(int i=0;i<marid.length;i++){
			if(managerDao.rubbish(marid[i])){
				continue;
			}else{
				System.out.println("编号为："+marid[i]+"的管理员批量产出失败");
				break;
			}
		}
		String path="ManagerServlet?type=findall";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		//抓取异常之后return结束页面跳转，否则模糊查询再次提交会出现错误
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	//恢复模拟删除
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
	//批量恢复模拟删除管理员
	public void batchrecover(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String[] marid = request.getParameterValues("marid");
		ManagerDAO managerDao = new ManagerDAO();
		
		for(int i=0;i<marid.length;i++){
			if(managerDao.recover(marid[i])){
				continue;
			}else{
				System.out.println("恢复编号为："+marid[i]+"的管理员失败");
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
	
	//回收站显示管理员
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
		
		//调用managerDAO里面的count()方法，得到总数据数
		int cou = managerDAO.countrubbish();
		
		//得到总数据数之后计算出总页数
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//将pagesize，currentpage这两个值传给PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Manager> managerlist=managerDAO.rubbishlist(pg);
		
		for (Manager manager : managerlist) {
			if(manager.getMstate().endsWith("3"))
				manager.setMstate("已删除");
//			else
//				manager.setMstate("未启用");
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
	
	
	//模糊查询模拟删除的管理员列表
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
		
		//调用managerDAO里面的count()方法，得到总数据数
		int cou = managerDAO.countsearchrubbish(name);
		
		//得到总数据数之后计算出总页数
		int pagecount = cou % pagesize == 0 ? cou/pagesize : cou/pagesize+1;
		
		//将pagesize，currentpage这两个值传给PageBean
		PageBean pg = new PageBean();
		pg.setCurrentpage(currentpage);
		pg.setPagesize(pagesize);
		pg.setCount(cou);
		pg.setPagecount(pagecount);
		
		ArrayList<Manager> managerlist=managerDAO.searchrubbish(pg,name);
		
		for (Manager manager : managerlist) {
			if(manager.getMstate().endsWith("3"))
				manager.setMstate("已删除");
//			else
//				manager.setMstate("未启用");
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
	
	
	//增加
	public void add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//1、请求转发“新增管理员”相应的页面不需要从jsp页面拿数据
		//2、使用请求转发进行对页面的发送
		//3、不需要调用函数，也不需要有返回值
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
	
	//注册管理员提交
	public void register(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//从jsp获取用户名，密码
		String name=request.getParameter("username");
		String pwd=request.getParameter("userpasswd");
		String oncepwd=request.getParameter("onceuserpasswd");
		String mstate=request.getParameter("level");
		
		//将获取的数据传输给Manager
		Manager manager=new Manager();
		manager.setManager_name(name);
		manager.setPasswd(pwd);
		manager.setOncepasswd(oncepwd);
		manager.setMstate(mstate);
		
		//调用ManagerDAO的register()函数
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
	
	//删除管理员
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
	
	//批量彻底删除
	public void batchdeletes(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String [] marid = request.getParameterValues("marid");
		ManagerDAO managerDao = new ManagerDAO();
		Manager manager=new Manager();
		
		
		for(int i=0;i<marid.length;i++){
			if(managerDao.delete(marid[i])){
				continue;
			}else{
				System.out.println("删除编号为："+marid[i]+"的管理员失败");
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
	
	
	
	
	//编辑管理员
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
	//提交更改的管理员信息
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
	
	//提示错误
	public void titlefalse(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		response.getWriter().println("更改错误，重新更改");
		
		response.setHeader("Refresh", "3;url=ManagerServelt?type=findall");
	}
	
	//检查密码是否相同
	public void checkpasswd(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userpasswd = request.getParameter("userpasswd");
		String onceuserpasswd = request.getParameter("onceuserpasswd");
		String result = null;
		
		if(userpasswd.equals(onceuserpasswd)){
			result = "<font color='green'>密码一致</font>";
		}else{
			result = "<font color='red'>密码不一致</font>";
		}
		
		response.getWriter().print(result);
		
	}
	
	//检查用户名是否被使用
	public void check(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("username");
		String result=null;
		
		if(name != ""){
			
			ManagerDAO managerDAO = new ManagerDAO();
			if(managerDAO.checkusername(name)){
				result = "<font color='red'>该用户已被使用</font>";
			}else{
				result = "<font color='green'>该用户可以使用</font>";
			}
			response.getWriter().print(result);
		}	

	}
	
	
	


}
