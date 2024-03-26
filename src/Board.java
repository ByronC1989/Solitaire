import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Stack;


public class Board extends JFrame{

    static protected Panel Panel;
    public static final int PANEL_WIDTH = 600, PANEL_HEIGHT =500;
    public Board() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel= new Panel();
        Panel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        add(Panel);
        pack();
    }
    public static void main(String [] args) {
        new Board().setVisible(true);
    }

    public class Panel extends JPanel {
        protected static int x_Shift=80;
        private static int OFFSET = 500;
        private static deck deck;
        private static waste waste;

        public Panel(){
            super.setLayout(null);
            initializePiles();
        }

        public void initializePiles(){
            deck = new deck(OFFSET,20);
            add(deck);
            waste = new waste(500 - x_Shift,20);
            add(waste);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(29, 117, 36));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        public abstract class Piles extends JPanel {
            protected int x, y;
            protected Stack<Card> cards;

            public Piles(int x, int y) {
                super.setLocation(x, y);
                cards = new Stack<>();
            }
            public void push(Card someCard) {
                this.cards.push(someCard);
            }
            public boolean isEmpty() {
                return this.cards.isEmpty();
            }
        }

        public class deck extends Piles {
            public deck(int x, int y) {
                super(x, y);
                super.setSize(72,96);
                for(Suit suit: Suit.values())
                    for(int j = 1;j <= 13; ++j){
                        Card card = new Card(j,suit);
                        push(card);
                        System.out.println("Pushed into deck" + card);
                    }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(5));
                g2d.setColor(Color.white);
                g2d.drawRect(0, 0, 72, this.getHeight());
                if (!isEmpty()) {
                    g.drawImage(Card.getCardBack(), 0, 0, 72, this.getHeight(), this);
                }
            }
        }

        public class Card {
            public static String CardBackFilename = "01_back",cardOutlineFilename = "bottom";
            public static String Directory = "images", extension = ".png";
            private Image im;
            private int value;
            private String suit;

            public Card(int value, Suit suit){
                this.value= value;
                switch (suit){
                    case CLUBS: this.suit = "c";
                        break;
                    case DIAMONDS: this.suit = "d";
                        break;
                    case HEARTS: this.suit ="h";
                        break;
                    case SPADES: this.suit = "s";
                        break;

                }
            }
            public static Image getCardOutline(){
                ImageIcon ii = new ImageIcon(
                        Card.class.getResource(Directory + "/" + cardOutlineFilename + extension));
                Image image = ii.getImage();
                return image;
            }
            public String toString(){
                return value + "of " + "]";
            }
            public static Image getCardBack() {
                ImageIcon ii = new ImageIcon(
                        Card.class.getResource(Directory + "/" + CardBackFilename + extension));
                Image image = ii.getImage();
                return image;

            }
        }
        public class waste extends Piles{
            public waste (int x,int y){
                super(x,y);
                super.setSize(72,96);
            }

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(Card.getCardOutline(),0,0,72,this.getHeight(),this);
            }
        }
        public class Foundation extends Piles{
            public Foundation (int x,int y){
                super(x,y);
            }
        }
    }


}