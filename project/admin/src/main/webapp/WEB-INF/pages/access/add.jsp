<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">添加用户</div>
<form role="form" action="/access" class="form-horizontal ajax" method="post" redirect="/access">
   <div class="form-group">
       <label for="ordernum" class="col-sm-2 control-label">序号</label>
       <div class="col-sm-10">
           <input type="number" class="form-control" name="ordernum">
       </div>
   </div>
   <div class="form-group">
       <label for="url" class="col-sm-2 control-label">访问路径:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="url">
       </div>
   </div>
   <div class="form-group">
       <label for="permission" class="col-sm-2 control-label">权限代码:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="permission">
       </div>
   </div>
   <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">提交</button>
   </div>
</form>
