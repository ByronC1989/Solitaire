import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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

class Board extends JFrame implements ActionListener, MouseListener {

    int width = 72;
    int height = 90;

    // back of card
    private final ImageIcon cardBack = new ImageIcon("src/Images/01_back.png");
    // ImageIcon cardBack = new ImageIcon("Solitaire/src/Images/01_back.png");
    private final Image backOfCard = cardBack.getImage()
            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    private final ImageIcon backScaled = new ImageIcon(backOfCard); //stockpile imgIcon
    private final ImageIcon aceSpade = new ImageIcon("src/Images/ace_of_spades.png");
    private final Image aceSpadeUpdate = aceSpade.getImage()
            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    private final ImageIcon aceSpadeScaled = new ImageIcon(aceSpadeUpdate); //stockpile imgIcon
    private final ImageIcon aceHeart = new ImageIcon("src/Images/ace_of_hearts.png");
    private final Image aceHeartUpdate = aceHeart.getImage()
            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    private final ImageIcon aceHeartScaled = new ImageIcon(aceHeartUpdate); //stockpile imgIcon
    private final ImageIcon aceDiamonds = new ImageIcon("src/Images/ace_of_diamonds.png");
    private final Image aceDiamondsUpdate = aceDiamonds.getImage()
            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    private final ImageIcon aceDiamondsScaled = new ImageIcon(aceDiamondsUpdate); //stockpile imgIcon

    private final ImageIcon aceClubs = new ImageIcon("src/Images/ace_of_clubs.png");
    private final Image aceClubsUpdate = aceClubs.getImage()
            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    private final ImageIcon aceClubsScaled = new ImageIcon(aceClubsUpdate); //stockpile imgIcon

    private ImageIcon talonIcon;

    // Labels
    JLabel stockpileLabel = new JLabel(backScaled); //stockpile label
    private JLabel talonLabel = new JLabel();
    private JLabel fDiamond = new JLabel(aceDiamondsScaled); // diamond foundation label
    private JLabel fHeart = new JLabel(aceHeartScaled);  // heart foundation label
    private JLabel fClub = new JLabel(aceClubsScaled); // club foundation label
    private JLabel fSpade = new JLabel(aceSpadeScaled); // spade foundation label
    JLabel text = new JLabel();

    private final JLabel[] tableauLabels = new JLabel[7];
    private JMenuBar menuBar;
    private JMenuItem gameItem;
    private  JMenuItem exitItem;
    private JMenu gameMenu;
    JLabel Timage = this.talonLabel;

    private final JPanel tableauPanel;
    private final JPanel foundationPanel;
    private final JPanel deckPanel;
    private final JPanel tracker;

    private GameEngine game;


    public Board(GameEngine game) {
        this.game = game;

        // create Frame
        this.setTitle("Solitaire");         // title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600,500); // Frame Size -- maybe expand height
        this.setLayout(null);
        this.getContentPane()
                .setBackground(new Color(29, 117, 36)); // background colour
        //        board.pack();

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

        this.tableauPanel = new JPanel(); // create tableau Panel
        this.foundationPanel = new JPanel(); // create foundation panel
        this.deckPanel = new JPanel(); // Deck Panel Probably define in class
        this.tracker = new JPanel(); // Card Tracker Probably define in class

        //this.setLocationRelativeTo(null);
    }

    public void setup() {
        tracker.setBounds(420, 116, 160, 25);
        text.setText("Cards Remaining: ");
        text.setForeground(Color.white);
        tracker.setBackground(new Color(29, 117, 36));
        tracker.add(text);
        this.add(tracker);

        deckPanel.setBackground(new Color(29, 117, 36));
        deckPanel.setBounds(420,20,160, 94);
        deckPanel.setLayout(null);

        stockpileLabel.setBounds(80, 2, width, height); // draw stockpile
        talonLabel.setBounds(5, 2, width, height); // draw talon pile

        talonLabel.addMouseListener(this); // add mouse events

        deckPanel.add(stockpileLabel);
        deckPanel.add(talonLabel);
        this.add(deckPanel); // end of decks panel

        foundationPanel.setBackground(new Color(29, 117, 36));
        foundationPanel.setBounds(10,20,320, 94);
        foundationPanel.setLayout(null);

        fSpade.setBounds(5, 2, width, height); // draw stockpile
        fHeart.setBounds(82, 2, width, height); // draw stockpile
        fClub.setBounds(159, 2, width, height); // draw stockpile
        fDiamond.setBounds(236, 2, width, height); // draw stockpile

        fHeart.setIcon(createGreyedOutImage(fHeart.getIcon())); // grey out
        fDiamond.setIcon(createGreyedOutImage(fDiamond.getIcon()));
        fClub.setIcon(createGreyedOutImage(fClub.getIcon()));
        fSpade.setIcon(createGreyedOutImage(fSpade.getIcon()));

        fHeart.addMouseListener(this); // add mouse events
        fDiamond.addMouseListener(this); // add mouse events
        fClub.addMouseListener(this); // add mouse events
        fSpade.addMouseListener(this); // add mouse events

        foundationPanel.add(fDiamond);
        foundationPanel.add(fHeart);
        foundationPanel.add(fClub);
        foundationPanel.add(fSpade);
        this.add(foundationPanel);

        int x = 5;
        tableauPanel.setBackground(new Color(29, 117, 36));
        tableauPanel.setBounds(10,150,560, 94);
        tableauPanel.setLayout(null);
        for(int i = 0; i < 7; i++) {
            tableauLabels[i] = new JLabel();
            tableauLabels[i].setBorder(new RoundBorder(10));
            tableauLabels[i].setBounds(x,2, width, height);
            tableauLabels[i].addMouseListener(this);
            tableauPanel.add(tableauLabels[i]);
            x+= 80;
        }
        this.add(tableauPanel);

        this.setVisible(true);
    }

    public void removePanels () {
        this.remove(tableauPanel);
        this.remove(foundationPanel);
        this.remove(deckPanel);
        this.remove(tracker);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // control menu
        if(e.getSource()==gameItem) {
            // needs some kind of update
           System.out.println("new game");
           removePanels();
           setup();
        } else {
            System.exit(0);
        }

    }

    //stateChange class to update talon/stockpile on click/////////////////////////////////////////
    public void drawDeck(){
        stockpileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card talonCard = null;
                if (game.getDeck().deckSize() > 0) {
                    game.getDeck().moveCardsToTalon(game.getTalon(), 1);
                    talonCard = game.getTalon().topCard();
                    talonIcon = talonCard.displayCard();
                    Timage.setIcon(talonIcon);
                    text.setText("Cards Remaining: " + game.getDeck().deckSize());
                    if (game.getDeck().deckSize() == 0){
                        stockpileLabel.setIcon(createGreyedOutImage(
                                stockpileLabel.getIcon()));
                    }
                } else {
                    game.getTalon().moveCardsToDeck(game.getDeck(), game.getTalon().deckSize());
                    stockpileLabel.setIcon(backScaled);
                    Timage.setIcon(null);
                }
            }
        });
    }

    // Utility method to create a semi-transparent version of an existing icon
    private ImageIcon createGreyedOutImage(Icon originalIcon) {
        int width = originalIcon.getIconWidth();
        int height = originalIcon.getIconHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        originalIcon.paintIcon(null, g2, 0, 0);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f)); // Very transparent
        g2.setColor(new Color(128, 128, 128, 200)); // Light grey with very high transparency
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        return new ImageIcon(img);
    }

    Component srcPile; // Store the source pile -- move to top
    Component destPile; // Store the destination pile -- move to top

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        srcPile = e.getComponent(); // gets the component of what was clicked on.

        if(srcPile==talonLabel){
            System.out.println("Pressed on: Talon Pile");
        } else if (srcPile==fHeart) {
            System.out.println("Pressed on: Hearts Foundation");
        } else if (srcPile==fDiamond) {
            System.out.println("Pressed on: Diamonds Foundation");
        } else if (srcPile==fClub) {
            System.out.println("Pressed on: Clubs Foundation");
        } else if (srcPile==fSpade) {
            System.out.println("Pressed on: Spades Foundation");
        } else if (srcPile==tableauLabels[0]) {
            System.out.println("Pressed on: Tableau 01");
        }else {
            System.out.println("Something went wrong: Pressed");
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(destPile==talonLabel){
            System.out.println("Released on: Talon Pile");
        } else if (destPile==fHeart) {
            System.out.println("Released on: Hearts Foundation");
        } else if (destPile==fDiamond) {
            System.out.println("Released on: Diamonds Foundation");
        } else if (destPile==fClub) {
            System.out.println("Released on: Clubs Foundation");
        } else if (destPile==fSpade) {
            System.out.println("Released on: Spades Foundation");
        } else {
            System.out.println("Something went wrong: Released");
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        destPile = e.getComponent(); // updates component with what label the mouse entered last

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}