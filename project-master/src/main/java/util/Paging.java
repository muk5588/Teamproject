package util;

public class Paging {

	private int curPage;	//    - 현재 페이지 번호
	private int totalCount; //    - 총 게시글 수
	private int listCount;	//    - 한 페이지 당 보여질 게시글 수
	private int totalPage;	//    - 총 페이지 수
	private int pageCount;	//    - 한 화면에 출력될 페이지네이션의 개수
	private int startPage;	//    - 화면에서 보이는 시작 페이지네이션의 번호
	private int endPage;	//    - 화면에서 보이는 끝 페이지네이션의 번호
	private int startNo;	//    - 화면에 보이는 게시글의 시작 번호 ( rownum )
	private int endNo;		//    - 화면에 보이는 게시글의 끝 번호 ( rownum )
	
	
	public Paging() {}

	
	public Paging(int totalCount, int curPage) {
		
		setTotalCount(totalCount);
		setCurPage(curPage);
		
		//페이징 처리 정보 생성
		makePaging();
	}

	//페이징 처리 정보를 생성하는 메소드
	private void makePaging() {
		if(totalCount == 0) return;	//게시글이 없을 경우 중단한다. 
		
		if(curPage == 0) setCurPage(1); //첫 페이지를 기본 페이지로 설정
		if(listCount == 0) setListCount(10);	//화면에 보일 게시글 수 기본값 10
		if(pageCount == 0) setPageCount(10); 	//화면에 보일 페이징 수 기본값 10
	
		
		//총 페이지 수 계산
		totalPage = totalCount / listCount;
		if(totalCount % listCount > 0) totalPage++;
		
		//현재 페이지값 보정
		if(curPage > totalPage) curPage = totalPage;
		
		//화면에 보여질 페이지네이션의 시작, 끝 번호
		startPage = ((curPage - 1)/pageCount) * pageCount + 1;
		endPage = startPage + pageCount - 1;
		
		//끝 페이지 보정
		if(endPage > totalPage) endPage = totalPage;
		
		//화면에 보여질 게시글의 시작, 끝 번호
		startNo = (curPage-1)*listCount - 1;
		endNo = curPage * listCount;
	}
	
	
	
	//총 게시글 수, 현재 페이지 번호, 보여질 게시글 수, 보여질 페이지 수를 지정한다. 
	public Paging(int curPage, int totalCount, int listCount, int pageCount) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		setListCount(listCount);
		setPageCount(pageCount);
		
		//페이징 처리 정보 생성
		makePaging();
	}



	@Override
	public String toString() {
		return "Paging [curPage=" + curPage + ", totalCount=" + totalCount + ", listCount=" + listCount + ", totalPage="
				+ totalPage + ", pageCount=" + pageCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", startNo=" + startNo + ", endNo=" + endNo + "]";
	}



	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
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

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	
}
