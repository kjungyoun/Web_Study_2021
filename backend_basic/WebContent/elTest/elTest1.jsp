<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.backend.dto.*" %>
    
<%
	pageContext.setAttribute("music","벚꽃앤딩,여수밤바다");
	pageContext.setAttribute("price", "1000,2000,3000");
	
	String[] p={"1000","2000"};
	pageContext.setAttribute("p",p);
	
	Book book = new Book("1","처음하는 servlet","youn",3000,"너무 어려움");
	pageContext.setAttribute("book", book);
	pageContext.setAttribute("author", "title");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
music: ${pageScope.music} , ${music}<br/>
price: ${pageScope.price} , ${price}<br/>
p	 : ${pageScope.p['0']} , ${p['0']} , ${p[0]} <br/>
book 정보 <br/>
	isbn : ${pageScope.book.isbn}
	${book.isbn}
	${book['isbn']}<br/>
저자 정보 <br/>
	${pageScope.book.author}
	${book.author}
	${book['author']}
	${book[author]} <br/>
</body>
</html>