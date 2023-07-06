import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuFrame extends JFrame {
    private JMenuItem startMenuItem; //start submenu
    private JMenuItem exitMenuItem; //exit submenu
    private JMenuItem main; //main submenu
    private JMenuItem controls; //controls submenu
    private JMenuItem description; //description submenu

    public MenuFrame() {
        this.setTitle("BallSaver");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300, 200));
        ImageIcon ball = new ImageIcon("src/ball.jpg");
        this.setIconImage(ball.getImage());
        this.setResizable(false);

        setMenuBar();

        MenuPanel menuPanel = new MenuPanel();
        this.add(menuPanel); //involves everything that appears on the window
        this.setLocationRelativeTo(null); //window appears in center of the screen
        this.setVisible(true);

        MenuController menuController = new MenuController(this, menuPanel);
        menuController.run();
    }

    private void setMenuBar() {
        //menu
        JMenuBar menuBar = new JMenuBar();
        //file MENU
        JMenu file = new JMenu("File");
        startMenuItem = new JMenuItem("Start");
        exitMenuItem = new JMenuItem("Exit");
        startMenuItem.setEnabled(false);
        exitMenuItem.setEnabled(false);

        //Help menu
        JMenu help = new JMenu("Help");
        main = new JMenuItem("Menu");
        controls = new JMenuItem("Controls");
        description = new JMenuItem("Description");

        file.add(startMenuItem);
        file.add(exitMenuItem);
        help.add(main);
        help.add(controls);
        help.add(description);

        menuBar.add(file);
        menuBar.add(help);
        this.setJMenuBar(menuBar);
        file.setMnemonic(KeyEvent.VK_F); //alt f
        startMenuItem.setMnemonic(KeyEvent.VK_S); //s
        exitMenuItem.setMnemonic(KeyEvent.VK_E); //e
    }

    public JMenuItem getStartMenuItem() {
        return startMenuItem;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JMenuItem getMain() {
        return main;
    }

    public JMenuItem getControls() {
        return controls;
    }

    public JMenuItem getDescription() {
        return description;
    }
}
