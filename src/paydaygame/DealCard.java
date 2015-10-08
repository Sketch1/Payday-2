/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paydaygame;

public class DealCard {
    
     int buyPrice;
     int sellPrice;
     int ID;
     String name;
    
    public DealCard(int c, int s, int n) {
        buyPrice = c;
        sellPrice = s;
        ID = n;
        //A catch here, in case ID is somehow bigger than 23 would be good, and would meet a requirment.
    }
    
    public int getbuyPrice() {
        return buyPrice;
    }
    
    public int getsellPrice() {
        return sellPrice;
    }
    
    public void describeYourself() {
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
        System.out.println("He drew '" + name + ".' It costs $" + buyPrice + " and, if sold, will return $" + sellPrice + ".");
        
    }
    
}
