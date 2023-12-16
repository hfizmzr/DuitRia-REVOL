package DuitRia;

public class Master {
    public static void detectBankrupt(Players[] player, int playerAmount){
        for(int i=0 ; i<playerAmount ; i++){
            player[i].dead = player[i].isBankrupt();
        }
    }
    
    public static void move(int turn, Players[] player){
        int dice1 = 3, dice2 = 3;
        //dice roll
        
        player[turn].movement(dice1, dice2);
    }
}
