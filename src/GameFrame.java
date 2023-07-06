import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements Same {

    public GameFrame() {
        this.setName("BallSaver");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(gameFrameWidth, gameFrameHeight));
        this.setLocationRelativeTo(null);
        ImageIcon ballIcon = new ImageIcon("src/ball.jpg");
        this.setIconImage(ballIcon.getImage());
        this.setResizable(false);

        Ball ball = new Ball();
        Saver saver = new Saver();
        Life life = new Life();
        Time time = new Time();
        FootballGate footballGate = new FootballGate();
        GamePanel gamePanel = new GamePanel(ball, saver, life, time, footballGate);
        this.add(gamePanel);
        this.setVisible(true);

        Controller controller = new Controller(gamePanel);
        controller.run();
    }
}
