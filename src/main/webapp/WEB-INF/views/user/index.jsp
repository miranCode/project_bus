<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "사용자 메인");
    request.setAttribute("bodyClass", "main");
%>
<jsp:include page="./inc/header.jsp" />
            	<!-- #content 영역 시작 -->
            	
				<div id="content">
					${serverTime}
				</div>  
				<!-- #content 영역 끝 -->
<jsp:include page="./inc/footer.jsp" />