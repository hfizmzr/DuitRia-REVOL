package DuitRia;

public class Players {
    
    public int money = 15000000, netWorth = 15000000;
    boolean dead = false;
    public int movement(int position, int dice1, int dice2){
            position+=dice1+dice2;
            if(position>=40){
                position%=10;
                salary();
            }
        return position;
    }
    
    public void salary(){
        System.out.println("You just past GO(Collect RM 2M)");
        money+=2000000;
        netWorth+=2000000;
        System.out.println("Your money is RM " + money);
        System.out.println("Your net worth is RM " + netWorth);
    }
    
    public boolean isBankrupt(){
        return (netWorth<0);
    }
}
