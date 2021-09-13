package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Ghost child class which holds the data of Ghost
 */
class Ghost extends Enemies {

    Ghost(Room room, int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.room = room;
        type = "Ghost";
        enemy_type_tile = 73;
        health_points = 5;
        strength_points = 5;
        defence_points = 1;
        dexterity_points = 90;
        experience_points = 10;
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 73;
    }
}
