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
					${serverTime} , ${ uname }
					<script type="text/javascript">
					var xhr = new XMLHttpRequest();
					var url = 'http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute'; /*URL*/
					var queryParams = '?' + encodeURIComponent('serviceKey') + '='+'NrhAspAZ5h1y8R3c%2BBmqbFmZbwHTLhMLIBtpZb%2FqPGYuobL2oN%2FI4V1q08wfaf%2FCqSW8EkbltM6yaZ8DAn4xdA%3D%3D'; /*Service Key*/
					queryParams += '&' + encodeURIComponent('busRouteId') + '=' + encodeURIComponent('100100549'); /**/
					xhr.open('GET', url + queryParams);
					xhr.onreadystatechange = function () {
					    if (this.readyState == 4) {
					        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
					    }
					};

					xhr.send('');
					</script>

				</div>  
				<!-- #content 영역 끝 -->
<jsp:include page="./inc/footer.jsp" />	