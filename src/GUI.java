import javax.swing.*;

public class GUI implements Runnable {
    private JFrame frame;
    private JTextField activityIntervalField;
    private JButton STARTButton;
    private JButton EXITButton;
    private JLabel statusLabel;
    private JPanel panel;
    private JTextField activityDurationField;

    // variables declaration
    int interval;
    int duration;

    @Override
    public void run() {
        // initialize main frame
        frame = new JFrame("Java Mouse Mover");
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);

        // add action listeners
        STARTButton.addActionListener(e -> {
            // verify input
            if(!verifyInput()){
                JOptionPane.showMessageDialog(null,"Incorrect parameters specified!","Input verification failed",JOptionPane.WARNING_MESSAGE);
                return;
            }

            Mover mover = new Mover(statusLabel,interval,duration);
            mover.addPropertyChangeListener(evt -> {
                int timeLeft = mover.getTimeLeft();
                if(timeLeft >= 0)
                    statusLabel.setText("Next activity in " + mover.getTimeLeft() + " seconds");
                else
                    statusLabel.setText("Activity in progress");
            });
            Thread thread = new Thread(mover);
            thread.start();


        });

        EXITButton.addActionListener(e -> System.exit(0));
    }

    private boolean verifyInput() {
        try {
            interval = Integer.parseInt(activityIntervalField.getText());
            duration = Integer.parseInt(activityDurationField.getText());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
