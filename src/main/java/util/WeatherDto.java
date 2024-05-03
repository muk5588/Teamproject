package util;

public class WeatherDto {
    private String date;
    private String time;
    private double PTY;
    private double REH;
    private double RN1;
    private double T1H;
    private double UUU;
    private double VVV;
    private double VEC;
    private double WSD;

    public WeatherDto() {
    }

    public WeatherDto(String date, String time, double PTY, double REH, double RN1, double t1H, double UUU, double VVV, double VEC, double WSD) {
        this.date = date;
        this.time = time;
        this.PTY = PTY;
        this.REH = REH;
        this.RN1 = RN1;
        T1H = t1H;
        this.UUU = UUU;
        this.VVV = VVV;
        this.VEC = VEC;
        this.WSD = WSD;
    }

    @Override
    public String toString() {
        return "WeatherDto{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", PTY=" + PTY +
                ", REH=" + REH +
                ", RN1=" + RN1 +
                ", T1H=" + T1H +
                ", UUU=" + UUU +
                ", VVV=" + VVV +
                ", VEC=" + VEC +
                ", WSD=" + WSD +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPTY() {
        return PTY;
    }

    public void setPTY(double PTY) {
        this.PTY = PTY;
    }

    public double getREH() {
        return REH;
    }

    public void setREH(double REH) {
        this.REH = REH;
    }

    public double getRN1() {
        return RN1;
    }

    public void setRN1(double RN1) {
        this.RN1 = RN1;
    }

    public double getT1H() {
        return T1H;
    }

    public void setT1H(double t1H) {
        T1H = t1H;
    }

    public double getUUU() {
        return UUU;
    }

    public void setUUU(double UUU) {
        this.UUU = UUU;
    }

    public double getVVV() {
        return VVV;
    }

    public void setVVV(double VVV) {
        this.VVV = VVV;
    }

    public double getVEC() {
        return VEC;
    }

    public void setVEC(double VEC) {
        this.VEC = VEC;
    }

    public double getWSD() {
        return WSD;
    }

    public void setWSD(double WSD) {
        this.WSD = WSD;
    }
}
