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
	<div class="container">
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
		   <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">提交</button>
		   </div>
		</div>
      </form>
    </div>
    <script src="<c:url value='/resources/js/jquery-1.11.3.min.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
</body>
</html>