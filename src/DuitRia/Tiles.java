package DuitRia;

import java.util.Scanner;

public class Tiles{
    public int house = 0, housePrice, landPrice, owner = -1;
    public boolean mortgaged = false, fate = false, tax = false, station = false, commodity = false, jail = false, go = false, unowned = true;
    String name;
    
    public void mortgage(Players player){
        if(house>0){
            player.Balance+=housePrice/2*house;
            house = 0;
        }
        player.Balance+=landPrice/2;
        mortgaged = true;
    }
    public void unmortgaged(Players player){
        player.Balance -= (landPrice/2) + (landPrice/20);
        mortgaged = false;
    }
}

//Hazim: set properties for different tile and boolean to check which methods to call according to type of tile
class PetalingStreet extends Tiles{
    PetalingStreet(){
        
        this.name = "Petaling Street";
        this.landPrice = 600000;
        
    }
}
class JonkerStreet extends Tiles{
    JonkerStreet(){
        this.name = "Jonker Street";
        this.landPrice = 600000;
    }
}
class MasjidJamek extends Tiles{
    MasjidJamek(){
        this.name = "Masjed Jamek";
        this.landPrice = 1000000;
    }
}
class BatuCaves extends Tiles{
    BatuCaves(){
        this.name = "Batu Caves";
        this.landPrice = 1000000;
    }
}
class SriMahaMariammanTemple extends Tiles{
    SriMahaMariammanTemple(){
        this.name = "SriMahaMariammanTemple";
        this.landPrice = 1200000;
    }
}

class FateTile extends Tiles{
    FateTile(){
        this.fate = true;
        this.name = "Fate";
    }
}

class Tax extends Tiles{
    Tax(){
        this.tax = true;
        this.name = "Tax";
    }
} 

class Station extends Tiles{
    Station(String name){
        this.station = true;
        this.name = name;
    }
}

class Commodity extends Tiles{
    Commodity(String name){
        this.commodity = true;
        this.name = name;
    }
} 

class JailTile extends Tiles{
    JailTile(){
        this.jail = true;
        this.name = "Jail";
    }
}

class Go extends Tiles{
    Go(){
        this.go = true;
        this.name = "go";
    }
}