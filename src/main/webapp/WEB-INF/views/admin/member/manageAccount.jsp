<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "권한 설정");
    request.setAttribute("bodyClass", "admin");
%>
<jsp:include page="../inc/header.jsp" />
            	<!-- #content 영역 시작 -->
            	<link rel="stylesheet" href="/resources/css/admin/manageAccount.css">
						<div id="content">
							<div id="manageAccount_area">
								<div id="account_box">
									<form action="/admin/updateAccount" method="post">
										<div id="list_box">
										<ul class="table table-header">
											<li>
												<ul class="flex">
								            		<li>선택</li>
								            		<li>아이디</li>
								            		<li>이름</li>
								            		<li>레벨</li>
								            		<li>로그인권한</li>
								            		<li>등록일</li>
								            		<li>최근 로그인</li>
									            </ul>
											</li>
										</ul>
										<ul id="adminList" class="table">
									        <c:forEach var="admin" items="${adminList}">
									            <li>
									            	<ul class="flex">
									            		<li><input type="checkbox" name="selectedIds" value="${admin.id}" class="admin-checkbox" /></li>
									            		<li><a href="/admin/join?id=${admin.id}">${admin.id}</a></li>
									            		<li><a href="/admin/join?id=${admin.id}">${admin.name}</a></li>
									            		<li>
									            			<div id="set_level">
									            				<!--select.level 의 option value값이 저장된 ${admin.level} 값과 같은면 selected 활성화 -->
																<select name="level_${admin.id}" id="level_${admin.id}">
																	<c:forEach var="i" begin="1" end="3"> <!-- 1부터 10까지 반복 -->
																        <option value="${i}" 
																                <c:if test="${admin.level == i}">selected</c:if>>${i}</option>
																    </c:forEach>
																</select>
															</div>
									            		</li>
									            		<li>
									            			<div id="set_access">
											                    <div id="active_box">
											                        <label for="access_${admin.id}_active">ACTIVE</label>
											                        <input type="radio" id="access_${admin.id}_active" name="access_${admin.id}" value="ACTIVE"
											                               <c:if test="${admin.access == 'ACTIVE'}">checked</c:if>>
											                    </div>
											                    <div id="blocked_box">
											                        <label for="access_${admin.id}_blocked">BLOCKED</label>
											                        <input type="radio" id="access_${admin.id}_blocked" name="access_${admin.id}" value="BLOCKED"
											                               <c:if test="${admin.access == 'BLOCKED'}">checked</c:if>>
											                    </div>
											                </div>
									            		</li>
									            		<li>
									            			<fmt:formatDate value="${admin.regidate}" pattern="yyyy-MM-dd"/>
									            		</li>
									            		<li>
									            			<fmt:formatDate value="${admin.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"/>
									            		</li>
										            </ul>
									                <!-- 체크박스를 이용해 선택 -->
									            </li>
									        </c:forEach>
									    </ul>
										</div>
									    <!-- 페이지네이션 -->
								        <div id="pagination" class="pagination">
								        	<ul class="flex">
											    <c:if test="${currentPage > 1}">
											    	<li class="wAuto">
											        	<a href="javascript:void(0);" class="page-link" data-page="${currentPage - 1}">&laquo; 이전</a>
											        </li>
											    </c:if>
											
											    <c:forEach begin="1" end="${totalPage}" var="i">
											        <c:choose>
											            <c:when test="${i == currentPage}">
											            	<li class="on">
												                <a>${i}</a>
											                </li>
											            </c:when>
											            <c:otherwise>
											            	<li>
											                	<a href="javascript:void(0);" class="page-link" data-page="${i}">${i}</a>
											                </li>
											            </c:otherwise>
											        </c:choose>
											    </c:forEach>
											
											    <c:if test="${currentPage < totalPage}">
											    	<li class="wAuto">
											        	<a href="javascript:void(0);" class="page-link" data-page="${currentPage + 1}">다음 &raquo;</a>
											        </li>
											    </c:if>
										    </ul>
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