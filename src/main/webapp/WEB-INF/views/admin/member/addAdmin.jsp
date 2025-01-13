<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 추가");
    request.setAttribute("bodyClass", "member");
%>
<jsp:include page="../inc/header.jsp" />
            	<!-- #content 영역 시작 -->
            	<link rel="stylesheet" href="/resources/css/admin/addAdmin.css">
				<div id="content">
					<div id="addAdmin_area">
						<div id="insert_manager_box">
							<form action="/admin/join" method="post" onsubmit="return validCheck(this);">
								<div id="id_box">
									<div id="ID_text">
										<label for="id">ID:&nbsp;</label>
									</div>
									<div id="id_input">
										<input type="text" id="id" name="id" placeholder="Insert into your new manager ID">
									</div>
								</div>
								<div id="pw_box">
									<div id="PW_text">
										<label for="pw">PW:&nbsp;</label>
									</div>
									<div id="pw_input">
										<input type="password" id="pw" name="pw" placeholder="Insert into your new manager PW">
									</div>
								</div>
								<div id="name_box">
									<div id="NAME_text">
										<label for="name">NAME:&nbsp;</label>
									</div>
									<div id="name_input">
										<input type="text" id="name" name="name" placeholder="Insert into your new manager name">
									</div>
								</div>
								<div class="flex ju-between al-center">
									<div id="level_set">
										<div id="LEVEL_text">
											<label for="level">LEVEL:&nbsp;</label>
										</div>
										<div id="level_select">
											<select name="level" id="level">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
											</select>
										</div>
									</div>
									<div id="btn_box">
										<button type="submit" id="submitbtn">ADD</button>
										<button type="reset" id="resetbtn">RESET</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<script src="/resources/js/admin/addAdmin.js"></script>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />