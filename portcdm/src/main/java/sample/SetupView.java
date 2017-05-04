package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.layout.GridPane;

import javafx.scene.control.ScrollPane;

import static sample.SizeAndGrid.getSceneHeight;
import static sample.SizeAndGrid.getSceneWidth;


/**
 * Created by Oskar on 2017-05-03.
 */
public class SetupView {
    private static ScrollPane rootPane;
    private static GridPane grid;
    private static Scene scene;

    public static void Setup_View() {
        grid = Setup_Grid();


        rootPane = new ScrollPane();
        rootPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rootPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rootPane.setContent(grid);

        scene = new Scene(rootPane, getSceneWidth(), getSceneHeight());


    }

    public static Scene getScene() {
        return scene;
    }

    public static ScrollPane getRootPane() {
        return rootPane;
    }

    public static GridPane getGrid() {
        return grid;
    }

    public static GridPane Setup_Grid (){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }


}
