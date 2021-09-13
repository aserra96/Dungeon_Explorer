package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the class for Door placement of the walls of the rooms
 */
public class Door {

    int x;
    int y; 
    int posx;
    int posy; 
    int where; 
    int wall;

    Door(int from, int where, int wall, int place, int height, int width) {

        this.where = where;
        this.wall = wall;

        //North
        if (wall == 0) {
            x = place;
            y = 0;
            posx = x;
            posy = y + 1;
        } 
        //South
        if (wall == 1) {
            x = place;
            y = height - 1;
            posx = x;
            posy = y - 1;
        }
        //East
        if (wall == 2) {
            x = width - 1;
            y = place;
            posx = x - 1;
            posy = y;
        }
        //West
        if (wall == 3) {
            x = 0;
            y = place;
            posx = x + 1;
            posy = y;
        }
    }
}

