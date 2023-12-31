package GUI;

import javax.swing.*;

public class GameWindow extends JFrame{
    GameWindow(GamePanel panel){
        this.setTitle("Duitria");
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
