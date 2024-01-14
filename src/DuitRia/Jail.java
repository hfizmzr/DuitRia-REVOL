package DuitRia;

import java.util.Random;

public class Jail {
    public static void goToJail(Players player) {
        player.jail = true;
        player.position = 10;
        System.out.println("You go to jail");
    }
    
    public static void getOutJail(Players player){
        if(player.Balance >= 250000 || player.netWorth < 250000){
            System.out.println("You didn't roll doubles. Pay RM250000");
            player.Balance -= 250000;
            player.netWorth -= 250000;
            player.jail=false;
        }
    }
}
