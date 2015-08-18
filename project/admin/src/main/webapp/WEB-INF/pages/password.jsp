<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">修改密码</div>
<form role="form" action="/user/changePassword" class="form-horizontal ajax" method="post" redirect="/user/profile">
   <div class="form-group">
       <label for="username" class="col-sm-2 control-label">用户名:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="username">
       </div>
   </div>
   <div class="form-group">
       <label for="oldPassword" class="col-sm-2 control-label">原密码:</label>
       <div class="col-sm-10">
           <input type="password" class="form-control" name="oldPassword">
       </div>
   </div>
   <div class="form-group">
       <label for="newPassword" class="col-sm-2 control-label">新密码:</label>
       <div class="col-sm-10">
           <input type="password" class="form-control" name="newPassword">
       </div>
   </div>
   <div class="form-group">
       <label for="confirmPassword" class="col-sm-2 control-label">确认密码:</label>
       <div class="col-sm-10">
           <input type="password" class="form-control" name="confirmPassword">
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
