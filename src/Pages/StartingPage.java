package Pages;

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

public class StartingPage extends JPanel{
    final static int Width = 1280, Height = 720;
    private Image gameBackground = new ImageIcon("Images/GameBackground.jpg").getImage();
    public static JButton startButton;
    public static JButton[] playerButton = new JButton[3];
    public static JPanel nameInputPanel = new JPanel();
    public static JTextField[] nameInput;
    public static JLabel[] playerInstruction;
    public static JButton submitButton = new JButton("Submit");
    public static JPanel tokenChoicePanel = new JPanel();
    public static JPanel turnPanel = new JPanel();
    public static JLabel[] tokens = new JLabel[4];
    public static JButton[] chooseButton = new JButton[4];
    public static JButton randomButton = new JButton("Roll");
    private static int tick = 0, diceSpeed = 12, diceLimit = 0;
    public static JButton continueButton = new JButton("Continue");
    public static ImageIcon token0 = new ImageIcon("TokenDesign/Big Token 0.png");
    public static ImageIcon token1 = new ImageIcon("TokenDesign/Big Token 1.png");
    public static ImageIcon token2 = new ImageIcon("TokenDesign/Big Token 2.png");
    public static ImageIcon token3 = new ImageIcon("TokenDesign/Big Token 3.png");
    public static Image[] token = new Image[4];
    public static int[] tokenWidth = {105, 106, 100, 96};
    public static int[] tokenHeight = {86, 92, 86, 101};
    public static int[] dice1 = {0,0,0,0};
    public static int[] dice2 = {0,0,0,0};
    public static boolean drawDice = false, diceAni = false, result = false;
    public static Random rand = new Random();
    public static int[] totalDiceScore = new int[4];
    private int tempInt;
    private String tempString;
    BufferedImage diceSprite;
    BufferedImage[] diceAnimation;
    
    public StartingPage(){
        this.setBounds(0,0,Width,Height);
        this.setBackground(Color.black);
        this.setLayout(null);
        createStartButton();
        createPlayerButton();
        createRandomButton();
        createContinueButton();
        createToken();
        importImage();
        loadAnimation();
        this.add(startButton);
    }
    
    public static void createToken(){
        for(int i=0 ; i<4 ; i++){
            token[i] = new ImageIcon("TokenDesign/Big Token " + i + ".png").getImage();
        }
    }
    
    public static void createTurnPanel(){
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            tokens[GamePanel.player[i].tokenIndex].setBounds(100, 60*(i+1) + 100*i, tokenWidth[GamePanel.player[i].tokenIndex], tokenHeight[GamePanel.player[i].tokenIndex]);
        }
    }
    
    public static void createRandomButton(){
        int buttonWidth = 100;
        int buttonHeight = 50;
        randomButton.setBounds(600,200,buttonWidth,buttonHeight);
        randomButton.setBackground(Color.blue);
        randomButton.setBorderPainted(false);
    }
    
    public static void createContinueButton(){
        int buttonWidth = 100;
        int buttonHeight = 50;
        continueButton.setBounds(600,300,buttonWidth,buttonHeight);
        continueButton.setBackground(Color.blue);
        continueButton.setBorderPainted(false);
        continueButton.setEnabled(false);
    }
    
    public static void createStartButton(){
        int buttonWidth = 290;
        int buttonHeight = 120;
        Icon icon = new ImageIcon("ButtonBackground/StartButton/startButtonBG.png");
        startButton = new JButton(icon);
        startButton.setBackground(Color.blue);
        startButton.setBounds((Width-buttonWidth)/2,(Height-buttonHeight)/2,buttonWidth, buttonHeight);
        startButton.setOpaque(false);
        startButton.setBorderPainted(false);
    }
    
    public static void createPlayerButton(){
        int buttonWidth = 200;
        int buttonHeight = 100;
        for(int i=0 ; i<3 ; i++){
            playerButton[i] = new JButton(Integer.toString(i+2) + " Players");
            playerButton[i].setBounds((Width-buttonWidth)/2,90+(80*i)+(100*i),buttonWidth,buttonHeight);
            playerButton[i].setBackground(Color.blue);
            playerButton[i].setBorderPainted(false);
        }
    }
    
    public static void createNameInputPanel(){
        int panelHeight = 70+(GamePanel.playerAmount*80);
        int panelWidth = 400;
        
        playerInstruction = new JLabel[GamePanel.playerAmount];
        nameInput = new JTextField[GamePanel.playerAmount];
        nameInputPanel.setBounds((Width-panelWidth)/2,(Height-panelHeight)/2,400, 70 + (GamePanel.playerAmount*80));
        nameInputPanel.setOpaque(false);
        nameInputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
        submitButton.setPreferredSize(new Dimension(300,50));
        submitButton.setBackground(Color.blue);
        submitButton.setBorderPainted(false);
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            playerInstruction[i] = new JLabel();
            playerInstruction[i].setForeground(Color.white);
            playerInstruction[i].setText("Enter your name player " + (i+1));
            playerInstruction[i].setPreferredSize(new Dimension(300,30));
            nameInput[i] = new JTextField();
            nameInput[i].setPreferredSize(new Dimension(300,30));
            nameInputPanel.add(playerInstruction[i]);
            nameInputPanel.add(nameInput[i]);
        }
        nameInputPanel.add(submitButton);
    }
    
    public static boolean checkValidName(JTextField[] nameInput){
        boolean valid = true;
        String[] name = new String[GamePanel.playerAmount];
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            name[i] = nameInput[i].getText();
        }
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            if(name[i].equals(""))
                valid = false;
            for(int j=i+1 ; j<GamePanel.playerAmount ; j++){
                if(name[i].equalsIgnoreCase(name[j])){
                    valid = false;
                }
            }
        }
        if(valid)
                GamePanel.createPlayer(name);
        return valid;
    }
    
    public static void createTokenChoicePanel(){
        int panelWidth = 400;
        int panelHeight = 500;
        tokenChoicePanel.setBounds((Width-panelWidth)/2,(Height-panelHeight)/2,panelWidth,panelHeight);
        tokenChoicePanel.setOpaque(false);
        tokenChoicePanel.setLayout(null);
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            playerInstruction[i].setText(GamePanel.player[i].Name + ". Choose your token.");
            playerInstruction[i].setBounds(100,0,200,25);
            playerInstruction[i].setHorizontalAlignment(JLabel.CENTER);
            playerInstruction[i].setBackground(Color.blue);
            playerInstruction[i].setOpaque(true);
        }
        for(int i=0 ; i<4 ; i++){
            chooseButton[i] = new JButton("Choose");    
            chooseButton[i].setBackground(Color.blue);
            chooseButton[i].setBorderPainted(false);
            tokens[i] = new JLabel();
            tokens[i].setOpaque(true);
        }
        chooseButton[0].setBounds(50,200,100,50);
        chooseButton[1].setBounds(250,200,100,50);
        chooseButton[2].setBounds(50,400,100,50);
        chooseButton[3].setBounds(250,400,100,50);
        tokens[0].setBounds(55,105,105,86);
        tokens[0].setIcon(token0);
        tokens[0].setOpaque(false);
        tokens[1].setBounds(255,100,106,92);
        tokens[1].setIcon(token1);
        tokens[1].setOpaque(false);
        tokens[2].setBounds(55,305,100,86);
        tokens[2].setIcon(token2);
        tokens[2].setOpaque(false);
        tokens[3].setBounds(255,290,96,101);
        tokens[3].setIcon(token3);
        tokens[3].setOpaque(false);
        for(int i=0 ; i<4 ; i++){
            tokenChoicePanel.add(chooseButton[i]);
            tokenChoicePanel.add(tokens[i]);
        }
        tokenChoicePanel.add(playerInstruction[0]);
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
    
    private void importImage(){
        InputStream is = getClass().getResourceAsStream("/DiceAnimation.png");
        try {
            diceSprite = ImageIO.read(is);
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void drawResult(Graphics g){
        if(result){
            for(int i=0 ; i<GamePanel.playerAmount ; i++){
                int width = tokenWidth[GamePanel.player[i].tokenIndex];
                int height = tokenHeight[GamePanel.player[i].tokenIndex];
                g.drawImage(token[GamePanel.player[i].tokenIndex],1000,60*(i+1)+height*i,width,height,null);
            }
        }
    }
    
    public void animateDice(){
        if(diceAni){
            tick++;
            if(tick>=diceSpeed){
                tick = 0;
                diceLimit++;
                for(int i=0 ; i<GamePanel.playerAmount ; i++){
                    dice1[i] = rand.nextInt(6);
                    dice2[i] = rand.nextInt(6);
                }
            }
            if(diceLimit==10){
                diceAni = false;
                continueButton.setEnabled(true);
                result = true;
                diceLimit = 0;
                for(int i=0 ; i<GamePanel.playerAmount ; i++){
                    totalDiceScore[i] = dice1[i] + dice2[i] + 2;
                }
                for(int i=0 ; i<GamePanel.playerAmount-1 ; i++){
                    for(int j=0 ; j<GamePanel.playerAmount-1 ; j++){
                        if(totalDiceScore[j]<totalDiceScore[j+1]){
                            tempInt = totalDiceScore[j];
                            totalDiceScore[j] = totalDiceScore[j+1];
                            totalDiceScore[j+1] = tempInt;
                            tempInt = GamePanel.player[j].tokenIndex;
                            GamePanel.player[j].tokenIndex = GamePanel.player[j+1].tokenIndex;
                            GamePanel.player[j+1].tokenIndex = tempInt;
                            tempString = GamePanel.player[j].Name;
                            GamePanel.player[j].Name = GamePanel.player[j+1].Name;
                            GamePanel.player[j+1].Name = tempString;
                        }
                    }
                }
            }
        }
    }
    
    public void resetPage(){
        drawDice = false;
        this.removeAll();
        this.add(startButton);
        result = false;
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            nameInputPanel.remove(nameInput[i]);
        }
        tokenChoicePanel.removeAll();
        randomButton.setEnabled(true);
        continueButton.setEnabled(false);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(gameBackground,0,0,null);
        animateDice();
        drawResult(g);
        if(drawDice){
            for(int i=0 ; i<GamePanel.playerAmount ; i++){
                g.drawImage(diceAnimation[dice1[i]],250 , 45 + 165*i,100,100,null);
                g.drawImage(diceAnimation[dice2[i]],360 , 45 + 165*i,100,100,null);
            }
        }
        repaint();
    }
}
