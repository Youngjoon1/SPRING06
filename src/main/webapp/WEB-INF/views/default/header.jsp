<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
   contextPath : ${contextPath }
   
   <h1>CARE LAB</h1>
   <a href=" ${contextPath }/index">HOME</a>
   <c:choose>
   		<c:when test="${login == null }">
   			<a href=" ${contextPath }/member/login">LOGIN</a>
   			<a href=" ${contextPath }/member/login">MEMBERS</a>
   		</c:when>
   		<c:otherwise>
   			<a href=" ${contextPath }/member/logout">LOGOUT</a>
   			<a href=" ${contextPath }/member/list">MEMBERS</a>
   		</c:otherwise>
   </c:choose>
   
		
	<hr>
</body>
</html>