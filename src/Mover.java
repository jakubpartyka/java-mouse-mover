import javax.swing.*;

public class Mover extends Thread{

    private JLabel statusLabel;

    public Mover(JLabel statusLabel){
        super();
        this.statusLabel = statusLabel;
    }

    @Override
    public void run() {
        super.run();
    }
}
