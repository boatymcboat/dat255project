package presenters;

import javafx.application.Application;
import javafx.stage.Stage;
import views.View;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new View(primaryStage);
    }
}
