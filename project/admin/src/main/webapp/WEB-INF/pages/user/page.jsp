<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">用户列表</div>
<div class="row">
<div class="col-sm-offset-1">
	<button class="btn btn-primary" onclick="addPage('/user')">添加</button>
</div>
</div>
<table class="table table-bordered mgt-1">
	<thead>
		<tr>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>是否通过验证</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result }" var="t">
		<tr id="${t.id }">
			<td>${t.username }</td>
			<td>${t.realname }</td>
			<td><input type="checkbox" <c:if test="${t.verified }">checked="checked"</c:if> disabled="disabled"></td>
			<td>
				<button class="btn btn-primary" onclick="editPage('/user','${t.id }')">修改</button>
				<button class="btn btn-primary" onclick="del('/user','${t.id }')">删除</button>
			</td>
		</tr>
		</c:forEach>  
	</tbody>
</table>

<ul id="user-page"></ul>
<script>
	pagination('${page.totalPages }','${page.page }',"/user","${page.size }","#user-page");
</script>

