package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the battle class which calculates the damage done by the enemy and player
 */
import java.util.Random;

public class Battle {

    private static Random generator = new Random();

    static void enemyAttack(Enemies enemy) {
    	//takes stats of the enemy and calculates it to damage towards the player
        Armory.editAbilities();
        int strength = generator.nextInt(enemy.strength_points + 5 * Character.present_level);
        int defence = generator.nextInt(Character.defence_points + Armory.DEF) / 2;
        int true_damage = strength - defence;
        if (generator.nextInt(100) <= (Character.dexterity_points + Armory.DEX))
            Interface.newEvent("You dodged the attack.");
        else if (true_damage <= 0)
            Interface.newEvent("You blocked the attack.");
        else {
            Interface.newEvent(enemy.type + " attacks for : " + true_damage);
            Character.modifyHealth(-true_damage);
        }
    }

    static void characterAttack(Enemies enemy) {
    	//takes stats of the player and calculates it to damage towards the enemy
        Armory.editAbilities();
        enemy.is_under_attack = true;
        int strength = generator.nextInt(Character.strength_points + Armory.STR);
        int defence = generator.nextInt(enemy.defence_points) / 2;
        int true_damage = strength - defence + Armory.ATT;
        //effects of debuffs
        if (Potions.character_confused > 0 || Potions.character_paralyze > 0) {
            Interface.newEvent("You missed");
        } else if (generator.nextInt(100) <= enemy.dexterity_points) {
            Interface.newEvent(enemy.type + " dodged the attack.");
        } else if (true_damage <= 0)
            Interface.newEvent(enemy.type + " blocked the attack.");
        else {
            Interface.newEvent("Character attacks for : " + true_damage);
            enemy.health_points -= (true_damage);
        }
        if (enemy.health_points < 0)
            enemy.health_points = 0;
    }
}