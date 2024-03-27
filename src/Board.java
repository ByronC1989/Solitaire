import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class Board {
    public static void main (String [] args){
        JFrame frame = new JFrame("Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(new Color(29, 117, 36));
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };
        panel.setPreferredSize(new Dimension(600,500));
        JLabel label = new JLabel("Solitaire");
        panel.add(label);

        ImageIcon icon = new ImageIcon("src/Images/01_back.png");

        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(72, 90, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel imageLabel = new JLabel(scaledIcon);


        panel.setLayout(null);
        panel.add(imageLabel);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
        int x = panel.getWidth() - scaledIcon.getIconWidth() - 20;
        int y = 20;
        imageLabel.setBounds(x, y, 72, 90);
        panel.add(imageLabel);
            }
        });
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}