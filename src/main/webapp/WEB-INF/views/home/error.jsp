<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp" %>
<link rel="stylesheet" href="/resources/home/css/board.css">

<!-- 인터셉트로 가로챈 에러메시지 출력 -->
	<div id="container">
		<!-- 메인상단위치표시영역 -->
		<div class="location_area customer">
			<div class="box_inner">
				<h2 class="tit_page">스프링 <span class="in">in</span> 자바</h2>
				<p class="location">에러페이지 <span class="path">/</span> ${exception.getMessage()}</p>
				<ul class="page_menu clear">
					<li><a href="#" class="on">${exception.getMessage()}</a></li>
				</ul>
			</div>
		</div>	
		<!-- //메인상단위치표시영역 -->

		<!-- 메인본문영역 -->
		<div class="bodytext_area box_inner">
			<a href="${prevPage}">이전페이지 이동</a>
			<p>에러 상세내역</p>
				
		</div>
		<!-- //메인본문영역 -->
	</div>


<%@ include file="./include/footer.jsp" %>