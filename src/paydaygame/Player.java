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
    GameUI toGameUI;
    int cash = 5000; //Players start with $5000
    int boardPosition = 0; //Players start on the Start Square.
    int month = 1; //Players start in month 1.
    int moveAhead;
    int ID;
    boolean finished = false; //To finish, you must first play!
    Deal toDeal;
    LinkedList handOfDealCards;
    boolean moving;
    
    public Player(Interface i, Board b, int in, Mail m, Deal d) {
        /*Variables are initialized.*/
        toInterface = i;
        toBoard = b;
        ID = in;
        toMail = m;
        toDeal = d;
        System.out.println("Player " + ID + " has been created!");
        handOfDealCards = new LinkedList();
        moving = false;
        
    }
    
    public void takeYourTurn() { /*The most important method in the game. Since
        it's quite complicated, a walkthrough has been added. This method is 
        called by the loop running in Interface.startGame.*/
        this.sleep(1000);
        toInterface.passString("*************************************************");
        toInterface.passString("Player " + ID + " has started his turn!");
        this.sleep(1000);
        toInterface.passString("At the start of his turn, he had $" + cash);
        this.sleep(1000);
        int movement = new Random().nextInt(6)+1; //The rolling of the die
        //The die flashes through several random phases... This really isn't working yet.
        toInterface.passString("Player " + ID + " picks up the die! He readys it, then dramatically throws it to the board!");
        this.sleep(1000);
        toGameUI.roll(movement);
        this.sleep(1000);
        toInterface.passString("He rolled a " + movement);
        if (movement == 6) {this.adjustBalance(-toInterface.jackpot, false);
            toInterface.passString("Player " + ID + " rolled a 6! He wins the jackpot, taking home $" + toInterface.jackpot);
            this.sleep(1000);
            toInterface.jackpot = 0;}
        if (boardPosition + movement >= 31) {boardPosition = 31;}
        else {boardPosition = boardPosition + movement;}/*Determines whether 
        this move will take the player beyond 31, where every player must stop. Then, moves.*/
        toGameUI.moveCounter(ID, boardPosition);
        while (toGameUI.counterMoving[ID]) {
            this.sleep(100); //DO NOTHING!
        }
        toInterface.passString("He moves to space " + boardPosition);
        this.sleep(1000);
        String thingWeDo = toBoard.getResult(boardPosition); //Queries Board for the action to be taken on this space.
        toInterface.passString("He's landed on " + thingWeDo + "!");
        this.sleep(1000);
        switch (thingWeDo) { //Does something based on what is returned by Board
            case "Mail": //Draws cards from the Mail deck, running doMailCard each time.
                int noOfCards = toBoard.additionalData[boardPosition];
                toInterface.passString("Player " + ID + " draws " + noOfCards + " Mail Cards!");
                this.sleep(1000);
                for (int index = 1; index <= noOfCards; index++) {
                    MailCard card = toMail.nextCard();
                    this.doMailCard(card);
                } 
                break;
            case "SweepstakesTirage": //Gains $5000
                this.adjustBalance(-5000, false);
                toInterface.passString("Player " + ID + " has won the SweepstakesTirage!");
                this.sleep(1000);
                break;
            case "Deal": //Draws a card from the Deal deck, and calls buyDealCard
                DealCard card = toDeal.nextCard();
                toInterface.passString("Player " + ID + " draws a deal card!");
                this.sleep(1000);
                this.buyDealCard(card, 0); //Query Deal Class for cards
                break;
            case "Lotto": //Plays the lottery.
                toInterface.passString("The lottery has started!");
                this.sleep(1000);
                toInterface.passString("Each player will contribute $100 to the pool! The bank will contribute $1000!");
                this.sleep(1000); //Move money around HERE.
                //this.adjustBalance(100);
                toInterface.allPayersPlay(-100, false);
                Player winnerL = toInterface.getOtherPlayer(-1); //Calls getOtherPlayer in interface, passing -1 to cause any player to be a possible return.
                toInterface.passString("Player " + winnerL.ID + " has won the lottery!");
                this.sleep(1000);
                toInterface.passString("He recives a total of $" + -(1000+(100*toInterface.noOfPlayers)));
                this.sleep(1000); //Move money around HERE.
                winnerL.adjustBalance(-1000-(100*toInterface.noOfPlayers), false);
                break;
            case "Pay": //Called by several squares, this causes the simple reduction of a Player's money.
                //cash = cash - toBoard.additionalData[boardPosition];/* - Query additional data for amount*/;
                this.adjustBalance(toBoard.additionalData[boardPosition], true);
                toInterface.passString("Thanks to where he landed, Player " + ID + " payed through the nose!"); //It's time to make this more specific.
                this.sleep(1000);
                break;
            case "RadioShow": //A random player wins $1000. In the game this was determined by the first player to roll a 3. We ditched that.
                toInterface.passString("A Phone In Radio Show has started!");this.sleep(1000);
                this.sleep(1000);
                Player winnerRS = toInterface.getOtherPlayer(-1);
                toInterface.passString("Player " + winnerRS.ID + " has won the Radioshow! He gets $1000!");
                this.sleep(1000);
                winnerRS.adjustBalance(-1000, false);
                break;
            case "Buyer": //This causes the player to sell the highest priced DealCard he has. It deverts to sellDealCard.
                //Sell highest price deal card
                toInterface.passString("Player " + ID + " has found a buyer for his deal!");
                this.sleep(1000);                
                this.sellDealCard();
                break;
            case "Birthday": //The player gets $100 from each other player.
                toInterface.passString("It's Player " + ID + "'s Birthday!");
                this.sleep(1000);
                //cash = cash + toInterface.noOfPlayers*100;/*+ number of players playing x 100*/;
                toInterface.passString("He collected $100 from each player, bringing his total takings to " + -toInterface.noOfPlayers*100);
                this.sleep(1000);
                this.adjustBalance(toInterface.noOfPlayers*-100, false);
                toInterface.allPayersPlay(toInterface.noOfPlayers*-100, false);
                break;
            case "Yardsale": //Allows the Player to aquire a DealCard increadably cheeply.
                toInterface.passString("Player " + ID + "has gone to a yard sale. He found a great deal!");
                this.sleep(1000);
                DealCard yardCard = toDeal.nextCard();
                this.buyDealCard(yardCard, new Random().nextInt(5)+1);//same as Deal but buy for random number (<7) x 100
                break;
            case "WalkForChar": //Causes everyone else to pay $100 x 1d6
                toInterface.passString("Player " + ID + " cruelly forced his friends to Walk For Charity!"
                        + " They all have to pay $100 x 1d6");
                this.sleep(1000);
                //Everyone else pays 100xroll
                int money = (new Random().nextInt(5)+1)*100;
                toInterface.passString("The entry fee for this walk is $" + money);
                this.sleep(1000);
                toInterface.allPayersPlay(money, true);
                //cash = cash + (money);
                this.adjustBalance(-money, true);
                break;
            case "Payday": //Player aquires $3500 and checks if the game is over.
                toInterface.passString("Player " + ID + " has landed on PAYDAY!");
                this.sleep(1000);
                //cash = cash + 3500;
                this.adjustBalance(-3500, false);
                month++;
                toInterface.passString("He is now in month " + month);
                this.sleep(1000);
                boardPosition = 0;
                toGameUI.moveCounter(ID, boardPosition);
                if (month >= toInterface.noOfMonths) {finished = true; toInterface.areWeDone();}
                break;
        }
        toInterface.passString("At the end of his turn, Player " + ID + " had $" + cash); //Cash is printed at the end of each turn.
        
    }
    
    public void doMailCard(MailCard c) { /*This method exicutes a drawn MailCard.*/
        int amount = c.getAmount(); //First it aquires all the peramiters.
        String toWhom = c.getToWhom();
        boolean move = c.getMove();
        if (move) { //This exicutes if the card moves the Player to another buyer or dealer space.
            boardPosition = toBoard.nextBOrDSpace(boardPosition);
            toInterface.passString("Player " + ID + "'s mail card moved him to the next buyer or dealer space!");
            this.sleep(1000);
            toGameUI.moveCounter(ID, boardPosition);
            while (toGameUI.counterMoving[ID]) {
                this.sleep(100); //DO NOTHING!
            }
            String thingWeDo = toBoard.getResult(boardPosition);
            switch (thingWeDo) {
                case "Deal":
                    toInterface.passString("It was a Deal Space!");
                    this.sleep(1000);
                    DealCard card = toDeal.nextCard();
                    toInterface.passString("Player " + ID + " draws a deal card!");
                    this.sleep(1000);
                    this.buyDealCard(card, 0); //Query Deal Class for cards
                    break;
                case "Buyer": //Sell highest price deal card
                    toInterface.passString("It was a Buyer Space!");
                    this.sleep(1000);
                    this.sellDealCard();
                    break;
            }
        }
        else { //The method does not end here, even though it looks like it.
            switch (toWhom) {
                case "pay": //Basic bills cause the Player to pay money to the bank
                    //cash = cash + amount;
                    toInterface.passString("Player " + ID + " Payed a bill for " + amount);
                    this.sleep(1000);
                    this.adjustBalance(-amount, true); //ALERT! ROGUE - SIGN DETECTED!
                    break;
                case "player": //Players pay money to other players. Sometimes the current Player is on the receiving end.
                    Player payee = toInterface.getOtherPlayer(ID);
                    //cash = cash + amount;
                    toInterface.passString("Player " + payee.ID + " payed Player " + ID + " $" + amount);
                    this.sleep(1000);
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
            toInterface.passString("Player " + ID + " bought a Deal!");
            this.sleep(1000);
            this.adjustBalance(buyPrice, true);
            handOfDealCards.add(d);
        }
        else {toInterface.passString("Oh no! Player " + ID + " couldn't aford his deal!");
        this.sleep(1000);}
    }
    
    public void sellDealCard() { /*This method completes the sale of a DealCard,
        and removes it from the Player's handOfDealCards. The current logic is 
        that the Player will always sell his highest sell price DealCard.*/
        int highestPrice = 0;
        int indexOfHighestPrice = 0;
        boolean empty = handOfDealCards.isEmpty();
        if (empty) {toInterface.passString("Oh No! Player " + ID + " didn't have any deal cards!");
        this.sleep(1000);
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
        toInterface.passString("Player " + ID + "'s Balance is now $" + cash);
        this.sleep(1000);
        toInterface.printCash(cash, month, ID);
        if (bank) {toInterface.jackpot = toInterface.jackpot+a;
            toInterface.passString("Thanks to the type of transaction, " + a + " was added to the Jackpot!");
            this.sleep(1000);
            toInterface.passString("It's now $" + toInterface.jackpot);
            this.sleep(1000);}
    }
    
    public boolean getFinished() { //Basic getters.
        if (finished) {toInterface.passString("Player " + ID + " is finished!"); this.adjustBalance(-0, false);
            this.sleep(1000);}
        else {toInterface.passString("Player " + ID + " is not finished"); this.adjustBalance(-0, false);
            this.sleep(1000);}
        return finished;
    }
    
    public int getCash() {
        return cash;
    }
    
    public int getBoardPosition() {
        return boardPosition;
    }
    
    public void sleep (int time) {
        try {Thread.sleep(time);}
            catch (java.lang.InterruptedException i) {System.out.println("BLAM! SLEEP EXCEPTION #1!");}
    }
    
    public void setGameUI(GameUI g) {
        toGameUI = g;
    }
    
}
