import java.awt.*;

public class Life extends Rectangle implements Same {
    private int lifeNumber;
    private int points;

    public Life() {
        points = 0;
        lifeNumber = 5;
    }

    public void setLifeNumber(int lifeNumber) {
        this.lifeNumber = lifeNumber;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLifeNumber() {
        return lifeNumber;
    }

    public int getPoints() {
        return points;
    }

    public void draw(Graphics g) {
        int coordinateX = 60;
        g.setColor(Color.green);
        for (int i = 0; i < lifeNumber; i++) {
            g.fillOval(coordinateX, 0, 20, 20);
            coordinateX += 20;
        }
        g.setColor(Color.BLACK);
        for (int i = lifeNumber; i < 5; i++) {
            g.fillOval(coordinateX, 0, 20, 20);
            coordinateX += 20;
        }
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.BOLD, 20));
        g.drawString("Life: ", 0, 20);
        g.drawString("Points: " + points, coordinateX + 20, 20);
        if (lifeNumber == 0) {
            g.drawString("Game over", gameFrameWidth / 2 - 50, gameFrameHeight / 2 - 100);
        }
    }
}
