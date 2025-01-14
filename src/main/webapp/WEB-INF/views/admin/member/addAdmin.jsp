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
							<div>
								<h3>관리자 추가</h3>
							</div>
							<form action="/admin/join" method="post" onsubmit="return validCheck(this);">
							    <div id="id_box">
							        <div id="ID_text">
							            <label for="id">ID:&nbsp;</label>
							        </div>
							        <div id="id_input">
							            <input type="text" id="id" name="id" placeholder="Insert into your new manager ID" 
							                value="${admin != null ? admin.id : ''}" 
							                ${admin != null ? 'readonly' : ''}>
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
							            <input type="text" id="name" name="name" placeholder="Insert into your new manager name" 
							                value="${admin != null ? admin.name : ''}">
							        </div>
							    </div>
							    <div id="level_btn_box">
							        <div id="level_set">
							            <div id="LEVEL_text">
							                <label for="level">LEVEL:&nbsp;</label>
							            </div>
							            <div id="level_select">
							                <select name="level" id="level">
							                    <option value="1" <c:if test="${admin != null && admin.level == 1}">selected</c:if>>1</option>
							                    <option value="2" <c:if test="${admin != null && admin.level == 2}">selected</c:if>>2</option>
							                    <option value="3" <c:if test="${admin != null && admin.level == 3}">selected</c:if>>3</option>
							                </select>
							            </div>
							        </div>
							        <div id="access_box">
									    <div id="ACCESS_text">
									        <p>ACCESS:&nbsp;</p>
									    </div>
									    <div id="access_select">
									    	<div class= "flex">
										        <input type="radio" id="access_active" name="access" value="ACTIVE"
										            <c:if test="${admin != null && admin.access == 'ACTIVE'}">checked</c:if> />
										        <label for="access_active">ACTIVE</label>
									        </div>
									        <div class= "flex">
										        <input type="radio" id="access_blocked" name="access" value="BLOCKED"
										            <c:if test="${admin != null && admin.access == 'BLOCKED'}">checked</c:if> />
										        <label for="access_blocked">BLOCKED</label>
									        </div>
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