package cs2012final;

import java.util.Random;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Enemy parent class
 */
public class Enemies {

    private Random generator = new Random();
    boolean is_under_attack = false;
    String type = "";
    Room room;
    int positionX, positionY = 0;
    private int prevX, prevY;
    int experience_points;
    int enemy_type_tile;
    int dexterity_points;
    int strength_points;
    int defence_points;
    int health_points;
    int last_tile;
    //checks if the enemy is alive
    boolean isAlive() {
        if (this.health_points == 0) {
            room.sizes[prevX][prevY] = last_tile;
            Interface.newEvent(type + " died");
            Character.experience(this.experience_points);
            if (generator.nextInt(3) == 0) {
                Drop drop = new Drop(positionX, positionY);
                room.drop_list.add(drop);
            }
            return false;
        }
        if (is_under_attack)
            AssetsLoader.battle(this.positionX, this.positionY);
        is_under_attack = false;
        return true;
    }
    //movement algaritham, follows playerr as long as there within 6 scares of the enemy
    //and if colission of player is found fighting has begun
    void moveAlgorithm() {

        prevX = positionX;
        prevY = positionY;
        room.sizes[positionX][positionY] = last_tile;

        if (Math.sqrt((positionX - Character.x_value) * (positionX - Character.x_value) +
                (positionY - Character.y_value) * (positionY - Character.y_value)) < 6) {
            if (positionX > Character.x_value) {
                if (positionY > Character.y_value) {
                    positionX--;
                    positionY--;
                } else if (positionY < Character.y_value) {
                    positionX--;
                    positionY++;
                } else
                    positionX--;
            } else if (positionX < Character.x_value) {
                if (positionY > Character.y_value) {
                    positionX++;
                    positionY--;
                } else if (positionY < Character.y_value) {
                    positionX++;
                    positionY++;
                } else
                    positionX++;
            } else {
                if (positionY > Character.y_value) {
                    positionY--;
                } else if (positionY < Character.y_value) {
                    positionY++;
                }
            }
        }

        if (room.sizes[positionX][positionY] >= 10 && room.sizes[positionX][positionY] < 20) {
            last_tile = room.sizes[positionX][positionY];
            room.sizes[positionX][positionY] = enemy_type_tile;
        } else {
            if (room.sizes[positionX][positionY] >= 44 && room.sizes[positionX][positionY] <= 47) {
                Battle.enemyAttack(this);
            }
            positionX = prevX;
            positionY = prevY;
            room.sizes[positionX][positionY] = enemy_type_tile;
        }
    }
}

