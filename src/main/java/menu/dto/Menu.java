package menu.dto;

public class Menu {
    private int userno,gradeno;
    private String nickname,gradename,comm;
    private String categoryName;
    private int categoryNo;
    public Menu() {
    }

    public Menu(int userno, int gradeno, String nickname, String gradename, String comm, String categoryName, int categoryNo) {
        this.userno = userno;
        this.gradeno = gradeno;
        this.nickname = nickname;
        this.gradename = gradename;
        this.comm = comm;
        this.categoryName = categoryName;
        this.categoryNo = categoryNo;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "userno=" + userno +
                ", gradeno=" + gradeno +
                ", nickname='" + nickname + '\'' +
                ", gradename='" + gradename + '\'' +
                ", comm='" + comm + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryNo=" + categoryNo +
                '}';
    }

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public int getGradeno() {
        return gradeno;
    }

    public void setGradeno(int gradeno) {
        this.gradeno = gradeno;
    }

    public String getnickname() {
        return nickname;
    }

    public void setnickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }
}
