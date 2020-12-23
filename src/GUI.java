import javax.swing.*;

public class GUI implements Runnable {
    private JFrame frame;
    private JTextField activityIntervalField;
    private JButton STARTButton;
    private JButton EXITButton;
    private JLabel statusLabel;
    private JPanel panel;

    @Override
    public void run() {
        frame = new JFrame("Java Mouse Mover");
        frame.add(panel);
        frame.setSize(400,400);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);

    }
}
