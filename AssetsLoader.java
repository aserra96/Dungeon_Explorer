package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the assets loader class as the name implies it places the 32x32 png
 * from the asset folder
 */
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class AssetsLoader {

    private Image shadow, drop_bag, hit_0, hit_1, hit_2;
    private Image wooden_doors, wooden_chest, stairs_down, stairs_up;
    private Image wall_block, wall_broken_block, column_block, bookshelf;
    private Image wall_plant_up, wall_plant_down, wall_plant_left, wall_plant_right,
            wall_moss_up, wall_moss_down, wall_moss_left, wall_moss_right;
    private Image floor_block, floor_divided, floor_decorated, floor_tile,
            grass_less, grass_up, grass_down, wooden_floor;
    private Image character_left, character_right, character_up, character_down;
    private Image enemy_zombie, enemy_skeleton, enemy_golem, enemy_ghost;
    static final int tile_size = 32;
    private static int enemy_x_index;
    private static int enemy_y_index;

    AssetsLoader() {
        //terminalShowing();
    }

    void load() throws IOException {
    	//assigns the img to something easily called on
        shadow = new Image("file:assets/gui/shadow.png");
        drop_bag = new Image("file:assets/inventory/bag.png");
        wall_block = new Image("file:assets/wall/brick.png");
        wall_broken_block = new Image("file:assets/wall/brick_broken.png");
        wall_plant_up = new Image("file:assets/wall/brick_plant_u.png");
        wall_plant_down = new Image("file:assets/wall/brick_plant_d.png");
        wall_plant_left = new Image("file:assets/wall/brick_plant_l.png");
        wall_plant_right = new Image("file:assets/wall/brick_plant_r.png");
        wall_moss_up = new Image("file:assets/wall/brick_moss_u.png");
        wall_moss_down = new Image("file:assets/wall/brick_moss_d.png");
        wall_moss_left = new Image("file:assets/wall/brick_moss_l.png");
        wall_moss_right = new Image("file:assets/wall/brick_moss_r.png");
        column_block = new Image("file:assets/wall/column.png");
        bookshelf = new Image("file:assets/wall/bookshelf.png");
        floor_block = new Image("file:assets/floor/floor_block.png");
        floor_divided = new Image("file:assets/floor/floor_divided.png");
        floor_decorated = new Image("file:assets/floor/floor_decorated.png");
        floor_tile = new Image("file:assets/floor/floor_tile.png");
        wooden_floor = new Image("file:assets/floor/wooden_floor.png");
        grass_less = new Image("file:assets/floor/grass_less.png");
        grass_up = new Image("file:assets/floor/grass_up.png");
        grass_down = new Image("file:assets/floor/grass_down.png");
        wooden_doors = new Image("file:assets/action/doors.png");
        wooden_chest = new Image("file:assets/action/chest.png");
        stairs_down = new Image("file:assets/action/stairs_down.png");
        stairs_up = new Image("file:assets/action/stairs_up.png");
        character_left = new Image("file:assets/character/character_l.png");
        character_right = new Image("file:assets/character/character_r.png");
        character_up = new Image("file:assets/character/character_u.png");
        character_down = new Image("file:assets/character/character_d.png");
        enemy_zombie = new Image("file:assets/enemies/zombie.png");
        enemy_skeleton = new Image("file:assets/enemies/skeleton.png");
        enemy_golem = new Image("file:assets/enemies/golem.png");
        enemy_ghost = new Image("file:assets/enemies/ghost.png");
        hit_0 = new Image("file:assets/battle/hit_0.png");
        hit_1 = new Image("file:assets/battle/hit_1.png");
        hit_2 = new Image("file:assets/battle/hit_2.png");
    }
    //creates the Pane of the game
    Pane draw() {

        Pane root = new Pane();
        Room room = Level.levels_list.get(Character.present_level).get(Character.present_room);
        Random generator = new Random();
        int random = generator.nextInt(3);

        //this section holds the code for the camera and moves keeping
        //a 11x11 camera view at all times
        int x_begin, y_begin, x_end, y_end;
        x_begin = Character.x_value - 5;
        y_begin = Character.y_value - 5;
        x_end = Character.x_value + 6;
        y_end = Character.y_value + 6;
        if (Character.x_value < 5) {
            x_begin = 0;
            x_end += (5 - Character.x_value);
        }
        if (Character.y_value < 5) {
            y_begin = 0;
            y_end += (5 - Character.y_value);
        }
        if (Character.x_value > (room.width - 6)) {
            x_end = room.width;
            x_begin += (room.width - Character.x_value - 6);
        }
        if (Character.y_value > (room.height - 6)) {
            y_end = room.height;
            y_begin += (room.height - Character.y_value - 6);
        }

        for (int x_tile = x_begin, x_index = 0; x_tile < x_end; x_tile++, x_index++) {
            for (int y_tile = y_begin, y_index = 0; y_tile < y_end; y_tile++, y_index++) {
            	//image view for tiles
                ImageView iV = new ImageView();
                iV.setFitHeight(tile_size);
                iV.setFitWidth(tile_size);

                //asset loader for walls
                if (room.sizes[x_tile][y_tile] >= 80 && room.sizes[x_tile][y_tile] <= 99) {
                    if (room.sizes[x_tile][y_tile] == 81)
                        iV.setImage(bookshelf);
                    if (room.sizes[x_tile][y_tile] == 87)
                        iV.setImage(column_block);
                    if (room.sizes[x_tile][y_tile] == 88)
                        iV.setImage(wall_block);
                    if (room.sizes[x_tile][y_tile] == 89)
                        iV.setImage(wall_broken_block);
                    if (room.sizes[x_tile][y_tile] == 90)
                        iV.setImage(wall_plant_up);
                    if (room.sizes[x_tile][y_tile] == 91)
                        iV.setImage(wall_plant_down);
                    if (room.sizes[x_tile][y_tile] == 92)
                        iV.setImage(wall_plant_left);
                    if (room.sizes[x_tile][y_tile] == 93)
                        iV.setImage(wall_plant_right);
                    if (room.sizes[x_tile][y_tile] == 94)
                        iV.setImage(wall_moss_up);
                    if (room.sizes[x_tile][y_tile] == 95)
                        iV.setImage(wall_moss_down);
                    if (room.sizes[x_tile][y_tile] == 96)
                        iV.setImage(wall_moss_left);
                    if (room.sizes[x_tile][y_tile] == 97)
                        iV.setImage(wall_moss_right);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                //asset loading for floor tiles
                if (room.sizes[x_tile][y_tile] >= 10 && room.sizes[x_tile][y_tile] < 20) {
                    if (room.sizes[x_tile][y_tile] == 10)
                        iV.setImage(floor_block);
                    if (room.sizes[x_tile][y_tile] == 11)
                        iV.setImage(floor_divided);
                    if (room.sizes[x_tile][y_tile] == 12)
                        iV.setImage(floor_decorated);
                    if (room.sizes[x_tile][y_tile] == 13)
                        iV.setImage(floor_tile);
                    if (room.sizes[x_tile][y_tile] == 14)
                        iV.setImage(wooden_floor);
                    if (room.sizes[x_tile][y_tile] == 15)
                        iV.setImage(grass_up);
                    if (room.sizes[x_tile][y_tile] == 16)
                        iV.setImage(grass_down);
                    if (room.sizes[x_tile][y_tile] == 17)
                        iV.setImage(grass_less);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                //loads the tile for doors and stairs
                if (room.sizes[x_tile][y_tile] >= 20 && room.sizes[x_tile][y_tile] <= 30) {
                    if (room.sizes[x_tile][y_tile] == 20)
                        iV.setImage(wooden_doors);
                    if (room.sizes[x_tile][y_tile] == 25)
                        iV.setImage(wooden_chest);
                    if (room.sizes[x_tile][y_tile] == 29)
                        iV.setImage(stairs_down);
                    if (room.sizes[x_tile][y_tile] == 30)
                        iV.setImage(stairs_up);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                //loads asset for dropped item
                for (Drop drop : room.drop_list) {
                    if (x_tile == drop.x_position && y_tile == drop.y_position) {
                        iV = new ImageView();
                        iV.setFitHeight(tile_size);
                        iV.setFitWidth(tile_size);
                        iV.setImage(drop_bag);
                        iV.setX(x_index * tile_size + 50);
                        iV.setY(y_index * tile_size + 50);
                        root.getChildren().add(iV);
                    }
                }
                //loads assets for chacter tiles
                if (room.sizes[x_tile][y_tile] >= 44 && room.sizes[x_tile][y_tile] <= 47) {
                    iV = new ImageView();
                    iV.setImage(background(Character.last_tile, "character"));
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    iV.setFitHeight(tile_size);
                    iV.setFitWidth(tile_size);
                    if (room.sizes[x_tile][y_tile] == 44)
                        iV.setImage(character_left);
                    if (room.sizes[x_tile][y_tile] == 45)
                        iV.setImage(character_right);
                    if (room.sizes[x_tile][y_tile] == 46)
                        iV.setImage(character_up);
                    if (room.sizes[x_tile][y_tile] == 47)
                        iV.setImage(character_down);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                /* ENEMIES TILES */
                if (room.sizes[x_tile][y_tile] >= 70 && room.sizes[x_tile][y_tile] < 80) {
                    iV = new ImageView();
                    iV.setImage(background(checkEnemyTile(x_tile, y_tile, room.index), "enemy"));
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                    iV = new ImageView();
                    iV.setFitHeight(tile_size);
                    iV.setFitWidth(tile_size);
                    if (room.sizes[x_tile][y_tile] == 70)
                        iV.setImage(enemy_zombie);
                    if (room.sizes[x_tile][y_tile] == 71)
                        iV.setImage(enemy_skeleton);
                    if (room.sizes[x_tile][y_tile] == 72)
                        iV.setImage(enemy_golem);
                    if (room.sizes[x_tile][y_tile] == 73)
                        iV.setImage(enemy_ghost);
                    iV.setX(x_index * tile_size + 50);
                    iV.setY(y_index * tile_size + 50);
                    root.getChildren().add(iV);
                }
                //assets loader for attacks
                if (Character.is_attacking) {
                    iV = new ImageView();
                    if (random == 0)
                        iV.setImage(hit_0);
                    if (random == 1)
                        iV.setImage(hit_1);
                    if (random == 2)
                        iV.setImage(hit_2);
                    iV.setFitHeight(AssetsLoader.tile_size);
                    iV.setFitWidth(AssetsLoader.tile_size);
                    iV.setX((enemy_x_index - x_begin) * AssetsLoader.tile_size + 50);
                    iV.setY((enemy_y_index - y_begin) * AssetsLoader.tile_size + 50);
                    root.getChildren().add(iV);
                }
            }
        }
        //image view for each asset within the 11x11
        ImageView iV = new ImageView();
        iV.setFitHeight(tile_size * 11);
        iV.setFitWidth(tile_size * 11);
        iV.setImage(shadow);
        iV.setX(50);
        iV.setY(50);
        root.getChildren().add(iV);

        new Interface(root);
        new Actions(root);
        return root;
    }
    //loads tile for enemy
    private int checkEnemyTile(int posX, int posY, int index) {
        int last_tile = 0;
        for (Enemies enemy : Level.levels_list.get(Character.present_level).get(index).enemies_list) {
            if (enemy.room.index == index) {
                if (posX == enemy.positionX && posY == enemy.positionY)
                    last_tile = enemy.last_tile;
            }
        }
        return last_tile;
    }
    //method for battle checks if charcter and enemy are attacking
    static void battle(int x_pos, int y_pos) {
        enemy_x_index = x_pos;
        enemy_y_index = y_pos;
        Character.is_attacking = true;
    }
    //method for background to maintain the tile moved from
    private Image background(int last_tile, String type) {
        if (last_tile == 10)
            return floor_block;
        if (last_tile == 11)
            return floor_divided;
        if (last_tile == 12)
            return floor_decorated;
        if (last_tile == 13)
            return floor_tile;
        if (last_tile == 14)
            return wooden_floor;
        if (last_tile == 15) {
            if (Objects.equals(type, "character")) {
                Character.last_tile = 16;
                return grass_down;
            } else if (Objects.equals(type, "enemy"))
                return grass_up;
        }
        if (last_tile == 16)
            return grass_down;
        if (last_tile == 17)
            return grass_less;
        if (last_tile == 29)
            return stairs_down;
        if (last_tile == 30)
            return stairs_up;
        else
            return floor_block;
    }
    //shows the application of assets
//    private void terminalShowing() {
//        for (int index = 0; index < Level.room_number; index++) {
//            Room room = Level.levels_list.get(Character.present_level).get(index);
//            for (int x = 0; x < room.width; x++) {
//                for (int y = 0; y < room.height; y++) {
//                    System.out.print(room.sizes[x][y]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
//    }
}