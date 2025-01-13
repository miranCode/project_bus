<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "권한 설정");
    request.setAttribute("bodyClass", "member");
%>
<jsp:include page="../inc/header.jsp" />
            	<!-- #content 영역 시작 -->
            	<link rel="stylesheet" href="/resources/css/admin/manageAccount.css">
						<div id="content">
							<div id="manageAccount_area">
								<div id="account_box">
									<form action="/admin/updateAccount" method="post">
										<div id="list_box">
										<ul id="adminList">
									        <c:forEach var="admin" items="${adminList}">
									            <li>
									                <!-- 체크박스를 이용해 선택 -->
									                <input type="checkbox" name="selectedIds" value="${admin.id}" class="admin-checkbox" />
									                ${admin.id} &nbsp-&nbsp ${admin.name} &nbsp-&nbsp ${admin.level} &nbsp-&nbsp ${admin.access}
									            </li>
									        </c:forEach>
									    </ul>
										</div>
									    <!-- 페이지네이션 -->
								        <div id="pagination">
										    <c:if test="${currentPage > 1}">
										        <a href="javascript:void(0);" class="page-link" data-page="${currentPage - 1}">&laquo; 이전</a>
										    </c:if>
										
										    <c:forEach begin="1" end="${totalPage}" var="i">
										        <c:choose>
										            <c:when test="${i == currentPage}">
										                <span>${i}</span>
										            </c:when>
										            <c:otherwise>
										                <a href="javascript:void(0);" class="page-link" data-page="${i}">${i}</a>
										            </c:otherwise>
										        </c:choose>
										    </c:forEach>
										
										    <c:if test="${currentPage < totalPage}">
										        <a href="javascript:void(0);" class="page-link" data-page="${currentPage + 1}">다음 &raquo;</a>
										    </c:if>
										</div>
										<div id="level_setting">
											<div id="set_level">
												<select name="level" id="level">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
												</select>
											</div>
											
											<div id="set_access">
												<div id="active_box">
													<label for="access">ACTIVE</label>
													<input type="radio" id="active" name="access" value="ACTIVE" checked>
												</div>
												<div id="blocked_box">
													<label for="blocked">BLOCKED</label>
													<input type="radio" id="blocked" name="access" value="BLOCKED">
												</div>
											</div>
										</div>
										
										<div id="set_btn">
											<div>
												<button type="submit" id="set_submit">APPLY</button>
											</div>
											<div>
												<button type="reset" id="set_reset">RESET</button>
											</div>
										</div>
							        </form>
								</div>
							</div>
						</div>
						<script>
							document.querySelectorAll('.page-link').forEach(link => {
							    link.addEventListener('click', function() {
							        const page = this.getAttribute('data-page');
							        // 페이지 번호를 URL에 반영하여 새로 고침
							        window.location.href = '/admin/manageAccount?page=' + page;
							    });
							});
						</script>
						<script src="/resources/js/admin/managerAccount.js"></script>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />