<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">菜单列表</div>
<div class="row">
<div class="col-sm-offset-1">
	<button class="btn btn-primary" onclick="addPage('/menu')">添加</button>
</div>
</div>
<table class="table table-bordered mgt-1">
<thead>
	<tr>
		<th>序号</th>
		<th>名称</th>
		<th>url</th>
		<th>访问权限</th>
		<th>操作</th>
	</tr>
</thead>
<tbody>
<c:set var="offset" value="${0}" scope="request"/> 
<jsp:include page="menus.jsp"/>  
</tbody>
</table>

<script type="text/javascript">
	function addMenu(id) {
		var url="/menu/add"+"?id="+id;
		renderBody(url);
	}
</script>
