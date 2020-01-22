<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>左侧导航menu</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="background/css/css.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="background/js/sdmenu.js"></script>
	<script type="text/javascript">
		// <![CDATA[
		var myMenu;
		window.onload = function() {
			myMenu = new SDMenu("my_menu");
			myMenu.init();
		};
		// ]]>
	</script>
	<style type=text/css>
	html{ SCROLLBAR-FACE-COLOR: #538ec6; SCROLLBAR-HIGHLIGHT-COLOR: #dce5f0; SCROLLBAR-SHADOW-COLOR: #2c6daa; SCROLLBAR-3DLIGHT-COLOR: #dce5f0; SCROLLBAR-ARROW-COLOR: #2c6daa;  SCROLLBAR-TRACK-COLOR: #dce5f0;  SCROLLBAR-DARKSHADOW-COLOR: #dce5f0; overflow-x:hidden;}
	body{overflow-x:hidden; background:url(images/main/leftbg.jpg) left top repeat-y #f2f0f5; width:194px;}
	</style>
  </head>
  
  <body onselectstart="return false;" ondragstart="return false;" oncontextmenu="return false;">
	<div id="left-top">
		<div><img src="background/images/main/member.gif" width="44" height="44" /></div>
	    <span>用户：${sessionScope.name }<br>角色：超级管理员</span>
	</div>
	    <div style="float: left" id="my_menu" class="sdmenu">
	      <div class="collapsed">
	        <span>系统设置</span>
	        <a href="background/main.jsp" target="mainFrame" onFocus="this.blur()">后台首页</a>
	        <a href="ManagerServlet?type=findall" target="mainFrame" onFocus="this.blur()">管理员列表</a>
	        <a href="ManagerServlet?type=rubbishlist" target="mainFrame" onFocus="this.blur()">管理员回收站</a>
	        <a href="background/main_menu.jsp" target="mainFrame" onFocus="this.blur()">自定义权限</a>
	      </div>
	      <div>
	        <span>栏目设置</span>
	        <a href="CatalogServlet?type=findallcatalog" target="mainFrame" onFocus="this.blur()">栏目管理</a>
	        <a href="CatalogServlet?type=max" target="mainFrame" onFocus="this.blur()">添加栏目</a>
	      </div>
	      <div>
	        <span>文章设置</span>
	        <a href="ArticleServlet?type=findallArticle" target="mainFrame" onFocus="this.blur()">文章管理</a>
	        <a href="ArticleServlet?type=add" target="mainFrame" onFocus="this.blur()">添加文章</a>
	      </div>
	      <div>
	        <span>系统设置</span>
	        <a href="background/main.jsp" target="mainFrame" onFocus="this.blur()">分组权限</a>
	        <a href="background/main_list.jsp" target="mainFrame" onFocus="this.blur()">级别权限</a>
	        <a href="background/main_info.jsp" target="mainFrame" onFocus="this.blur()">角色管理</a>
	        <a href="background/main.jsp" target="mainFrame" onFocus="this.blur()">自定义权限</a>
	      </div>
	    </div>
  </body>
</html>
