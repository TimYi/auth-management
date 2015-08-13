<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${menus }" var="menu">
	<c:if test="${menu.type eq 'URL' }" var="URL">
	<tr class="active" id="${menu.id }">
		<td class="pdl-${offset }">${menu.ordernum }</td>
		<td class="pdl-${offset }">${menu.name }</td>
		<td class="pdl-${offset }">${menu.url }</td>
		<td class="pdl-${offset }">${menu.permission }</td>
		<td>
			<button class="btn btn-primary" onclick="editPage('/menu','${menu.id }')">修改</button>
			<button class="btn btn-primary" onclick="del('/menu','${menu.id }')">删除</button>
		</td>
	</tr>
	</c:if>
	<c:if test="${!URL }">
		<tr class="success" id="${menu.id }">
			<td class="pdl-${offset }">${menu.ordernum }</td>
			<td class="pdl-${offset }">${menu.name }</td>
			<td class="pdl-${offset }"></td>
			<td class="pdl-${offset }">${menu.permission }</td>
			<td>
				<button class="btn btn-primary" onclick="editPage('/menu','${menu.id }')">修改</button>
				<button class="btn btn-primary" onclick="del('/menu','${menu.id }')">删除</button>
			</td>
		</tr>
		 <c:set var="menus" value="${menu.subMenus}" scope="request"/> 
		 <c:set var="offset" value="${offset+1}" scope="request"/> 
		 <jsp:include page="menus.jsp"/> 
		 <c:set var="offset" value="${offset-1}" scope="request"/> 
	</c:if>
</c:forEach>