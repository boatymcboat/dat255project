package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
public class AppLayout {

    public static void Setup_App(Stage primaryStage){
        primaryStage.setTitle("Professional Ship Agent Software 2018");

        Button btn = new Button();
        btn.setText("Start Agent Application");
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 800, 500);
        scene.setRoot(root);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        primaryStage.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

    }
}
