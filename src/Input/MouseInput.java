package Input;

import GUI.*;
import Pages.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MouseInput implements MouseListener{

    GamePanel gamePanel;
    StartingPage startingPage;
    GamePage gamePage;
    
    public MouseInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.startingPage = gamePanel.startingPage;
        this.gamePage = gamePanel.gamePage;
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == startingPage.startButton)
            startingPage.startButton.setIcon(new ImageIcon("ButtonBackground/StartButton/startButtonBGHover.png"));
        
        for(int i=0 ; i<3 ; i++){
            if(e.getSource() == startingPage.playerButton[i]){
                //startingPage.playerButton[i].setIcon(new ImageIcon("ButtonBackground/StartButton/startButtonBGHover.png"));
            }
        }
        for(int i=0 ; i<40 ; i++){
            if(e.getSource() == gamePage.tileButton[i]){
                gamePage.hoverTileButton(i);
            }
        }
    }
 
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == startingPage.startButton)
            startingPage.startButton.setIcon(new ImageIcon("ButtonBackground/StartButton/startButtonBG.png"));
        
        for(int i=0 ; i<3 ; i++){
            if(e.getSource() == startingPage.playerButton[i]){
                //startingPage.playerButton[i].setIcon(new ImageIcon("ButtonBackground/StartButton/startButtonBG.png"));
            }
        }
        
        for(int i=0 ; i<40 ; i++){
            if(e.getSource() == gamePage.tileButton[i]){
                gamePage.exitTileButton(i);
            }
        }
    }
    
    
    
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {   
    }

    public void mouseReleased(MouseEvent e) {
    }
    
}
