package Durak.Game;

import Durak.Players.Participants;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.*;

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


    public static boolean checkValue() {

        matchFound = false;


        for (Map.Entry<JPanel, Integer> entry : Frame.getPlayerCards().entrySet()) {
            Integer cardValue = entry.getValue();
            JPanel playerCard = entry.getKey();

            for (Card killedCard : killedCards) {

                if (killedCard.getValue() == cardValue) {
                    matchFound = true;

                }else{
                   matchFound = false;
                   playerCard.setEnabled(false);
                }
            }
        }

        return matchFound;
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
            isPlayerTurn = true;
        }

    }

    private static boolean canDefend(Card cpuCard, Card tableCard) {

        if (cpuCard.getValue() > tableCard.getValue()) {
            if(Objects.equals(cpuCard.getSuit(), tableCard.getSuit())) {
                return true;

            }else if(isTrump(cpuCard) && isTrump(tableCard)){
                return true;

            } else if(isTrump(cpuCard) && !isTrump(tableCard)) {
                return true;
            }
        }
        if(cpuCard.getValue() < tableCard.getValue()){
            if(isTrump(cpuCard) && !isTrump(tableCard)) {
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
        killedCards.addAll(table);
        table.clear();

        System.out.println(table + "ON TABLE");
        System.out.println(killedCards + "KILLED");
        System.out.println(graveYard + "IN GRAVEYARD");
        Frame.getCpu().getHand().remove(i);
        isPlayerTurn = true;
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

                setPlayerTurn(true);
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
            isPlayerTurn = true;
            Frame.getNotification().setText("Player starts the game!");

        }else{
            isPlayerTurn = false;
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
    }
}
