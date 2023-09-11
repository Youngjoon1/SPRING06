<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/root/resources/member/js/daumPost.js" ></script>
<style type="text/css">
	#Con {
		text-align: center;
	}
</style>
</head>
<body>
	<c:import url="../default/header.jsp" />
	<div id="Con">
		<h1>회원수정</h1>
		<form action="userModify" method="post">
			<input type="text" value="${info.dto.id}" name="id" readonly><br>
			<input type="text" name="pw"><br>
			<input type="text" value="${info.addr1}" id="addr1" readonly name="addr"><br>
			<input type="text" value="${info.addr2}" id="addr2" readonly name="addr"><br>
			<input type="text" value="${info.addr3}" id="addr3" name="addr"><br>
			<button type="button" onclick="daumPost()">주소검색</button>
			<br>
			<input type="submit" value="수정">
		</form>
	</div>
	
</body>
</html>