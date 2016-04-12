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
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameUI extends JPanel implements KeyListener {

    JFrame toJFrame;
    Interface toInterface;
    Deal toDeal;
    Mail toMail;
    Player toPlayer[];
    Board toBoard;

    int mailDeckSize;
    int dealDeckSize;

    //Creates Image Variables
    private BufferedImage boardImage;
    private BufferedImage dealBack;
    private BufferedImage mailBack;
    private BufferedImage dealCardImgs[];
    private BufferedImage mailCardImgs[];
    private BufferedImage dieFaces[];
    private BufferedImage counters[];
    private BufferedImage bills[];

    //Creates Incidental Varibles
    int currentDie;
    int noOfPlayers;
    String currentOutput = "";
    boolean textJustPainted;
    int playerCurrentlyRecievingMoney;
    int playerCurrentlySendingMoney;
    Font billFont = new Font("Serif", Font.BOLD, 18);
    Font oldFont = new Font("Serif", Font.PLAIN, 12);

    //Creates Counter Arrays
    int squareX[];
    int squareY[];
    int counterXOffset[];
    int counterYOffset[];
    int counterXStarting[];
    int counterYStarting[];
    int counterXCurrent[];
    int counterYCurrent[];
    int counterXGoal[];
    int counterYGoal[];
    boolean counterMoving[];
    int counterMovementPercentage[];

    //Creates Money Arrays
    int noOfPlayer100s[];
    int noOfPlayer500s[];
    int noOfPlayer1000s[];
    int noOfPlayer5000s[];
    int noOfPlayer10000s[];
    int moneyXOffset[];
    int moneyYOffset[];
    int moneyXStarting[];
    int moneyYStarting[];
    int moneyXCurrent[];
    int moneyYCurrent[];
    int moneyXGoal[];
    int moneyYGoal[];
    boolean moneyMoving[];
    boolean anyMoneyMoving;
    boolean moneyJustMoved;
    int moneyMovementPercentage[];
    int[] billXCoords;
    int[] playerYCoords;
    int i = 0;

    public GameUI(int nOP, Interface i, Deal d, Mail m, Board b) {
        //Creates URL Arrays
        URL dealCardURLs[];
        URL mailCardURLs[];
        URL dieFaceURLs[];
        URL counterURLs[];

        //Creates JFrame
        toJFrame = new JFrame("Payday. The Game. It's Cool.");
        toJFrame.add(this);
        toJFrame.setSize(1200, 1000); 
        toJFrame.setLocationByPlatform(true);
        toJFrame.setVisible(true);
        KeyBoard toKeyBoard = new KeyBoard();
        toJFrame.addKeyListener(toKeyBoard);
        this.setFocusable(true);
        this.requestFocusInWindow();
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
        squareY = new int[32];
        counterXStarting = new int[6];
        counterYStarting = new int[6];
        counterXCurrent = new int[6];
        counterYCurrent = new int[6];
        counterXGoal = new int[6];
        counterYGoal = new int[6];
        counterMoving = new boolean[6];
        counterMovementPercentage = new int[6];
        moneyXStarting = new int[5];
        moneyYStarting = new int[5];
        moneyXCurrent = new int[5];
        moneyYCurrent = new int[5];
        moneyXGoal = new int[5];
        moneyYGoal = new int[5];
        moneyMoving = new boolean[5];
        moneyMovementPercentage = new int[5];
        billXCoords = new int[5];
        playerYCoords = new int[6];
        bills = new BufferedImage[5];
        

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

        //Player/BillLoc Declaration
        billXCoords[0] = 800;
        billXCoords[1] = 870;
        billXCoords[2] = 935;
        billXCoords[3] = 1000;
        billXCoords[4] = 1065;
        playerYCoords[0] = 150;
        playerYCoords[1] = 280;
        playerYCoords[2] = 415;
        playerYCoords[3] = 555;
        playerYCoords[4] = 695;
        playerYCoords[5] = 835;

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
            counterXStarting[index] = squareX[0] + counterXOffset[index];
            counterYStarting[index] = squareY[0] + counterYOffset[index];
            counterXCurrent[index] = counterXStarting[index];
            counterYCurrent[index] = counterYStarting[index];
            counterXGoal[index] = counterXStarting[index];
            counterYGoal[index] = counterYStarting[index];
        }

        //Image IO Read
        try {
            boardImage = ImageIO.read(boardImgUrl);
            bills[0] = ImageIO.read($100ImgUrl);
            bills[1] = ImageIO.read($500ImgUrl);
            bills[2] = ImageIO.read($1000ImgUrl);
            bills[3] = ImageIO.read($5000ImgUrl);
            bills[4] = ImageIO.read($10000ImgUrl);
            dealBack = ImageIO.read(dealBackImgURL);
            mailBack = ImageIO.read(mailBackImgURL);
            for (int index = 0; index < dealDeckSize; index++) {
                if (toInterface.devMode) {
                    System.out.println(dealCardURLs[index]);
                }
                dealCardImgs[index] = ImageIO.read(dealCardURLs[index]);
            }
            for (int index = 0; index < mailDeckSize; index++) {
                if (toInterface.devMode) {
                    System.out.println(mailCardURLs[index]);
                }
                mailCardImgs[index] = ImageIO.read(mailCardURLs[index]);
            }
            for (int index = 0; index < 7; index++) {
                if (toInterface.devMode) {
                    System.out.println(dieFaceURLs[index]);
                }
                dieFaces[index] = ImageIO.read(dieFaceURLs[index]);
            }
            for (int index = 0; index < 6; index++) {
                if (toInterface.devMode) {
                    System.out.println(counterURLs[index]);
                }
                counters[index] = ImageIO.read(counterURLs[index]);
            }
            currentDie = 0;
        } catch (java.io.IOException e) {
            System.out.println("BLAM! IO EXCEPTION!");
        }

        for (int index = 0; index < 5; index++) {
            moneyMoving[index] = false;
            moneyMovementPercentage[index] = 0;
        }

    }

    public void refresh(Graphics g) {
        //All that refresh does is change the coords of things that are drawn in render.
        for (int index = 0; index < 6; index++) {
            if (counterMoving[index]) {
                counterMovementPercentage[index] = counterMovementPercentage[index] + 1; //Adjust this number to change how fast the counters move.
                Point tempPoint = animatedLocation(counterXStarting[index], counterYStarting[index], counterXGoal[index], counterYGoal[index], counterMovementPercentage[index]);
                counterXCurrent[index] = tempPoint.x;
                counterYCurrent[index] = tempPoint.y;
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
        for (int index = 0; index < 5; index++) {
            if (moneyMoving[index]) {
                moneyMovementPercentage[index] = moneyMovementPercentage[index] + 2; //Adjust this number to change how fast the counters move.
                Point tempPoint = animatedLocation(moneyXStarting[index], moneyYStarting[index], moneyXGoal[index], moneyYGoal[index], moneyMovementPercentage[index]);
                moneyXCurrent[index] = tempPoint.x;
                moneyYCurrent[index] = tempPoint.y;
                if (moneyMovementPercentage[index] >= 100) {
                    moneyMovementPercentage[index] = 0;
                    moneyMoving[index] = false;
                    synchronized(toInterface.lockObj) {
                    toInterface.lockObj.notify();}
                    if (!moneyMoving[0] && !moneyMoving[1] && !moneyMoving[2] && !moneyMoving[3] && !moneyMoving[4]) {
                        anyMoneyMoving = false;
                        moneyJustMoved = true;
                    }
                }
                this.render(g);
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        //g2d.fillRect(0, 0, 1200, 1000);//Flush the buffer and paint the world
        g2d.fillRect(0, 870, 699, 20);
        g2d.setFont(oldFont);
        g2d.setColor(Color.black);        
        g2d.drawString(currentOutput, 0, 880); //Some bad flicker here
        g2d.drawLine(701, 0, 700, 1200);
        g2d.drawLine(0, 145, 1200, 145);
        g2d.drawLine(0, 860, 700, 860);
        
        g2d.drawImage(mailBack, 0, 0, this);
        g2d.drawImage(dealBack, 175, 0, this);        
        
        g2d.drawImage(bills[0], 325, 0, this);
        g2d.drawImage(bills[1], 400, 0, this);
        g2d.drawImage(bills[2], 475, 0, this);
        g2d.drawImage(bills[3], 550, 0, this);
        g2d.drawImage(bills[4], 625, 0, this);

        g2d.drawImage(dieFaces[currentDie], 725, 10, this);

        g2d.drawImage(boardImage, 5, 150, this); //Placing the board paint here seems to solve the fickering of the counters.

        for (int index = 0; index < noOfPlayers; index++) {
            g2d.drawImage(counters[index], counterXCurrent[index], counterYCurrent[index], this);
        }
        
        if (anyMoneyMoving || moneyJustMoved) {
            g2d.setColor(Color.white);
            g2d.fillRect(701, 146, 1200, 1000); //White retangle behind money 
            g2d.setColor(Color.black);
            g2d.drawLine(800, 277, 1100, 277);
            g2d.drawLine(800, 410, 1100, 410);
            g2d.drawLine(800, 550, 1100, 550);
            g2d.drawLine(800, 690, 1100, 690);
            g2d.drawLine(800, 830, 1100, 830);
            for (int index = 0; index < 5; index++) {
                if (moneyMoving[index]) {
                g2d.drawImage(bills[index], moneyXCurrent[index], moneyYCurrent[index], this);
                }
            }
            g2d.setFont(billFont); 
            for (int p = 0; p < noOfPlayers; p++) {
                for (int b = 0; b < 5; b++) {
                    g2d.drawImage(counters[p], billXCoords[4]+50, playerYCoords[p], this);
                    g2d.drawString("$" + toPlayer[p].getCash(), billXCoords[4]+50, playerYCoords[p]+50);
                    if (toPlayer[p].getBillsIHave(b) > 0) {
                    g2d.drawImage(bills[b], billXCoords[b], playerYCoords[p], this);
                    g2d.drawString("x" + toPlayer[p].getBillsIHave(b), billXCoords[b]+35, playerYCoords[p]+100);
                    }
                }
            }
            moneyJustMoved = false;
        }
    }
    
    @Override
    public void paint(Graphics g) {
        this.render(g);
    }

    public Point animatedLocation(int x1, int y1, int x2, int y2, int percentage) {
        /*given an object moving from point x1, y1, to point x2, y2, determine its location when it is <percentage> of the way along assuming a falling speed profile (fast--slow)*/
        Point thePoint = new Point(0, 0);
        double doublePercentage = percentage; // float the percentage
        double scaledPercentage = doublePercentage / 100.0; //scale percentage to between 0 and 1
        //double shiftedPercentage = scaledPercentage - 1; 
        //shift the percentage to be between -1 and +1
        double adjustedPercentage1 = Math.sqrt(scaledPercentage); //Take the square root; this gives result between 0 and 1
        double adjustedPercentage = 100.0 * adjustedPercentage1; //scale result to between 0 to 100
        //System.out.println("percentage " + percentage + " adjusted to " + adjustedPercentage); //DEBUG
        // construct a Point that is adjustedPercentage along the way from (x1, y1) to (x2, y2)
        thePoint.setLocation((x2 - x1) * adjustedPercentage / 100.0 + x1, (y2 - y1) * adjustedPercentage / 100.0 + y1);
        //System.out.println("At " + percentage + "% of the way from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ") we are at (" + thePoint.getX() + ", " + thePoint.getY() + ")."); //DEBUG
        return thePoint;

    }

    public Point scale(int reductionFactor/*A percentage*/, int originalSizeX, int originalSizeY) {
        int finalX = originalSizeX * reductionFactor / 100;
        int finalY = originalSizeY * reductionFactor / 100;
        Point destinationLoc = new Point(finalX, finalY);
        return destinationLoc;
    }

    public void roll(int n) {
        /*int randomFace;
         for (int index = 0; index < 3000; index++) {
         randomFace = new Random().nextInt(6)+1; 
         currentDie = randomFace;
         }*///This doesn't work right now. Someday, the die faces will cycle past at blinding speed for a moment, before stopping at the right face.
        currentDie = n;
    }

    public void moveCounter(int callerID, int endingSquare) { //Player will call this method.
        counterXStarting[callerID] = counterXGoal[callerID];
        counterYStarting[callerID] = counterYGoal[callerID];
        counterXGoal[callerID] = squareX[endingSquare] + counterXOffset[callerID];
        counterYGoal[callerID] = squareY[endingSquare] + counterYOffset[callerID];
        counterXCurrent[callerID] = counterXStarting[callerID];
        counterYCurrent[callerID] = counterYStarting[callerID];
        counterMoving[callerID] = true;
    }

    public void moveBill(int type, int number, int payerID, int payeeID) {
        playerCurrentlyRecievingMoney = payeeID;
        playerCurrentlySendingMoney = payerID;
        if (payerID == -1) { //Bank pays players
            switch (type) {
                case 4:
                    moneyXStarting[type] = 625;
                    moneyYStarting[type] = 0;
                    break;
                case 3:
                    moneyXStarting[type] = 550;
                    moneyYStarting[type] = 0;
                    break;
                case 2:
                    moneyXStarting[type] = 475;
                    moneyYStarting[type] = 0;
                    break;
                case 1:
                    moneyXStarting[type] = 400;
                    moneyYStarting[type] = 0;
                    break;
                case 0:
                    moneyXStarting[type] = 325;
                    moneyYStarting[type] = 0;
                    break;
            }
            moneyXGoal[type] = billXCoords[type];
            moneyYGoal[type] = playerYCoords[payeeID];
            moneyXCurrent[type] = moneyXStarting[type];
            moneyYCurrent[type] = moneyYStarting[type];
            moneyMoving[type] = true;
        }
        if (payeeID != payerID && payerID != -1 && payeeID != -1) { //Players pay players
            moneyXStarting[type] = billXCoords[type];
            moneyYStarting[type] = playerYCoords[payerID];
            moneyXGoal[type] = billXCoords[type];
            moneyYGoal[type] = playerYCoords[payeeID];
            moneyXCurrent[type] = moneyXStarting[type];
            moneyYCurrent[type] = moneyYStarting[type];
            moneyMoving[type] = true;
        }
        if (payeeID == -1) { //Players pay bank
            switch (type) {
                case 4:
                    moneyXGoal[type] = 625;
                    moneyYGoal[type] = 0;
                    break;
                case 3:
                    moneyXGoal[type] = 550;
                    moneyYGoal[type] = 0;
                    break;
                case 2:
                    moneyXGoal[type] = 475;
                    moneyYGoal[type] = 0;
                    break;
                case 1:
                    moneyXGoal[type] = 400;
                    moneyYGoal[type] = 0;
                    break;
                case 0:
                    moneyXGoal[type] = 325;
                    moneyYGoal[type] = 0;
                    break;
            }
            moneyXStarting[type] = billXCoords[type];
            moneyYStarting[type] = playerYCoords[payerID];
            moneyXCurrent[type] = moneyXStarting[type];
            moneyYCurrent[type] = moneyYStarting[type];
            moneyMoving[type] = true;
        }
    }

    public int[] moveMoney(int callerID, int payee, int cash, int payer) {
        anyMoneyMoving = true;
        int outgoingBills[] = this.billCalc(Math.abs(cash));
        for (int index = 0; index < 5; index++) {
            if (outgoingBills[index] > 0) {
                this.moveBill(index, outgoingBills[index], payer, payee);
            }
        }  
        return outgoingBills;
    }

    public void setCurrentOutput(String s) {
        currentOutput = s;
        System.out.println(s);
        textJustPainted = true;
    }

    public int[] billCalc(int cash) {
        int numberOfEachBill[];
        numberOfEachBill = new int[5];
        for (int index = 0; index < 5; index++) {
            numberOfEachBill[index] = 0;
        }
        while (cash >= 10000) {
            numberOfEachBill[4]++;
            cash = cash - 10000;
        }
        while (cash >= 5000) {
            numberOfEachBill[3]++;
            cash = cash - 5000;
        }
        while (cash >= 1000) {
            numberOfEachBill[2]++;
            cash = cash - 1000;
        }
        while (cash >= 500) {
            numberOfEachBill[1]++;
            cash = cash - 500;
        }
        while (cash >= 100) {
            numberOfEachBill[0]++;
            cash = cash - 100;
        }
        return numberOfEachBill;
    }

    public void distributeStartingMoney() {
        toInterface.passString("Giving players their starting cash!");
        for (int index = 0; index < noOfPlayers; index++) {
            toPlayer[index].decreaseBalance(-5000, false, index, -1);
        }
    }
    
    public void change(int player, int required$) {
        int[] requiredBreakdown;
        requiredBreakdown = this.billCalc(required$);
        int billToChange = -1;
        boolean billChanged = false;
        for (int index = 4; index >= 0; index--) {
            if (toPlayer[player].getBillsIHave(index) < requiredBreakdown[index]) {
                for (int slot = index+1; slot <= 4; slot++) {
                    if (toPlayer[player].getBillsIHave(slot) > 0) {
                        billToChange = slot;
                        billChanged = true;
                    }
                    if (billChanged) {break;}
                }
                break;
            }
        }
        while (billToChange >= 0) {
            switch (billToChange) {
                case -1:
                    break;
                case 1:
                    toPlayer[player].setBillsIHave(1, toPlayer[player].getBillsIHave(1)-1);
                    toPlayer[player].setBillsIHave(0, toPlayer[player].getBillsIHave(0)+5);
                    break;
                case 2:
                    toPlayer[player].setBillsIHave(2, toPlayer[player].getBillsIHave(2)-1);
                    toPlayer[player].setBillsIHave(1, toPlayer[player].getBillsIHave(1)+2);
                    break;
                case 3:
                    toPlayer[player].setBillsIHave(3, toPlayer[player].getBillsIHave(3)-1);
                    toPlayer[player].setBillsIHave(2, toPlayer[player].getBillsIHave(2)+5);
                    break;
                case 4:
                    toPlayer[player].setBillsIHave(4, toPlayer[player].getBillsIHave(4)-1);
                    toPlayer[player].setBillsIHave(3, toPlayer[player].getBillsIHave(3)+2);
                    break;
            }
            billChanged = false;
            for (int index = 4; index >= 0; index--) {
                if (toPlayer[player].getBillsIHave(index) < requiredBreakdown[index]) {
                    for (int slot = index+1; slot <= 4; slot++) {
                        if (toPlayer[player].getBillsIHave(slot) > 0) {
                            billToChange = slot;
                            billChanged = true;
                        }
                        if (billChanged) {break;}
                    }
                    break;
                }
                else {billToChange = -1;}
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
	}

    @Override
    public void keyReleased(KeyEvent e) {
        }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
    }
}
