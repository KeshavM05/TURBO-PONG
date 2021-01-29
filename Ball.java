import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Ball extends Rectangle
{
    
    Random random;
    double xVelocity;
    double yVelocity;
    int initialSpeed = 5;
    
    
    public Ball(int x, int y, int width, int height)
    {
        /** Ball is a subclass of the rectangle superclass
         * (Which is the reason) Ball extends Rectangle
         * 
         * We can call the super constructors to assign these arguments for us
         */
               
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);// + 1;        
        if (randomXDirection == 0)
        {
            randomXDirection--;            
        }
        setXDirection(randomXDirection * initialSpeed);
        
        int randomYDirection = random.nextInt(2);// + 1;        
        if (randomYDirection == 0)
        {
            randomYDirection--;            
        }
        setYDirection(randomYDirection * initialSpeed);
               
    }

    public void setXDirection(double randomXDirection)
    {
        xVelocity = randomXDirection;
    }
    
    public void setYDirection(double randomYDirection)
    {
        yVelocity = randomYDirection;
    }
    
    public void move()
    {
        x += xVelocity;
        y += yVelocity;
    }
    
    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }   
    
}
