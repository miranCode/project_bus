<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
request.setAttribute("pageTitle", "사용자 메인");
request.setAttribute("bodyClass", "main");
%>
<jsp:include page="./inc/header.jsp" />
<!-- #content 영역 시작 -->

<link rel="stylesheet" type="text/css"
	href="/resources/css/user/index.css" />


<div class="container">
	<div class="mainbuspost">

		<div class="mainbuspost1">
			<div class="sign-post">
				<!-- 원판 (버스 표지판 상단 원형) -->
				<div class="circle">
					<div class="icon">🚍</div>
					<!-- 버스 아이콘 -->
				</div>
			</div>
		</div>
		<div class="bus-stop">


			<!-- 정류장 간판 -->
			<div class="bus-sign">🚌</div>

			<!-- 정류장 지붕 -->
			<div class="bus-stop-roof">🚌 숭실대 > 🚌 숭실대입구</div>


			<!-- 정류장 정보 -->
			<div class="bus-info">
				<div class="eight flex ju-between">
					<div>
						<button class="button" onclick="window.location.href='/busArrival';">버스노선조회</button>
		            	<p>
			            	버스정보시스템에서 제공하는 정보는 교통 및 버스운행 상황, 정보 변경 시점에 따라 실제 상황과 다를수 있으며, 도착예정시간은 예측정보로서 실제 도착시간과 차이가 발생할 수 있습니다.
			                <span>※ 막차시간은 교통상황 및 수요에 따라 조정될 수 있음</span>
			                <span>※ 제공범위 : 시내버스 및 시외버스(일부)</span>
		            	</p>
		            </div>
					<div>
						<button class="button" onclick="window.location.href='/busArrivalByStation';">정류장조회</button>
						<p>
							실시간 버스 정보를 확인할 수 있습니다. 
				            이용자의 편의를 위하여 버스정보와 관련해 
				            인터넷을 통해 실시간으로 버스의 현재위치 정보, 
				            정류소 도착 예정 버스,버스노선의 검색, 환승정보, 
				            정류소 검색정보 등을 제공하고 있습니다.
						</p>
					</div>	
				</div>
			</div>

			<!-- 정류장 벤치 -->
			<div class="bus-stop-bench">
				<div class="bench-leg left"></div>
				<div class="bench-leg middle"></div>
				<div class="bench-leg right"></div>
			</div>

			<!-- 정류장 바닥 부분 -->
			<div class="bus-stop-footer"></div>
		</div>
	</div>






	<div class="mainbus">
		<div class="bus">
			<div class="bus-body">
				<!-- 창문 -->
				<div class="window window-1"></div>
				<div class="window window-2"></div>
				<div class="window window-3"></div>
				<div class="window window-4"></div>

				<!-- 문 -->
				<div class="door"></div>
			</div>
			<!-- 바퀴 -->
			<div class="wheel wheel-1">
				<div class="hubcap"></div>
				<!-- 허브캡 -->
			</div>
			<div class="wheel wheel-2">
				<div class="hubcap"></div>
				<!-- 허브캡 -->
			</div>

			<div class="headlight">
				<div class="light-beam"></div>
				<!-- 왼쪽으로 퍼지는 불빛 추가 -->
				<div class="light-beam left"></div>


			</div>
			<div class="tail-light right"></div>
		</div>
	</div>
	<!-- 버스 -->
</div>


</div>
<jsp:include page="./inc/footer.jsp" />