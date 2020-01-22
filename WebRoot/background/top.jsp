<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台页面头部</title>
    <link href="background/css/css.css" type="text/css" rel="stylesheet" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body onselectstart="return false" oncontextmenu=return(false) style="overflow-x:hidden;">
	<!--禁止网页另存为-->
	<noscript><iframe scr="*.htm"></iframe></noscript>
	<!--禁止网页另存为-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="header">
	  <tr>
	    <td rowspan="2" align="left" valign="top" id="logo"><img src="background/images/main/logo.jpg" width="74" height="64"></td>
	    <td align="left" valign="bottom">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td align="left" valign="bottom" id="header-name">侯马经济开发区</td>
	        <td align="right" valign="top" id="header-right">
	        	<a target="_top" onFocus="this.blur()" class="admin-out" onclick="exit()" >注销</a>
	            <a href="IndexServlet" target="top" onFocus="this.blur()" class="admin-home">管理首页</a>
	        	<a href="IndexServlet" target="_blank" onFocus="this.blur()" class="admin-index">网站首页</a>       	
	            <span>
	<!-- 日历 -->
	<SCRIPT type=text/javascript src="background/js/clock.js"></SCRIPT>
	<SCRIPT type=text/javascript>showcal();</SCRIPT>
	            </span>
	        </td>
	      </tr>
	    </table></td>
	  </tr>
	  <tr>
	    <td align="left" valign="bottom">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td align="left" valign="top" id="header-admin">后台管理系统</td>
	        <td align="left" valign="bottom" id="header-menu">
	        <a href="index.html" target="left" onFocus="this.blur()" id="menuon">后台首页</a>
	        <a href="index.html" target="left" onFocus="this.blur()">用户管理</a>
	        <a href="index.html" target="left" onFocus="this.blur()">栏目管理</a>
	        <a href="index.html" target="left" onFocus="this.blur()">信息管理</a>
	        <a href="index.html" target="left" onFocus="this.blur()">留言管理</a>
	        <a href="index.html" target="left" onFocus="this.blur()">附件管理</a>
	        <a href="index.html" target="left" onFocus="this.blur()">站点管理</a>
	        </td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
  </body>
  <script type="text/javascript">
  	function exit(){
  		if(confirm("是否注销管理员")){
  			top.location.href = "ManagerServlet?type=logout";
  		}
  	}
  	history.go(1);
  </script>
</html>
