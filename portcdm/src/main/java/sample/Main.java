package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AppLayout.Setup_App(primaryStage);

    }


    public static void main(String[] args) {
        //Controller.connect();
        Controller cnt = new Controller();
        System.out.println(cnt.runUpdates());
        launch(args);

    }
}
