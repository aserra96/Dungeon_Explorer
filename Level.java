package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the dungeon creator creating 10 rooms and 10 levels the player can
 * traverse this is generated randomly so no dungeon is ever the same
 */
import java.util.ArrayList;

public class Level {

    static int room_number = 10;
    static int level_number = 10;
    static ArrayList<ArrayList<Room>> levels_list = new ArrayList<>();
    private ArrayList<Room> rooms_list;

    void newDungeon() {
        Interface.newEvent(" ");
        Interface.newEvent("You enter the dungeon");
        Interface.newEvent("Good Luck!");
        Interface.newEvent(" ");
        //calls on to create the level
        addFloor();
    }
    //call upon to create a floor of the dungeon
    private void addFloor() {
    	//using for loop to create each of the 10 floors
        for (int index = 0; index < level_number; index++) {
        	//calls upon to add 10 rooms to the dungeon
            addRooms();
            // adds the level to a list to keep track
            levels_list.add(rooms_list);
        }
    }
    //call upon to create 10 rooms for the dungeon floor
    private void addRooms() {
    	//calls upon structur Generator to create the room
        StructureGenerator structure = new StructureGenerator();
        structure.generate(room_number);
        //creates the room and adds it to a the room list to keep track
        rooms_list = new ArrayList<>();
        for (int index = 0; index < room_number; index++) {
            Room room = new Room(index);
            rooms_list.add(room);
        }
    }
}
