package Durak.Game;

import Durak.Players.Participants;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

public class GameActions {

    private static final Random random = new Random();
    @Getter
    private static List<Card> table = new ArrayList<>();
    @Getter
    private static List<Card> graveYard = new ArrayList<>();
    @Getter
    private static List<Card> killedCards = new ArrayList<>();
    @Getter @Setter
    private static boolean isPlayerTurn;
    @Getter @Setter
    private static boolean cpuAttacking;
    @Getter @Setter
    private static boolean noLegalMovesLeft = false;
    @Getter @Setter
    private static boolean isFirstCardToTable = true;
    @Getter @Setter
    private static boolean matchFound;


    public static void checkValue() {

        for (Map.Entry<JPanel, Card> entry : Frame.getPlayerCards().entrySet()) {
            int cardValue = entry.getValue().getValue();
            JPanel playerCard = entry.getKey();

            for (Card killedCard : killedCards) {

                if (killedCard.getValue() == cardValue) {
                    playerCard.setEnabled(true);
                    break;

                } else {
                    playerCard.setEnabled(false);
                }
            }
            if(Frame.getPlayerCard1().getX() != 325){
                Frame.getPlayerCard1().setEnabled(false);
            }
            if(Frame.getPlayerCard2().getX() != 375){
                Frame.getPlayerCard2().setEnabled(false);
            }
            if(Frame.getPlayerCard3().getX() != 425){
               Frame.getPlayerCard3().setEnabled(false);
            }
            if(Frame.getPlayerCard4().getX() != 475){
                Frame.getPlayerCard4().setEnabled(false);
            }
            if(Frame.getPlayerCard5().getX() != 525){
                Frame.getPlayerCard5().setEnabled(false);
            }
            if(Frame.getPlayerCard6().getX() != 575){
                Frame.getPlayerCard6().setEnabled(false);
            }
        }
    }


public static void cpuAttack(){
    cpuAttacking = true;
    System.out.println("CPU attacking");
    System.out.println(table);

    //TODO:
}
    public static void cpuDefence() {

        cpuAttacking = false;
        List<Card> cpuHand = Frame.getCpu().getHand();
         Card tableCard = table.get(0);



            for (int i = 0; i < cpuHand.size(); i++) {
                Card cpuCard = cpuHand.get(i);

                if (canDefend(cpuCard, tableCard)) {
                    cardsKilled(i);

                        JPanel getCpuCard = null;
                        JPanel getPlayerCard = null;
                        for (Map.Entry<JPanel, Card> entry : Frame.getCpuCards().entrySet()) {
                            if (entry.getValue().equals(cpuCard)) {
                                getCpuCard = entry.getKey();
                                break;
                            }
                        }
                        for (Map.Entry<JPanel, Card> entry2 : Frame.getPlayerCards().entrySet()) {
                            if (entry2.getValue().equals(tableCard)) {
                                getPlayerCard = entry2.getKey();
                                break;
                            }
                        }


                        getCpuCard.setBounds(getPlayerCard.getX(), getPlayerCard.getY() + 30, getPlayerCard.getWidth(), getPlayerCard.getHeight());
                        table.clear();
                    break;
                } else {
                    noLegalMovesLeft = true;
                }
            }

        if(noLegalMovesLeft){

            Frame.getPlayer().getHand().removeAll(killedCards);
            Frame.getPlayer().getHand().remove(tableCard);
            onLosingHand(Frame.getCpu());
            addCard(Frame.getPlayer());
            killedCards.clear();
            table.clear();
            setPlayerTurn(true);

            Frame.getNotification().setText("CPU lost the hand.");

            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                @Override
                public void run(){
                    Frame.getNotification().setText("Player turn.");
                }
            }, 1500);


            noLegalMovesLeft = false;
            Frame.restartTurn();
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                checkValue();
            }
        }, 3000);
        
    }



    public static boolean canDefend(Card card, Card tableCard) {

        if (card.getValue() > tableCard.getValue()) {
            if(Objects.equals(card.getSuit(), tableCard.getSuit())) {
                return true;

            }else if(isTrump(card) && isTrump(tableCard)){
                return true;

            } else if(isTrump(card) && !isTrump(tableCard)) {
                return true;
            }
        }
        if(card.getValue() < tableCard.getValue()){
            if(isTrump(card) && !isTrump(tableCard)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTrump(Card card) {
        return card.getSuit().equals(Frame.getTrumpSuit());
    }

    private static void cardsKilled(int i) {
        table.add(Frame.getCpu().getHand().get(i));
        Frame.getCpu().getHand().remove(i);
        killedCards.addAll(table);

        System.out.println(table + "ON TABLE");
        System.out.println(killedCards + "KILLED");
        System.out.println(graveYard + "IN GRAVEYARD");
       setPlayerTurn(true);
        noLegalMovesLeft = false;
    }

    public static void cpuFirstTurn() {

    cpuAttacking = true;

        List<Card> cpuHand = Frame.getCpu().getHand();
        cpuHand.sort(Comparator.comparingInt(Card::getValue));


                if (!isPlayerTurn && !Objects.equals(cpuHand.get(0).getSuit(), Frame.getTrumpSuit())) {
                    Frame.getCpuCard1().setBounds(200,420,75,115);
                    Frame.getCpuCard1().repaint();
                    table.add(cpuHand.get(0));
                    cpuHand.remove(0);

                }else if (!isPlayerTurn && !Objects.equals(cpuHand.get(1).getSuit(), Frame.getTrumpSuit()) ) {
                    Frame.getCpuCard2().setBounds(200,420,75,115);
                    Frame.getCpuCard2().repaint();
                    table.add(cpuHand.get(1));
                    cpuHand.remove(1);
                }else if (!isPlayerTurn && !Objects.equals(cpuHand.get(2).getSuit(), Frame.getTrumpSuit()) ) {
                    Frame.getCpuCard3().setBounds(200,420,75,115);
                    Frame.getCpuCard3().repaint();
                    table.add(cpuHand.get(2));
                    cpuHand.remove(2);
                }else if (!isPlayerTurn && !Objects.equals(cpuHand.get(3).getSuit(), Frame.getTrumpSuit()) ) {
                    Frame.getCpuCard4().setBounds(200,420,75,115);
                    Frame.getCpuCard4().repaint();
                    table.add(cpuHand.get(3));
                    cpuHand.remove(3);
                }else if (!isPlayerTurn && !Objects.equals(cpuHand.get(4).getSuit(), Frame.getTrumpSuit()) ) {
                    Frame.getCpuCard5().setBounds(200,420,75,115);
                    Frame.getCpuCard5().repaint();
                    table.add(cpuHand.get(4));
                    cpuHand.remove(4);
                }else{
                    Frame.getCpuCard6().setBounds(200,420,75,115);
                    Frame.getCpuCard6().repaint();
                    table.add(cpuHand.get(5));
                    cpuHand.remove(5);
                }


            }


    public static void drawTrump() {

        Frame.getTrump().setBounds(75,430,100,100);
        Random random = new Random();
        int randomTrump = random.nextInt(Card.getDeck().size());

        ImageIcon icon = Card.getDeck().get(randomTrump).getCardTemplate();

        Frame.setTrumpSuit(Card.getDeck().get(randomTrump).getSuit());

        Frame.displayCardImage(Frame.getTrump(), Frame.rotateIcon(icon,90));

        Card trumpToLastIndex = Card.getDeck().get(randomTrump);
        Card.getDeck().add(trumpToLastIndex);
        Card.getDeck().remove(randomTrump);

        Frame.getCardPack().setBounds(50,425,75,124);
        Frame.displayCardImage(Frame.getCardPack(), Card.getBack());
    }

    public static void checkFirstTurn(){

        List<Integer> playerStartTrumps = new ArrayList<>();
        List<Integer> cpuStartTrumps = new ArrayList<>();

        int playerTrumpMinValue = 0;
        int cpuTrumpMinValue = 0;

        for (int i = 0; i < 6 ; i++) {
            if(Objects.equals(Frame.getPlayer().getHand().get(i).getSuit(), Frame.getTrumpSuit())){
                playerStartTrumps.add(Frame.getPlayer().getHand().get(i).getValue());
            }

            if(Objects.equals(Frame.getCpu().getHand().get(i).getSuit(), Frame.getTrumpSuit())){
                cpuStartTrumps.add(Frame.getCpu().getHand().get(i).getValue());
            }
        }

        Collections.sort(playerStartTrumps);
        Collections.sort(cpuStartTrumps);

        if(!playerStartTrumps.isEmpty()){
            playerTrumpMinValue = playerStartTrumps.get(0);
        }

        if(!cpuStartTrumps.isEmpty()){
            cpuTrumpMinValue = cpuStartTrumps.get(0);
        }


        if(playerTrumpMinValue < cpuTrumpMinValue && playerTrumpMinValue != 0){
            setPlayerTurn(true);
            Frame.getNotification().setText("Player starts the game!");

        }else{
            setPlayerTurn(false);
            Frame.getNotification().setText("CPU starts the game!");
        }


        }



    public static void onLosingHand(Participants participant){

            participant.getHand().addAll(killedCards);
            participant.getHand().add(table.get(0));
            participant.getHand().sort(Comparator.comparingInt(Card::getValue));


    }

    public static void addCard(Participants participant){

        if(participant.getHand().size() < 6){
            for (int i = participant.getHand().size(); i < 6  ; i++) {
               int randomCard = random.nextInt(Card.getDeck().size());
               participant.getHand().add(Card.getDeck().get(randomCard));
                Card.getDeck().remove(randomCard);
            }
        }

        participant.getHand().sort(Comparator.comparingInt(Card::getValue));
        Frame.getCardsLeft().setText(String.valueOf(Card.getDeck().size()));
    }
}
