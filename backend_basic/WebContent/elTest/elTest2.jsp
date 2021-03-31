<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 Cookie cookie = new Cookie("id","ssafy");
	response.addCookie(cookie);
%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	isbn : ${param.isbn}<br/>
	isbn : ${paramValues.isbn[0] }<br/>
	cookie: ${cookie.id.value}<br/>
</body>
</html>