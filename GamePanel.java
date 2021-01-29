import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable
{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));      
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);  
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    public boolean AI;
    
    public GamePanel(boolean AVP)
    {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        //N
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        
        AI = AVP;
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void newBall()
    {
        random = new Random();
        //ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT/2) - (BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
        ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }
    
    public void newPaddles()
    {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1, AI);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2, AI);
    }
    
    public void paint(Graphics g)
    {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    
    public void draw(Graphics g)
    {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    
    public void move()
    {
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    
    public void EnableAI()
    {
        //if(ball.xVelocity < GAME_WIDTH / 2 && ball.yVelocity < (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2))
        //{
        
        double AIlocation = ball.yVelocity;
        //if (AIlocation > 0)
        //    AIlocation = 3;
        //else if (AIlocation < 0)
        //    AIlocation = -3;
        paddle1.setYDirection(AIlocation);
        //}
    }
    
    public void checkCollision()
    {
        // To prevent paddles from going beyond the window
        if (paddle1.y <= 0)
        {
            paddle1.y = 0;

        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
        {
            paddle1.y = (GAME_HEIGHT - PADDLE_HEIGHT);
        }
        
        if (paddle2.y <= 0)
        {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
        {
            paddle2.y = (GAME_HEIGHT - PADDLE_HEIGHT);
        }
        
        // To check for paddles and ball collision
        if(ball.intersects(paddle1))
        {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // optional for more difficulty
            if(ball.yVelocity > 0)
            {
                ball.yVelocity ++ ; // optional for more difficulty
            }
            else
            {                
                ball.yVelocity --; // optional for more difficulty
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        
        if(ball.intersects(paddle2))
        {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // optional for more difficulty
            if(ball.yVelocity > 0)
            {
                ball.yVelocity ++ ; // optional for more difficulty
            }
            else
            {                
                ball.yVelocity --; // optional for more difficulty
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        
        // To prevent ball from going beyond the top and bottom part of the window        
        if (ball.y <= 0)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= (GAME_HEIGHT - BALL_DIAMETER))
        {
            ball.setYDirection(-ball.yVelocity);
        }
        
        // To register points and create new ball when ball goes beyond the paddles
        if (ball.x <=0)
        {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }
        if (ball.x >= (GAME_WIDTH - BALL_DIAMETER))
        {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player1);
        }
    }
    
    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        
        while(true)
        {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1)
            {
                move();
                if(AI && ball.x < GAME_WIDTH / 2)
                {
                    EnableAI();
                }
                
                checkCollision();
                repaint();
                delta--;                
            }
        }
    }
    
    public class AL extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        
        public void keyReleased(KeyEvent e)
        {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);        
        }
    }
}
