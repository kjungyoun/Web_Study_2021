<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% System.out.println("plus_ajax.jsp  수행중... "); %>
{ 
	"op1": ${param.op1},
	"op2" : ${param.op2},
	"result" :${param.op1+param.op2}  
}