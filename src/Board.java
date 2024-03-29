import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.AbstractBorder;

class RoundBorder extends AbstractBorder { // make the card slots rounded
    private int radius;

    public RoundBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) { // design of card slots
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.white);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }

}//end class RoundBorder

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

        ImageIcon backOfCard = new ImageIcon("Solitaire/src/Images/01_back.png");

        int width = 72;
        int height = 90;
        Image img1 = backOfCard.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        //added testCard object to test displayCard and flipCard function//
        Card testCard = new Card(Rank.KING, Suit.HEARTS);
        testCard.flipCard(); //test flipCard
        ImageIcon testIcon = testCard.displayCard();
        ///////////////////////////////////////////////////////////////////

        ImageIcon scaledIcon1 = new ImageIcon(img1);

        JLabel imageLabel = new JLabel(scaledIcon1);
        JLabel pileLabel2 = new JLabel();
        JLabel pileLabel3 = new JLabel();
        JLabel pileLabel4 = new JLabel();
        JLabel pileLabel5 = new JLabel();
        JLabel pileLabel6 = new JLabel();
        JLabel pileLabel7 = new JLabel();
        JLabel pileLabel8 = new JLabel();
        JLabel pileLabel9 = new JLabel();
        JLabel pileLabel10 = new JLabel();
        JLabel pileLabel11= new JLabel();
        JLabel pileLabel12= new JLabel();
        JLabel pileLabel13= new JLabel();
        //create test card label
        JLabel imageLabelTest = new JLabel(testIcon);


        int roundRadius = 10;
        pileLabel3.setBorder(new RoundBorder(roundRadius));
        pileLabel2.setBorder(new RoundBorder(roundRadius));
        pileLabel4.setBorder(new RoundBorder(roundRadius));
        pileLabel5.setBorder(new RoundBorder(roundRadius));
        pileLabel6.setBorder(new RoundBorder(roundRadius));
        pileLabel7.setBorder(new RoundBorder(roundRadius));
        pileLabel8.setBorder(new RoundBorder(roundRadius));
        pileLabel9.setBorder(new RoundBorder(roundRadius));
        pileLabel10.setBorder(new RoundBorder(roundRadius));
        pileLabel11.setBorder(new RoundBorder(roundRadius));
        pileLabel12.setBorder(new RoundBorder(roundRadius));
        pileLabel13.setBorder(new RoundBorder(roundRadius));

        panel.setLayout(null);
        //display test card on panel
        panel.add(imageLabelTest);
        panel.add(imageLabel);
        panel.add(pileLabel2);
        panel.add(pileLabel3);
        panel.add(pileLabel4);
        panel.add(pileLabel5);
        panel.add(pileLabel6);
        panel.add(pileLabel7);
        panel.add(pileLabel8);
        panel.add(pileLabel9);
        panel.add(pileLabel10);
        panel.add(pileLabel11);
        panel.add(pileLabel12);
        panel.add(pileLabel13);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //display labelTest in Talon border
                int x0 = 500;
                int y0 = 20;
                imageLabelTest.setBounds(x0, y0, width, height);

                int x1 = 420;
                int y1 = 20;
                imageLabel.setBounds(x1, y1, width, height);

                int x2 = 500;
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

                int x7 = 20;
                int y7 = 150;
                pileLabel7.setBounds(x7, y7, width, height);

                int x8 = 100;
                int y8 = 150;
                pileLabel8.setBounds(x8, y8, width, height);

                int x9 = 180;
                int y9 = 150;
                pileLabel9.setBounds(x9, y9, width, height);

                int x10 = 260;
                int y10 = 150;
                pileLabel10.setBounds(x10, y10, width, height);

                int x11 = 340;
                int y11 = 150;
                pileLabel11.setBounds(x11, y11, width, height);

                int x12 = 420;
                int y12 = 150;
                pileLabel12.setBounds(x12, y12, width, height);

                int x13 = 500;
                int y13 = 150;
                pileLabel13.setBounds(x13, y13, width, height);
            }
        });
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}