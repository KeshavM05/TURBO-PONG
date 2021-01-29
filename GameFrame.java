import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class GameFrame extends JFrame
{

    GamePanel panel;
    
    public GameFrame()
    {
        super();
        panel  = new GamePanel();
        this.add(panel);
        
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //N
        this.pack();
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
