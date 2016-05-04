<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@ page contentType="text/html;charset=UTF-8"%>


<!doctype html>
<html>
<head>
<title>::Hello::</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<!-- 
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		-->
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="${pageContext.request.contextPath}/assets/images/rome-trevi.jpg"
						width='560px'>
					<h2>Welcome to JoonMin's site!</h2>
					<p>
						We are welcoming <em>your</em> visit sincerely.<br> This site is <span
							style="color: #FF0000">unique</span> and <span
							style="color: #FF0000">exotic</span> place.<br> Please look
						around My website and experience<br> what you would <span
							style="color: #FF0000">never</span> have in elsewhere.<br>
						<br> Leave a message on our <a href="/mysite/guestbook">Guest
							Log</a><br>
						</p>
					<h3>
						제출자: <span style="color: blue">서준민</span>
					</h3>
					

				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>