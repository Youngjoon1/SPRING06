<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세보기</title>
<style type="text/css">
	#modal_wrap {
		display:none;
		position:fixed; z-index: 9; margin: auto;
		top : 0; left: 0; right: 0; width: 100%; height: 100%;
		background-color: rgba(0,0,0,0.4) 
	}
	
	#first {
		display:none;
		position:fixed; z-index: 10; margin: auto;
		top : 30px; left: 0; right: 0; width: 350px; height: 450px;
		background-color: silver;
	}

</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function slideClick() {
		$("#first").slideDown("slow");
		$("#modal_wrap").show();
	}
	
	function slide_hide() {
		$("#first").hide();
		$("#modal_wrap").hide();
	}

	function rep(){
	      let form = {};
	      let arr = $("#frm").serializeArray();
	      arr.forEach(data=>{
	         form[data.name] = data.value;
	      });
	      console.log( form )
	      $.ajax({
	         url : "addReply", type : "post", 
	         data : JSON.stringify( form ), 
	         contentType : "application/json; charset=utf-8",
	         success : () => {
	            alert("답글이 달렸습니다!!!!");
	            slide_hide();
	            
	         },
	         error : () => {
	            alert("문제 발생!!!");
	         }
	      });
	   }
		
	function replyData() {
		$.ajax({
			url : "replyData/"+${user.writeNo},
			type : "get", dataType : "json",
			success : (data) => {
				let txt = "";
				data.forEach((list)=>{
					txt += "아이디 : " + list.id+"<br>";
					
					var date = new Date(list.write_date);
					
					txt += "제목 : " + list.title+"<br>";  
					txt += "내용 : " + list.content+"<br>";  
					txt += "작성일 : " + date.toLocaleString()+"<br>";  
				});
				$("#reply").html(txt);
			},
			error : () => {
				alert("문제발생!!!");
			}
		});
	}
	window.onload = () => { replyData() }
	
</script>
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
					<td colspan="2"><img src="download?name=${user.imageFileName}"
						width=200 height=200> <b>${user.imageFileName}</b></td>
				</tr>
				<tr>
					<td colspan="4"><c:if test="${user.id.equals(login)}">
							<button type="button"
								onclick="location.href='modifyForm?writeNo=${user.writeNo}'">수정하기</button>
							<button type="button"
								onclick="location.href='delete?writeNo=${user.writeNo}&fileName=${user.imageFileName}'">삭제하기</button>
						</c:if>
						<button type="button" onclick="slideClick()">답글달기</button>
						<button type="button" onclick="location.href='boardAllList'">리스트로 돌아가기</button></td>
				</tr>
				<tr>
					<td id="reply" colspan="4">
						답글이 없습니다
					</td>
			 	</tr>
			</table>
			
			
			
			<div id="modal_wrap">
				<div id="first">
					<div style="width: 250px; margin: auto; padding-top: 20px">
						<h3 align="center">- 답글 페이지 -</h3>
						<form id="frm">
							<input type="text" name="write_group" value="${user.writeNo}"
								readonly><br> <b>작성자</b> ${ login }<br> <b>제목</b>
							<input type="text" name="title" size="30"><br> <b>내용</b>
							<textarea name="content"></textarea>
							<hr>
							<button type="button" onclick="rep()">답글</button>
							<button type="button" onclick="slide_hide()">취소</button>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>