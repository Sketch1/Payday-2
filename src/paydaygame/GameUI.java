/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameUI extends JPanel {
    
    JFrame toJFrame;
    Interface toInterface;
    
    private BufferedImage boardImage;
    private BufferedImage $100;
    private BufferedImage $500;
    private BufferedImage $1000;
    private BufferedImage $5000;
    private BufferedImage $10000;
    
    public GameUI (int noOfPlayers, Interface i) {
        toJFrame = new JFrame("Payday. The Game. It's Cool.");
        toJFrame.add(this);
        toJFrame.setSize(1200, 1000);
        toJFrame.setVisible(true);
        toJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        toInterface = i;
        int index = 0;
        
        URL boardImgUrl = this.getClass().getResource("resources/board.png");
        URL $100ImgUrl = this.getClass().getResource("resources/$100.jpg");
        URL $500ImgUrl = this.getClass().getResource("resources/$500.jpg");
        URL $1000ImgUrl = this.getClass().getResource("resources/$1000.jpg");
        URL $5000ImgUrl = this.getClass().getResource("resources/$5000.jpg");
        URL $10000ImgUrl = this.getClass().getResource("resources/$10000.jpg");
        while (index < 48) {
            String name = index + "ImgUrl";
            URL name = this.getClass().getResource("resources/Mail/" + index + ".jpg");
        }
        
        try {boardImage = ImageIO.read(boardImgUrl);
           }
        catch (java.io.IOException e) {System.out.println("IO Exception in MainMenu Class");}
    }
    
    public void runUI() {
        while (toInterface.finished) {
            this.refresh();
            this.repaint();
            try {Thread.sleep(10);}
            catch (java.lang.InterruptedException i) {System.out.println("EXCEPTION!");}
        }
    }
    
    public void refresh() {
        //All that refresh does is change the coords of things that are drawn in paint.
        } 
    
    @Override
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(boardImage, 5, 150, this);
        g2d.drawLine(700, 0, 700, 1200);
        g2d.drawLine(0, 145, 1200, 145);
        g2d.drawLine(0, 860, 1200, 860);
        
        }
    }