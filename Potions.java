package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the potion class which keeps track what potion does what and the
 * effect done on the player
 */
import java.util.Arrays;
import java.util.Random;

public class Potions {

    static boolean[] potion_known = new boolean[7];
    private static int randomize;
    static int character_paralyze = 0;
    static int character_confused = 0;
    static int character_harm = 0;
    static int character_full = 0;
    private static Random generator = new Random();
    
    Potions() {
        Arrays.fill(potion_known, false);
        randomize = generator.nextInt(7);
    }
    
    //maintains the 7 types of potiuons available as color of potions alligns
    //with effect
    static void mixtureType(int index) {

        int type = Math.floorMod((randomize + index), 7);
        String[] Potion = new String[7];
        Potion[0] = "confusion";
        Potion[1] = "paralytic gas";
        Potion[2] = "strength";
        Potion[3] = "dexterity";
        Potion[4] = "healing";
        Potion[5] = "experience";
        Potion[6] = "harming";
        String[] colors = new String[7];
        colors[0] = "yellow";
        colors[1] = "blue";
        colors[2] = "red";
        colors[3] = "purple";
        colors[4] = "green";
        colors[5] = "aqua";
        colors[6] = "orange";
        //if the potion is known or unkown this is printed 
        if (!potion_known[index])
            Interface.newEvent("A " + colors[index] + " Potion. The effects are unknown to you.");
        else
            Interface.newEvent("A " + Potion[type] + " Potion. Are you sure you want to drink it?");
    }
    
    //Method for drinking the potion
    static void drinkPotion(int index) {
    	//depending on the index of the potion if else statements will be carries out and 
    	//effects will be added to charcter (future refrence try to use switch statement if possible)
        int type = Math.floorMod((randomize + index), 7);
        if (type == 0) {
            Interface.newEvent("What is happening?");
            character_confused += 15;
        } else if (type == 1) {
            Interface.newEvent("Probably nothing happened ... I guess.");
            character_paralyze += 10;
        } else if (type == 2) {
            Interface.newEvent("New found strength surges through your body.");
            Character.strength_points += 2;
        } else if (type == 3) {
            Interface.newEvent("You feel much more skillful.");
            Character.dexterity_points += 2;
        } else if (type == 4) {
            Interface.newEvent("Delicious and refreshing. Do I have more of it?");
            Character.health_points = Character.max_health;
            character_paralyze = 0;
            character_confused = 0;
            character_harm = 0;
        } else if (type == 5) {
            Interface.newEvent("The enlightenment of the ancestors is coming upon you.");
            Character.experience(Character.next_level - Character.experience);
        } else if (type == 6) {
            Interface.newEvent("Pain stabs your body. It was a poison.");
            character_harm += 25;
        }
        potion_known[index] = true;
    }
    
    //identify method if true will update index type for potion
    static void identify(int index) {
        potion_known[index] = true;
        mixtureType(index);
    }
    
    //method to drop potion
    static int dropPotion() {

        int random = generator.nextInt(60);

        if (random < 10)
            return 2;
        else if (random < 15)
            return 3;
        else if (random < 20)
            return 4;
        else if (random < 45)
            return 5;
        else if (random < 50)
            return 6;
        else
            return 7;
    }
    
    //records prev potion
    static int prevPotion(int numOf) {
        return Math.floorMod((numOf - randomize), 7);
    }
    
    //records next potion
    static int nextPotion(int numOf) {
        return Math.floorMod((numOf + randomize), 7);
    }
}
