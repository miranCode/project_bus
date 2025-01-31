<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>버스 도착 정보</title>
</head>
<body>
    <h1>정류소 버스 도착 정보 조회</h1>

    <!-- 정류소 명칭 입력 폼 -->
    <form action="/getBusArrivalByStationInfo" method="get">
        <label for="stNm">정류소 명칭:</label>
        <input type="text" id="stNm" name="stNm" required>
        <button type="submit">조회</button>
    </form>

    <hr>

    <h2>버스 도착 정보:</h2>

    <c:if test="${not empty busRouteAbrvList}">
        <ul>
            <c:forEach var="busRouteAbrv" items="${busRouteAbrvList}" varStatus="status">
                <li>
                    <strong>노선명:</strong> ${busRouteAbrv} <br>
                    <strong>정류소명:</strong> ${stNmList[status.index]} <br>
                    <strong>도착 메시지:</strong> ${arrmsg1List[status.index]} <br>
                    <strong>도착 예정 시간:</strong> ${exps1List[status.index]} 분 후 도착
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty busRouteAbrvList}">
        <p>버스 도착 정보가 없습니다.</p>
    </c:if>
</body>
</html>
