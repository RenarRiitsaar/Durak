package Durak.Game;

import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Card {

    @Getter
    private static List<Card> deck;
    private final String cardID;
    @Getter
    private final String suit;
    @Getter
    private final int value;
    @Getter
    private final ImageIcon cardTemplate;
    @Getter
    private static final ImageIcon back = new ImageIcon("src/main/resources/back.jpg");

    Card(String name, int value, ImageIcon cardTemplate){

        String[] card = name.split("");
        this.cardID = card[0];
        this.suit = card[1];
        this.value = value;
        this.cardTemplate = cardTemplate;


    }

public static void buildDeck(){

        List<ImageIcon> heartsImage = new ArrayList<>();
        List<ImageIcon> diamondsImage = new ArrayList<>();
        List<ImageIcon> spadesImage = new ArrayList<>();
        List<ImageIcon> clubsImage = new ArrayList<>();

        List<Card> hearts = new ArrayList<>();
        List<Card> diamonds = new ArrayList<>();
        List<Card> spades = new ArrayList<>();
        List<Card> clubs = new ArrayList<>();


    for (int i = 0; i <= 9; i++) {
        heartsImage.add(new ImageIcon("src/main/resources/" + i + "H.png"));
        diamondsImage.add(new ImageIcon("src/main/resources/" + i + "D.png"));
        spadesImage.add(new ImageIcon("src/main/resources/" + i + "S.png"));
        clubsImage.add(new ImageIcon("src/main/resources/" + i + "C.png"));

    }

    for (int i = 2; i <= 9; i++) {
        hearts.add(new Card(i + "H", i, heartsImage.get(i)));
        diamonds.add(new Card(i + "D", i, diamondsImage.get(i)));
        spades.add(new Card(i + "S", i, spadesImage.get(i)));
        clubs.add(new Card(i + "C", i, clubsImage.get(i)));

    }


    deck = new ArrayList<>();

    deck.add(new Card("TH", 10,  new ImageIcon("src/main/resources/TH.png")));
    deck.add(new Card("JH", 11,  new ImageIcon("src/main/resources/JH.png")));
    deck.add(new Card("QH", 12,  new ImageIcon("src/main/resources/QH.png")));
    deck.add(new Card("KH", 13,  new ImageIcon("src/main/resources/KH.png")));
    deck.add(new Card("AH", 14,  new ImageIcon("src/main/resources/AH.png")));

    deck.add(new Card("TD", 10, new ImageIcon("src/main/resources/TD.png")));
    deck.add(new Card("JD", 11, new ImageIcon("src/main/resources/JD.png")));
    deck.add(new Card("QD", 12, new ImageIcon("src/main/resources/QD.png")));
    deck.add(new Card("KD", 13, new ImageIcon("src/main/resources/KD.png")));
    deck.add(new Card("AD", 14, new ImageIcon("src/main/resources/AD.png")));

    deck.add(new Card("TS", 10, new ImageIcon("src/main/resources/TS.png")));
    deck.add(new Card("JS", 11, new ImageIcon("src/main/resources/JS.png")));
    deck.add(new Card("QS", 12, new ImageIcon("src/main/resources/QS.png")));
    deck.add(new Card("KS", 13, new ImageIcon("src/main/resources/KS.png")));
    deck.add(new Card("AS", 14, new ImageIcon("src/main/resources/AS.png")));

    deck.add(new Card("TC", 10, new ImageIcon("src/main/resources/TC.png")));
    deck.add(new Card("JC", 11, new ImageIcon("src/main/resources/JC.png")));
    deck.add(new Card("QC", 12, new ImageIcon("src/main/resources/QC.png")));
    deck.add(new Card("KC", 13, new ImageIcon("src/main/resources/KC.png")));
    deck.add(new Card("AC", 14, new ImageIcon("src/main/resources/AC.png")));

    deck.addAll(hearts);
    deck.addAll(diamonds);
    deck.addAll(spades);
    deck.addAll(clubs);

    Collections.shuffle(deck);

}

    @Override
    public String toString() {
        return "Card{" +
                "cardID='" + cardID + '\'' +
                ", suit='" + suit + '\'' +
                ", value=" + value +
                ", cardTemplate=" + cardTemplate +
                '}';
    }


}
