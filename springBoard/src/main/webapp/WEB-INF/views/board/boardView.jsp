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
#inputVo td {
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
</br>
	<button type="button" onclick="location.href='insertForm.do'">글쓰기</button>
	<button type="button" onclick="location.href='excelDown.do'">엑셀 다운로드</button>
    <button type="button" onclick="location.href='excelFormDown.do'">엑셀양식 다운로드</button>
    <button type="button" id="btn" onclick="popupOpen('fileUpload.do')"> 엑셀 업로드 </button>
    </br></br>
	<table id="board">
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
	</table>
			<h2>입력값 메인화면 조회</h2>
    <button type="button" id="btn" onclick="popupOpen2('excelUpload.do')"> 입력값 합게 구하기 </button>   
	</br></br>
	<table id="inputVo">
		<tr>
			<td>번호</td>
			<td>입력값1</td>
			<td>입력값2</td>
			<td>입력값3</td>
			<td>입력값4</td>
			<td>입력값5</td>
	        <td>합계</td>	
		</tr>
		<tbody>
		<c:forEach var="i" items="${ iList }">
		<tr>
			<td>${ i.no }</td>
			<td>${ i.inputValue1 }</td>
			<td>${ i.inputValue2 } </td>
			<td>${ i.inputValue3 }</td>		
			<td>${ i.inputValue4 }</td>
			<td>${ i.inputValue5 } </td>
			<td>${ i.totalValue }</td>		
		</tr>
		</c:forEach>
		</tbody>
	</table></br></br>
</div>
</body>
<script>
var winObject = null ;
function openWindow(num){
	windObject = window.open("checkPwd.do?bNo="+num, null, "width=300 height=200");
	window.location.reload(true);
}
</script>
<script type="text/javascript">
function popupOpen(){
	var popUrl = "fileUpload.do";	//팝업창에 출력될 페이지 URL
	var popOption = "width=1000, height=700, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	}
function popupOpen2(){
	var popUrl = "excelUpload.do";	//팝업창에 출력될 페이지 URL
	var popOption = "width=1000, height=700, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	}
</script>
</html>