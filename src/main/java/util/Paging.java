package util;

public class Paging {

    private int curPage; // 현재 페이지 번호
    private int totalCount; // 총 게시글 수
    private int listCount; // 한 페이지 당 보여질 게시글 수
    private int totalPage; // 총 페이지의 수
    private int pageCount; // 한 화면에 출력될 페이지네이션의 개수
    private int startPage; // 화면에 보이는 시작 페이지네이션 번호
    private int endPage; // 화면에 보이는 끝 페이지네이션 번호
    private int startNo; // 화면의 보이는 게시글의 시작 번호
    private int endNo; // 화면에 보이는 게시글의 끝 번호
    private String search; // 검색어
    private String searchKind; // 검색어 종류 ( 제목: title, 내용:content)
    private int categoryNo; // 카테고리 종류
    private int userno;
    private String complete; // 답변 여부 (Y/N)

    public Paging() {}

    public Paging(int totalCount, int curPage) {
        setTotalCount(totalCount);
        setCurPage(curPage);
        makePaging();
    }

    public Paging(int totalCount, int curPage, int listCount, int pageCount) {
        setTotalCount(totalCount);
        setCurPage(curPage);
        makePaging();
    }

    private void makePaging() {
        if(totalCount == 0) return; // 게시글이 없을 경우 중단

        if(curPage == 0) setCurPage(1); // 첫 페이지를 기본 페이지로 설정
        if(listCount == 0) setListCount(10); // 화면에 보일 게시글 수 기본값
        if(pageCount == 0) setPageCount(10); // 화면에 보일 페이징 수 기본값

        totalPage = totalCount / listCount;
        if(totalCount % listCount > 0) totalPage++;

        if(curPage > totalPage ) curPage = totalPage;

        startPage = ((curPage - 1) / pageCount) * pageCount + 1;
        endPage = startPage + pageCount - 1;

        if(endPage > totalPage) endPage = totalPage;

        startNo = (curPage - 1) * listCount + 1;
        endNo = curPage * listCount;
    }

    // Getter와 Setter 메소드들
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearchKind() {
        return searchKind;
    }

    public void setSearchKind(String searchKind) {
        this.searchKind = searchKind;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "curPage=" + curPage +
                ", totalCount=" + totalCount +
                ", listCount=" + listCount +
                ", totalPage=" + totalPage +
                ", pageCount=" + pageCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", startNo=" + startNo +
                ", endNo=" + endNo +
                ", search='" + search + '\'' +
                ", searchKind='" + searchKind + '\'' +
                ", categoryNo=" + categoryNo +
                ", userno=" + userno +
                ", complete='" + complete + '\'' +
                '}';
    }
}
