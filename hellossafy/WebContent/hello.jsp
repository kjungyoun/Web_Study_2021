<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Date, java.text.*"
    %>
<% 
DateFormat df = new SimpleDateFormat("HH:mm:ss");
Date date = new Date(); 
String time = df.format(date);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	HelloSSAFY <br>
	안녕 싸피!!
	시간: <%= time %>
	</div>
</body>
</html>