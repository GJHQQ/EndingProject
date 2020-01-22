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
    
    <title>My JSP 'index_boke.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--  <meta HTTP-EQUIV="refresh" content="0.1;url=http://127.0.0.1:8080/EndingPorject/IndexServlet">-->
	<title>兔小白个人博客</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<link href="css/base.css" rel="stylesheet">
	<link href="css/index.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/sliders.js"></script>
	<!--[if lt IE 9]>
	<script src="js/modernizr.js"></script>
	<![endif]-->
	<!-- 返回顶部调用 begin -->
	<script type="text/javascript" src="js/up/jquery.js"></script>
	<script type="text/javascript" src="js/up/js.js"></script>
	<!-- 返回顶部调用 end-->
	
  </head>
  
  <body>
  <a href="IndexServlet" >没数据？？点我获取</a>
  
   <header>
  <div class="logo f_l"> <a href="/"><img src="images/logo.png"></a> </div>
  <nav id="topnav" class="f_r">
    <ul>
      <a href="index.html" target="_blank">首页</a> <a href="news.html" target="_blank">关于我</a> <a href="p.html" target="_blank">文章</a> <a href="a.html" target="_blank">心情</a> <a href="c.html" target="_blank">相册</a> <a href="b.html" target="_blank">留言</a>
    </ul>
    <script src="js/nav.js"></script> 
  </nav>
</header>
<article>
  <div class="l_box f_l">
    <div class="banner">
      <div id="slide-holder">
        <div id="slide-runner"> <a href="/" target="_blank"><img id="slide-img-1" src="images/a1.jpg"  alt="" /></a> <a href="/" target="_blank"><img id="slide-img-2" src="images/a2.jpg"  alt="" /></a> <a href="/" target="_blank"><img id="slide-img-3" src="images/a3.jpg"  alt="" /></a> <a href="/" target="_blank"><img id="slide-img-4" src="images/a4.jpg"  alt="" /></a>
          <div id="slide-controls">
            <p id="slide-client" class="text"><strong></strong><span></span></p>
            <p id="slide-desc" class="text"></p>
            <p id="slide-nav"></p>
          </div>
        </div>
      </div>
      <script>
	  if(!window.slider) {
		var slider={};
	}

	slider.data= [
    {
        "id":"slide-img-1", // 与slide-runner中的img标签id对应
        "client":"标题1",
        "desc":"这里修改描述 这里修改描述 这里修改描述" //这里修改描述
    },
    {
        "id":"slide-img-2",
        "client":"标题2",
        "desc":"add your description here"
    },
    {
        "id":"slide-img-3",
        "client":"标题3",
        "desc":"add your description here"
    },
    {
        "id":"slide-img-4",
        "client":"标题4",
        "desc":"add your description here"
    } 
	];

	  </script> 
    </div>
    <!-- banner代码 结束 -->
    	<div class="topnews">
      	<h2><span><a href="/" target="_blank">栏目标题</a><a href="/" target="_blank">栏目标题</a><a href="/" target="_blank">栏目标题</a></span><b>文章</b>推荐</h2>
      
      
      
      	<c:forEach items="${requestScope.article}" var="arlist">
	      	 <div class="blogs">
		        <figure><img src="images/01.jpg"></figure>
		        <ul>
		          <h3><a target="_blank" id="title" href="IndexServlet?type=articlecontent&arid=${arlist.ar_id }">${arlist.ar_title }</a></h3>
		          <p style="height:75px;">${arlist.ar_content }</p>
		          <p class="autor"><span class="lm f_l" >
		          <a href="/">	          		
		          	 <c:forEach items="${requestScope.cata}" var="calist">
		          	 	<c:if test="${arlist.ca_id == calist.ca_id}">
		          	 		${calist.ca_name }
		          	 	</c:if>	          	 	
		          	 </c:forEach>	         	 
		          </a></span>
		          <span class="dtime f_l">${arlist.ar_time }</span>
		          <span class="viewnum f_r">浏览（<a href="/">${arlist.clicks }</a>）</span>
		          <span class="pingl f_r">评论（<a href="/">30</a>）</span></p>
		        </ul>
		      </div>
	      </c:forEach>
	      </div>
      
      
    
      
    
  </div>
  
  
  
  
  <div class="r_box f_r">
    <div class="tit01">
      <h3>关注我</h3>
      <div class="gzwm">
        <ul>
          <li><a class="xlwb" href="#" target="_blank">新浪微博</a></li>
          <li><a class="txwb" href="#" target="_blank">腾讯微博</a></li>
          <li><a class="rss" href="portal.php?mod=rss" target="_blank">RSS</a></li>
          <li><a class="wx" href="mailto:admin@admin.com">邮箱</a></li>
        </ul>
      </div>
    </div>
    <!--tit01 end-->
    <div class="ad300x100"> <img src="images/ad300x100.jpg"> </div>
    <div class="moreSelect" id="lp_right_select"> 
      <script>
window.onload = function ()
{
	var oLi = document.getElementById("tab").getElementsByTagName("li");
	var oUl = document.getElementById("ms-main").getElementsByTagName("div");
	
	for(var i = 0; i < oLi.length; i++)
	{
		oLi[i].index = i;
		oLi[i].onmouseover = function ()
		{
			for(var n = 0; n < oLi.length; n++) oLi[n].className="";
			this.className = "cur";
			for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
			oUl[this.index].style.display = "block"
		}	
	}
}
</script>
      <div class="ms-top">
        <ul class="hd" id="tab">
          <li class="cur"><a href="/">点击排行</a></li>
          <li><a href="/">最新文章</a></li>
          <li><a href="/">站长推荐</a></li>
        </ul>
      </div>
      <div class="ms-main" id="ms-main">
        <div style="display: block;" class="bd bd-news" >
          <ul>
          	<!-- 点击排行 -->
            <c:forEach items="${requestScope.arclicks}" var="art">
            	<li><a href="IndexServlet?type=articlecontent&arid=${art.ar_id }" target="_blank">${art.ar_title}</a></li>
            </c:forEach>
          
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul>
          <!-- 最新文章 -->
            <c:forEach items="${requestScope.article}" var="art">
            	<li><a href="IndexServlet?type=articlecontent&arid=${art.ar_id }" target="_blank">${art.ar_title}</a></li>
            </c:forEach>
          </ul>
        </div>
        <div class="bd bd-news">
          <ul>
          	<!-- 站长推荐 -->
            <c:forEach items="${requestScope.article}" var="art">
            	<li><a href="IndexServlet?type=articlecontent&arid=${art.ar_id }" target="_blank">${art.ar_title}</a></li>
            </c:forEach>
          </ul>
        </div>
      </div>
      <!--ms-main end --> 
    </div>
    <!--切换卡 moreSelect end -->
    
    <div class="cloud">
      <h3>标签云</h3>
      <ul>
      	<c:forEach items="${requestScope.cata}" var="calist">
      		<li><a href="/">${calist.ca_name }</a></li>
      	</c:forEach>
      </ul>
    </div>
    <div class="tuwen">
      <h3>图文推荐</h3>
      <ul>
        <li><a href="/"><img src="images/01.jpg"><b>住在手机里的朋友</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/"><img src="images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src="images/03.jpg"><b>手机的16个惊人小秘密，据说...</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/"><img src="images/06.jpg"><b>住在手机里的朋友</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/"><img src="images/04.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
      </ul>
    </div>
    <div class="ad"> <img src="images/03.jpg"> </div>
    <div class="links">
      <h3><span>[<a href="/">申请友情链接</a>]</span>友情链接</h3>
      <ul>
        <li><a href="/">杨青个人博客</a></li>
        <li><a href="/">web开发</a></li>
        <li><a href="/">前端设计</a></li>
        <li><a href="/">Html</a></li>
        <li><a href="/">CSS3</a></li>
        <li><a href="/">Html5+css3</a></li>
        <li><a href="/">百度</a></li>
      </ul>
    </div>
  </div>
  <!--r_box end --> 
</article>
<footer>
  <p class="ft-copyright">兔小白博客 Design by DanceSmile 蜀ICP备11002373号-1</p>
  <div id="tbox"> <a id="togbook" href="/"></a> <a id="gotop" href="javascript:void(0)"></a> </div>
</footer>
  </body>
<script type="text/javascript">

</script>
  
</html>










