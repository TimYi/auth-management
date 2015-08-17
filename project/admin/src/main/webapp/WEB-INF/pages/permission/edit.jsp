<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">修改权限</div>
<form role="form" action="/permission/${t.id }" class="form-horizontal ajax" method="post" redirect="/permission">
   <div class="form-group">
       <label for="name" class="col-sm-2 control-label">名称:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="name" value="${t.name }">
       </div>
   </div>
   <div class="form-group">
       <label for="code" class="col-sm-2 control-label">代码:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="code" value="${t.code }">
       </div>
   </div>
   <div class="form-group">
       <label for="description" class="col-sm-2 control-label">描述:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="description" value="${t.description }">
       </div>
   </div>
   <div class="form-group">
       <label for="type" class="col-sm-2 control-label">类型:</label>
       <div class="col-sm-10">
	       <input type="text" class="form-control" name="permissionType" value="${t.type }" readonly="readonly" />
       </div>
   </div>
   <c:if test="${type eq 'OPERATE' }">
   <input type="hidden" name="fieldId" value="${t.field.id }" />
   <div class="form-group">
       <label class="col-sm-2 control-label">上级权限:</label>
       <div class="col-sm-10">
	       <input type="text" class="form-control" value="${t.field.name }" readonly="readonly" />
       </div>
   </div>
   </c:if>
   <!-- 
   <div class="form-group">
       <label for="parentId" class="col-sm-2 control-label">上级菜单:</label>
       <div class="col-sm-10">
	       <select class="form-control" name="parentId">
	       	<option value="">无</option>
	       	   <c:forEach items="${menus }" var="menu">
	       	   	<option <c:if test="${menu.id eq parentId }">selected</c:if> value="${menu.id }">${menu.name }</option>
	       	   </c:forEach>
	       </select>
       </div>
   </div>
    -->
   <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">提交</button>
   </div>
</form>
