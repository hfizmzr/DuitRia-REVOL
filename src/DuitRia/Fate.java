package DuitRia;

public class Fate{
    
    public void getFate(int index, Players[] player, int turn, Tiles[] tile, Jail jail){
        switch(index){
            case 1:
                advanceToGo(player[turn]);
                break;
            case 2:
                railRoad(player, tile, turn);
                break;
            case 3:
                collectBirthday(player, turn);
                break;
            case 4:
                bankError(player[turn]);
                break;
            case 5:
                goBack(player[turn]);
                break;
            case 6:
                jail.goToJail(player[turn]);
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
        System.out.println("Advance to GO and collect RM2000000");
        player.position = 0;
        player.Balance +=2000000;
        player.netWorth +=2000000;
    }
     
    public void railRoad(Players[] player, Tiles[] tile, int turn){
        System.out.println("Advance to the nearest railroad");
        while(tile[player[turn].position].rail==false){
            player[turn].position++;
            if(player[turn].position==40){
                player[turn].salary();
                player[turn].position=10;
            }
        }
        System.out.println(player[turn].Name + " landed on " + player[turn].position + ". " + tile[player[turn].position].name);
        if(tile[player[turn].position].unowned)
            player[turn].buyProperties(tile[player[turn].position], turn);
        else if(tile[player[turn].position].owner!=turn)
            player[turn].payDoubleRent(tile, player);
    }
      
      
      public void collectBirthday(Players[] player, int turn){
          System.out.println("You RM100000 from each player for your birthday");
          for(int i=0 ; i<player.length ; i++){
              player[i].debt = false;
              if(i==turn)
                  continue;
              do{
                if(player[i].Balance>=100000 || player[i].netWorth<100000){
                    player[i].Balance-=100000;
                    player[i].netWorth-=100000;
                    player[turn].Balance+=100000;
                    player[turn].netWorth+=100000;
                    System.out.println(player[i].Name + " payed RM100000 to " + player[turn].Name);
                    player[i].debt = false;
                }
                else{
                    player[i].debt = true;
                    System.out.println(player[i].Name + " dont have enough money");
                    player[i].sellProperties();
                }
              }while(player[i].debt);
          }

      }
      
      
    public void bankError(Players player){
        player.Balance += 2000000;
        player.netWorth += 2000000;
        System.out.println(player.Name + " got RM2000000 due to bank error");
    }
      
      
    public void goBack(Players player){
         //direction
        System.out.println("Go back 3 spaces");
        player.position-=3;
        player.debt = true;
    }
    
    public void repairHouse(Players player){
        System.out.println(player.Name + " has a total of " + player.totalHouse + " houses");
        System.out.println("You have to pay RM200000 for each house");        
        int amount = player.totalHouse*200000;
        System.out.println("You have to pay RM" + amount);
        do{
            if(player.Balance>=amount|| player.netWorth<amount){
                player.Balance-=amount;
                player.netWorth-=amount;
                System.out.println(player.Name + " payed RM" + amount + " to repair all you houses");
                player.debt = false;
            }
            else{
                player.debt = true;
                System.out.println(player.Name + " dont have enough money");
                player.sellProperties();
            }
        }while(player.debt);
    }
    
    public void hospitalFees(Players player){
        System.out.println(player.Name + " have to pay RM250000 for hospital fees");
        int amount = 250000;
        do{
            if(player.Balance>=amount|| player.netWorth<amount){
                player.Balance-=amount;
                player.netWorth-=amount;
                System.out.println(player.Name + " payed RM" + amount + " hospital fees");
                player.debt = false;
            }
            else{
                player.debt = true;
                System.out.println(player.Name + " dont have enough money");
                player.sellProperties();
            }
        }while(player.debt);
    }
    
    public void schoolFees(Players player){
        System.out.println(player.Name + " have to pay RM100000 for school fees");
        int amount = 100000;
        do{
            if(player.Balance>=amount|| player.netWorth<amount){
                player.Balance-=amount;
                player.netWorth-=amount;
                System.out.println(player.Name + " payed RM" + amount + " school fees");
                player.debt = false;
            }
            else{
                player.debt = true;
                System.out.println(player.Name + " dont have enough money");
                player.sellProperties();
            }
        }while(player.debt);
    }
    
    public void speedingFine(Players player){
        System.out.println(player.Name + " have to pay RM100000 for speeding fine");
        int amount = 100000;
        do{
            if(player.Balance>=amount|| player.netWorth<amount){
                player.Balance-=amount;
                player.netWorth-=amount;
                System.out.println(player.Name + " payed RM" + amount + " speeding fine");
                player.debt = false;
            }
            else{
                player.debt = true;
                System.out.println(player.Name + " dont have enough money");
                player.sellProperties();
            }
        }while(player.debt);
    }
}
