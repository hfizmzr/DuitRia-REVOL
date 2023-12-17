/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DuitRia;

import java.util.Random;

/**
 *
 * @author Ezra
 */
public class Jail {
    public void GoToJail(Players player) {
        player.position = 10;
        System.out.println("You go to jail");
    }
    
    public void GetOutJail(Players player){
        Random rand = new Random();
        
        int dice1 = rand.nextInt(6) + 1;
        int dice2 = rand.nextInt(6) + 1;
        
        if(dice1 == dice2){
            player.position += (dice1 + dice2);
            System.out.println("You rolled doubles! You can exit jail.");
        }
        
        else{
            System.out.println("You didn't roll doubles. Pay up!");
            player.Balance -= 250000; 
            player.position += (dice1 + dice2); 
        }
    }
}
