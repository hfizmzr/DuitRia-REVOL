package DuitRia;

import java.util.Random;

public class Master {
    Jail jail = new Jail();
    Random rand = new Random();
    Fate fate = new Fate();
    public void detectBankrupt(Players[] player){
        for(int i=0 ; i<player.length ; i++){
            player[i].bankrupt = player[i].isBankrupt();
        }
    }
    
    public boolean play(Players[] player){//Haizm: checks if there is more than one player alive
        int alive = 0;
        
        for(int i=0 ; i<player.length ; i++){
            if(player[i].bankrupt==false)
                alive++;
        }
        return (alive>1);
    }
    
    public void move(int turn, Players[] player, Dice dice, Tiles[] tile){        
            
        player[turn].movement(dice, jail);
        if(player[turn].jail){
            detectBankrupt(player);
            return;
        }
        int position = player[turn].position;
        //int fateIndex = rand.nextInt(10)+1;
        int fateIndex = 2;
        System.out.println(player[turn].Name + " landed on " + position + "." + tile[position].name);
        System.out.println("Round = " + player[turn].round);
        if(player[turn].round<1)
            return;
        do{
            position = player[turn].position;
            
            player[turn].debt = false;
            if(tile[position].nothing);
            else if(tile[position].fate){
                fate.getFate(fateIndex, player, turn, tile, jail);
            }
            else if(tile[position].tax){
                player[turn].payTax();
            }
            else if(tile[position].station){
                if(tile[position].unowned)
                    player[turn].buyProperties(tile[position], turn);
                else if(tile[position].mortgaged == false)
                    player[turn].payRent(tile, player);
            }
            else if(tile[position].goToJail){
                jail.goToJail(player[turn]);
            }
            else if(tile[position].unowned){
                player[turn].buyProperties(tile[position], turn);
            }
            else if(tile[position].owner!=turn){
                if(tile[position].mortgaged == false)
                    player[turn].payRent(tile, player);
            }
            else if(tile[position].house<4){
                if(player[turn].round>=3)
                    player[turn].buyHouse(tile[position]);
            }
            player[turn].mortgageProperties();
            player[turn].unmortgageProperties();
            player[turn].sellProperties();
        }while(player[turn].debt);
        System.out.println(player[turn].Name + " Balance: RM" + player[turn].Balance);
        System.out.println(player[turn].Name + " netWorth: RM" + player[turn].netWorth);
        detectBankrupt(player);
    }
    
    public static void initializeTiles(Tiles[] tile){//Hazim: create object for tiles in the array
        tile[0] = new Nothing("Go");
        tile[1] = new PetalingStreet();
        tile[2] = new FateTile();
        tile[3] = new JonkerStreet();
        tile[4] = new Tax();
        tile[5] = new Station("KLIA", false);
        tile[6] = new MasjidJamek();
        tile[7] = new FateTile();
        tile[8] = new BatuCaves();
        tile[9] = new SriMahaMariammanTemple(); 
        tile[10] = new Nothing("Visiting jail");
        tile[11] = new NationalMuseum();
        tile[12] = new TenagaNationalBerhad();
        tile[13] = new RoyalPalace();
        tile[14] = new MerdekaSquare();
        tile[15] = new Station("KLIA 2", false);
        tile[16] = new AFamosaFort();
        tile[17] = new FateTile();
        tile[18] = new KellieCastle();
        tile[19] = new Stadthuys();
        tile[20] = new Nothing("Free Parking");
        tile[21] = new FraserHill();
        tile[22] = new FateTile();
        tile[23] = new CameronHighlands();
        tile[24] = new GentingHighland();
        tile[25] = new Station("KL Sentral Station", true);
        tile[26] = new PahangNationalPark();
        tile[27] = new JabatanBekalanAir();
        tile[28] = new GunungMuluNationalPark();
        tile[29] = new KinabaluNationalPark();
        tile[30] = new GoToJail();
        tile[31] = new TiomanIslands();
        tile[32] = new PerhentianIslands();
        tile[33] = new FateTile();
        tile[34] = new SepadanIslands();
        tile[35] = new Station("Pudu Sentral Station", true);
        tile[36] = new FateTile();
        tile[37] = new KLCC();
        tile[38] = new Tax();
        tile[39] = new Sepang2Circuit();
    }
}
