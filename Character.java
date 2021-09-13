package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Character Class which holds the position and stats of ethe character
 */
import java.util.ListIterator;

public class Character {

    static int x_value, y_value;
    static int last_tile = 0;
    static int present_room = 0;
    static int present_level = 0;
    static int max_health = 200;
    static int health_points = 200;
    static int strength_points = 15;
    static int dexterity_points = 10;
    static int defence_points = 5;
    static int experience = 0;
    static int next_level = 100;
    static int level = 1;
    static int hunger = 0;
    static boolean is_dead = false;
    static boolean is_attacking = false;
    static boolean action_made = false;
    //Construcor of character
    void createCharacter() {
        Room room = Level.levels_list.get(0).get(0);
        last_tile = room.sizes[room.width / 2][room.height / 2];
        x_value = room.width / 2;
        y_value = room.height / 2;
        room.sizes[x_value][y_value] = 47;
        room.visited = true;
        Interface.inventory[0] = 9;
    }

    //character movement downwards
    void increaseY() {
        nextStep(x_value, y_value + 1);
    }

    //character movement upwards
    void decreaseY() {
        nextStep(x_value, y_value - 1);
    }

    //character movement rightwards
    void increaseX() {
        nextStep(x_value + 1, y_value);
    }

    //character movement leftwards
    void decreaseX() {
        nextStep(x_value - 1, y_value);
    }

    /* ACTION AFTER STEP*/
    private void nextStep(int step_x, int step_y) {

        Room room = Level.levels_list.get(present_level).get(present_room);
        Items.was_clicked = false;

        if (room.sizes[step_x][step_y] == 20)
            nextRoom(step_x, step_y);
        else if (room.sizes[step_x][step_y] == 29)
            nextLevel();
        else if (room.sizes[step_x][step_y] == 30)
            prevLevel();
        else if (room.sizes[step_x][step_y] == 25) {
            for (Chests chest : room.chests_list) {
                if (chest.x_position == step_x && chest.y_position == step_y) {
                    chest.checkTreasure();
                }
            }
        } else if (room.sizes[step_x][step_y] < 70 || room.sizes[step_x][step_y] > 99) {
            if (Potions.character_paralyze == 0) {
                room.sizes[x_value][y_value] = last_tile;
                last_tile = room.sizes[step_x][step_y];
                if (step_x == x_value - 1)
                    room.sizes[step_x][step_y] = 44;
                if (step_x == x_value + 1)
                    room.sizes[step_x][step_y] = 45;
                if (step_y == y_value - 1)
                    room.sizes[step_x][step_y] = 46;
                if (step_y == y_value + 1)
                    room.sizes[step_x][step_y] = 47;

                ListIterator<Drop> iterator = room.drop_list.listIterator();
                while (iterator.hasNext()) {
                    Drop drop = iterator.next();
                    if (drop.x_position == step_x && drop.y_position == step_y) {
                        if (Interface.inventory[9] == 0) {
                            drop.checkTreasure();
                            iterator.remove();
                        } else
                            Interface.newEvent("Inventory full!");
                    }
                }
                x_value = step_x;
                y_value = step_y;
            } else
            	Potions.character_paralyze -= 1;
            buffs();
            action_made = true;
        } else if (room.sizes[step_x][step_y] >= 70 || room.sizes[step_x][step_y] <= 80) {
            for (Enemies enemy : room.enemies_list) {
                if (enemy.room.index == present_room) {
                    if ((step_x) == enemy.positionX && step_y == enemy.positionY) {
                        Battle.characterAttack(enemy);
                        action_made = true;
                    }
                }
            }
        }
    }
    //method for new rooms entered and checks if the room has been explored or if looped
    private void nextRoom(int x, int y) {

        Room former_room = Level.levels_list.get(present_level).get(present_room);
        for (Door door : former_room.doors) {
            if (door.x == x && door.y == y) {
                former_room.sizes[x_value][y_value] = last_tile;
                int whereIWas = present_room;
                present_room = door.where;
                Room new_room = Level.levels_list.get(present_level).get(present_room);
                if (whereIWas == present_room)
                    Interface.newEvent("Strange... I'm back in the same room.");
                else if (new_room.visited)
                    Interface.newEvent("I was already here...");
                else
                    Interface.newEvent("I've never seen this room before...");
                new_room.visited = true;
                for (Door newdoor : Level.levels_list.get(present_level).get(present_room).doors) {
                    if (newdoor.where == whereIWas) {
                        Room newroom = Level.levels_list.get(present_level).get(present_room);
                        last_tile = newroom.sizes[newdoor.posx][newdoor.posy];
                        if (newdoor.wall == 0)
                            newroom.sizes[newdoor.posx][newdoor.posy] = 47;
                        if (newdoor.wall == 1)
                            newroom.sizes[newdoor.posx][newdoor.posy] = 46;
                        if (newdoor.wall == 2)
                            newroom.sizes[newdoor.posx][newdoor.posy] = 44;
                        if (newdoor.wall == 3)
                            newroom.sizes[newdoor.posx][newdoor.posy] = 45;
                        x_value = newdoor.posx;
                        y_value = newdoor.posy;
                    }
                }
            }
        }
    }
    //method for checking the next floor ur on
    private void nextLevel() {
        if (present_level < Level.level_number - 1) {
            Level.levels_list.get(present_level).get(present_room).sizes[x_value][y_value] = last_tile;
            present_level += 1;
            present_room = 0;
            last_tile = 30;
            Room room = Level.levels_list.get(present_level).get(present_room);
            x_value = room.width / 2;
            y_value = room.height / 2;
            room.sizes[x_value][y_value] = 47;
            Interface.newEvent("It is the " + (present_level + 1) + " floor.");
        } else
            Interface.newEvent("There is nothing more here.");
    }
    //method for returningh to previous level and stairs
    private void prevLevel() {
        if (present_level > 0) {
            Level.levels_list.get(present_level).get(present_room).sizes[x_value][y_value] = last_tile;
            present_level -= 1;
            present_room = Level.room_number - 1;
            last_tile = 29;
            Room room = Level.levels_list.get(present_level).get(present_room);
            x_value = room.width / 2;
            y_value = room.height / 2;
            room.sizes[x_value][y_value] = 47;
            Interface.newEvent("I know this area, why am i back?.");
        } else
            Interface.newEvent("I will not leave till my mission is done!.");
    }
    //method for Hunger
    static void hunger() {
        hunger += 1;
        if (hunger == 100)
            Interface.newEvent("You are still full.");
        if (hunger == 200)
            Interface.newEvent("Your stomach is fine.");
        if (hunger == 300)
            Interface.newEvent("You feel normally.");
        if (hunger == 400)
            Interface.newEvent("You are hungry!");
        if (hunger == 500)
            Interface.newEvent("You are starving!");
        if (hunger > 500) {
            modifyHealth(-1);
        }
    }
    //method for health change
    static void modifyHealth(int points) {
        health_points += points;
        if (health_points > max_health)
            health_points = max_health;
        if (health_points <= 0) {
            health_points = 0;
            System.out.println("Character died.");
            is_dead = true;
        }
    }
    //method for buffs
    private static void buffs() {
        if (Potions.character_harm > 0) {
            modifyHealth(-5);
            Potions.character_harm -= 1;
        }
        if (Potions.character_full > 0) {
            modifyHealth(1);
            Potions.character_full -= 1;
        }
    }
    //method for experience
    static void experience(int experience_points) {
        experience += experience_points;
        if (experience >= next_level) {
            level += 1;
            Interface.newEvent("You are now on level " + level);
            experience = experience - next_level;
            next_level += 50;
            strength_points += 1;
            dexterity_points += 1;
            defence_points += 1;
            max_health += 10;
            modifyHealth(50 + 10 * level);
        }
    }
}