package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View {
    private Stage primaryStage;

    public View(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        setupView();
    }

    private void setupView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
