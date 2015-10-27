/*
 * Copyright Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

/*More needs to go here, but: The reason Deck does not contain nextCard is that it would have been too hard to make it compute correctly, with different
numbers of cards in different decks.*/

import java.util.Random;

public class Deck {
    
    int deckOrder[];
    Deal toDealDeck;
    Mail toMailDeck;
    
    public Deck() {
        
    }
    
     public void shuffle(int noOfCards, String deck) {
        deckOrder = new int[noOfCards];
        for (int i=0; i < noOfCards; i++) {
         deckOrder[i] = i;
     }
        int end = noOfCards-1;
        while (end > 0) {
            int number = new Random().nextInt(end+1);
            int temp = deckOrder[number];
            deckOrder[number] = deckOrder[end];
            deckOrder[end] = temp;
            end--;
        }
        System.out.println("The " + deck + " Deck was shuffled!");
        //for (int i=0; i < 24; i++) {System.out.println(dealDeckOrder[i]);}
     }
    
    
    
}
