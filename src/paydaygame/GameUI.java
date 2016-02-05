/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

public class GameUI extends JPanel {
    
    JFrame toJFrame;
    Interface toInterface;
    Deal toDeal;
    Mail toMail;
    Player toPlayer[];
    Board toBoard;
    
    int mailDeckSize;
    int dealDeckSize;
    
    //Creates Image Variables And Arrays
    private BufferedImage boardImage;
    private BufferedImage $100;
    private BufferedImage $500;
    private BufferedImage $1000;
    private BufferedImage $5000;
    private BufferedImage $10000;
    private BufferedImage dealBack;
    private BufferedImage mailBack;
    private BufferedImage dealCardImgs[];
    private BufferedImage mailCardImgs[];
    private BufferedImage dieFaces[];
    private BufferedImage counters[];
    int currentDie;
    int squareX[];
    int squareY[];
    int counterXOffset[];
    int counterYOffset[];
    int noOfPlayers;
    int counterXStarting[];
    int counterYStarting[];
    int counterXCurrent[];
    int counterYCurrent[];
    int counterXGoal[];
    int counterYGoal[];
    boolean counterMoving[];
    int counterMovementPercentage[];
    String currentOutput;
    boolean textJustPainted;
    
    public GameUI (int nOP, Interface i, Deal d, Mail m, Board b) {
        //Creates URL Arrays
        URL dealCardURLs[];
        URL mailCardURLs[];
        URL dieFaceURLs[];
        URL counterURLs[];
        
        //Creates JFrame
        toJFrame = new JFrame("Payday. The Game. It's Cool.");
        toJFrame.add(this);
        toJFrame.setSize(1200, 1000);
        toJFrame.setVisible(true);
        toJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Class Pointer Declaration
        toInterface = i;
        toDeal = d;
        toMail = m;
        toPlayer = new Player[6];
        toBoard = b;
        noOfPlayers = nOP;
        textJustPainted = true;
        
        //Array Size Variable Declaration
        mailDeckSize = toMail.getDeckSize();
        dealDeckSize = toDeal.getDeckSize();
        
        //Array Size Declaration. It's like customs around here.
        dealCardImgs = new BufferedImage[dealDeckSize];
        dealCardURLs = new URL[dealDeckSize];
        mailCardImgs = new BufferedImage[mailDeckSize];
        mailCardURLs = new URL[mailDeckSize];
        dieFaces = new BufferedImage[7];
        dieFaceURLs = new URL[7];
        counters = new BufferedImage[6];
        counterURLs = new URL[6];
        counterXOffset = new int[6];
        counterYOffset = new int[6];
        squareX = new int[32];
        squareY = new int [32];
        counterXStarting = new int [6];
        counterYStarting = new int [6];
        counterXCurrent = new int [6];
        counterYCurrent = new int [6];
        counterXGoal = new int [6];
        counterYGoal = new int [6];
        counterMoving = new boolean [6];
        counterMovementPercentage = new int [6];
        
        //Counter Offsets (From Upper Left Corner) Location Declaration
        counterXOffset[0] = 3;
        counterYOffset[0] = 3;
        counterXOffset[1] = 43;
        counterYOffset[1] = -2;
        counterXOffset[2] = 0;
        counterYOffset[2] = 31;
        counterXOffset[3] = 46;
        counterYOffset[3] = 25;
        counterXOffset[4] = 3;
        counterYOffset[4] = 58;
        counterXOffset[5] = 43;
        counterYOffset[5] = 53;
        
        //URL Path Declaration
        URL boardImgUrl = this.getClass().getResource("resources/board.png");
        URL $100ImgUrl = this.getClass().getResource("resources/$100.jpg");
        URL $500ImgUrl = this.getClass().getResource("resources/$500.jpg");
        URL $1000ImgUrl = this.getClass().getResource("resources/$1000.jpg");
        URL $5000ImgUrl = this.getClass().getResource("resources/$5000.jpg");
        URL $10000ImgUrl = this.getClass().getResource("resources/$10000.jpg");
        URL dealBackImgURL = this.getClass().getResource("resources/Deal/Back.jpg");
        URL mailBackImgURL = this.getClass().getResource("resources/Mail/Back.jpg");
        
        //Repeat for arrays.
        for (int index = 0; index < dealDeckSize; index++) {
            dealCardURLs[index] = this.getClass().getResource("resources/Deal/" + index + ".jpg");
        }
        
        for (int index = 0; index < mailDeckSize; index++) {
            mailCardURLs[index] = this.getClass().getResource("resources/Mail/" + index + ".jpg");
        }
        
        for (int index = 0; index < 7; index++) {
            dieFaceURLs[index] = this.getClass().getResource("resources/Die" + index + ".png");
        }
        
        for (int index = 0; index < 6; index++) {
            counterURLs[index] = this.getClass().getResource("resources/Counter" + index + ".png");
        }
        
        for (int index = 0; index < 32; index++) {
            squareX[index] = toBoard.getSquareX(index);
            squareY[index] = toBoard.getSquareY(index);
        }
        
        for (int index = 0; index < noOfPlayers; index++) {
            toPlayer[index] = toInterface.getPlayer(index);
            counterMoving[index] = false;
            counterMovementPercentage[index] = 0;
            counterXStarting[index] = squareX[0]+counterXOffset[index]; counterYStarting[index] = squareY[0]+counterYOffset[index];
            counterXCurrent[index] = counterXStarting[index]; counterYCurrent[index] = counterYStarting[index];
            counterXGoal[index] = counterXStarting[index]; counterYGoal[index] = counterYStarting[index];
            }
        
        //Image IO Read
        try {
            boardImage = ImageIO.read(boardImgUrl);
            $100 = ImageIO.read($100ImgUrl);
            $500 = ImageIO.read($500ImgUrl);
            $1000 = ImageIO.read($1000ImgUrl);
            $5000 = ImageIO.read($5000ImgUrl);
            $10000 = ImageIO.read($10000ImgUrl);
            dealBack = ImageIO.read(dealBackImgURL);
            mailBack = ImageIO.read(mailBackImgURL);
            for (int index = 0; index < dealDeckSize; index++) {
                if (toInterface.devMode) {System.out.println(dealCardURLs[index]);}
                dealCardImgs[index] = ImageIO.read(dealCardURLs[index]);
            }
            for (int index = 0; index < mailDeckSize; index++) {
                if (toInterface.devMode) {System.out.println(mailCardURLs[index]);}
                mailCardImgs[index] = ImageIO.read(mailCardURLs[index]);
            }
            for (int index = 0; index < 7; index++) {
                if (toInterface.devMode) {System.out.println(dieFaceURLs[index]);}
                dieFaces[index] = ImageIO.read(dieFaceURLs[index]);
            }
            for (int index = 0; index < 6; index++) {
                if (toInterface.devMode) {System.out.println(counterURLs[index]);}
                counters[index] = ImageIO.read(counterURLs[index]);
            }
            currentDie = 0;
           }
        catch (java.io.IOException e) {System.out.println("BLAM! IO EXCEPTION!");}
       
    }
    
    public void refresh(Graphics g) {
        //All that refresh does is change the coords of things that are drawn in render.
        for (int index = 0; index < 6; index++) {
            if (counterMoving[index]) {
                counterMovementPercentage[index] = counterMovementPercentage[index] + 1; //Adjust this number to change how fast the counters move.
                Point tempPoint = animatedLocation2(counterXStarting[index], counterYStarting[index], counterXGoal[index], counterYGoal[index], counterMovementPercentage[index]);
                counterXCurrent[index] = tempPoint.x; counterYCurrent[index] = tempPoint.y;
                this.render(g);
                if (counterMovementPercentage[index] >= 100) {
                    counterMovementPercentage[index] = 0;
                    counterMoving[index] = false;
                }
            }
        }
        if (textJustPainted) {
            textJustPainted = false;
            this.render(g);
        }
    } 
    
    public void render (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0, 870, 699, 20);//Creating a white retangle which lets the text appear nicely.
        g2d.setColor(Color.black);
        g2d.drawString(currentOutput, 0, 880); //Some bad flicker here
        g2d.drawLine(700, 0, 700, 1200);
        g2d.drawLine(0, 145, 1200, 145);
        g2d.drawLine(0, 860, 700, 860);
        g2d.drawLine(800, 277, 1100, 277);
        g2d.drawLine(800, 419, 1100, 419);
        g2d.drawLine(800, 561, 1100, 561);
        g2d.drawLine(800, 703, 1100, 703);
        g2d.drawLine(800, 845, 1100, 845);
        Point mailBackScaled = this.scale(50, mailBack.getWidth(), mailBack.getHeight());
        g2d.drawImage(mailBack, 0, 0, (int)mailBackScaled.getX(), (int)mailBackScaled.getY(), 0, 0, mailBack.getWidth(), mailBack.getHeight(), this);
        
        Point dealBackScaled = this.scale(50, dealBack.getWidth(), dealBack.getHeight());
        g2d.drawImage(dealBack, 175, 0, (int)dealBackScaled.getX()+175, (int)dealBackScaled.getY(), 0, 0, dealBack.getWidth(), dealBack.getHeight(), this);
        
        Point $100Scaled = this.scale(12, $100.getWidth(), $100.getHeight());
        g2d.drawImage($100, 325, 0, (int)$100Scaled.getX()+325, (int)$100Scaled.getY(), 0, 0, $100.getWidth(), $100.getHeight(), this);
        
        Point $500Scaled = this.scale(12, $500.getWidth(), $500.getHeight());
        g2d.drawImage($500, 400, 0, (int)$500Scaled.getX()+400, (int)$500Scaled.getY(), 0, 0, $500.getWidth(), $500.getHeight(), this);
        
        Point $1000Scaled = this.scale(12, $1000.getWidth(), $1000.getHeight());
        g2d.drawImage($1000, 475, 0, (int)$1000Scaled.getX()+475, (int)$1000Scaled.getY(), 0, 0, $1000.getWidth(), $1000.getHeight(), this);
        
        Point $5000Scaled = this.scale(12, $5000.getWidth(), $5000.getHeight());
        g2d.drawImage($5000, 550, 0, (int)$5000Scaled.getX()+550, (int)$5000Scaled.getY(), 0, 0, $5000.getWidth(), $5000.getHeight(), this);
        
        Point $10000Scaled = this.scale(12, $10000.getWidth(), $10000.getHeight());
        g2d.drawImage($10000, 625, 0, (int)$10000Scaled.getX()+625, (int)$10000Scaled.getY(), 0, 0, $10000.getWidth(), $10000.getHeight(), this);
        
        Point dieScaled = this.scale(17, dieFaces[0].getWidth(), dieFaces[0].getHeight()); //This will work for all dice.
        g2d.drawImage(dieFaces[currentDie], 725, 10, (int)dieScaled.getX()+725, (int)dieScaled.getY()+10, 0, 0, dieFaces[0].getWidth(), dieFaces[0].getHeight(), this);
        
        g2d.drawImage(boardImage, 5, 150, this); //Placing the board paint here seems to solve the fickering of the counters.
        
        Point counterScaled = this.scale(10, counters[0].getWidth(), counters[0].getHeight()); //This will work for all counters.
        for (int index = 0; index < noOfPlayers; index++) {
            g2d.drawImage(counters[index], counterXCurrent[index], counterYCurrent[index], (int)counterScaled.getX()+counterXCurrent[index], (int)counterScaled.getY()+counterYCurrent[index], 0, 0, counters[index].getWidth(), counters[index].getHeight(), this);
            //g2d.drawImage(counters[index], squareX[boardPosition]+counterXOffset[index], squareY[boardPosition]+counterYOffset[index], (int)counterScaled.getX()+squareX[boardPosition]+counterXOffset[index],(int)counterScaled.getY()+squareY[boardPosition]+counterYOffset[index], 0, 0, counters[index].getWidth(), counters[index].getHeight(), this);
        }
    }
    
    @Override
    public void paint (Graphics g) {
        this.render(g);
        }
    
    public Point animatedLocation (int x1, int y1, int x2, int y2, int percentage) {
        /*given an object moving from point x1, y1, to point x2, y2, determine its location when it is <percentage> of the way alongassuming a parabolic speed profile (fast--slow--fast)*/
        Point thePoint = new Point(0,0);
        double doublePercentage = percentage; // float the percentage
        double scaledPercentage = doublePercentage/50.0; //scale percentage to between 0 and 2
        double shiftedPercentage = scaledPercentage - 1; //shift the poercentage to be between -1 and +1
        double adjustedPercentage1 = Math.cbrt(shiftedPercentage) + 1; //Take the cube root and add one. cbrt(x) + 1 gives result between 0 and 2
        double adjustedPercentage = 50.0 * adjustedPercentage1; //scale result to between 0 to 100
        //System.out.println("percentage " + percentage + " adjusted to " + adjustedPercentage); //DEBUG
        // construct a Point that is adjustedPercentage along the way from (x1, y1) to (x2, y2)
        thePoint.setLocation( (x2 - x1) * adjustedPercentage / 100.0 + x1, (y2 - y1) * adjustedPercentage / 100.0 + y1 );
        //System.out.println("At " + percentage + "% of the way from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ") we are at (" + thePoint.getX() )", " + thePoint.getY() + ")."); //DEBUG
        return thePoint;
        } 
    
    public Point animatedLocation2 (int x1, int y1, int x2, int y2, int percentage) {
        /*given an object moving from point x1, y1, to point x2, y2, determine its location when it is <percentage> of the way along assuming a falling speed profile (fast--slow)*/
        Point thePoint = new Point(0,0);
        double doublePercentage = percentage; // float the percentage
        double scaledPercentage = doublePercentage/100.0; //scale percentage to between 0 and 1
        //double shiftedPercentage = scaledPercentage - 1; 
        //shift the percentage to be between -1 and +1
        double adjustedPercentage1 = Math.sqrt(scaledPercentage); //Take the square root; this gives result between 0 and 1
        double adjustedPercentage = 100.0 * adjustedPercentage1; //scale result to between 0 to 100
        //System.out.println("percentage " + percentage + " adjusted to " + adjustedPercentage); //DEBUG
        // construct a Point that is adjustedPercentage along the way from (x1, y1) to (x2, y2)
        thePoint.setLocation( (x2 - x1) * adjustedPercentage / 100.0 + x1, (y2 - y1) * adjustedPercentage / 100.0 + y1 );
        //System.out.println("At " + percentage + "% of the way from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ") we are at (" + thePoint.getX() + ", " + thePoint.getY() + ")."); //DEBUG
        return thePoint;

    }
    
    public Point scale (int reductionFactor/*A percentage*/, int originalSizeX, int originalSizeY) {
        int finalX = originalSizeX * reductionFactor / 100;
        int finalY = originalSizeY * reductionFactor / 100;
        Point destinationLoc = new Point(finalX, finalY);
        return destinationLoc;
    }
    
    public void roll (int i) {
        int randomFace;
        for (int index = 0; index < 3000; index++) {
        randomFace = new Random().nextInt(6)+1; 
        currentDie = randomFace;
        }
        currentDie = i;
    }
    
    public void moveCounter (int callerID, int endingSquare) { //Player will call this method.
        counterXStarting[callerID] = counterXGoal[callerID]; counterYStarting[callerID] = counterYGoal[callerID];
        counterXGoal[callerID] = squareX[endingSquare]+counterXOffset[callerID]; counterYGoal[callerID] = squareY[endingSquare]+counterYOffset[callerID]; 
        counterXCurrent[callerID] = counterXStarting[callerID]; counterYCurrent[callerID] = counterYStarting[callerID];
        counterMoving[callerID] = true;
    }
    
    public void setCurrentOutput(String s) {
        currentOutput = s;
        System.out.println(s);
        textJustPainted = true;
    }
    
   }