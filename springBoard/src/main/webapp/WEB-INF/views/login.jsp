<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style>
#container {
	width: 300px;
	text-align: center;
	margin: 100px auto;
}
#loginArea td {
	board: 1px solid;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<body>
	<div id="container">
	<form action="login.do">
		<table id="loginArea">
			<tr>
				<td>ID</td>
				<td><input type="text" name="userId" required></td>
				<td rowspan="3"><button id="loginBtn">로그인</button></td>
			</tr>
			<tr> 
				<td>PW</td>
				<td><input type="password" name="userPwd" required></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
					<label>로그인 유지 <input type="checkbox" name="loginC"></label>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
<script>
$(function(){
	$("#loginBtn").on("click", function(){
		$()
	});
});
</script>
</html>