package Input;

import DuitRia.*;
import GUI.*;
import Pages.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ButtonInput implements ActionListener{
    GamePanel gamePanel;
    StartingPage startingPage;
    GamePage gamePage;
    Players[] player;
    static int index = 0;
    static int votes = 0, end = 0;
    static boolean voting = false;
    public ButtonInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.startingPage = gamePanel.startingPage;
        this.gamePage = gamePanel.gamePage;
        this.player = gamePanel.player;
    }
    
    public void clearButton(){
        for(int i=0 ; i<40 ; i++){
            gamePage.remove(gamePage.sellButton[i]);
            gamePage.remove(gamePage.sellHouseButton[i]);
            gamePage.remove(gamePage.mortgageButton[i]);
            gamePage.remove(gamePage.buyButton[i]);
            gamePage.remove(gamePage.upgradeButton[i]);
        }
    }
    public void clearAll(){
        for(int i=0 ; i<40 ; i++){
            gamePage.remove(gamePage.cardsPanel[i]);
            gamePage.remove(gamePage.sellButton[i]);
            gamePage.remove(gamePage.sellHouseButton[i]);
            gamePage.remove(gamePage.mortgageButton[i]);
            gamePage.remove(gamePage.buyButton[i]);
            gamePage.remove(gamePage.upgradeButton[i]);
        }
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
                gamePage.votingLabel = new JLabel[GamePanel.playerAmount];
                gamePage.voteEnd = new JButton[GamePanel.playerAmount];
                gamePage.votePlay = new JButton[GamePanel.playerAmount];
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
        
        for(int i=0 ; i<GamePanel.playerAmount && voting ; i++){
            if(e.getSource() == gamePage.voteEnd[i]){
                gamePage.votingLabel[i].remove(gamePage.voteEnd[i]);
                gamePage.votingLabel[i].remove(gamePage.votePlay[i]);
                System.out.println("you voted to end");
                votes++;
                end++;
            }
            if(e.getSource() == gamePage.votePlay[i]){
                gamePage.votingLabel[i].remove(gamePage.voteEnd[i]);
                gamePage.votingLabel[i].remove(gamePage.votePlay[i]);
                System.out.println("you voted to play");
                votes++;
            }
            if(votes==GamePanel.playerAmount){
                votes = 0;
                gamePage.remove(gamePage.votingPanel);
                gamePage.votingPanel.removeAll();
                for(int j=0 ; j<GamePanel.playerAmount ; j++){
                    gamePage.votingLabel[i].removeAll();
                }
                if(end==GamePanel.playerAmount)
                    gamePage.endGame();
                else
                    gamePage.add(gamePage.add(gamePage.endGameButton));
                end = 0;
            }
        }
        
        for(int i=0 ; i<4 ; i++){
            if(e.getSource() == startingPage.chooseButton[i]){
                System.out.println("you pressed choose button");
                startingPage.chooseButton[i].setEnabled(false);
                gamePanel.player[index].tokenIndex = i;
                startingPage.tokenChoicePanel.remove(startingPage.playerInstruction[index]);
                index++;
                if(index<gamePanel.playerAmount)
                    startingPage.tokenChoicePanel.add(startingPage.playerInstruction[index]);
                else{
                    index = 0;
                    startingPage.remove(startingPage.tokenChoicePanel);
                    startingPage.createTurnPanel();
                    startingPage.drawDice = true;
                    startingPage.add(StartingPage.randomButton);
                    startingPage.add(StartingPage.continueButton);
                    for(int j=0 ; j<GamePanel.playerAmount ; j++){
                        startingPage.add(startingPage.tokens[GamePanel.player[j].tokenIndex]);
                    }
                }
            }
        }
        
        if(e.getSource() == startingPage.randomButton){
            startingPage.diceAni = true;
            startingPage.randomButton.setEnabled(false);
        }
        
        if(e.getSource() == startingPage.continueButton){
            gamePanel.remove(startingPage);
            startingPage.resetPage();
            gamePanel.add(gamePage);
            gamePage.createProfileLabel();
            gamePage.updateProfileLabel();
            gamePanel.resetGame();
        }
        if(e.getSource() == gamePage.rollButton){
            if(gamePanel.player[gamePanel.turn].jail){
                gamePage.remove(gamePage.prompt);
            }
            GamePage.diceAni = true;
            GamePage.rollButton.setEnabled(false);
        }
        if(e.getSource() == gamePage.endTurnButton){
            gamePage.rollButton.setEnabled(true);
            gamePage.endTurnButton.setEnabled(false);
            gamePage.remove(gamePage.prompt);
            gamePanel.turn++;
            gamePanel.turn%=gamePanel.playerAmount;gamePage.remove(gamePage.tileInfo);
            while(gamePanel.player[gamePanel.turn].bankrupt){
                gamePanel.turn++;
                gamePanel.turn%=gamePanel.playerAmount;gamePage.remove(gamePage.tileInfo);
            }
            gamePage.updateProfileLabel();
            if(gamePanel.player[gamePanel.turn].jail){
                gamePage.updateInJailPrompt();
                gamePage.add(gamePage.prompt);
            }
            gamePanel.player[gamePanel.turn].arrived = false;
            GamePage.fateCards.setIcon(new ImageIcon("CardDesign/fate 0.png"));
            clearAll();
        }
        
        if(e.getSource() == gamePage.endGameButton){
            gamePage.remove(gamePage.endGameButton);
            gamePage.createVotingPanel();
            gamePanel.setupVotingButton();
            voting = true;
            gamePage.add(gamePage.votingPanel);
        }
        
        if(e.getSource() == gamePage.outJailButton){
            Jail.getOutJail(gamePanel.player[gamePanel.turn]);
            if(!gamePanel.player[gamePanel.turn].jail){
                gamePage.moveAni = true;
                gamePage.remove(gamePage.prompt);
                gamePage.remove(gamePage.outJailButton);
                gamePage.updateProfileLabel();
            }
        }
        
        if(e.getSource() == gamePage.payButton){
            clearButton();
            if(gamePanel.tile[gamePanel.player[Fate.nowTurn].position].fate){
                gamePage.fate.getFate(gamePage.pickFate, gamePanel.player, Fate.nowTurn, gamePanel.tile, gamePage);
            }
            else if(gamePanel.tile[gamePanel.player[gamePanel.turn].position].tax)
                gamePanel.player[gamePanel.turn].payTax();
            else{
                if(gamePage.doubleRent)
                    gamePanel.player[gamePanel.turn].payDoubleRent(GamePanel.tile, GamePanel.player);
                else
                    gamePanel.player[gamePanel.turn].payRent(GamePanel.tile, GamePanel.player);
            }
            if(!GamePanel.player[GamePanel.turn].debt){
                gamePage.updateProfileLabel();
                gamePage.remove(gamePage.payButton);
                gamePage.endTurnButton.setEnabled(true);
                gamePage.doubleRent = false;
            }
        }
        
        for(int i=0 ; i<40 ; i++){
            if(e.getSource() == gamePage.tileButton[i] && !gamePanel.tile[i].tax){
                clearAll();
                System.out.println("You pressed " + gamePanel.tile[i].name);
                gamePage.updateTileInfo(i);
                gamePage.add(gamePage.tileInfo);
                gamePage.add(gamePage.cardsPanel[i]);
                gamePage.addInteractionButton(i);
                
            }
            if(e.getSource() == gamePage.closeButton[i]){
                gamePage.remove(gamePage.tileInfo);
                clearAll();
            }
            if(e.getSource() == gamePage.buyButton[i]){
                gamePanel.player[gamePanel.turn].buyProperties(gamePanel.tile[gamePanel.player[gamePanel.turn].position], gamePanel.turn);
                gamePage.updateTileInfo(i);
                clearButton();
                gamePage.addInteractionButton(i);
                gamePage.updateProfileLabel();
                gamePage.updateTileButton();
            }
            if(e.getSource() == gamePage.sellButton[i]){
                gamePanel.player[gamePanel.turn].sellProperties(gamePanel.tile[i]);
                gamePage.updateProfileLabel();
                gamePage.updateTileInfo(i);
                clearButton();
                gamePage.addInteractionButton(i);
                gamePage.updateTileButton();
            }
            if(e.getSource() == gamePage.mortgageButton[i]){
                gamePanel.player[gamePanel.turn].mortgageProperties(gamePanel.tile[i]);
                clearButton();
                gamePage.updateProfileLabel();
                gamePage.addInteractionButton(i);
                gamePage.updateTileInfo(i);
            }
            if(e.getSource() == gamePage.unmortgageButton[i]){
                gamePanel.player[gamePanel.turn].unmortgageProperties(gamePanel.tile[i]);
                clearButton();
                gamePage.updateProfileLabel();
                gamePage.addInteractionButton(i);
                gamePage.updateTileInfo(i);
            }
            if(e.getSource() == gamePage.upgradeButton[i]){
                gamePanel.player[gamePanel.turn].buyHouse(gamePanel.tile[gamePanel.player[gamePanel.turn].position]);
                gamePage.updateProfileLabel();
                gamePage.updateTileInfo(i);
                clearButton();
                gamePage.addInteractionButton(i);
                if(gamePanel.tile[i].house==4){
                    gamePage.remove(gamePage.upgradeButton[i]);
                }
            }
            
            if(e.getSource() == gamePage.sellHouseButton[i]){
                gamePanel.player[GamePanel.turn].sellHouse(gamePanel.tile[i]);
                clearButton();
                gamePage.updateProfileLabel();
                gamePage.addInteractionButton(i);
                gamePage.updateTileInfo(i);
            }
        }
        if(e.getSource() == gamePage.playAgainButton){
            gamePanel.remove(gamePage);
            gamePage.resetPage();
            gamePanel.add(startingPage);
        }
    }
}
