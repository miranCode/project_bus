<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 불편사항 접수 상세보기");
    request.setAttribute("bodyClass", "board");
%>
<jsp:include page="../inc/header.jsp" />
				<script type="text/javascript" src="/resources/js/select.js"></script>
            	<!-- #content 영역 시작 -->
            	
				<div id="content">
					
				</div>  
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />