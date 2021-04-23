<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
</head>
<body>
	<%@ include file="/include/header.jsp" %>
	<h2>도서 목록</h2>
	<table align="center" border="1">
		<thead><tr><th>번호</th><th>isbn</th><th>제목</th><th>저자</th><th>가격</th></tr></thead>
		<tbody>
			<c:forEach var="book" items="${books}" varStatus='cnt'>
				<tr>
					<td>${cnt.count}</td>
					<td>${book.isbn }</td>
					<td><a href='${root}/main?act=search&isbn=${book.isbn}'>${book.title}</a></td>
					<td>${book.author}</td>
					<td>${book.price }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>