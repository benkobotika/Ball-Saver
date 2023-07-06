import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;
import java.util.stream.IntStream;

public class Controller implements Runnable {
    private final GamePanel gamePanel;

    public Controller(GamePanel panel) {
        gamePanel = panel;
        gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getX() >= 0 && e.getX() <= gamePanel.getWidth() - gamePanel.getSaver().getSaverWidth()) {
                    gamePanel.getSaver().setLocation(e.getX(), gamePanel.getSaver().getPositionY());
                    gamePanel.getSaver().setPositionX(e.getX());
                }
                gamePanel.repaint();
            }
        });
    }

    @Override
    public void run() {
        Clip clip = null;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/gamemusic-6082.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //jatekido
        TimeThread timeThread = new TimeThread(gamePanel.getTime());
        timeThread.start();

        //foszal
        Clip finalClip = clip;
        Thread gameThread = new Thread(() -> {
            final int[] ballSpeed = {5};
            final Thread[] ballThread = {new BallThread(gamePanel, ballSpeed[0])};
            ballThread[0].start();
            gamePanel.getLife().setLifeNumber(5);
            gamePanel.getLife().setPoints(0);

            IntStream.iterate(0, i -> gamePanel.getLife().getLifeNumber() > 0, i -> i + 1)
                    .forEach(i -> {
                        try {
                            Thread.sleep(1); //thread sleep time before checking that ball thread is alive or not
                            //and saver intersects with the ball or not
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!ballThread[0].isAlive()) { //ball thread is not alive
                            if (ballSpeed[0] > 1) {
                                ballSpeed[0]--;
                            }
                            gamePanel.setBall(new Ball()); //new ball
                            ballThread[0] = new BallThread(gamePanel, ballSpeed[0]); //new ball thread
                            ballThread[0].start();
                        }
                        if (!gamePanel.getBall().isChecked() && gamePanel.getBall().getPositionY() >= gamePanel.getSaver().getPositionY()) {
                            if (!(gamePanel.getBall().getPositionX() >= gamePanel.getSaver().getPositionX() &&
                                    gamePanel.getBall().getPositionX() <= gamePanel.getSaver().getPositionX() + gamePanel.getSaver().getSaverWidth())) { //saver reached the ball
                                gamePanel.getLife().setLifeNumber(gamePanel.getLife().getLifeNumber() - 1);
                                gamePanel.repaint(); //calls paint() method (this components update as soon as possible)
                            } else {
                                gamePanel.getBall().setDirection(0);
                                gamePanel.getLife().setPoints(gamePanel.getLife().getPoints() + 1);
                                sound("beep"); //success sound
                                gamePanel.repaint();
                            }
                            gamePanel.getBall().setChecked(true);///checked the ball that is saved or not
                        }
                    });

            timeThread.setTimeStopped(true); //stop time

            assert finalClip != null;
            finalClip.stop();

            insertToFile();

            if (gamePanel.getLife().getPoints() > gamePanel.getRecord()) {
                sound("record");
            } else {
                sound("over");
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });
        gameThread.start();
    }

    public void sound(String type) {
        Clip end;
        try {
            AudioInputStream audioIn;
            if (type.equals("record")) {
                audioIn = AudioSystem.getAudioInputStream(new File("src/level-win-6416.wav"));
            } else if (type.equals("beep")) {
                audioIn = AudioSystem.getAudioInputStream(new File("src/correct-2-46134.wav"));
            } else {
                audioIn = AudioSystem.getAudioInputStream(new File("src/videogame-death-sound-43894.wav"));
            }

            end = AudioSystem.getClip();
            end.open(audioIn);
            end.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertToFile() {
        File file = new File("src/data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(gamePanel.getLife().getPoints() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
