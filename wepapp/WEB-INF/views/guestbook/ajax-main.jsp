<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@page import="com.estsoft.mysite.vo.GuestBookVo"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	pageContext.setAttribute("newLine", "\r\n");
%>

<!doctype html>
<html>
<head>
<title>::Guest Book::</title>

<meta http-equiv="content-type" content="text/html; charset=utf-8">

<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">


<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
	var page = 1;
	var dialogDelete = null;
	var fetchList = function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/guestbook/ajax-list?p="
					+ page,
			type : "get",
			dataType : "json",
			data : "",
			success : function(response) {
				if (response.result != "success") {
					return;
				}
				if (response.data.length == 0) {
					//console.log("-----end-----")
					$("#btn-next").attr("disabled", "true")
					return;
				}

				//HTML 생성한 후 UL에 append
				$.each(response.data, function(index, vo) {
					//console.log(vo);
					$("#gb-list").append(renderHtml(vo));
				});
				page++;

			},
			error : function(xhr /*XMLHttpRequest*/, status, error) {
				console.error(status + ":" + error);
			}
		});
	};

	var renderHtml = function(vo) {
		//대신에 js template Library를 사용한다. ex)EJS,underscore.js
		var html = "<li id='li-"+vo.no+"'><table><tr>" + "<td>" + vo.name + "</td>" + "<td>"
				+ vo.regDate + "</td>"
				+ "<td><a href='#' class='a-del' data-no='" + vo.no + "'>삭제</a></td>" + "</tr><tr>"
				+ "<td colspan='3'>"
				+ vo.message.replace(/\r\n/g, "<br>").replace(/\n/g, "<br>")
				+ "</td>" + "</tr></table><br></li>";

		return html;
	};

	$(function() {

		$("#form-insert")
				.submit(
						function(event) {

							event.preventDefault();

							var name = $("input[name='name']").val();
							var passwd = $("input[name='passwd']").val();
							var content = $("#message").val();

							/* console.log(name);
							console.log(passwd);
							console.log(content); */

							this.reset();

							$.ajax({
										url : "${pageContext.request.contextPath}/guestbook/ajax-insert",
										type : "post",
										dataType : "json",
										data : "name="+ name
										+ "&passwd=" + passwd
										+ "&message=" + content,
										success : function(response) {
											$("#gb-list").prepend(
													renderHtml(response.data));
										},
										error : function(
												xhr /*XMLHttpRequest*/,
												status, error) {
											console.error(status + ":" + error);
										}

									})
						});

		$("#btn-next").on("click", function() {
			fetchList();
		});

		$(window).scroll(function() {
			var documentHeight = $(document).height();
			var windowHeight = $(window).height();
			var scrollTop = $(window).scrollTop();

			if ((scrollTop + windowHeight) >= (documentHeight - 50)) {
				fetchList();
			}
			//console.log(documentHeight + ":" + windowHeight + ":" + scrollTop);

		});

		//삭제 버튼 클릭 이벤트 매핑(Live Event)
		$(document).on("click", ".a-del", function(event) {
			event.preventDefault();

			//$("#dialogMessage").dialog();
			//console.log("delete clicked");
			
			var no = $(this).attr("data-no");
			$("#del-no").val(no);
			//console.log(no);
			
			dialogDelete.dialog("open");	

		});

		//dialogDelete 객체 생성
		dialogDelete = $("#dialog-form").dialog({
			autoOpen : false,
			height : 200,
			width : 350,
			modal : true,
			buttons : {
				"삭제" : function(){
					var no = $("#del-no").val();
					var password = $("#del-password").val();
					console.log(no+":"+password);
				},
				"취소" : function() {
					dialogDelete.dialog("close");
				}
			},
			close : function() {
				//form[0].reset();
				//allFields.removeClass("ui-state-error");
			}
		});

		//최초 데이터 가져오기
		fetchList();
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form id="form-insert">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="passwd"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="message"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE="확인 "></td>
						</tr>
					</table>
				</form>
				<ul id="gb-list">

				</ul>
				<div style="margin-top: 20px; text-align: right">
					<button id="btn-next">다음 가져오기</button>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>

	<div id="dialogMessage" title="Pop-up Dialog Example" style="display:none">
		<p>Hello World</p>
	</div>

	<div id="dialog-form" title="삭제">
		<p style="margin-top:20px" class="validateTips">비밀번호를 입력하세요.</p>

		<form>

			<label for="password">비밀번호</label> 
			<input type="hidden" id="del-no" value="">
			<input type="password"
				name="password" id="del-password" value=""
				class="text ui-widget-content ui-corner-all"> <input
				type="submit" tabindex="-1" style="position: absolute; top: -1000px">

		</form>
	</div>

</body>
</html>