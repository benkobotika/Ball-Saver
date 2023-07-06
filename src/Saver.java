import java.awt.*;

public class Saver extends Rectangle implements Same {
    private int positionX;
    private final int positionY;
    private final int saverWidth;
    private final int saverHeight;

    public Saver() {
        positionX = gameFrameWidth / 2 - 30;
        positionY = gameFrameHeight - 50;
        saverWidth = 60;
        saverHeight = 25;
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

    public int getSaverWidth() {
        return saverWidth;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(positionX, positionY, saverWidth, saverHeight);
    }
}
