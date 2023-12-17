package DuitRia;

import java.util.Random;

public class Dice {
    
    //string[] player
    //int[] total dice score
    //playerturn(player,totaldicescore);
    
    public static int diceRoll() {
        
        Random rand = new Random();
        
        int dice1 = rand.nextInt(6) + 1;
        int dice2 = rand.nextInt(6) + 1;
        int totalDice = dice1 + dice2;
        
        return totalDice;
    }
    
    public static void playerTurn(Players[] player, int[] totalDiceScore) { // Hazim:  change String[] to Players[]
        for (int i = 0; i < player.length; i++) {
            int diceRoll = diceRoll();
            totalDiceScore[i] = diceRoll;
            // duplicate re-roll
            for (int j = 0; j < i; j++) {
                while(totalDiceScore[i] == totalDiceScore[j]) {
                    diceRoll = diceRoll();
                    totalDiceScore[i] = diceRoll;
                    j = 0;
                }
            }
            
            System.out.println(player[i].Name + " roll " + diceRoll); // Hazim: call by player.Name
        }
        
        placement(player,totalDiceScore);
        
        for (int i = 0; i < player.length; i++) {
            System.out.println((i+1) + ". " + player[i].Name);
        }
    }
    
    private static void placement(Players[] player, int[] totalDiceScore) {//Hazim: String[] to Players []
                                                                          
        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < player.length-i-1; j++) {
                if(totalDiceScore[j] < totalDiceScore[j+1]) {
                    // sort highest to lowest
                    int temp1 = totalDiceScore[j];
                    totalDiceScore[j] = totalDiceScore[j+1];
                    totalDiceScore[j+1] = temp1;
                    // sort highest to lowest
                    String temp2 = player[j].Name;
                    player[j].Name = player[j+1].Name;          //Hazim: call name using player[].Name
                    player[j+1].Name = temp2;
                }
            }
        }
    }
}
