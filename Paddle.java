import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Paddle extends Rectangle
{
    
    int id;
    int yVelocity;
    int speed = 10;
    
    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id)
    {
        /** Ball is a subclass of the rectangle superclass
         * (Which is the reason) Ball extends Rectangle
         * 
         * We can call the super constructors to assign these arguments for us
         */
         
        super(x,y,PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e)
    {
        switch(id)
        {
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W)
                {
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    setYDirection(speed);
                    move();
                }
                break;
                
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    setYDirection(speed);
                    move();
                }
                break;
            
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        switch(id)
        {
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W)
                {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    setYDirection(0);
                    move();
                }
                break;
                
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    setYDirection(0);
                    move();
                }
                break;
            
        }
    }
    
    public void setYDirection(int yDirection)
    {
        yVelocity = yDirection;
    }
    
    public void move()
    {
        y = y + yVelocity;
    }
    
    public void draw(Graphics g)
    {
        if (id == 1)
        {
            g.setColor(Color.white);            
        }
        
        if (id == 2)
        {
            g.setColor(Color.white);
        }
        
        g.fillRect(x, y, width, height);
        
    }
}
