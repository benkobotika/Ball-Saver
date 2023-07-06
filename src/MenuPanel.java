import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuPanel extends JPanel implements Same {
    private JPanel panel1; //menu panel
    private JPanel panel2; //controls panel
    private JPanel panel3; //description panel
    private JButton startButton; //start button
    private JButton exitButton; //exit button
    private JProgressBar progressBar;
    private final CardLayout cardLayout;

    public MenuPanel() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        drawMenuPanel();
        drawProgressBar();
        drawMenuLabel();

        this.add(panel1, "P1");
        this.add(panel2, "P2");
        this.add(panel3, "P3");
    }

    private void drawMenuPanel() {
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setPreferredSize(new Dimension(300, 200));
        panel1.setBackground(Color.blue);
        panel1.setBorder(new LineBorder(Color.cyan, 5));

        startButton = new JButton("Start");
        startButton.setBackground(Color.green);
        startButton.setFont(consolas);
        startButton.setEnabled(false);

        exitButton = new JButton("Exit");
        exitButton.setFont(consolas);
        exitButton.setBackground(new Color(255, 0, 54));
        exitButton.setEnabled(false);

        panel1.add(startButton);
        panel1.add(exitButton);

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1));
        panel2.setBorder(new LineBorder(Color.cyan, 5));
        panel2.add(new JLabel("Game controls"));

        ImageIcon mouse = new ImageIcon("src/mouse.jpg");
        JLabel mouseLabel = new JLabel();
        mouseLabel.setIcon(mouse);

        panel2.add(mouseLabel);
        panel2.add(new JLabel("Mouse: moving mouse on the window"));

        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBorder(new LineBorder(Color.cyan, 5));

        JTextArea descriptionTextArea = new JTextArea("The main purpose of the game \"BallSaver\" is to\n test the player's reaction speed. \nIn this game, the player starts with 5 lives,\nwhich means that every time they fail to \ncatch a ball, their lives will decrease by 1.\nIf the number of lives becomes 0,\nthe game will end.");
        descriptionTextArea.setBackground(Color.blue);
        descriptionTextArea.setForeground(Color.white);
        descriptionTextArea.setFont(new Font("Times New Roman", Font.BOLD, 13));

        panel3.add(descriptionTextArea);
    }

    private void drawProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setForeground(Color.green);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        panel1.add(progressBar);
    }

    private void drawMenuLabel() {
        JLabel label = new JLabel("BallSaver");
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
        label.setForeground(Color.lightGray);
        panel1.add(label);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}