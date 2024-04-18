package grade.dto;

public class Grade {
    private int gradeno;
    private String gradename;
    private String comm;

    public Grade() {
    }

    public Grade(int gradeno, String gradename, String comm) {
        this.gradeno = gradeno;
        this.gradename = gradename;
        this.comm = comm;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeno=" + gradeno +
                ", gradename='" + gradename + '\'' +
                ", comm='" + comm + '\'' +
                '}';
    }

    public int getGradeno() {
        return gradeno;
    }

    public void setGradeno(int gradeno) {
        this.gradeno = gradeno;
    }

    public String getgradename() {
        return gradename;
    }

    public void setgradename(String gradename) {
        this.gradename = gradename;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }
}
