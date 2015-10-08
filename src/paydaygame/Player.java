/*
 * Copywrite Galen Hite 2015. Reproduce at your peril.
 */
package paydaygame;

import java.util.Random;
import java.util.LinkedList;

public class Player {
    //Interface must call the construction of as many of these as are needed.
    Interface toInterface;
    Board toBoard;
    Mail toMail;
    int cash = 5000;
    int boardPosition = 0;
    int month = 1;
    int moveAhead;
    int ID;
    boolean finished = false;
    Deal toDeal;
    LinkedList myDealCards;
    
    public Player(Interface i, Board b, int in, Mail m, Deal d) {
        toInterface = i;
        toBoard = b;
        ID = in;
        toMail = m;
        toDeal = d;
        System.out.println("Player " + ID + " has been created!");
        myDealCards = new LinkedList();
    }
    
    public void takeYourTurn() {
        System.out.println("****************************************************");
        System.out.println("Player " + ID + " has started his turn!");
        System.out.println("At the start of his turn, he had $" + cash);
        int movement = new Random().nextInt(5)+1;
        System.out.println("He rolled a " + movement);
        if (boardPosition + movement >= 31) {boardPosition = 31;}
        else {boardPosition = boardPosition + movement;}
        System.out.println("He moves to space " + boardPosition);
        String thingWeDo = toBoard.getResult(boardPosition);
        System.out.println("He's landed on " + thingWeDo + "!");
        switch (thingWeDo) {
            case "Mail":
                int noOfCards = toBoard.additionalData[boardPosition];
                System.out.println("Player " + ID + " draws " + noOfCards + " Mail Cards!");
                for (int index = 1; index <= noOfCards; index++) {
                    MailCard card = toMail.nextCard();
                    this.doMailCard(card);
                } 
                break;
            case "SweepstakesTirage":
                //cash = cash + 5000;
                this.adjustBalance(-5000);
                System.out.println("Player " + ID + " has won the SweepstakesTirage!");
                break;
            case "Deal":
                    DealCard card = toDeal.nextCard();
                    System.out.println("Player " + ID + " draws a deal card!");
                    this.buyDealCard(card, 0); //Query Deal Class for cards
                break;
            case "Lotto":
                System.out.println("The lottery has started!");
                //cash = cash - 100;
                System.out.println("Each player will contribute $100 to the pool! The bank will contribute $1000!");
                //this.adjustBalance(100);
                toInterface.allPayersPlay(-100);
                Player winnerL = toInterface.getOtherPlayer(-1);
                System.out.println("Player " + winnerL.ID + " has won the lottery!");
                System.out.println("He recives a total of $" + (1000+(100*toInterface.noOfPlayers)));
                winnerL.adjustBalance(-1000-(100*toInterface.noOfPlayers));
                break;
            case "Pay":
                //cash = cash - toBoard.additionalData[boardPosition];/* - Query additional data for amount*/;
                this.adjustBalance(toBoard.additionalData[boardPosition]);
                System.out.println("Thanks to where he landed, Player " + ID + " payed through the nose!");
                break;
            case "RadioShow":
                System.out.println("A Phone In Radio Show has started!");
                Player winnerRS = toInterface.getOtherPlayer(-1);
                System.out.println("Player " + winnerRS.ID + " has won the Radioshow! He gets $1000!");
                winnerRS.adjustBalance(-1000);
                break;
            case "Buyer":
                //Sell highest price deal card
                this.sellDealCard();
                break;
            case "Birthday":
                System.out.println("It's Player " + ID + "'s Birthday!");
                //cash = cash + toInterface.noOfPlayers*100;/*+ number of players playing x 100*/;
                System.out.println("He collected $100 from each player, bringing his total takings to " + toInterface.noOfPlayers*100);
                this.adjustBalance(toInterface.noOfPlayers*-100); //OTHER PLAYERS NOT LOSING MONEY HERE YET!
                break;
            case "Yardsale":
                System.out.println("Player " + ID + "has gone to a yard sale. He found a great deal!");
                DealCard yardCard = toDeal.nextCard();
                this.buyDealCard(yardCard, new Random().nextInt(5)+1);//same as Deal but buy for random number (<7) x 100
                break;
            case "WalkForChar":
                System.out.println("Player " + ID + " cruelly forced his friends to Walk For Charity!"
                        + " They all have to pay $100 x 1d6");
                //Everyone else pays 100xroll
                int money = (new Random().nextInt(5)+1)*100;
                System.out.println("The entry fee for this walk is $" + money);
                toInterface.allPayersPlay(money);
                //cash = cash + (money);
                this.adjustBalance(-money);
                break;
            case "Payday":
                System.out.println("Player " + ID + " has landed on PAYDAY!");
                //cash = cash + 3500;
                this.adjustBalance(-3500);
                month++;
                System.out.println("He is now in month " + month);
                boardPosition = 0;
                if (month >= toInterface.noOfMonths) {finished = true; toInterface.areWeDone();}
                break;
        }
        System.out.println("At the end of his turn, Player " + ID + " had $" + cash);
        
    }
    
    public void doMailCard(MailCard c) {
        int amount = c.getAmount();
        String toWhom = c.getToWhom();
        boolean move = c.getMove();
        //System.out.println("It's paramiters are " + amount + " and " + toWhom /*It would be nice if toWhom = None, return that, rather than blank*/ + " and " + move);
        if (move) {
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
                case "Buyer":
                    //Sell highest price deal card
                    System.out.println("It was a Buyer Space!");
                    this.sellDealCard();
                    break;
            }
        }
        else {
            switch (toWhom) {
                case "pay":
                    //cash = cash + amount;
                    System.out.println("Player " + ID + " Payed a bill for " + amount);
                    this.adjustBalance(-amount);
                    break;
                case "player":
                    Player payee = toInterface.getOtherPlayer(ID);
                    //cash = cash + amount;
                    System.out.println("Player " + payee.ID + " payed Player " + ID + " $" + amount);
                    this.adjustBalance(-amount);
                    payee.adjustBalance(amount);
                    break;
            }
        }
    }
    
    public void buyDealCard(DealCard d, int i) {
        int buyPrice;
        if (i == 0) {buyPrice = d.getbuyPrice();}
        else {buyPrice = i*100;}
        int sellPrice = d.getsellPrice();
        d.describeYourself();
        if (cash > buyPrice) {//cash = cash - buyPrice;
            System.out.println("Player " + ID + " bought a Deal!");
            this.adjustBalance(buyPrice);
            myDealCards.add(d);
        }
        else {System.out.println("Oh no! Player " + ID + " couldn't aford his deal!");}
    }
    
    public void sellDealCard() {
        int highestPrice = 0;
        int indexOfHighestPrice = 0;
        boolean empty = myDealCards.isEmpty();
        if (empty) {System.out.println("Oh No! Player " + ID + " Didn't have any deal cards!"); return;} 
        for (int index = 0; index < myDealCards.size(); index++) {
            DealCard card = (DealCard) myDealCards.get(index);
            if (card.getsellPrice() > highestPrice) {highestPrice = card.getsellPrice(); indexOfHighestPrice = index;} 
        }
        //cash = cash + highestPrice;
        this.adjustBalance(-highestPrice);
        myDealCards.remove(indexOfHighestPrice);
    }
    
    public void adjustBalance(int a) {
        cash = cash - a;
        System.out.println("Player " + ID + "'s Balance is now $" + cash);
        toInterface.printCash(cash, month, ID);
    }
    
    public boolean getFinished() {
        if (finished) {System.out.println("Player " + ID + " is finished!"); this.adjustBalance(-0);}
        else {System.out.println("Player " + ID + " is not finished"); this.adjustBalance(-0);}
        return finished;
    }
    
    public int getCash() {
        return cash;
    }
    
}
