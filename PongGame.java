
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PongGame extends JFrame implements ActionListener
{
    
    JButton start, quit, instructions;
    JRadioButton leftHuman, rightHuman, leftFollowAI, rightFollowAI, leftPredictAI, rightPredictAI, ws3, ws5, ws10, ws15, ws25;
    JLabel game, leftSelect, rightSelect, scoreSelect;
    
    GameFrame frame;
    public boolean leftAI = false;
    public boolean rightAI = false;
    public int winScore = 10;
    public PongGame()
    {
        super("TurboPong");
        this.setBounds(100,100,900,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TurboPong");

        JPanel content = new JPanel(new BorderLayout());
        JPanel left = new JPanel(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new BorderLayout());
        JPanel bottom = new JPanel();
        JPanel leftRB = new JPanel();
        JPanel rightRB = new JPanel();
        JPanel centerRB = new JPanel();
        
        
        game = new JLabel("", JLabel.CENTER);
        game.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        game.setText("TurboPong");
        
        leftSelect = new JLabel("", JLabel.CENTER);
        leftSelect.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        leftSelect.setText("Choose Player 1");
        
        rightSelect = new JLabel("", JLabel.CENTER);
        rightSelect.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        rightSelect.setText("Choose Player 2");
        
        scoreSelect = new JLabel("", JLabel.CENTER);
        scoreSelect.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        scoreSelect.setText("Select win score");
        
        instructions = new JButton("Instructions");
        start = new JButton("Start");
        quit = new JButton("Quit");
        
        ButtonGroup leftGroup = new ButtonGroup();
        ButtonGroup rightGroup = new ButtonGroup();
        ButtonGroup scoreGroup = new ButtonGroup();
        
        leftHuman = new JRadioButton("Human", true);
        rightHuman = new JRadioButton("Human", true);
        //leftFollowAI = new JRadioButton("Smart Following AI");
        rightFollowAI = new JRadioButton("Invincible Following AI");
        leftPredictAI = new JRadioButton("Smart Predicting AI");
        //rightPredictAI = new JRadioButton("Smart Predicting AI");
        
        
        //Score Buttons
        ws3 = new JRadioButton("3 points");
        ws5 = new JRadioButton("5 points");
        ws10 = new JRadioButton("10 points", true);
        ws15 = new JRadioButton("15 points");
        ws25 = new JRadioButton("25 points");
        
        
        leftGroup.add(leftHuman);
        rightGroup.add(rightHuman);
        //leftGroup.add(leftFollowAI);
        rightGroup.add(rightFollowAI);
        leftGroup.add(leftPredictAI);
        //rightGroup.add(rightPredictAI);
        
        scoreGroup.add(ws3);
        scoreGroup.add(ws5);
        scoreGroup.add(ws10);
        scoreGroup.add(ws15);
        scoreGroup.add(ws25);
        
        
        
        left.add(leftSelect, BorderLayout.NORTH);
        leftRB.add(leftHuman);
        //leftRB.add(leftFollowAI);
        leftRB.add(leftPredictAI);
        left.add(leftRB, BorderLayout.CENTER);
        
        right.add(rightSelect, BorderLayout.NORTH);
        rightRB.add(rightHuman);
        rightRB.add(rightFollowAI);
        //rightRB.add(rightPredictAI);
        right.add(rightRB, BorderLayout.CENTER);
        
        center.add(scoreSelect, BorderLayout.NORTH);
        centerRB.add(ws3);
        centerRB.add(ws5);
        centerRB.add(ws10);
        centerRB.add(ws15);
        centerRB.add(ws25);
        center.add(centerRB, BorderLayout.CENTER);
        
        bottom.add(instructions);
        bottom.add(start);
        bottom.add(quit);
        
        content.add(game, BorderLayout.NORTH);
        content.add(left, BorderLayout.WEST);
        content.add(right, BorderLayout.EAST);
        content.add(center, BorderLayout.CENTER);
        
        content.add(bottom, BorderLayout.SOUTH);
        
        instructions.addActionListener(this);
        start.addActionListener(this);
        quit.addActionListener(this);
        
        leftHuman.addActionListener(this);
        rightHuman.addActionListener(this);
        //leftFollowAI.addActionListener(this);
        rightFollowAI.addActionListener(this);
        leftPredictAI.addActionListener(this);
        //rightPredictAI.addActionListener(this);
        
        ws3.addActionListener(this);
        ws5.addActionListener(this);
        ws10.addActionListener(this);
        ws15.addActionListener(this);
        ws25.addActionListener(this);
        
        
        this.setContentPane(content);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new PongGame();
    }
    
    public void actionPerformed (ActionEvent e)
    {
        
        if(e.getSource() == start)
        {
            frame = new GameFrame(leftAI, rightAI, winScore);
        }
        
        else if (e.getSource() == quit)
        {
            this.dispose();
            System.exit(0);
        }
        else if (e.getSource() == instructions)
        {
            JOptionPane.showMessageDialog(null, "TURBO PONG is an extended version of the classic arcade \"PONG\" Game featuring the \"TURBO\" \r\nArtificial Intelligence that predicts every single move you make. \r\nIn addition to a user friendly interface, TURBO PONG includes Player vs Player, AI vs Player, and an \r\nextra special AI vs AI mode to make your jaw drop. \r\n\r\nStill not feeling excited??? Then, you are as calm as the \"TURBO\" Artificial Intelligence in the game. :)\r\n\r\nControls for Player 1(Human):\r\n\"W\" key for going up and \"S\" key for going down\r\nControls for Player 2(Human):\r\n\"↑\" arrow key for going up and \"↓\" arrow key for going down", "Instructions", JOptionPane.PLAIN_MESSAGE);
        }
        else if (e.getSource() == leftHuman)
        {
            leftAI = false;
        }        
        else if (e.getSource() == leftPredictAI)
        {
            leftAI = true;
        }
        else if (e.getSource() == rightHuman)
        {
            rightAI = false;
        }
        else if (e.getSource() == rightFollowAI)
        {
            rightAI = true;
        }        
        else if (e.getSource() == ws3)
        {
            winScore = 3;
        }
        else if (e.getSource() == ws5)
        {
            winScore = 5;
        }
        else if (e.getSource() == ws10)
        {
            winScore = 10;
        }
        else if (e.getSource() == ws15)
        {
            winScore = 15;
        }
        else if (e.getSource() == ws25)
        {
            winScore = 25;
        }
        
    }
}
