package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the Actions class which is meant to be used as the death screen
 * and win screen(to be added as i continue this project)
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Actions {

    Actions(Pane root) {
        death(root);
    }

    private void death(Pane root) {
    	//places the death screen
        if (Character.is_dead) {
            Image dead_screen = new Image("file:assets/gui/dead_screen.png");
            ImageView iV = new ImageView();
            iV.setFitHeight(AssetsLoader.tile_size * 11);
            iV.setFitWidth(AssetsLoader.tile_size * 11);
            iV.setImage(dead_screen);
            iV.setX(50);
            iV.setY(50);
            root.getChildren().add(iV);
        }
    }
}

