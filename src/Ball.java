import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle implements Same {
    private int positionX;
    private int positionY;
    private int direction;
    private boolean checked;

    public Ball() {
        Random random = new Random();
        positionX = random.nextInt(gameFrameWidth - 20);
        positionY = 30;
        direction = 1; //down
        checked = false; //not checked that ball was saved or not
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(positionX, positionY, 10, 10);
    }
}
