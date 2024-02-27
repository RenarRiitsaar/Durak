package Durak.Game;

import lombok.Getter;

import javax.swing.*;
import java.util.*;

public class GameActions {

    private static final Random random = new Random();
    private static List<Card> tableCards = new ArrayList<>();
    @Getter
    private static boolean isPlayerTurn = false;


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



    public static void onLosingHand(){
        for (int i = 0; i < tableCards.size() ; i++) {
        Frame.getPlayer().getHand().add(tableCards.get(i));

        }
    }

    public static void addCard(){
        if(Frame.getPlayer().getHand().size() < 6){
            for (int i = Frame.getPlayer().getHand().size(); i < 6  ; i++) {
               int randomCard = random.nextInt(Card.getDeck().size());
                Frame.getPlayer().getHand().add(Card.getDeck().get(randomCard));
                Card.getDeck().remove(randomCard);
            }
        }
        if(Frame.getCpu().getHand().size() < 6){
            for (int i = Frame.getCpu().getHand().size(); i < 6; i++) {
                int randomCard = random.nextInt(Card.getDeck().size());
                Frame.getCpu().getHand().add(Card.getDeck().get(randomCard));
                Card.getDeck().remove(randomCard);
            }
        }
    }
}
