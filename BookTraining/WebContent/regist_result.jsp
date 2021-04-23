<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!-- DTO를 참조하기 위해서 import 처리가 필요하다. -->
<%-- jstl의 core 라이브러리를 사용하기 위해 taglib를 이용한다. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록 결과</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid black;
}

th:nth-child(1) {
	width: 100px;
}
</style>
</head>
<body>
	<%@ include file="/include/header.jsp"%>
	<h2>도서 정보</h2>
	<%-- c:if 태그를 이용해 request 영역에 book이 있다면 내용을 출력한다. --%>
	<c:choose>
		<c:when test="${not empty book}">
			<table align="center" border="1">
			<tr>
				<td>도서번호</td>
				<td>${book.isbn}</td>
			</tr>
			<tr>
				<td>도서명</td>
				<td>${book.title}</td>
			</tr>
			<tr>
				<td>저자</td>
				<td>${book.author}</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>${book.price}</td>
			</tr>
			<tr>
				<td>설명</td>
				<td>${book.desc}</td>
			</tr>
		</table>
		</c:when>
		<c:otherwise>
			<p>등록된 도서가 없습니다.</p>
		</c:otherwise>
	</c:choose>
	<!-- 다시 도서를 등록할 수 있는 링크를 제공한다. -->
	<a href="regist.jsp">추가등록</a>
</body>
</html>