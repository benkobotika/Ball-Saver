public class TimeThread extends Thread {
    private final Time time;
    private boolean timeStopped = false;

    TimeThread(Time time) {
        this.time = time;
    }

    public void setTimeStopped(boolean timeStopped) {
        this.timeStopped = timeStopped;
    }

    @Override
    public void run() {
        while (!timeStopped) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (time.getSec() + 1 == 60) {
                time.setMin(time.getMin() + 1);
                time.setSec(0);
            } else {
                time.setSec(time.getSec() + 1);
            }
        }
    }
}
