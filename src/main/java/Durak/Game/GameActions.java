package Durak.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameActions {

    private static Random random = new Random();
    private static List<Card> tableCards = new ArrayList<>();

    public static void checkFirstTurn(){

        List<Card> playerStartTrumps = new ArrayList<>();
        List<Card> cpuStartTrumps = new ArrayList<>();

        for (int i = 0; i < 6 ; i++) {
            if(Objects.equals(Frame.getPlayer().getHand().get(i).getSuit(), Frame.getTrumpSuit())){
                playerStartTrumps.add(Frame.getPlayer().getHand().get(i));
            }

            if(Objects.equals(Frame.getCpu().getHand().get(i).getSuit(), Frame.getTrumpSuit())){
                cpuStartTrumps.add(Frame.getCpu().getHand().get(i));
            }

        }
        System.out.println(playerStartTrumps  + " PLAYER");
        System.out.println(cpuStartTrumps + "CPU");
    }



    public static void onLosingHand(){
        for (int i = 0; i < tableCards.size() ; i++) {
        Frame.getPlayer().getHand().add(tableCards.get(i));

        }
    }

    public static void addCard(){
        if(Frame.getPlayer().getHand().size() < 6){
            for (int i = Frame.getPlayer().getHand().size(); i <6  ; i++) {
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
