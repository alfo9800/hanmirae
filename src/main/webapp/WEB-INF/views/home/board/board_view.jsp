<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" href="/resources/home/css/board.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="/resources/plugins/fontawesome-free/css/all.min.css">
<!-- Bootstrap 4 -->
<link rel="stylesheet" href="/resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<script src="/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLte -->
<link rel="stylesheet" href="/resources/dist/css/adminlte.min.css">


	<!-- 메인콘텐츠영역 -->
	<div id="container">
		<!-- 메인상단위치표시영역 -->
		<%@ include file="../include/container_header.jsp" %>	
		<!-- //메인상단위치표시영역 -->

		<!-- 메인본문영역 -->
		<div class="bodytext_area box_inner">			
			<ul class="bbsview_list">
				<li class="bbs_title"><c:out value="${boardVO.title}" /> [작성자] : <span>${boardVO.writer}</span> </li>
				<li class="bbs_hit">작성일 : <span><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.reg_date}" /></span></li>
				<li class="bbs_date">조회수 : <span>${boardVO.view_count}</span></li>
				<li class="bbs_content">
					<div class="editer_content">
						${boardVO.content}
                    </div>
				</li>
				<li class="bbs_title" style="height:inherit;">
					첨부파일 다운로드
					<c:forEach begin="0" end="1" var="index">
						<c:if test="${boardVO.save_file_names[index] != null}">
							<br>
							<a href="/download?save_file_name=${boardVO.save_file_names[index]}&real_file_name=${boardVO.real_file_names[index]}" >${boardVO.real_file_names[index]} 다운로드 링크[${index}]</a>
							<c:set var="fileNameArray" value="${fn:split(boardVO.save_file_names[index],'.')}" />
			                <c:set var="extName" value="${fileNameArray[fn:length(fileNameArray)-1]}" />
							<!-- lenght의 결과는 2-1= 배열의 인덱스1 -->
							<!-- 첨부파일이 이미지 인지 아닌지 비교해서 img태그를 사용할 지 결정 -->
							
							<!-- fn:contains함수({'jpg','gif','png'...}비교배열내용,JPG,jpg첨부파일확장자) -->
							<c:choose>
								<c:when test="${fn:containsIgnoreCase(checkImgArray,extName)}">
									<br>
									<img style="width:100%;" src="/image_preview?save_file_name=${boardVO.save_file_names[index]}&real_file_name=${boardVO.real_file_names[index]}">
								</c:when>
								<c:otherwise>
									<c:out value="${checkImgArray}" />
									<!-- 사용자 홈페이지 메인 최근게시물 미리보기 이미지가 없을 때 사용 예정. -->
								</c:otherwise>
							</c:choose>
							<!-- true이면 이미지 파일이란 의미 -->		
						</c:if>
					</c:forEach>
				</li>
			</ul>
			<p class="btn_line txt_right">
				<a href="/home/board/board_list?page=${pageVO.page}" class="btn_bbs">목록</a>
				<a href="/home/board/board_update?bno=${boardVO.bno}&page=${pageVO.page}" class="btn_bbs">
				수정
				</a>				
				<button class="btn_baseColor btn_smallColor" id="btn_board_delete">삭제</button>
			</p>
			
		</div>
		<!-- //메인본문영역 -->
	</div>
	<!-- //메이콘텐츠영역 -->
	
	<!-- 댓글영역 시작 -->
          <div class="card card-primary card-outline col-12" style="padding-bottom:10px;">
	          <div class="card-header">
	            <h5 class="card-title">Add New Reply</h5>
	          </div>
	          <form action="#" name="reply_form" method="post">
	          <div class="card-body">
	          	<div class="form-group">
                   <label for="writer">Writer</label>
                   <input value="${session_username}" type="text" class="form-control" name="replyer" id="replyer" placeholder="작성자를 입력해 주세요." required>
                   <!-- 폼에서 input같은 입력태그에는 name속성이 반드시 필요, 이유는 DB에 입력할때,
                   	 값을 전송하게 되는데, 전송값을 담아두는 이름이 name가 되고, 위에서는 writer 입니다. -->
                </div>
                <div class="form-group">
                   <label for="reply_text">Reply Text</label>
                   <input type="text" class="form-control" name="reply_text" id="reply_text" placeholder="내용을 입력해 주세요." required>
                   <!-- 아래 게시판에서는 폼을 전송할때 submit 타입을 사용 하지만, 댓글은 Ajax로 전송하기 때문에, button타입으로 지정함. -->
                </div>
                <button type="button" class="btn btn-warning float-left mr-1 text-white" id="insertReplyBtn">댓글등록</button>
	          </div>
	          </form>
	          
	          <div class="timeline">
	          	  <!-- .time-label의 before 위치 -->		          
		          <div class="time-label">
	                <span data-toggle="collapse" data-target="#div_reply" class="bg-red btn" id="btn_reply_list">Reply List[<span id="reply_count">${boardVO.reply_count}</span>]&nbsp;&nbsp;</span>
	              </div>
	              
	              <div id="div_reply" class="timeline collapse">	              	
	              	<!-- 페이징처리 시작 -->
	          		<div class="pagination justify-content-center">
		            	<ul class="pagination">
			            	 <ul class="pagination pageVO">
								
			            	</ul>
	         		 </div>
		  	  		<!-- 페이징처리 끝 --> 	              	
	              </div>	              
	          </div><!-- //.timeline -->
	              <input type="hidden" name="reply_page" id="reply_page" value="1">
	      </div>
<!-- 댓글영역 끝 -->
<!-- 자바스트립트용 #template 엘리먼트 제작(아래) jstl 향상된 for문과 같은 역할 
jstl을 사용하려면, jsp에서 @taglib uri=... 처럼 외부 core를 가져와서 사용한 것처럼
자바스크립트에서도 외부 core를 가져와야 합니다.(아래)
-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- 댓글리스트 템플릿(빵틀) 만들기(아래) -->
<!-- jsp의 forEach문과 같은 역할 #each . -->
<script id="template" type="text/x-handlebars-template">
{{#each .}}
<div class="template-div" data-rno="{{rno}}">
 <i class="fas fa-envelope bg-blue"></i>
 <div class="timeline-item">
   <h3 class="timeline-header">{{replyer}}</h3>
   <div class="timeline-body">{{reply_text}}</div>
   <div class="timeline-footer">
	 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#replyModal">
  		수정
	 </button>
   </div>
 </div>
</div>
{{/each}}
	</script>

<!-- 화면을 재구현 Representation하는 함수 -->
<script>
var printReplyList = function(data, target, templateObject){
	var template = Handlebars.compile(templateObject.html()); //html태그로 변환
	var html = template(data); //빅데이터를 리스트 템플릿에 바이딩 결함시켜주는 역할. 변수 html에 저장 되었음.
	$(".template-div").remove(); //화면에 보이는 댓글리스트만 지우기
	target.prepend(html); //target은 .time-label클래스 영역을 가리킴.
};
</script>

<!-- 댓글 페이징 재구현Representation하는 함수 -->
<script>
var printPageVO = function(pageVO, target) {
	 var paging = "";
	 if(pageVO.prev){
		 paging = paging + '<li class="paginate_button page-item previous disabled" id="example2_previous"><a href="'+(pageVO.startPage-1)+'" aria-controls="example2" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li>';
	 }
	 for(cnt=pageVO.startPage;cnt<=pageVO.endPage;cnt++){ var active = (cnt==pageVO.page)?"active":"";
		 paging = paging + '<li class="paginate_button page-item '+active+'"><a href="'+cnt+'" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">'+cnt+'</a></li>';
	 }
	 if(pageVO.next){
		 paging = paging + '<li class="paginate_button page-item next" id="example2_next"><a href="'+(pageVO.endPage+1)+'" aria-controls="example2" data-dt-idx="7" tabindex="0" class="page-link">Next</a></li>';
	 }
	 target.html(paging);
}
</script>

<!-- 댓글 리스트 실행 하는 함수 -->
<script>
var replyList = function(){
	var page = $("#reply_page").val(); //현재 지정된 댓글 페이지 값 가져오기
	$.ajax({
		url:"/reply/reply_list/${boardVO.bno}/" +page, //쿼리스트링X, 패스베리어블로 보냄.
		type:"post", //원래는 get인데, post로 보낼수 있음.
		dataType:"json",
		success:function(result){
			if(result=="undefined" || result=="" || result==null){
				$("#div_reply").empty();
				$("#div_reply").html('<div class="pagination justify-content-center"><ul class="pagination pageVO"></ul></div>');
				alert("조회된 값이 없습니다.");
			}else{
				printReplyList(result.replyList,$("#div_reply"),$("#template")); //댓글리스트 출력
				printPageVO(result.pageVO,$(".pageVO"));
			}
		},
		error:function(){
			
		}
	});
}
</script>

<!-- 페이징의 번호 링크액션을 처리하는 함수 -->
<script>
$(document).ready(function(){
	$(".pageVO").on("click","li a",function(event){
		event.preventDefault(); //디폴트 액션 링크 이동 방지
		var page = $(this).attr("href"); //GET
		$("#reply_page").val(page); //SET :매개변수가 없으면 GET $("#reply_page").val();
		//alert("debug");
		replyList();
	});
});
</script>

<!-- 댓글 리스트 버튼 클릭이벤트 처리 -->
<script>
$(document).ready(function(){
	$("#btn_reply_list").on("click", function(){
		//alert("debug");
		replyList();
	});
});
</script>

<!-- ---------------------------------------------------------------------------- -->

<!-- 댓글 수정 버튼 액션 처리 -->
<script>
$(document).ready(function(){
	$("#updateReplyBtn").on("click",function(){
		if("${session_enabled}" == ""){
			alert("회원만 댓글 수정이 가능합니다.");
			location.href = "/login";
			return false;
		}
		var reply_text = $("#replytext").val(); //modal의 input에 해당하는 것 가져옴. 아래rno도.
		var rno = $("#rno").val();
		$.ajax({
			type:"patch",
			url:"/reply/reply_update",
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"PATCH"
			},
			data:JSON.stringify({
				rno:rno,
				reply_text:reply_text
			}),
			dataType:"text",
			success:function(result){
				if(result=="success"){
					alert("댓글 수정에 성공하였습니다.");
					$("#replyModal").modal("hide");
					replyList();
				}else{
					alert("댓글 수정에 실패하였습니다.");
				}
			},
			error:function(result){
				alert("RestAPI서버에 문제가 있습니다. 다음에 이용해 주세요!");
			}
		});
	});
});
</script>

<!-- 댓글 삭제 버튼 액션 처리 -->
<script>
$(document).ready(function(){
	$("#deleteReplyBtn").on("click",function(){
		if("${session_enabled}" == ""){
			alert("회원만 댓글 삭제가 가능합니다.");
			location.href = "/login";
			return false;
		}
		var rno = $("#rno").val();
		$.ajax({
			type:"delete",
			url:"/reply/reply_delete/${boardVO.bno}/"+rno,
			dataType:"text",
			success:function(result){
				if(result=="success"){
					alert("댓글 삭제에 성공하였습니다.");
					var reply_count = $("#reply_count").text(); //GET 
					$("#reply_count").text(parseInt(reply_count)-1); //SET
					replyList();
					$("#replyModal").modal("hide"); //모달창 숨기는 기능
				}
			},
			error:function(result){
				alert("RestAPI서버오류로 삭제에 실패했습니다.");
			}
		});
	});
});
</script>

<!-- 댓글 등록 버튼 액션 처리 -->
<script>
$(document).ready(function() {
	$("#insertReplyBtn").on("click", function() {//댓글등록버튼을 클릭했을 때 구현내용(아래)
	if("${session_enabled}" == ""){ //버튼 클릭 시 비로그인 시 로그인 화면으로 가도록 설정
		alert("회원만 댓글 등록이 가능합니다.");
		location.href = "/login"; //location은 자바스크립트 내장 함수로서 (href:URL이동 함수)이다.
		return false;
	}
	//alert("디버그");
		//Ajax를 이용해서, 화면을 Representation (REST-API방식) 부분 화면을 재구현(아래)
		var bno = "${boardVO.bno}"; //자바
		var reply_text = $("#reply_text").val(); //jQuery //input type은 val()함수를 가져올 수 있음.
		var replyer = $("#replyer").val(); //jQuery
		if(reply_text == "" || replyer == "") {
			alert("댓글 내용과 작성자는 필수 입력 사항입니다.");
			return false;
		}
		$.ajax({//통신프로그램
			//여기서부터는 프론트 엔드 개발자 영역
			type:'post',//지금은 html이라서 get방식이지만, jsp로가면, post방식으로 바꿔야 합니다.
			url:'/reply/reply_write',//jsp로 가면, ReplyController 에서 지정한 url로 바꿔야 합니다.
			dataType:'text',//ReplyController에서 받은 데이터의 형식은 text형식으로 받겠다고 명시.
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"POST"
			},
			data:JSON.stringify({
				bno:bno,
				reply_text:reply_text,
				replyer:replyer
			}),
			success:function(result) {//응답이 성공하면(상태값200)위경로에서 반환받은 result(json데이터)를 이용해서 화면을 재구현
				var reply_count = $("#reply_count").text(); //GET //var로 reply_count라는 위에서 만든 변수를 만든다.
				if(reply_count == ""){reply_count=0;}
				$("#reply_count").text(parseInt(reply_count)+1); //SET
				
				//예로 3페이지에서 보다가 등록 후 작성한 댓글을 확인 가능하도록 1page로 가도록 유도
				$("#reply_page").val("1"); //1page값으로 SET
				replyList(); //댓글입력 후 리스트 출력함수 호출
				$("#replyer").val("");
				$("#reply_text").val("");
			},
			error:function(result){
				alert("RestAPI서버가 작동하지 않습니다. 다음에 이용해 주세요!");
			}
		});
	});
});
</script>

<!-- 댓글리스트에서 수정 버튼을 클릭했을때, 팝업창이 뜨는데, 그 팝업창에 내용을 동적으로 변경시켜주는 구현(아래)  -->
<script>
$(document).ready(function() {
	$(".timeline").on("click", ".template-div", function() {//.template-div 댓글 리스트영역
		//$(this);클릭한 댓글에 따라서 this는 첫번째 댓글일 수 도 있고, 두번째 댓글일 수도 있습니다.
		$("#rno").val($(this).attr("data-rno"));
		$(".modal-title").html($(this).find(".timeline-header").text());
		$("#replytext").val($(this).find(".timeline-body").text());
		//alert("디버그" + $(this).find(".timeline-body").text());
		//마우스클릭으로 선택한 댓글의 .timeline-body영역의 text문자를 
		//모달창의 #replaytext영역에 값으로 입력하겠다.
	});
});
</script>

<!-- Modal -->
<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">작성자</h5><!-- 작성자는 동적으로 값이 바뀝니다. -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<input type="hidden" name="rno" id="rno" value=""><!-- 상단 $(".timeline").on("click" 액션으로 value값이 채워집니다. -->
        <input type="text" class="form-control" name="replytext" id="replytext" placeholder="내용을 입력해 주세요.">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary" id="updateReplyBtn">수정</button>
        <button type="button" class="btn btn-danger" id="deleteReplyBtn">삭제</button>
      </div>
    </div>
  </div>
</div>

<form name="action_form">
<input type="hidden" name="bno" value="${boardVO.bno}">
<input type="hidden" name="page" value="${pageVO.page}" >
</form>


<script>
$(document).ready(function(){
	$("#btn_board_delete").on("click",function(){
		if(confirm("정말로 삭제 하시겠습니까?")) {
			$("form[name='action_form']").attr("method","post");
			$("form[name='action_form']").attr("action","/home/board/board_delete");
			$("form[name='action_form']").submit();
		}
	});	
});
</script>


<%@ include file="../include/footer.jsp" %>