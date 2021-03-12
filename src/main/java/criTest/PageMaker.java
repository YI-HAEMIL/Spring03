package criTest;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

// ** Criteria를 이용해서 출력할 Page View에 필요한 값을 완성
public class PageMaker {
	private int totalRow; // 전체 레코드 개수
	private int sPageNo; // view에 표시할 첫 PageNo
	private int ePageNo; // view에 표시할 끝 PageNo
	private boolean prev; // 이전 Page블럭으로
	private boolean next; // 다음 Page블럭으로
	private int displayPageNo = 3; // view에 표시할 PageNo의 개수
	private int lastPageNo;
	// 출력 가능한 마지막 PageNo (totalRow, rowPerPage로 계산)

	private Criteria cri;

	// 1) 필요한 값 set
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
		calcData();
	}

	// 2) 필요한 값 계산
	// => totalRow를 이용해서 sPageNo, ePageNo,
	// prev, next, lastPageNo 등 계산
	public void calcData() {
		// 2.1) currPage가 속한 페이지블럭의 ePageNo 를 계산
		// => n개씩 출력한다면 ePagNo 는 늘 n의 배수
		// 	  displayPageNo=3 이면 3, 6, 9, 12, ......
		// => ex) 17 page 요청 , displayPageNo=3, 일때 17이 몇번째 그룹 인지 계산하려면,
		// 		  17/3 -> 5 나머지 2 결론은 정수 나누기 후 나머지가 있으면 +1 이 필요함
		// 		-> Math.ceil(17/3) -> Math.ceil(5.73..) -> 6.0 -> 6번쨰 그룹 16,17,18
		// 즉, 17이 몇번째 그룹 인지 계산하고, 여기에 * displayPageNo 를 하면 됨.
		
		ePageNo = (int) (Math.ceil(cri.getCurrPage() / (double) displayPageNo) * displayPageNo);
		
		// Math.ceil(c) : 매개변수 c 보다 크면서 같은 가장 작은 정수를 double 형태로 반환
		// ceil -> 천장 , 예) 11/3=3.666.. lastPage=4
		// => Math.ceil(12.345) => 13.0
		
		sPageNo = (ePageNo - displayPageNo) + 1;
		
		// 2.2) lastPageNo, ePageNo 확인
		// => 계산으로 얻어진 ePageNo가 실제 LastPage인 lastPageNo보다 크면 수정 필요
		lastPageNo = (int)Math.ceil(totalRow/(double)cri.getRowPerPage());
		if(ePageNo > lastPageNo) { ePageNo = lastPageNo; }
		prev = sPageNo==1? false:true;
		next = ePageNo==lastPageNo? false:true;
	} //calcData

	// 3) 필요값 get
	public int getTotalRow() {
		return totalRow;
	}

	public int getsPageNo() {
		return sPageNo;
	}

	public int getePageNo() {
		return ePageNo;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNo() {
		return displayPageNo;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public Criteria getCri() {
		return cri;
	}
	
	// 4) QueryString 만들기
	// 패키지 org.springframework.web.util
	// => 웹개발에 필요한 많은 유틸리티 클래스 제공
	// => UriComponents , UriComponentsBuilder
	// Uri를 동적으로 생성해주는 클래스,
	// 파라미터가 조합된 uri를 손쉽게 만들어줌
	// => ?currPage=8&perPageRow=10 이것을 만들어줌
	// ? 부터 만들어지므로 jsp Code에서 ? 포함하지 않도록 주의
	public String makeQuery(int currPage) {
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance().
				queryParam("currPage", currPage).		// ?currPage=5
				queryParam("rowPerPage", cri.getRowPerPage()).
				build();
		return uriComponents.toString();
	} //makeQuery

	@Override
	public String toString() {
		return "PageMaker [totalRow=" + totalRow + ", sPageNo=" + sPageNo + ", ePageNo=" + ePageNo + ", prev=" + prev
				+ ", next=" + next + ", displayPageNo=" + displayPageNo + ", lastPageNo=" + lastPageNo + ", cri=" + cri
				+ "]";
	}
}
