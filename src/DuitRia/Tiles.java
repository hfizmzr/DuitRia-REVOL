package DuitRia;

import java.util.Scanner;

public class Tiles{
    public int house = 0, housePrice = 200000, landPrice, owner = 0;
    public boolean mortgaged = false, fate = false, tax = false, station = false, jail = false, go = false, nothing = false, goToJail = false, unowned = false;
    String name;
    public int[] rent = new int[5];
    
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
    public int getRent(){
        return rent[house];
    }
}

//Hazim: set properties for different tile and boolean to check which methods to call according to type of tile
class PetalingStreet extends Tiles{
    PetalingStreet(){
        rent[0] = 60000;
        rent[1] = 120000;
        rent[2] = 320000;
        rent[3] = 520000;
        rent[4] = 720000;
        this.name = "Petaling Street";
        this.landPrice = 600000;
    }
}
class JonkerStreet extends Tiles{
    JonkerStreet(){
        rent[0] = 60000;
        rent[1] = 120000;
        rent[2] = 320000;
        rent[3] = 520000;
        rent[4] = 720000;
        this.name = "Jonker Street";
        this.landPrice = 600000;
    }
}
class MasjidJamek extends Tiles{
    MasjidJamek(){
        rent[0] = 100000;
        rent[1] = 200000;
        rent[2] = 400000;
        rent[3] = 600000;
        rent[4] = 800000;
        this.name = "Masjed Jamek";
        this.landPrice = 1000000;
    }
}
class BatuCaves extends Tiles{
    BatuCaves(){
        rent[0] = 100000;
        rent[1] = 200000;
        rent[2] = 400000;
        rent[3] = 600000;
        rent[4] = 800000;
        this.name = "Batu Caves";
        this.landPrice = 1000000;
    }
}
class SriMahaMariammanTemple extends Tiles{
    SriMahaMariammanTemple(){
        rent[0] = 120000;
        rent[1] = 240000;
        rent[2] = 420000;
        rent[3] = 620000;
        rent[4] = 820000;
        this.name = "Sri Maha Mariamman Temple";
        this.landPrice = 1200000;
    }
}

class NationalMuseum extends Tiles {
    NationalMuseum() {
        rent[0] = 140000;
        rent[1] = 280000;
        rent[2] = 480000;
        rent[3] = 680000;
        rent[4] = 880000;
        this.name = "National Meseum";
        this.landPrice = 1400000;
    }
}

class TenagaNationalBerhad extends Tiles{
    TenagaNationalBerhad(){
        rent[0] = 150000;
        rent[1] = 300000;
        rent[2] = 500000;
        rent[3] = 700000;
        rent[4] = 800000;
        this.name = "Tenaga National Berhad";
        this.landPrice = 1500000;
    }
}

class RoyalPalace extends Tiles {
    RoyalPalace() {
        rent[0] = 140000;
        rent[1] = 280000;
        rent[2] = 480000;
        rent[3] = 680000;
        rent[4] = 880000;
        this.name = "Royal Palace";
        this.landPrice = 1400000;
    }
}

class MerdekaSquare extends Tiles {
    MerdekaSquare() {
        rent[0] = 140000;
        rent[1] = 280000;
        rent[2] = 480000;
        rent[3] = 680000;
        rent[4] = 880000;
        this.name = "Merdeka Square";
        this.landPrice = 1400000;
    }
}

class AFamosaFort extends Tiles {
    AFamosaFort() {
        rent[0] = 170000;
        rent[1] = 340000;
        rent[2] = 540000;
        rent[3] = 740000;
        rent[4] = 840000;
        this.name = "A Famosa Fort";
        this.landPrice = 1700000;
    }
}

class KellieCastle extends Tiles {
    KellieCastle() {
        rent[0] = 180000;
        rent[1] = 360000;
        rent[2] = 560000;
        rent[3] = 760000;
        rent[4] = 860000;
        this.name = "Kellie Castle";
        this.landPrice = 1800000;
    }
}

class Stadthuys extends Tiles {
    Stadthuys() {
        rent[0] = 200000;
        rent[1] = 400000;
        rent[2] = 600000;
        rent[3] = 800000;
        rent[4] = 1000000;
        this.name = "Stadthuys";
        this.landPrice = 2000000;
    }
}
    
class FraserHill extends Tiles {
    FraserHill() {
        rent[0] = 220000;
        rent[1] = 440000;
        rent[2] = 640000;
        rent[3] = 840000;
        rent[4] = 1040000;
        this.name = "Fraser's Hill";
        this.landPrice = 2200000;
    }
}

class CameronHighlands extends Tiles {
    CameronHighlands() {
        rent[0] = 220000;
        rent[1] = 440000;
        rent[2] = 640000;
        rent[3] = 840000;
        rent[4] = 1040000;
        this.name = "Cameron Highlands";
        this.landPrice = 2200000;
    }
}

class GentingHighland extends Tiles {
    GentingHighland() {
        rent[0] = 240000;
        rent[1] = 480000;
        rent[2] = 680000;
        rent[3] = 880000;
        rent[4] = 1080000;
        this.name = "Genting Highland";
        this.landPrice = 2200000;
    }
}

class PahangNationalPark extends Tiles {
    PahangNationalPark() {
        rent[0] = 260000;
        rent[1] = 520000;
        rent[2] = 720000;
        rent[3] = 920000;
        rent[4] = 1120000;
        this.name = "Pahang National Park";
        this.landPrice = 2600000;
    }
}

class JabatanBekalanAir extends Tiles {
    JabatanBekalanAir() {
        rent[0] = 150000;
        rent[1] = 300000;
        rent[2] = 500000;
        rent[3] = 700000;
        rent[4] = 800000;
        this.name = "Jabatan Bekalan Air";
        this.landPrice = 1500000;
    }
}

class GunungMuluNationalPark extends Tiles {
    GunungMuluNationalPark() {
        rent[0] = 260000;
        rent[1] = 520000;
        rent[2] = 720000;
        rent[3] = 920000;
        rent[4] = 1120000;
        this.name = "Gunun Mulu National Park";
        this.landPrice = 2600000;
    }
}

class KinabaluNationalPark extends Tiles {
    KinabaluNationalPark() {
        rent[0] = 270000;
        rent[1] = 540000;
        rent[2] = 740000;
        rent[3] = 940000;
        rent[4] = 1140000;
        this.name = "Kinabalu National Park";
        this.landPrice = 2700000;
    }
}

class TiomanIslands extends Tiles {
    TiomanIslands() {
        rent[0] = 300000;
        rent[1] = 600000;
        rent[2] = 800000;
        rent[3] = 100000;
        rent[4] = 1200000;
        this.name = "Tioman Islands";
        this.landPrice = 3000000;
    }
}

class PerhentianIslands extends Tiles {
    PerhentianIslands() {
        rent[0] = 300000;
        rent[1] = 600000;
        rent[2] = 800000;
        rent[3] = 100000;
        rent[4] = 1200000;
        this.name = "Perhentian Islands";
        this.landPrice = 3000000;
    }
}

class SepadanIslands extends Tiles {
    SepadanIslands() {
        rent[0] = 320000;
        rent[1] = 620000;
        rent[2] = 820000;
        rent[3] = 120000;
        rent[4] = 1220000;
        this.name = "Sepadan Islands";
        this.landPrice = 3200000;
    }
}

class KLCC extends Tiles {
    KLCC() {
        rent[0] = 350000;
        rent[1] = 700000;
        rent[2] = 900000;
        rent[3] = 1100000;
        rent[4] = 1230000;
        this.name = "KLCC";
        this.landPrice = 3500000;
    }
}

class Sepang2Circuit extends Tiles {
    Sepang2Circuit() {
        rent[0] = 400000;
        rent[1] = 800000;
        rent[2] = 1000000;
        rent[3] = 1200000;
        rent[4] = 1400000;
        this.name = "Sepang II Circuit";
        this.landPrice = 4000000;
    }
}
//  

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
        rent[0] = 200000;
        this.station = true;
        this.name = name;
        this.landPrice = 2000000;
    }
}

class Nothing extends Tiles{
    Nothing(String name){
        this.nothing = true;
           this.name = name;
    }
}

class GoToJail extends Tiles{
    GoToJail(){
        this.goToJail = true;
        this.name = "Go to jail!!!";
    }
}