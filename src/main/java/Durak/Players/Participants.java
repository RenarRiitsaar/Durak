package Durak.Players;

import Durak.Game.Card;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public abstract class Participants {

@Getter
    private  List<Card> hand;

    Random random = new Random();

    Participants(){

        hand = new ArrayList<>();
    }

    public void dealHand(){

        for (int i = 0; i < 6 ; i++) {
            int randomCard = random.nextInt(Card.getDeck().size());

            hand.add(Card.getDeck().get(randomCard));
            Card.getDeck().remove(randomCard);

        }
    }

}
