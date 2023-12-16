package dice;

import java.util.Arrays;
import java.util.Random;

public class Dice {
    
    public static void main(String[] args) {
        String[] player = {"Player 1", "Player 2", "Player 3", "Player 4"};
        int[] totalDiceScore = new int[player.length];
        
        playerTurn(player, totalDiceScore);
    }
    
    private static int diceRoll() {
        
        Random rand = new Random();
        
        int dice1 = rand.nextInt(6) + 1;
        int dice2 = rand.nextInt(6) + 1;
        int totalDice = dice1 + dice2;
        
        return totalDice;
    }
    
    private static void playerTurn(String[] player, int[] totalDiceScore) {
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
            
            System.out.println(player[i] + " roll " + diceRoll);
        }
        
        placement(player,totalDiceScore);
        
        for (int i = 0; i < player.length; i++) {
            System.out.println((i+1) + ". " + player[i]);
        }
    }
    
    private static void placement(String[] player, int[] totalDiceScore) {
        
        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < player.length-i-1; j++) {
                if(totalDiceScore[j] < totalDiceScore[j+1]) {
                    // sort highest to lowest
                    int temp1 = totalDiceScore[j];
                    totalDiceScore[j] = totalDiceScore[j+1];
                    totalDiceScore[j+1] = temp1;
                    // sort highest to lowest
                    String temp2 = player[j];
                    player[j] = player[j+1];
                    player[j+1] = temp2;
                }
            }
        }
    }
}
