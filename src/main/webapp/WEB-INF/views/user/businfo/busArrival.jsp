<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "버스 도착 정보 조회");
    request.setAttribute("bodyClass", "user");
%>
<jsp:include page="../inc/header.jsp" />
    <div id="content">
	    <style>
				    /* 전체 컨테이너 스타일 */
			.bus-title {
			    font-size: 24px;
			    font-weight: bold;
			    text-align: center;
			    margin-bottom: 20px;
			}
			
			/* 버스 노선 입력 폼 스타일 */
			.bus-form {
			    display: flex;
			    flex-direction: column;
			    align-items: center;
			    gap: 10px;
			    padding: 15px;
			    border-radius: 8px;
			    max-width: 350px;
			    margin: 0 auto;
			}
			
			/* 라벨 스타일 */
			.bus-label {
			    font-size: 16px;
			    font-weight: bold;
			    color: #333;
			}
			
			/* 입력 필드 스타일 */
			.bus-input {
			    width: 100%;
			    max-width: 250px;
			    padding: 8px;
			    border: 2px solid #007bff;
			    border-radius: 5px;
			    font-size: 16px;
			}
			
			/* 버튼 스타일 */
			.bus-button {
			    background: #007bff;
			    color: white;
			    border: none;
			    padding: 8px 15px;
			    font-size: 16px;
			    cursor: pointer;
			    border-radius: 5px;
			    transition: background 0.3s;
			}
			
			/* 버튼 호버 효과 */
			.bus-button:hover {
			    background: #0056b3;
			}    
	    </style>
		<h1 class="bus-title">버스 도착 정보 조회</h1>

		<!-- 버스 노선 ID 입력 폼 -->
		<form action="/getBusArrivalInfo" method="get" class="bus-form">
		    <label for="rteNm" class="bus-label">버스 번호</label>
		    <input type="text" id="rteNm" name="rteNm" required class="bus-input">
		    <button type="submit" class="bus-button">조회</button>
		</form>
	    <!-- API 호출 후 도착 정보가 출력되는 부분 -->
	    <style>
	    	/* 전체 컨테이너 스타일 */
			/* 전체 컨테이너 스타일 */
			#content {
			    max-width: 500px;
			    margin: 20px auto;
			    text-align: center;
			}
			
			/* 버스 노선 컨테이너 (스크롤 추가) */
			.busline-container {
			    max-height: 500px; /* 노선이 길어질 경우 대비 */
			    width : 500px;
			    overflow-y: auto;
			    border: 2px solid #ddd;
			    padding: 10px;
			    border-radius: 10px;
			    background: #f9f9f9;
			}
			
			/* 버스 노선 스타일 */
			ul.busline {
			    display: flex;
			    flex-direction: column;
			    align-items: flex-start;
			    position: relative;
			    padding-left: 128px; /* 선과 아이콘을 위한 공간 */
			}
			
			/* 기본 정류장 스타일 */
			ul.busline li {
			    position: relative;
			    padding: 10px 0;
			    text-align: left;
			}
			
			/* 버스 노선 선 (수직 라인) */
			ul.busline li:not(:last-child)::after {
			    content: "";
			    position: absolute;
			    left: -10px;
			    top: 0;
			    width: 4px;
			    height: 100%;
			    background-color: blue;
			}
			
			/* 정류장 아이콘 */
			ul.busline li::before {
			    content: "⬤";
			    position: absolute;
			    left: -14px;
			    top: 45%;
			    transform: translateY(-50%);
			    font-size: 14px;
			    color: blue;
			}
			
			/* 곧 도착한 경우 아이콘 변경 */
			ul.busline li.arriving::before {
			    content: "🚌"; /* 버스 아이콘 */
			    color: red;
			    font-size: 50px;
			}
			
			/* 스크롤바 스타일 (크롬, 엣지, 사파리) */
			.busline-container::-webkit-scrollbar {
			    width: 8px;
			}
			
			.busline-container::-webkit-scrollbar-thumb {
			    background: blue;
			    border-radius: 10px;
			}
			
			.busline-container::-webkit-scrollbar-track {
			    background: #ddd;
			    border-radius: 10px;
			}
			/* "곧 도착" 문구 스타일 */
			span.arriving-text {
			    color: red;
			    font-weight: bold;
			}
	    </style>
	    <div class="busline-container">
	    <ul class="busline">
	        <c:forEach var="stNm" items="${stNmList}" varStatus="status">
	            <li class="">
	            	<p>
		                ${stNm}
		                <c:choose>
		                    
		                    <c:when test="${arrivalStatusList[status.index] == '곧 도착'}">
		                        <span class="arriving-text">(곧 도착)</span>
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
	</div>

<jsp:include page="../inc/footer.jsp" />
