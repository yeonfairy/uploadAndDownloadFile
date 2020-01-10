<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ page session="true" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>ЖДАП ѕч·Оµе</title>
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
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1251" >
    <form name="file" role="form" method="post" enctype="multipart/form-data"> 
    <script>
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	</head>
	<body>
	<table>
		<h1>їўјї ЖДАП ѕч·Оµе</h1><br /></br >
	<tr>
		<td>Г·єОЖДАП ѕч·Оµе</td>
		<td>
	 	<input type="file" name="file"><br/>
		</td>	
	</tr>
	</table>
	</body>
</html>