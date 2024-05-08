import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.AbstractBorder;

class RoundBorder extends AbstractBorder {
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
    private ImageIcon talonIcon;
    private JLabel talonImage = new JLabel();
    private JLabel[] foundationLabels = new JLabel[4];
    private Foundation foundation;
    public Board() {
        foundation = new Foundation();
    }
    private void updateFoundationGUI(Suit suit) {
        int pileIndex = suit.ordinal();
        java.util.List<Card> pile = foundation.getPile(pileIndex);
        if (!pile.isEmpty()) {
            Card topCard = pile.get(pile.size() - 1);
            ImageIcon cardIcon = topCard.displayCard();
            foundationLabels[pileIndex].setIcon(cardIcon);
        } else {
            foundationLabels[pileIndex].setIcon(null);
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
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
    public void enableDragAndDrop(JLabel talonImage, Talon talon, JLabel[] foundationLabels, JPanel panel) {
        talonImage.addMouseListener(new MouseAdapter() {
            private int offsetX, offsetY;
            private int droppedPileIndex = -1;

            @Override
            public void mousePressed(MouseEvent e) {
                offsetX = e.getX();
                offsetY = e.getY();
                System.out.println("Mouse clicked");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getXOnScreen() - offsetX - panel.getLocationOnScreen().x;
                int y = e.getYOnScreen() - offsetY - panel.getLocationOnScreen().y;
                System.out.println("Mouse Released");

                for (int i = 0; i < foundationLabels.length; i++) {
                    if (foundationLabels[i].getBounds().contains(x, y)) {
                        Card draggedCard = talon.topCard();
                        if (draggedCard != null) {
                            if (foundation.canPlace(draggedCard, Suit.values()[i])) {
                                foundation.place(draggedCard, Suit.values()[i]);
                                updateFoundationGUI(Suit.values()[i]);
                                talon.removeTopCard();
                                droppedPileIndex = i;
                                if (droppedPileIndex != -1) {
                                    JOptionPane.showMessageDialog(panel, "Card dropped onto " + Suit.values()[droppedPileIndex] + " foundation pile.", "Notification", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                System.out.println("Invalid move: Cannot place card on this foundation pile.");
                            }
                        }
                        break;
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - offsetX;
                int y = e.getYOnScreen() - offsetY;
                talonImage.setLocation(x, y);
            }
        });
    }
    /////////////////////////// end stateChange ///////////////////////////////////////////////////

    public static void main (String [] args){

        Board board = new Board();
        Deck deck = new Deck();
        Talon talon = new Talon();
        Tableau tableau = new Tableau();
        tableau.initialize(deck);

        JFrame frame = new JFrame("Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
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

        for (int i = 0; i < 4; i++) {
            board.foundationLabels[i] = new JLabel();
            panel.add(board.foundationLabels[i]);
        }

        //stockpile imgIcon
        ImageIcon scaledIcon1 = new ImageIcon(img1);
        //stockpile label
        JLabel imageLabel = new JLabel(scaledIcon1);
        //border labels
        JLabel pileLabel2 = new JLabel();
        JLabel FoundationPile1 = new JLabel();
        JLabel FoundationPile2 = new JLabel();
        JLabel FoundationPile3 = new JLabel();
        JLabel FoundationPile4 = new JLabel();
        JLabel pileLabel7 = new JLabel();
        JLabel pileLabel8 = new JLabel();
        JLabel pileLabel9 = new JLabel();
        JLabel pileLabel10 = new JLabel();
        JLabel pileLabel11= new JLabel();
        JLabel pileLabel12= new JLabel();
        JLabel pileLabel13= new JLabel();

        //call statechange to use mouseListener
        board.stateChange(imageLabel, deck, talon);

        int roundRadius = 10;
        FoundationPile1.setBorder(new RoundBorder(roundRadius));
        FoundationPile2.setBorder(new RoundBorder(roundRadius));
        FoundationPile3.setBorder(new RoundBorder(roundRadius));
        FoundationPile4.setBorder(new RoundBorder(roundRadius));
        pileLabel2.setBorder(new RoundBorder(roundRadius));
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
        panel.add(FoundationPile1);
        panel.add(FoundationPile2);
        panel.add(FoundationPile3);
        panel.add(FoundationPile4);
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

                //stock pile
                int x1 = 500;
                int y1 = 20;
                imageLabel.setBounds(x1, y1, width, height);

                int x2 = 500;
                int y2 = 20;
                pileLabel2.setBounds(x2, y2, width, height);

                //foundation piles
                int x3 = 20;
                int y3 = 20;
                FoundationPile1.setBounds(x3, y3, width, height);

                int x4 = 100;
                int y4 = 20;
                FoundationPile2.setBounds(x4, y4, width, height);

                int x5 = 180;
                int y5 = 20;
                FoundationPile3.setBounds(x5, y5, width, height);

                int x6 = 260;
                int y6 = 20;
                FoundationPile4.setBounds(x6, y6, width, height);



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

        board.enableDragAndDrop(board.talonImage, talon, board.foundationLabels, panel);
    }
}