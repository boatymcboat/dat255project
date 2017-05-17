package presenters;

import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.messaging.LogicalLocation;
import eu.portcdm.messaging.ServiceObject;
import eu.portcdm.messaging.ServiceTimeSequence;
import eu.portcdm.messaging.TimeType;
import eu.portcdm.messaging.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.MessageSender;
import model.PortCallManager;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    public MessageSenderPresenter(){
        //servicetypebox.setItems(FXCollections.observableArrayList("Anchoring","b"));
    }
    //Method to create the options for the drop down menus used in the view.
    public void initialize(){
        servicetypebox.setItems(FXCollections.observableArrayList("Cargo Operation"));
        servicesequencebox.setItems(FXCollections.observableArrayList("Commenced", "Completed"));
        servicetimetypebox.setItems(FXCollections.observableArrayList("Estimated"));
        locationtypebox.setItems(FXCollections.observableArrayList("Berth"));
    }
    // Method for creating a message when pressing the send location state button.
    public void sendlocationstate(ActionEvent actionEvent) {
        MessageSender sender = new MessageSender();
        //servicetypebox.setItems(FXCollections.observableArrayList("Anchoring", "b"));
        PortCallManager manager = new PortCallManager();
        sender.sendLocationState(manager.getActiveCall(), LocationTimeSequence.ARRIVAL_TO,
                LogicalLocation.ANCHORING_AREA, LogicalLocation.BERTH,
                ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT), TimeType.ESTIMATED);
    }
    //Method for creating a message when pressing the send service state button
    public void sendservicestate(ActionEvent actionEvent) {
        MessageSender sender = new MessageSender();
        PortCallManager manager = new PortCallManager();
        //System.out.println(servicedatebox.getValue().toString());
        if(servicesequencebox.getValue().equals("Commenced")) {
            sender.sendServiceState(manager.getPortCall(0), ServiceObject.CARGO_OPERATION, ServiceTimeSequence.COMMENCED,
                    LogicalLocation.ANCHORING_AREA, ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT),
                    TimeType.ESTIMATED);
        }
        else{
            sender.sendServiceState(manager.getPortCall(0), ServiceObject.CARGO_OPERATION, ServiceTimeSequence.COMPLETED,
                    LogicalLocation.ANCHORING_AREA, ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT),
                    TimeType.ESTIMATED);
        }
    }
    /*public getView(){
        try {
            FXMLLoader.load(getClass().getResource("messagesender.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
