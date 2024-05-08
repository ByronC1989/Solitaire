import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
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

class Board extends JFrame implements ActionListener {
    private ImageIcon talonIcon;
    private JLabel talonImage = new JLabel();
    private JLabel[] foundationLabels = new JLabel[4];
    private Foundation foundation;
    private Deck deck;
    private Talon talon;
    private Tableau tableau;

    private JMenuBar menuBar;
    private JMenuItem gameItem;
    private  JMenuItem exitItem;
    private JMenu gameMenu;
    public Board() {

        this.setTitle("Solitaire");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // Creating a menu
        this.menuBar = new JMenuBar();

        this.gameItem = new JMenuItem("New Game");
        this.exitItem = new JMenuItem("Exit");

        this.gameMenu = new JMenu("Game");
        gameItem.addActionListener(this);
        exitItem.addActionListener(this);

        gameMenu.add(gameItem);
        gameMenu.add(exitItem);

        menuBar.add(gameMenu);
        this.setJMenuBar(menuBar);
        // end of menu

        this.foundation = new Foundation();
        this.deck = new Deck();
        this.talon = new Talon();
        this.tableau = new Tableau();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==gameItem) {
            // needs some kind of update
           Board board = new Board();
           board.tableau.initialize(board.deck);
           System.out.println(board.deck.deckSize());
        } else {
            System.exit(0);
        }

    }

    //stateChange class to update talon/stockpile on click/////////////////////////////////////////
    public void stateChange(JLabel imageLabel, Deck deck, Talon talon){

        JLabel Timage = this.talonImage;
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card talonCard = null;
                if (deck.deckSize() > 0) {
                    deck.moveCardsToTalon(talon, 1);
                    System.out.println(" PRESSED THE LABEL ");
                    talonCard = talon.topCard();
                    talonIcon = talonCard.displayCard();
                    Timage.setIcon(talonIcon);
                    System.out.println("Cards Remaining: " + deck.deckSize());
                    System.out.println(" Bottom of mouse click ");
                } else {
                    talon.moveCardsToDeck(deck, talon.deckSize());
                    Timage.setIcon(null);
                }
                if (talonCard != null) {
                    if (talonCard.getRank() == Rank.ACE.getRank()) {
                        // If the card is an Ace, automatically place it on the foundation
                        for (Suit suit : Suit.values()) {
                            if (foundation.canPlace(talonCard, suit)) {
                                foundation.place(talonCard, suit);
                                break;
                            }
                        }
                    } else {
                        // For non-Ace cards, check if they can be placed on the foundation
                        for (Suit suit : Suit.values()) {
                            if (foundation.canPlace(talonCard, suit)) {
                                foundation.place(talonCard, suit);
                                // If placed successfully, break the loop
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    /////////////////////////// end stateChange ///////////////////////////////////////////////////

    public static void main (String [] args){

        Board board = new Board();
        board.tableau.initialize(board.deck);

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
        // ImageIcon backOfCard = new ImageIcon("Solitaire/src/Images/01_back.png");

        int width = 72;
        int height = 90;
        Image img1 = backOfCard.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        //stockpile imgIcon
        ImageIcon scaledIcon1 = new ImageIcon(img1);
        //stockpile label
        JLabel imageLabel = new JLabel(scaledIcon1);
        //border labels
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

        //call statechange to use mouseListener
        board.stateChange(imageLabel, board.deck, board.talon);

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
        panel.add(board.talonImage);
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
                int x0 = 420;
                int y0 = 20;
                board.talonImage.setBounds(x0, y0, width, height);

                int x1 = 500;
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
        board.getContentPane().add(panel);

        board.pack();
        board.setLocationRelativeTo(null);
        board.setVisible(true);
    }

}