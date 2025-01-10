<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 버스 노선정보 ");
    request.setAttribute("bodyClass", "bus");
%>
<jsp:include page="../inc/header.jsp" />
<script type="text/javascript" src="/resources/js/api/busline.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/admin/bus.css?after" />
				
            	<!-- #content 영역 시작 -->
				<div id="content">
					<div class="btn-area btn-right">
						<button type="button" id="busLineApi" class="update_info btn btn-bagic line">서울버스 노선 DB 저장</button>
					</div>
					<p class="total">Total : <span>${budLineListSize}</span></p>
					<div class="table">
						<ul class="flex table-header ">
							<li class="w10per">no</li>
							<li class="w40per">노선ID<span>RTE_ID</span></li>
							<li class="w50per">노선번호<span>RTE_NM</span></li>
						</ul>
						<ul class="table-body">
							<c:choose>
								<c:when test="${not empty budLineList}">
									<c:forEach var="list" items="${budLineList}" varStatus="status">
										<li>
											<ul class="flex ">
												<li class="w10per">${status.index + 1}</li>
												<li class="w40per">${list.RTE_ID}</li>
												<li class="w50per">${list.RTE_NM}</li>
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