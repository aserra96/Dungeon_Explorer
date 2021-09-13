package cs2012final;

/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Armoury Parent class which holds all 
 * values of equipment in the game
 */
class Armors extends Armory {

    static void armorType(int index) {
        String[] armors = new String[4];
        armors[1] = "Leather, better them what i have";
        armors[2] = "This chain mail what happened to the old owner.";
        armors[3] = "Heavy armour, what is waiting for me as I go down!?";

        Interface.newEvent(armors[index]);
    }
    //Method to use Armor
    static void useArmor(int index) {
        Interface.addStuff(index + 30);
    }
    //Mehod to drop Armour
    static int dropArmor() {
        int random = generator.nextInt(10);
        if (random < 6)
            return 31;
        else if (random < 9)
            return 32;
        else
            return 33;
    }
}
