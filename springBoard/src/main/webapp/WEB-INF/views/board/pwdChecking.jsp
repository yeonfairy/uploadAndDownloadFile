<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pwd Checking</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<table>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" id="checkpwd"></td>
		<td><button type="button" id="btn">확인</button></td>
	</tr>
</table>
</body>
<script>
$(function(){
	$("#btn").on("click", function(){
		var pwd = $("#checkpwd").val().trim();
		if ( pwd.length == 0 ) return false;
		$.ajax({
			url: "checkcheck.do",
			data: { pwd : pwd,
					bNo : ${ bNo }},
			type: "POST",
			error: function(e) { console.log(e); },
			success: function(result){
				if (result == 1) {
					opener.location.href='boardDetail.do?bNo='+"${ bNo }";
					self.close();
				} else {
					alert("비밀번호가 일치하지 않습니다");
					opener.location.href='board.do';
					self.close();
				}
			}
		});
	})
});
</script>
</html>