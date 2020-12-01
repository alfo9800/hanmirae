//아래 영역은 네이티브(오리지널)자바스크립트로 메뉴액션 구현
//document.getElementById("css 아이디영역");

//함수의 기본형
function Add(a, b) {//a,b 받아서 구현(아래) 매개변수=인자=파라미터 
	var sum = a+b;
	alert(a + "더하기" + b + " 는 " + sum + "입니다.");
}
//Add(3,5);//실행은 함수명을 호출하면 실행됨.//현재 지금 상태는 실행되지 않게 주석 처리

//j쿼리 기본형식사용
$(document).ready(function(){
	$(".menu-toggle-btn").click(function(){
		//alert("j쿼리로 메뉴를 클릭하였습니다."); //디버그
		$(".gnb").stop().slideToggle("fast"); //j쿼리 함수는 뒤에서 앞으로 해석. 그리고 대소문자 구분!!(slideToggle)
		
	});
});

//네이티브 자바스크립트로 메뉴 제어 하기 : 위에서 부터 아래로 실행됨
//document.getElementsById 는 1개의값만 리턴(출력)
//document.getElementsByClassName 는 배열 값을 리턴(출력)
/* window.onload = document_ready; //문서를 미리 읽어들이고, 함수를 실행. j쿼리의 $(document).ready()
function document_ready() {
	var menu_toggle_btn = document.getElementsByClassName("menu-toggle-btn");
	menu_toggle_btn[0].onclick = function() {
		alert('네이티브 js로 메뉴를 클릭하였습니다.');
	}
} */