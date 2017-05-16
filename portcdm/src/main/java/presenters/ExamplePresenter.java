package presenters;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.MainView;
import views.MainViewImpl;

/**
 * Created by Aron on 2017-05-16.
 */
public class ExamplePresenter {

    public Button startButton;

    public void startApp(ActionEvent actionEvent) {
        MainView mainView = new MainViewImpl((Stage) startButton.getScene().getWindow());
        MainPresenter presenter = new MainPresenterImpl(mainView);
    }
}
