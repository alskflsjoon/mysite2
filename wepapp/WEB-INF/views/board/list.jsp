<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<h4><span style="color:#FF0000">댓글은 3중까지만 가능합니다</span></h4>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }"></c:set>
					<c:forEach items="${map.list }" var="vo" varStatus="status">				
					<tr>
						<td>${map.forIndex - (map.pNo-1)*3 - status.index }</td>
						<td style="text-align:left; padding-left:${vo.depth*20}px">
						<c:if test="${vo.depth>0 }">
						<img src="${pageContext.request.contextPath}/assets/images/reply.png">
						</c:if>
						<a href="${pageContext.request.contextPath}/board/view/${vo.no}">${vo.title}</a></td>
						<td>${vo.userName }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						<td>
						<c:if test="${authUser.no==vo.userNo }">
						<a href="${pageContext.request.contextPath }/board/delete/${vo.no}" class="del">삭제</a>
						</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				
				<div class="pager">
					<ul>
						<c:if test="${map.pNo>=4 }">
							<li><a href="${pageContext.request.contextPath}/board?kwd=${map.keywd }&pNo=${ (map.pCn-1)*3+3}">◀</a></li>
    				    </c:if>
    				    <c:if test="${map.pCn*3+1<=map.Tcount}">
							<c:choose>
								<c:when test="${(map.pNo%3)==1}">
	    				    		<li class="selected">${ map.pNo}</li>
	    				    	</c:when>
	    				    	<c:otherwise>
	    				    		<li><a href="${pageContext.request.contextPath}/board?kwd=${map.keywd }&pNo=${ map.pCn*3+1}">${ map.pCn*3+1}</a></li>
	    				    	</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${map.pCn*3+2<=map.Tcount}">
							<c:choose>
								<c:when test="${(pNo%3)==2}">
	    				    		<li class="selected">${ map.pNo}</li>
	    				    	</c:when>
	    				    	<c:otherwise>
	    				    		<li><a href="${pageContext.request.contextPath}/board?kwd=${map.keywd }&pNo=${ map.pCn*3+2}">${ map.pCn*3+2}</a></li>
	    				    	</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${map.pCn*3+3<=map.Tcount}">
							<c:choose>
								<c:when test="${(map.pNo%3)==0}">
	    				    		<li class="selected">${ map.pNo}</li>
	    				    	</c:when>
	    				    	<c:otherwise>
	    				    		<li><a href="${pageContext.request.contextPath}/board?kwd=${map.keywd }&pNo=${ map.pCn*3+3}">${ map.pCn*3+3}</a></li>
	    				    	</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${(map.pCn*3+3)< map.Tcount }">
							<li><a href="${pageContext.request.contextPath}/board?kwd=${map.keywd }&pNo=${ (map.pCn+1)*3+1}">▶</a></li>
    				    </c:if>
					</ul>
				</div>
				<form id="search_form" action="${pageContext.request.contextPath}/board" method="post">
					내용&#38;제목 검색 <input type="text" id="kwd" name="kwd" value="${map.keywd }">
					<input type="submit" value="찾기">
				</form>
				<div class="bottom">
					<c:if test="${!empty authUser }">
					<a href="${pageContext.request.contextPath}/board/writeForm" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html> 