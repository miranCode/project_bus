<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 메인");
    request.setAttribute("bodyClass", "adm main");
%>

<jsp:include page="./inc/header.jsp" />
<fmt:formatNumber value="9331828" pattern="#,##0 명" var="seoul"/>
<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm:ss" var="startDate"/>
<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss" var="endDate"/>
<style>
	body.adm.main {}
	body.adm.main div.info-area{}
	body.adm.main div.info-area > div.box{width:24%; padding: 30px; box-sizing: border-box; border-radius: 9px; border:1px solid #ddd;}
	body.adm.main div.info-area > div.box div.left{width:60%; margin-right: 5%;}
	body.adm.main div.info-area > div.box div.right{width: 35%;}
	
	.circle-box{position: relative; width: 110px; height: 110px; box-sizing: border-box; border-radius: 100px; /* 원만들기 */ }
	.circle-box > div{
	    position: absolute; 
	    left: 50%; 
	    top:50%; 
	    transform: translate(-50%, -50%);
	    display: block; 
	    width: 60%; 
	    height: 60%;
	    font-weight: bold;
	    border-radius: 50%;
	    background: #fff;
	}
	.circle-box > div > span{
	  position: absolute; 
	  left: 50%; 
	  top:50%; 
	  transform: translate(-50%, -50%);
	  text-align: center;
	  font-size: 12px;
	  width: 100%;
	}
</style>
            	<!-- #content 영역 시작 -->
				<div id="content">
					로그인 성공!
					<div class="info-area flex ju-between">
						<div class="box">
							<p><span>기준 : 24.12</span>서울 총 인구수</p>
							<p>${seoul}</p>
							<div class="flex">
								<div class="left">
									<p><span>월 평균</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
								</div>
								<div class="right">
									<div class="circle-box ">
										<div class="">
										    <span>30%</span>
										</div>
										<svg  viewBox="0 0 200 200" width="100%" height="100%">
										    <circle cx="100" cy="100" r="90" class="circle animated-circle" />
									    </svg>
									</div>
								</div>
							</div>
						</div>
						<div class="box">
							<p>
								<sapn>기준 : ${startDate} ~ ${endDate}</sapn>
								평일 평균 버스이용객수 
							</p>
							<div class="flex">
								<div class="left">
									<p><span>간선</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>지선</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>마을</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>심야</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>순환</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>광역</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
								</div>
								<div class="right">
									<div class="circle-box ">
										<div class="">
										    <span>30%</span>
										</div>
										<svg  viewBox="0 0 200 200" width="100%" height="100%">
										    <circle cx="100" cy="100" r="90" class="circle animated-circle" />
									    </svg>
									</div>
								</div>
							</div>
						</div>
						<div class="box">
							<p>
								<sapn>기준 : ${startDate} ~ ${endDate}</sapn>
								토요일 평균 버스이용객수 
							</p>
							<div class="flex">
								<div class="left">
									<p><span>간선</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>지선</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>마을</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>심야</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>순환</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>광역</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
								</div>
								<div class="right">
									<div class="circle-box ">
										<div class="">
										    <span>30%</span>
										</div>
										<svg  viewBox="0 0 200 200" width="100%" height="100%">
										    <circle cx="100" cy="100" r="90" class="circle animated-circle" />
									    </svg>
									</div>
								</div>
							</div>
						</div>
						<div class="box">
							<p>
								<sapn>기준 : ${startDate} ~ ${endDate}</sapn>
								일(공휴일) 평균 버스이용객수 
							</p>
							<div class="flex">
								<div class="left">
									<p><span>간선</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>지선</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>마을</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>심야</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>순환</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
									<p><span>광역</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p>
								</div>
								<div class="right">
									<div class="circle-box ">
										<div class="">
										    <span>30%</span>
										</div>
										<svg  viewBox="0 0 200 200" width="100%" height="100%">
										    <circle cx="100" cy="100" r="90" class="circle animated-circle" />
									    </svg>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="chart-area bar-type">
						<!-- 시간별 00 ~ 23 -->
					</div>
					
				</div>  
				<!-- #content 영역 끝 -->
<jsp:include page="./inc/footer.jsp" />