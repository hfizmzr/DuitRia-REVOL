package Input;

import GUI.*;
import Pages.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInput implements ActionListener{
    GamePanel gamePanel;
    StartingPage startingPage;
    static int index = 0;
    public ButtonInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.startingPage = gamePanel.startingPage;
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startingPage.startButton){
            startingPage.remove(startingPage.startButton);
            for(int i=0 ; i<3 ; i++){
                startingPage.add(startingPage.playerButton[i]);
            }
        }
        
        for(int i=0 ; i<3 ; i++){
            if(e.getSource() == startingPage.playerButton[i]){
                gamePanel.playerAmount = i+2;
                for(int j=0 ; j<3 ; j++){
                    startingPage.remove(startingPage.playerButton[j]);
                }
                startingPage.createNameInputPanel();
                startingPage.add(startingPage.nameInputPanel);
            }
        }
        
        if(e.getSource() == startingPage.submitButton){
            if(startingPage.checkValidName(startingPage.nameInput)){
                startingPage.remove(startingPage.nameInputPanel);
                startingPage.createTokenChoicePanel();
                gamePanel.setupPlayerDependantButton();
                startingPage.add(startingPage.tokenChoicePanel);
            }
        }
        
        for(int i=0 ; i<4 ; i++){
            if(e.getSource() == startingPage.chooseButton[i]){
                startingPage.chooseButton[i].setEnabled(false);
                gamePanel.player[index].tokenIndex = i;
                startingPage.tokenChoicePanel.remove(startingPage.playerInstruction[index]);
                index++;
                if(index<gamePanel.playerAmount)
                    startingPage.tokenChoicePanel.add(startingPage.playerInstruction[index]);
                else{
                    gamePanel.remove(startingPage);
                }
            }
        }
        
    }
}
