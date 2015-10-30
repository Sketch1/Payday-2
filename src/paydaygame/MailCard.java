/*
 * Copyright 2015 Galen Hite. Reproduce at your peril.
 */
package paydaygame;

/*This is the MailCard class, various instances of which make up the mail deck.
It is not very similar to DealCard, containing only one of the same varibles.
It contains basic getter methods to return various values, and also a method for
describing itself in the output.*/

public class MailCard {
    
    int amount;
    String toWhom;
    boolean move;
    String name;
    
    public MailCard(int a, String t, boolean m, String passedName) {
        /*Constuctor initiallizes class with passed peramiters.*/
        amount = a;
        toWhom = t;
        move = m;
        name = passedName;
    }
    
    public int getAmount() { /*Basic getters*/
        return amount;
    }
    
    public String getToWhom() {
        return toWhom;
    }
    
    public boolean getMove() {
        return move;
    }
    
    public void describeYourself() {/*This method uses a long but simple case
        statement to describe what the DealCard is, and how much it is sold for.*/
        switch (name) {
            case "Move ahead to the next buyer or dealer space!":
                System.out.println("This is a card moving the player ahead to the next buyer or dealer space.");
                break;
            case "Bill":
                System.out.println("This is a bill for $" + amount + " to be payed to the bank.");
                break;
            case "MONSTER CHARGE!":
                System.out.println("THIS IS A GIANT CHARGE FOR LOTS OF MONEY, TOTALLYING ABSALOUTELY NO LESS THAN $" + amount + ".");
                break;
            case "Charity":
                System.out.println("This is a request of cash from a certain charity, totalling $" + amount + ".");
                break;
            case "Mad Money":
                System.out.println("This is a lot of money ($" + amount + "!) from another player! Yeah!");
                break;
            case "Pay A Neighbour":
                System.out.println("This is a bill from another player demanding $" + amount + "! The cad!");
        }
    }
}
