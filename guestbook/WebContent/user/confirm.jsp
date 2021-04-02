<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-lg-6" align="right">
	<strong>${userinfo.userName}</strong>님 환영합니다. 
	<a href="${root}/main?act=logout">로그아웃</a>
</div>