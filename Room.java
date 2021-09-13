package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the room creator placing enemies chests and doors with diffrent sized rooms
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Room {

    private boolean isEnemy, isZombiable, isSkeletonable, isGolemable, isGhostable = false;
    private Random generator = new Random();
    private int[] north, south, east, west;
    boolean visited = false;
    int height, width = 0;
    int index = 0;
    int[][] sizes;
    ArrayList<Enemies> enemies_list;
    ArrayList<Chests> chests_list;
    ArrayList<Drop> drop_list;
    ArrayList<Door> doors;
    
    //records the room created
    Room(int index) {

        this.index = index;
        doors = new ArrayList<>();
        chests_list = new ArrayList<>();
        drop_list = new ArrayList<>();
        enemies_list = new ArrayList<>();
        if (index != Level.room_number - 1 && index != 0) {
            height = generator.nextInt(10) + 11;
            width = generator.nextInt(10) + 11;
        } else {
            height = 11;
            width = 11;
        }
        sizes = new int[width][height];
        north = new int[width];
        south = new int[width];
        east = new int[height];
        west = new int[height];
        //sees if the room created is a regular room or stair room
        if (index != Level.room_number - 1 && index != 0) {
        	//calls to add walls to the room
            addWalls();
            //calls to assign the type of room
            roomType();
            //calls to add enemies if allowed
            addEnemies();
        } else {
        	//calls to add room
            addWalls();
            //calls to add staircase
            roomTypeStairs();
        }

    }

    private void roomType() {
        Random generator = new Random();
        int random = generator.nextInt(3);

        /* ROOM TYPE TILES */
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                sizes[x][y] = 10;
                if (generator.nextInt(4) == 0)
                    sizes[x][y] = 11;
                else if (generator.nextInt(4) == 0)
                    sizes[x][y] = 12;
                else if (generator.nextInt(4) == 0)
                    sizes[x][y] = 13;
            }
        }
        // Room - coulumns
        if (height % 2 != 0 && width % 2 != 0) {
            //Room - v-colummns
            if (random == 0) {
                for (int y = 2; y < height - 2; y += 2) {
                    sizes[2][y] = 87;
                    sizes[width - 3][y] = 87;
                }
                //allows skeleton to be added
                isSkeletonable = true;
            }
            //Room - s-columns
            if (random == 1) {
                for (int y = 2; y < height - 2; y += 2) {
                    sizes[2][y] = 87;
                    sizes[width - 3][y] = 87;
                }
                for (int x = 2; x < width - 2; x += 2) {
                    sizes[x][2] = 87;
                    sizes[x][height - 3] = 87;
                }
                //allows skeleton to be added
                isSkeletonable = true;
            }
            //Room - sh-coulumns
            if (random == 2) {
                for (int x = 2; x < width - 2; x += 2) {
                    for (int y = 2; y < height - 2; y += 2) {
                        sizes[x][y] = 87;
                    }
                }
                //allows skeleton to be added
                isSkeletonable = true;
            }
        }
        //Room - grass
        else if (random == 0) {
            for (int x = 1; x < width - 1; x++) {
                for (int y = 1; y < height - 1; y++) {
                    sizes[x][y] = 16;
                    if (generator.nextInt(6) < 4)
                        sizes[x][y] = 15;
                    else if (generator.nextInt(6) < 3)
                        sizes[x][y] = 17;
                }
            }
            //calls to add chests
            addChests();
            //Golems are only allowed in grass areas
            isGolemable = true;
        }
        //Room - Hall
        else if (random == 1) {
            for (int x = 1; x < width - 1; x++) {
                for (int y = 1; y < height - 1; y++) {
                    sizes[x][y] = 14;
                }
            }
            //Room - study
            if (height % 2 != 0) {
                for (int x = 2; x < width - 2; x++) {
                    for (int y = 2; y < height - 2; y += 2) {
                        sizes[x][y] = 81;
                        if (generator.nextInt(30) == 0) {
                            sizes[x][y] = 25;
                            Chests chest = new Chests(x, y);
                            chests_list.add(chest);
                        }
                    }
                }
            } else {
            	//allows Zombie to be added
                isZombiable = true;
                addChests();
            }
        } else {
        	//allows Ghost to be added
            isGhostable = true;
            addChests();
        }

        if (isZombiable || isSkeletonable || isGolemable || isGhostable)
            isEnemy = true;
    }
    
    //class for stair case rooms
    private void roomTypeStairs() {

        //places tiles in room
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                sizes[x][y] = 10;
                if (generator.nextInt(4) == 0)
                    sizes[x][y] = 11;
                else if (generator.nextInt(4) == 0)
                    sizes[x][y] = 12;
                else if (generator.nextInt(4) == 0)
                    sizes[x][y] = 13;
            }
        }
        //Staircase upwards
        if (index == 0) {
            sizes[width / 2][height / 2] = 30;
            visited = true;
        }
        //staircase downwards
        if (index == Level.room_number - 1) {
            sizes[width / 2][height / 2] = 29;
        }
    }
    
    //class to add walls to the room
    private void addWalls() {
        Arrays.fill(north, 88);
        Arrays.fill(south, 88);
        Arrays.fill(east, 88);
        Arrays.fill(west, 88);

        for (int i = 2; i < north.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                north[i] = 90;
            else if (generator.nextInt(5) == 0)
                north[i] = 94;
            else if (generator.nextInt(50) == 0)
                north[i] = 89;
        }
        for (int i = 2; i < south.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                south[i] = 91;
            else if (generator.nextInt(5) == 0)
                south[i] = 95;
            else if (generator.nextInt(50) == 0)
                south[i] = 89;
        }
        for (int i = 2; i < west.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                west[i] = 92;
            else if (generator.nextInt(5) == 0)
                west[i] = 96;
            else if (generator.nextInt(50) == 0)
                west[i] = 89;
        }
        for (int i = 2; i < east.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                east[i] = 93;
            else if (generator.nextInt(5) == 0)
                east[i] = 97;
            else if (generator.nextInt(50) == 0)
                east[i] = 89;
        }
        //calls to add doors 
        addDoors();

        for (int x = 0; x < width; x++) {
            sizes[x][0] = north[x];
            sizes[x][height - 1] = south[x];
        }
        for (int y = 0; y < height; y++) {
            sizes[width - 1][y] = east[y];
            sizes[0][y] = west[y];
        }
    }
    
    //class to add doors to rooms
    private void addDoors() {
        Random generator = new Random();
        for (int doorID = 0; doorID < Level.room_number; doorID++) {
            if (StructureGenerator.structure[index][doorID]) {
                int wall = generator.nextInt(4);
                int place;
                while (true) {
                    if (wall == 0) {
                        place = generator.nextInt(north.length - 4) + 2;
                        if (north[place] != 3 && north[place - 1] != 20 && north[place + 1] != 20) {
                            north[place] = 20;
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 1) {
                        place = generator.nextInt(south.length - 4) + 2;
                        if (south[place] != 20 && south[place - 1] != 20 && south[place + 1] != 20) {
                            south[place] = 20;
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 2) {
                        place = generator.nextInt(east.length - 4) + 2;
                        if (east[place] != 20 && east[place - 1] != 20 && east[place + 1] != 20) {
                            east[place] = 20;
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 3) {
                        place = generator.nextInt(west.length - 4) + 2;
                        if (west[place] != 20 && west[place - 1] != 20 && west[place + 1] != 20) {
                            west[place] = 20;
                            break;
                        } else
                            wall = 0;
                    }
                }
                Door door = new Door(index, doorID, wall, place, height, width);
                doors.add(door);
            }
        }
    }
    
    //class to add types of enemies depending if-add-able
    private void addEnemies() {

        if (isEnemy) {
            for (int numberOf = 0; numberOf < generator.nextInt(5); numberOf++) {
                int x_position = generator.nextInt(width - 4) + 2;
                int y_position = generator.nextInt(height - 4) + 2;
                if (sizes[x_position][y_position] >= 10 && sizes[x_position][y_position] < 20) {
                	//if-add-able it will place the enemy in a random positon in the room
                    if (isZombiable) {
                        Zombie zombie = new Zombie(this, x_position, y_position);
                        enemies_list.add(zombie);
                    }
                    if (isSkeletonable) {
                        Skeleton skeleton = new Skeleton(this, x_position, y_position);
                        enemies_list.add(skeleton);
                    }
                    if (isGolemable) {
                        Golem golem = new Golem(this, x_position, y_position);
                        enemies_list.add(golem);
                    }
                    if (isGhostable) {
                        Ghost ghost = new Ghost(this, x_position, y_position);
                        enemies_list.add(ghost);
                    }
                } else
                    numberOf--;
            }
        }

    }
    
    //class to add chests in the room works similar to the addenemies
    private void addChests() {

        if (generator.nextInt(2) == 0) {

            int wall = generator.nextInt(4);

            if (wall == 0) {
                int place = generator.nextInt(width - 2) + 1;
                if (north[place] != 20) {
                    sizes[place][1] = 25;
                    Chests chest = new Chests(place, 1);
                    chests_list.add(chest);
                }
            }
            if (wall == 1) {
                int place = generator.nextInt(width - 2) + 1;
                if (south[place] != 20) {
                    sizes[place][height - 2] = 25;
                    Chests chest = new Chests(place, height - 2);
                    chests_list.add(chest);
                }
            }
            if (wall == 2) {
                int place = generator.nextInt(height - 2) + 1;
                if (east[place] != 20) {
                    sizes[width - 2][place] = 25;
                    Chests chest = new Chests(width - 2, place);
                    chests_list.add(chest);
                }
            }
            if (wall == 3) {
                int place = generator.nextInt(height - 2) + 1;
                if (west[place] != 20) {
                    sizes[1][place] = 25;
                    Chests chest = new Chests(1, place);
                    chests_list.add(chest);
                }
            }
        }
    }
}
