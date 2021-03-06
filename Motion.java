package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Motion class which holds all keyinputs in the game
 */
import javafx.scene.input.KeyCode;
import java.util.ListIterator;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Motion {

    private Scene scene;
    private AssetsLoader assets;
    private Stage stage;

    Motion(Scene scene, AssetsLoader assets, Stage stage) {
        this.scene = scene;
        this.assets = assets;
        this.stage = stage;
        keyHandler();
    }

    private void keyHandler() {
        Character character = new Character();
        Items items = new Items();
        //Keys for Movement
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (Potions.character_confused == 0)
                    character.decreaseY();
                else {
                    character.decreaseX();
                    Potions.character_confused--;
                }
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.LEFT) {
                if (Potions.character_confused == 0)
                    character.decreaseX();
                else {
                    character.decreaseY();
                    Potions.character_confused--;
                }
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DOWN) {
                if (Potions.character_confused == 0)
                    character.increaseY();
                else {
                    character.increaseX();
                    Potions.character_confused--;
                }
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.RIGHT) {
                if (Potions.character_confused == 0)
                    character.increaseX();
                else {
                    character.increaseY();
                    Potions.character_confused--;
                }
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            //Key input for iventory
            } else if (event.getCode() == KeyCode.DIGIT1) {
                items.checkItem(0, false);
                Items.last_position = 0;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT2) {
                items.checkItem(1, false);
                Items.last_position = 1;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT3) {
                items.checkItem(2, false);
                Items.last_position = 2;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT4) {
                items.checkItem(3, false);
                Items.last_position = 3;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT5) {
                items.checkItem(4, false);
                Items.last_position = 4;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT6) {
                items.checkItem(5, false);
                Items.last_position = 5;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT7) {
                items.checkItem(6, false);
                Items.last_position = 6;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT8) {
                items.checkItem(7, false);
                Items.last_position = 7;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT9) {
                items.checkItem(8, false);
                Items.last_position = 8;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.DIGIT0) {
                items.checkItem(9, false);
                Items.last_position = 9;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            //Key input for interactions
            } else if (event.getCode() == KeyCode.F) {
                if (Items.was_clicked) {
                    items.checkItem(Items.last_position, true);
                    scene.setRoot(assets.draw());
                    stage.setScene(scene);
                }
            } else if (event.getCode() == KeyCode.X) {
                if (Items.was_clicked) {
                    items.dropItem(Items.last_position);
                    scene.setRoot(assets.draw());
                    stage.setScene(scene);
                }
            } else if (event.getCode() == KeyCode.I) {
                if (Items.was_clicked) {
                    items.identify(Items.last_position);
                    scene.setRoot(assets.draw());
                    stage.setScene(scene);
                }
            } else if (event.getCode() == KeyCode.E) {
                Interface.inventory_shown = !Interface.inventory_shown;
                Items.was_clicked = false;
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
            if (Character.action_made) {
                enemyMove();
                Character.hunger();
                Items.was_clicked = false;
                Character.action_made = false;
                Character.is_attacking = false;
            }
        });
    }
    //movement for enemy
    private void enemyMove() {
        ListIterator<Enemies> iterator = Level.levels_list.get
                (Character.present_level).get(Character.present_room).enemies_list.listIterator();
        while (iterator.hasNext()) {
            Enemies enemies = iterator.next();
            if (enemies.room.index == Character.present_room) {
                enemies.moveAlgorithm();
                if (!enemies.isAlive()) {
                    iterator.remove();
                    scene.setRoot(assets.draw());
                    stage.setScene(scene);
                }
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
        }
    }
}
