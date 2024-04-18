package menu.dto;

public class Menu {
    private int userno,gradeno;
    private String userid,gradename,comm;

    public Menu() {
    }

    public Menu(int userno, int gradeno, String userid, String gradename, String comm) {
        this.userno = userno;
        this.gradeno = gradeno;
        this.userid = userid;
        this.gradename = gradename;
        this.comm = comm;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "userno=" + userno +
                ", gradeno=" + gradeno +
                ", userid='" + userid + '\'' +
                ", gradename='" + gradename + '\'' +
                ", comm='" + comm + '\'' +
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
}
