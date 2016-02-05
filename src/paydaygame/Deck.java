/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

/* Deck is the class from which Deal and Mail inherit most of their methods. Deck
contains shuffle (currently using Fisher-Yates algoithm) and the actual decloration
of the two 'decks'. Other than that, there's not much here yet. The reason Deck 
does not contain nextCard is that it would have been too hard to make it compute
correctly, with different numbers of cards in different decks. This may be changed
in the future.*/

import java.util.Random;

public class Deck {
    
    int deckOrder[];
    Deal toDealDeck;
    Mail toMailDeck;
    Interface toInterface;
    int shuffles = 0;
    
    
    public Deck() {
        /*Deck is not constructed. It is just an idea that other classes refer
        to. I bet there's a name for this...*/
    }
    
     public void shuffle(int noOfCards, String deck) { /*Shuffle is a pretty 
         complexe method. Let's take a look. Passed a number of cards for the 
         deck, and also a name. The name is just for the print out at the end.*/
        deckOrder = new int[noOfCards]; //Creates deckOrder.
        for (int i=0; i < noOfCards; i++) {
         deckOrder[i] = i;
            }
        int end = noOfCards-1;
        while (end > 0) { //This is the actual shuffle part.
            int number = new Random().nextInt(end+1); /*Generates a new random number,
            no greater than the number of cards in the deck.*/
            int temp = deckOrder[number]; /*Creates a temp varible holding the number
            which was occupying the slot in deckOrder.*/
            deckOrder[number] = deckOrder[end]; /*Number at the end of the deckOrder
            moved to the generated slot.*/
            deckOrder[end] = temp; //Other number placed at end of deck.
            end--; //End of deck reduced to eliminate moving of number just moved.
            }
        if(shuffles > 1) {toInterface.passString("The " + deck + " Deck was shuffled!");} //1 not 2 because each subclass recieves its own version of the method.
        if (toInterface.devMode) {for (int i=0; i < 24; i++) {
            System.out.println("(DEV MODE) Here's the order:");
            System.out.println(deckOrder[i]);}}
        shuffles++;
     }
    
}
