import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Paddle extends Rectangle
{
    
    int id;
    double yVelocity;
    int speed = 10;
    boolean lock;
    
    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id, boolean AI)
    {        
        /** Ball is a subclass of the rectangle superclass
         * (Which is the reason) Ball extends Rectangle
         * 
         * We can call the super constructors to assign these arguments for us
         */
          
        super(x,y,PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
        
        lock = AI;
    }

    public void keyPressed(KeyEvent e)
    {
        
        switch(id)
        {
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W)
                {
                    if (lock == true)
                    {                    
                       
                        return;
                    }       
                    
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    if (lock == true)
                    {                                               
                        return;
                    }
                    
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
                    if (lock == true)
                    {
                        return;
                    }
                    
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S)
                {
                    if (lock == true)
                    {
                        return;
                    }
                    
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
    
    public void setYDirection(double yDirection)
    {
        yVelocity = yDirection;
    }
    
    public void move()
    {
        y += yVelocity;
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
