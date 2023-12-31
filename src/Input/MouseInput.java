package Input;

import GUI.*;
import Pages.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MouseInput implements MouseListener{

    GamePanel gamePanel;
    StartingPage startingPage;
    
    public MouseInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.startingPage = gamePanel.startingPage;
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == startingPage.startButton)
            startingPage.startButton.setIcon(new ImageIcon("ButtonBackground/StartButton/startButtonBGHover.png"));
        
        for(int i=0 ; i<3 ; i++){
            if(e.getSource() == startingPage.playerButton[i]){
                //startingPage.playerButton[i].setIcon(new ImageIcon("ButtonBackground/StartButton/startButtonBGHover.png"));
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
    }
    
    
    
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {   
    }

    public void mouseReleased(MouseEvent e) {
    }
    
}
