package criTest;

// Criteria: (판단이나 결정을 위한) 기준
// 출력할 Row를 select하기 위한 클래스

public class Criteria {
	private int currPage ;      // 현재 페이지번호
	private int rowPerPage;		// 페이지당 보여줄 Row 갯수
	private int sno;            // startRno
	private int eno;            // endRno
	
	// 기본값 생성자로 초기화
	public Criteria() {
		//최초 게시판에 진입할 때를 위해서 기본 값을 설정 해야 한다.
		this.currPage=1;
		this.rowPerPage=5;
	} //생성자
	
	// 1) 출력할 (요청받은) PageNo set
	public void setCurrPage(int currPage) {
		if (currPage > 1) {
			this.currPage = currPage;
		} else this.currPage=1;
	}
	
	// 2) 1페이지당 보여줄 Row(Record, 튜플) 갯수 확인
	// 제한조건 점검(50개까지만 허용)
	public void setRowPerPage(int rowPerPage) {
		if(rowPerPage < 1 || rowPerPage > 50) {
			this.rowPerPage = 5;
		} else this.rowPerPage = rowPerPage;
	}
	
	// 3) startRowNo, endRowNo 계산
	public void setSnoEno() {
		if(this.sno < 1) this.sno = 1;
		this.sno = (this.currPage - 1)*this.rowPerPage + 1;
		this.eno = this.sno + this.rowPerPage - 1;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public int getCurrPage() {
		return currPage;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	@Override
	public String toString() {
		return "Criteria [currPage=" + currPage + ", rowPerPage=" + rowPerPage + ", sno=" + sno + ", eno=" + eno + "]";
	}
	
}
