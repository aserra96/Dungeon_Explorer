package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the main class this sets up the window, icon, and name of the game 
 * all colors on primaryStage or done via a stylesheet(thank you Joge for teaching me and 
 * helping me understand it) 
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

	@Override
	// creates the name of the application and uses an icon for the game
	public void start(Stage primaryStage) throws Exception {
		gameStart(primaryStage);
        primaryStage.setTitle("ScuffAdeventure III");
        primaryStage.getIcons().add(new Image("file:assets/battle/hit_0.png"));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	
	//starts the game by creating the dungeon with enemies, creating and placing character
	//then grabbing and applying assets
	private void gameStart(Stage primaryStage) throws IOException {
        //creates the generated dungeon
		Level level = new Level();
        level.newDungeon();
        //creates character
        Character character = new Character();
        character.createCharacter();
        //loads all assets
        AssetsLoader assets = new AssetsLoader();
        assets.load();
        //creates scene
        Scene scene = new Scene(assets.draw(), 570, 540);
        scene.getStylesheets().add("file:assets/sylesheet.css"); //Stylesheet is misspelled
        new Motion(scene, assets, primaryStage); // allows motion of the scene
        primaryStage.setScene(scene);
    }
}
