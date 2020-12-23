import javax.swing.*;
import java.awt.*;
import java.util.Random;

@SuppressWarnings({"BusyWait", "rawtypes"})
public class Mover extends SwingWorker{

    private final int interval, duration;
    private int timeLeft;
    boolean active = true;

    //moise moving related variables
    public static int MAX_X;
    public static int MAX_Y;

    public Mover(int interval,int duration) {
        super();
        this.interval = interval;
        this.duration = duration;
        timeLeft = interval;

        // determine screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        MAX_X = (int) screenSize.getWidth();
        MAX_Y = (int) screenSize.getHeight();
    }

    @Override
    protected Object doInBackground() throws AWTException {
        while (active) {
            while (active && timeLeft >= 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeLeft--;
                setProgress(timeLeft);
                if(timeLeft <= 0)
                    performActivity();
            }
        }
        return null;
    }

    private void performActivity() throws AWTException {
        Robot robot = new Robot();
        Random random = new Random();
        long t = System.currentTimeMillis();
        long end = t + (duration * 1000L);
        while(System.currentTimeMillis() < end) {
            robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        timeLeft = interval;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
