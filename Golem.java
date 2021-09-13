package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Golem child class which holds the data of Golem
 */
class Golem extends Enemies {

    Golem(Room room, int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.room = room;
        type = "Golem";
        enemy_type_tile = 72;
        health_points = 50;
        strength_points = 20;
        defence_points = 10;
        dexterity_points = 1;
        experience_points = 50;
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 72;
    }
}
