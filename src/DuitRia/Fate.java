
package DuitRia;

import java.util.Random;
import java.util.Scanner;


public class Fate{
    
     public void Advancetogo(int y,Players player1){
 
           if(y==1)
              player1.position+= 37;
          else if(y==6)
          player1.position += 32;
          else if(y==16)
              player1.position+= 22;
          else if(y==21)
              player1.position += 17;
          else if(y==32)
              player1.position += 6;
          else
              player1.position += 3;
           
             player1.Balance +=2000000;
           System.out.print("Current money is "+player1.Balance);
           
    }
      
      
      public void Railroad(int y,Players player1){
          //player 
          if(y==1)
              player1.position+= 22;
          else if(y==6)
          player1.position += 17;
          else if(y==16)
              player1.position+= 7;
          else if(y==21)
              player1.position += 2;
          else if(y==32)
              player1.position += 1;
          else
              player1.position += 28;
              
      }
      
      
      public void Collectbirthday(Players player1, Players player2, Players player3){
          //currentmoney
          player1.Balance += 300000;  
          //connect dngn acc player
          
          player2.Balance -= 100000; 
          player3.Balance -= 100000; 
          System.out.print("Current money is "+player1.Balance);
          
      }
      
      
      public void Bankerror(Players player1){
          //currentmoney
          player1.Balance +=2000000;
          System.out.print("Current money is "+player1.Balance);
      }
      
      
      public void GoBack(Players player1){
         //direction
        player1.position-=3;
      }
      
      
      public void GoJail(int y,Players player1){
          //player1.Balance nnti dkt git dia akan connect
          if(y==1)
               player1.position +=27 ;
          else if(y==6)
           player1.position += 22;
          else if(y==16){
               player1.position += 52;
              //sbb dia xdpt 2M
          player1.Balance -= 2000000;
          }
          else if(y==21){
               player1.position +=47;
              player1.Balance -= 2000000;
          }
          else if(y==32){
              player1.position +=36;
             player1.Balance-=2000000;
          }
          else{
               player1.position +=33;
              player1.Balance-=2000000;
          }
          
      }
      
      
}
