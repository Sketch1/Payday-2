/*
 * Copyright 2015 Galen Hite. Reproduce at your peril.
 */
package paydaygame;

/*This is the Mail class, which contains the array deck. It is very similar to 
the Deal class, and contains the same methods, including the nextCard method, 
which allows Players to draw from the deck.*/

public class Mail extends Deck {
    
    
    MailCard deck[];
    
    int nextCard = 0;
    int deckSize = 49;
    
    public Mail(Interface i) { /*This is the consturctor, which creates the Mail Deck, an 
        array containing the 47 MailCards, and then shuffles it.*/
        toInterface = i;
        System.out.println("The Mail Deck has been created!");
        deck = new MailCard[deckSize];
        deck[0] = new MailCard(0, "", true, "Move ahead to the next buyer or dealer space!");
        deck[1] = new MailCard(0, "", true, "Move ahead to the next buyer or dealer space!");
        deck[2] = new MailCard(-2000, "pay", false, "Bill");
        deck[3] = new MailCard(-4000, "pay", false, "MONSTER CHARGE!");
        deck[4] = new MailCard(0, "", true, "Move ahead to the next buyer or dealer space!");
        deck[5] = new MailCard(-300, "pay", false, "Charity");
        deck[6] = new MailCard(1500, "player", false, "Mad Money");
        deck[7] = new MailCard(-100, "pay", false, "Bill");
        deck[8] = new MailCard(-2000, "player", false, "Pay A Neighbour");
        deck[9] = new MailCard(-300, "player", false, "Pay A Neighbour");
        deck[10] = new MailCard(-200, "pay", false, "Bill");
        deck[11] = new MailCard(-2500, "pay", false, "Bill");
        deck[12] = new MailCard(-600, "pay", false, "Bill");
        deck[13] = new MailCard(200, "player", false, "Mad Money");
        deck[14] = new MailCard(-800, "pay", false, "Bill");
        deck[15] = new MailCard(-1500, "pay", false, "Bill");
        deck[16] = new MailCard(-300, "player", false, "Pay A Neighbour");
        deck[17] = new MailCard(-5000, "pay", false, "Bill");
        deck[18] = new MailCard(-500, "pay", false, "Charity");
        deck[19] = new MailCard(1000, "player", false, "Mad Money");
        deck[20] = new MailCard(-400, "pay", false, "Charity");
        deck[21] = new MailCard(100, "player", false, "Mad Money");
        deck[22] = new MailCard(0, "", true, "Move ahead to the next buyer or dealer space!");
        deck[23] = new MailCard(1500, "player", false, "Mad Money");
        deck[24] = new MailCard(-200, "pay", false, "Bill");
        deck[25] = new MailCard(0, "", true, "Move ahead to the next buyer or dealer space!");
        deck[26] = new MailCard(-200, "player", false, "Pay A Neighbour");
        deck[27] = new MailCard(0, "", true, "Move ahead to the next buyer or dealer space!");
        deck[28] = new MailCard(-1000, "player", false, "Pay A Neighbour");
        deck[29] = new MailCard(-300, "pay", false, "Charity");
        deck[30] = new MailCard(-1000, "pay", false, "MONSTER CHARGE!");
        deck[31] = new MailCard(400, "player", false, "Mad Money");
        deck[32] = new MailCard(-300, "pay", false, "Bill");
        deck[33] = new MailCard(0, "", true, "Move ahead to the next buyer or dealer space!");
        deck[34] = new MailCard(-300, "pay", false, "Bill");
        deck[35] = new MailCard(-3000, "pay", false, "Bill");
        deck[36] = new MailCard(-3000, "pay", false, "MONSTER CHARGE!");
        deck[37] = new MailCard(-1500, "pay", false, "Bill");
        deck[38] = new MailCard(-500, "pay", false, "Bill");
        deck[39] = new MailCard(-400, "pay", false, "Charity");
        deck[40] = new MailCard(2000, "player", false, "Mad Money");
        deck[41] = new MailCard(-300, "pay", false, "Bill");
        deck[42] = new MailCard(-2000, "player", false, "Pay A Neighbour");
        deck[43] = new MailCard(-200, "pay", false, "Charity");
        deck[44] = new MailCard(2000, "player", false, "Mad Money");
        deck[45] = new MailCard(-300, "player", false, "Pay A Neighbour");
        deck[46] = new MailCard(-200, "player", false, "Pay A Neighbour");
        deck[47] = new MailCard(-700, "pay", false, "Bill");
        deck[48] = new MailCard(2000, "player", false, "Mad Money");
        this.shuffle(deckSize, "Mail");      
    }
    
    private void cloakingDevice() /*This method allows me to hide
    the annoying but nessessary commentted out original version of deck, which
    includes comments showing what the various cards are. This could be useful
    in the future, and so I don't want to delete it. This is utterly redundant, 
    and is neither called nor used by anything.*/ {
        /*mailCards = new int[45];
        mailCards[0] = 0; //Indicates "Move ahead to the next buyer or dealer space"
        mailCards[1] = 0;
        mailCards[2] = -2000; //Bill
        mailCards[3] = -4000; //MONSTER CHARGE
        mailCards[4] = 0;
        mailCards[5] = -300; //Charity: Citizens For A Clean Environment
        mailCards[6] = 1500; //From ONE player (Mad Money)
        mailCards[7] = -100; //Bill
        mailCards[8] = -2000; //To A Neighbour
        mailCards[9] = -300; //To A Neighbour
        mailCards[10] = -200; //Bill
        mailCards[11] = -2500; //Bill
        mailCards[12] = -600; //Bill
        mailCards[13] = 200; //From ONE Player (Mad Money)
        mailCards[14] = -800; //Bill
        mailCards[15] = -1500; //Bill
        mailCards[16] = -300; //To A Neighbour
        mailCards[17] = -5000; //Bill
        mailCards[18] = -500; //Charity: Whale Rescue
        mailCards[19] = 1000; //From Player Of Your Choice (Mad Money)
        mailCards[20] = -400; //Charity: Scholarship Drive
        mailCards[21] = 100; //From Player Of Choice (Mad Money)
        mailCards[22] = 0;
        mailCards[23] = 1500; //From Player Of Choice (Mad Money)
        mailCards[24] = -200; //Bill
        mailCards[25] = 0;
        mailCards[26] = -200; //To A Neighbour
        mailCards[27] = 0;
        mailCards[28] = -1000; //To A Neighbour
        mailCards[29] = -300; //Charity: Eagles
        mailCards[30] = -1000; //MONSTER CHARGE
        mailCards[31] = 400; //From player of your choice (Mad Money)
        mailCards[32] = -300; //Bill
        mailCards[33] = 0;
        mailCards[34] = -300; //Bill
        mailCards[35] = -3000; //Bill
        mailCards[36] = -3000; //MONSTER CHARGE!
        mailCards[37] = -1500; //Bill
        mailCards[38] = -500; //Bill
        mailCards[39] = -400; //Charity: Pollution
        mailCards[40] = 2000; //From Player Of Your Choice (Mad Money)
        mailCards[41] = -300; //Bill
        mailCards[42] = -2000; //To A Neighbour
        mailCards[43] = -200; //Charity
        mailCards[44] = 2000; //From Player Of Your Choice (Mad Money)
        mailCards[45] = -300; //To A Neighbour
        mailCards[46] = -200; //To A Neighbour
        mailCards[47] = -700; //Bill
        mailCards[48] = 1500; //Mad Money*/  
    } 
    
    
    public MailCard nextCard() { /*Next card returns the next card within to
        Player, upon request. It also calls for the MailCard returned to describe
        itself in the output.*/
        if (nextCard == deckSize) {
            this.shuffle(deckSize, "Mail");
            nextCard = 0;}
        nextCard++;
        System.out.println("The mail deck has returned a card!");
        deck[deckOrder[nextCard-1]].describeYourself();
        return deck[deckOrder[nextCard-1]];
    }
    
    public int getDeckSize() {
        return deckSize;
    }
    
}
