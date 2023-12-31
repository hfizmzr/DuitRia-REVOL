package GUI;

public class Game implements Runnable{
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 120;
    
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        startGameLoop();
    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        long previousFrame = System.nanoTime();
        long currentFrame;
        double timePerFrame = 1000000000/FPS_SET;
        long previousUpdate = System.nanoTime();
        long currentUpdate;
        double timePerUpdate = 1000000000/UPS_SET;
        double DeltaF=0;
        double DeltaU=0;
        int frame = 0;
        int update = 0;
        double lastCheck = System.currentTimeMillis();
        
        while(true){
            currentFrame = System.nanoTime();
            DeltaF += (currentFrame - previousFrame)/timePerFrame;
            previousFrame = currentFrame;
            
            currentUpdate = System.nanoTime();
            DeltaU += (currentUpdate - previousUpdate)/timePerUpdate;
            previousUpdate = currentUpdate;
            
            if(DeltaF>=1){
                DeltaF--;
                gamePanel.repaint();
                gamePanel.revalidate();
                frame++;
            }
            
            if(DeltaU>=1){
                DeltaU--;
                //update();
                update++;
            }
            
                    
            if(System.currentTimeMillis() - lastCheck >=1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("\nPlayer Amount = " + gamePanel.playerAmount);
                if(!(gamePanel.player==null))
                    for(int i=0 ; i<gamePanel.playerAmount ; i++){
                        System.out.println("Player " + (i+1) + ": " + gamePanel.player[i].Name);
                        System.out.println("Player " + (i+1) + ": " + "token "+ gamePanel.player[i].tokenIndex);
                    }
                System.out.println("FPS = " + frame);
                System.out.println("UPS = " + update);
                frame = 0;
                update = 0;
            }
        }
    }
}
