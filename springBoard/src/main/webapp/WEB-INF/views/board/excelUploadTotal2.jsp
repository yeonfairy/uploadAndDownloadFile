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
<script>
	src = "https://code.jquery.com/jquery-3.4.1.min.js"
	integrity = "sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin = "anonymous" >
</script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<div id="container">
		<h2>파일 업로드</h2>
		<form action="excelUploadTotal2.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="boardWriter" value="${ loginUser.userId }">
			<input type="hidden" name="boardPwd">
			<table>
				<tr>
					<td>엑셀 첨부파일 </br>(*xls만 업로드 가능)
					</td>
					<td><input id="excelFile" type="file" name="excelFile" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<button>저장 </button>
						<button type="button" onClick="window.open('','_self').close();">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script>

/* 
  function checkFileType(filePath) {
		alert("checkFileType");
		var fileFormat = filePath.split(".");
		if (fileFormat.indexOf("xls") > -1) {
			return true;
		} else {
			return false;
		}
	}
	 */
/* 	function check() {
		 
		var file = $("#excelFile").val();
		if (file == "" || file == null) {
			alert("파일을 선택해주세요.");
			return false;
		} else if (!checkFileType(file)) {
			alert("엑셀 파일만 업로드 가능합니다.");
			return false;
		}
		if (confirm("업로드 하시겠습니까?")) {
			var options = {
				success : function(data) {
					alert("모든 데이터가 업로드 되었습니다."+data);
				},
				type : "POST"
			};
			alert("bbbb");
			$("board.do").ajaxSubmit(options);
		}
	} */
</script>
</html>