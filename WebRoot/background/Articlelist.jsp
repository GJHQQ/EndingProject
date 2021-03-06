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
    
    <title>主要内容区main</title>
    <link href="background/css/css.css" type="text/css" rel="stylesheet" />
	<link href="background/css/main.css" type="text/css" rel="stylesheet" />
	<link rel="shortcut icon" href="background/images/main/favicon.ico" />
	<style>
	body{overflow-x:hidden; background:#f2f0f5; padding:15px 0px 10px 5px;}
	#searchmain{ font-size:12px;}
	#search{ font-size:12px; background:#548fc9; margin:10px 10px 0 0; display:inline; width:100%; color:#FFF; float:left}
	#search form span{height:40px; line-height:40px; padding:0 0px 0 10px; float:left;}
	#search form input.text-word{height:24px; line-height:24px; width:180px; margin:8px 0 6px 0; padding:0 0px 0 10px; float:left; border:1px solid #FFF;}
	#search form input.text-but{height:24px; line-height:24px; width:55px; background:url(background/images/main/list_input.jpg) no-repeat left top; border:none; cursor:pointer; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666; float:left; margin:8px 0 0 6px; display:inline;}
	#search a.add{ background:url(background/images/main/add.jpg) no-repeat -3px 7px #548fc9; padding:0 10px 0 26px; height:40px; line-height:40px; font-size:14px; font-weight:bold; color:#FFF; float:right}
	#search a:hover.add{ text-decoration:underline; color:#d2e9ff;}
	#main-tab{ border:1px solid #eaeaea; background:#FFF; font-size:12px;}
	#main-tab th{ font-size:12px; background:url(background/images/main/list_bg.jpg) repeat-x; height:32px; line-height:32px;}
	#main-tab td{ font-size:12px; line-height:40px;}
	#main-tab td a{ font-size:12px; color:#548fc9;}
	#main-tab td a:hover{color:#565656; text-decoration:underline;}
	.bordertop{ border-top:1px solid #ebebeb}
	.borderright{ border-right:1px solid #ebebeb}
	.borderbottom{ border-bottom:1px solid #ebebeb}
	.borderleft{ border-left:1px solid #ebebeb}
	.gray{ color:#dbdbdb;}
	td.fenye{ padding:10px 0 0 0; text-align:right;}
	.bggray{ background:#f9f9f9}
	</style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="background/js/jquery-3.4.1.js"></script>
	
  </head>
  
<body >&nbsp; 
<!--main_top-->
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：文章管理</td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="ArticleServlet?type=search">
	         <span>文章标题：</span>
	         <input type="text" name="ar_title" value="" class="text-word" placeholder="输入文章标题">
	         <input name="" type="submit" value="查询" class="text-but">
	         </form>
         </td>
  		  <td width="10%" align="center" valign="middle" style="text-align:right; width:150px;"><a href="ArticleServlet?type=add" target="mainFrame" onFocus="this.blur()" class="add">新建文章</a></td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab" >
      <tr>
        <th align="center" valign="middle" class="borderright">文章ID</th>
        <th align="center" valign="middle" class="borderright">所在栏目</th>
        <th align="center" valign="middle" class="borderright">文章序号</th>
        <th align="center" valign="middle" class="borderright">文章标题</th>
        <th align="center" valign="middle" class="borderright">文章内容</th>
        <th align="center" valign="middle" class="borderright">发表用户</th>
        <th align="center" valign="middle" class="borderright">发表时间</th>
        <th align="center" valign="middle" class="borderright">文章状态</th>
        <th align="center" valign="middle" class="borderright">点击数</th>
        <th align="center" valign="middle">操作</th>
      </tr>
      <c:forEach items="${requestScope.articlelist}" var="artlist">
      	 <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="center" valign="middle" class="borderright borderbottom">${artlist.ar_id }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">
		        <c:forEach items="${requestScope.catlist}" var="catalog">
		        	<c:if test="${catalog.ca_id == artlist.ca_id }">
		        		${catalog.ca_name }
		        	</c:if>
		        </c:forEach>
	        </td>	  
	        <td align="center" valign="middle" class="borderright borderbottom">${artlist.ar_number }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${artlist.ar_title }</td>
	        <td align="center" valign="left" class="borderright borderbottom" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;width:300px;">${artlist.ar_content }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${artlist.ar_user }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${artlist.ar_time }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${artlist.ar_state }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${artlist.clicks }</td>

	        
	        <td align="center" valign="middle" class="borderbottom">
	        <a href="ArticleServlet?type=edit&arid=${artlist.ar_id }" target="mainFrame" onFocus="this.blur()" class="add">编辑</a><span class="gray">&nbsp;|&nbsp;</span>
	        <a style="cursor: pointer;" target="mainFrame" onFocus="this.blur()" class="add" onclick="cli(${artlist.ar_id })">删除</a></td>
	      </tr>
      </c:forEach>
      
    </table></td>
    </tr>
  <tr>
  	<c:if test="${requestScope.bool == '0' && requestScope.pg.count != 0}">
  		<td align="left" valign="top" class="fenye"> 
	    	${requestScope.pg.count }条数据 ${requestScope.pg.currentpage }/${requestScope.pg.pagecount } 页&nbsp;&nbsp;
		    <a href="ArticleServlet?type=search&currentpage=1&ar_title=${requestScope.title }" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
		    <a href="ArticleServlet?type=search&currentpage=<c:if test="${requestScope.pg.currentpage > 1}">${requestScope.pg.currentpage-1}</c:if><c:if test="${requestScope.pg.currentpage == 1}">${1}</c:if>&ar_title=${requestScope.title }" target="mainFrame" onFocus="this.blur()" name="lastpage">上一页</a>&nbsp;&nbsp;
		    <a href="ArticleServlet?type=search&currentpage=<c:if test="${requestScope.pg.currentpage < requestScope.pg.pagecount}">${requestScope.pg.currentpage+1}</c:if><c:if test="${requestScope.pg.currentpage == requestScope.pg.pagecount}">${requestScope.pg.pagecount}</c:if>&ar_title=${requestScope.title }" target="mainFrame" onFocus="this.blur()" name="nextpage">下一页</a>&nbsp;&nbsp;
		    <a href="ArticleServlet?type=search&currentpage=${requestScope.pg.pagecount }&ar_title=${requestScope.title }" target="mainFrame" onFocus="this.blur()">尾页</a>
		</td>
  	</c:if>
  	<c:if test="${requestScope.bool != '0' && requestScope.pg.count != 0}">
	  	<td align="left" valign="top" class="fenye"> 
	    	${requestScope.pg.count }条数据 ${requestScope.pg.currentpage }/${requestScope.pg.pagecount } 页&nbsp;&nbsp;
		    <a href="ArticleServlet?type=findallArticle&currentpage=1" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
		    <a href="ArticleServlet?type=findallArticle&currentpage=<c:if test="${requestScope.pg.currentpage > 1}">${requestScope.pg.currentpage-1}</c:if><c:if test="${requestScope.pg.currentpage == 1}">${1}</c:if>" target="mainFrame" onFocus="this.blur()" name="lastpage">上一页</a>&nbsp;&nbsp;
		    <a href="ArticleServlet?type=findallArticle&currentpage=<c:if test="${requestScope.pg.currentpage < requestScope.pg.pagecount}">${requestScope.pg.currentpage+1}</c:if><c:if test="${requestScope.pg.currentpage == requestScope.pg.pagecount}">${requestScope.pg.pagecount}</c:if>" target="mainFrame" onFocus="this.blur()" name="nextpage">下一页</a>&nbsp;&nbsp;
		    <a href="ArticleServlet?type=findallArticle&currentpage=${requestScope.pg.pagecount }" target="mainFrame" onFocus="this.blur()">尾页</a>
		</td>
  	</c:if>
    <td>
  		<c:if test="${requestScope.bool == '0' && requestScope.pg.count == 0}">	
	  		<p style="text-align: center;color:red;margin-top:20px;">无查询结果</p>
	  	</c:if>
	  	<c:if test="${requestScope.bool != '0' && requestScope.pg.count == 0}">	
	  		<p style="text-align: center;color:red;margin-top:20px;">无文章</p>
	  	</c:if>
  	</td>
  </tr>
</table>

</body>
<script type="text/javascript">
	function cli(id){
		if(confirm("是否删除此文章")){
			window.location.href="ArticleServlet?type=delete&id="+id;
		}
	}
	
</script>

</html>







