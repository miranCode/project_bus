<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 버스 노선정보 ");
    request.setAttribute("bodyClass", "bus");
%>
<jsp:include page="../inc/header.jsp" />
<script type="text/javascript" src="/resources/js/api/bususe.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/admin/bus.css?after" />
            	<!-- #content 영역 시작 -->
				<div id="content">
					<div class="btn-area btn-right">
						<button type="button" id="busUseApi" class="update_info btn btn-bagic line">노선별 정류장별 승하차 인원 DB 저장</button>
					</div>
					<p class="total">Total : <span>${busUseSize}</span></p>
					<div class="table">
						<ul class="flex table-header">
							<li class="w4per">no</li>
							<li class="w6per">사용일자 <span>USE_YMD</span></li>
							<li class="w6per">노선ID<span>RTE_ID</span></li>
							<li class="w4per">노선번호<span>RTE_NO</span></li>
							<li class="w10per">노선명<span>RTE_NM</span></li>
							<li class="w10per">표준버스정류장ID<span>STOPS_ID</span></li>
							<li class="w10per">버스정류장ARS번호<span>STOPS_ARS_NO</span></li>
							<li class="w20per">역명<span>SBWY_STNS_NM</span></li>
							<li class="w10per">승차총승객수<span>GTON_TNOPE</span></li>
							<li class="w10per">하차총승객수<span>GTOFF_TNOPE</span></li>
							<li class="w10per">등록일자<span>REG_YMD</span></li>
						</ul>
						<ul class="table-body">
							<c:choose>
								<c:when test="${not empty busUseList}">
									<c:forEach var="list" items="${busUseList}" varStatus="status">
										<li>
											<ul class="flex ">
												<li class="w4per">${status.index + 1}</li>
												<li class="w6per">${list.USE_YMD}</li>
												<li class="w6per">${list.RTE_ID}</li>
												<li class="w4per">${list.RTE_NO}</li>
												<li class="w10per">${list.RTE_NM}</li>
												<li class="w10per">${list.STOPS_ID}</li>
												<li class="w10per">${list.STOPS_ARS_NO}</li>
												<li class="w20per">${list.SBWY_STNS_NM}</li>
												<li class="w10per">${list.GTON_TNOPE}</li>
												<li class="w10per">${list.GTOFF_TNOPE}</li>
												<li class="w10per">${list.REG_YMD}</li>
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