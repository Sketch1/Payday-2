/*
 * Copyright 2015 Galen Hite. Reproduce at your peril.
 */
package paydaygame;

/*This is the Deal Class, which is an extention of Deck. It contains the Deal 
Deck, an arry of 24 DealCards. It also contains the nextCard method, which
allows Players to draw from the deck.*/

public class Deal extends Deck {
    
    DealCard deck[];
    int nextCard = 0;
    int deckSize = 24;
    
    public Deal(Interface i) { /*This is the consturctor, which creates the Deal Deck, an 
        array containing 24 DealCards, and then shuffles it.*/
        toInterface = i;
        nextCard = 0;
        System.out.println("The Deal Deck has been created!");
        deck = new DealCard[deckSize];
        deck[0] = new DealCard(12000, 20000, 0); //Rocketship Internation
        deck[1] = new DealCard(3500, 6500, 1); //Shepherd's Pie Co
        deck[2] = new DealCard(9500, 14000, 2); //Sahara Safari
        deck[3] = new DealCard(11000, 16000, 3); //Hot Rocks Jewelry Co
        deck[4] = new DealCard(2500, 6500, 4); //Gotcha Security Inc
        deck[5] = new DealCard(2000, 6000, 5); //Burgers 'N' Buns
        deck[6] = new DealCard(10000, 15000, 6); //Rock More Records
        deck[7] = new DealCard(4000, 7500, 7); //Miss Muffet's Tuffet
        deck[8] = new DealCard(6000, 11000, 8); //Louie's Limos Inc
        deck[9] = new DealCard(3500, 6000, 9); //Heavenly Pink Cosmetic Company
        deck[10] = new DealCard(5000, 9000, 10); //Yum Yum Yogurt
        deck[11] = new DealCard(8000, 12000, 11); //Galloping Golf Ball Co
        deck[12] = new DealCard(3500, 7000, 12); //Fish 'N' Cheeps Pet Store
        deck[13] = new DealCard(12000, 20000, 13); //Everglades Condo
        deck[14] = new DealCard(2000, 5000, 14); //Tippytoe Ballet School
        deck[15] = new DealCard(8000, 12000, 15); //Dipsydoodle Noodles
        deck[16] = new DealCard(10000, 18000, 16); //Humungous Hippos Ltd
        deck[17] = new DealCard(7000, 14000, 17); //Teen Jeans Inc
        deck[18] = new DealCard(8000, 12000, 18); //Pete's Pizza Palace
        deck[19] = new DealCard(3000, 6500, 19); //Chuckles Comady Club
        deck[20] = new DealCard(11000, 19000, 20); //Fly-By-Nite Airlines
        deck[21] = new DealCard(3500, 6000, 21); //Pop's Soda Pop Inc
        deck[22] = new DealCard(2000, 5500, 22); //Wheels 'N' Squeals Skateboards
        deck[23] = new DealCard(7500, 13000, 23); //Laughing Gas Inc
        this.shuffle(deckSize, "Deal");
    }
    
    public DealCard nextCard() { /*Next card returns the next card within to
        Player, upon request. It also calls for the DealCard returned to describe
        itself in the output.*/
        if (nextCard == deckSize) {
            this.shuffle(deckSize, "Deal");
            nextCard = 0;}
        nextCard++;
        System.out.println("The Deal deck has returned a card!");
        deck[deckOrder[nextCard-1]].describeYourself();
        return deck[deckOrder[nextCard-1]];
    }
    
}