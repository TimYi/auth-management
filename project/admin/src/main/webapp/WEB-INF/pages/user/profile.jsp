<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">个人中心</div>
<form class="form-horizontal">
   <div class="form-group">
       <label for="username" class="col-sm-2 control-label">用户名:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="username" value="${t.username }" readonly="readonly">
       </div>
   </div>
   <div class="form-group">
       <label for="realname" class="col-sm-2 control-label">真实姓名:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="realname" value="${t.realname }" readonly="readonly">
       </div>
   </div>
   <div class="form-group">
       <label for="email" class="col-sm-2 control-label">邮箱:</label>
       <div class="col-sm-10">
           <input type="email" class="form-control" name="email" value="${t.email }" readonly="readonly">
       </div>
   </div>
   <div class="form-group">
       <label for="telephone" class="col-sm-2 control-label">手机号码:</label>
       <div class="col-sm-10">
           <input type="tel" class="form-control" name="telephone" value="${t.telephone }" readonly="readonly">
       </div>
   </div>
   <div class="form-group">
       <label for="departmentId" class="col-sm-2 control-label">所属部门:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="departmentId" 
           <c:if test="${! empty t.department }">value="${t.department.name }"</c:if> readonly="readonly">
       </div>
   </div>
</form>
<script>
	(function(){
		
	})();
</script>
