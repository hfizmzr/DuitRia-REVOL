package DuitRia;

import GUI.GamePanel;
import Pages.GamePage;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Players {
    
	public String Name; // change to public *Hazim
	public int Balance, netWorth, totalHouse=0, round=1, tokenIndex;
        public boolean bankrupt = false, debt = false, jail = false, arrived = false;
	private Map<String, Tiles> properties;
        private Map<String, Tiles> mortgaged;
	public int position = 0, choice;
        final int total = 40;
    
    //commit note: syahir
    //tambah function player, beli jual, sewa, bayar sewa, beli rumah. jual rumah belom
    
    public Players(String name, int StartingBalance){
    	this.Name = name;
    	this.Balance = StartingBalance;
    	this.netWorth = StartingBalance;
    	this.properties = new HashMap<>();  
        this.mortgaged = new HashMap<>();
	this.position = 0;

    }
    
    //buying properties
    
    public void payTax(){
        if(Balance>=2000000 || netWorth<2000000){
            System.out.println("You payed RM2000000 of tax");
            Balance -= 2000000;
            netWorth -= 2000000;
            debt = false;
        }
        else
            debt = true;
    }
        
    public void buyProperties(Tiles tile, int turn) {
        if(Balance >= tile.landPrice){
            Balance -= tile.landPrice;
            netWorth -= tile.landPrice/2; // kita setkan key dya, "nama tiles" dgn value dya, propertiea
            System.out.println(Name + " bought the " + tile.name);
            tile.unowned = false;
            tile.owner = turn;
        }
    }
    
    public void mortgageProperties(Tiles tile){
        System.out.println(Name + " mortgaged " + tile.name);
        Balance += tile.landPrice/2;
        tile.mortgaged = true;
    }
    
    public void unmortgageProperties(Tiles tile){
        int amount = tile.landPrice/2;
        int interest = amount/10;
        System.out.println(Name + " unmortgaged " + tile.name);
        Balance -= amount + interest;
        netWorth -= interest;
        tile.mortgaged = false;
    }
    
    public void sellProperties(Tiles tile){
        Balance += tile.landPrice/2;
        System.out.println(Name + " sold " + tile.name);
        tile.unowned = true;
        tile.owner = -1;
    }

    public void payRent(Tiles[] tile, Players[] player) {
        int rent = tile[position].getRent(tile);
        if(Balance>=rent || netWorth<rent){
            System.out.println("You have to pay RM" + rent + " to " + player[tile[position].owner].Name);
            Balance -= rent;
            netWorth -= rent;
            player[tile[position].owner].Balance += rent;
            player[tile[position].owner].netWorth += rent;
            System.out.println("Paid RM" + rent + " to " + player[tile[position].owner].Name);
            debt = false;
            GamePage.updatePayRentPrompt(rent);
        }
        else
            debt = true;
    }
    
    public void payDoubleRent(Tiles[] tile, Players[] player) {
        debt = false;
        int rent = 2*tile[position].getRent(tile);
    	if (Balance >= rent || netWorth < rent){
            Balance -= rent;
            netWorth -= rent;
            player[tile[position].owner].Balance += rent;
            player[tile[position].owner].netWorth += rent;
            System.out.println("Paid RM" + rent + " to " + player[tile[position].owner].Name);
            GamePage.updatePayRentPrompt(rent);
    	}
    	else
            debt = true;
    }
    
    public void buyHouse(Tiles tile) {
        if(Balance >= tile.housePrice){
            Balance -= tile.housePrice;
            netWorth -= tile.housePrice/2;
            tile.house++;
            totalHouse++;
        }
    }
    
    public void sellHouse(Tiles tile) {
        
        tile.house--;
        totalHouse--;
        Balance += tile.housePrice/2;
        System.out.println(Name + " sold " + choice + " houses in " + tile.name);
    }

    public void movement(Dice dice, Jail jail){
        System.out.println("\nIt is " + Name + " turn");//Hazim: tambah prompt.
        System.out.println(Name + " Balance: RM" + Balance);
        System.out.println(Name + " Net Worth: RM" + netWorth);
        int totalDice = dice.diceRoll();
        System.out.println(Name + " rolled " + totalDice);
        position+=totalDice;
        position = position%total;
        if(position>=0 && position < totalDice){
            salary();
            round++;
        }
    }
    
    
    
    public void salary(){
        System.out.println("You just past GO(Collect RM 2M)");
        Balance+=2000000;
        netWorth+=2000000;
        System.out.println("Your money is RM " + Balance);
        System.out.println("Your net worth is RM " + netWorth);
    }
    
    public boolean isBankrupt(int index){
        if(netWorth<0){
            for(int i=0 ; i<40 ; i++){
                if(GamePanel.tile[i].owner==index){
                    GamePanel.tile[i].unowned = true;
                    GamePanel.tile[i].owner = -1;
                    GamePanel.tile[i].house = 0;
                    GamePanel.tile[i].mortgaged = false;
                }
            }
        }
        return (netWorth<0);
    }
    
    public String getName() {
    	return Name;
    }
    
    public int getBalance() {
    	return Balance;
    }
    
}
