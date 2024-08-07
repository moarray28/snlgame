import java.awt.*;
import javax.swing.*;

import javax.imageio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import java.awt.event.*;
import java.io.File;
import java.io.IOException;


class gameframe extends JFrame
{   
gamepanel jp1;
Image icon;
Toolkit tk;
Dimension screenSize;

    gameframe()
    {
        jp1 =new gamepanel();

        
        add(jp1);
    
        setTitle("Snakes & Ladders");
        icon = Toolkit.getDefaultToolkit().getImage("assets/logo.png");  
        setIconImage(icon);  

        tk=Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        setSize(screenSize.width,screenSize.height);
       //setSize(1200,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible( true);
       
    }
}
 
            
 class gamepanel extends JPanel implements ActionListener
{

    Image board,player1,player2,bgimg,logoImage;
    JLabel title,msg,mtitle1,mtitle2;
    ImageIcon dice1,dice2,dice3,dice4,dice5,dice6;
    int  p1score=1, x1 =1, y1 =0;
    int p2score =1 , x2= 1,y2 =0;
    int turn =1,s =0,d =0 ;
    boolean strt =false,nostrt =true;
    JButton diceButton,newgameButton,quitButton;
    JTextArea messagebox,p1name,p2name,p1scr,p2scr;
    

    

                          // from  0 to  positive x  direction
                          // 0   1   2   3   4  5   6   7   8   9   
        public int xpos[]={105,160,215,270,325,380,435,490,545,600,600,545,490,435,380,325,270,215,160,105};
                            //                                     10  11  12  13  14  15  16  17  18  19  
                            //                                    from postive x  to 0 direction
                                           

                            //from 0 to positive y direction 
                            //0   1    2  3   4   5   6  7   8  9
        public int ypos[]={650,595,540,485,430,375,320,265,210,155};

                //                    0  1  2  3  4  5  6    7  8  9  10 11
          public  int snkmo_ldrup[]= {21,27,47,56,74,90,99,  23,32,61,65,75};  //snake mouth and ladder postion to go up
        public int snktl_ldrdown[]=  {2,15,29,37,46,52,41,   42,51,79,84,96}; // snake tail and ladder position to go down

            String[] snakemessages={
                                                    
                "Sorry, you got a bit too close to \n the snake. Back you go!",             
                "The snake's got you, I'm afraid.\nTime to go back down.",
                "Don't feel too bad, it happens to \n the best of us. Time for a do-over!",
                "Back to square one for you, but don't \ngive up hope yet!",
                "The snake may have taken you down a notch, but there's \n  still plenty of game left to play.",
                "That pesky snake has set you back,\n but keep your chin up and keep going.",
               "Unlucky roll - looks like a snake \n is taking you down again.",
               "You might be feeling down now,\n but remember, it's all part of the game.",
                "The snake's got you in its clutches,\n but there are still opportunities to catch up.",
                "It's okay to feel frustrated, but keep \n pushing forward despite the setback.",
                "Looks like you'll have to work \n a little harder to make up lost ground.",
                "The snake may have bitten hard, \n but you can come back stronger than ever.",
                "A tough break for sure but \n don't let it get you down.",
                "Back to where you started from \nbut don't forget how far you've come already!",
                "Definitely not your lucky turn, but \n keep your head high and keep playing.",
                "Losing ground is never easy but chin up, \n there's still lots of game left to play.",
                "You might feel like you're slipping behind,\n but you can always make a comeback.",
                "Setbacks happen, but they don't  \n define the game - keep moving forward.",
                "A disappointment for sure, but don't \n lose sight of the fun in this game!"
                                        };

            String laddermessages[]=
            {
                "Whoa! Way to go up !", 
                "Congratulations, you've climbed higher!",
               "You're making great progress!",
               " Keep up the good work!",
                "Fantastic! You're on a roll.",
                " Incredible! You're moving up!",
               "You've reached new heights!",
                "Amazing job, you're crushing it!",
               "Woohoo! You're climbing the ladder of success!",
               " You must be feeling pretty proud right now!",
               " Wow, you're really taking control of the board!",
               " Impressive moves, keep it up!",
               " You're unstoppable!",
               " A real winner in the making!",
               " You're doing everything right!",
               " You've got this in the bag!",
               " Keep aiming high, you're doing great!",
               " Great work, you're an expert ladder climber!",
               " You're flying high and on top of the game!"
            };

    gamepanel()
    {
        //setBackground(new Color(50,249,150));
        setLayout(null);
        try{
        bgimg = ImageIO.read(new File("assets/bgimage.jpg"));          // background image
        
        logoImage =ImageIO.read(new File("assets/logo.png"));          //logo image
        
        board = ImageIO.read(new File("assets/board.png"));           //board image
        
        player1 =ImageIO.read(new File("assets/player 1.png"));         //player 1 image
        
        player2 =ImageIO.read(new File("assets/player 2.png"));         //player 2 image
        }
        catch(IOException e4)
        {}
        
    //main title
        //upper title
        mtitle1 = new JLabel("Snakes &");
        mtitle1.setBounds(365,130,520,150);
        mtitle1.setForeground(Color.decode("#6E260E"));
        mtitle1.setFont(new Font("Papyrus",Font.BOLD,115));
        add(mtitle1);
    
        //lower title
        mtitle2 = new JLabel("Ladders");
        mtitle2.setBounds(585,270,520,150);
        mtitle2.setForeground(Color.decode("#6E260E"));
        mtitle2.setFont(new Font("Papyrus",Font.BOLD,115));
        add(mtitle2);
      
    //header
        //title
        title =new JLabel("Snakes & Ladders");
        title.setForeground(Color.decode("#E1AF33"));
        title.setBounds(40,26,750,85);
        title.setFont(new Font("papyrus",Font.PLAIN,80));
        title.setVisible(strt);
        add(title);

    //player info
        //textarea for player 1
        p1name = new JTextArea("Player 1");
        p1name.setBounds(760,210,200,50);
        p1name.setFont(new Font("Papyrus",Font.PLAIN,30));
        p1name.setBackground(Color.decode("#1b1c1e"));
        p1name.setForeground(Color.decode("#E1AF33"));
        p1name.setVisible(strt);
        add(p1name);


        //textarea for player 2
        p2name = new JTextArea("Player 2");
        p2name.setBounds(760,280,200,50);
        p2name.setFont(new Font("Papyrus",Font.PLAIN,30));
        p2name.setBackground(Color.decode("#1b1c1e"));
        p2name.setForeground(Color.decode("#E1AF33"));
        p2name.setVisible(strt);
        add(p2name);

    

    //messagebox
            messagebox =new JTextArea("~~ Welcome to Snakes and Ladders Game ~~ \n :)");
            messagebox.setBounds(705,530,500,140);
            messagebox.setBackground( Color.decode("#1b1c1e"));
            messagebox.setForeground(Color.decode( "#E1AF33"));
            messagebox.setFont(new Font("Papyrus",Font.BOLD,24));
            messagebox.setVisible(strt);
            messagebox.setEditable( false);
            add(messagebox);
        
    //buttons 
        //dice button
        dice1 =new ImageIcon("assets/dice1.png");
        diceButton = new JButton(dice1);

        diceButton.setBounds(700,400,80,80);
        diceButton.setBackground(Color.decode("#20ada4") );
        diceButton.setVisible(strt);
        add(diceButton);

        diceButton.addActionListener(this);

        //new game    
         newgameButton = new JButton("New Game");
         newgameButton.setBounds(1120,200,195,70);
         newgameButton.setForeground(Color.decode("#E1AF33"));
         newgameButton.setFont(new Font("Papyrus",Font.BOLD,30));
         newgameButton.setBackground(Color.decode( "#1b1c1e") );
         add(newgameButton);
        
         newgameButton.addActionListener(this);

       //quit
        quitButton = new JButton("Quit");
        quitButton.setBounds(1120,300,195,65);
        quitButton.setForeground(Color.decode("#E1AF33"));
        quitButton.setBackground(Color.decode( "#1b1c1e"));
        quitButton.setFont(new Font("Papyrus",Font.BOLD,30));
        add(quitButton);

        quitButton.addActionListener(this);

    //score box
        
        //scorebox for player 1
        p1scr =new JTextArea("1");
        p1scr.setForeground(Color.decode("#E1AF33"));
        p1scr.setBackground(Color.decode( "#1b1c1e"));
        p1scr.setBounds(980,210,53,50);
        p1scr.setFont(new Font("Papyrus",Font.PLAIN,29));
        p1scr.setVisible(strt);
        p1scr.setEditable( false);
        add(p1scr); 

        //scorebox for player 2
        p2scr =new JTextArea("1");
        p2scr.setForeground(Color.decode("#E1AF33"));
        p2scr.setBackground(Color.decode( "#1b1c1e"));
        p2scr.setBounds(980,280,53,50);
        p2scr.setFont(new Font("papyrus",Font.PLAIN,29));
        p2scr.setVisible(strt);
        p2scr.setEditable( false);
        add(p2scr); 
     }

public void actionPerformed(ActionEvent e ) 
{
    if(e.getSource() == newgameButton)
    {
        strt =true;
       
        messagebox.setVisible(strt);
        diceButton.setVisible(strt);
        nostrt =false;
        
        newgameButton.setText("Restart");
        title.setVisible(strt);
        mtitle1.setVisible(nostrt);
        mtitle2.setVisible(nostrt);

        p1name.setVisible(strt);
        p1name.setText("Player 1");

        p2name.setVisible(strt);
        p2name.setText("Player 2");

        p1scr.setVisible(strt);
        p1scr.setText("1");

        p2scr.setVisible(strt);
        p2scr.setText("1");
        
        messagebox.setForeground(Color.decode("#E1AF33"));
        messagebox.setText("~~ Welcome to Snakes and Ladders Game ~~ \n :)");
        turn =1;
        p1score = 1;
        p2score = 1;
        x1 = 1; y1 = 0; x2 =1; y2 =0;
        

       
    }
    
    if(e.getSource()== quitButton)
    {
        System.exit(0);
    }    

    if(e.getSource() == diceButton)
    {
        

            try
            {
                
               String file1 = "assets/diceroll.wav"; 
            File file = new File(file1).getAbsoluteFile();
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            }
            catch(Exception e4)
            { 
                e4.getStackTrace();
            }          
            d = (int) (6*Math.random()) + 1;
            switch(d)
            {               
                case 1:
                dice1 =new ImageIcon("assets/dice1.png");
                diceButton.setIcon(dice1);
                break;
                
                case 2:
                dice2 =new ImageIcon("assets/dice2.png");
                diceButton.setIcon(dice2);
                break;
                
                case 3:
                dice3 =new ImageIcon("assets/dice3.png");
                diceButton.setIcon(dice3);
                break;
                
                case 4:
                dice4 =new ImageIcon("assets/dice4.png");
                diceButton.setIcon(dice4);
                break;
                
                case 5:
                dice5 =new ImageIcon("assets/dice5.png");
                diceButton.setIcon(dice5);
                break;
                
                case 6:
                dice6 =new ImageIcon("assets/dice6.png");
                diceButton.setIcon(dice6);
                break;

                
            }

     
        
                          
    


    if(turn  == 1)
    {
        p1score = p1score + d; 
        p1scr.setText(Integer.toString(p1score));
        
        
        
        if( p1score > 100 ) 
        {
            p1score -= d;
        }
        else if( p1score == 100 )
        {
            
            messagebox.setForeground(Color.red);
            messagebox.setText("Woah!,  "+p1name.getText()+" won the Game.");
        }

    y1 = positiony(p1score);
    x1  = positionx(p1score,y1);
            

            for(int i =0;i<12;i++)
            {
                if(snkmo_ldrup[i] ==  p1score)
                {
                    s =(int )(18*Math.random())+1;
                    p1score = snktl_ldrdown[i];
                    messagebox.setForeground(Color.red);
                    if(i < 7)
                    {
                        messagebox.setText(snakemessages[s]);
                        try{

                            Thread.sleep(400);
                        }
                        catch(InterruptedException e2)
                        { }
                        
                    }
                    else if(i>6 && i<12)
                    {
                        messagebox.setText(laddermessages[s]);
                        try
                        {

                            Thread.sleep(350);
                        }
                        catch(InterruptedException exc)
                        { }
                    }
                    break;
                }
            }
            
            


            y1 = positiony(p1score);
            x1  = positionx(p1score,y1);
            
            p1scr.setText(Integer.toString(p1score));

            if(p2score >= 100)
            {
                turn =1;
            }
            else { turn ++; }



        }
        else 
        {
            p2score = p2score + d; 
            p2scr.setText(Integer.toString(p2score));

      if( p2score > 100 ) 
                {
                    p2score = p2score - d;
                }
                else if( p2score == 100 )
                {
                   
                   
                    messagebox.setForeground( Color.green);
                    messagebox.setText("Woah! " +p2name.getText()+" won the Game.");
                }

                y2 = positiony(p2score);
                x2 = positionx(p2score,y2);

            
        for(int i =0;i<12;i++)
        {
        if(snkmo_ldrup[i] ==  p2score)
        {
                int s =(int )(18*Math.random())+1;
                p2score = snktl_ldrdown[i];
                messagebox.setForeground( Color.green);
            if(i < 7)
            {
                    messagebox.setText(snakemessages[s]);
                    try
                    {
                        Thread.sleep(350);
                    }
                    catch(InterruptedException e3)
                    { }
            }
            else if(i>6 && i<12)
            {
                    messagebox.setText(laddermessages[s]);
                    try
                    {   
                        Thread.sleep(350);
                    }
                    catch(InterruptedException e3)
                    { }
            }
                break;
        }
        }

        y2 = positiony(p2score);
        x2 = positionx(p2score,y2);

    p2scr.setText(Integer.toString(p2score));

    if(p1score >= 100 )
    {
    turn = 2;
    }
    else {  turn --; }
}

if(p1score == 100 && p2score == 100 )
{
    try
    {
        Thread.sleep(1000);
    }
    catch(InterruptedException e2)
    {}
messagebox.setForeground((Color.yellow));

messagebox.setText("Game Over :) ");
}
        
      
}
    }

//for getting width/x position of the player piece
 public int positionx(int score,int y) 
{   
    int posr =0;
    posr = score%10;
       
       
    if(score == 100)
    {
        return 1;
    }
   if( y == 1 ||  y == 3 ||  y == 5 ||  y == 7 ||  y == 9)
   
    {
        if(posr == 0)
        {
           
         if(score == p1score)
           {    y1 -=1; }
           else
           {    y2-=1;  } 

            //y-=1;
        return posr +11;
        }
    return posr+10;
    }
    else if( y == 0 ||  y == 2 ||  y == 4 ||  y == 6 ||  y == 8)
    {
        if(posr == 0)
        {
            if(score == p1score)
           {    y1 -=1; }
           else
           {    y2 -=1;  } 

            
        return posr +1;
        }
    }
    
     return (posr);
}


//for getting height/y position of the player piece

public int positiony(int score) 
{
    int score1 = score/10;

    if(score == 100)
    {
        return 9;
    }
     return(score1);
}


public void paintComponent(Graphics g) 
{
    

    super.paintComponent(g);
g.drawImage(bgimg, 0, 0, 1536,864,this);
g.setColor(Color.decode( "#1b1c1e"));

if(nostrt)
{
g.drawImage(logoImage,900,170,140,140,this);
}

// drawing images 

 

//this player will change the place
if(strt)
{
    g.fillRoundRect(695, 520, 550, 180, 26, 26); // for messagebox
    g.fillRoundRect(90, 140, 570, 570, 21, 21); // for board
    g.drawImage(board, 100,150,550,550, this);
 
    g.fillRect(0, 0, 1536, 130);  // header 
    g.drawImage(logoImage,1350,10,110,110,this);


   g.setColor(Color.decode("#ae002c"));
    g.fillRoundRect(695, 395, 180, 90, 12, 9);   // for diceButton

    if(turn == 1)
    {
        g.drawImage(player1,800,420,40,40,this);
    }
    else if(turn == 2)
    {
        g.drawImage(player2,800,420,40,40,this);
    }
    
    g.drawImage(player1,700,212,40,40,this);
    g.drawImage(player2,700,282,40,40,this);
    
g.drawImage(player2,xpos[x2-1],ypos[y2],40,40,this);
g.drawImage(player1,xpos[x1-1],ypos[y1],40,40,this);


}




repaint();

}

}
class snlgame
{
     public static void main(String[] args) 
    {
        new gameframe();
    }
}