package cs2012final;

/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Armoury Parent class which holds all 
 * values of equipment in the game
 */
class Shields extends Armory {

    static void shieldType(int index) {
        String[] shields = new String[4];
        shields[1] = "Weak, wooden shield. It won't stand much time.";
        shields[2] = "Decorated, although not distinguished by anything special.";
        shields[3] = "Huge and beautiful. Nothing will pass through it.";

        Interface.newEvent(shields[index]);
    }
    //Method to eqip Shield
    static void useShield(int index) {
        Interface.addStuff(index + 40);
    }
    //Method to Drop Shield
    static int dropShield() {
        int random = generator.nextInt(10);
        if (random < 6)
            return 41;
        else if (random < 9)
            return 42;
        else
            return 43;
    }
}
