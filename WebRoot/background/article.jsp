<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'article.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="background/css/article.css">

  </head>
  
  <body>
  <c:if test="${requestScope.bool == '0'}">
  	<script type="text/javascript">
  		alert("error文章不存在");
  	</script>
  </c:if>
  
  
    <div class="box">
		<h1 class="ar_title">${requestScope.article.ar_title }</h1>
		<div class="nat">
			
			<!-- 显示栏目名称，而非栏目ID -->
			<c:forEach items="${requestScope.catalog}" var="calist">
				<c:if test="${calist.ca_id == requestScope.article.ca_id}">
					<img src="background/images/newsbg01.png">
					<span class="catalog">${calist.ca_name }</span>
				</c:if>
			</c:forEach>
		
		
			<span class="time">
				<img src="background/images/newsbg02.png">
				${requestScope.article.ar_time }
			</span>
			
			<span class="user">
				<img src="background/images/author2.png">
				${requestScope.article.ar_user }
			</span>
		</div>
		<div class="ar_content">${requestScope.article.ar_content }</div>
	</div>
  </body>
</html>
