<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 페이지</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/root/resources/board/js/img_view.js"></script>
</head>
<body>
	<c:import url="../default/header.jsp" />
	<form method="post" action="modify" enctype="multipart/form-data">
		<input type="hidden" name="imageFileName" value="${dto.imageFileName}">
		<b>글번호</b>
		<input type="text" name="writeNo" value="${dto.writeNo}" readonly>
		<br>
		<b>작성자</b>
		<input type="text" name="id" value="${dto.id}" readonly>
		<br>
		<b>제목</b>
		<input type="text" name="title" value="${dto.title}">
		<br>
		<b>내용</b>
		<textarea rows="15" cols="30" name="content">${dto.content}</textarea>
		<br>
		<b>이미지</b>
		<input type="file" name="imgFileName" id="fileName" onchange="readURL(this)"><br>
		<img id="preview" src="download?name=${dto.imageFileName}" width="150" height="150" alt="선택없음"><br>
		
		<input type="submit" value="수정"><br>
		
		<a href="boardAllList">목록으로 이동</a>
	</form>
</body>
</html>