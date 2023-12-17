package DuitRia;

import java.util.Scanner;

public class Tiles{
    public int house = 0, housePrice, landPrice;
    public boolean mortgaged = false, fate = false, tax = false, station = false, commodity = false, jail = false, go = false;
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
        this.name = "Sri Maha Mariamman Temple";
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

class NationalMuseum extends Tiles {
    NationalMuseum() {
        this.name = "National Meseum";
        this.landPrice = 1400000;
    }
}

class TenagaNationalBerhad extends Tiles {
    TenagaNationalBerhad() {
        this.name = "Tenaga National Berhad";
        this.landPrice = 1500000;
    }
}

class RoyalPalace extends Tiles {
    RoyalPalace() {
        this.name = "Royal Palace";
        this.landPrice = 1400000;
    }
}

class MerdekaSquare extends Tiles {
    MerdekaSquare() {
        this.name = "Merdeka Square";
        this.landPrice = 1400000;
    }
}

class AFamosaFort extends Tiles {
    AFamosaFort() {
        this.name = "A Famosa Fort";
        this.landPrice = 1700000;
    }
}

class KellieCastle extends Tiles {
    KellieCastle() {
        this.name = "Kellie Castle";
        this.landPrice = 1800000;
    }
}

class Stadthuys extends Tiles {
    Stadthuys() {
        this.name = "Stadthuys";
        this.landPrice = 2000000;
    }
}

class FreeParking extends Tiles {
    FreeParking() {
        this.name = "Free Parking";
    }
}

class FraserHill extends Tiles {
    FraserHill() {
        this.name = "Fraser's Hill";
        this.landPrice = 2200000;
    }
}

class CameronHighlands extends Tiles {
    CameronHighlands() {
        this.name = "Cameron Highlands";
        this.landPrice = 2200000;
    }
}

class GentingHighland extends Tiles {
    GentingHighland() {
        this.name = "Genting Highland";
        this.landPrice = 2200000;
    }
}

class PahangNationalPark extends Tiles {
    PahangNationalPark() {
        this.name = "Pahang National Park";
        this.landPrice = 2600000;
    }
}

class JabatanBekalanAir extends Tiles {
    JabatanBekalanAir() {
        this.name = "Jabatan Bekalan Air";
        this.landPrice = 1500000;
    }
}

class GunungMuluNationalPark extends Tiles {
    GunungMuluNationalPark() {
        this.name = "Gunun Mulu National Park";
        this.landPrice = 2600000;
    }
}

class KinabaluNationalPark extends Tiles {
    KinabaluNationalPark() {
        this.name = "Kinabalu National Park";
        this.landPrice = 2700000;
    }
}

class GoToJail extends Tiles {
    GoToJail() {
        this.name = "Go To Jail";
    }
}

class TiomanIslands extends Tiles {
    TiomanIslands() {
        this.name = "Tioman Islands";
        this.landPrice = 3000000;
    }
}

class PerhentianIslands extends Tiles {
    PerhentianIslands() {
        this.name = "Perhentian Islands";
        this.landPrice = 3000000;
    }
}

class SepadanIslands extends Tiles {
    SepadanIslands() {
        this.name = "Sepadan Islands";
        this.landPrice = 3200000;
    }
}

class KLCC extends Tiles {
    KLCC() {
        this.name = "KLCC";
        this.landPrice = 3500000;
    }
}

class Sepang2Circuit extends Tiles {
    Sepang2Circuit() {
        this.name = "Sepang II Circuit";
        this.landPrice = 4000000;
    }
}