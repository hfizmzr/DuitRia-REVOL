package Pages;

import DuitRia.*;
import GUI.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePage extends JPanel{
    final static int Width = 1280, Height = 720;
    private Image gameBackground = new ImageIcon("Images/GameBackground.jpg").getImage();
    private Image gameBoard = new ImageIcon("Images/GameBoard.png").getImage();
    private Dice dice = new Dice();
    private Jail jail = new Jail();
    private Image token0 = new ImageIcon("TokenDesign/token 0.png").getImage();
    private Image token1 = new ImageIcon("TokenDesign/token 1.png").getImage();
    private Image token2 = new ImageIcon("TokenDesign/token 2.png").getImage();
    private Image token3 = new ImageIcon("TokenDesign/token 3.png").getImage();
    public static JButton rollButton = new JButton("Roll");
    public static JButton endTurnButton = new JButton("End Turn");
    public Fate fate = new Fate();
    BufferedImage diceSprite;
    BufferedImage[] diceAnimation;
    public static int[] x = {895, 960, 925, 933};
    public static int[] y = {647, 622, 680, 655};
    public static int dice1, dice2, distance, moved = 0, moveSpeed = 1;
    private static int tick = 0, diceSpeed = 12, dice1Index = 0, dice2Index = 0, diceLimit = 0, fateSpeed = 12, fateLimit = 0;
    public static int direction = 1, pickFate, debt;
    public static int[] tokenPosition = {0, 0, 0, 0};
    public static boolean diceAni = false, moveAni = false, fateAni = false, doubleRent = false;
    public static JButton[] tileButton = new JButton[40];
    public static JLabel prompt = new JLabel();
    public static JLabel tileInfo = new JLabel();
    public static JPanel[] cardsPanel = new JPanel[40];
    public static Cards cards = new Cards();
    public static JButton[] closeButton = new JButton[40];
    public static JButton[] buyButton = new JButton[40];
    public static JButton[] upgradeButton = new JButton[40];
    public static JButton[] sellButton = new JButton[40];
    public static JButton[] sellHouseButton = new JButton[40];
    public static JButton[] mortgageButton = new JButton[40];
    public static JButton[] unmortgageButton = new JButton[40];
    public static JButton payButton = new JButton("Pay");
    public static JButton outJailButton = new JButton("Get out of jail");
    public static JLabel[] playerProfileLabel;
    public static JLabel fateCards = new JLabel();
    public static JPanel votingPanel = new JPanel();
    public static JPanel endGamePanel = new JPanel();
    public static JLabel winnerLabel = new JLabel();
    public static JLabel[] votingLabel;
    public static JButton[] voteEnd;
    public static JButton[] votePlay;
    public static JButton playAgainButton = new JButton("Play Again");
    public static JButton endGameButton = new JButton("Press to end the game");
    public static Color[] color = {Color.red, Color.pink, Color.green, Color.yellow};
    Random rand = new Random();
    
    public GamePage(){
        this.setBounds(0,0,Width,Height);
        this.setBackground(Color.black);
        this.setLayout(null);
        createRollButton();
        createEndTurnButton();
        createPayButton();
        createTileButton();
        createCloseButton();
        createTileInfo();
        createInteractionButton();
        createOutJailButton();
        createEndGameButton();
        cards.createFateCards(fateCards);
        cards.createCardsPanel(cardsPanel);
        for(int i=0 ; i<40 ; i++)
            cardsPanel[i].add(closeButton[i]);
        createPrompt();
        importImage();
        loadAnimation();
        this.add(rollButton);
        this.add(endTurnButton);
        this.add(fateCards);
        this.add(endGameButton);
        for(int i=0 ; i<40 ; i++){
            this.add(tileButton[i]);
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(gameBackground,0,0,null);
        g.drawImage(gameBoard,(1280-720)/2,0,null);
        drawToken(g);
        animateDice();
        animateFate();
        move();
        g.drawImage(diceAnimation[dice1Index],1030,50,93,100,null);
        g.drawImage(diceAnimation[dice2Index],1140,50,93,100,null);
        repaint();
    }
    
    public void drawToken(Graphics g){
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            switch(GamePanel.player[i].tokenIndex){
                case 0:
                    g.drawImage(token0,x[0],y[0],null);
                    break;
                case 1:
                    g.drawImage(token1,x[1],y[1],null);
                    break;
                case 2:
                    g.drawImage(token2,x[2],y[2],null);
                    break;
                case 3:
                    g.drawImage(token3, x[3], y[3], null);
                    break;
            }            
        }
    }
    
    public void createInteractionButton(){
        for(int i=0 ; i<40 ; i++){
            buyButton[i] = new JButton("Buy");
            upgradeButton[i] = new JButton("Upgrade");
            sellButton[i] = new JButton("Sell");
            sellHouseButton[i] = new JButton("Sell House");
            mortgageButton[i] = new JButton("Mortgage");
            unmortgageButton[i] = new JButton("Unmortgage");
            buyButton[i].setBounds(1040,430,200,50);
            buyButton[i].setBackground(Color.blue);
            upgradeButton[i].setBounds(1040,490,200,50);
            upgradeButton[i].setBackground(Color.blue);
            sellButton[i].setBounds(1040,550,200,50);
            sellButton[i].setBackground(Color.blue);
            sellHouseButton[i].setBounds(1040,550,200,50);
            sellHouseButton[i].setBackground(Color.blue);
            mortgageButton[i].setBounds(1040,610,200,50);
            mortgageButton[i].setBackground(Color.blue);
            unmortgageButton[i].setBounds(1040,610,200,50);
            unmortgageButton[i].setBackground(Color.blue);
        }
    }
    
    public void addInteractionButton(int index){
        if(!GamePanel.tile[index].fate && !GamePanel.tile[index].nothing && !GamePanel.tile[index].tax){
            if(GamePanel.tile[index].unowned){
                if(GamePanel.player[GamePanel.turn].round>0 && GamePanel.player[GamePanel.turn].position == index && GamePanel.player[GamePanel.turn].arrived && GamePanel.player[GamePanel.turn].Balance >= GamePanel.tile[index].landPrice){
                    this.add(buyButton[index]);
                }
            }
            else{
                if(GamePanel.turn == GamePanel.tile[index].owner){
                    if(GamePanel.tile[index].house==0){
                        if(GamePanel.tile[index].mortgaged){
                            int unmortgageAmount = GamePanel.tile[index].landPrice/2 + (GamePanel.tile[index].landPrice/2)/10;
                            if(GamePanel.player[GamePanel.turn].Balance >= unmortgageAmount)
                                this.add(unmortgageButton[index]);
                        }
                        else{    
                            this.add(sellButton[index]);
                            this.add(mortgageButton[index]);
                        }
                    }
                    if(GamePanel.tile[index].house>0){
                        this.add(sellHouseButton[index]);
                    }
                    if(GamePanel.player[GamePanel.turn].round>=3 && !GamePanel.tile[index].station && GamePanel.player[GamePanel.turn].Balance >= GamePanel.tile[index].housePrice && GamePanel.tile[index].house<4 && !GamePanel.tile[index].mortgaged){
                        this.add(upgradeButton[index]);
                    }
                }
            }
        }
    }
    
    public static void createPlayAgainButton(){
        int buttonWidth = 200, buttonHeight = 100;
        playAgainButton.setBounds((540-buttonWidth)/2,340,buttonWidth,buttonHeight);
        playAgainButton.setBackground(Color.blue);
    }
    
    public void createVotingPanel(){
        int panelWidth = 540, panelHeight = 310;
        votingPanel.setBounds((Width-panelWidth)/2, 90, panelWidth, panelHeight);
        votingPanel.setBackground(Color.blue);
        votingPanel.setLayout(null);
        
        for(int i=0 ; i<votingLabel.length ; i++){
            votingLabel[i] = new JLabel();
            votingLabel[i].setLayout(null);
            voteEnd[i] = new JButton("End");
            votePlay[i] = new JButton("Play");
            voteEnd[i].setBounds(130,80,100,50);
            votePlay[i].setBounds(130,20,100,50);
            voteEnd[i].setBorderPainted(false);
            votePlay[i].setBorderPainted(false);
            voteEnd[i].setBackground(Color.red);
            votePlay[i].setBackground(Color.green);
            switch(i){
                case 0:
                    votingLabel[0].setBounds(0,0,270,155);
                    break;
                case 1:    
                    votingLabel[1].setBounds(0,155,270,155);
                    break;
                case 2:
                    votingLabel[2].setBounds(270,0,270,155);
                    break;
                case 3:    
                    votingLabel[3].setBounds(270,155,270,155);
                    break;
            }
            votingLabel[i].setIcon(new ImageIcon("TokenDesign/Big Token " + GamePanel.player[i].tokenIndex + ".png"));
            votingLabel[i].setVerticalAlignment(JLabel.CENTER);
            votingLabel[i].setHorizontalAlignment(JLabel.LEFT);
            votingLabel[i].add(votePlay[i]);
            votingLabel[i].add(voteEnd[i]);
            votingPanel.add(votingLabel[i]);
        }
    }
    
    public void createEndGamePanel(){
        int panelWidth = 540, panelHeight = 540, max = Integer.MIN_VALUE, winner= 0;
        endGamePanel.setBounds((Width-panelWidth)/2, (Height-panelHeight)/2, panelWidth, panelHeight);
        endGamePanel.setBackground(Color.white);
        endGamePanel.setLayout(null);
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            if(GamePanel.player[i].netWorth>max){
                max = GamePanel.player[i].netWorth;
                winner = i;
            }
        }
        System.out.println(winner);
        System.out.println(GamePanel.player[winner].Name);
        createWinnerLabel(winner);
        endGamePanel.add(winnerLabel);
        createPlayAgainButton();
        endGamePanel.add(playAgainButton);
    }
    
    public void createWinnerLabel(int winner){
        int labelWidth = 540, labelHeight = 340;
        int index = GamePanel.player[winner].tokenIndex;
        winnerLabel.setBounds(0,0,labelWidth,labelHeight);
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        winnerLabel.setVerticalAlignment(JLabel.CENTER);
        winnerLabel.setIcon(new ImageIcon("TokenDesign/Big Token " + index + ".png"));
        winnerLabel.setText(GamePanel.player[winner].Name + " wins the game!!!");
        winnerLabel.setHorizontalTextPosition(JLabel.CENTER);
        winnerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        winnerLabel.setBackground(Color.white);
        winnerLabel.setFont(new Font(null,Font.BOLD,20));
    }
    
    public void endGame(){
        for(int i=0 ; i<40 ; i++){
            tileButton[i].setEnabled(false);
        }
        this.remove(fateCards);
        rollButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        createEndGamePanel();
        this.add(endGamePanel);
    }
    
    public void createPayButton(){
        int buttonWidth = 200;
        int buttonHeight = 100;
        payButton.setBounds((Width-buttonWidth)/2,210,buttonWidth,buttonHeight);
        payButton.setBackground(Color.blue);
        payButton.setBorderPainted(false);
    }
    
    public void createEndGameButton(){
        int buttonWidth = 200;
        int buttonHeight = 60;
        endGameButton.setBounds((Width-buttonWidth)/2,320,buttonWidth,buttonHeight);
        endGameButton.setBackground(Color.blue);
        endGameButton.setBorderPainted(false);
    }
    
    public void createOutJailButton(){
        int buttonWidth = 200;
        int buttonHeight = 100;
        outJailButton.setBounds((Width-buttonWidth)/2,210,buttonWidth,buttonHeight);
        outJailButton.setBackground(Color.blue);
        outJailButton.setBorderPainted(false);
    }
    
    public void createProfileLabel(){
        playerProfileLabel = new JLabel[GamePanel.playerAmount];
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            playerProfileLabel[i] = new JLabel();
            playerProfileLabel[i].setBounds(40,(20*(i+1))+(155*i),200,155);
            playerProfileLabel[i].setBackground(Color.blue);
            playerProfileLabel[i].setOpaque(true);
            playerProfileLabel[i].setText("<HTML>" + GamePanel.player[i].Name + "<BR>Money: RM" + GamePanel.player[i].Balance +"<BR>NetWorth: RM" + GamePanel.player[i].netWorth + "</HTML>");
            playerProfileLabel[i].setIcon(new ImageIcon("TokenDesign/Big Token " + GamePanel.player[i].tokenIndex + ".png"));
            this.add(playerProfileLabel[i]);
        }
    }
    
    public static void updateProfileLabel(){
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            playerProfileLabel[i].setBackground(Color.blue);
            playerProfileLabel[i].setText("<HTML>Name: " + GamePanel.player[i].Name + "<BR>Money: RM" + GamePanel.player[i].Balance +"<BR>NetWorth: RM" + GamePanel.player[i].netWorth + "</HTML>");
            playerProfileLabel[i].setBorder(BorderFactory.createLineBorder(color[i], 4));
        }
        
        playerProfileLabel[GamePanel.turn].setBackground(Color.green);
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            if(GamePanel.player[i].bankrupt)
                playerProfileLabel[i].setBackground(Color.red);
        }
    }
    
    public void createTileInfo(){
        tileInfo = new JLabel();
        tileInfo.setBounds(1040,300,200,100);
        tileInfo.setIcon(new ImageIcon("ButtonBackground/TileInfoBackground.png"));
        tileInfo.setHorizontalAlignment(JLabel.CENTER);
        tileInfo.setOpaque(true);
        tileInfo.setBackground(Color.blue);
    }
    
    public void createCloseButton(){
        ImageIcon closeButtonBackground = new ImageIcon("ButtonBackground/CloseButton.png");
        for(int i=0 ; i<40 ; i++){
            closeButton[i] = new JButton(closeButtonBackground);
            closeButton[i].setBackground(Color.red);
            closeButton[i].setOpaque(false);
            closeButton[i].setContentAreaFilled(false);
            closeButton[i].setBorderPainted(false);
            closeButton[i].setBounds(0,0,30,30);
        }
    }
    
    public void createRollButton(){
        int buttonWidth = 200;
        int buttonHeight = 50;
        rollButton.setBounds(1040,170,buttonWidth,buttonHeight);
        rollButton.setBackground(Color.blue);
        rollButton.setBorderPainted(false);
    }
    
    public void createEndTurnButton(){
        int buttonWidth = 200;
        int buttonHeight = 50;
        endTurnButton.setBounds(1040,230,buttonWidth,buttonHeight);
        endTurnButton.setBackground(Color.blue);
        endTurnButton.setBorderPainted(false);
        endTurnButton.setEnabled(false);
    }
    
    public void createTileButton(){
        for(int i=0 ; i< 40 ; i++){
            tileButton[i] = new JButton();
            tileButton[i].setOpaque(false);
            tileButton[i].setBorder(BorderFactory.createLineBorder(Color.black, 2));
            tileButton[i].setContentAreaFilled(false);
            tileButton[i].setBorderPainted(true);
        }
        
        for(int i=1 ; i<10 ; i++){
            tileButton[i].setBounds(370 + (60*(9-i)),630,60,90);
        }
        for(int i=11 ; i<20 ; i++){
            tileButton[i].setBounds(280,90 + (60*(19-i)),90,60);
        }
        for(int i=21 ; i<30 ; i++){
            tileButton[i].setBounds(370 + (60*(i-21)),0,60,90);
        }
        for(int i=31 ; i<40 ; i++){
            tileButton[i].setBounds(910,90 + (60*(i-31)),90,60);
        }
    }
    
    private void importImage(){
        InputStream is = getClass().getResourceAsStream("/DiceAnimation.png");
        try {
            diceSprite = ImageIO.read(is);
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadAnimation(){
        diceAnimation = new BufferedImage[6];
        
        for(int i=0 ; i<diceAnimation.length ; i++){
            if(i<3)
                diceAnimation[i] = diceSprite.getSubimage(196*i, 0, 196, 196);
            else
                diceAnimation[i] = diceSprite.getSubimage(196*(i-3), 225, 196, 196);
        }
    }
    
    public void animateDice(){
        if(diceAni){
            tick++;
            if(tick>=diceSpeed){
                tick = 0;
                dice1Index = rand.nextInt(6);
                dice2Index = rand.nextInt(6);
                diceLimit++;
            }
            if(diceLimit==10){
                GamePanel.player[GamePanel.turn].movement(dice, jail);
                diceAni = false;
                diceLimit=0;
                dice1Index = dice1;
                dice2Index = dice2;
                if(GamePanel.player[GamePanel.turn].jail){
                    if(dice1==dice2){
                        updateRollDoublePrompt();
                        GamePanel.player[GamePanel.turn].jail = false;
                        this.add(prompt);
                        moveAni = true;
                        
                    }
                    else{
                        Jail.getOutJail(GamePanel.player[GamePanel.turn]);
                        updateProfileLabel();
                    }
                }
                if(!GamePanel.player[GamePanel.turn].jail){
                    moveAni = true;
                }
                else{
                    rollButton.setEnabled(false);
                    updateDebtPrompt(250000);
                    moveAni = false;
                    this.add(prompt);
                    this.add(outJailButton);
                }
            }
        }
    }
    
    public void animateFate(){
        if(fateAni){
            tick++;
            endTurnButton.setEnabled(false);
            if(tick>=fateSpeed){
                tick = 0;
                fateCards.setIcon(new ImageIcon("CardDesign/fate " + (rand.nextInt(10)+1) + ".png"));
                fateLimit++;
            }
            if(fateLimit==10){
                pickFate = rand.nextInt(10)+1;
                fateCards.setIcon(new ImageIcon("CardDesign/fate " + pickFate + ".png"));
                fateAni = false;
                fateLimit = 0;
                Fate.nowTurn = GamePanel.turn;
                endTurnButton.setEnabled(true);
                if(pickFate==3){
                    for(int i=0 ; i<GamePanel.playerAmount ; i++){
                        if(i!=GamePanel.turn)
                            GamePanel.player[i].debt = true;
                    }
                }
                fate.getFate(pickFate,GamePanel.player, GamePanel.turn, GamePanel.tile, this);
                Master.detectBankrupt(GamePanel.player);
                if(!Master.play(GamePanel.player)){
                    endGame();
                }
                updateProfileLabel();
            }
        }
    }
    public void move(){
        if(GamePanel.player[GamePanel.turn].bankrupt){
            endTurnButton.setEnabled(true);
            rollButton.setEnabled(false);
        }
        if(moveAni){
            tick++;
            int token = GamePanel.player[GamePanel.turn].tokenIndex;
            int side = tokenPosition[token]/10;
            if(direction == 1){
                switch (tokenPosition[token]) {
                    case 0:
                    case 29:
                        if(token == 0 || token == 2)
                            distance = 70;
                        else
                            distance = 80;
                        break;
                    case 10:
                    case 39:
                        if(token == 0 || token == 1)
                            distance = 70;
                        else
                            distance = 80;
                        break;
                    case 20:
                    case 9:
                        if(token == 3 || token == 1)
                            distance = 70;
                        else
                            distance = 80;
                        break;
                    case 30:
                    case 19:
                        if(token == 3 || token == 2)
                            distance = 70;
                        else
                            distance = 80;
                        break;
                    default:
                        distance = 60;
                        break;
                }
            }
            else{
                if(tokenPosition[token]%40==0)
                    side = (tokenPosition[token]/10 + 3)%4;
                switch (tokenPosition[token]) {
                    case 1:
                    case 30:
                        if(token == 0 || token == 2)
                            distance = 70;
                        else
                            distance = 80;
                        break;
                    case 11:
                    case 0:
                        if(token == 0 || token == 1)
                            distance = 70;
                        else
                            distance = 80;
                        break;
                    case 21:
                    case 10:
                        if(token == 3 || token == 1)
                            distance = 70;
                        else
                            distance = 80;
                        break;
                    case 31:
                    case 20:
                        if(token == 3 || token == 2)
                            distance = 70;
                        else
                            distance = 80;
                        break;
                    default:
                        distance = 60;
                        break;
                }
            }
            
            if(tick>=moveSpeed){
                tick = 0;
                switch (side) {
                    case 0 -> x[token]-= 1*direction;
                    case 1 -> y[token] -= 1*direction;
                    case 2 -> x[token] += 1*direction;
                    case 3 -> y[token] += 1*direction;
                }
                moved++;
            }
            
            if(moved==distance){
                moved = 0;
                tokenPosition[token] += (1*direction);
                if(direction == 1){
                    tokenPosition[token] %=40;
                    if(tokenPosition[token]==0){
                        if(!GamePanel.player[GamePanel.turn].jail){
                            updateSalaryPrompt();
                            updateProfileLabel();
                            this.add(prompt);
                        }
                    }
                }
                else if(tokenPosition[token] == -1)
                    tokenPosition[token] = 39;
                    
            }
            if(tokenPosition[token] == GamePanel.player[GamePanel.turn].position){
                direction = 1;
                moveAni = false;
                endTurnButton.setEnabled(true);
                GamePanel.player[GamePanel.turn].arrived = true;
                
                if(GamePanel.tile[tokenPosition[token]].fate){
                    fateAni = true;
                    if(GamePanel.player[GamePanel.turn].debt){
                        endTurnButton.setEnabled(false);
                        updateFateDebtPrompt(pickFate);
                        this.add(prompt);
                        this.add(payButton);
                    }
                }
                
                else if(GamePanel.tile[tokenPosition[token]].tax){
                    GamePanel.player[GamePanel.turn].payTax();
                    if(GamePanel.player[GamePanel.turn].debt){
                        endTurnButton.setEnabled(false);
                        updateDebtPrompt(2000000);
                        this.add(prompt);
                        this.add(payButton);
                    }
                }
                
                else if(GamePanel.tile[tokenPosition[token]].goToJail){
                    moveAni = true;
                    endTurnButton.setEnabled(false);
                    GamePanel.player[GamePanel.turn].arrived = false;
                    Jail.goToJail(GamePanel.player[GamePanel.turn]);
                    updateJailPrompt();
                    this.add(prompt);
                }
                
                else if(!GamePanel.tile[tokenPosition[token]].unowned && GamePanel.tile[tokenPosition[token]].owner != GamePanel.turn && !GamePanel.tile[tokenPosition[token]].mortgaged){
                    if(doubleRent){
                        GamePanel.player[GamePanel.turn].payDoubleRent(GamePanel.tile, GamePanel.player);
                        debt = GamePanel.tile[tokenPosition[token]].getRent(GamePanel.tile)*2;
                    }
                    else{
                        GamePanel.player[GamePanel.turn].payRent(GamePanel.tile, GamePanel.player);
                        debt = GamePanel.tile[tokenPosition[token]].getRent(GamePanel.tile);
                    }
                    if(GamePanel.player[GamePanel.turn].debt){
                        endTurnButton.setEnabled(false);
                        updateDebtPrompt(debt);
                        this.add(prompt);
                        this.add(payButton);
                    }
                    else{
                        doubleRent = false;
                        this.add(prompt);
                    }
                }
                Master.detectBankrupt(GamePanel.player);
                if(!Master.play(GamePanel.player)){
                    endGame();
                }
                updateProfileLabel();
            }
        }
    }
    
    public static void createPrompt(){
        int labelWidth = 200;
        int labelHeight = 100;
        prompt.setBounds((Width-labelWidth)/2,100,labelWidth,labelHeight);
        prompt.setBackground(Color.blue);
        prompt.setOpaque(true);
        prompt.setHorizontalAlignment(JLabel.CENTER);
    }
    
    public static void updateSalaryPrompt(){
        prompt.setText("<HTML>" + GamePanel.player[GamePanel.turn].Name + ",<BR> you received RM2000000</HTML>");
    }
    
    public static void updateDebtPrompt(int debt){
        prompt.setText("<HTML>" + GamePanel.player[GamePanel.turn].Name + " you need to pay RM" + debt + "<BR>Sell your properties</HTML>");
    }
    public static void updatePayRentPrompt(int rent){
        prompt.setText("<HTML>" + GamePanel.player[GamePanel.turn].Name + " payed RM" + rent + 
                "<BR>to " + GamePanel.player[GamePanel.tile[GamePanel.player[GamePanel.turn].position].owner].Name + "</HTML>");
    }
    public static void updateJailPrompt(){
        prompt.setText(GamePanel.player[GamePanel.turn].Name + ", GO TO JAILL!!!");
    }
    
    public static void updateInJailPrompt(){
        prompt.setText("<HTML>     You are in jail<BR>Roll twice or pay RM250000 to get out</HTML>");
    }
    
    public static void updateTaxPrompt(){
        prompt.setText("<HTML>" + GamePanel.player[GamePanel.turn].Name + " You payed RM2000000 of tax </HTML>");
    }
    
    public static void updateRollDoublePrompt(){
        prompt.setText("You rolled double");
    }
    public static void updateFateDebtPrompt(int pickFate){
        switch(pickFate){
            case 7:
                updateDebtPrompt(GamePanel.player[GamePanel.turn].totalHouse * 200000);
                break;
            case 8:
                updateDebtPrompt(250000);
                break;
            case 9:
                updateDebtPrompt(100000);
                break;
            case 10:
                updateDebtPrompt(100000);
                break;
            
        }
    }
    
    public static void updateTileInfo(int index){
        if(GamePanel.tile[index].unowned)
            tileInfo.setText("Unowned");
        else if(GamePanel.tile[index].mortgaged){
            tileInfo.setText("<HTML>Owner: " + GamePanel.player[GamePanel.tile[index].owner].Name + 
                    "<BR>Mortgaged</HTML>");
        }
        else{
            tileInfo.setText("<HTML>Owner: " + GamePanel.player[GamePanel.tile[index].owner].Name + 
                    "<BR>Houses: " + GamePanel.tile[index].house + "</HTML>");
        }
    }
    
    public static void updateTileButton(){
        for(int i=0 ; i<40 ; i++){
            switch(GamePanel.tile[i].owner){
                case 0:
                    tileButton[i].setBorder(BorderFactory.createLineBorder(Color.red, 2));
                    break;
                case 1:
                    tileButton[i].setBorder(BorderFactory.createLineBorder(Color.pink, 2));
                    break;
                case 2:
                    tileButton[i].setBorder(BorderFactory.createLineBorder(Color.green, 2));
                    break;
                case 3:
                    tileButton[i].setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
                    break;
                default:
                    tileButton[i].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    break;    
            }
        }
    }
    public static void hoverTileButton(int tileIndex){
        switch(GamePanel.tile[tileIndex].owner){
            case 0:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.red, 4));
                break;
            case 1:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.pink, 4));
                break;
            case 2:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.green, 4));
                break;
            case 3:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.yellow, 4));
                break;
            default:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.black, 4));
                break;    
        }
    }
    public static void exitTileButton(int tileIndex){
        switch(GamePanel.tile[tileIndex].owner){
            case 0:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.red, 2));
                break;
            case 1:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.pink, 2));
                break;
            case 2:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.green, 2));
                break;
            case 3:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
                break;
            default:
                tileButton[tileIndex].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                break;    
        }
    }
    
    public void resetPage(){
        x[0] = 895;
        x[1] = 960;
        x[2] = 925;
        x[3] = 933;
        y[0] = 647;
        y[1] = 622;
        y[2] = 680;
        y[3] = 655;
        
        for(int i=0 ; i<4 ; i++)
            tokenPosition[i] = 0;

        this.remove(endGamePanel);
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            this.remove(playerProfileLabel[i]);
        }
        endGamePanel.removeAll();
        this.add(endGameButton);
        this.add(fateCards);
        rollButton.setEnabled(true);
        endTurnButton.setEnabled(false);
        endGamePanel.removeAll();
        for(int i=0 ; i<40 ; i++){
            tileButton[i].setEnabled(true);
        }
    }
}
