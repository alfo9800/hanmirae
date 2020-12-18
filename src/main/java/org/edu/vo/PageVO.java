package org.edu.vo;
/**
 * PageVO클래스로서의 회원,게시판 공통으로 사용됨.
 * 1페이지당 보여줄 개수를 이용해서 전체데이터를 분할해서 보여주는 역할.
 * 필요이유: 회원 또는 게시물이 10만건을 한 페이지에 보여주면, 조회속도 느려짐.
 * 그래서 1페이지당  10,20개 씩 제한을 두어, 속도향상 및 사용자화면을 개선 시킴.
 * - 1페이지 계산 10(1페이지당출력할 갯수)*(1[몇번째페이지번호]-1) = 0  
   - 2페이지 계산 10*(2-1) = 10[계산결과나온 시작페이지번호] 
 * @author 한미래
 *
 */
public class PageVO {
	//페이징 처리 부분
	private int perPageNum; //리스트 하단에 보이는 번호의 갯수 값이 들어가는 변수
	private int perQueryPageNum; //쿼리에서 1페이지당 출력할 개수값이 들어가는 변수
	private Integer page; //jsp에서 선택한 페이지 번호 변수
	private int startNo; //[계산식필요]쿼리에서 사용 될 시작번호 값이 들어가는 변수
	private boolean prev; //[계산식필요]페이징에서 이전 번호가 있을 때 표시값이 들어가는 변수
	private boolean next; //[계산식필요]페이징에서 이후 번호가 있을 때 표시값이 들어가는 변수
	//boolean:일반형데이터 변수- 클래스형변수,null로 입력되었을 때 처리하는 로직
	
	//위에 생성한 프리뷰,넥스트 변수값이 있는지 없는지 확인하려면, [계산식필요]
	//계산할 때 필요한 변수 3가지.
	private int totalCount; //회원(또는 게시물) 전체의 갯수 값이 들어가는 변수
	private int startPage; //jsp화면에서 보여주는 페이징 리스트의 시작번호
	private int endPage; //jsp화면에서 보여주는 페이징 리스트의 끝 번호
	
	//검색에 필요한 변수 2개도 포함시켜서, 컨트롤러에서 매개 변수 사용을 축소하게 됨.(requestparam 사용을 축소)
	private String search_type; //검색조건
	private String search_keyword; //검색어
	
	
	//전체클래스에서 계산식이 4개 필요. 개발자가 만들어야 함.
	//[계산식 필요]-> startPage(11), endPage(20), prev(true), next(false)
	private void calcPage() {
		//page변수는 현재jsp에서 클릭한 페이지번호를 받아서 아래 계산식에서 사용
		//(int)형변환: 2.1 ,2.8 이 나오면 모두 2로 반환이 되어서 나온다.
		//ceil메서드: 천장함수 = 천장 값을 반환  / 1.1-> 2 , 2.3-> 3
		//floor메서드: 바닥함수 = 바닥 값을 반환 / 1.1-> 1, 2.3-> 2
		int tempEnd = (int)(
				Math.ceil(page/(double)this.perPageNum)*this.perPageNum
				);
		//jsp에서 클릭한 페이지번호 예로 1을 기준으로 끝 페이지를 계산한다(위)
		/*start페이지와 end페이지 변수가 필요한 이유?
		 ex) < 1 2 3 4 5 6 7 8 9 10 > 페이징 리스트의 시작1과 끝10 값이 바뀌게 됨.
		       < 11 12 13 14 15 16 17 18 19 20 > 시작11과 끝20
		 */
		this.startPage = (tempEnd - this.perPageNum) + 1;
		//jsp에서 11을 클릭 했을 때: (20-10)+1 = 11 스타트 페이지 값(위)
		
		/*(아래) 20×10 = 200 개의 레코드 (회원(게시물))
		 *만약 회원(게시물) 195개 일 경우가 있음.
		 */	
		if(tempEnd*this.perPageNum > this.totalCount) {
			//클릭한 page번호로 계산된 게시물 추가 실제 게시물(totalCount)수보다 클 때
			this.endPage =  (int)Math.ceil(
					this.totalCount/(double)this.perPageNum
					); // 195/10 -> 19.5의 ceil은 20. 결과적으로 20출력			
		} else {
			//ex) 전체회원(게시물)수가 195일 때 page 1을 클릭한 경우 100 > 195
			//195/10 -> 20 잘못된 경우, 다음처럼 나와서 위 조건을 타면 안되고 else문으로 진입해라.
			this.endPage = tempEnd; //endpage가 10일 때 tempEnd를 10으로 표시해라.
		}
		//------------------------------endPage를 구하는 계산식--------------------------------
		//아래는 prev, next 구하는 계산식.
		this.prev = (this.startPage != 1); //시작페이지가 1조다 크면 무조건 페이지가 있다고 본다.
		//ex) 스타트페이지 11. 결과값은 true.
		this.next = (this.endPage*this.perPageNum < this.totalCount);
		//20×10 < 195 결과는 false이기 때문에 jsp에서 '<'표시가 안보이게 함.
	}
	
	//GET SET
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		perPageNum = 10; //강제로 1페이지당 보여줄 개수 값을 10개로 저장
		this.perPageNum = perPageNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getStartNo() {
		//DB쿼리에서 사용. 결과값은 시작인덱스 번호를 구하는 계산식. 0부터 사용.
		//(jsp에서 클릭한 페이지번호 -1)×페이지당 보여지는 개수
		startNo = perQueryPageNum*(this.page-1); //개발자가 추가한 계산식.perPageNum=5
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public boolean getPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean getNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//totalCount 변수값이 만들어지는 순간 calcPage메서드가 실행되면, 4개의 변수값 SET반환.
		calcPage(); //totalCount변수값이 필수로 필요한 메서드이다.
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}

	public int getPerQueryPageNum() {
		return perQueryPageNum;
	}

	public void setPerQueryPageNum(int perQueryPageNum) {
		this.perQueryPageNum = perQueryPageNum;
	}
	
}
