<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">添加用户</div>
<form id="user-add-form" role="form" action="/user" class="form-horizontal ajax" method="post" redirect="/user">
   <div class="form-group">
       <label for="username" class="col-sm-2 control-label">用户名:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="username">
       </div>
   </div>
   <div class="form-group">
       <label for="realname" class="col-sm-2 control-label">真实姓名:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="realname">
       </div>
   </div>
   <div class="form-group">
       <label for="password" class="col-sm-2 control-label">密码:</label>
       <div class="col-sm-10">
           <input type="password" class="form-control" name="password">
       </div>
   </div>
   <div class="form-group">
       <label for="verified" class="col-sm-2 control-label">是否启用:</label>
       <div class="col-sm-10">
       <div class="checkbox">
           <label>
	      <input type="checkbox" name="verified">
	      </label>
       </div>
       </div>
   </div>
   <!-- 
   <div class="checkbox">
      <label>
      <input type="checkbox" name="verified"> 是否启用
      </label>
   </div>
    -->
   <div class="form-group">
       <label for="email" class="col-sm-2 control-label">邮箱:</label>
       <div class="col-sm-10">
           <input type="email" class="form-control" name="email">
       </div>
   </div>
   <div class="form-group">
       <label for="telephone" class="col-sm-2 control-label">手机号码:</label>
       <div class="col-sm-10">
           <input type="tel" class="form-control" name="telephone">
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
       <label for="roleIds" class="col-sm-2 control-label">拥有角色:</label>
       <div class="col-sm-10">
       <c:forEach items="${rs }" var="r">
       <div class="checkbox">
          <label>
	      <input type="checkbox" name="roleIds" id=${r.id } value="${r.id }">${r.name }
	      </label>
       </div>
       </c:forEach>
       </div>
   </div>
   <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">提交</button>
   </div>
</form>
<script>
	(function(){
		
	})();
</script>
