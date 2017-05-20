package presenters;

import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.dto.PortCall;
import eu.portcdm.messaging.LogicalLocation;
import eu.portcdm.messaging.ServiceObject;
import eu.portcdm.messaging.ServiceTimeSequence;
import eu.portcdm.messaging.TimeType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.MessageSender;
import model.PortCallManager;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Aron on 2017-05-20.
 */
public class MainViewPresenter {
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
    public ComboBox portcallpicker;
    public Text CurrentIdDisplay;
    private PortCallManager manager;
    private PortCall call;

    public MainViewPresenter(){
        //servicetypebox.setItems(FXCollections.observableArrayList("Anchoring","b"));
    }
    //Method to create the options for the drop down menus used in the view.
    public void initialize(){

        servicetypebox.setItems(FXCollections.observableArrayList(ServiceObject.values()));
        servicesequencebox.setItems(FXCollections.observableArrayList(ServiceTimeSequence.values()));
        servicetimetypebox.setItems(FXCollections.observableArrayList(TimeType.values()));
        locationtypebox.setItems(FXCollections.observableArrayList(LogicalLocation.values()));
        locationtimesqeuencebox.setItems((FXCollections.observableArrayList(LocationTimeSequence.values())));
        tolocationbox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
        fromlocationbox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
        locationtimetypebox.setItems((FXCollections.observableArrayList(TimeType.values())));


        manager = new PortCallManager();
        setCall(manager.getActiveCall());

        portcallpicker.setItems((FXCollections.observableArrayList(manager.getIds())));
        portcallpicker.setValue(call.getId());
    }

    public void setCall(PortCall call){
        this.call = call;
        CurrentIdDisplay.setText(call.getId());
    }
    // Method for creating a message when pressing the send location state button.
    public void sendlocationstate(ActionEvent actionEvent) {
        MessageSender sender = new MessageSender();
        //servicetypebox.setItems(FXCollections.observableArrayList("Anchoring", "b"));
        PortCallManager manager = new PortCallManager();

        //Retrieve the enums and use them in the menus
        //TODO: See if explicit type conversion can be removed. Should be impossible for object to be of wrong class,
        //TODO  but still looks weird.
        LocationTimeSequence timeSequence = (LocationTimeSequence) locationtimesqeuencebox.getValue();
        LogicalLocation tolocation = (LogicalLocation) tolocationbox.getValue();
        LogicalLocation fromlocation = (LogicalLocation) fromlocationbox.getValue();
        TimeType locationtimetype = (TimeType) locationtimetypebox.getValue();

        //Create the time string
        String time = locationdatebox.getValue().toString();
        time = time + "T" + locationhoursbox.getText() + ":" + locationminutesbox.getText() + ":00.000Z";

        //Compare the manually generated string to the timestamp
        System.out.println("Manually generated: " +time);
        System.out.println("Timestamp: " + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        //Send the message
        //TODO: Solve the issue that the manually generated string don't seem to work when used in this method
        sender.sendLocationState(manager.getActiveCall(), timeSequence,
                fromlocation, tolocation, ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT), locationtimetype);
    }
    //Method for creating a message when pressing the send service state button
    public void sendservicestate(ActionEvent actionEvent) {
        MessageSender sender = new MessageSender();

        //Retrieve the enums and use them in the menus
        //TODO: See if explicit type conversion can be removed. Should be impossible for object to be of wrong class,
        //TODO  but still looks weird.
        ServiceObject servicetype = (ServiceObject) servicetypebox.getValue();
        ServiceTimeSequence servicesequence = (ServiceTimeSequence) servicesequencebox.getValue();
        TimeType servicetimetype = (TimeType) servicetimetypebox.getValue();
        LogicalLocation location = (LogicalLocation) locationtypebox.getValue();
        PortCallManager manager = new PortCallManager();

        //Create the time string
        String time = servicedatebox.getValue().toString();
        time = time + "T" + servicehoursbox.getText() + ":" + serviceminutesbox.getText() + ":00.000Z";

        //Compare the manually generated string to the timestamp
        System.out.println("Manually generated: " +time);
        System.out.println("Timestamp: " + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));

        //Send the message
        //TODO: Solve the issue that the manually generated string don't seem to work when used in this method
        sender.sendServiceState(manager.getPortCall(0), servicetype, servicesequence,
                location, ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT),
                servicetimetype);


    }

    public void changecall(ActionEvent actionEvent) {
        setCall(manager.getPortCall((String) portcallpicker.getSelectionModel().getSelectedItem()));
    }
}
