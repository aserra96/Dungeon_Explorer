package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Armoury Parent class which holds all 
 * values of equipment in the game
 */
import java.util.Random;

public class Armory {
    static Random generator = new Random();
    static int DEX = 0;
    static int STR = 0;
    static int DEF = 0;
    static int ATT = 0;
    //Edits stats depending on equipment
    static void editAbilities() {
        DEX = 0; STR = 0; DEF = 0; ATT = 0;

        for (int place = 0; place < 2; place++) {
            if (Interface.equipment[place] == 11)
                DEX += 2;
            if (Interface.equipment[place] == 12)
                DEX -= 5;
            if (Interface.equipment[place] == 13)
                STR += 2;
            if (Interface.equipment[place] == 14)
                STR -= 5;
            if (Interface.equipment[place] == 15)
                DEF += 2;
            if (Interface.equipment[place] == 16)
                DEF -= 5;
        }
        if (Interface.equipment[3] == 21)
            ATT += 1;
        if (Interface.equipment[3] == 22)
            ATT += 3;
        if (Interface.equipment[3] == 23)
            ATT += 5;
        if (Interface.equipment[2] == 31)
            DEF += 1;
        if (Interface.equipment[2] == 32)
            DEF += 3;
        if (Interface.equipment[2] == 33)
            DEF += 5;
        if (Interface.equipment[4] == 41)
            DEF += 1;
        if (Interface.equipment[4] == 42)
            DEF += 3;
        if (Interface.equipment[4] == 43)
            DEF += 5;
    }
}