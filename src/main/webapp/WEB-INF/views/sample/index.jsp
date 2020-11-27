<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jsp: JavaSeverPage 자바서버용페이지 -->
<%--자바주석 시작 
- jsp에서 자바프로그램을 사용할 때 쓰는 태그 < %~~~~~% > %로 감싸주게 됩니다.  
- 다른언어PHP 일 때, <?~~~~?> 로 감싸주게 됩니다.
- @ page 골뱅이는 애노테이션-페이지 지시자. 랭귀지:java
- 콘텐츠타입:text/html; 문자셋:유니코드 페이징인코딩:유니코드
자바주석 끝 --%>

<!-- Homecontroller.java에서 index.jsp로 매칭 시킨 결과가 나오는 페이지 입니다.<br>
- html에서는 /resources/sample/index.html 이렇게 접근합니다.<br>
- jsp프로그램에서는 위 html처럼 바로접근 못하고, 홈컨트롤러를 거쳐서 index.jsp를 접근하는 이유는 보안때문 입니다. (은행,관공서 등에서 사용. 스프링을 배우는 목적)<br>
- 다른 예로, PHP는 위에 있는 html처럼 파일에 바로 접근이 가능합니다. (그래서, 중소기업에서 사용)<br>
- 출발은 자바...자바에서 출발한 스프링프레임워크는 :자바에서 웹프로그램 만드는데 특화된 라이브러리(프로그램묶음)를 구조화 시켜
   개발자가 편하게 만들어 놓은 것<br>
- jsp는 지금 보이는 프로젝트는 스프링MVC프로젝트입니다.<br>
- 여기서 MVC에서 view단에 해당하는 기술 부분이 jsp입니다.<br>
- MVC = Model(데이터베이스) - View(jsp페이지) <-> Controller(컨트롤러자바클래스)<br>
- org.edu.controller 패키지명:자바클래스가 있는 폴더 경로를 말합니다.<br>
- 패키지를 사용하는 이유: 자바클래스를 기능별로 묶어 놓기 위해서 명시적으로 만든 묶음.<br>
 -->
 
 
<!DOCTYPE html> <!-- 현재 문서의 형태선언 -->
<html> <!-- html문서는 태그열고 ~ 태그닫고 하는 영역확인 중요 -->
<head> <!-- 문서 내용과는 관계없는 문서정보=메타데이터(데이터의데이터)가 존재 -->
<meta charset="UTF-8"> <!-- 문서의 언어 인코딩설정 지금은 유니코드로 됨 -->
<!-- 반응형을 작동하기 위해서 사용하는 메타태그 / 뷰포트는 모니터,모바일액정,프린터 -->
<meta name="viewport" content="width=device-width,initial-scale=1,minium=scale=1,maximum-scale=1">
<title>반응형 홈페이지</title> <!-- 문서의 제목을 브라우저 상단왼쪽에 표시 -->
<!-- 외부 자바스크립트 불러오는 태그(아래) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> <!-- j쿼리 코어 -->
<script src="/resources/sample/js/user.js"></script>
<!-- 외부 css파일 불러오는 태그(아래) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- 위 부트스트랩 코어 -->

<link rel="stylesheet" href="/resources/sample/css/reset.css" type="text/css">
<link rel="stylesheet" href="/resources/sample/css/mobile.css" type="text/css">
<link rel="stylesheet" href="/resources/sample/css/tablet.css" type="text/css">
<link rel="stylesheet" href="/resources/sample/css/pc.css" type="text/css">
<!-- span태그 자리를 차지하지 않는 영역 = 한뼘, px 픽셀화면에 표시되는 크기의 단위 -->
<style>
/*@미디어쿼리사용-위에 메타태그viewport값 필수!!!*/
/*pc용 스타일 시작*/
/*미디어쿼리:미디어의 가로크기가 1132px이상 이면 아래 스타일 적용*/
@media all and (min-width:1132px) {

}
/*pc용 스타일 끝*/
</style>
<script>
/* 자바스크립트 시작*/
$(document).ready(function(){
	$(".carousel").carousel({
		interval:1000, //이미지가 좌우로 움직이는 대기시간 지정. 1초, 크롬 IE 작동O, 파이어폭스 작동X
		pause:false
	});
});

/* 자바스크립트 끝 */
</script>
</head>
<body> <!--  문서내용이 들어가는 영역 -->
<div id="wrap"> <!-- 랩핑 시킨 영역 -->
<header class="header">
<!-- 상단로고와 메뉴영역 시작 -->
<h1 class="logo">
<a href="index.html">LOGO</a> <!-- a태그는 페이지링크역할, href헤르프 속성값에 URL을 입력해서 이동 -->
</h1> <!-- 헤드라인 글자를 표시 h1~h6(숫자는 크기를 나타냄) -->
<div class="menu-toggle-btn">
<span></span>
<span></span>
<span></span>
</div>

<!-- html5 시만텍 웹= 의미가 있는 태그를 사용하게 해서 AI가 인식하게 됩니다. nav태그-->
<nav class="gnb"> <!-- gnb:글로벌네비게이션(전체 영역 메뉴) -->
<!-- 리스트를 모아주는 태그 ul(Unordered List *.~,*.~),  ol(Ordered List 1.~, 2.~) -->
<ul>
<li><a href="index.html">HOME</a></li>
<li><a href="weare.html">WE ARE</a></li>
<li><a href="work.html">WORK</a></li>
<li><a href="blog.html">BLOG</a></li>
<li><a href="contact.html">CONTACT US</a></li>
</ul>
</nav>
<!-- 상단로고와 메뉴영역 끝 -->
</header>
<section> <!-- division 디비전이라는 div 영역표시 태그 대신에 사용 -->
<!-- 본문슬라이드영역 시작 -->
<!-- 이미지를 불러오는 태그 img -->
<!-- 경로:상대(.img/...), 절대경로(/resources/sample/img/...) -->
<!-- 태그안쪽에 지정한 스타일을 인라인스타일  -->
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="/resources/sample/img/tree.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="/resources/sample/img/plant.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="/resources/sample/img/light.jpg" class="d-block w-100" alt="...">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

<!-- 분문슬라이드영역 끝 -->
</section>
<footer class="footer">
<!-- 하단로고 영역 시작 -->
<p>LOGO</p><!-- 문장영역을 의미하는 paragraph -->
<!-- 하단로고 영역 끝 -->
</footer>
</div>
</body>
</html>