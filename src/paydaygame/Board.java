/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paydaygame;

public class Board {
    
    String theBoard[];
    int[] additionalData;
    
    public Board() {
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
    }
        
    public String getResult(int index) {
        return theBoard[index];
    }
    
    public int nextBOrDSpace(int bp) {
        int nextSpace = 31;
        for (int index = bp; index < 31; index++) {
            String test = theBoard[index];
            if (test == "Buyer" || test == "Deal") {nextSpace = index; break;}
        }
        return nextSpace;
    }
    
}
