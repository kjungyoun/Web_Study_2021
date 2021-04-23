<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#registForm>label {
	display: inline-block;
	width: 100px;
}
</style>
</head>
<body>
	<%@ include file="/include/header.jsp"%>
	<h2>도서 등록</h2>
	<form method="post" action="${root }/main" id="registForm">
		<input type="hidden" name="act" value="regist">
		<table align="center" border="1">
			<tr>
				<td>도서번호</td>
				<td><input type='text' id='isbn' name='isbn'/></td>
			</tr>
			<tr>
				<td>도서명</td>
				<td><input type='text' id='title' name='title'/></td>
			</tr>
			<tr>
				<td>저자</td>
				<td><input type='text' id='author' name='author'/></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type='text' id='price' name='price'/></td>
			</tr>
			<tr>
				<td>설명</td>
				<td><textarea rows='5' cols='23' id="desc" name='desc'></textarea></td>
			</tr>
			<tr>
				<td colspan='2'><input type="submit" value="도서 등록" id="regist"></td>
			</tr>
		</table>
	</form>
</body>
<script>

</script>
</html>
