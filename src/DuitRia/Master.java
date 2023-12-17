package DuitRia;

public class Master {
    public static void detectBankrupt(Players[] player){
        for(int i=0 ; i<player.length ; i++){
            player[i].bankrupt = player[i].isBankrupt();
        }
    }
    
    public static boolean play(Players[] player){//Haizm: checks if there is more than one player alive
        int alive = 0;
        
        for(int i=0 ; i<player.length ; i++){
            if(player[i].bankrupt==false)
                alive++;
        }
        return (alive>1);
    }
    
    public static void move(int turn, Players[] player, Dice dice, Tiles[] tile){
        player[turn].movement(dice);
        int position = player[turn].position;
        System.out.println(player[turn].Name + " landed on " + position + "." + tile[position].name);
        
        if(tile[position].fate){
            //fate()
        }
        else if(tile[position].tax){
            //tax()
        }
        else if(tile[position].station && tile[position].unowned){
            //buy
        }
        else if(tile[position].commodity && tile[position].unowned){
            //buy()
        }
        else if(tile[position].jail){
            //nothing happen just visiting
        }
        else if(tile[position].unowned){
            //this is a tile that player can buy house on
            //buy()
        }
        else if(tile[position].owner!=turn){
            //rent
        }
        else if(tile[position].house<4){
            //buyhouse
        }
        //ask if user want to sell or mortgage property
        //mortgage
        //sell
        
        detectBankrupt(player);
    }
    
    public static void initializeTiles(Tiles[] tile){//Hazim: create object for tiles in the array
        tile[0] = new Go();
        tile[1] = new PetalingStreet();
        tile[2] = new FateTile();
        tile[3] = new JonkerStreet();
        tile[4] = new Tax();
        tile[5] = new Station("KLIA");
        tile[6] = new MasjidJamek();
        tile[7] = new FateTile();
        tile[8] = new BatuCaves();
        tile[9] = new SriMahaMariammanTemple(); 
        tile[10] = new JailTile();
        /*tile[11]
        tile[12]
        tile[13]
        tile[14]
        tile[15]
        tile[16]
        tile[17]
        tile[18]
        tile1[9]
        tile[20]
        tile[21]
        tile[22]
        tile[23]
        tile[24]
        tile[25]
        tile[26]
        tile[27]
        tile[28]
        tile[29]
        tile[30]
        tile[31]
        tile[32]
        tile[33]
        tile[34]
        tile[35]
        tile[36]
        tile[37]
        tile[38]
        tile[39]*/
    }
}
