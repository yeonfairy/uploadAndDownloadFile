<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
<style>
#container {
	width: 800px;
	text-align: center;
	margin: auto;
}
#board td {
	border: 1px solid #ccc;
	width: 200px;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<div id="container">
<h2>메인화면 조회</h2>
	<table id="board">
	</br></br>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
			<td>첨부파일</td>
		</tr>
		<tbody>
		<c:forEach var="b" items="${ bList }">
		<tr>
			<td>${ b.boardNo }</td>
			<c:choose>
			<c:when test="${ !empty b.boardPwd }">
			<td><a href="javascript:openWindow(${ b.boardNo });">${ b.boardTitle }</a></td>
			</c:when>
			<c:otherwise>
			<td><a href="boardDetail.do?bNo=${ b.boardNo }">${ b.boardTitle }</a></td>
			</c:otherwise>
			</c:choose>
			<td>${ b.boardWriter }</td>
			<td>${ b.boardCount } </td>
			<td>${ b.orgFileName }</td>			
		</tr>
		</c:forEach>
		</tbody>
	</table></br></br>
	<button type="button" onclick="location.href='insertForm.do'">글쓰기</button>
</div>
</body>
<script>
var winObject = null ;
function openWindow(num){
	windObject = window.open("checkPwd.do?bNo="+num, null, "width=300 height=200");
}
</script>
</html>