<%@page import="com.estsoft.mysite.vo.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!doctype html>
<html>
<head>
<title>::Modify::</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="modify-form" name="ModifyForm" method="post" action="/mysite/user/modify">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${authVo.name }">

					<label class="block-label">비밀번호</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="F" 
						<c:if test='${authVo.gender == "F" }'>
						checked="checked"
						</c:if>
						>
						<label>남</label> <input type="radio" name="gender" value="M"
						<c:if test='${authVo.gender == "M" }'>
						checked="checked"
						</c:if>
						>
					</fieldset>
					
					<h5><span style="color:#FF0000">빈 칸이 있으면 안됩니다.</span></h5>
													
					<input type="submit" value="modify">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>