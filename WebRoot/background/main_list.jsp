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
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
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
	#batch{font-size:15px;padding-right:10px;margin-top:3px;cursor: pointer;}
	#ckbox{visibility:hidden;margin-top:15px;}
	.ckout{vertical-align: middle;}
	#ok{visibility : hidden;}
	.align{text-align: center;margin-right: 20px;font-size:15px;cursor: pointer;height:24px;}
	#sub，#back{height:24px; widht:35px;}
	</style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="background/js/jquery-3.4.1.js"></script>

  </head>
  
<body>&nbsp; 
<!--main_top-->
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：用户管理</td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="70%" align="left" valign="middle" ">
	         <form method="post" action="ManagerServlet?type=search">
	         <span>管理员：</span>
	         <input type="text" name="manager_name" value="" class="text-word" placehloder="输入管理员名称">
	         <input name="" type="submit" value="查询" class="text-but">
	         </form>
         </td>
  		 <td width="50%" align="center" valign="middle" style="text-align:right; width:300px;">
  		  	 <a href="ManagerServlet?type=add" target="mainFrame" onFocus="this.blur()" class="add">新增管理员</a>
  		 </td>
  		 <c:if test="${requestScope.pg.count != 0}">
	  		 <td width="15%" align="center" valign="middle" style="text-align:left; width:150px;">
	  		  	 <i class="fa fa-list" id="batch"><span style="margin-left:10px;font-weight: bolder;">批量操作</span></i>
	  		 </td>
  		 </c:if>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form action="ManagerServlet?type=batchdel" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr>
        <th align="center" valign="middle" class="borderright">管理员ID</th>
        <th align="center" valign="middle" class="borderright">用户名</th>
        <th align="center" valign="middle" class="borderright">密码</th>
        <th align="center" valign="middle" class="borderright">状态</th>
        <th align="center" valign="middle">操作</th>
      </tr>
      <c:forEach items="${requestScope.managerlist}" var="manlist">
      	 <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="center" valign="middle" class="borderright borderbottom">${manlist.manager_id }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${manlist.manager_name }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${manlist.passwd }</td>
	        <td align="center" valign="middle" class="borderright borderbottom">${manlist.mstate }</td>
	        
	        <td align="center" valign="middle" class="borderbottom">
	        	<a href="ManagerServlet?type=edit&id=${manlist.manager_id }"  target="mainFrame" onFocus="this.blur()" class="add">编辑</a><span class="gray">&nbsp;|&nbsp;</span>
	        	<a style="cursor: pointer;" target="mainFrame" onFocus="this.blur()" class="add" onclick="cli(${manlist.manager_id })">删除</a><span class="gray">&nbsp;|&nbsp;</span>
	        	<span class="ckout"><input type="checkbox" name="marid" value="${manlist.manager_id }" id="ckbox"></input></span>
	        </td>
	     </tr>
      </c:forEach>
      <c:if test="${requestScope.pg.count != 0}">
      <tr id="ok">
      	<td></td><td></td><td></td><td></td>
      	<td class="align" style="height:24px;">
      		<input type="submit" id="sub" value="确定">
      		<input type="button" id="back" value="取消">
      	</td>
      </tr>
      </c:if>
    </table>
    </form>
    </td>
    </tr>
  <tr>
  	<c:if test="${requestScope.bool == '0' && requestScope.pg.count != 0}">	
  		<td align="left" valign="top" class="fenye"> 
	    	${requestScope.pg.count }条数据 ${requestScope.pg.currentpage }/${requestScope.pg.pagecount } 页&nbsp;&nbsp;
		    <a href="ManagerServlet?type=search&currentpage=1&manager_name=${requestScope.name}" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
		    <a href="ManagerServlet?type=search&currentpage=<c:if test="${requestScope.pg.currentpage > 1}">${requestScope.pg.currentpage-1}</c:if><c:if test="${requestScope.pg.currentpage == 1}">${1}</c:if>&manager_name=${requestScope.name}" target="mainFrame" onFocus="this.blur()" name="lastpage">上一页</a>&nbsp;&nbsp;
		    <a href="ManagerServlet?type=search&currentpage=<c:if test="${requestScope.pg.currentpage < requestScope.pg.pagecount}">${requestScope.pg.currentpage+1}</c:if><c:if test="${requestScope.pg.currentpage == requestScope.pg.pagecount}">${requestScope.pg.pagecount}</c:if>&manager_name=${requestScope.name}" target="mainFrame" onFocus="this.blur()" name="nextpage">下一页</a>&nbsp;&nbsp;
		    <a href="ManagerServlet?type=search&currentpage=${requestScope.pg.pagecount }&manager_name=${requestScope.name}" target="mainFrame" onFocus="this.blur()">尾页</a>
		</td>
  	</c:if>
  	<c:if test="${requestScope.bool != '0' && requestScope.pg.count != 0}">
  		<td align="left" valign="top" class="fenye"> 
	    	${requestScope.pg.count }条数据 ${requestScope.pg.currentpage }/${requestScope.pg.pagecount } 页&nbsp;&nbsp;
		    <a href="ManagerServlet?type=findall&currentpage=1" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
		    <a href="ManagerServlet?type=findall&currentpage=<c:if test="${requestScope.pg.currentpage > 1}">${requestScope.pg.currentpage-1}</c:if><c:if test="${requestScope.pg.currentpage == 1}">${1}</c:if>" target="mainFrame" onFocus="this.blur()" name="lastpage">上一页</a>&nbsp;&nbsp;
		    <a href="ManagerServlet?type=findall&currentpage=<c:if test="${requestScope.pg.currentpage < requestScope.pg.pagecount}">${requestScope.pg.currentpage+1}</c:if><c:if test="${requestScope.pg.currentpage == requestScope.pg.pagecount}">${requestScope.pg.pagecount}</c:if>" target="mainFrame" onFocus="this.blur()" name="nextpage">下一页</a>&nbsp;&nbsp;
		    <a href="ManagerServlet?type=findall&currentpage=${requestScope.pg.pagecount }" target="mainFrame" onFocus="this.blur()">尾页</a>
		</td>
  	</c:if>	
    <td>
  		<c:if test="${requestScope.bool == '0' && requestScope.pg.count == 0}">	
	  		<p style="text-align: center;color:red;margin-top:20px;">无查询结果</p>
	  	</c:if>
	  	<c:if test="${requestScope.bool != '0' && requestScope.pg.count == 0}">	
	  		<p style="text-align: center;color:red;margin-top:20px;">无管理员</p>
	  	</c:if>
  	</td>
  </tr>
</table>

</body>
<script type="text/javascript">
	
	//全选
	var allsel = document.getElementById("all");
	//获取所有checkbox复选框
	var inp = document.getElementsByName("marid");
	//显示批量操作
	var batch = document.getElementById("batch");
	//确定按钮
	var ok = document.getElementById("ok");
	//确定是否批量删除
	var sub = document.getElementById("sub");
	//取消批量删除
	var back = document.getElementById("back");
	
	function cli(id){
		if(confirm("是否删除管理员")){
			window.location.href="ManagerServlet?type=rubbish&manid="+id;
		}
	}
	
	
	
	sub.onclick = function(){
		if(confirm("是否批量删除管理员")){
			return true;
		}else{
			return false;
		}
	}
	
	back.onclick = function(){
		ok.style.visibility = "hidden";
		//ok.style.float = 'center';
		for(var i=0;i<inp.length;i++){
			inp[i].style.visibility = "hidden";
			//inp[i].style.float = 'right';
		}
	}
	
	
	batch.onclick = function(){
		ok.style.visibility = "visible";
		//ok.style.float = 'center';
		for(var i=0;i<inp.length;i++){
			inp[i].style.visibility = "visible";
			//inp[i].style.float = 'right';
		}	
	}
	
	
</script>

</html>







