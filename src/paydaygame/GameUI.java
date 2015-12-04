/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
//Yes, I know these are unused.
import java.net.URL;
import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GameUI extends JPanel {
    
    JFrame toJFrame;
    Interface toInterface;
    
    private BufferedImage boardImage;
    
    public GameUI (int noOfPlayers, Interface i) {
        toJFrame = new JFrame("Payday. The Game. It's Cool.");
        toJFrame.add(this);
        toJFrame.setSize(1200, 1000);
        toJFrame.setVisible(true);
        toJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        toInterface = i;
        
        URL boardImgUrl = this.getClass().getResource("resources/board.png");
        
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
        int x = 5;
        int y = 10;
        }
    }