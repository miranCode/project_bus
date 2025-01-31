<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "버스 도착 정보 조회");
    request.setAttribute("bodyClass", "user");
%>
<jsp:include page="../inc/header.jsp" />
    <div id="content">
	    <h1>버스 도착 정보 조회</h1>
	    
	    <!-- 버스 노선 ID 입력 폼 -->
	    <form action="/getBusArrivalInfo" method="get">
	        <label for="rteNm">버스 노선 ID:</label>
	        <input type="text" id="rteNm" name="rteNm" required>
	        <button type="submit">조회</button>
	    </form>
	    <!-- API 호출 후 도착 정보가 출력되는 부분 -->
	    <h2>버스 도착 정보:</h2>
	    <style>
	    	#container{height:auto; display:block;}
	    	ul.busline li{position:relative; width:calc(100%/5);}
	    	ul.busline li:after{content:"";}
	    	ul.busline li:before{position:absolute; bottom:0; left:0; display:block; content:""; width:100%; height:10px; background:blue;}
	    	ul.busline li p{font-size:14px; line-height:1.3; transform: rotate(-21deg); transform-origin: left;}
	    	ul.busline li p span{display:block; font-size:12px;}
	    </style>
	    <ul class="busline flex flex-wrap">
	        <c:forEach var="stNm" items="${stNmList}" varStatus="status">
	            <li class="">
	            	<p>
		                ${stNm}
		                <c:choose>
		                    
		                    <c:when test="${arrivalStatusList[status.index] == '곧 도착'}">
		                        <span>(곧 도착)</span>
		                    </c:when>
		                    
		                    <c:when test="${arrivalStatusList[status.index] != '곧 도착' && arrivalStatusList[status.index + 1] == '곧 도착'}">
		                        <span>🚌</span> 
		                    </c:when>
		                   
		                    <c:otherwise>
		                        <c:if test="${arrivalStatusList[status.index] != '곧 도착' && arrivalStatusList[status.index + 1] != '곧 도착' && arrivalStatusList[status.index] != '곧 도착'}">
		                            <span>
		                                <c:if test="${not empty exps1List[status.index]}">
		                                    (${exps1List[status.index]} 분 후 도착)
		                                </c:if>
		                            </span>
		                        </c:if>
		                        
		                    </c:otherwise>
		                </c:choose>
	                </p>
	            </li>
	        </c:forEach>
	    </ul>
	</div>

<jsp:include page="../inc/footer.jsp" />
