<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="script" fragment="true" %>
<%@ attribute name="style" fragment="true" %>
<%@ attribute name="user" fragment="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>backetend</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css'/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/bootstrap-treeview.min.css'/>" type="text/css" rel="stylesheet"/>
    <style type="text/css">
    	.pdl-1 {padding-left:1em;}
    	.pdl-2 {padding-left:2em;}
    	.pdl-3 {padding-left:3em;}
    	.pdl-4 {padding-left:4em;}
    	.mgt-1 {margin-top:1em;}
    	
		.table>tbody>tr>td.pdl-1 {padding-left:1em;	}
		.table>tbody>tr>td.pdl-2 {padding-left:2em;	}
		.table>tbody>tr>td.pdl-3 {padding-left:3em;	}
		.table>tbody>tr>td.pdl-4 {padding-left:4em;	}
		.table>thead>tr>th.pdl-1 {padding-left:1em;	}
		.table>thead>tr>th.pdl-2 {padding-left:2em;	}
		.table>thead>tr>th.pdl-3 {padding-left:3em;	}
		.table>thead>tr>th.pdl-4 {padding-left:4em;	}
		
		.captcha {
			cursor:pointer;
		}
    </style>
    <jsp:invoke fragment="style"></jsp:invoke>
</head>
<body>	
    <div class="container-fluid">
    	<nav class="navbar navbar-default" role="navigation">
		   <div class="navbar-header">
		      <a class="navbar-brand" href="<c:url value='/'/>">双丰网络后台管理系统</a>
		   </div>
		   <div>
		      <ul class="nav navbar-nav">
		      <jsp:invoke fragment="user"></jsp:invoke>
		      </ul>
		   </div>
		</nav>
        <div class="row">
            <div class="col-sm-2"> 
				<div id="tree">
				</div>          
            </div>
            <div id="body" class="col-sm-10">

            	<jsp:doBody/>
            </div>            
        </div>  
    </div>

    
    <script src="<c:url value='/resources/js/jquery-1.11.3.min.js'/>"></script>
    <script src="<c:url value='/resources/js/jquery.form.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap-treeview.js'/>"></script>
    <script src="<c:url value='/resources/js/bootbox.min.js'/>"></script>
    <script src="<c:url value='/resources/js/art-template.js'/>"></script>
    <script charset="utf-8" src="<c:url value='/resources/js/kindeditor.js'/>"></script>
    <script charset="utf-8" src="<c:url value='/resources/js/autoheight.js'/>"></script>
    <script charset="utf-8" src="<c:url value='/resources/js/lang/zh_CN.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap-datepicker.min.js'/>"></script>
    <script src="<c:url value='/resources/js/jquery.twbsPagination.min.js'/>"></script>
    <script src="<c:url value='/resources/js/base.js'/>"></script>
    <script src="<c:url value='/resources/js/dynamicTabs.js'/>"></script>
    <script src="<c:url value='/resources/js/component.js'/>"></script>
    <script src="<c:url value='/resources/js/admin.js'/>"></script>
    <jsp:invoke fragment="script"></jsp:invoke>
</body>
</html>

<!--              
<ul id="myTab" class="nav nav-tabs">
   <li class="active">
      <a href="#home" data-toggle="tab">
         W3Cschool Home
      </a>
   </li>
   <li>
   <a href="#ios" data-toggle="tab">iOS</a>
   <img src="<c:url value='/resources/image/close.png'/>" onclick="dynamicTab.close()" style="position:absolute;top:0;right:0;height:18px;width:18px;cursor:pointer"/>
   </li>
   <li class="dropdown">
      <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">Java 
         <b class="caret"></b>
      </a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
         <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
         <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
      </ul>
   </li>
</ul>
<div id="myTabContent" class="tab-content">
   <div class="tab-pane fade in active" id="home">
      <p>W3Cschoool菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
   </div>
   <div class="tab-pane fade" id="ios">
      <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple 
      TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
   </div>
   <div class="tab-pane fade" id="jmeter">
      <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
   </div>
   <div class="tab-pane fade" id="ejb">
      <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
      </p>
   </div>
</div>      
 -->   
