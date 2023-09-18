<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세보기</title>
</head>
<body>
	<c:import url="../default/header.jsp" />
	<h1>상세내용보기</h1>
	<div id="Con">
		<div>
			<h1>개인 정보</h1>
		</div>
		<div>
			<table border="1">
				<tr>
					<th>글번호</th>
					<td>${user.writeNo}</td>
					<th>작성자</th>
					<td>${user.id}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${user.title}</td>
					<th>등록일자</th>
					<td>${user.saveDate}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${user.content}</td>
					<td colspan="2">
						<img src="download?name=${user.imageFileName}" width=200 height=200>
						<b>${user.imageFileName}</b>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<c:if test="${user.id.equals(login)}">
							<button type="button" onclick="">수정하기</button>
							<button type="button" onclick="">삭제하기</button>
						</c:if>
						<button type="button" onclick="">답글달기</button>
						<button type="button" onclick="">리스트로 돌아가기</button>
					</td>
				</tr>
			</table>	
		</div>
	</div>		
</body>
</html>