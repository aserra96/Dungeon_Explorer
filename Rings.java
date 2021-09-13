package cs2012final;

/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Armoury Parent class which holds all 
 * values of equipment in the game
 */
class Rings extends Armory {

    static boolean[] rings_known = new boolean[7];

    static void ringType(int index) {
        String[] rings = new String[7];
        rings[1] = "holy ring of dexterity: DEX + 2";
        rings[2] = "cursed ring of dexterity: DEX - 5";
        rings[3] = "holy ring of strength: STR + 2";
        rings[4] = "cursed ring of strength: STR - 5";
        rings[5] = "holy ring of defence: DEF + 2";
        rings[6] = "cursed ring of defence: DEF - 5";
        //discorver the rings properties
        if (!rings_known[index])
            Interface.newEvent("It is a ring. I don't know it's properties.");
        else
            Interface.newEvent("It is a " + rings[index]);
    }
    //Method for identification
    static void identify(int index) {
        rings_known[index] = true;
        ringType(index);
    }
    //Method to equip ring
    static void useRing(int index) {
        Interface.addStuff(index + 10);
    }
    //Method to drop ring
    static int dropRing() {
        int random = generator.nextInt(6);
        if (random == 0)
            return 11;
        else if (random == 1)
            return 12;
        else if (random == 2)
            return 13;
        else if (random == 3)
            return 14;
        else if (random == 4)
            return 15;
        else
            return 16;
    }
}
