/*
 * Copywrite Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

/*This is arugably the most important class in the program. Player contains the 
takeYourTurn method, which runs the game's cycle. It also has buyDealCard,
sellDealCard, and doMailCard, to perform its other important functions.*/

import java.util.Random;
import java.util.LinkedList;

public class Player {
    //Global Varibles
    Interface toInterface;
    Board toBoard;
    Mail toMail;
    int cash = 5000; //Players start with $5000
    int boardPosition = 0; //Players start on the Start Square.
    int month = 1; //Players start in month 1.
    int moveAhead;
    int ID;
    boolean finished = false; //To finish, you must first play!
    Deal toDeal;
    LinkedList handOfDealCards;
    
    public Player(Interface i, Board b, int in, Mail m, Deal d) {
        /*Variables are initialized.*/
        toInterface = i;
        toBoard = b;
        ID = in;
        toMail = m;
        toDeal = d;
        System.out.println("Player " + ID + " has been created!");
        handOfDealCards = new LinkedList();
    }
    
    public void takeYourTurn() { /*The most important method in the game. Since
        it's quite complicated, a walkthrough has been added. This method is 
        called by the loop running in Interface.startGame.*/
        System.out.println("*************************************************");
        System.out.println("Player " + ID + " has started his turn!");
        System.out.println("At the start of his turn, he had $" + cash);
        int movement = new Random().nextInt(6)+1; //The rolling of the die
        System.out.println("He rolled a " + movement);
        if (movement == 6) {this.adjustBalance(-toInterface.jackpot, false);
            System.out.println("Player " + ID + " rolled a 6! He wins the jackpot, taking home $" + toInterface.jackpot);
            toInterface.jackpot = 0;}
        if (boardPosition + movement >= 31) {boardPosition = 31;}
        else {boardPosition = boardPosition + movement;} /*Determines whether 
        this move will take the player beyond 31, where every playuer must stop. Then, moves.*/
        System.out.println("He moves to space " + boardPosition);
        String thingWeDo = toBoard.getResult(boardPosition); //Queries Board for the action to be taken on this space.
        System.out.println("He's landed on " + thingWeDo + "!");
        switch (thingWeDo) { //Does something based on what is returned by Board
            case "Mail": //Draws cards from the Mail deck, running doMailCard each time.
                int noOfCards = toBoard.additionalData[boardPosition];
                System.out.println("Player " + ID + " draws " + noOfCards + " Mail Cards!");
                for (int index = 1; index <= noOfCards; index++) {
                    MailCard card = toMail.nextCard();
                    this.doMailCard(card);
                } 
                break;
            case "SweepstakesTirage": //Gains $5000
                //cash = cash + 5000;
                this.adjustBalance(-5000, false);
                System.out.println("Player " + ID + " has won the SweepstakesTirage!");
                break;
            case "Deal": //Draws a card from the Deal deck, and calls buyDealCard
                    DealCard card = toDeal.nextCard();
                    System.out.println("Player " + ID + " draws a deal card!");
                    this.buyDealCard(card, 0); //Query Deal Class for cards
                break;
            case "Lotto": //Plays the lottery.
                System.out.println("The lottery has started!");
                //cash = cash - 100;
                System.out.println("Each player will contribute $100 to the pool! The bank will contribute $1000!");
                //this.adjustBalance(100);
                toInterface.allPayersPlay(-100, false);
                Player winnerL = toInterface.getOtherPlayer(-1); //Calls getOtherPlayer in interface, passing -1 to cause any player to be a possible return.
                System.out.println("Player " + winnerL.ID + " has won the lottery!");
                System.out.println("He recives a total of $" + (1000+(100*toInterface.noOfPlayers)));
                winnerL.adjustBalance(-1000-(100*toInterface.noOfPlayers), false);
                break;
            case "Pay": //Called by several squares, this causes the simple reduction of a Player's money.
                //cash = cash - toBoard.additionalData[boardPosition];/* - Query additional data for amount*/;
                this.adjustBalance(toBoard.additionalData[boardPosition], true);
                System.out.println("Thanks to where he landed, Player " + ID + " payed through the nose!");
                break;
            case "RadioShow": //A random player wins $1000. In the game this was determined by the first player to roll a 3. We ditched that.
                System.out.println("A Phone In Radio Show has started!");
                Player winnerRS = toInterface.getOtherPlayer(-1);
                System.out.println("Player " + winnerRS.ID + " has won the Radioshow! He gets $1000!");
                winnerRS.adjustBalance(-1000, false);
                break;
            case "Buyer": //This causes the player to sell the highest priced DealCard he has. It deverts to sellDealCard.
                //Sell highest price deal card
                this.sellDealCard();
                break;
            case "Birthday": //The player gets $100 from each other player.
                System.out.println("It's Player " + ID + "'s Birthday!");
                //cash = cash + toInterface.noOfPlayers*100;/*+ number of players playing x 100*/;
                System.out.println("He collected $100 from each player, bringing his total takings to " + toInterface.noOfPlayers*100);
                this.adjustBalance(toInterface.noOfPlayers*-100, false);
                toInterface.allPayersPlay(toInterface.noOfPlayers*-100, false);
                break;
            case "Yardsale": //Allows the Player to aquire a DealCard increadably cheeply.
                System.out.println("Player " + ID + "has gone to a yard sale. He found a great deal!");
                DealCard yardCard = toDeal.nextCard();
                this.buyDealCard(yardCard, new Random().nextInt(5)+1);//same as Deal but buy for random number (<7) x 100
                break;
            case "WalkForChar": //Causes everyone else to pay $100 x 1d6
                System.out.println("Player " + ID + " cruelly forced his friends to Walk For Charity!"
                        + " They all have to pay $100 x 1d6");
                //Everyone else pays 100xroll
                int money = (new Random().nextInt(5)+1)*100;
                System.out.println("The entry fee for this walk is $" + money);
                toInterface.allPayersPlay(money, true);
                //cash = cash + (money);
                this.adjustBalance(-money, true);
                break;
            case "Payday": //Player aquires $3500 and checks if the game is over.
                System.out.println("Player " + ID + " has landed on PAYDAY!");
                //cash = cash + 3500;
                this.adjustBalance(-3500, false);
                month++;
                System.out.println("He is now in month " + month);
                boardPosition = 0;
                if (month >= toInterface.noOfMonths) {finished = true; toInterface.areWeDone();}
                break;
        }
        System.out.println("At the end of his turn, Player " + ID + " had $" + cash); //Cash is printed at the end of each turn.
        
    }
    
    public void doMailCard(MailCard c) { /*This method exicutes a drawn MailCard.*/
        int amount = c.getAmount(); //First it aquires all the peramiters.
        String toWhom = c.getToWhom();
        boolean move = c.getMove();
        if (move) { //This exicutes if the card moves the Player to another buyer or dealer space.
            boardPosition = toBoard.nextBOrDSpace(boardPosition);
            System.out.println("Player " + ID + "'s mail card moved him to the next buyer or dealer space!");
            String thingWeDo = toBoard.getResult(boardPosition);
            switch (thingWeDo) {
                case "Deal":
                    System.out.println("It was a Deal Space!");
                    DealCard card = toDeal.nextCard();
                    System.out.println("Player " + ID + " draws a deal card!");
                    this.buyDealCard(card, 0); //Query Deal Class for cards
                    break;
                case "Buyer": //Sell highest price deal card
                    System.out.println("It was a Buyer Space!");
                    this.sellDealCard();
                    break;
            }
        }
        else { //The method does not end here, even though it looks like it.
            switch (toWhom) {
                case "pay": //Basic bills cause the Player to pay money to the bank
                    //cash = cash + amount;
                    System.out.println("Player " + ID + " Payed a bill for " + amount);
                    this.adjustBalance(-amount, true); //ALERT! ROGUE - SIGN DETECTED!
                    break;
                case "player": //Players pay money to other players. Sometimes the current Player is on the receiving end.
                    Player payee = toInterface.getOtherPlayer(ID);
                    //cash = cash + amount;
                    System.out.println("Player " + payee.ID + " payed Player " + ID + " $" + amount);
                    this.adjustBalance(-amount, false);
                    payee.adjustBalance(amount, false);
                    break;
            }
        }
    }
    
    public void buyDealCard(DealCard d, int i) { /*This method completes the
        purchase of a DealCard, and adds it to the Player's handOfDealCards. 
        The current logic is that the Player will buy a DealCard as long as he 
        can afford it.*/
        int buyPrice;
        if (i == 0) {buyPrice = d.getbuyPrice();}
        else {buyPrice = i*100;}
        int sellPrice = d.getsellPrice();
        d.describeYourself();
        if (cash > buyPrice) {//cash = cash - buyPrice;
            System.out.println("Player " + ID + " bought a Deal!");
            this.adjustBalance(buyPrice, true);
            handOfDealCards.add(d);
        }
        else {System.out.println("Oh no! Player " + ID + " couldn't aford his deal!");}
    }
    
    public void sellDealCard() { /*This method completes the sale of a DealCard,
        and removes it from the Player's handOfDealCards. The current logic is 
        that the Player will always sell his highest sell price DealCard.*/
        int highestPrice = 0;
        int indexOfHighestPrice = 0;
        boolean empty = handOfDealCards.isEmpty();
        if (empty) {System.out.println("Oh No! Player " + ID + " didn't have any deal cards!"); 
            return;} 
        for (int index = 0; index < handOfDealCards.size(); index++) {
            DealCard card = (DealCard) handOfDealCards.get(index);
            if (card.getsellPrice() > highestPrice) {highestPrice = card.getsellPrice(); indexOfHighestPrice = index;} 
        }
        //cash = cash + highestPrice;
        this.adjustBalance(-highestPrice, false);
        handOfDealCards.remove(indexOfHighestPrice);
    }
    
    public void adjustBalance(int a, boolean bank) { /*This is the method though which all 
        changes to the Player's balance must pass. The method currently works
        on an inverted system, because when it was created, it was handling only
        certain transactions, where it made more sense to subtract a. Now, though,
        it handles all transactions, and so the code is hard to read. Some day, we
        might fix this.*/
        cash = cash - a;
        System.out.println("Player " + ID + "'s Balance is now $" + cash);
        toInterface.printCash(cash, month, ID);
        if (bank) {toInterface.jackpot = toInterface.jackpot+a;
            System.out.println("Thanks to the type of transaction, " + a + " was added to the Jackpot!");
            System.out.println("It's now $" + toInterface.jackpot);}
    }
    
    public boolean getFinished() { //Basic getters.
        if (finished) {System.out.println("Player " + ID + " is finished!"); this.adjustBalance(-0, false);}
        else {System.out.println("Player " + ID + " is not finished"); this.adjustBalance(-0, false);}
        return finished;
    }
    
    public int getCash() {
        return cash;
    }
    
}
