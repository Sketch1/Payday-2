/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;


public class TopLevel {
    
    public static void main(String args[]) { /*This is the main method, which 
        sets up the GUI, and then awaits the user's pressing the goButton.*/
        String lock = "Lock";
        UIThread toUIThread = new UIThread(lock); 
        Thread thread2 = new Thread(toUIThread);
        Thread thread1 = new Thread(new Interface(lock, toUIThread, thread2));
        thread1.start();       
    }
    
}
