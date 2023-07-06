import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.stream.IntStream;

public class MenuController implements Runnable {
    private final MenuPanel menuPanel;
    private final MenuFrame menuFrame;
    private Clip clip;

    public MenuController(MenuFrame frame, MenuPanel panel) {
        menuFrame = frame;
        menuPanel = panel;

        menuPanel.getStartButton().addActionListener(e -> {
            clip.stop();
            menuFrame.dispose();
            new GameFrame();
        });
        menuPanel.getExitButton().addActionListener(e -> menuFrame.dispose());
        menuFrame.getStartMenuItem().addActionListener(e -> {
            clip.stop();
            menuFrame.dispose();
            new GameFrame();
        });
        menuFrame.getExitMenuItem().addActionListener(e -> menuFrame.dispose());
        menuFrame.getMain().addActionListener(e -> menuPanel.getCardLayout().show(menuPanel, "P1"));
        menuFrame.getControls().addActionListener(e -> menuPanel.getCardLayout().show(menuPanel, "P2"));
        menuFrame.getDescription().addActionListener(e -> menuPanel.getCardLayout().show(menuPanel, "P3"));

    }

    @Override
    public void run() {
        loadProgressBar();
        menuPanel.getStartButton().setEnabled(true);
        menuPanel.getExitButton().setEnabled(true);
        menuFrame.getStartMenuItem().setEnabled(true);
        menuFrame.getExitMenuItem().setEnabled(true);
        while (menuPanel.isDisplayable()) {
            backgroundSound();
            try {
                Thread.sleep(52000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //java 8
    public void loadProgressBar() {
        IntStream.range(0, 101)
                .forEach(i -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    menuPanel.getProgressBar().setValue(i);
                });
        menuPanel.getProgressBar().setString("Ready to play :)");
    }

    public void backgroundSound() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/video-game-music-loop-27629.wav"));

            clip = AudioSystem.getClip();
            clip.open(audioIn);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
