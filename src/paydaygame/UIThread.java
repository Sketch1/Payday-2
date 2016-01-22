/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

import java.awt.Graphics;

public class UIThread extends Thread {
    
    GameUI toGameUI;
    Interface toInterface;
    
    public void update(Interface i, GameUI g) {
        toGameUI = g;
        toInterface = i;
    }
    
    @Override
    public void run() {
        System.out.println("RunUI: 'Called!'");
        while (!toInterface.finished) {
            toGameUI.refresh();
            toGameUI.repaint();
            Graphics myGraphics = toGameUI.getGraphics();
            toGameUI.render(myGraphics);
            if (toInterface.devMode) {System.out.println("Repainted.");}
            try {Thread.sleep(1000);}
            catch (java.lang.InterruptedException error) {System.out.println("BLAM! MENU RUN EXCEPTION!");}
        }
    }   
}
