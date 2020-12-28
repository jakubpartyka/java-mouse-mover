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
    protected Object doInBackground() {
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

    private void performActivity() {
        Random random = new Random();
        long t = System.currentTimeMillis();
        long end = t + (duration * 1000L);
        while(System.currentTimeMillis() < end) {
            //robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
            mouseGlide(random.nextInt(MAX_X), random.nextInt(MAX_Y),random.nextInt(MAX_X), random.nextInt(MAX_Y));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        timeLeft = interval;
    }

    private void mouseGlide(int x1, int y1, int x2, int y2) {
        try {
            Robot r = new Robot();
            double dx = (x2 - x1) / ((double) 10000);
            double dy = (y2 - y1) / ((double) 10000);
            double dt = 10 / ((double) 10000);
            for (int step = 1; step <= 10000; step++) {
                Thread.sleep((int) dt);
                r.mouseMove((int) (x1 + dx * step), (int) (y1 + dy * step));
            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
