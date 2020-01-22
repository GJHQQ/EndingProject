package com.hqq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPasswdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userpasswd = request.getParameter("userpasswd");
		String onceuserpasswd = request.getParameter("onceuserpasswd");
		String result = null;
		
		if(userpasswd.equals(onceuserpasswd)){
			result = "<font color='green'>√‹¬Î“ª÷¬</font>";
		}else{
			result = "<font color='red'>√‹¬Î≤ª“ª÷¬</font>";
		}
		
		response.getWriter().print(result);

	}

}
