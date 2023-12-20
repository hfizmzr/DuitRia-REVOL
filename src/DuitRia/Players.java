package DuitRia;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Players {
    
	public String Name; // change to public *Hazim
	public int Balance, netWorth;
        public boolean bankrupt = false;
	private Map<String, Tiles> properties;
	public int position = 0, choice;
        Scanner sc = new Scanner(System.in);
        Scanner next = new Scanner(System.in);
        int total = 10;
    
    //commit note: syahir
    //tambah function player, beli jual, sewa, bayar sewa, beli rumah. jual rumah belom
    
    public Players(String name, int StartingBalance){
    	this.Name = name;
    	this.Balance = StartingBalance;
    	this.netWorth = StartingBalance;
    	this.properties = new HashMap<>();

	this.position = 0;

    }
    
    //buying properties
    
    public void collectTax(){
        System.out.println("You have to pay RM2000000 of tax");
        Balance -= 2000000;
        netWorth -= 2000000;
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
    
    public boolean checkOwner(String PropertyName) {
    	return properties.containsKey(PropertyName); // kita check nama tiles tadi exist tak dalam hashmap player ni
    }
    
    public void sellProperties(String PropertyName, int sellPrice) {
    	if(checkOwner(PropertyName)){
            Balance += sellPrice;
            properties.remove(PropertyName);
            System.out.println("Property " + PropertyName + " sold");
            System.out.println("Balance: " + Balance);
    	}
    	else {
            System.out.println("xdk buat cara xdk");
        }
    }
    
    public void payRent(Tiles tile, Players[] player) {
        int rent = tile.getRent();
    	if (Balance >= rent){
            Balance -= rent;
            netWorth -= rent;
            player[tile.owner].Balance += rent;
            System.out.println("Paid RM" + rent + " to " + player[tile.owner].Name);
            System.out.println("Balance: " + Balance);
    	}
    	else {
            System.out.println("duit mnoi, merempat lah tepi jalan");
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
    }
    
    public void sellHouse(Properties property) {
    	
    	int hargaJual = 200000;
    	
    	if (checkOwner(property.getName()) && property.getHouseNum() > 0) {
            Balance += hargaJual;
            property.sellHouse(this);
            System.out.println("House sold");
            System.out.println("Balance: " + Balance);
            System.out.println("Bilangan rumah: " + property.getHouseNum());
    	}
    	else {
    	}
    	
    }

    public void movement(Dice dice){
            //commit am betulkan movement;
            System.out.print("\nIt is " + Name + " turn");//Hazim: tambah prompt.
            next.nextLine();
            System.out.println(Name + " Balance: RM" + Balance);
            System.out.println(Name + " Net Worth: RM" + netWorth);
            int totalDice = dice.diceRoll();
            System.out.println(Name + " rolled " + totalDice);
            position+=totalDice;
            position = position%total;
            if(position>=0 && position < totalDice){
                salary();
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
        return (netWorth<0);
    }
    
    public String getName() {
    	return Name;
    }
    
    public int getBalance() {
    	return Balance;
    }
    
}
