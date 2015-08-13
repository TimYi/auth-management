<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="title">权限列表</div>
<div class="row">
<div class="col-sm-offset-1">
	<button class="btn btn-primary" onclick="addPage('/permission')">添加</button>
</div>
</div>
<table class="table table-bordered mgt-1">
	<thead>
		<tr>
			<th>名称</th>
			<th>代码</th>
			<th>描述</th>
			<th>类型</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result }" var="t">
		<tr id="${t.id }">
			<td>${t.name }</td>
			<td>${t.code }</td>
			<td>${t.description }</td>
			<td>${t.type }</td>
			<td>
				<button class="btn btn-primary" onclick="addPage('/permission','parentId=${t.id }&type=OPERATE')">添加</button>
				<button class="btn btn-primary" onclick="editPage('/permission','${t.id }')">修改</button>
				<button class="btn btn-primary" onclick="del('/permission','${t.id }')">删除</button>
			</td>
		</tr>
		<c:if test="${!empty t.operations }">
		<c:forEach items="${t.operations }" var="o">
			<tr id="${o.id }">
				<td class="pdl-1">${o.name }</td>
				<td class="pdl-1">${o.code }</td>
				<td class="pdl-1">${o.description }</td>
				<td class="pdl-1">${o.type }</td>
				<td>
					<button class="btn btn-primary" onclick="editPage('/permission','${o.id }')">修改</button>
					<button class="btn btn-primary" onclick="del('/permission','${o.id }')">删除</button>
				</td>
			</tr>
		</c:forEach>
		</c:if>
		</c:forEach>  
	</tbody>
</table>

<ul id="pagination"></ul>
<script>
	pagination('${page.totalPages }','${page.page }',"/permission","${page.size }");
</script>

