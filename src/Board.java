import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.AbstractBorder;

class RoundBorder extends AbstractBorder {
    private int radius;

    public RoundBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.white);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }
}

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

        ImageIcon backOfCard = new ImageIcon("src/Images/01_back.png");

        int width = 72;
        int height = 90;
        Image img1 = backOfCard.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon1 = new ImageIcon(img1);

        JLabel imageLabel = new JLabel(scaledIcon1);
        JLabel pileLabel2 = new JLabel();
        JLabel pileLabel3 = new JLabel();
        JLabel pileLabel4 = new JLabel();
        JLabel pileLabel5 = new JLabel();
        JLabel pileLabel6 = new JLabel();


        int roundRadius = 10;
        pileLabel3.setBorder(new RoundBorder(roundRadius));
        pileLabel2.setBorder(new RoundBorder(roundRadius));
        pileLabel4.setBorder(new RoundBorder(roundRadius));
        pileLabel5.setBorder(new RoundBorder(roundRadius));
        pileLabel6.setBorder(new RoundBorder(roundRadius));

        panel.setLayout(null);
        panel.add(imageLabel);
        panel.add(pileLabel2);
        panel.add(pileLabel3);
        panel.add(pileLabel4);
        panel.add(pileLabel5);
        panel.add(pileLabel6);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int x1 = panel.getWidth() - width - 20;
                int y1 = 20;
                imageLabel.setBounds(x1, y1, width, height);

                int x2 = x1 - 100;
                int y2 = 20;
                pileLabel2.setBounds(x2, y2, width, height);

                int x3 = 20;
                int y3 = 20;
                pileLabel3.setBounds(x3, y3, width, height);

                int x4 = 100;
                int y4 = 20;
                pileLabel4.setBounds(x4, y4, width, height);

                int x5 = 180;
                int y5 = 20;
                pileLabel5.setBounds(x5, y5, width, height);

                int x6 = 260;
                int y6 = 20;
                pileLabel6.setBounds(x6, y6, width, height);
            }
        });
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}