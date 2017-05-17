package presenters;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Created by Oskar on 2017-05-17.
 */
public class MessageSenderPresenter {
    public ChoiceBox servicetypebox;
    public ChoiceBox servicesequencebox;
    public ChoiceBox locationtypebox;
    public DatePicker servicedatebox;
    public TextField servicehoursbox;
    public TextField serviceminutesbox;
    public ChoiceBox servicetimetypebox;
    public Button sendservicestatebox;
    public ChoiceBox locationtimesqeuencebox;
    public ChoiceBox tolocationbox;
    public ChoiceBox fromlocationbox;
    public DatePicker locationdatebox;
    public TextField locationhoursbox;
    public TextField locationminutesbox;
    public ChoiceBox locationtimetypebox;
    public Button sendlocationbox;

    public void sendlocationstate(ActionEvent actionEvent) {
    }

    public void sendservicestate(ActionEvent actionEvent) {
    }
}
