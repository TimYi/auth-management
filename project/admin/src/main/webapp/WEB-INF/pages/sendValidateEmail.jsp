<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>backetend</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css'/>" type="text/css" rel="stylesheet"/>
    <style type="text/css">		
		.captcha {
			cursor:pointer;
		}
    </style>
</head>
<body>	
<div class="container" style="margin-top:15em;">
<form role="form" class="form-horizontal" method="post">
   <div class="form-group">
       <label for="email" class="col-sm-2 control-label">绑定邮箱:</label>
       <div class="col-sm-10">
           <input type="email" class="form-control" name="email">
       </div>
   </div>
   <div class="form-group">
       <label for="captcha" class="col-sm-2 control-label">验证码:</label>
       <div class="col-sm-8">
           <input type="text" class="form-control" name="captcha">
       </div>
       <div class="col-sm-2">       		
           <img src="<c:url value='/captcha'/>" class="captcha"/>
       </div>
   </div>
   <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">提交</button>
   </div>
</form>
</div>
<script src="<c:url value='/resources/js/jquery-1.11.3.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/js/jquery.form.js'/>"></script>
<script src="<c:url value='/resources/js/base.js'/>"></script>
<script>
	$('form').on('submit', function(e) {	
	    $(this).ajaxSubmit({
	        dataType:"json",
	        success: function(data) { 
	        	if(data.status=="OK") {	
	        		alert(data.result);
	        		window.location.href=basePath;  
				} else {
					alert("错误原因："+data.errorDescription);
					$("img.captcha").trigger("click");
				}
	        }
	    });
	    e.preventDefault();
	});
	$("img.captcha").on('click', function(e) {
		var src=$(this).attr("src");
		$(this).attr("src",src);
	});
</script>
</body>
</html>
