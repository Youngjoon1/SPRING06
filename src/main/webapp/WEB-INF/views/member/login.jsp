<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>login</title>
</head>
<body>
	member -> login.jsp
	<%@ include file="../default/header.jsp" %>
	${contextPath}
	<form action="${contextPath}/member/loginChk" method="post">
		<input type="text" name="id"><br>
		<input type="text" name="pw"><br>
		<input type="submit" value="로그인"><br>
		<a href="${contextPath}/member/register">회원가입</a><br>
		<input type="checkbox" name="autoLogin">자동로그인
	</form>
	
	
</body>
</html>