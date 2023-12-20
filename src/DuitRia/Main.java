package DuitRia;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        int n=2, turn=0;
        int[] totalDiceScore = new int[n];
        int total = 10;
        
        Players[] player = new Players[n];
        Scanner sc = new Scanner(System.in);
        Dice dice = new Dice();
        Master master = new Master();
        Tiles[] tile = new Tiles[total];
        
        master.initializeTiles(tile);
        
        for(int i=0 ; i<n ; i++){
            System.out.print("Enter your name player " + (i+1) + ": ");
            player[i] = new Players(sc.nextLine(), 15000000);
        }
        
        for(int i=0 ; i<n ; i++){
            System.out.println(player[i].Name);
        }
        
        dice.playerTurn(player, totalDiceScore);

        while(master.play(player)){
                turn%=n;
                
                if(player[turn].bankrupt==false)
                    master.move(turn, player, dice, tile);

                turn++;
        }
        
    }
}
    