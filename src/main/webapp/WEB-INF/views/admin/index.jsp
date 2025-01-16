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
<link rel="stylesheet" type="text/css" href="/resources/css/admin/graph.css?after" />
<style>
	div.section{margin-top:50px; padding-top:40px; border-top:5px solid #f5f5f5; box-sizing: border-box;}
	div.section:first-of-type{margin-top:0;}
	div.box{padding: 30px; box-sizing: border-box; border-radius: 9px; border:1px solid #ddd;}
	div.box p.tit-area {font-weight:800; margin-bottom:20px; line-height:1.3; font-size:16px !important;}
	div.box p.tit-area > span{display: block; font-size:12px; color:#999; font-weight:400; margin-bottom: 5px;}
	div.sec01 > div.left{width:31%; margin-right:2%;}
	div.sec01 > div.right{width:67%;}
	ul.main_borad{}
	ul.main_borad > li{margin-bottom: 5px;}
	ul.main_borad > li:last-child{margin-bottom: 0}
	ul.main_borad li a{position:relative; display:block; width:100%; padding:10px 10px 10px 60px; box-sizing: border-box; border:1px solid #ddd; border-radius: 9px;}
	ul.main_borad li a ul li{font-size:14px;}
	ul.main_borad li a ul li:first-child{position:absolute; top:50%; left:10px; transform: translateY(-50%); font-size:12px;}
	ul.main_borad li a ul li:last-child{color:#999;}
	div.info-area > div.box{width:49%;margin-bottom:2%;}
	div.info-area > div.box:nth-child(n + 3){margin-bottom:0}
	div.info-area > div.box div.left{width:60%; margin-right: 5%;}
	div.info-area > div.box div.left p{font-size:14px;}
	div.info-area > div.box div.right{width: 35%;}
</style>
<script> 
	// 페이지가 로딩된 후 다시 불러온다. 그래서 제이쿼리는 위에  있어도 괜찮다. 
	$(document).ready(function(){ 
		$("ul.tabs > li:first").addClass("on");
	    $(".chart-area:first").addClass("on");
		$("ul.tabs > li").click(function name() {
			var idx = $(this).index();
			$(this).addClass("on").siblings().removeClass("on");
			$(this).parents().siblings().find(".chart-area").eq(idx).addClass("on").siblings().removeClass("on");
		});
	});
</script> 
            	<!-- #content 영역 시작 -->
				<div id="content">
					<div class="section sec01 flex">
						<div class="borad-area left">
							<div class="box">
								<p class="tit-area"><span><fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy.MM.dd EEEE" /></span>불편사항 접수 현황</p>
								<ul class="main_borad">
									<li>
										<a href="">
											<ul class="flex al-center ju-between">
												<li class="txt-red">미처리</li>
												<li class="ell">제목</li>
												<li>00.00.00</li>
											</ul>
										</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="info-area flex flex-wrap ju-between right">
							<div class="box flex">
								<div class="left">
									<p class="tit-area">
										<span>기준 : ${startDate} ~ ${endDate}</span>
										일(공휴일) 평균 버스이용객수 
									</p>
									<ul>
										<li>
											<div class="">
												<p>
													<span>간선</span>
													<fmt:formatNumber value="9331828" pattern="#,##0.00"/>
												</p>
											</div>
											<div class=""></div>
										</li>
										<li><p><span>지선</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p></li>
										<li><p><span>마을</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p></li>
										<li><p><span>심야</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p></li>
										<li><p><span>순환</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p></li>
										<li><p><span>광역</span><fmt:formatNumber value="9331828" pattern="#,##0.00"/></p></li>
									</ul>
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
							<div class="box flex">
								<div class="left">
									<p class="tit-area">
										<span>기준 : ${startDate} ~ ${endDate}</span>
										일(공휴일) 평균 버스이용객수 
									</p>
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
							<div class="box flex">
								<div class="left">
									<p class="tit-area">
										<span>기준 : ${startDate} ~ ${endDate}</span>
										일(공휴일) 평균 버스이용객수 
									</p>
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
							<div class="box flex">
								<div class="left">
									<p class="tit-area">
										<span>기준 : ${startDate} ~ ${endDate}</span>
										일(공휴일) 평균 버스이용객수 
									</p>
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
					<div class="section sec02">
						<div class="tab-area">
							<ul class="tabs flex">
								<li>서울간선버스</li>
								<li>서울심야버스</li>
								<li>서울지선버스</li>
								<li>서울순환버스</li>
								<li>서울마을버스</li>
								<li>서울광역버스</li>
							</ul>
							<div class="tab-content">
							<c:forEach var="list" items="${busTimeList}">
								<div class="chart-area bar-type">
				                    <p>(단위 : 명) <span>2024.12 서울 ${list.TRFC_MNS_TYPE_NM} 시간별 사용객 평균</span></p>
				                    <p>(시간)</p>
				                    <div class="graph-area">
				                        <div class="bg-graph">
				                            <ul class="txt right">
				                                <li><b>160,000</b><span class="line"></span></li>
				                                <li><b>150,000</b><span class="line"></span></li>
				                                <li><b>140,000</b><span class="line"></span></li>
				                                <li><b>130,000</b><span class="line"></span></li>
				                                <li><b>120,000</b><span class="line"></span></li>
				                                <li><b>110,000</b><span class="line"></span></li>
				                                <li><b>100,000</b><span class="line"></span></li>
				                                <li><b>90,000</b><span class="line"></span></li>
				                                <li><b>80,000</b><span class="line"></span></li>
				                                <li><b>70,000</b><span class="line"></span></li>
				                                <li><b>60,000</b><span class="line"></span></li>
				                                <li><b>50,000</b><span class="line"></span></li>
				                                <li><b>40,000</b><span class="line"></span></li>
				                                <li><b>30,000</b><span class="line"></span></li>
				                                <li><b>20,000</b><span class="line"></span></li>
				                                <li><b>10,000</b><span class="line"></span></li>
				                            </ul>
				                        </div>
				                        <div class="graph-box flex">
				                            <div class="bar-box">
				                                <ul class="bar">
					                                <c:forEach var="i" begin="0" end="23">
					                                	<c:set var="fieldName" value="HR_${i}_ON" />
													    <li class="animated-bar" style="height:calc(${list[fieldName]} / 160000 * 100%)">
														    <span><fmt:formatNumber value="${i}" pattern="00"/></span>
														    <div><fmt:formatNumber value="${list[fieldName]}" pattern="#,##0.0"/> <strong>명</strong></div>
													    </li>
													</c:forEach>
				                                </ul>
				                            </div>
				                        </div>
				                    </div>
				                </div>
			                </c:forEach>
			                </div>
		                </div>
					</div>
				</div>  
				<!-- #content 영역 끝 -->
<jsp:include page="./inc/footer.jsp" />