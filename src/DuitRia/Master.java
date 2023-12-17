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
        tile[11] = new NationalMuseum();
        tile[12] = new TenagaNationalBerhad();
        tile[13] = new RoyalPalace();
        tile[14] = new MerdekaSquare();
        tile[15] = new Station("KLIA 2");
        tile[16] = new AFamosaFort();
        tile[17] = new FateTile();
        tile[18] = new KellieCastle();
        tile[19] = new Stadthuys();
        tile[20] = new FreeParking();
        tile[21] = new FraserHill();
        tile[22] = new FateTile();
        tile[23] = new CameronHighlands();
        tile[24] = new GentingHighland();
        tile[25] = new Station("KL Sentral Station");
        tile[26] = new PahangNationalPark();
        tile[27] = new JabatanBekalanAir();
        tile[28] = new GunungMuluNationalPark();
        tile[29] = new KinabaluNationalPark();
        tile[30] = new GoToJail();
        tile[31] = new TiomanIslands();
        tile[32] = new PerhentianIslands();
        tile[33] = new FateTile();
        tile[34] = new SepadanIslands();
        tile[35] = new Station("Pudu Sentral Station");
        tile[36] = new FateTile();
        tile[37] = new KLCC();
        tile[38] = new Tax();
        tile[39] = new Sepang2Circuit();
    }
}
