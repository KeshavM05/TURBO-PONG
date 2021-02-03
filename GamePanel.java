
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
    public boolean LeftAI;
    public boolean RightAI;
    public int WinScore;

    public GamePanel(boolean leftAI, boolean rightAI, int winScore)
    {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        //N
        this.setFocusable(true);
        //N
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        LeftAI = leftAI;
        RightAI = rightAI;
        WinScore = winScore;
        
        gameThread = new Thread(this);
        gameThread.start();
        
    }

    public void newBall()
    {
        random = new Random();
        //ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT/2) - (BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
        ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
        LeftAI_target = setLeftAITarget();
        //RightAI_target = setRightAITarget();

    }

    public void newPaddles()
    {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1, LeftAI, RightAI);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2, LeftAI, RightAI);
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
        //g.setColor(Color.red);
        //g.fillOval(PADDLE_WIDTH+BALL_DIAMETER/2,(int)AI_target,5,5);
        
    }

    public void move()
    {
        paddle1.move();
        //paddle2.y=ball.y-PADDLE_HEIGHT/2;
        paddle2.move();
        if(RightAI)
        {
            paddle2.y=ball.y-PADDLE_HEIGHT/2;
        }
        ball.move();
        
    }

    private double LeftAI_target;
    private boolean LeftAI_set=false;
    private double setLeftAITarget()
    {
        double x=ball.x;
        double y=ball.y;
        double dx=ball.xVelocity;
        double dy=ball.yVelocity;
        while (x>0)
        {
            if (dy<0)
            {
                x-=Math.abs(y/dy*dx);
                y=0;
                dy=-dy;
            } else {
                x-=Math.abs((GAME_HEIGHT-y)/dy*dx);
                y=GAME_HEIGHT;
                dy=-dy;
            }
        }
        if (dy<0)
        {
            y=GAME_HEIGHT+x/dx*dy;
        } else {
            y=x/dx*dy;
        }
        return y;
    }
    
    public void EnableLeftAI()
    {
        //if(ball.xVelocity < GAME_WIDTH / 2 && ball.yVelocity < (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2))
        //{

        if (!LeftAI_set)
        {
            LeftAI_target = setLeftAITarget();
            LeftAI_set=true;
        }

        double LeftAIlocation = 0;
        if(ball.xVelocity > 0)
        {
            LeftAI_set=false;
            LeftAI_target=GAME_HEIGHT/2;

        }
        if (paddle1.y == LeftAI_target-PADDLE_HEIGHT/2)
        {
            LeftAIlocation = 0;
        }
        else if(paddle1.y < LeftAI_target-PADDLE_HEIGHT/2)
        {
            LeftAIlocation = 3;
        }
        else 
        {
            LeftAIlocation = -3;
        }

        //if (AIlocation > 0)
        //    AIlocation = 3;
        //else if (AIlocation < 0)
        //    AIlocation = -3;
        paddle1.setYDirection(LeftAIlocation);
        //System.out.println(paddle1.y);
        //}
    }

    
    //private double RightAI_target;
    //private boolean RightAI_set=false;
    //private double setRightAITarget()
    //{
    //    double x=ball.x;
    //    double y=ball.y;
    //    double dx=ball.xVelocity;
    //    double dy=ball.yVelocity;
    //    while (x>0)
    //    {
    //        if (dy<0)
    //        {
    //            x-=Math.abs(y/dy*dx);
    //            y=0;
    //            dy=-dy;
    //        } else {
    //            x-=Math.abs((GAME_HEIGHT-y)/dy*dx);
    //            y=GAME_HEIGHT;
    //            dy=-dy;
    //        }
    //    }
    //    if (dy<0)
    //    {
    //        y=GAME_HEIGHT+x/dx*dy;
    //    } else {
    //        y=x/dx*dy;
    //    }
    //    return y;
    //}
    
    public void EnableRightAI()
    {
        //if(ball.xVelocity < GAME_WIDTH / 2 && ball.yVelocity < (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2))
        //{
    
        //if (!RightAI_set)
        //{
        //    RightAI_target = setRightAITarget();
        //    RightAI_set=true;
        //}
       
        double RightAI_target = ball.y;
        double RightAIlocation = 0;
        if(ball.xVelocity < 0)
        {
            //RightAI_set=false;
            RightAI_target=GAME_HEIGHT/2;
    
        }
        if (paddle2.y == RightAI_target-PADDLE_HEIGHT/2)
        {
            RightAIlocation = 0;
        }
        else if(paddle2.y < RightAI_target-PADDLE_HEIGHT/2 && ball.x > GAME_WIDTH/2)
        {
            RightAIlocation = 15;
        }
        else if(paddle2.y > RightAI_target-PADDLE_HEIGHT/2 && ball.x > GAME_WIDTH/2)
        {
            RightAIlocation = -15;
        }
    
        //if (AIlocation > 0)
        //    AIlocation = 3;
        //else if (AIlocation < 0)
        //    AIlocation = -3;
        paddle2.setYDirection(RightAIlocation);
        //System.out.println(paddle2.y);
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
        
        // To check for paddles and ball collision
        //if(ball.intersects(paddle1) && ball.xVelocity<0)
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
        //if(ball.intersects(paddle2) && ball.xVelocity>0)
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
            //if (ball.xVelocity<0)
            //{
            //    ball.xVelocity=0;
            //    ball.yVelocity=0;
            //}
        }

        // To register points and create new ball when ball goes beyond the paddles
        if (ball.x <=0)
        {
            score.player2++;
            newPaddles();
            newBall();
            //System.out.println("Player 2: " + score.player2);
            
            if (score.player2 >= WinScore)
            {
                repaint();
                JOptionPane.showMessageDialog(null, "Player 2 Wins by " + (score.player2 - score.player1) + " points!");
                System.exit(1);
            }
        }
        if (ball.x >= (GAME_WIDTH - BALL_DIAMETER))
        {
            score.player1++;
            newPaddles();
            newBall();
            //System.out.println("Player 1: " + score.player1);
            
            if (score.player1 >= WinScore)
            {
                repaint();
                JOptionPane.showMessageDialog(null, "Player 1 Wins by " + (score.player1 - score.player2) + " points!");
                System.exit(1);
            }
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
                if(LeftAI)// && ball.x < GAME_WIDTH/2)
                {
                    EnableLeftAI();
                    //EnableRightAI();
                }
                //if(RightAI)// && ball.x > GAME_WIDTH/2)
                //{
                    //EnableLeftAI();
                //    EnableRightAI();
                //}

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
