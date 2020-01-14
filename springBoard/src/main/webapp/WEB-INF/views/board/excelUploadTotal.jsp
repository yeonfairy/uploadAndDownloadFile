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
		<h2>(엑셀) 입력값 합계 구하기</h2>
		<form action="excelUploadTotal.do" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>엑셀 첨부파일 </br>(*xls만 업로드 가능)
					</td>
					<td><input id="excelFile" type="file" name="excelFile" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="button" id="addExcelImpoartBtn" class="btn"
							onclick="check()">
							<span>추가</span>
						</button>
						<button>저장</button>
						<form>
							<button type="button" onClick="window.open('','_self').close();">취소</button>
						</form>
					</td>
				</tr>			
			</table>
			</br>
			<h2>입력값 메인화면 조회</h2>
	<table id="board">
	</br></br>
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
		</form>
	</div>
</body>
<script>
	function checkFileType(filePath) {
		var fileFormat = filePath.split(".");
		if (fileFormat.indexOf("xls") > -1) {
			return true;
		} else {
			return false;
		}
	}
	function check() {
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
					alert("모든 데이터가 업로드 되었습니다.");
				},
				type : "POST"
			};
			$("excelUploadTotal.do").ajaxSubmit(options);
		}
	}
</script>
</html>