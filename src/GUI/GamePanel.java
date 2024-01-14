package GUI;

import DuitRia.*;
import Input.*;
import Pages.*;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel{
    public StartingPage startingPage = new StartingPage();
    public GamePage gamePage = new GamePage();
    final int Width = 1280, Height = 720;
    public static int playerAmount, turn=0;
    public static Players[] player;
    public static Jail jail = new Jail();
    int totalDiceScore;
    final static int total = 40;
    public static Dice dice = new Dice();
    public static Tiles[] tile = new Tiles[total];
    Master master = new Master();
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(Width,Height));
        this.setBackground(Color.white);
        this.setLayout(null);
        setupInitializedButton();
        this.add(startingPage);
        master.initializeTiles(tile);
    }
    
    public void setupInitializedButton(){
        startingPage.startButton.addActionListener(new ButtonInput(this));
        startingPage.startButton.addMouseListener(new MouseInput(this));
        for(int i=0 ; i<3 ; i++){
            startingPage.playerButton[i].addActionListener(new ButtonInput(this));
            startingPage.playerButton[i].addMouseListener(new MouseInput(this));
        }
        startingPage.submitButton.addActionListener(new ButtonInput(this));
        startingPage.submitButton.addMouseListener(new MouseInput(this));
        startingPage.randomButton.addActionListener(new ButtonInput(this));
        startingPage.randomButton.addMouseListener(new MouseInput(this));
        startingPage.continueButton.addActionListener(new ButtonInput(this));
        startingPage.continueButton.addMouseListener(new MouseInput(this));
        gamePage.rollButton.addActionListener(new ButtonInput(this));
        gamePage.rollButton.addMouseListener(new MouseInput(this));
        gamePage.endTurnButton.addActionListener(new ButtonInput(this));
        gamePage.endTurnButton.addMouseListener(new MouseInput(this));
        gamePage.payButton.addActionListener(new ButtonInput(this));
        gamePage.payButton.addMouseListener(new MouseInput(this));
        gamePage.outJailButton.addActionListener(new ButtonInput(this));
        gamePage.outJailButton.addMouseListener(new MouseInput(this));
        gamePage.endGameButton.addActionListener(new ButtonInput(this));
        gamePage.endGameButton.addMouseListener(new MouseInput(this));
        gamePage.playAgainButton.addActionListener(new ButtonInput(this));
        gamePage.playAgainButton.addMouseListener(new MouseInput(this));
        for(int i=0 ; i<40 ; i++){
            gamePage.tileButton[i].addActionListener(new ButtonInput(this));
            gamePage.tileButton[i].addMouseListener(new MouseInput(this));
            gamePage.closeButton[i].addActionListener(new ButtonInput(this));
            gamePage.closeButton[i].addMouseListener(new MouseInput(this));
            gamePage.buyButton[i].addActionListener(new ButtonInput(this));
            gamePage.buyButton[i].addMouseListener(new MouseInput(this));
            gamePage.sellButton[i].addActionListener(new ButtonInput(this));
            gamePage.sellButton[i].addMouseListener(new MouseInput(this));
            gamePage.sellHouseButton[i].addActionListener(new ButtonInput(this));
            gamePage.sellHouseButton[i].addMouseListener(new MouseInput(this));
            gamePage.mortgageButton[i].addActionListener(new ButtonInput(this));
            gamePage.mortgageButton[i].addMouseListener(new MouseInput(this));
            gamePage.unmortgageButton[i].addActionListener(new ButtonInput(this));
            gamePage.unmortgageButton[i].addMouseListener(new MouseInput(this));
            gamePage.upgradeButton[i].addActionListener(new ButtonInput(this));
            gamePage.upgradeButton[i].addMouseListener(new MouseInput(this));
        }
    }
    
    public void setupPlayerDependantButton(){
        for(int i=0 ; i<4 ; i++){
            startingPage.chooseButton[i].addActionListener(new ButtonInput(this));
            startingPage.chooseButton[i].addMouseListener(new MouseInput(this));
        }
    }
    
    public void setupVotingButton(){
        for(int i=0 ; i<GamePanel.playerAmount ; i++){
            System.out.println("button is set");
            gamePage.voteEnd[i].addActionListener(new ButtonInput(this));
            gamePage.voteEnd[i].addMouseListener(new MouseInput(this));
            gamePage.votePlay[i].addActionListener(new ButtonInput(this));
            gamePage.votePlay[i].addMouseListener(new MouseInput(this));
        }
    }
    
    public static void createPlayer(String[] name){
        player = new Players[playerAmount];
        for(int i=0 ; i<playerAmount ; i++){
            player[i] = new Players(name[i], 15000000);
        }
    }
    
    public void resetGame(){
        for(int i=0 ; i<40 ; i++){
            tile[i].owner = -1;
            tile[i].house = 0;
            tile[i].mortgaged = false;
            tile[i].unowned = true;
            turn = 0;
        }
    }
}
