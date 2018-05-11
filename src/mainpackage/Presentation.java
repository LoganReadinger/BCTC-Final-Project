package mainpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
 
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Presentation extends JPanel implements ActionListener, KeyListener {
   
   static ClassLoader loader = Presentation.class.getClassLoader();
   static Random rand = new Random();
   Timer mainTimer;
   
   static Image ImgBattery;
   static Image ImgBatteryEvil;
   Image ImgBackground;
   Image ImgIntroScreen;
   Image ImgGameOverScreen;
   Image ImgEndGameScreen;
   Image ImgFloor;
   Image ImgBevel;
   Image ImgLines;
   Image ImgBoss;
   Image ImgInstruct;
    
   static String stringPassColor;
   String EnemyColor;
    
   Enemy DisplayEnemy = new Enemy();
   Player Player1 = new Player();
   static String[] colors = new String[6]; {
        colors[0] = "Red";
        colors[1] = "Orange";
        colors[2] = "Yellow";
        colors[3] = "Green";
        colors[4] = "Blue";
        colors[5] = "Gray";
   }
  
   String[] enemies = new String[9]; {
        enemies[0] = "flappy.png";
        enemies[1] = "ninja.png";
        enemies[2] = "gelatin.png";
        enemies[3] = "papastomp.png";
        enemies[4] = "ghost.png";
        enemies[5] = "lilstompy.png";
        enemies[6] = "roboknight.png";
        enemies[7] = "walkbird.png";
        enemies[8] = "wiggle.png";
   }
  //Good Pickups
   Pickup Pickup1 = new Pickup();
   Pickup Pickup2 = new Pickup();
   Pickup Pickup3 = new Pickup();
   Pickup Pickup4 = new Pickup();
   //Bad Pickups
   Pickup Pickup5 = new Pickup();
   Pickup Pickup6 = new Pickup();
   Pickup Pickup7 = new Pickup();
   Pickup Pickup8 = new Pickup();
   
   
   static int IntWaveNumber = 0;
   static int IntEnemyRef = 0;
   static int IntCurrentMovementDirection = 0;
   static int IntCurrentAnimationDirection = 4;
   static int IntBatteryCount = 0;
   static int IntBatteryCountEnemy = 0;
   static int IntPickupsCleared = 0;
   static int IntPickupsPassed = 0;
   static int IntEnemiesDefeated = 0;
   static int IntColorRef = 0;
   static int FloorCount = 0;
   static int IntDeaths = 0;
   int BoundBoxWidth = 569;
   int IntPlayerSize = 50;
   
   
   static boolean BlnGameOver = false;
   static boolean BlnGameEnd = false;
   static boolean BLnGameInstruct = false;
   boolean BlnBattleTrigger = false;
   boolean BlnIntro = true;
   boolean BlnInitialize = false;
   
    public static void main(String[] args) {
        new Presentation();
    }
   
    public Presentation() {
        //Focus on JFrame
        setFocusable(true);
        this.addKeyListener(this);
       
        //Initialize accessed by Presentation Layer
        Timer timer = new Timer(1, this);
        timer.setInitialDelay(0);
        timer.start();
       
       
        //Declare frame bits
        try {
            ImgLines =ImageIO.read(loader.getResource("lines.png"));
            ImgBevel = ImageIO.read(loader.getResource("bevel.png"));
            ImgBackground = ImageIO.read(loader.getResource("border.png"));
            ImgIntroScreen = ImageIO.read(loader.getResource("intro.png"));
            ImgGameOverScreen = ImageIO.read(loader.getResource("gameover.png"));
            ImgEndGameScreen= ImageIO.read(loader.getResource("endgame.png"));
            ImgBoss = ImageIO.read(loader.getResource("boss.png"));
            ImgInstruct = ImageIO.read(loader.getResource("instructions.png"));
        } catch(IOException ie) {
                ie.printStackTrace();
            }
       
        ImgBattery = SpriteRetrieval.getSprite(IntBatteryCount, 0, "Battery.png", 150);
        ImgBatteryEvil = SpriteRetrieval.getSprite(IntBatteryCountEnemy, 0, "BatteryEvil.png", 150);
       
    }
   
   
    //Display Graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
            //Notes:
            //Pickups:
                //0 - Green Pickup
                //1 - Red Pickup
                //Format:  (x,y.level,type)
       
            //PassColor defines Floor and Enemy Color
            PassColor(colors[IntColorRef]);
       
        //Define current Level Design
        g.drawImage(ImgFloor, 200, 25, null);
        g.drawImage(ImgBackground, 0, 0, null);
       
       
        //Current Displayed Enemy
        if (FloorCount < 5) {
        DisplayEnemy.paint(g,enemies[IntEnemyRef],1,colors[IntColorRef]);
        } else {
        g.drawImage(ImgBoss, 200, 25, null);
    	}
        
        
        //Current Pickup Layout
        //Pickup.WaveStart = true;
       
        //4 good top/4 bad bottom
        //Good
        Pickup1.paint(g, 149 + BoundBoxWidth, 0, 1, 0);
        Pickup2.paint(g, 298 + BoundBoxWidth, 0, 1, 0);
        Pickup3.paint(g, 447 + BoundBoxWidth, 0, 1, 0);
        Pickup4.paint(g, 596 + BoundBoxWidth, 0, 1, 0);
        //Bad
        Pickup5.paint(g, 149 + BoundBoxWidth, 133, 1, 1);
        Pickup6.paint(g, 298 + BoundBoxWidth, 133, 1, 1);
        Pickup7.paint(g, 447 + BoundBoxWidth, 133, 1, 1);
        Pickup8.paint(g, 596 + BoundBoxWidth, 133, 1, 1);
       
       
        if (IntWaveNumber == 1) {
            //3 good top 2 bad top/ 3 bad bottom 2 good bottom
            Pickup1.setNewPos(149 + BoundBoxWidth, 0);
            Pickup2.setNewPos(298 + BoundBoxWidth, 133);
            Pickup3.setNewPos(447 + BoundBoxWidth, 0);
            Pickup4.setNewPos(596 + BoundBoxWidth, 133);
           
            Pickup5.setNewPos(149 + BoundBoxWidth, 133);
            Pickup6.setNewPos(298 + BoundBoxWidth, 0);
            Pickup7.setNewPos(447 + BoundBoxWidth, 133);
            Pickup8.setNewPos(596 + BoundBoxWidth, 0);
           
            IntWaveNumber = 0;
        }
       
        if (IntWaveNumber == 2) {
            //2 good top 2 bad top/2 bad bottom 2 good bottom
            Pickup1.setNewPos(149 + BoundBoxWidth, 32);
            Pickup2.setNewPos(298 + BoundBoxWidth, 101);
            Pickup3.setNewPos(447 + BoundBoxWidth, 32);
            Pickup4.setNewPos(596 + BoundBoxWidth, 101);
           
            Pickup5.setNewPos(149 + BoundBoxWidth, 0);
            Pickup6.setNewPos(298 + BoundBoxWidth, 133);
            Pickup7.setNewPos(447 + BoundBoxWidth, 0);
            Pickup8.setNewPos(596 + BoundBoxWidth, 133);
           
            IntWaveNumber = 0;
        }
        if (IntWaveNumber == 3) {
            //line of good/bad
            Pickup1.setNewPos(209 + BoundBoxWidth, 67);
            Pickup2.setNewPos(329 + BoundBoxWidth, 67);
            Pickup3.setNewPos(449 + BoundBoxWidth, 67);
            Pickup4.setNewPos(569 + BoundBoxWidth, 67);
           
            Pickup5.setNewPos(149 + BoundBoxWidth, 67);
            Pickup6.setNewPos(269 + BoundBoxWidth, 67);
            Pickup7.setNewPos(389 + BoundBoxWidth, 67);
            Pickup8.setNewPos(509 + BoundBoxWidth, 67);
            IntWaveNumber = 0;
        }
       
        if (IntWaveNumber == 4) {
            //big zig-zag
            Pickup1.setNewPos(71 + BoundBoxWidth, 0);       //top           2
            Pickup2.setNewPos(142 + BoundBoxWidth, 65);     //middle    3
            Pickup3.setNewPos(355 + BoundBoxWidth, 0);      //top           6
            Pickup4.setNewPos(426 + BoundBoxWidth,65);  //middle    7
           
            Pickup5.setNewPos(568 + BoundBoxWidth, 65); //middle    1
            Pickup6.setNewPos(213 + BoundBoxWidth, 133); //bottom   4
            Pickup7.setNewPos(284 + BoundBoxWidth, 65);     //middle    5
            Pickup8.setNewPos(497 + BoundBoxWidth, 133); //bottom   8
           
            IntWaveNumber = 0;
        }
       
        if (IntWaveNumber ==5){
            //3 bad top 1 good top/3 good bottom 1 bad bottom
            Pickup1.setNewPos(149 + BoundBoxWidth, 133);    //bottom
            Pickup2.setNewPos(298 + BoundBoxWidth, 0);  //top
            Pickup3.setNewPos(447 + BoundBoxWidth, 0);      //top
            Pickup4.setNewPos(596 + BoundBoxWidth, 133);    //bottom
           
            Pickup5.setNewPos(149 + BoundBoxWidth, 0);      //top
            Pickup6.setNewPos(298 + BoundBoxWidth, 133);        //bottom
            Pickup7.setNewPos(447 + BoundBoxWidth, 133);    //bottom
            Pickup8.setNewPos(596 + BoundBoxWidth, 0);      //top
           
            IntWaveNumber = 0;
        }
        if (IntWaveNumber == 11) {
            //3 good top 2 bad top/ 3 bad bottom 2 good bottom
            Pickup1.setNewPos(149 + BoundBoxWidth, 0);
            Pickup2.setNewPos(298 + BoundBoxWidth, 0);
            Pickup3.setNewPos(447 + BoundBoxWidth, 0);
            Pickup4.setNewPos(596 + BoundBoxWidth, 0);
           
            Pickup5.setNewPos(149 + BoundBoxWidth, 133);
            Pickup6.setNewPos(298 + BoundBoxWidth, 133);
            Pickup7.setNewPos(447 + BoundBoxWidth, 133);
            Pickup8.setNewPos(596 + BoundBoxWidth, 133);
            IntWaveNumber = 0;
        }
       
        g.drawImage(ImgLines, 0, 0, null);
        g.drawImage(ImgBevel, 0, 0, null);
        g.drawImage(ImgBattery, 28, 636, null);
        g.drawImage(ImgBatteryEvil, 822, 636, null);
        
        //Showing Useful Information in Top Left
        g.setColor(Color.yellow);
        g.drawString("Enemies Defeated: " + IntEnemiesDefeated, 77, 580);
        
        g.setColor(Color.cyan);
        g.drawString("Floors Cleared: " + FloorCount + " / 5", 82, 600);
       
        g.setColor(Color.green);
        g.drawString("Times Died: " + IntDeaths, 116, 620);
        
        //Add Player
        if (BlnIntro == false && BLnGameInstruct == false) {
        Player1.paint(g,245,680);
        }
        //Add Intro
        if (BlnIntro == true) {
            g.drawImage(ImgIntroScreen, 0, 0, null);
        }
        if (BLnGameInstruct == true) {
            g.drawImage(ImgInstruct, 0, 0, null);
        }
        //Add Game Over
        if (BlnGameOver == true) {
            g.drawImage(ImgGameOverScreen, 0, 0, null);
        }
        //Add Game End
        if (BlnGameEnd == true) {
            g.drawImage(ImgEndGameScreen, 0, 0, null);
        }
    }
 
 
    @Override
    //Repaint any image each tick
    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }
 
    @Override
    //Accept user input
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
            IntCurrentMovementDirection = Player.move("up");
        }
        if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
            IntCurrentMovementDirection = Player.move("down");
        }
        if (e.getKeyCode() == 68 && Player.moveLeftRight == 2){
            IntCurrentMovementDirection = Player.move("right");
        }
        if (e.getKeyCode() == 65 && Player.moveLeftRight == 1){
            IntCurrentMovementDirection = Player.move("left");
        }
        if(e.getKeyCode() == 39 && Player.moveLeftRight == 2) {
        	IntCurrentMovementDirection = Player.move("right");
        }
        if(e.getKeyCode() == 37 && Player.moveLeftRight == 1) {
        	IntCurrentMovementDirection = Player.move("left");
        }
        
        if (e.getKeyCode() == 90 && BlnIntro == true){
            BlnIntro = false;
            BLnGameInstruct = true;
        } else if (e.getKeyCode() == 90 && BLnGameInstruct == true) {
        	BLnGameInstruct = false;
        }
        
        if (e.getKeyCode() == 90 && BlnGameOver == true){
        	BlnGameOver = false;
        }
        if (e.getKeyCode() == 90 && BlnGameEnd == true) {
        	GameDisplayWindow.frame.dispatchEvent(new WindowEvent(GameDisplayWindow.frame, WindowEvent.WINDOW_CLOSING));
        }
        //System.out.println(e.getKeyCode());
    }
 
    @Override
    public void keyReleased(KeyEvent er) {
    }
 
    @Override
    public void keyTyped(KeyEvent arg0) {
    }
   
    public static void healthDecrease(int amount) {
    	//amount = 0;
        if (IntBatteryCount < 9) {
            IntBatteryCount += amount;
            ImgBattery = SpriteRetrieval.getSprite(IntBatteryCount, 0, "Battery.png", 150);
        } else {
        	IntDeaths += 1;
            IntBatteryCount = 0;
            IntBatteryCountEnemy = 0;
            ImgBattery = SpriteRetrieval.getSprite(IntBatteryCount, 0, "Battery.png", 150);
            ImgBatteryEvil = SpriteRetrieval.getSprite(IntBatteryCountEnemy, 0, "Battery.png", 150);
            BlnGameOver = true;
            Player.PlayerXPosition = 245;
            Player.PlayerYPosition = 680;
            IntWaveNumber = 11;
            IntEnemiesDefeated = 0;
            IntPickupsCleared = 0;
            IntCurrentMovementDirection = Player.move("right");
        }
    }
   
   
    public static void healthDecreaseEnemy(int amount) {
    	//amount = 10;
        if (IntBatteryCountEnemy < 9) {
            IntBatteryCountEnemy += amount;
            ImgBatteryEvil = SpriteRetrieval.getSprite(IntBatteryCountEnemy, 0, "BatteryEvil.png", 150);
        } else {
            IntBatteryCountEnemy = 0;
            ImgBatteryEvil = SpriteRetrieval.getSprite(IntBatteryCountEnemy, 0, "BatteryEvil.png", 150);
            IntEnemyRef = rand.nextInt((8 - 0) + 1) + 0;
            IntEnemiesDefeated += 1;
            if (FloorCount < 5 && IntEnemiesDefeated == 3){
            	IntColorRef += 1;
            	FloorCount += 1;
            	IntEnemiesDefeated = 0;
            }
            
            if (FloorCount == 5 && IntEnemiesDefeated == 1){
            BlnGameEnd = true;
            Player.PlayerXPosition = 245;
            Player.PlayerYPosition = 680;
            IntWaveNumber = 11;
            }
            }
            
        }
 
    public void PassColor(String Color) {
        Graphics g = null;
        try {
            ImgFloor = ImageIO.read(loader.getResource("Floor" + Color + ".png"));
            EnemyColor = Color;
            stringPassColor = Color;
            } catch(IOException ie) {
                ie.printStackTrace();
            }
    }
}