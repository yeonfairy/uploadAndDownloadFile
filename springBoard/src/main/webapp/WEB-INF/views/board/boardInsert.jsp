<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardInsert</title>
<style>
#container {
	width: 800px;
	margin: auto;
}
h2 {
	text-align: center;
}
table {
	width: 100%;
}
table td {
	border: 1px solid #ccc;
}
table input, table textarea {
	width: 99%;
}
textarea {
	min-height: 300px;
}

tr:nth-child(3) td {
	text-align: left;
}
tr:nth-child(4) td {
	text-align: right;
}
tr:nth-child(5) td {
	text-align: center;
}
</style>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<div id="container">
<h2>글쓰기</h2>
<form action="boardInsert.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="boardWriter" value="${ loginUser.userId }">
<input type="hidden" name="boardPwd">
<table>
	<tr>
		<td>제목</td>
		<td><input type="text" name="boardTitle"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="boardContent"></textarea></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td>
	 	<input type="file" name="file"><br/>
		</td>	
	</tr>
		<tr>
		<td colspan="2">
			<button type="button" id="pwdInput" onclick="pwdWindowOpen();">비밀번호 설정</button>
			<span id="pwdResult"> N </span>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button>저장</button>
			<button type="button" onclick="location.href='board.do'">취소</button>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<script>
function pwdWindowOpen(){
	window.open("inputPwd.do", null, "width=800 height=400");
}
function pwdSetting(str) {
	$("input[name=boardPwd]").val(str);
	$("#pwdResult").text(" Y ");
}
</script>
</html>