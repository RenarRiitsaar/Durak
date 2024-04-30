package Durak;

import Durak.Game.Frame;
import Durak.Game.GameActions;

public class Main {


    public static void main(String[] args) {

        //TODO: CPU logic, player defend (playerCard1)
        new Frame();
        Runnable runnable =
                () -> {
                    while(true){
                        Frame.checkEndTurn();
                    }
                };
runnable.run();

    }
}
