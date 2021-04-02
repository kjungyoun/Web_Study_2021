<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.backend.dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="price" value="3000,5000"/>
<c:set var="totla" value="0"/>
<c:forEach var="p" items='${price}'>
	<c:set var="total" value="${total+p}"/>
	${p} ${total}<br/>
</c:forEach>

<%
Book book = new Book();
pageContext.setAttribute("book", book);
%>

<!-- 특정 Scope에 data가 없는 경우 에러가 발생! -->
<c:set target="${book}" property="isbn" value="1"/>
isbn : ${book.isbn}

</body>
</html>