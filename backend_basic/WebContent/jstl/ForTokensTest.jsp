<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ssafy.backend.dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
	pageContext.setAttribute("music","벚꽃앤딩,여수밤바다");
	pageContext.setAttribute("price", "1000,2000,3000");
	pageContext.setAttribute("price2", "1000/2000/3000");
	
	String[] p={"1000","2000"};
	pageContext.setAttribute("p",p);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	music:
	<c:forEach items="${music}" var="m">
		${m}<br/>
	</c:forEach>
	
	<c:set var='total'/>
	<c:forEach items="${price}" var="p1">
		${p1} 
		<c:set var="total" value='${total+p1}'/> ${total}<br/>
	</c:forEach>
	<c:forEach items="${p}" var="p">
		${p}
		<c:set var="total" value='${total+p}'/> ${total}<br/>
	</c:forEach>
	<br/>
	<br/>
	<c:forTokens items="${price}" delims="," var="p1">
		${p1}
	</c:forTokens>
	<br/>
	<c:forTokens items="${price2}" delims="/" var="p2">
		${p2}
	</c:forTokens>
</body>
</html>