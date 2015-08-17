<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">部门列表</div>
<div class="row">
<div class="col-sm-offset-1">
	<button class="btn btn-primary" onclick="addPage('/department')">添加</button>
</div>
</div>
<table class="table table-bordered mgt-1">
	<thead>
		<tr>
			<th>名称</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result }" var="t">
		<tr id="${t.id }">
			<td>${t.name }</td>
			<td>${t.description }</td>
			<td>
				<button class="btn btn-primary" onclick="editPage('/department','${t.id }')">修改</button>
				<button class="btn btn-primary" onclick="del('/department','${t.id }')">删除</button>
			</td>
		</tr>
		</c:forEach>  
	</tbody>
</table>

<ul id="department-page"></ul>
<script>
	pagination('${page.totalPages }','${page.page }',"/department","${page.size }","#department-page");
</script>

