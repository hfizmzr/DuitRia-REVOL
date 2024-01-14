package DuitRia;

import GUI.GamePanel;
import Pages.GamePage;
import static Pages.GamePage.*;

public class Fate{
    public static int nowTurn;
    public void getFate(int index, Players[] player, int turn, Tiles[] tile, GamePage gamePage){
        switch(index){
            case 1:
                advanceToGo(player[turn]);
                break;
            case 2:
                railRoad(player, tile, turn);
                break;
            case 3:
                collectBirthday(player, turn, gamePage);
                break;
            case 4:
                bankError(player[turn]);
                break;
            case 5:
                goBack(player[turn]);
                break;
            case 6:
                Jail.goToJail(player[turn]);
                moveAni = true;
                endTurnButton.setEnabled(false);
                GamePanel.player[GamePanel.turn].arrived = false;
                break;
            case 7:
                repairHouse(player[turn]);
                break;
            case 8:
                hospitalFees(player[turn]);
                break;
            case 9:
                schoolFees(player[turn]);
                break;
            case 10:
                speedingFine(player[turn]);
                break;
        }
    }
    
    public void advanceToGo(Players player){
        player.position = 0;
        moveAni = true;
        player.salary();
        endTurnButton.setEnabled(false);
        GamePanel.player[GamePanel.turn].arrived = false;
    }
     
    public void railRoad(Players[] player, Tiles[] tile, int turn){
        while(tile[player[turn].position].rail==false){
            player[turn].position++;
            if(player[turn].position==40)
                player[turn].position=0;
        }
        System.out.println(player[turn].Name + " landed on " + player[turn].position + ". " + tile[player[turn].position].name);
        moveAni = true;
        endTurnButton.setEnabled(false);
        GamePanel.player[GamePanel.turn].arrived = false;
        if(!tile[player[turn].position].unowned && tile[player[turn].position].owner != turn)
            GamePage.doubleRent = true;
    }
      
      
      public void collectBirthday(Players[] player, int turn, GamePage gamePage){
        System.out.println("You RM100000 from each player for your birthday");
        for(int i=0 ; i<player.length ; i++){
            if(i==nowTurn || !player[i].debt)
                  continue;
            if(player[i].Balance>=100000 || player[i].netWorth<100000){
                player[i].Balance-=100000;
                player[i].netWorth-=100000;
                player[nowTurn].Balance+=100000;
                player[nowTurn].netWorth+=100000;
                System.out.println(player[i].Name + " payed RM100000 to " + player[turn].Name);
                player[i].debt = false;
            }
            else{
                GamePanel.turn = i;
                GamePage.endTurnButton.setEnabled(false);
                updateDebtPrompt(100000);
                updateProfileLabel();
                gamePage.add(prompt);
                gamePage.add(payButton);
                return;
            }
            GamePage.endTurnButton.setEnabled(true);
            GamePanel.turn = nowTurn;
        }

      }
      
      
    public void bankError(Players player){
        player.Balance += 2000000;
        player.netWorth += 2000000;
        System.out.println(player.Name + " got RM2000000 due to bank error");
        GamePage.endTurnButton.setEnabled(true);
    }
      
      
    public void goBack(Players player){
        moveAni = true;
        endTurnButton.setEnabled(false);
        GamePanel.player[GamePanel.turn].arrived = false;
        System.out.println("Go back 3 spaces");
        player.position-=3;
        if(player.position == -1)
            player.position = 39;
        player.debt = true;
        GamePage.direction = -1;
    }
    
    public void repairHouse(Players player){
        int amount = player.totalHouse*200000;
        if(player.Balance>=amount|| player.netWorth<amount){
            player.Balance-=amount;
            player.netWorth-=amount;
            System.out.println(player.Name + " payed RM" + amount + " to repair all you houses");
            player.debt = false;
        }
        else
            player.debt = true;
    }
    
    public void hospitalFees(Players player){
        int amount = 250000;
        if(player.Balance>=amount|| player.netWorth<amount){
            player.Balance-=amount;
            player.netWorth-=amount;
            System.out.println(player.Name + " payed RM" + amount + " hospital fees");
            player.debt = false;
        }
        else
            player.debt = true;
    }
    
    public void schoolFees(Players player){
        int amount = 100000;
        if(player.Balance>=amount|| player.netWorth<amount){
            player.Balance-=amount;
            player.netWorth-=amount;
            System.out.println(player.Name + " payed RM" + amount + " school fees");
            player.debt = false;
        }
        else
            player.debt = true;
    }
    
    public void speedingFine(Players player){
        int amount = 100000;
        if(player.Balance>=amount|| player.netWorth<amount){
            player.Balance-=amount;
            player.netWorth-=amount;
            System.out.println(player.Name + " payed RM" + amount + " speeding fine");
            player.debt = false;
        }
        else
            player.debt = true;
    }
}
