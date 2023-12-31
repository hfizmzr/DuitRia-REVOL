package Pages;

import GUI.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

public class StartingPage extends JPanel{
    final static int Width = 1280, Height = 720;
    public static JButton startButton;
    public static JButton[] playerButton = new JButton[3];
    public static JPanel nameInputPanel = new JPanel();
    public static JTextField[] nameInput;
    public static JLabel[] playerInstruction;
    public static JButton submitButton = new JButton("Submit");
    public static JPanel tokenChoicePanel = new JPanel();
    public static JLabel[] tokens = new JLabel[4];
    public static JButton[] chooseButton = new JButton[4];
    
    public StartingPage(){
        this.setBounds(0,0,Width,Height);
        this.setBackground(Color.black);
        this.setLayout(null);
        createStartButton();
        createPlayerButton();
        this.add(startButton);
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
            playerInstruction[i].setBounds(100,0,200,100);
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
        tokens[0].setBounds(75,125,50,50);
        tokens[0].setBackground(Color.red);
        tokens[1].setBounds(275,125,50,50);
        tokens[1].setBackground(Color.blue);
        tokens[2].setBounds(75,325,50,50);
        tokens[2].setBackground(Color.yellow);
        tokens[3].setBounds(275,325,50,50);
        tokens[3].setBackground(Color.green);
        for(int i=0 ; i<4 ; i++){
            tokenChoicePanel.add(chooseButton[i]);
            tokenChoicePanel.add(tokens[i]);
        }
        tokenChoicePanel.add(playerInstruction[0]);
    }
}
