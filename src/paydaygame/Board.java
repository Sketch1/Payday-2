/*
 * Copyright 2015 Galen Hite. Reproduce at your peril.
 */

package paydaygame;

/*This class contains the board, which is an array of 31 spaces, each of which
has a different type or aspect. It also contains methods for returning the next
space a player will land on, and for moving him ahead to the next Buyer or
Dealer space.*/ 

public class Board {
    
    String theBoard[];
    int[] additionalData;
    
    public Board() { /*This is where the board is created. There are two arrays:
        theBoard contains a string identifying the type of space for processing
        in Player, while additionalData carries other information Player needs,
        either an amount to be payed, or a number of Mail Cards to be drawn.*/
       theBoard = new String[33];
       additionalData = new int[33];
       theBoard[0] = "Start";
       theBoard[1] = "Mail";
       additionalData[1] = 1;
       theBoard[2] = "SweepstakesTirage";
       theBoard[3] = "Mail";
       additionalData[3] = 3;
       theBoard[4] = "Deal";
       theBoard[5] = "Mail";
       additionalData[5] = 2;
       theBoard[6] = "Lotto";
       theBoard[7] = "Pay";
       additionalData[7] = 500;
       theBoard[8] = "RadioShow";
       theBoard[9] = "Buyer";
       theBoard[10] = "Birthday";
       theBoard[11] = "Mail";
       additionalData[11] = 1;
       theBoard[12] = "Deal";
       theBoard[13] = "Lotto";
       theBoard[14] = "Pay";
       additionalData[14] = 400;
       theBoard[15] = "Deal";
       theBoard[16] = "Mail";
       additionalData[16] = 3;
       theBoard[17] = "Buyer";
       theBoard[18] = "Pay";
       additionalData[18] = 600;
       theBoard[19] = "Mail";
       additionalData[19] = 1;
       theBoard[20] = "Lotto";
       theBoard[21] = "Yardsale";
       theBoard[22] = "Mail";
       additionalData[22] = 1;
       theBoard[23] = "Buyer";
       theBoard[24] = "Mail";
       additionalData[24] = 2;
       theBoard[25] = "Deal";
       theBoard[26] = "Buyer";
       theBoard[27] = "Lotto";
       theBoard[28] = "Pay";
       additionalData[28] = 500;
       theBoard[29] = "Buyer";
       theBoard[30] = "WalkForChar";
       theBoard[31] = "Payday";  
       /*Possible idea for the future: Create a BoardSpace class, which can keep
       track of these varibles itself. It could also hold onto an image of 
       itself, if that would be valuble in construction the GUI.*/
    }
        
    public String getResult(int index) { /*This method returns the type of space
        the player has just landed on, for compuation in Player.*/
        return theBoard[index];
    }
    
    public int nextBOrDSpace(int bp) { /*This name stands for Next Buyer Or 
        Dealer space. Relationship to the word "board" is entirely coincidental.
        This method detects whether a buyer space or a dealer space comes next 
        in the board sequince.*/
        int nextSpace = 31;
        for (int index = bp; index < 31; index++) {
            String test = theBoard[index];
            if (test == "Buyer" || test == "Deal") {nextSpace = index; break;}
        }
        return nextSpace;
    }
    
}
