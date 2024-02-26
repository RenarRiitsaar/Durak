package Durak.Game;

import Durak.Players.ComputerPlayer;
import Durak.Players.Player;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Frame extends JFrame implements ActionListener {

    @Getter
    public static JPanel playerCard1 = new JPanel();
    @Getter
    public static JPanel playerCard2 = new JPanel();
    @Getter
    public static JPanel playerCard3 = new JPanel();
    @Getter
    public static JPanel playerCard4 = new JPanel();
    @Getter
    public static JPanel playerCard5 = new JPanel();
    @Getter
    public static JPanel playerCard6 = new JPanel();
    @Getter
    public static JPanel playerCard7 = new JPanel();
    @Getter
    public static JPanel playerCard8 = new JPanel();
    @Getter
    public static JPanel playerCard9 = new JPanel();
    @Getter
    public static JPanel playerCard10 = new JPanel();
    @Getter
    public static JPanel playerCard11 = new JPanel();
    @Getter
    public static JPanel playerCard12 = new JPanel();
    @Getter
    public static JPanel cardPack = new JPanel();
    @Getter
    private static Player player;
    @Getter
    private static ComputerPlayer cpu;
    private static JPanel cpuCard1 = new JPanel();
    private static JPanel cpuCard2 = new JPanel();
    private static JPanel cpuCard3 = new JPanel();
    private static JPanel cpuCard4 = new JPanel();
    private static JPanel cpuCard5 = new JPanel();
    private static JPanel cpuCard6 = new JPanel();
    private static JPanel cpuCard7 = new JPanel();
    private static JPanel cpuCard8 = new JPanel();
    private static JPanel cpuCard9 = new JPanel();
    private static JPanel cpuCard10 = new JPanel();
    private static JPanel cpuCard11 = new JPanel();
    private static JPanel cpuCard12 = new JPanel();
    private static JPanel trump = new JPanel();
    @Getter
    private static String trumpSuit;


    public Frame(){
        player = new Player();
        cpu = new ComputerPlayer();

        Card.buildDeck();

        player.dealHand();
        cpu.dealHand();


        drawTrump();
        playerCards();
        cpuCards();

        GameActions.checkFirstTurn();

        frameSettings();


    }

    private void frameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(1000,1000,1000,1000);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);

        this.add(cardPack);
        this.add(playerCard1);
        this.add(playerCard2);
        this.add(playerCard3);
        this.add(playerCard4);
        this.add(playerCard5);
        this.add(playerCard6);
        this.add(playerCard7);
        this.add(playerCard8);
        this.add(playerCard9);
        this.add(playerCard10);
        this.add(playerCard11);
        this.add(playerCard12);

        this.add(trump);
        this.add(cpuCard1);
        this.add(cpuCard2);
        this.add(cpuCard3);
        this.add(cpuCard4);
        this.add(cpuCard5);
        this.add(cpuCard6);
        this.add(cpuCard7);
        this.add(cpuCard8);
        this.add(cpuCard9);
        this.add(cpuCard10);
        this.add(cpuCard11);
        this.add(cpuCard12);
    }

    public static ImageIcon rotateIcon(ImageIcon icon, double angle) {
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(angle), width / 2.0, height / 2.0);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage rotatedImage = op.filter(image, null);

        return new ImageIcon(rotatedImage);

    }

    private static void drawTrump() {

        trump.setBounds(75,430,100,100);
        Random random = new Random();
        int randomTrump = random.nextInt(Card.getDeck().size());

        ImageIcon icon = Card.getDeck().get(randomTrump).getCardTemplate();

        trumpSuit = Card.getDeck().get(randomTrump).getSuit();

        displayCardImage(trump, rotateIcon(icon,90));

        Card trumpToLastIndex = Card.getDeck().get(randomTrump);
        Card.getDeck().add(trumpToLastIndex);
        Card.getDeck().remove(randomTrump);

        cardPack.setBounds(50,425,75,124);
        displayCardImage(cardPack, Card.getBack());





    }

    private static void cpuCards(){

        cpuCard1.setBounds(250,200,75,124);
        cpuCard1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card1 Clicked");
            }
        });

        cpuCard2.setBounds(300,200,75,124);
        cpuCard2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card2 clicked");
            }
        });

        cpuCard3.setBounds(350,200,75,124);
        cpuCard3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card3 clicked");
            }
        });

        cpuCard4.setBounds(400,200,75,124);
        cpuCard4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card4 Clicked");
            }
        });

        cpuCard5.setBounds(450,200,75,124);
        cpuCard5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card5 clicked");
            }
        });

        cpuCard6.setBounds(500,200,75,124);
        cpuCard6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card6 clicked");
            }
        });

        cpuCard7.setBounds(100,200,100,100);
        cpuCard7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card7 clicked");
            }
        });

        cpuCard8.setBounds(100,100,100,100);
        cpuCard8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card8 clicked");
            }
        });

        cpuCard9.setBounds(100,100,100,100);
        cpuCard9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card9 clicked");
            }
        });

        cpuCard10.setBounds(100,100,100,100);
        cpuCard10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card10 clicked");
            }
        });


        cpuCard11.setBounds(100,100,100,100);
        cpuCard11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card11 Clicked");
            }
        });

        cpuCard12.setBounds(100,100,100,100);
        cpuCard12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card12 clicked");
            }
        });
        displayCardImage(cpuCard1, cpu.getHand().get(0).getCardTemplate());
        displayCardImage(cpuCard2, cpu.getHand().get(1).getCardTemplate());
        displayCardImage(cpuCard3, cpu.getHand().get(2).getCardTemplate());
        displayCardImage(cpuCard4, cpu.getHand().get(3).getCardTemplate());
        displayCardImage(cpuCard5, cpu.getHand().get(4).getCardTemplate());
        displayCardImage(cpuCard6, cpu.getHand().get(5).getCardTemplate());


        //displayCardImage(cpuCard7, cpu.getHand().get(6).getCardTemplate());
        //displayCardImage(cpuCard8, cpu.getHand().get(7).getCardTemplate());
        //displayCardImage(cpuCard9, cpu.getHand().get(8).getCardTemplate());
        //displayCardImage(cpuCard10, cpu.getHand().get(9).getCardTemplate());
        //displayCardImage(cpuCard11, cpu.getHand().get(10).getCardTemplate());
        //displayCardImage(cpuCard12, cpu.getHand().get(11).getCardTemplate());

    }

    private static void playerCards() {
        playerCard1.setBounds(250,600,75,124);
        playerCard1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card1 Clicked");
            }
        });

        playerCard2.setBounds(300,600,75,124);
        playerCard2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card2 clicked");
            }
        });

        playerCard3.setBounds(350,600,75,124);
        playerCard3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card3 clicked");
            }
        });

        playerCard4.setBounds(400,600,75,124);
        playerCard4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card4 Clicked");
            }
        });

        playerCard5.setBounds(450,600,75,124);
        playerCard5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card5 clicked");
            }
        });

        playerCard6.setBounds(500,600,75,124);
        playerCard6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card6 clicked");
            }
        });

        playerCard7.setBounds(100,100,100,100);
        playerCard7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card7 clicked");
            }
        });

        playerCard8.setBounds(100,100,100,100);
        playerCard8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card8 clicked");
            }
        });

        playerCard9.setBounds(100,100,100,100);
        playerCard9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card9 clicked");
            }
        });

        playerCard10.setBounds(100,100,100,100);
        playerCard10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card10 clicked");
            }
        });

        playerCard11.setBounds(100,100,100,100);
        playerCard11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card11 Clicked");
            }
        });

        playerCard12.setBounds(100,100,100,100);
        playerCard12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card12 clicked");
            }
        });

        displayCardImage(playerCard1, player.getHand().get(0).getCardTemplate());
        displayCardImage(playerCard2, player.getHand().get(1).getCardTemplate());
        displayCardImage(playerCard3, player.getHand().get(2).getCardTemplate());
        displayCardImage(playerCard4, player.getHand().get(3).getCardTemplate());
        displayCardImage(playerCard5, player.getHand().get(4).getCardTemplate());
        displayCardImage(playerCard6, player.getHand().get(5).getCardTemplate());
        //displayCardImage(playerCard7, player.getHand().get(6).getCardTemplate());
        //displayCardImage(playerCard8, player.getHand().get(7).getCardTemplate());
        //displayCardImage(playerCard9, player.getHand().get(8).getCardTemplate());
        //displayCardImage(playerCard10, player.getHand().get(9).getCardTemplate());
        //displayCardImage(playerCard11, player.getHand().get(10).getCardTemplate());
        //displayCardImage(playerCard12, player.getHand().get(11).getCardTemplate());

    }

    public static void displayCardImage(JPanel jp, ImageIcon imageIcon) {
        JLabel jl = new JLabel();
        jl.setIcon(imageIcon);
        imageIcon.getImage().getScaledInstance(75,125,java.awt.Image.SCALE_SMOOTH);
        jp.add(jl);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
