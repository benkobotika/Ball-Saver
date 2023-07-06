import java.awt.*;

public class FootballGate implements Same {
    public void draw(Graphics g) {
        g.setColor(Color.white);
        for (int i = 0; i <= gameFrameWidth; i += 5) {
            g.drawLine(i, gameFrameHeight - 45, i, gameFrameHeight);
        }
        for (int i = 0; i <= 20; i += 5) {
            g.drawLine(0, gameFrameHeight - 45 + i, gameFrameWidth, gameFrameHeight - 45 + i);
        }
    }
}
