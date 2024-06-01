import javax.swing.*;
import java.awt.*;
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

}// end class RoundBorder

class Board extends JFrame implements ActionListener, MouseListener {

    int width = 72;
    int height = 90;
    private ImageIcon talonIcon;
    // Labels
    JLabel stockpileLabel; // stockpile label
    private JLabel talonLabel = new JLabel();
    JLabel text = new JLabel();

    private final JLabel[] tableauLabels = new JLabel[7];
    private final JLabel[] foundationLabels = new JLabel[4];
    private JMenuBar menuBar;
    private JMenuItem gameItem;
    private JMenuItem exitItem;
    private JMenu gameMenu;
    JLabel Timage = this.talonLabel;

    private final JPanel tableauPanel;
    private final JPanel foundationPanel;
    private final JPanel deckPanel;
    private final JPanel tracker;

    private GameEngine game;
    public Stopwatch stopwatch;

    public Board(GameEngine game) {
        this.game = game;

        this.stopwatch = new Stopwatch();
        this.stopwatch.setBounds(420, 250, 160, 25);
        this.add(stopwatch);

        // create Frame
        this.setTitle("Solitaire"); // title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 500); // Frame Size -- maybe expand height
        this.setLayout(null);
        this.getContentPane()
                .setBackground(new Color(29, 117, 36)); // background colour
        // board.pack();

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

        // this.setLocationRelativeTo(null);
    }

    public void setup() {
        initializeTracker();
        initializeDeckPanel();
        initializeFoundationPanel();
        initializeTableauPanel();
        this.setVisible(true);
    }

    private void initializeTracker() {
        tracker.setBounds(420, 116, 160, 25);
        text.setText("Cards Remaining: " + game.getDeck().deckSize());
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        text.setForeground(Color.white);
        tracker.setBackground(new Color(29, 117, 36));
        tracker.add(text);
        this.add(tracker);
    }

    private void initializeDeckPanel() {
        deckPanel.setBackground(new Color(29, 117, 36));
        deckPanel.setBounds(420, 20, 160, 94);
        deckPanel.setLayout(null);

        stockpileLabel = new JLabel(scaledImage(new ImageIcon("src/Images/01_back.png")));
        stockpileLabel.setBounds(80, 2, width, height);
        talonLabel.setBounds(5, 2, width, height);
        talonLabel.addMouseListener(this);

        deckPanel.add(stockpileLabel);
        deckPanel.add(talonLabel);
        this.add(deckPanel);
    }

    private void initializeFoundationPanel() {
        foundationPanel.setBackground(new Color(29, 117, 36));
        foundationPanel.setBounds(10, 20, 320, 94);
        foundationPanel.setLayout(null);

        int x = 5;
        for (int i = 0; i < 4; i++) {
            foundationLabels[i] = new JLabel();
            foundationLabels[i].setBorder(new RoundBorder(10));
            foundationLabels[i].setBounds(x, 2, width, height);
            foundationLabels[i].addMouseListener(this);
            // add tableau icon here
            foundationPanel.add(foundationLabels[i]);
            x += 77;
        }
        this.add(foundationPanel);
    }

    private void initializeTableauPanel() {
        tableauPanel.setBackground(new Color(29, 117, 36));
        tableauPanel.setBounds(10, 150, 560, 94);
        tableauPanel.setLayout(null);
        int x = 5;
        for (int i = 0; i < 7; i++) {
            tableauLabels[i] = new JLabel();
            tableauLabels[i].setBorder(new RoundBorder(10));
            tableauLabels[i].setIcon(scaledImage(game.getTableau().peekTopCard(i).displayCard()));
            tableauLabels[i].setBounds(x, 2, width, height);
            tableauLabels[i].addMouseListener(this);
            // add tableau icon here
            tableauPanel.add(tableauLabels[i]);
            x += 80;
        }
        this.add(tableauPanel);
    }

    public void removePanels() {
        this.remove(tableauPanel);
        this.remove(foundationPanel);
        this.remove(deckPanel);
        this.remove(tracker);
        // add clear labels
        // use to update images of the foundation.
        for(int i = 0; i < 4; i++) {
                foundationLabels[i].setIcon(null);
                foundationLabels[i].setBorder(new RoundBorder(10));
        }
        // use to update images of the tableau.
        for(int i = 0; i < 7; i++) {
                tableauLabels[i].setIcon(null);
                tableauLabels[i].setBorder(new RoundBorder(10));
                System.out.println("Tableau clean up");
        }
        talonLabel.setIcon(null);
        game.cleanUp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // control menu
        if (e.getSource() == gameItem) {
            // needs some kind of update
            System.out.println("new game");
            game.cleanUp();
            game.initializeGame();
            updateLabels();
            text.setText("Cards Remaining: " + game.getDeck().deckSize());
            if (stopwatch.isRunning()) {
                stopwatch.stop();
                stopwatch.reset();
            }
        } else {
            System.exit(0);
        }

    }

    public ImageIcon scaledImage(ImageIcon image) {

        Image newImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage); // stockpile imgIcon

    }

    // stateChange class to update talon/stockpile on
    // click/////////////////////////////////////////
    public void drawDeck() {
        stockpileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card talonCard = null;
                if (game.getDeck().deckSize() > 0) {
                    game.getDeck().moveCardsToTalon(game.getTalon(), 1);

                    if(game.getTalon().topCard().getIsFaceDown()) {
                        game.getTalon().topCard().flipCard(); // flip card
                    }

                    talonCard = game.getTalon().topCard();
                    talonIcon = talonCard.displayCard();
                    Timage.setIcon(talonIcon);
                    text.setText("Cards Remaining: " + game.getDeck().deckSize());
                    if (game.getDeck().deckSize() == 0) {
                        stockpileLabel.setIcon(createGreyedOutImage(
                                stockpileLabel.getIcon()));
                    }
                } else {
                    game.getTalon().moveCardsToDeck(game.getDeck(), game.getTalon().deckSize());
                    // stockpileLabel.setIcon(backScaled);
                    stockpileLabel.setIcon(scaledImage(new ImageIcon("src/Images/01_back.png")));
                    text.setText("Cards Remaining: " + game.getDeck().deckSize());
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

    private void updateLabels() {

        if(game.getTalon().deckSize() != 0) {
            talonIcon = game.getTalon().topCard().displayCard();
            Timage.setIcon(talonIcon);
        } else {
            Timage.setIcon(null);
        }

        // use to update images of the foundation.
        for(int i = 0; i < 4; i++) {
            if(game.getFoundation().topCard(i) != null) {
                //System.out.println("Foundation " + (i + 1) + " " + game.getFoundation().topCard(i));
                foundationLabels[i].setIcon(scaledImage(game.getFoundation().topCard(i).displayCard()));
            } else {
                //System.out.println("Foundation " + (i + 1) + " is empty");
                foundationLabels[i].setIcon(null);
                foundationLabels[i].setBorder(new RoundBorder(10));
            }
        }

        // use to update images of the tableau.
        for(int i = 0; i < 7; i++) {
            if(game.getTableau().peekTopCard(i) != null) {
                //System.out.println("Tableau " + (i + 1) + " " + game.getTableau().peekTopCard(i));
                if(game.getTableau().peekTopCard(i).getIsFaceDown()){
                    // flip card over if the next card is face down
                    game.getTableau().peekTopCard(i).flipCard();
                }
                tableauLabels[i].setIcon(scaledImage(game.getTableau().peekTopCard(i).displayCard()));
            } else {
                //System.out.println("Tableau " + (i + 1) + " is empty");
                tableauLabels[i].setIcon(null);
                tableauLabels[i].setBorder(new RoundBorder(10));
            }
        }
    }

    Component srcPile; // Store the source pile -- move to top
    Component destPile; // Store the destination pile -- move to top

    @Override
    public void mouseClicked(MouseEvent e) {
        stopwatch.start();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Card tempCard;
        srcPile = e.getComponent(); // gets the component of what was clicked on.
        String source = "";

        if (srcPile == talonLabel) {

            System.out.println("Pressed on: Talon Pile");
            source = "talon";
            tempCard = game.getTalon().drawCard();
            game.moveCard(tempCard, source);

        } else if (srcPile == foundationLabels[0]) {

            System.out.println("Pressed on: Foundation 01");
            tempCard = game.getFoundation().topCard(0);
            if(game.moveCardTableau(tempCard)){
                game.getFoundation().removetopCard(0);
            }
            System.out.println("Foundation 01 Card: " + tempCard);

        } else if (srcPile == foundationLabels[1]) {

            System.out.println("Pressed on: Foundation 02");
            tempCard = game.getFoundation().topCard(1);
            if(game.moveCardTableau(tempCard)){
                game.getFoundation().removetopCard(1);
            }
            System.out.println("Foundation 02 Card: " + tempCard);

        } else if (srcPile == foundationLabels[2]) {

            System.out.println("Pressed on: Foundation 03");
            tempCard = game.getFoundation().topCard(2);
            if(game.moveCardTableau(tempCard)){
                game.getFoundation().removetopCard(2);
            }
            System.out.println("Foundation 03 Card: " + tempCard);

        } else if (srcPile == foundationLabels[3]) {

            System.out.println("Pressed on: Foundation 04");
            tempCard = game.getFoundation().topCard(3);
            if(game.moveCardTableau(tempCard)){
                game.getFoundation().removetopCard(3);
            }
            System.out.println("Foundation 04 Card: " + tempCard);

        } else if (srcPile == tableauLabels[0]) {

            System.out.println("Pressed on: Tableau 01");
            tempCard = game.getTableau().peekTopCard(0);
            if(game.moveCard(tempCard, source)){
              game.getTableau().removeTopCard(0);
            }
            System.out.println("Tableau 01 Card: " + tempCard);

        } else if (srcPile == tableauLabels[1]) {

            System.out.println("Pressed on: Tableau 02");
            tempCard = game.getTableau().peekTopCard(1);
            if(game.moveCard(tempCard, source)){
                game.getTableau().removeTopCard(1);
            }
            System.out.println("Tableau 02 Card: " + tempCard);

        } else if (srcPile == tableauLabels[2]) {

            System.out.println("Pressed on: Tableau 03");
            tempCard = game.getTableau().peekTopCard(2);
            if(game.moveCard(tempCard, source)){
                game.getTableau().removeTopCard(2);
            }
            System.out.println("Tableau 03 Card: " + tempCard);

        } else if (srcPile == tableauLabels[3]) {

            System.out.println("Pressed on: Tableau 04");
            tempCard = game.getTableau().peekTopCard(3);
            if(game.moveCard(tempCard, source)){
                game.getTableau().removeTopCard(3);
            }
            System.out.println("Tableau 04 Card: " + tempCard);

        } else if (srcPile == tableauLabels[4]) {

            System.out.println("Pressed on: Tableau 05");
            tempCard = game.getTableau().peekTopCard(4);
            if(game.moveCard(tempCard, source)){
                game.getTableau().removeTopCard(4);
            }
            System.out.println("Tableau 05 Card: " + tempCard);

        } else if (srcPile == tableauLabels[5]) {

            System.out.println("Pressed on: Tableau 06");
            tempCard = game.getTableau().peekTopCard(5);
            if(game.moveCard(tempCard, source)){
                game.getTableau().removeTopCard(5);
            }
            System.out.println("Tableau 06 Card: " + tempCard);

        } else if (srcPile == tableauLabels[6]) {

            System.out.println("Pressed on: Tableau 07");
            tempCard = game.getTableau().peekTopCard(6);
            if(game.moveCard(tempCard, source)){
                game.getTableau().removeTopCard(6);
            }
            System.out.println("Tableau 07 Card: " + tempCard);

        } else {
            System.out.println("Something went wrong: Pressed");
        }

        updateLabels();

        if (!stopwatch.isRunning()) {
            stopwatch.start();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!stopwatch.isRunning()) {
            stopwatch.start();
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