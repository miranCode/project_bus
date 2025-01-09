<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 추가");
    request.setAttribute("bodyClass", "member");
%>
<jsp:include page="../inc/header.jsp" />
            	<!-- #content 영역 시작 -->
				<div id="content">
					<div id="insert_manager_box">
						<form action="" method="post" onsubmit="validCheck(this);">
							<div id="id_box">
								<label for="id">ID:&nbsp;</label>
								<div id="id_input">
									<input type="text" id= "id" placeholder="Insert into your new manager ID">
								</div>
							</div>
							<div id="pw_box">
								<label for="pw">PW:&nbsp;</label>
								<div id="pw_input">
									<input type="password" id="pw" placeholder="Insert into your new manager PW">
								</div>
							</div>
							<button type="submit" id="submitbtn">ADD</button>
							<button type="reset" id="resetbtn">RESET</button>
						</form>
					</div>
				</div>
				<script src="/resources/js/admin/addAdmin.js"></script>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />