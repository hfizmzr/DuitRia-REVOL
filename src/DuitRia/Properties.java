package DuitRia;

public class Properties {

	private String name;
	private int price;
	private int numHouse;
	private Players owner;
	private String plotColour;


	public Properties(String Name, int Price, String plotcolour) {
		this.name = Name;
		this.price = Price;
		this.numHouse = 0;
		this.plotColour = plotcolour; 
		this.owner = null;
		
	}
		
	
	String[] Board =	 { 	"GO", "Petaling Street", "Fate" , "Jonker Street"  , "Tax", "KLIA",
							"Masjid Jamek" ,"Fate", "Batu Caves", "Sri Maha Mariahman Temple", "Prison" ,
							"National Museum","Tenaga National Berhad", "Royal Palace", "Merdeka Square" , 
							"Klia2", "A Famosa Port", "Fate", "Kelle Castle", "Stadthuys", "Free Parking" ,
							"Fraser's Hill", "Fate", "Cameron Highlands", "Genting Highland", "KL Sentral Station" ,
							"Pahang National Park","Jabatan Bekalan Air", "Gunung Mulu National Park", "Kinabalu National Park" ,
							"Go To Prison", "Tioman islands", "Perhentian Islands","Fate", "Sepadan Islands", "Pudu Sentral Station", "Fate",
							"KLCC", "Tax", "Sepang II Circuit" };
	
	
	public void DisplayBoard(Properties daym) {	
		for (int i = 0; i < 40; i++) {
			System.out.printf("[%d]: %s\n", i, daym.Board[i]);
		}
	}
	
	
	
	public int calculateRent() {
		//kena tengok card
		int rent = 0;
		
		return rent;
	}
	
	public void buyHouse(Players player) { // berapa rumah sekali boleh beli
		int HouseCost = 200000;
		
		if (player.getBalance() >= HouseCost) {
			numHouse++;
		}
	}
	
	public void sellHouse(Players player) {
		numHouse--;
	}
	
	
	//to access price
	public int getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public Players GetOwner() {
		return owner;
	}
	
	public int getHouseNum() {
		return numHouse;
	}
}
