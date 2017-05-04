package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AppController application = new AppController(primaryStage);
        application.initiate();
    }


    public static void main(String[] args) {
        //Controller.connect();
        Controller cnt = new Controller();
        System.out.println(cnt.runUpdates().getClass());
        launch(args);

    }
}
