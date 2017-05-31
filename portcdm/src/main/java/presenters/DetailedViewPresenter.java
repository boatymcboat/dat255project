package presenters;

import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.dto.PortCall;
import eu.portcdm.messaging.LogicalLocation;
import eu.portcdm.messaging.ServiceObject;
import eu.portcdm.messaging.ServiceTimeSequence;
import eu.portcdm.messaging.TimeType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LocationManager;
import model.MessageSender;
import model.PortCallManager;
import model.StatementReader;
import org.testng.Assert;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Presenter for the secondary view, displaying unfiltered data in order to make more information about the PortCall accessible.
 */
public class DetailedViewPresenter {

    public Text detailedviewtext;
    public Text vesselName;
    public ListView pclist;
    public ListView stateList;
    public Text latestUpdate;
    public ChoiceBox servicetypebox;
    public ChoiceBox servicesequencebox;
    public ChoiceBox locationtypebox;
    public DatePicker servicedatebox;
    public TextField servicehoursbox;
    public TextField serviceminutesbox;
    public ChoiceBox servicetimetypebox;
    public ChoiceBox locationtimesqeuencebox;
    public ChoiceBox tolocationbox;
    public ChoiceBox fromlocationbox;
    public DatePicker locationdatebox;
    public TextField locationhoursbox;
    public TextField locationminutesbox;
    public ChoiceBox locationtimetypebox;
    public ChoiceBox locationstatefromlocationnamecoicebox;
    public ChoiceBox locationstatetolocationnamechoicebox;
    public ChoiceBox servicestatelocationnamechoicebox;
    public Button gotooverviewbutton;
    public ChoiceBox servicestatetolocationtypechoicebox;
    public ChoiceBox servicestatetolocationnamechoicebox;

    private PortCallManager pcmanager;
    private StatementReader reader;
    private PortCall call;

    /**
     * Method called on initialization of the presenter and view.
     * The method sets the items in some of the menus and retrieves information about the most recently changed PortCall.
     */
    public void initialize(){
        pcmanager = new PortCallManager();
        call = pcmanager.getActiveCall();
        reader = new StatementReader(call);
        vesselName.setText(call.getVessel().getName());
        latestUpdate.setText(call.getLastUpdate());
        pclist.setItems(FXCollections.observableArrayList(pcmanager.getIds()));
        stateList.setItems(FXCollections.observableArrayList(reader.getStateDefinitions()));
        stateList.getSelectionModel().selectFirst();

        servicetypebox.setItems(FXCollections.observableArrayList(ServiceObject.values()));
        servicesequencebox.setItems(FXCollections.observableArrayList(ServiceTimeSequence.values()));
        servicetimetypebox.setItems(FXCollections.observableArrayList(TimeType.values()));
        locationtypebox.setItems(FXCollections.observableArrayList(LogicalLocation.values()));
        locationtimesqeuencebox.setItems((FXCollections.observableArrayList(LocationTimeSequence.values())));
        tolocationbox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
        fromlocationbox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
        locationtimetypebox.setItems((FXCollections.observableArrayList(TimeType.values())));
        servicestatetolocationtypechoicebox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
    }


    /**
     * Method which catches actionevents sent by any of the menus containing location types.
     * The method resolves which menu sent the actionevent and sets the corresponding location name menu to contain locations of that type.
     * @param actionEvent
     */
    public void locationTypeChosen(ActionEvent actionEvent) {
        ChoiceBox tempBox;
        ChoiceBox source;
        String locationtype;
        if (actionEvent.getSource().equals(locationtypebox)) {
            locationtype = locationtypebox.getValue().toString();
            tempBox = servicestatelocationnamechoicebox;
        } else if (actionEvent.getSource().equals(servicestatetolocationtypechoicebox)) {
            locationtype = servicestatetolocationtypechoicebox.getValue().toString();
            tempBox = servicestatetolocationnamechoicebox;
        } else if (actionEvent.getSource().equals(tolocationbox)) {
            locationtype = tolocationbox.getValue().toString();
            tempBox = locationstatetolocationnamechoicebox;
        } else if (actionEvent.getSource().equals(fromlocationbox)) {
            locationtype = fromlocationbox.getValue().toString();
            tempBox = locationstatefromlocationnamecoicebox;
        } else {
            return;
        }
        if (locationtype.equals("BERTH")) {
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getBerths()));
        } else if (locationtype.equals("TRAFFIC_AREA")) {
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getTrafficAreas()));
        } else if (locationtype.equals("ANCHORING_AREA")) {
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getAnchoringAreas()));
        } else if (locationtype.equals("TUG_ZONE")) {
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getTugZones()));
        } else if (locationtype.equals("PILOT_BOARDING_AREA")) {
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getPilotBAs()));
        } else if (locationtype.equals("ETUG_ZONE")) {
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.geteTugZones()));
        } else if (locationtype.equals("LOC")) {
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getLocs()));
        } else if (locationtype.equals("VESSEL")) {
            tempBox.setItems(FXCollections.observableArrayList("VESSEL"));

        }
    }

    /**
     * Refreshes the view with the latest infomration when the refresh button if pressed.
     * @param mouseEvent
     */
    public void refresh(MouseEvent mouseEvent) {
        pcmanager.refreshCalls();
        pclist.setItems(FXCollections.observableArrayList(pcmanager.getIds()));
        reader.setActiveCall(call);
        detailedviewtext.setText(reader.getStatements((String) stateList.getSelectionModel().getSelectedItem()));
        vesselName.setText(call.getVessel().getName());
        latestUpdate.setText(call.getLastUpdate());
        stateList.setItems(FXCollections.observableArrayList(reader.getStateDefinitions()));
    }

    /**
     * Changes the PortCall used when the user selects a new PortCall in the menu for portcallID.
     * @param mouseEvent
     */
    public void changeCall(MouseEvent mouseEvent) {
        call = pcmanager.getPortCall((String) pclist.getSelectionModel().getSelectedItem());
        vesselName.setText(call.getVessel().getName());
        latestUpdate.setText(call.getLastUpdate());
        reader.setActiveCall(call);
        stateList.setItems(FXCollections.observableArrayList(reader.getStateDefinitions()));
    }

    /**
     * Method for creating a message when pressing the send location state button.
     * Uses the selected options in the corresponding menus and text fields. Uses assertions to ensure no required field is null.
     */
    public void sendlocationstate(ActionEvent actionEvent) {
        MessageSender sender = new MessageSender();


        //Retrieve the enums and use them in the menus. Uses assertions to check that all required fields are filled.
        Assert.assertNotNull(locationtimesqeuencebox.getValue(),"Please select a time sequence.");
        LocationTimeSequence timeSequence = (LocationTimeSequence) locationtimesqeuencebox.getValue();
        Assert.assertNotNull(tolocationbox.getValue(),"Please select the to location.");
        LogicalLocation tolocation = (LogicalLocation) tolocationbox.getValue();
        //No assertion as this field is allowed to be null.
        LogicalLocation fromlocation = (LogicalLocation) fromlocationbox.getValue();
        Assert.assertNotNull(locationtimetypebox.getValue(),"Please select the time type.");
        TimeType locationtimetype = (TimeType) locationtimetypebox.getValue();
        Assert.assertNotNull(locationstatetolocationnamechoicebox.getValue(),"Please select the to location name.");
        String tolocationName = (String) locationstatetolocationnamechoicebox.getValue();
        //No assertion as this field is allowed to be null.
        String fromlocationName = (String) locationstatefromlocationnamecoicebox.getValue();

        //Create the time string
        Assert.assertNotNull(locationdatebox.getValue(),"Please select a date.");
        String time = locationdatebox.getValue().toString();
        Assert.assertNotNull(locationhoursbox.getText(),"Please enter the hour.");
        Assert.assertNotNull(locationminutesbox.getText(),"Please enter the minutes.");
        time = time + "T" + locationhoursbox.getText() + ":" + locationminutesbox.getText() + ":00.000Z";

        //Send the message
        sender.sendLocationState(call, timeSequence,
                fromlocation, fromlocationName, tolocation, tolocationName, time, locationtimetype, call.getId());
    }
    /**
     * Method for creating a message when pressing the send service state button
     * Uses the selected options in the corresponding menus and text fields. Uses assertions to ensure no required field is null.
     * @param actionEvent automatically generated when the user clicks the "send servicestate" button.
     */
    public void sendservicestate(ActionEvent actionEvent) {
        MessageSender sender = new MessageSender();


        //Retrieve the selections from the drop down menus. Uses assertions to check that all required fields are filled.
        Assert.assertNotNull(servicetypebox.getValue(),"Please select a service state.");
        ServiceObject servicetype = (ServiceObject)servicetypebox.getValue() ;
        Assert.assertNotNull(servicesequencebox.getValue(),"Please select a service sequence.");
        ServiceTimeSequence servicesequence = (ServiceTimeSequence) servicesequencebox.getValue();
        Assert.assertNotNull(servicetimetypebox.getValue(),"Please select a time type.");
        TimeType servicetimetype = (TimeType) servicetimetypebox.getValue();
        Assert.assertNotNull(locationtypebox.getValue(),"Please select a location type.");
        LogicalLocation location = (LogicalLocation) locationtypebox.getValue();
        Assert.assertNotNull(servicestatelocationnamechoicebox.getValue(),"Please select a location name.");
        String locationName = (String) servicestatelocationnamechoicebox.getValue();
        //Create the time string
        Assert.assertNotNull(servicedatebox.getValue(),"Please select a date.");
        String time = servicedatebox.getValue().toString();
        //Builds a string representing time in the format used by portCDM
        Assert.assertNotNull(servicehoursbox.getText(),"Please enter the hour.");
        Assert.assertNotNull(serviceminutesbox.getText(),"Please enter the minutes.");
        time = time + "T" + servicehoursbox.getText() + ":" + serviceminutesbox.getText() + ":00.000Z";

        if(servicetype.toString().equals("PILOTAGE")|| servicetype.toString().equals("ESCORT_TOWAGE")|| servicetype.toString().equals("TOWAGE")){
            Assert.assertNotNull(servicestatetolocationtypechoicebox.getValue(),"Please select to location type.");
            LogicalLocation target = (LogicalLocation) servicestatetolocationtypechoicebox.getValue();
            Assert.assertNotNull(servicestatetolocationnamechoicebox.getValue(),"Please select to location name.");
            String tolocationname = (String) servicestatetolocationnamechoicebox.getValue();
            sender.sendServiceState(call,servicetype,servicesequence,location,locationName,target,tolocationname,time,servicetimetype, call.getId());
        }
        else {
            //Send the message
            sender.sendServiceState(call, servicetype, servicesequence,
                    location, locationName, time,
                    servicetimetype, call.getId());
        }

    }

    /**
     * Retrieves a new list of the most recently updated PortCalls.
     * @param actionEvent
     */
    public void refreshpcs(ActionEvent actionEvent) {
        pcmanager.refreshCalls();
        pclist.setItems(FXCollections.observableArrayList(pcmanager.getIds()));
    }

    /**
     * Switches the view to the main view.
     * @param actionEvent
     */
    public void goToMainView(ActionEvent actionEvent) {
        Stage stage = (Stage) gotooverviewbutton.getScene().getWindow();
        try {
            stage.setScene(new Scene((ScrollPane) FXMLLoader.load(getClass().getResource("/views/MainView.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
