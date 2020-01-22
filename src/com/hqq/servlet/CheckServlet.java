package com.hqq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqq.dao.ManagerDAO;
import com.hqq.demo.Manager;

public class CheckServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("username");
		String result=null;
		
		if(name != ""){
			
			ManagerDAO managerDAO = new ManagerDAO();
			if(managerDAO.checkusername(name)){
				result = "<font color='red'>���û��ѱ�ʹ��,����ͬ��ĸ�Ĵ�Сд</font>";
			}else{
				result = "<font color='green'>���û�����ʹ��</font>";
			}
			response.getWriter().print(result);
		}	
		
	}

}
