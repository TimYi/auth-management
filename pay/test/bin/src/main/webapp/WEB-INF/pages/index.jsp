<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>backetend</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css'/>" type="text/css" rel="stylesheet"/>
</head>
<body>	
	<div class="container">
	<div class="row">
      <c:forEach items="${channels }" var="channel">      	
      	<div class="col-sm-6"><button onclick="wap_pay('${channel}')" class="btn btn-default">${channel }</button></div>
      </c:forEach>
    </div>
    </div>
    <script src="<c:url value='/resources/js/jquery-1.11.3.min.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/js/pingpp_pay.js'/>"></script>
    <script>
    	function wap_pay(channel) {
    		$.ajax({
    			url:"charge",
    			data:{channel:channel},
    			dataType:"json",
    			success:function(data) {
    				if(data.status=="OK") {
    					var charge=data.result;
    					pingpp.createPayment(charge, function(result, err) {
    	                    console.log(result);
    	                    console.log(err);
    	                });
    				} else {
    					alert(data.errorDescription);
    				}
    			}
    		});
    	}
    </script>
</body>
</html>