<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">修改角色</div>
<form id="eidt-${t.id }" role="form" action="/department/${t.id }" class="form-horizontal ajax" method="post" redirect="/department">
   <div class="form-group">
       <label for="name" class="col-sm-2 control-label">名称:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="name" value="${t.name }">
       </div>
   </div>
   <div class="form-group">
       <label for="description" class="col-sm-2 control-label">描述:</label>
       <div class="col-sm-10">
           <input type="text" class="form-control" name="description" value="${t.description }">
       </div>
   </div>
   <div class="form-group">
       <label class="col-sm-2 control-label">选择角色权限:</label>
       <div class="col-sm-10">
           <div id="department-edit-${t.id }"></div>
       </div>
   </div>
   <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">提交</button>
   </div>
</form>

<script>
	(function(){
		var originRoles=[<c:forEach items="${t.roles }" var="r">{id:"${r.role.id}",permissions:[<c:forEach items="${r.permissions }" var="p">"${p.id }",</c:forEach>]},</c:forEach>];
		var permissionTree=new DepartmentRolePermissionTree("#department-edit-${t.id }",originRoles);
		$("#eidt-${t.id }").on('submit',function(e) {
			var tf=$(this);
			permissionTree.savePermissions();
			var roles=permissionTree.roles;
			var keys=roles.keys();
			for(var i=0;i<keys.length;i++) {
				var r=keys[i];
				var ps=roles.get(r);
				for(var j=0;j<ps.length;j++) {
					var p=ps[j];
					$(String.format("<input type='hidden' name='rolePermissions[{0}][{1}]' value='{2}'>",r,j,p))
					.appendTo(tf);
				}				
			}
			e.preventDefault();
		});
	})();
</script>
