package grade.dto;

public class Grade {
    private int gradeno;
    private String gradeName;
    private String comm;

    public Grade() {
    }

    public Grade(int gradeno, String gradeName, String comm) {
        this.gradeno = gradeno;
        this.gradeName = gradeName;
        this.comm = comm;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeno=" + gradeno +
                ", gradeName='" + gradeName + '\'' +
                ", comm='" + comm + '\'' +
                '}';
    }

    public int getGradeno() {
        return gradeno;
    }

    public void setGradeno(int gradeno) {
        this.gradeno = gradeno;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }
}
