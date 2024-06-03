import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
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
    JLabel stockpileLabel; // stockpile label
    private JLabel talonLabel = new JLabel();
    JLabel text = new JLabel();

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
        this.stopwatch.setBounds(10, 400, 160, 25);
        this.add(stopwatch);

        // create Frame
        this.setTitle("Solitaire"); // title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 500);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(29, 117, 36)); // background colour - green

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

        this.tableauPanel = new JPanel(); // create tableau Panel
        this.foundationPanel = new JPanel(); // create foundation panel
        this.deckPanel = new JPanel(); // Deck Panel Probably define in class
        this.tracker = new JPanel(); // Card Tracker Probably define in class
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
        // stockpileLabel = new JLabel(scaledImage(new
        // ImageIcon("Solitaire/src/Images/01_back.png")));
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
            foundationPanel.add(foundationLabels[i]);
            x += 77;
        }
        this.add(foundationPanel);
    }

    private void initializeTableauPanel() {
        tableauPanel.setBackground(new Color(29, 117, 36));
        tableauPanel.setBounds(10, 150, 560, 400);
        tableauPanel.setLayout(null);
        cascadeTableau(); // init cascade
        this.add(tableauPanel);
    }

    private void cascadeTableau() {
        tableauPanel.removeAll(); // clear panel before updating
        int x = 5; // starting x pos

        for (int i = 0; i < 7; i++) {
            JPanel pilePanel = new JPanel();
            pilePanel.setLayout(null); // supposed to use absolute layout for cascade??? seems to work
            pilePanel.setBackground(new Color(29, 117, 36));
            pilePanel.setBounds(x, 0, width, tableauPanel.getHeight()); // set area of pile panel

            int yOffset = 0; // reset offset ea col
            List<Card> column = game.getTableau().getColumn(i);

            for (Card card : column) {
                JLabel cardLabel = new JLabel(scaledImage(card.displayCard()));
                cardLabel.setBounds(0, yOffset, width, height);
                cardLabel.addMouseListener(this); // add mouse listener to ea card label
                pilePanel.add(cardLabel, 0); // add each card at the 0th index to layer
                yOffset += 20; // inc offset for cascade
            }

            tableauPanel.add(pilePanel); // display pilePanel on tableauPanel
            x += 80;// inc x for next col
        }

        tableauPanel.repaint(); // repaint tableau panel to show changes
        tableauPanel.revalidate(); // revalidate tableau panel - refresh layout
    }

    public void removePanels() {
        this.remove(tableauPanel);
        this.remove(foundationPanel);
        this.remove(deckPanel);
        this.remove(tracker);

        for (int i = 0; i < 4; i++) {
            foundationLabels[i].setIcon(null);
            foundationLabels[i].setBorder(new RoundBorder(10));
        }
        talonLabel.setIcon(null);
        game.cleanUp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameItem) {
            int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "New Game",
                    JOptionPane.YES_NO_OPTION);
            if (dialogButton == JOptionPane.YES_OPTION) {
                System.out.println("new game");
                game.cleanUp();
                game.initializeGame();
                cascadeTableau(); // display initial tableau
                text.setText("Cards Remaining: " + game.getDeck().deckSize());
                if (stopwatch.isRunning()) {
                    stopwatch.stop();
                    stopwatch.reset();
                }
            }
        } else if (e.getSource() == exitItem) {
            int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
            if (dialogButton == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public ImageIcon scaledImage(ImageIcon image) {
        Image newImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    public void drawDeck() {
        stockpileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card talonCard = null;
                if (game.getDeck().deckSize() > 0) {
                    game.getDeck().moveCardsToTalon(game.getTalon(), 1);
                    if (game.getTalon().topCard().getIsFaceDown()) {
                        game.getTalon().topCard().flipCard();
                    }
                    talonCard = game.getTalon().topCard();
                    talonIcon = talonCard.displayCard();
                    Timage.setIcon(talonIcon);
                    text.setText("Cards Remaining: " + game.getDeck().deckSize());
                    if (game.getDeck().deckSize() == 0) {
                        stockpileLabel.setIcon(createGreyedOutImage(stockpileLabel.getIcon()));
                    }
                } else {
                    game.getTalon().moveCardsToDeck(game.getDeck(), game.getTalon().deckSize());
                    // stockpileLabel.setIcon(scaledImage(new
                    // ImageIcon("Solitaire/src/Images/01_back.png")));
                    stockpileLabel.setIcon(scaledImage(new ImageIcon("src/Images/01_back.png")));
                    text.setText("Cards Remaining: " + game.getDeck().deckSize());
                    Timage.setIcon(null);
                }
            }
        });
    }

    private ImageIcon createGreyedOutImage(Icon originalIcon) {
        int width = originalIcon.getIconWidth();
        int height = originalIcon.getIconHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        originalIcon.paintIcon(null, g2, 0, 0);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.setColor(new Color(128, 128, 128, 200));
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        return new ImageIcon(img);
    }

    private void updateLabels() {
        if (game.getTalon().deckSize() != 0) {
            talonIcon = game.getTalon().topCard().displayCard();
            Timage.setIcon(talonIcon);
        } else {
            Timage.setIcon(null);
        }

        for (int i = 0; i < 4; i++) {
            if (game.getFoundation().topCard(i) != null) {
                foundationLabels[i].setIcon(scaledImage(game.getFoundation().topCard(i).displayCard()));
            } else {
                foundationLabels[i].setIcon(null);
                foundationLabels[i].setBorder(new RoundBorder(10));
            }
        }

        cascadeTableau();// refresh tableau display
    }

    Component srcPile;
    Component destPile;

    @Override
    public void mouseClicked(MouseEvent e) {
        stopwatch.start();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Card tempCard;
        srcPile = e.getComponent();
        String source = "";

        if (srcPile == talonLabel) {
            System.out.println("Pressed on: Talon Pile");
            source = "talon";
            tempCard = game.getTalon().drawCard();
            game.moveCard(tempCard, source);
        } else if (srcPile == foundationLabels[0]) {
            System.out.println("Pressed on: Foundation 01");
            tempCard = game.getFoundation().topCard(0);
            if (game.moveCardTableau(tempCard)) {
                game.getFoundation().removetopCard(0);
            }
            System.out.println("Foundation 01 Card: " + tempCard);
        } else if (srcPile == foundationLabels[1]) {
            System.out.println("Pressed on: Foundation 02");
            tempCard = game.getFoundation().topCard(1);
            if (game.moveCardTableau(tempCard)) {
                game.getFoundation().removetopCard(1);
            }
            System.out.println("Foundation 02 Card: " + tempCard);
        } else if (srcPile == foundationLabels[2]) {
            System.out.println("Pressed on: Foundation 03");
            tempCard = game.getFoundation().topCard(2);
            if (game.moveCardTableau(tempCard)) {
                game.getFoundation().removetopCard(2);
            }
            System.out.println("Foundation 03 Card: " + tempCard);
        } else if (srcPile == foundationLabels[3]) {
            System.out.println("Pressed on: Foundation 04");
            tempCard = game.getFoundation().topCard(3);
            if (game.moveCardTableau(tempCard)) {
                game.getFoundation().removetopCard(3);
            }
            System.out.println("Foundation 04 Card: " + tempCard);
        } else {
            for (int i = 0; i < 7; i++) {
                JPanel pilePanel = (JPanel) tableauPanel.getComponent(i);
                int cardCount = pilePanel.getComponentCount();
                if (cardCount == 0)
                    continue; // skip empty piles

                for (int j = 0; j < cardCount; j++) {
                    JLabel cardLabel = (JLabel) pilePanel.getComponent(j);
                    if (e.getSource() == cardLabel) {
                        System.out.println("Pressed on: Tableau " + (i + 1));
                        List<Card> column = game.getTableau().getColumn(i);

                        // flip card if not face up
                        boolean faceUpCardFound = false;
                        for (Card card : column) {
                            if (!card.getIsFaceDown()) {
                                faceUpCardFound = true;
                                break;
                            }
                        }

                        if (!faceUpCardFound && !column.isEmpty()) {
                            Card bottomCard = column.get(column.size() - 1);
                            if (bottomCard.getIsFaceDown()) {
                                bottomCard.flipCard();
                                System.out.println("Flipped card: " + bottomCard);
                                break;
                            }
                        } else {
                            // Move the top face-up card
                            tempCard = game.getTableau().peekTopCard(i);
                            if (game.moveCard(tempCard, source)) {
                                game.getTableau().removeTopCard(i);
                            }
                            System.out.println("Tableau " + (i + 1) + " Card: " + tempCard);
                        }
                        break;
                    }
                }
            }
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
        destPile = e.getComponent();
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}