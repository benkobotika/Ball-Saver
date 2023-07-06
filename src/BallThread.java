public class BallThread extends Thread implements Same {
    private final GamePanel gamePanel;
    private final int speed;

    public BallThread(GamePanel gamePanel, int speed) {
        this.gamePanel = gamePanel;
        this.speed = speed;
    }

    @Override
    public void run() {
        while ((gamePanel.getBall().getHeight() < gameFrameHeight && gamePanel.getBall().getDirection() == 1 && !gamePanel.getBall().isChecked()) ||
                (gamePanel.getBall().getPositionY() > -gamePanel.getBall().getHeight() && gamePanel.getBall().getDirection() == 0)) {
            if (gamePanel.getBall().getDirection() == 1) {
                gamePanel.getBall().setLocation(gamePanel.getBall().getPositionX(), gamePanel.getBall().getPositionY() + 2);
                gamePanel.getBall().setPositionY(gamePanel.getBall().getPositionY() + 2);
            } else {
                gamePanel.getBall().setLocation(gamePanel.getBall().getPositionX(), gamePanel.getBall().getPositionY() - 2);
                gamePanel.getBall().setPositionY(gamePanel.getBall().getPositionY() - 2);
            }
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gamePanel.repaint();
        }
    }
}
