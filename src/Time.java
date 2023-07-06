import java.awt.*;

public class Time {
    private int min;
    private int sec;

    public Time() {
        min = 0;
        sec = 0;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public void draw(Graphics g) {
        g.drawString(min + ":" + sec, 340, 20);
    }
}
