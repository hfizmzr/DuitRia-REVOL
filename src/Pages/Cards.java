package Pages;

import java.awt.*;
import javax.swing.*;

public class Cards {
    JLabel[] cards = new JLabel[40];

    public void createCardsPanel(JPanel[] cardsPanel){
        int panelWidth = 229, panelHeight = 263;
        createCards();
        for(int i=0 ; i<40 ; i++){
            cardsPanel[i] = new JPanel();
            cardsPanel[i].setBounds(600,367,panelWidth, panelHeight);
            cardsPanel[i].setOpaque(false);
            cardsPanel[i].setLayout(null);
            cardsPanel[i].add(cards[i]);
        }
    }
    
    public void createCards(){
        int cardWidth = 169, cardHeight = 233;
        for(int i=0 ; i<40 ; i++){
            
            cards[i] = new JLabel();
            cards[i].setBounds(30,30,cardWidth,cardHeight);
            cards[i].setOpaque(false);
            cards[i].setIcon(new ImageIcon("CardDesign/tile " + i + ".png"));
        }
    }
    
    public void createFateCards(JLabel fateCards){
        int cardWidth = 188, cardHeight = 234;
            fateCards.setBounds(400,397,cardWidth,cardHeight);
            fateCards.setOpaque(false);
            fateCards.setIcon(new ImageIcon("CardDesign/fate " + 0 + ".png"));
    }
}
