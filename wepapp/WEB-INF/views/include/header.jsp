<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%/* @page import="com.estsoft.mysite.vo.UserVo" */ %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String inDate   = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
%>


		<div id="header">
			<h1><a href="${pageContext.request.contextPath}/main"><img src="${pageContext.request.contextPath}/assets/images/plogo.png" width="200px"/></a></h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a><li>
						<li><a href="${pageContext.request.contextPath}/user/joinform">회원가입</a><li>
						<li><%=inDate %></li>
					</c:when>
						
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/modifyform">정보수정</a><li>
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a><li>
						<li><span style="color:#79B30B">${sessionScope.authUser.name }</span>님 안녕하세요 :)</li>
					<li><%=inDate %>&nbsp; &nbsp;</li>
				</c:otherwise>
				</c:choose>
			</ul>
		</div>