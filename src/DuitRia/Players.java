package DuitRia;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Players {
    
	public String Name; // change to public *Hazim
	public int Balance, netWorth, totalHouse=0, round=0, tokenIndex;
        public boolean bankrupt = false, debt = false, jail = false;
	private Map<String, Tiles> properties;
        private Map<String, Tiles> mortgaged;
	public int position = 0, choice;
        Scanner sc = new Scanner(System.in);
        Scanner next = new Scanner(System.in);
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
        debt = false;
        if(Balance >= 2000000 || netWorth<2000000){
            System.out.println("You payed RM2000000 of tax");
            Balance -= 2000000;
            netWorth -= 2000000;
        }
        else{
            System.out.println("You dont have enought money to pay tax. You have to sell you properties");
            debt=true;
        }
    }
        
    public void buyProperties(Tiles tile, int turn) {
        System.out.print("Do you want to buy " + tile.name + ". Enter 1 if you want to buy it for RM" + tile.landPrice + ": ");
        choice = sc.nextInt();
        if(choice != 1)
            return;
        if(Balance >= tile.landPrice) {
            Balance -= tile.landPrice;
            netWorth -= tile.landPrice/2;
            properties.put(tile.name, tile); // kita setkan key dya, "nama tiles" dgn value dya, propertiea
            System.out.println(Name + " bought the " + tile.name);
            tile.unowned = false;
            tile.owner = turn;
        }
        else {
            System.out.println("x cukup duit, dasar miskin");
        }
    }
    
    public void mortgageProperties(){
        int count;
        String mortgage;
        again:
        while(true){
            count = 0;
            System.out.print(Name + " enter 1 if you want to mortgage or any property: ");
            choice = sc.nextInt();
            if(choice != 1)
                return;
            if(properties.size()==0){
                System.out.println(Name + " you have no properties to mortgage");
                return;
            }
            for(String i : properties.keySet()){
                System.out.println(++count + ". " + i + " : " + properties.get(i).house + " houses");
            }
            System.out.print("Enter the name of the property that you want to mortgage: ");
            mortgage = next.nextLine();
            
            for(String i : properties.keySet()){

                if(mortgage.equals(i)){
                    if(properties.get(mortgage).house>0){
                        sellHouse(properties.get(mortgage));
                        continue again;
                    }
                    else{
                        System.out.println(Name + " mortgaged " + properties.get(i).name);
                        Balance += properties.get(i).landPrice/2;
                        properties.get(i).mortgaged = true;
                        mortgaged.put(mortgage, properties.get(mortgage));
                    }
                }
            }
            properties.remove(mortgage);
        }
    }
    
    public void unmortgageProperties(){
        int count;
        String unmortgage;
        again:
        while(true){
            count = 0;
            System.out.print(Name + " enter 1 if you want to unmortgage or any property: ");
            choice = sc.nextInt();
            if(choice != 1)
                return;
            if(mortgaged.size()==0){
                System.out.println(Name + " you have no mortgaged properties");
                return;
            }
            for(String i : mortgaged.keySet()){
                System.out.println(++count + ". " + i + " : " + mortgaged.get(i).house + " houses");
            }
            System.out.print("Enter the name of the property that you want to unmortgage: ");
            unmortgage = next.nextLine();
            
            for(String i : mortgaged.keySet()){

                if(unmortgage.equals(i)){
                    int amount = mortgaged.get(i).landPrice/2;
                    int interest = amount/10;
                    System.out.println("To unmortgage this property you have to pay RM" + (amount+interest));
                    if(Balance<amount+interest){
                        System.out.println("You dont have enough money to unmortgage this property");
                        continue again;
                    }
                    System.out.println(Name + " unmortgaged " + mortgaged.get(i).name);
                    Balance -= amount + interest;
                    netWorth -= interest;
                    mortgaged.get(i).mortgaged = false;
                    properties.put(unmortgage, mortgaged.get(unmortgage));
                }
            }
            mortgaged.remove(unmortgage);
        }
    }
    
    public void sellProperties() {
        int count;
        String sell;
        again:
        while(true){
            count = 0;
            System.out.print(Name + " enter 1 if you want to sell any property: ");
            choice = sc.nextInt();
            if(choice != 1)
                return;
            if(properties.size()==0){
                System.out.println(Name + " you have no properties");
                return;
            }
            for(String i : properties.keySet()){
                System.out.println(++count + ". " + i + " : " + properties.get(i).house + " houses");
            }
            System.out.print("Enter the name of the property that you want to sell: ");
            sell = next.nextLine();
            
            for(String i : properties.keySet()){

                if(sell.equals(i)){
                    if(properties.get(sell).house>0){
                        sellHouse(properties.get(sell));
                        continue again;
                    }
                    else{
                        System.out.println(Name + " sold " + properties.get(i).name);
                        Balance += properties.get(i).landPrice/2;
                        properties.get(i).owner = -1;
                        properties.get(i).unowned = true; 
                    }
                }
            }
            properties.remove(sell);
        }
    }
    
    public void payRent(Tiles[] tile, Players[] player) {
        debt = false;
        int rent = tile[position].getRent(tile);
        System.out.println("You have to pay RM" + rent + " to " + player[tile[position].owner].Name);
    	if (Balance >= rent || netWorth < rent){
            Balance -= rent;
            netWorth -= rent;
            player[tile[position].owner].Balance += rent;
            player[tile[position].owner].netWorth += rent;
            System.out.println("Paid RM" + rent + " to " + player[tile[position].owner].Name);
    	}
    	else{
            System.out.println("You dont have enough money sell properties to pay");
            debt = true;
    	}
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
    	}
    	else{
            System.out.println("You dont have enough money sell properties to pay");
            debt = true;
    	}
    }
    
    public void buyHouse(Tiles tile) {
        if(netWorth<tile.housePrice)
            return;
        do{
            System.out.print("Enter 1 if you want to buy a house in " + tile.name + " for RM" + tile.housePrice + " each: ");
            choice = sc.nextInt();
            if(choice!=1)
                return;
            System.out.println("Number of house in " + tile.name + " is " + tile.house);
            System.out.print("How many house do you want to buy: ");
            choice = sc.nextInt();
            if(choice*tile.housePrice<=Balance){
                //sell
            }
        }while(choice<=0 || (choice+tile.house)>4 || choice*tile.housePrice>=Balance);
        
        Balance -= choice*tile.housePrice;
        netWorth -= choice*tile.housePrice/2;
        tile.house += choice;
        totalHouse += choice;
    }
    
    public void sellHouse(Tiles tile) {
        do{
            System.out.print("There are " + tile.house + " House(s) in " + tile.name + "How many houses do you want to sell: ");
            choice = sc.nextInt();
        }while(choice>tile.house || choice<0);
        
        tile.house -= choice;
        totalHouse -= choice;
        Balance += choice*tile.housePrice/2;
        System.out.println(Name + " sold " + choice + " houses in " + tile.name);
    }

    public void movement(Dice dice, Jail jail){
        System.out.print("\nIt is " + Name + " turn");//Hazim: tambah prompt.
        next.nextLine();
        System.out.println(Name + " Balance: RM" + Balance);
        System.out.println(Name + " Net Worth: RM" + netWorth);
        if(this.jail == true){
            jail.getOutJail(this);
        }
        else{
            int totalDice = dice.diceRoll();
            System.out.println(Name + " rolled " + totalDice);
            position+=totalDice;
            position = position%total;
            if(position>=0 && position < totalDice){
                salary();
                round++;
            }
        }
    }
    
    
    
    public void salary(){
        System.out.println("You just past GO(Collect RM 2M)");
        Balance+=2000000;
        netWorth+=2000000;
        System.out.println("Your money is RM " + Balance);
        System.out.println("Your net worth is RM " + netWorth);
    }
    
    public boolean isBankrupt(){
        if(netWorth<0){
            System.out.println(Name + " is bankrupt");
            for(String i : properties.keySet()){
                properties.get(i).owner = -1;
                properties.get(i).unowned = true; 
                properties.get(i).house = 0; 
            }
            properties.clear();
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
