package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Simple class to load the initial FXML file.
 */
public class View {
    private Stage primaryStage;

    /**
     * Creates an instance, saves the Stage in the view and loads the default FXML file
     *
     * @param primaryStage the Stage for which to load the FXML file
     * @throws IOException if the file is not found
     */
    public View(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        setupView();
    }

    // Loads the MainView.fxml file and sets the Stage to use it
    private void setupView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
