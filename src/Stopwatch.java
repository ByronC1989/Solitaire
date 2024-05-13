import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch extends JPanel implements ActionListener {
    private JLabel timeLabel;
    private Timer timer;
    private int elapsedTime;
    private boolean isTimerRunning;


    public Stopwatch() {
        setBackground(new Color(29, 117, 36));
        timeLabel = new JLabel("00:00:00", JLabel.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timeLabel.setForeground(Color.white);
        timeLabel.setOpaque(true);
        timeLabel.setBackground(new Color(29, 117, 36));

        timer = new Timer(1000, this);
        timer.setInitialDelay(0);

        add(timeLabel);
    }

    public void start() {
        if (!isTimerRunning) {
            timer.start();
            isTimerRunning = true;
        }
    }

    public void stop() {
        timer.stop();
        isTimerRunning = false;
    }

    public void reset() {
        elapsedTime = 0;
        updateLabel();
    }
    public boolean isRunning() {
        return isTimerRunning;
    }

    private void updateLabel() {
        int hours = elapsedTime / 3600;
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedTime++;
        updateLabel();
    }
}