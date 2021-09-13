package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Skeleton child class which holds the data of Skeleton
 */
class Skeleton extends Enemies {

    Skeleton(Room room, int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.room = room;
        type = "Skeleton";
        enemy_type_tile = 71;
        health_points = 25;
        strength_points = 10;
        defence_points = 5;
        dexterity_points = 15;
        experience_points = 35;
        last_tile = room.sizes[positionX][positionY];
        room.sizes[positionX][positionY] = 71;
    }
}
