<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理登录界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="background/css/alogin.css" rel="stylesheet" type="text/css" />

  </head>
  
  <body>
  
    <form id="form1" action="ManagerServlet?type=login" method="post">
    <div class="Main">
        <ul>
            <li class="top"></li>
            <li class="top2"></li>
            <li class="topA"></li>
            <li class="topB"><span><img src="background/images/login/name.png" alt="" style="width: 287px; height: 129px;" width="287" height="129"/></span></li>
            <li class="topC"></li>
            <li class="topD">
                <ul class="login" style="padding:0;">
                    <li><span class="left login-text">用户名：</span> <span style="left">
                        <input id="Text1" type="text" class="txt" name="username"/>     
                    </span></li>
                    <li><span class="left login-text">密码：</span> <span style="left">
                       <input id="Text2" type="password" class="txt" name="pwd" />  
                    </span></li>
                    <li><span class="left login-text">验证码：</span> <span style="left">
                       <input id="Text2" type="password" class="txt" name="check_code" style="width:80px;vertical-align: middle;"/>
                       <img src="CheckCodeServlet" style="vertical-align: middle;">  
                    </span></li>
					
                </ul>
            </li>
            <li class="topE"></li>
            <li class="middle_A"></li>
            <li class="middle_B"></li>
            <li class="middle_C"><span class="btn"><input name="" type="image" src="background/images/login/btnlogin.gif" /></span></li>
            <li class="middle_D"></li>
            <li class="bottom_A"></li>
            <li class="bottom_B">个人网站后台管理系统&nbsp;&nbsp;www.GJHQQ.com</li>
        </ul>
    </div>
    <!--<input type="hidden" name="type" value="login">-->
    </form>
    
    
     <c:if test="${requestScope.bool == '500'}">
	  	<script type="text/javascript">
	  		alert("该用户名已被禁用");
	  	</script>
	  </c:if>
	  <c:if test="${requestScope.bool == '503'}">
	  	<script type="text/javascript">
	  		alert("用户名或密码错误");
	  	</script>
	  </c:if>
	  <c:if test="${requestScope.bool == '504'}">
	  	<script type="text/javascript">
	  		alert("验证码错误");
	  	</script>
	  </c:if>
</body>
</html>
