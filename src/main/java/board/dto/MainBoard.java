package board.dto;

public class MainBoard {
    private int categoryNo;
    private String title;
    private String categoryName;
    private String nickName;
    private int boardNo;
    public MainBoard() {
    }

    public MainBoard(int categoryNo, String title, String categoryName, String nickName, int boardNo) {
        this.categoryNo = categoryNo;
        this.title = title;
        this.categoryName = categoryName;
        this.nickName = nickName;
        this.boardNo = boardNo;
    }

    @Override
    public String toString() {
        return "MainBoard{" +
                "categoryNo=" + categoryNo +
                ", title='" + title + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", boardNo=" + boardNo +
                '}';
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }
}
