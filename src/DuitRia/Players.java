package DuitRia;

import java.util.HashMap;
import java.util.Map;

public class Players {
    
	private String Name;
	private int Balance;
	private Map<String, Properties> properties;
	private int position;
	
	
    public int money = 15000000, netWorth = 15000000;
    
    //commit note: syahir
    //tambah function player, beli jual, sewa, bayar sewa, beli rumah. jual rumah belom
    
    public Players(String name, int StartingBalance) {
    	this.Name = name;
    	this.Balance = StartingBalance;
    	this.properties = new HashMap<>();
	this.position = 0;
    }
    
    //buying properties
    
    public void buyProperties(Properties property) {
    	if (Balance >= property.getPrice()) {
    		Balance -= property.getPrice();
    		properties.put(property.getName(), property); // kita setkan key dya, "nama tiles" dgn value dya, propertiea
    													  // dlm function propeties player ni howtf to explain
    													  // essetially kita assign property ni dkt hashmap player
    		System.out.println("Player bought the " + property.getName());
    		System.out.println("Player balance now is " + Balance);
    	}
    	else {
			System.out.println("x cukup duit, dasar miskin");
		}
    }
    
    public boolean checkOwner(String PropertyName) {
    	return properties.containsKey(PropertyName); // kita check nama tiles tadi exist tak dalam hashmap player ni
    }
    
    public void sellProperties(String PropertyName, int sellPrice) {
    	if (checkOwner(PropertyName)) {
    		Balance += sellPrice;
    		properties.remove(PropertyName);
    		System.out.println("Property " + PropertyName + " sold");
    		System.out.println("Balance: " + Balance);
    	}
    	else {
			System.out.println("xdk buat cara xdk");
		}
    }
    
    public void payRent(Properties property) {
    	int rent = property.calculateRent();
    	if (Balance >= rent) {
    		Balance -= rent;
    		property.GetOwner().receiveRent(rent);
    		System.out.println("Paid RM" + rent + " to " + property.GetOwner().getName());
    		System.out.println("Balance: " + Balance);
    	}
    	else {
    		System.out.println("duit mnoi, merempat lah tepi jalan");
    	}
    }
    
    
    
    public void receiveRent(int rent) {
    	Balance += rent; 
    }
    
    public void buyHouse(Properties property) {
    	if (checkOwner(property.getName())){
    		property.buyHouse(this);
    		System.out.println("House bought");
    		System.out.println("Number of house in this land is " + property.getHouseNum());
    	}
    	else {
    		System.out.println("duit turu");
    	}
    	
    	
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
    
    
    
    
    
    
    
    boolean dead = false;
    public int movement(int position, int dice1, int dice2){
            position+=dice1+dice2;
            if(position>=40){
                position%=10;
                salary();
            }
        return position;
    }
    
    public void salary(){
        System.out.println("You just past GO(Collect RM 2M)");
        money+=2000000;
        netWorth+=2000000;
        System.out.println("Your money is RM " + money);
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
