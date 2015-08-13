<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.table>tbody>tr>td.pdl-1 {padding-left:1em;	}
	.table>tbody>tr>td.pdl-2 {padding-left:2em;	}
	.table>tbody>tr>td.pdl-3 {padding-left:3em;	}
	.table>tbody>tr>td.pdl-4 {padding-left:4em;	}
	.table>thead>tr>th.pdl-1 {padding-left:1em;	}
	.table>thead>tr>th.pdl-2 {padding-left:2em;	}
	.table>thead>tr>th.pdl-3 {padding-left:3em;	}
	.table>thead>tr>th.pdl-4 {padding-left:4em;	}
</style>

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
