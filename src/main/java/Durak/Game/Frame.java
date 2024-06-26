package Durak.Game;

import Durak.Players.ComputerPlayer;
import Durak.Players.Player;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static Durak.Game.GameActions.*;

public class Frame extends JFrame implements ActionListener {

    @Getter
    private static JPanel playerCard1 = new JPanel();
    @Getter
    private static JPanel playerCard2 = new JPanel();
    @Getter
    private static JPanel playerCard3 = new JPanel();
    @Getter
    private static JPanel playerCard4 = new JPanel();
    @Getter
    private static JPanel playerCard5 = new JPanel();
    @Getter
    private static JPanel playerCard6 = new JPanel();
    @Getter
    private static JPanel playerCard7 = new JPanel();
    @Getter
    private static JPanel playerCard8 = new JPanel();
    @Getter
    private static JPanel playerCard9 = new JPanel();
    @Getter
    private static JPanel playerCard10 = new JPanel();
    @Getter
    private static JPanel playerCard11 = new JPanel();
    @Getter
    private static JPanel playerCard12 = new JPanel();
    @Getter
    private static JPanel cardPack = new JPanel();
    @Getter
    private static Player player;
    @Getter
    private static ComputerPlayer cpu;
    @Getter
    private static JPanel cpuCard1 = new JPanel();
    @Getter
    private static JPanel cpuCard2 = new JPanel();
    @Getter
    private static JPanel cpuCard3 = new JPanel();
    @Getter
    private static JPanel cpuCard4 = new JPanel();
    @Getter
    private static JPanel cpuCard5 = new JPanel();
    @Getter
    private static JPanel cpuCard6 = new JPanel();
    @Getter
    private static JPanel cpuCard7 = new JPanel();
    @Getter
    private static JPanel cpuCard8 = new JPanel();
    @Getter
    private static JPanel cpuCard9 = new JPanel();
    @Getter
    private static JPanel cpuCard10 = new JPanel();
    @Getter
    private static JPanel cpuCard11 = new JPanel();
    @Getter
    private static JPanel cpuCard12 = new JPanel();
    @Getter
    private static JPanel trump = new JPanel();
    @Getter @Setter
    private static String trumpSuit;
    @Getter
    private static JLabel notification = new JLabel();
    @Getter
    private static JLabel cardsLeft = new JLabel();
    @Getter
    private static JLabel graveYardCards = new JLabel();
    @Getter
    private static Map<JPanel, Card> playerCards = new HashMap<>();
    @Getter
    private static Map<JPanel, Card> cpuCards = new HashMap<>();

    private final JButton testButton = new JButton("test");
    private final JButton endTurn = new JButton("End turn");



    public Frame(){
        player = new Player();
        cpu = new ComputerPlayer();

        Card.buildDeck();

        player.dealHand();
        cpu.dealHand();


        frameSettings();
        GameActions.drawTrump();
        cpuCards();
        playerCards();
        GameActions.checkFirstTurn();



        if(!GameActions.isPlayerTurn()) {
            GameActions.cpuFirstTurn();
            GameActions.setCpuAttacking(true);
        }else{
            GameActions.setPlayerTurn(true);
            GameActions.setCpuAttacking(false);

        }
    }

    public static void checkEndTurn(){

        try {
            Thread.sleep(3000);
            int disabledCardCount = 0;
            for (Map.Entry<JPanel, Card> entry : Frame.getPlayerCards().entrySet()) {


                if (!entry.getKey().isEnabled()) {
                    disabledCardCount++;
                }
            }


            if (disabledCardCount == 6 || GameActions.getKilledCards().size() >= 12) {

                player.getHand().removeAll(getKilledCards());
                cpu.getHand().removeAll(getKilledCards());
                addCard(player);
                addCard(cpu);
                getGraveYard().addAll(getKilledCards());
                getKilledCards().clear();
                restartTurn();
                notification.setText("No cards left to add!");
                graveYardCards.setText("In graveyard: " + GameActions.getGraveYard().size());

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        notification.setText("CPU turn.");
                    }
                }, 1500);

                GameActions.setPlayerTurn(false);
                cpuAttack();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void restartTurn(){

        playerCard1.removeAll();
        playerCard1.setBounds(325, 600, 75, 115);
        displayCardImage(playerCard1, player.getHand().get(0).getCardTemplate());
        playerCard1.repaint();
        playerCard1.revalidate();

        playerCard2.removeAll();
        playerCard2.setBounds(375, 600, 75, 115);
        displayCardImage(playerCard2, player.getHand().get(1).getCardTemplate());
        playerCard2.repaint();
        playerCard2.revalidate();

        playerCard3.removeAll();
        playerCard3.setBounds(425, 600, 75, 115);
        displayCardImage(playerCard3, player.getHand().get(2).getCardTemplate());
        playerCard3.repaint();
        playerCard3.revalidate();

        playerCard4.removeAll();
        playerCard4.setBounds(475, 600, 75, 115);
        displayCardImage(playerCard4, player.getHand().get(3).getCardTemplate());
        playerCard4.repaint();
        playerCard4.revalidate();

        playerCard5.removeAll();
        playerCard5.setBounds(525, 600, 75, 115);
        displayCardImage(playerCard5, player.getHand().get(4).getCardTemplate());
        playerCard5.repaint();
        playerCard5.revalidate();

        playerCard6.removeAll();
        playerCard6.setBounds(575, 600, 75, 115);
        displayCardImage(playerCard6, player.getHand().get(5).getCardTemplate());
        playerCard6.repaint();
        playerCard6.revalidate();

        playerCards.clear();
        mapPlayerCards();

        for(Map.Entry<JPanel, Card> panel : playerCards.entrySet()){
            panel.getKey().setEnabled(true);

        }


        cpuCard1.removeAll();
        cpuCard1.setBounds(325, 200, 75, 115);
        displayCardImage(cpuCard1, cpu.getHand().get(0).getCardTemplate());
        cpuCard1.repaint();
        cpuCard1.revalidate();

        cpuCard2.removeAll();
        cpuCard2.setBounds(375, 200, 75, 115);
        displayCardImage(cpuCard2, cpu.getHand().get(1).getCardTemplate());
        cpuCard2.repaint();
        cpuCard2.revalidate();

        cpuCard3.removeAll();
        cpuCard3.setBounds(425, 200, 75, 115);
        displayCardImage(cpuCard3, cpu.getHand().get(2).getCardTemplate());
        cpuCard3.repaint();
        cpuCard3.revalidate();

        cpuCard4.removeAll();
        cpuCard4.setBounds(475, 200, 75, 115);
        displayCardImage(cpuCard4, cpu.getHand().get(3).getCardTemplate());
        cpuCard4.repaint();
        cpuCard4.revalidate();

        cpuCard5.removeAll();
        cpuCard5.setBounds(525, 200, 75, 115);
        displayCardImage(cpuCard5, cpu.getHand().get(4).getCardTemplate());
        cpuCard5.repaint();
        cpuCard5.revalidate();

        cpuCard6.removeAll();
        cpuCard6.setBounds(575, 200, 75, 115);
        displayCardImage(cpuCard6, cpu.getHand().get(5).getCardTemplate());
        cpuCard6.repaint();
        cpuCard6.revalidate();

        cpuCards.clear();
        mapCpuCards();


    }

    private void frameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(1000,1000,1000,1000);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);

        testButton.setBounds(825, 450, 100, 50);
        testButton.addActionListener(this);

        endTurn.setBounds(825, 400, 100, 50);
        endTurn.addActionListener(this);

        notification.setFont(new Font("MV Boli", Font.BOLD,30));
        notification.setText("");
        notification.setBounds(300, 100, 1000, 100);
        notification.setVisible(true);

        cardsLeft.setFont(new Font("MV Boli", Font.BOLD,30));
        cardsLeft.setText(String.valueOf(Card.getDeck().size()));
        cardsLeft.setBounds(60,350,100,100);
        cardsLeft.setVisible(true);

        graveYardCards.setFont(new Font("MV Boli", Font.BOLD,30));
        graveYardCards.setText("In graveyard: " + GameActions.getGraveYard().size());
        graveYardCards.setBounds(30,600,300,100);
        graveYardCards.setVisible(true);

        this.add(cardsLeft);
        this.add(graveYardCards);
        this.add(endTurn);
        this.add(testButton);
        this.add(notification);
        this.add(cardPack);
        this.add(trump);

        this.add(cpuCard12);
        this.add(cpuCard11);
        this.add(cpuCard10);
        this.add(cpuCard9);
        this.add(cpuCard8);
        this.add(cpuCard7);
        this.add(cpuCard6);
        this.add(cpuCard5);
        this.add(cpuCard4);
        this.add(cpuCard3);
        this.add(cpuCard2);
        this.add(cpuCard1);
        this.add(playerCard12);
        this.add(playerCard11);
        this.add(playerCard10);
        this.add(playerCard9);
        this.add(playerCard8);
        this.add(playerCard7);
        this.add(playerCard6);
        this.add(playerCard5);
        this.add(playerCard4);
        this.add(playerCard3);
        this.add(playerCard2);
        this.add(playerCard1);
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


    private static void cpuCards() {

        cpuCard1.setBounds(325, 200, 75, 115);
        cpuCard1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card1 Clicked");


            }
        });

        cpuCard2.setBounds(375, 200, 75, 115);
        cpuCard2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card2 clicked");
            }
        });

        cpuCard3.setBounds(425, 200, 75, 115);
        cpuCard3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card3 clicked");
            }
        });

        cpuCard4.setBounds(475, 200, 75, 115);
        cpuCard4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card4 Clicked");
            }
        });

        cpuCard5.setBounds(525, 200, 75, 115);
        cpuCard5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card5 clicked");
            }
        });

        cpuCard6.setBounds(575, 200, 75, 115);
        cpuCard6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card6 clicked");
            }
        });

        cpuCard7.setBounds(325, 250, 75, 115);
        cpuCard7.setVisible(false);

        cpuCard7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card7 clicked");
            }
        });

        cpuCard8.setBounds(375, 250, 75, 115);
        cpuCard8.setVisible(false);
        cpuCard8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card8 clicked");
            }
        });

        cpuCard9.setBounds(425, 250, 75, 115);
        cpuCard9.setVisible(false);
        cpuCard9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card9 clicked");
            }
        });

        cpuCard10.setBounds(475, 250, 75, 115);
        cpuCard10.setVisible(false);
        cpuCard10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card10 clicked");
            }
        });


        cpuCard11.setBounds(525, 250, 75, 115);
        cpuCard11.setVisible(false);
        cpuCard11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card11 Clicked");
            }
        });

        cpuCard12.setBounds(575, 250, 75, 115);
        cpuCard12.setVisible(false);
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

        int numOfCards = cpu.getHand().size();

        for (int i = 6; i < numOfCards; i++) {


            switch (i) {
                case 7:
                    cpuCard7.setVisible(true);
                    displayCardImage(cpuCard7, cpu.getHand().get(6).getCardTemplate());

                case 8:
                    cpuCard8.setVisible(true);
                    displayCardImage(cpuCard8, cpu.getHand().get(7).getCardTemplate());

                case 9:
                    cpuCard9.setVisible(true);
                    displayCardImage(cpuCard9, cpu.getHand().get(8).getCardTemplate());

                case 10:
                    cpuCard10.setVisible(true);
                    displayCardImage(cpuCard10, cpu.getHand().get(9).getCardTemplate());

                case 11:
                    cpuCard11.setVisible(true);
                    displayCardImage(cpuCard11, cpu.getHand().get(10).getCardTemplate());

                case 12:
                    cpuCard12.setVisible(true);
                    displayCardImage(cpuCard12, cpu.getHand().get(11).getCardTemplate());
            }
        }
        mapCpuCards();
    }

    private static void mapCpuCards() {
        cpuCards.put(cpuCard1, cpu.getHand().get(0));
        cpuCards.put(cpuCard2, cpu.getHand().get(1));
        cpuCards.put(cpuCard3, cpu.getHand().get(2));
        cpuCards.put(cpuCard4, cpu.getHand().get(3));
        cpuCards.put(cpuCard5, cpu.getHand().get(4));
        cpuCards.put(cpuCard6, cpu.getHand().get(5));
    }


    static void playerCards() {
        playerCard1.setBounds(325, 600, 75, 115);
            playerCard1.addMouseListener(new MouseAdapter() {

               @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    GameActions.checkValue();

                    if (playerCard1.isEnabled() && !isPlayerTurn()) {
                        if(GameActions.canDefend(playerCards.get(playerCard1), getTable().get(0))){

                        }
                    }
                    if(playerCard1.isEnabled() && isPlayerTurn()){
                        playerCard1.setEnabled(false);
                        GameActions.setCpuAttacking(false);
                        playerCard1.setBounds(200, 450, 75, 115);
                       GameActions.getTable().add(playerCards.get(playerCard1));
                        GameActions.setPlayerTurn(false);
                        GameActions.cpuDefence();
                    }
                }
            });

        playerCard2.setBounds(375, 600, 75, 115);
            playerCard2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    GameActions.checkValue();

                    if (GameActions.isCpuAttacking()) {
                        cpuAttack();
                    }

                    if(playerCard2.isEnabled() && isPlayerTurn()) {
                        GameActions.setCpuAttacking(false);
                        playerCard2.setBounds(300, 450, 75, 115);
                        GameActions.getTable().add(playerCards.get(playerCard2));
                        GameActions.setPlayerTurn(false);
                        playerCard2.setEnabled(false);
                        GameActions.cpuDefence();

                    }
                }
            });

        playerCard3.setBounds(425, 600, 75, 115);
            playerCard3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    GameActions.checkValue();

                    if (GameActions.isCpuAttacking()) {
                        cpuAttack();
                    }

                    if(playerCard3.isEnabled() && isPlayerTurn()) {

                        GameActions.setCpuAttacking(false);
                        playerCard3.setBounds(400, 450, 75, 115);
                        GameActions.getTable().add(playerCards.get(playerCard3));
                        GameActions.setPlayerTurn(false);
                        playerCard3.setEnabled(false);
                        GameActions.cpuDefence();

                    }
                }
            });


        playerCard4.setBounds(475, 600, 75, 115);
            playerCard4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    GameActions.checkValue();

                    if (GameActions.isCpuAttacking()) {
                        cpuAttack();
                    }

                    if(playerCard4.isEnabled() && isPlayerTurn()) {

                        GameActions.setCpuAttacking(false);
                        playerCard4.setBounds(500, 450, 75, 115);
                        GameActions.getTable().add(playerCards.get(playerCard4));
                        GameActions.setPlayerTurn(false);
                        playerCard4.setEnabled(false);
                        GameActions.cpuDefence();
                    }
                }
            });

            playerCard5.setBounds(525, 600, 75, 115);
            playerCard5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    GameActions.checkValue();

                        if (GameActions.isCpuAttacking()) {
                            cpuAttack();
                        }
                        if(playerCard5.isEnabled() && isPlayerTurn()) {

                            GameActions.setCpuAttacking(false);
                            playerCard5.setBounds(600, 450, 75, 115);
                            GameActions.getTable().add(playerCards.get(playerCard5));
                            GameActions.setPlayerTurn(false);
                            playerCard5.setEnabled(false);
                            GameActions.cpuDefence();
                        }
                }
            });



            playerCard6.setBounds(575, 600, 75, 115);
            playerCard6.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    GameActions.checkValue();



                    if (GameActions.isCpuAttacking()) {
                        cpuAttack();
                    }

                    if (playerCard6.isEnabled() && isPlayerTurn()) {

                        GameActions.setCpuAttacking(false);
                        playerCard6.setBounds(700, 450, 75, 115);
                        GameActions.getTable().add(playerCards.get(playerCard6));
                        GameActions.setPlayerTurn(false);
                        playerCard6.setEnabled(false);
                        GameActions.cpuDefence();

                    }
                }
            });


        playerCard7.setBounds(325, 650, 75, 115);
        playerCard7.setVisible(false);
        playerCard7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card7 clicked");
                GameActions.setPlayerTurn(false);

            }
        });


        playerCard8.setBounds(375, 650, 75, 115);
        playerCard8.setVisible(false);
        playerCard8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card8 clicked");
                GameActions.setPlayerTurn(false);

            }
        });

        playerCard9.setBounds(425, 650, 75, 115);
        playerCard9.setVisible(false);
        playerCard9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card9 clicked");
                GameActions.setPlayerTurn(false);

            }
        });

        playerCard10.setBounds(475, 650, 75, 115);
        playerCard10.setVisible(false);
        playerCard10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card10 clicked");
                GameActions.setPlayerTurn(false);

            }
        });

        playerCard11.setBounds(525, 650, 75, 115);
        playerCard11.setVisible(false);
        playerCard11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card11 Clicked");
                GameActions.setPlayerTurn(false);

            }
        });

        playerCard12.setBounds(575, 650, 75, 115);
        playerCard12.setVisible(false);
        playerCard12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Card12 clicked");
                GameActions.setPlayerTurn(false);

            }
        });

        displayCardImage(playerCard1, player.getHand().get(0).getCardTemplate());
        displayCardImage(playerCard2, player.getHand().get(1).getCardTemplate());
        displayCardImage(playerCard3, player.getHand().get(2).getCardTemplate());
        displayCardImage(playerCard4, player.getHand().get(3).getCardTemplate());
        displayCardImage(playerCard5, player.getHand().get(4).getCardTemplate());
        displayCardImage(playerCard6, player.getHand().get(5).getCardTemplate());


        int numOfCards = player.getHand().size();


        for (int i = 6; i < numOfCards; i++) {


            switch (i) {
                case 7:
                    playerCard7.setVisible(true);
                    displayCardImage(playerCard7, player.getHand().get(6).getCardTemplate());

                case 8:
                    playerCard8.setVisible(true);
                    displayCardImage(playerCard8, player.getHand().get(7).getCardTemplate());

                case 9:
                    playerCard9.setVisible(true);
                    displayCardImage(playerCard9, player.getHand().get(8).getCardTemplate());

                case 10:
                    playerCard10.setVisible(true);
                    displayCardImage(playerCard10, player.getHand().get(9).getCardTemplate());

                case 11:
                    playerCard11.setVisible(true);
                    displayCardImage(playerCard11, player.getHand().get(10).getCardTemplate());

                case 12:
                    playerCard12.setVisible(true);
                    displayCardImage(playerCard12, player.getHand().get(11).getCardTemplate());
            }
        }


        mapPlayerCards();


    }

    static void mapPlayerCards() {
        playerCards.put(playerCard1, player.getHand().get(0));
        playerCards.put(playerCard2, player.getHand().get(1));
        playerCards.put(playerCard3, player.getHand().get(2));
        playerCards.put(playerCard4, player.getHand().get(3));
        playerCards.put(playerCard5, player.getHand().get(4));
        playerCards.put(playerCard6, player.getHand().get(5));
    }

    public static void displayCardImage(JPanel jp, ImageIcon imageIcon) {
        JLabel jl = new JLabel();
        jl.setIcon(imageIcon);
        imageIcon.getImage().getScaledInstance(75,125,java.awt.Image.SCALE_SMOOTH);
        jp.add(jl);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == testButton){
            for(Map.Entry<JPanel, Card> card : playerCards.entrySet()){
                System.out.println(card.getKey() + "is enabled: " + card.getKey().isEnabled());
            }

            System.out.println("Is player turn?: " + GameActions.isPlayerTurn());
        }

        if(e.getSource() == endTurn) {

            if (isPlayerTurn()) {
                player.getHand().removeAll(getKilledCards());
                cpu.getHand().removeAll(getKilledCards());
                addCard(player);
                addCard(cpu);
                getGraveYard().addAll(getKilledCards());
                getKilledCards().clear();
                restartTurn();
                graveYardCards.setText("In graveyard: " + GameActions.getGraveYard().size());
                notification.setText("CPU turn.");
                GameActions.setPlayerTurn(false);
                cpuAttack();
            } else {
                JOptionPane.showMessageDialog(null, "It's CPU turn", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
