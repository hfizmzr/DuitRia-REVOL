package GUI;

import DuitRia.*;
import Input.*;
import Pages.*;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel{
    public StartingPage startingPage = new StartingPage();
    final int Width = 1280, Height = 720;
    public static int playerAmount;
    public static Players[] player;
    
    GamePanel(){
        this.setPreferredSize(new Dimension(Width,Height));
        this.setBackground(Color.white);
        this.setLayout(null);
        setupInitializedButton();
        this.add(startingPage);
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
    }
    
    public void setupPlayerDependantButton(){
        for(int i=0 ; i<4 ; i++){
            startingPage.chooseButton[i].addActionListener(new ButtonInput(this));
            startingPage.chooseButton[i].addMouseListener(new MouseInput(this));
        }
    }
    
    public static void createPlayer(String[] name){
        player = new Players[playerAmount];
        for(int i=0 ; i<playerAmount ; i++){
            player[i] = new Players(name[i], 15000000);
        }
    }
}
