<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 버스 노선정보 ");
    request.setAttribute("bodyClass", "bus");
%>
<jsp:include page="../inc/header.jsp" />
<link rel="stylesheet" type="text/css" href="/resources/css/admin/bus.css?after" />
				<script type="text/javascript" src="/resources/js/select.js"></script>
            	<!-- #content 영역 시작 -->
				<div id="content">
					<div class="table">
						<ul class="flex table-header column11">
							<li>no</li>
							<li>사용일자 <span>USE_YMD</span></li>
							<li>노선번호 <span>RTE_ID</span></li>
							<li>노선명 <span>RTE_NO</span></li>
							<li><span>RTE_NM</span></li>
							<li>표준버스정류장ID <span>STOPS_ID</span></li>
							<li>버스정류장ARS번호 <span>STOPS_ARS_NO</span></li>
							<li>역명 <span>SBWY_STNS_NM</span></li>
							<li>승차총승객수 <span>GTON_TNOPE</span></li>
							<li>하차총승객수 <span>GTOFF_TNOPE</span></li>
							<li>등록일자 <span>REG_YMD</span></li>
						</ul>
					</div>
				</div>  
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />