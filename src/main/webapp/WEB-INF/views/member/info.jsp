<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#Con{
		text-align: center;
	}	
</style>
</head>
<body>

	<c:import url="../default/header.jsp" />
	<div id="Con">
		<h1>${mem.dto.id} 정보</h1><br>
		<b>아이디 : ${mem.dto.id}</b><br>
		<b>비밀번호 : ${mem.dto.pw}</b><br>
		<b>addr1주소 : ${mem.addr1}</b><br>
		<b>addr2주소 : ${mem.addr2}</b><br>
		<b>addr3주소 : ${mem.addr3}</b><br>
		<a href="modify?id=${mem.dto.id}">수정하기</a><br>
		<a href="userDel?id=${mem.dto.id}">삭제하기</a><br>
		<a href="list">리스트로 가기</a>
	</div>
	

</body>
</html>