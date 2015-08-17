<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">访问权限列表</div>
<div class="row">
<div class="col-sm-offset-1">
	<button class="btn btn-primary" onclick="addPage('/access')">添加</button>
	<button class="btn btn-primary" onclick="ajax({method:'POST',url:'/access/refresh',success:function(data){alert(data)}})">刷新权限</button>
</div>
</div>
<table class="table table-bordered mgt-1">
	<thead>
		<tr>
			<th>序号</th>
			<th>访问地址</th>
			<th>访问权限</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result }" var="t">
		<tr id="${t.id }">
			<td>${t.ordernum }</td>
			<td>${t.url }</td>
			<td>${t.permission }</td>
			<td>
				<button class="btn btn-primary" onclick="editPage('/access','${t.id }')">修改</button>
				<button class="btn btn-primary" onclick="del('/access','${t.id }')">删除</button>
			</td>
		</tr>
		</c:forEach>  
	</tbody>
</table>

<ul id="access-page"></ul>
<script>
	pagination('${page.totalPages }','${page.page }',"/access","${page.size }","#access-page");
</script>

