package Durak.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DeckBuilder {

    private final String cardID;
    private final String suit;
    private final int value;
    private final ImageIcon cardTemplate;

    private static final ImageIcon back = new ImageIcon("src/main/resources/back.jpg");

    DeckBuilder(String name, int value, ImageIcon cardTemplate){

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

        List<DeckBuilder> hearts = new ArrayList<>();
        List<DeckBuilder> diamonds = new ArrayList<>();
        List<DeckBuilder> spades = new ArrayList<>();
        List<DeckBuilder> clubs = new ArrayList<>();


    for (int i = 0; i <= 9; i++) {
        heartsImage.add(new ImageIcon("src/main/resources/" + i + "H.png"));
        diamondsImage.add(new ImageIcon("src/main/resources/" + i + "D.png"));
        spadesImage.add(new ImageIcon("src/main/resources/" + i + "S.png"));
        clubsImage.add(new ImageIcon("src/main/resources/" + i + "C.png"));

    }

    for (int i = 2; i <= 9; i++) {
        hearts.add(new DeckBuilder(i + "H", i, heartsImage.get(i)));
        diamonds.add(new DeckBuilder(i + "D", i, diamondsImage.get(i)));
        spades.add(new DeckBuilder(i + "S", i, spadesImage.get(i)));
        clubs.add(new DeckBuilder(i + "C", i, clubsImage.get(i)));

    }



    List<DeckBuilder> deck = new ArrayList<>();

    deck.add(new DeckBuilder("TH", 10,  new ImageIcon("src/main/resources/TH.png")));
    deck.add(new DeckBuilder("JH", 11,  new ImageIcon("src/main/resources/JH.png")));
    deck.add(new DeckBuilder("QH", 12,  new ImageIcon("src/main/resources/QH.png")));
    deck.add(new DeckBuilder("KH", 13,  new ImageIcon("src/main/resources/KH.png")));
    deck.add(new DeckBuilder("AH", 14,  new ImageIcon("src/main/resources/AH.png")));

    deck.add(new DeckBuilder("TD", 10, new ImageIcon("src/main/resources/TD.png")));
    deck.add(new DeckBuilder("JD", 11, new ImageIcon("src/main/resources/JD.png")));
    deck.add(new DeckBuilder("QD", 12, new ImageIcon("src/main/resources/QD.png")));
    deck.add(new DeckBuilder("KD", 13, new ImageIcon("src/main/resources/KD.png")));
    deck.add(new DeckBuilder("AD", 14, new ImageIcon("src/main/resources/AD.png")));

    deck.add(new DeckBuilder("TS", 10, new ImageIcon("src/main/resources/TS.png")));
    deck.add(new DeckBuilder("JS", 11, new ImageIcon("src/main/resources/JS.png")));
    deck.add(new DeckBuilder("QS", 12, new ImageIcon("src/main/resources/QS.png")));
    deck.add(new DeckBuilder("KS", 13, new ImageIcon("src/main/resources/KS.png")));
    deck.add(new DeckBuilder("AS", 14, new ImageIcon("src/main/resources/AS.png")));

    deck.add(new DeckBuilder("TC", 10, new ImageIcon("src/main/resources/TC.png")));
    deck.add(new DeckBuilder("JC", 11, new ImageIcon("src/main/resources/JC.png")));
    deck.add(new DeckBuilder("QC", 12, new ImageIcon("src/main/resources/QC.png")));
    deck.add(new DeckBuilder("KC", 13, new ImageIcon("src/main/resources/KC.png")));
    deck.add(new DeckBuilder("AC", 14, new ImageIcon("src/main/resources/AC.png")));

    deck.addAll(hearts);
    deck.addAll(diamonds);
    deck.addAll(spades);
    deck.addAll(clubs);

    Collections.shuffle(deck);

    System.out.println(deck.get(32).toString());
    System.out.println(deck.size());

}

    @Override
    public String toString() {
        return "DeckBuilder{" +
                "cardID='" + cardID + '\'' +
                ", suit='" + suit + '\'' +
                ", value=" + value +
                ", cardTemplate=" + cardTemplate +
                '}';
    }
}
