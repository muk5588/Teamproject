package QandA.dto;

import java.util.Date;

public class QandA {
    private int id;          // 글 번호
    private int userno;      // 작성자 사용자 번호
    private int cateno;      // 게시판 종류 코드
    private String title;    // 게시글 제목
    private String content;  // 게시글 본문
    private Date createDate; // 작성일
    private Date updateDate; // 수정일
    private int view;        // 조회수
    private int good;        // 추천수

    // 기본 생성자
    public QandA() {
    }

    // 매개변수가 있는 생성자
    public QandA(int id, int userno, int cateno, String title, String content, Date createDate, Date updateDate, int view, int good) {
        this.id = id;
        this.userno = userno;
        this.cateno = cateno;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.view = view;
        this.good = good;
    }
    
    

    @Override
	public String toString() {
		return "QandA [id=" + id + ", userno=" + userno + ", cateno=" + cateno + ", title=" + title + ", content="
				+ content + ", createDate=" + createDate + ", updateDate=" + updateDate + ", view=" + view + ", good="
				+ good + "]";
	}

	// getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public int getCateno() {
        return cateno;
    }

    public void setCateno(int cateno) {
        this.cateno = cateno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }
}
