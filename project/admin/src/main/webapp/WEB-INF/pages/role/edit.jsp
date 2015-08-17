<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">修改角色</div>
<form id="role-edit-form" role="form" action="/role/${t.id }" class="form-horizontal ajax" method="post" redirect="/role">
   <div class="form-group">
       <label for="name" class="col-sm-2 control-label">名称:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="name" value="${t.name }">
       </div>
   </div>
   <div class="form-group">
       <label for="type" class="col-sm-2 control-label">类型:</label>
       <div class="col-sm-10">
	       <select name="type">
	       	<option value="GENERIC" <c:if test="${t.type eq 'GENERIC' }">selected="selected"</c:if>>普通</option>
	       	<option value="DEPARTMENT" <c:if test="${t.type eq 'DEPARTMENT' }">selected="selected"</c:if>>部门</option>
	       </select>
       </div>
   </div>
   <div class="form-group">
       <label for="description" class="col-sm-2 control-label">描述:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="description" value="${t.description }">
       </div>
   </div>
   <div class="form-group">
       <label for="description" class="col-sm-2 control-label">选择权限:</label>
       <div class="col-sm-10">
           <div id="role-edit-permissions"></div>
       </div>
   </div>
   <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">提交</button>
   </div>
</form>

<script>	
	(function(){
		var origionPermissions=[<c:forEach items="${t.permissions }" var="p">"${p.id}",</c:forEach>];
		var permissionTree=new PermissionTree("#role-edit-permissions",origionPermissions);
		$("#role-edit-form").on('submit',function(e) {
			var permissions=permissionTree.getPermissions();
			for(var i=0;i<permissions.length;i++) {
				var p=permissions[i];
				var tf=$(this);
				$(String.format("<input type='hidden' name='permissionIds' value='{0}'>",p)).appendTo(tf);
			}
			e.preventDefault();
		});
	})();
</script>
