<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>backetend</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css'/>" type="text/css" rel="stylesheet"/>
</head>
<body>	
	<div class="container" style="margin-top:15em;">
       <form role="form" class="form-horizontal" method="post">
          <div class="form-group">
              <label for="username" class="col-sm-2 control-label">用户名:</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" name="username" placeholder="please input username">
              </div>
          </div>
          <div class="form-group">
              <label for="password" class="col-sm-2 control-label">密码:</label>
              <div class="col-sm-10">
                  <input type="password" class="form-control" name="password" placeholder="please input password">
              </div>
          </div>
          <div class="form-group">
              <label for="realname" class="col-sm-2 control-label">真实姓名:</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" name="realname" placeholder="please input realname">
              </div>
          </div>
          <div class="form-group">
              <label for="email" class="col-sm-2 control-label">邮箱地址:</label>
              <div class="col-sm-10">
                  <input type="email" class="form-control" name="email" placeholder="please input email">
              </div>
          </div>
          <div class="form-group">
              <label for="telephone" class="col-sm-2 control-label">手机号码:</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" name="telephone" placeholder="please input telephone">
              </div>
          </div>
          <div class="form-group">
		      <label for="departmentId" class="col-sm-2 control-label">所属部门:</label>
		      <div class="col-sm-10">
		      <c:forEach items="${ds }" var="d">
		      <div class="radio">
		         <label>
			     <input type="radio" name="departmentId" id=${d.id } value="${d.id }">${d.name }
			     </label>
		      </div>
		      </c:forEach>
		      </div>
		  </div>
          <div class="form-group">
		   <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">提交</button>
		   </div>
		</div>
      </form>
    </div>
    <script src="<c:url value='/resources/js/jquery-1.11.3.min.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/js/jquery.form.js'/>"></script>
    <script>
	    $('form').on('submit', function(e) {
		    $(this).ajaxSubmit({
		        dataType:"json",
		        success: function(data) { 
		        	if(data.status=="OK") {	
		        		alert(data.result);
					} else {
						alert("错误原因："+data.errorDescription);
					}
		        }
		    });
		    e.preventDefault();
		});
    </script>
</body>
</html>