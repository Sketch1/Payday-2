/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard extends KeyAdapter implements KeyListener {

    GameUI toGameUI;

    public KeyBoard(){
       super(); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Released");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Typed");
    }
}