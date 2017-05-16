package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Aron on 2017-05-16.
 */
public class ExampleView {
    Stage primaryStage;

    public ExampleView(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        setupView();
    }

    private void setupView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ExampleView.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
