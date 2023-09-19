<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../default/header.jsp" %>
	<table border="1">
		<tr>
			<th>글 번호(writeNo)</th>
			<th>작성자(id)</th>
			<th>제목(title)</th>
			<th>날짜(saveDate)</th>
			<th>조회수(hit)</th>
			<th>파일명(imageFileName)</th>
		</tr>
		
		<c:if test="${ list.size() == 0 }">
		<tr>
			<th colspan="6">작성된 글이 없습니다</th>
		</tr>
		</c:if>
		
		<c:forEach var="dto" items="${ list }">
		<tr>
			<td>${dto.writeNo }</td>
			<td>${dto.id }</td>
			
			<td>
				<a href="content_view?writeNo=${dto.writeNo }">
					${dto.title }
				</a>
			</td>
			
			<td>${dto.saveDate }</td>
			<td>${dto.hit }</td>
			<td>${dto.imageFileName }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<a href="writeForm">글작성</a>
			</td>
		</tr>
	</table>
</body>
</html>










