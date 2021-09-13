package cs2012final;

/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Swords class which holds they type of swords in the game
 */
class Swords extends Armory {

    static void swordType(int index) {
        String[] swords = new String[4];
        swords[1] = "It's just a simple dagger. It will gently improves your impact.";
        swords[2] = "It's a short sword. Your attacks will become much stronger.";
        swords[3] = "It's a long sword. You have to have the strength of a warrior to use it.";

        Interface.newEvent(swords[index]);
    }
    //Method to equip swords
    static void useSword(int index) {
        Interface.addStuff(index + 20);
    }
    //Method to drop swords
    static int dropSword() {
        int random = generator.nextInt(10);
        if (random < 6)
            return 21;
        else if (random < 9)
            return 22;
        else
            return 23;
    }
}
