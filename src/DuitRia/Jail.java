package DuitRia;

import java.util.Random;

public class Jail {
    public void goToJail(Players player) {
        player.jail = true;
        player.position = 10;
        System.out.println("You go to jail");
    }
    
    public void getOutJail(Players player){
        Random rand = new Random();
        
        int dice1 = rand.nextInt(6) + 1;
        int dice2 = rand.nextInt(6) + 1;
        int totalDice = dice1+dice2;
        System.out.println("You are in jail");
        System.out.println("You rolled " + dice1 + " and " + dice2);
        while(player.jail == true){
            if(dice1 == dice2){
                player.position += totalDice;
                System.out.println("You rolled doubles! You can exit jail.");
                player.jail = false;
                if(player.position>=0 && player.position < totalDice)
                    player.salary();
                return;
            }
            else if(player.Balance>=250000){
                System.out.println("You didn't roll doubles. Pay RM250000");
                player.Balance -= 250000;
                player.position += (dice1 + dice2);
                player.jail=false;
            }
            else if(player.netWorth>=250000){
                System.out.println("You dont have enough money to get out of jail");
                player.sellProperties();
            }
            else{
                System.out.println("You cant afford to get out of jail");
                return;
            }
        }
    }
}
