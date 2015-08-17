<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<my:admin>
<jsp:attribute name="user">
<shiro:user>  
<li><a>[<shiro:principal/>]您好</a></li>
<li><a href="<c:url value='/logout'/>">退出</a></li>
</shiro:user> 
</jsp:attribute>
</my:admin>