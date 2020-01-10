<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pwdSetting</title>
<style>
#pwdBtn {
	width: 100%;
	min-height: 100%;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>

<body>
<table>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pwd1"></td>
		<td rowspan="2"><button type="button" id="pwdBtn" onclick="pwdCheck();">저장</button></td>
	</tr>
	<tr>
		<td>비밀번호 재입력</td>
		<td><input type="password" name="pwd2"></td>			
	</tr>
</table>
</body>
<script>
function pwdCheck(){
	var pwd1 = $("input[name=pwd1]").val().trim();
	var pwd2 = $("input[name=pwd2]").val().trim();
	
	if ( pwd1 != pwd2 ) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	} else if ( pwd1.length == 0 ) {
		return false;
	}
	
	opener.pwdSetting(pwd1);
	self.close();
}
</script>
</html>