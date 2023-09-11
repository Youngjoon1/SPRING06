<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<style type="text/css">
	#tableCon {
		margin-left: 500px;
	
	}
</style>
</head>
<body>
	<c:import url="../default/header.jsp" />
		<div id="tableCon">
		<h1>회 원 정 보</h1>
			<table border="1">
				<tr>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>주소</th>
				</tr>
				<c:forEach var="list" items="${userList}">
					<tr>
						<td><a href="info?id=${list.id}">${list.id}</a></td>
						<td>${list.pw}</td>
						<td>${list.addr}</td>
					</tr>
				</c:forEach>
				
			
			</table>
		</div>
	
</body>
</html>