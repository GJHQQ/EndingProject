<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主要内容区main</title>
    <link href="background/css/css.css" type="text/css" rel="stylesheet" />
	<link href="background/css/main.css" type="text/css" rel="stylesheet" />
	<link rel="shortcut icon" href="background/images/main/favicon.ico" />
	<script type="text/javascript" src="background/js/main.js"></script>
	<style>
	textarea{border:1px solid #ebebeb;width:1000px;resize:none;height:150px;padding:5px;text-indent: 2em;}
	body{overflow-x:hidden; background:#f2f0f5; padding:15px 0px 10px 5px;}
	#searchmain{ font-size:12px;}
	#search{ font-size:12px; background:#548fc9; margin:10px 10px 0 0; display:inline; width:100%; color:#FFF}
	#search form span{height:40px; line-height:40px; padding:0 0px 0 10px; float:left;}
	#search form input.text-word{height:24px; line-height:24px; width:180px; margin:8px 0 6px 0; padding:0 0px 0 10px; float:left; border:1px solid #FFF;}
	#search form input.text-but{height:24px; line-height:24px; width:55px; background:url(images/main/list_input.jpg) no-repeat left top; border:none; cursor:pointer; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666; float:left; margin:8px 0 0 6px; display:inline;}
	#search a.add{ background:url(images/main/add.jpg) no-repeat 0px 6px; padding:0 10px 0 26px; height:40px; line-height:40px; font-size:14px; font-weight:bold; color:#FFF}
	#search a:hover.add{ text-decoration:underline; color:#d2e9ff;}
	#main-tab{ border:1px solid #eaeaea; background:#FFF; font-size:12px;}
	#main-tab th{ font-size:12px; background:url(images/main/list_bg.jpg) repeat-x; height:32px; line-height:32px;}
	#main-tab td{ font-size:12px; line-height:40px;}
	#main-tab td a{ font-size:12px; color:#548fc9;}
	#main-tab td a:hover{color:#565656; text-decoration:underline;}
	.bordertop{ border-top:1px solid #ebebeb}
	.borderright{ border-right:1px solid #ebebeb}
	.borderbottom{ border-bottom:1px solid #ebebeb}
	.borderleft{ border-left:1px solid #ebebeb}
	.gray{ color:#dbdbdb;}
	td.fenye{ padding:10px 0 0 0; text-align:right;}
	.bggray{ background:#f9f9f9; font-size:14px; font-weight:bold; padding:10px 10px 10px 0; width:120px;}
	.main-for{ padding:10px;}
	.main-for input.text-word{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid; background:#FFF; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; padding:0 10px;}
	.main-for select{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid; background:#FFF; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666;}
	.main-for input.text-but{ width:100px; height:40px; line-height:30px; border: 1px solid #cdcdcd; background:#e6e6e6; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#969696; float:left; margin:0 10px 0 0; display:inline; cursor:pointer; font-size:14px; font-weight:bold;}
	#addinfo a{ font-size:14px; font-weight:bold; background:url(images/main/addinfoblack.jpg) no-repeat 0 1px; padding:0px 0 0px 20px; line-height:45px;}
	#addinfo a:hover{ background:url(images/main/addinfoblue.jpg) no-repeat 0 1px;}
	</style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/background/js/jquery-3.4.1.js"></script>
	
	
	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
  </head>
  
  <body>&nbsp; 
    <!--main_top-->
   
   <!-- 失败提示 -->
    <c:if test="${requestScope.bool == '0'}">
	    <script type="text/javascript">
	    	alert("操作失败");
	    </script>
    </c:if>
	
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
	  <tr>
	    <td width="99%" align="left" valign="top">您的位置：文章管理&nbsp;&nbsp;>&nbsp;&nbsp;新建文章</td>
	  </tr>
	  <tr>
	    <td align="left" valign="top" id="addinfo">
	    <a href="#" target="mainFrame" onFocus="this.blur()" class="add">新建文章</a>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    <form method="post" action="ArticleServlet?type=update">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">文章栏目：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        
	        <select name="ca_id" id="level">
	        	<c:forEach items="${requestScope.catal}" var="calist">
	        		<c:if test="${requestScope.arti.ca_id == calist.ca_id}">
	        			<option value="${calist.ca_id }" selected="selected">${calist.ca_name }</option>
	        		</c:if>
	        		<c:if test="${requestScope.arti.ca_id != calist.ca_id}">
	        			<option value="${calist.ca_id }" >${calist.ca_name }</option>
	        		</c:if>
	        	</c:forEach>
		        	
	        </select>
	        
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">文章序号：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="text" name="ar_number" value="${requestScope.arti.ar_number }" class="text-word" id="user_pwd" placeholder="" >
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">文章标题：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="text" name="ar_title" value="${requestScope.arti.ar_title }" class="text-word" id="user_pwd" placeholder="" >
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">文章内容：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <textarea name="ar_content" value="${requestScope.arti.ar_recontent }" class="text-word" id="editor" placeholder=""  style="width:1200px;height: 450px;">${requestScope.arti.ar_content }</textarea>
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">发布者：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="text" name="ar_user" value="${requestScope.arti.ar_user }" class="text-word" id="user_pwd" placeholder="" >
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">状态：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        
	        <select name="ar_state" id="level">	 
	        <c:if test="${requestScope.arti.ar_state == 1}">
	        	<option value="1" selected="selected">&nbsp;&nbsp;启用</option>
			    <option value="2" >&nbsp;&nbsp;未启用</option>
	        </c:if>       	
			<c:if test="${requestScope.arti.ar_state == 2}">
	        	<option value="1" >&nbsp;&nbsp;启用</option>
			    <option value="2" selected="selected">&nbsp;&nbsp;未启用</option>
	        </c:if>         
		     </select>
	        
	        
	        
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">&nbsp;</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="hidden" name="arid" value="${requestScope.arti.ar_id }">
	        <input type="hidden"  name="type" value="update">
	        <input name="" type="submit" value="提交" class="text-but" onClick="getvalues()">
	        <input name="" type="reset" value="重置" class="text-but"></td>
	        </tr>
	    </table>
	    </form>
	    </td>
	    </tr>
	</table>
  </body>
  <script type="text/javascript">
  alert("暂不支持从其他页面复制过来的超链接的加入，操作时请谨慎");
  var ue = UE.getEditor('editor');
  	$(function(){
  		$(":input[name='username']").change(function(){
  			var val = $(this).val();
  			val = $.trim(val);
  			
  			if( val != ""){
  				var url="${pageContext.request.contextPath}/CheckServlet";
  				var agrs={"username":val,"time":new Date()};
  				
  				$.post(url,agrs,function(data){
  					$("#message_name").html(data);
  				})
  			}
  		})
  		
  	})
 	$(function (){
  		$(":input[name='userpasswd'],:input[name='onceuserpasswd']").change(function(){
  			var val = $("#user_pwd").val(); 
  			var val2 = $("#user_repwd").val(); 
  			val = $.trim(val);
  			val2 = $.trim(val2);
  			
  			if(val != "" && val2 != ""){
  				var url = "${pageContext.request.contextPath}/CheckPasswdServlet";
  				var agrs = {"userpasswd":val,"onceuserpasswd":val2,"time":new Date()};	
  				$.post(url,agrs,function(data){
  					$("#message_repwd").html(data);
  				})
  			}
  		})
  		
  	})
 </script>
</html>
