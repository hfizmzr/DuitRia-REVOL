package DuitRia;

public class Master {
    public static void detectBankrupt(Players[] player, int playerAmount){
        for(int i=0 ; i<playerAmount ; i++){
            player[i].dead = player[i].isBankrupt();
        }
    }
    
    public static void move(int turn, Players[] player, Dice dice, Tiles[] tile){
        player[turn].movement(dice);
        int position = player[turn].position;
        System.out.println(player[turn].Name + " landed on " + position + "." + tile[position].name);
        
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
        tile[15] = new Station("KLIA 2");
        tile[16]
        tile[17] = new FateTile();
        tile[18]
        tile1[9]
        tile[20]
        tile[21]
        tile[22] = new FateTile();
        tile[23]
        tile[24]
        tile[25] = new Station("KL SENTRAL STATION");
        tile[26]
        tile[27]
        tile[28]
        tile[29]
        tile[30]
        tile[31]
        tile[32]
        tile[33] = new FateTile();
        tile[34]
        tile[35] = new Station("PUDU SENTRL STATION");
        tile[36] = new FateTile();
        tile[37]
        tile[38]
        tile[39]*/
    }
}
