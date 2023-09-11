<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../default/header.jsp" %>
	<h3>list</h3>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>주소</th>
		</tr>
		<c:forEach var="user" items="${list}">
			<tr>
				<td><a href="info?id=${user.id}">${user.id}</a></td>
				<td>${user.pw}</td>
				<td>${user.addr}</td>
			</tr>
		</c:forEach>	
	</table>
	
</body>
</html>