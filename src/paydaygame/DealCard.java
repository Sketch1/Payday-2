/*
 * Copyright 2015 Galen Hite. Reproduce at your peril.
 */
package paydaygame;

/*This is the DealCard class, which contains all the peramiters of a card from
the Deal deck. It contains getter methods for most paramiters (buyPrice, and 
sellPrice, but not ID). It also contains the describe yourself method.*/

public class DealCard {
    
     int buyPrice;
     int sellPrice;
     int ID;
     String name;
     Interface toInterface;
    
    public DealCard(int c, int s, int n) { /*Constuctor initiallizes class with 
        passed peramiters.*/
        buyPrice = c;
        sellPrice = s;
        ID = n;
        //A catch here, in case ID is somehow bigger than 23, would be good, and would meet a requirement.
    }
    
    public int getbuyPrice() { /*Basic getter methods.*/
        return buyPrice;
    }
    
    public int getsellPrice() {
        return sellPrice;
    }
    
    public void describeYourself() { /*This method uses a long but simple case
        statement to describe what the DealCard is, and how much it is sold for.*/
        switch (ID) {
            case 0:
                name = "Rocketship Internation";
                break;
            case 1:
                name = "Shepherd's Pie Co";
                break;
            case 2:
                name = "Sahara Safari";
                break;
            case 3:
                name = "Hot Rocks Jewlry Co";
                break;
            case 4:
                name = "Gotcha Security Inc";
                break;
            case 5:
                name = "Burgers 'N' Buns";
                break;
            case 6:
                name = "Rock More Records";
                break;
            case 7:
                name = "Miss Muffet's Tuffet";
                break;
            case 8:
                name = "Louie's Limos Inc";
                break;
            case 9:
                name = "Heavenly Pink Cosmetic Company";
                break;
            case 10:
                name = "Yum Yum Yogurt";
                break;
            case 11:
                name = "Galloping Golf Ball Co";
                break;
            case 12:
                name = "Fish 'N' Cheeps Pet Store";
                break;
            case 13:
                name = "Everglades Condo";
                break;
            case 14:
                name = "Tippytoe Ballet School";
                break;
            case 15:
                name = "Dipsydoodle Noodles";
                break;
            case 16:
                name = "Humungous Hippos LTD";
                break;
            case 17:
                name = "Teen Jeans Inc";
                break;
            case 18:
                name = "Pete's Pizza Palace";
                break;
            case 19:
                name = "Chuckles Comady Club";
                break;
            case 20:
                name = "Fly-By-Nite Airlines";
                break;
            case 21:
                name = "Pop's Soda Pop Inc";
                break;
            case 22:
                name = "Wheels 'N' Squeals Skateboards";
                break;
            case 23:
                name = "Laughing Gas Inc";
                break;
        }
        toInterface.passString("He drew '" + name + ".' It costs $" + -buyPrice + " and, if sold, will return $" + -sellPrice + ".");
        try {Thread.sleep(1000);}
            catch (java.lang.InterruptedException error) {System.out.println("BLAM! SLEEP EXCEPTION!");}
        //Danger, danger! toInterface does not exist in this class, hence, no print!
        
    }
    
    public void update (Interface i) {
        toInterface = i;
    }
    
}
