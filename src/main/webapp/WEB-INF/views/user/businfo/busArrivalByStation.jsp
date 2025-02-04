<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "버스 도착 정보 조회");
    request.setAttribute("bodyClass", "user");
%>

<jsp:include page="../inc/header.jsp" />
    <div class="busStaiton-container">
    	<style>
		/* 전체 컨테이너 */
		.container {
            overflow: visible !important;
        }
		.busStaiton-container {
		    margin: 20px auto;
		    text-align: center;
		}
		
		/* 제목 스타일 */
		.bus-title {
		    font-size: 24px;
		    font-weight: bold;
		    margin-bottom: 20px;
		}
		
		/* 검색 폼 스타일 */
		.bus-form {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    gap: 10px;
		    padding: 15px;
		    border-radius: 8px;
		    max-width: 500px;
		    margin: 0 auto 20px auto;
		}
		
		/* 입력 필드 스타일 */
		.bus-input {
		    width: 200px;
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
		
		/* 버스 노선도 스타일 */
		.busline {
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    list-style: none;
		    padding: 0;
		    position: relative;
		}
		
		/* 버스 정류장 스타일 */
		.bus-stop {
		    display: flex;
		    align-items: center;
		    position: relative;
		    width: 100%;
		    max-width: 500px;
		    padding: 15px;
		}
		
		/* 정류장 아이콘 */
		.bus-icon {
		    font-size: 20px;
		    margin-right: 10px;
		}
		
		/* 버스 정류장 정보 박스 */
		.bus-info {
		    background: white;
		    padding: 10px;
		    border-radius: 8px;
		    width: 100%;
		    box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
		    text-align: left;
		}
		
		/* 첫 번째 정류장의 선 제거 */
		.bus-stop:first-child::before {
		    display: none;
		}
		
		/* "곧 도착" 문구 강조 */
		.arriving-text {
		    color: red;
		    font-weight: bold;
		}
		
		/* 도착 정보 없음 */
		.no-info {
		    color: gray;
		    font-style: italic;
		    margin-top: 10px;
		}

    	</style>
        <h1 class="bus-title">정류장 조회</h1>
        <!-- 정류소 검색 폼 -->
        <form action="/getBusArrivalByStationInfo" method="get" class="bus-form">
            <label for="stNm" class="bus-label">정류소 명칭:</label>
            <input type="text" id="stNm" name="stNm" required class="bus-input" value="${stNm}" placeholder="정류소명을 입력해주세요." style="border:1px solid #ddd;">
            <button type="submit" class="bus-button">조회</button>
        </form>

        <hr>

        <h2 class="arrival-title">정류장 도착 정보:</h2>

        <div class="busline-container">
            <c:choose>
                <c:when test="${not empty busRouteAbrvList}">
                    <ul class="busline">
                        <c:forEach var="busRouteAbrv" items="${busRouteAbrvList}" varStatus="status">
                            <li class="bus-stop">
                                <span class="bus-icon">🚏</span>
                                <div class="bus-info">
                                    <p><strong>노선명:</strong> ${busRouteAbrv}</p>
                                    <p><strong>정류소명:</strong> ${stNmList[status.index]}</p>
                                    <p>
                                        <strong>도착 메시지:</strong> 
                                        <span class="${arrmsg1List[status.index] == '곧 도착' ? 'arriving-text' : ''}">
                                            ${arrmsg1List[status.index]}
                                        </span>
                                    </p>
                                    <p><strong>도착 예정 시간:</strong> ${exps1List[status.index]} 분 후 도착</p>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p class="no-info">버스 도착 정보가 없습니다.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

<jsp:include page="../inc/footer.jsp" />
