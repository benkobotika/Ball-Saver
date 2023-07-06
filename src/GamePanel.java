import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Same {
    private Ball ball; //ball component
    private final Saver saver; //saver component
    private final Life life; //life component
    private final Time time; //time component
    private final FootballGate footballGate; //football gate component
    private final ArrayList<Integer> data;
    private int record;

    public GamePanel(Ball ball, Saver saver, Life life, Time time, FootballGate footballGate) {
        data = new ArrayList<>();
        readFromFile();
        this.setSize(new Dimension(gameFrameWidth, gameFrameHeight));
        this.setBackground(new Color(5, 138, 13));

        this.ball = ball;
        this.saver = saver;
        this.life = life;
        this.time = time;
        this.footballGate = footballGate;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        g.drawString("BallSaver", gameFrameWidth / 2 - 75, gameFrameHeight / 2);
        ball.draw(g);
        saver.draw(g);
        life.draw(g);
        time.draw(g);
        footballGate.draw(g);
        drawData(g);
        drawPixelArt(g);
    }

    //java 8
    public void drawData(Graphics g) {
        double avg = data.stream().mapToDouble(i -> i).average().orElse(0); //stream
        g.drawString("Avg:" + String.format("%.2f", avg), 0, 40);
        record = data.stream().mapToInt(i -> i).max().orElse(0); //stream
        g.drawString("Best:" + record, 0, 60);
    }

    public void drawPixelArt(Graphics g) {
        Random random = new Random();
        int startX = gameFrameWidth / 2 - 10, startY = gameFrameHeight / 2 - 15, rStart = 80;
        int x, y;

        for (double i = 0; i <= 2 * 3.1415; i += 0.1) {
            drawGetX(g, random, startX, startY, rStart, i);
        }
        startY = startY - rStart;
        int r = 50;
        for (double i = 0.4; i <= 3.1415 - 0.4; i += 0.1) {
            x = drawGetX(g, random, startX, startY, r, i);
            y = (int) (startY + 2 * rStart - r * Math.sin(i));
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.fillRect(x, y, 10, 10);
        }

    }

    private int drawGetX(Graphics g, Random random, int startX, int startY, int r, double i) {
        int x;
        int y;
        x = (int) (startX + r * Math.cos(i));
        y = (int) (startY + r * Math.sin(i));
        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        g.fillRect(x, y, 10, 10);
        return x;
    }

    public void readFromFile() {
        File file = new File("src/data.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data.add(Integer.parseInt(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Saver getSaver() {
        return saver;
    }

    public Life getLife() {
        return life;
    }

    public Time getTime() {
        return time;
    }

    public int getRecord() {
        return record;
    }
}
