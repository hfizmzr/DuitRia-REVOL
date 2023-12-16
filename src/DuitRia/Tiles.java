package DuitRia;

import java.util.Scanner;

public class Tiles{
    public int house = 0, housePrice, landPrice;
    public boolean mortgaged = false;
    String name;
    
    public void mortgage(Players player){
        if(house>0){
            player.money+=housePrice/2*house;
            house = 0;
        }
        player.money+=landPrice/2;
        mortgaged = true;
    }
    public void unmortgaged(Players player){
        player.money -= (landPrice/2) + (landPrice/20);
        mortgaged = false;
    }
}

class PetalingStreet extends Tiles{
    PetalingStreet(){
        this.name = "Petaling Street";
        this.landPrice = 600000;
        
    }
}
class JonkerStreet extends Tiles{
    JonkerStreet(){
        this.landPrice = 600000;
    }
}


