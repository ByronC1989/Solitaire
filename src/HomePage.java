import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener {

    //declare class variables
    private final JFrame frame;
    private final JPanel panel;
    private final JButton button;
    private final JLabel titleLabel;
    private JLabel imageLabel;

    public HomePage(){
        //create frame
        frame = new JFrame();
        //Create panel
        panel = new JPanel();
        //create button
        button = new JButton(" Play ");
        //Create title label
        titleLabel = new JLabel("SOLITAIRE", SwingConstants.CENTER);
        //Create image label - maybe do something with this?
        //ImageIcon imgIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/fallingCards.png")));
        //imageLabel = new JLabel(imgIcon);

        //configure button ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ button
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Configure title label ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ title label
        titleLabel.setFont(new Font("ARIAL", Font.BOLD, 30));//font
        titleLabel.setForeground(Color.WHITE);//text colour
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

         //Configure image label ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ image label
        //imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the image

        //configure panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //BoxLayout - Vertical
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        //panel.add(imageLabel); // add back if doing something with img
        panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
        //panel.setLayout(new GridLayout(10, 20));
        panel.add(button);
        panel.setBackground(new Color(29, 117, 36));

        //configure frame ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ frame
        //frame.setSize(800, 600);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SOLITAIRE");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(" buttons working ");

    }
}
