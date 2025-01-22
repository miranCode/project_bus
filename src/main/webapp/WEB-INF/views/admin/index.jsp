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
	div.box{padding: 30px 30px 10px; box-sizing: border-box; border-radius: 9px; border:5px solid #f5f5f5;}
	div.box a{color:#666; font-size: 12px; padding: 5px; border-bottom:1px solid #ddd; line-height:1.3}
	div.box a:hover{color:#007bff; border-color:#007bff;}
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
	
	ul.main_borad li a:hover{border-color:#eee; box-shadow: inset 1px -3px 6px rgba(0, 0, 0, 0.1); color:#222; font-weight: 800}
	div.count-area{margin-top:30px; padding:20px 0 15px; border-top:5px solid #f5f5f5;}
	div.count-area > p{font-size:14px !important;}
	div.count-area p b{color:#007bff}
	div.count-area div.flex p{font-size:14px; padding:0 8px; text-align:center; border-right:3px solid #ddd; border-left:3px solid #ddd; line-height:1; margin-bottom:10px}
	div.count-area div.flex p span{font-weight:800;}
	div.count-area div.flex > div{width:25%;}
	div.count-area div.flex > div:first-of-type .circle-box svg circle.animated-circle{stroke:  red;}
	div.count-area div.flex > div:nth-of-type(2) .circle-box svg circle.animated-circle{stroke:  #007bff;}
	div.count-area div.flex > div:last-of-type .circle-box svg circle.animated-circle{stroke:  #0ac73e;}
	
	div.info-area > div.box{width:49%;margin-bottom:2%;}
	div.info-area > div.box:nth-child(n + 3){margin-bottom:0}
	div.info-area > div.box div.left{width:60%; margin-right: 5%;}
	div.info-area > div.box div.left p{font-size:14px;}
	div.info-area > div.box div.right{width: 35%;}
	div.info-area > div.box div.bottom{width:100%;}
	
	p.txt-12{margin-bottom:5px}
	
	div.sec03 ul.main-list{}
	div.sec03 ul.main-list > li{border:2px solid #f5f5f5; border-radius: 9px; padding:5px; box-sizing: border-box; margin-top: 5px;}
	div.sec03 ul.main-list > li > ul.flex > li{color: #666; font-size: 14px}
	
	div.sec03 ul.flex{justify-content: space-between; align-items: center;}
	div.sec03 ul.flex li{font-size: 12px; color:#999; width: calc(100% / 7); text-align: center; line-height: 1.3;}
	div.sec03 ul.flex li span{display: block; font-size: 12px;}
</style>
<script> 
	// 페이지가 로딩된 후 다시 불러온다. 그래서 제이쿼리는 위에  있어도 괜찮다. 
	$(document).ready(function(){ 
		$("ul.tabs > li:first").addClass("on");
	    $(".tab-box:first").addClass("on");
		$("ul.tabs > li").click(function name() {
			var idx = $(this).index();
			$(this).addClass("on").siblings().removeClass("on");
			$(this).parents().siblings().find(".tab-box").eq(idx).addClass("on").siblings().removeClass("on");
		});
	});
</script>
            	<!-- #content 영역 시작 -->
				<div id="content">
					<div class="section sec01 flex">
						<div class="borad-area left">
							<div class="box">
								<div class="flex ju-between al-center">
									<p class="tit-area"><span><fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy.MM.dd E" /></span>불편사항 접수</p>
									<a href="/qna/list">More</a>
								</div>
								
								<ul class="main_borad">
									<c:forEach var="list" items="${boardList}" begin="1" end="5">
										<li>
											<a href="/qna/view/${list.bno}">
												<ul class="flex al-center ju-between">
													<li class="${list.status == '미처리' ? 'txt-red' : (list.status == '완료' ? 'txt-green' : 'txt-blue')}">
														 ${list.status}
													</li>
													<li class="ell">${list.title}</li>
													<li><fmt:formatDate value="${list.regdate}" pattern="yyyy.MM.dd E" /></li>
												</ul>
											</a>
										</li>
									</c:forEach>
								</ul>
								<div class="count-area">
									<p class="tit-area">불편사항처리현황 <b>${totalcount}</b>건</p>
									<div class="flex ju-between flex-wrap">
										<c:forEach var="count" items="${boardS}">
											<div>
												<p>${count.status} <span>${count.count}</span>건</p>
												<div class="circle-box ">
													<div class="">
													    <span>
														    <fmt:formatNumber value="${(count.count / totalcount) * 100}" pattern="#,##0.00"/>%
													    </span>
													</div>
													<svg  viewBox="0 0 200 200" width="100%" height="100%">
													    <circle cx="100" cy="100" r="90" class="circle-bg-gray" />
													    <circle cx="100" cy="100" r="90" class="circle animated-circle" stroke-dasharray="565.48" stroke-dashoffset="${565.48 - ((count.count / totalcount) * 565.48)}" />
												    </svg>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<div class="info-area flex flex-wrap ju-between right">
							<!-- -->
							<div class="box flex ">
									<div class="left">
										<p class="tit-area">
											<span>
												기준 : 24.12
											</span>
											서울 인구 <b class="txt-blue"><fmt:formatNumber value="9331828" pattern="#,##0"/></b>
										</p>
										
										<p class="tit-area">
											<span>
												기준 : ${period[0].startD} ~ ${period[0].endD} (${dateCount}일)
											</span>
											일 평균 버스 사용자수
											<b class="txt-blue">
												<fmt:formatNumber value="${ (useCount / dateCount) }" pattern="#,##0"/>
											</b>
											
										</p>
									</div>
									<div class="right">
										<div class="circle-box ">
											<div class="">
											    <span>
											    	<fmt:formatNumber value="${ ((useCount / dateCount) / 9331828) * 100 }" pattern="#,##0.00"/>%
											    </span>
											</div>
											<svg  viewBox="0 0 200 200" width="100%" height="100%">
												<defs>
													<linearGradient id="gradient1" x1="0%" y1="0%" x2="100%" y2="100%">
													    <stop offset="20%" style="stop-color: #007bff; stop-opacity: 1" />
														<stop offset="100%" style="stop-color: #c8e2ff; stop-opacity: 1" />
													</linearGradient>
												 </defs>
											    <circle cx="100" cy="100" r="90" class="circle-bg-gray" />
											    <circle cx="100" cy="100" r="90" stroke="url(#gradient1)" fill="none" class="circle animated-circle" stroke-dasharray="565.48" stroke-dashoffset="${565.48 - (((useCount / dateCount) / 9331828) * 565.48)}" />
										    </svg>
										</div>
									</div>
								</div>
							<!--  -->
							<c:forEach var="list" items="${mBUList}">
								<div class="box ">
									<div class="">
										<p class="tit-area">
											<span>
												기준 : ${period[0].startD} ~ ${period[0].endD} (${dateCount}일)
											</span>
											버스 사용자수
											<b class="txt-blue">
												<fmt:formatNumber value="${ useCount }" pattern="#,##0"/>
											</b>
											
										</p>
										<p class="tit-area">
											<span>
												기준 : ${period[0].startD} ~ ${period[0].endD} (<fmt:formatNumber value="${list.daycount}" pattern="#,##0"/>일)
											</span>
											${list.day_status} 버스 사용자수 		
											<b class="txt-blue"> 
												<fmt:formatNumber value="${list.allcount}" pattern="#,##0.00"/>
												( <fmt:formatNumber value="${ (list.allcount*0.1 / useCount*0.1 ) * 10000.0 }" pattern="#,##0.00"/>% )
											</b>									
										</p>
									</div>
									<div class="flex ju-between bottom">
										<c:forEach var="item" begin="1" end="6">
											<c:set var="bName" value="bus${item}" />
											<div style="width:15%; ">	
												<p class="txt-12 txt-center">
													<span>${item == 1 ? '서울간선' : item == 2 ? '서울지선' : item == 3 ? '서울심야': item == 4 ? '서울순환': item == 5 ? '서울마을' : '서울광역'}</span>
												</p>
												<div class="circle-box ">
													<div class="">
													    <span><fmt:formatNumber value="${ list[bName] *0.1 / list.allcount *0.1 * 10000}" pattern="#,##0.00"/>%</span>
													</div>
													<svg  viewBox="0 0 200 200" width="100%" height="100%">
														<defs>
															<linearGradient id="gradient3" x1="0%" y1="0%" x2="100%" y2="100%">
															    <stop offset="20%" style="stop-color: #007bff; stop-opacity: 1" />
																<stop offset="100%" style="stop-color: #c8e2ff; stop-opacity: 1" />
															</linearGradient>
														 </defs>
													    <circle cx="100" cy="100" r="90" class="circle-bg-gray" />
													    <circle cx="100" cy="100" r="90" stroke="url(#gradient1)"  class="circle animated-circle" stroke-dasharray="565.48" stroke-dashoffset="${565.48 - ((list[bName] *0.1 / list.allcount *0.1) * 56500.48)}" />
												    </svg>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</c:forEach>
							
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
								<div class="chart-area bar-type tab-box">
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
														    <div>
														    	<fmt:formatNumber value="${ list[fieldName] / list.HR * 100 } " pattern="#,##0.00"/>%
														    	<br /> 
														    	<strong><fmt:formatNumber value="${list[fieldName]}" pattern="#,##0"/>명</strong>
														    </div>
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
					<div class="section sec03">
						<ul class="flex">
							<li>NO</li>
							<li>노선명(버스종류)</li>
							<li>정류장수</li>
							<li>사용수예측 / MAX AM </li>
							<li>사용수예측 / MAX PM </li>
							<li>불만접수 현황</li>
						</ul>
						<ul class="main-list">
							<c:forEach var="list" items="${routeTurn}" varStatus="status">
							<li>
								<ul class="flex">
									<li>${status.index + 1}</li>
									<li>${list.RTE_NO}<span>${list.kind} / ${list.type}</span></li>
									<li>${list.countN}</li>
									<li>
										(<fmt:formatNumber value="${list.sumN}" pattern="#,##0.00"/>/kind 숫자 * 08:00 버스 type 별 %)
									 	/ 08:00
									 </li>
									<li>
										(<fmt:formatNumber value="${list.sumN}" pattern="#,##0.00"/>/kind 숫자 * 8:00 버스 type 별 %)
									 	/ 18:00
									 </li>
									<li></li>
								</ul>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>  
				<!-- #content 영역 끝 -->
<jsp:include page="./inc/footer.jsp" />