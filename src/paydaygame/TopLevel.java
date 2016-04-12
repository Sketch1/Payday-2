/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TopLevel /*implements KeyListener*/ extends JPanel {
    
    public static void main(String args[]) { /*This is the main method, which 
        sets up the GUI, and then awaits the user's pressing the goButton.*/
        TopLevel toTopLevel = new TopLevel();
    }
    
    public TopLevel() {
        String lock = "Lock"; 
        UIThread toUIThread = new UIThread(lock); 
        Thread thread2 = new Thread(toUIThread);
        Thread thread1 = new Thread(new Interface(lock, toUIThread, thread2));
        thread1.start();
    }
    
    /*@Override
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
    }*/
}
