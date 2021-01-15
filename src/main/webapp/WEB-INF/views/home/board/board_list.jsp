<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" href="/resources/home/css/board.css">
<script>
$(document).ready(function() {
	//라이브러리 사용않하고, 오리지널 j쿼리로 만드는 유효성(아래)
	/* $("#search_form").submit(function(){ //검색버튼을 클릭=전송했을때.
		var search_keyword = $("input[name=search_keyword]").val();
		if(search_keyword == "") {
			alert("검색어는 빈 값으로 검색할 수 없습니다.!");
			return false;//조건이 맞으면, 전송중지.
		}
	}); */
});
</script>

	<!-- 메인콘텐츠영역 -->
	<div id="container">
		<!-- 메인상단위치표시영역 -->
		<div class="location_area customer">
			<div class="box_inner">
				<h2 class="tit_page">스프링 <span class="in">in</span> 자바</h2>
				<p class="location">고객센터 <span class="path">/</span> 공지사항</p>
				<ul class="page_menu clear">
					<li><a href="#" class="on">공지사항</a></li>
					<li><a href="#">문의하기</a></li>
				</ul>
			</div>
		</div>	
		<!-- //메인상단위치표시영역 -->

		<!-- 메인본문영역 -->
		<div class="bodytext_area box_inner">
			<!-- 검색폼영역 -->
			<form id="search_form" name="search_form" action="/home/board/board_list" class="minisrch_form">
				<fieldset>
					<legend>검색</legend>
					<input name="search_type" value="all" type="hidden">
					<input name="search_keyword" type="text" class="tbox" title="검색어를 입력해주세요" placeholder="검색어를 입력해주세요">
					<button class="btn_srch">검색</button>
				</fieldset>
			</form>
			<!-- //검색폼영역 -->
			
			<!-- 게시물리스트영역 -->
			<table class="bbsListTbl" summary="번호,제목,조회수,작성일 등을 제공하는 표">
				<caption class="hdd">공지사항  목록</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">조회수</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${board_list}" var="boardVO" varStatus="Status"> <!-- Status: 게시물 넘버링 하기위해 -->
						<tr>
							<!-- ${boardVO.bno} 대신에 보기편한 넘버링으로 변환 (계산식 사용) -->
		                     <td>
		                     ${pageVO.totalCount-(pageVO.page*pageVO.queryPerPageNum)+pageVO.queryPerPageNum-status.index} <!-- 전체게시물-(현재페이지*1페이지당 보여줄 개수)+1페이지당 보여줄 갯수-현재인덱스 값 -->
		                     </td>
							<td class="tit_notice">
								<a href="/home/board/board_view?bno=${boardVO.bno}&page=${pageVO.page}&search_type=${pageVO.search_type}&search_keyword=${pageVO.search_keyword}">
								<c:out value="${boardVO.title}" />
								</a>
							</td>
							<td>${boardVO.view_count}</td>
							<td>
								<fmt:formatDate value="${boardVO.reg_date}" pattern="yyyy-MM-dd HH:mm" />		
							</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
			<!-- //게시물리스트영역 -->
			
			<!-- 페이징처리영역 -->
			<div class="pagination">
				<c:if test="${pageVO.prev}">
					<a href="/home/board/board_list?page=${pageVO.startPage-1}&search_type=${pageVO.search_type}&search_keyword=${pageVO.search_keyword}" class="prevpage  pbtn"><img src="/resources/home/img/btn_prevpage.png" alt="이전 페이지로 이동"></a>
				</c:if>
				<c:forEach begin="${pageVO.startPage}" end="${pageVO.endPage}" var="idx">
					<a href="/home/board/board_list?page=${idx}&search_type=${pageVO.search_type}&search_keyword=${pageVO.search_keyword}">
					<span class="pagenum <c:out value="${(pageVO.page==idx)?'currentpage' : ''}" /> ">${idx}</span>
					</a>
				</c:forEach>
				<c:if test="${pageVO.next}">
					<a href="/home/board/board_list?page=${pageVO.endPage+1}&search_type=${pageVO.search_type}&search_keyword=${pageVO.search_keyword}" class="nextpage  pbtn"><img src="/resources/home/img/btn_nextpage.png" alt="다음 페이지로 이동"></a>
				</c:if>
			</div>
			<!-- //페이징처리영역 -->
			<p class="btn_line">
				<a href="/home/board/board_write" class="btn_baseColor">등록</a>
			</p>
		</div>
		<!-- //메인본문영역 -->
	</div>
	<!-- //메이콘텐츠영역 -->

<%@ include file="../include/footer.jsp" %>