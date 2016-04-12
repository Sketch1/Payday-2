/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

import java.awt.Graphics;

public class UIThread implements Runnable {
    
    GameUI toGameUI;
    Interface toInterface;
    String lockObj;
    
    public UIThread(String l) {
        lockObj = l;
    }
    
    public void setToInterface(Interface i) {
        toInterface = i;
    }
    
    public void setToGameUI(GameUI g) {
        toGameUI = g;
    }
    
    @Override
    public void run() {
        System.out.println("UIThread running");
        while (!toInterface.finished) {
            Graphics myGraphics = toGameUI.getGraphics();
            toGameUI.refresh(myGraphics);
            //toGameUI.render(myGraphics);
            //toGameUI.repaint();
            if (toInterface.devMode) {System.out.println("Repainted.");}
            try {Thread.sleep(25);}
            catch (java.lang.InterruptedException error) {System.out.println("BLAM! MENU RUN EXCEPTION!");}
        }
    }   
}
