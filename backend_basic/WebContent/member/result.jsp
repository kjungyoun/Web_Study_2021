<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.ssafy.backend.dto.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// request 바구니에서 객체를 뽑아옴
	Book book = (Book) request.getAttribute("book");
%>
<table align="center" border="1">
	<caption>등록한 책 정보</caption>
	<tr><td>isbn</td><td><%=book.getIsbn() %></td></tr>
	<tr><td>제목</td><td><%=book.getTitle() %></td></tr>
	<tr><td>저자</td><td><%=book.getAuthor() %></td></tr>
	<tr><td>가격</td><td><%=book.getPrice() %></td></tr>
	<tr><td>책정보</td><td><%=book.getDesc() %></td></tr>
</table>
</body>
</html>