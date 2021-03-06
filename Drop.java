package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Drop class as it states thsi will drop
 */ 
import java.util.Random;

public class Drop {

    int x_position;
    int y_position;
    private int treasure;
    //drops random item
    Drop(int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;

        Random generator = new Random();
        int random = generator.nextInt(140);

        if (random < 70)
            treasure = Potions.dropPotion();
        else if (random < 90)
            treasure = 8;
        else if (random < 110)
            treasure = 9;
        else if (random < 115)
            treasure = 10;
        else if (random < 125)
            treasure = Rings.dropRing();
        else if (random < 130)
            treasure = Swords.dropSword();
        else if (random < 135)
            treasure = Armors.dropArmor();
        else if (random < 140)
            treasure = Shields.dropShield();
    }
    //drops random item for treasure chests
    Drop(int x_position, int y_position, int treasure) {
        this.x_position = x_position;
        this.y_position = y_position;
        if (treasure > 0 && treasure < 8)
            this.treasure = Potions.nextPotion(treasure - 1) + 1;
        else if (treasure == 8 || treasure == 9 || treasure == 10)
            this.treasure = treasure;
        else if (treasure >= 11 && treasure <= 16)
            this.treasure = treasure;
        else if (treasure == 21 || treasure == 22 || treasure == 23)
            this.treasure = treasure;
        else if (treasure == 31 || treasure == 32 || treasure == 33)
            this.treasure = treasure;
        else if (treasure == 41 || treasure == 42 || treasure == 43)
            this.treasure = treasure;
    }
    //method to check if treasure exists 
    void checkTreasure() {
        if (treasure > 0 && treasure < 8)
            Interface.newItem(Potions.prevPotion(treasure - 1) + 1);
        else if (treasure == 8 || treasure == 9 || treasure == 10)
            Interface.newItem(treasure);
        else if (treasure >= 11 && treasure <= 16)
            Interface.newItem(treasure);
        else if (treasure == 21 || treasure == 22 || treasure == 23)
            Interface.newItem(treasure);
        else if (treasure == 31 || treasure == 32 || treasure == 33)
            Interface.newItem(treasure);
        else if (treasure == 41 || treasure == 42 || treasure == 43)
            Interface.newItem(treasure);
    }
}

