<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 버스 노선정보 ");
    request.setAttribute("bodyClass", "bus");
%>
<jsp:include page="../inc/header.jsp" />
<script type="text/javascript" src="/resources/js/api/route.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/admin/bus.css?after" />
            	<!-- #content 영역 시작 -->
				<div id="content">
					<div class="btn-area btn-right">
						<button type="button" id="routeApi" class="update_info btn btn-bagic line">노선별 정류장별 승하차 인원 DB 저장</button>
					</div>
					<p class="total">Total : <span>${routeSize}</span></p>
					<div class="table">
						<ul class="flex table-header">
							<li class="w4per">no</li>
							<li class="w6per">사용일자 <span>date</span></li>
							<li class="w10per">노선ID<span>busid</span></li>
							<li class="w10per">노선명<span>busname</span></li>
							<li class="w10per">운영횟수<span>line</span></li>
							<li class="w10per">승차총승객수<span>user</span></li>
							<li class="w10per">버스종류</li>
						</ul>
						<ul class="table-body">
							<c:choose>
								<c:when test="${not empty routeList}">
									<c:forEach var="list" items="${routeList}" varStatus="status">
										<li>
											<ul class="flex ">
												<li class="w4per">${status.index + 1}</li>
												<li class="w6per">${list.date}<span>${list.week} ${list.day}</span></li>
												<li class="w10per">${list.busid}</li>
												<li class="w10per">${list.busname}</li>
												<li class="w10per">${list.line}</li>
												<li class="w10per">-</li>
												<li class="w10per">${list.busct}</li>
											</ul>
										</li>
									</c:forEach>
									
								</c:when>
								<c:otherwise>
									<li class="no-data">저장된 데이터가 없습니다.</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>  
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />