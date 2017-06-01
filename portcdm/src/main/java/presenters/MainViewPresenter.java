package presenters;

import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.dto.PortCall;
import eu.portcdm.messaging.LogicalLocation;
import eu.portcdm.messaging.ServiceObject;
import eu.portcdm.messaging.ServiceTimeSequence;
import eu.portcdm.messaging.TimeType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import org.testng.Assert;

import java.io.IOException;

/**
 * Presenter class for the main view of the application
 */
public class MainViewPresenter {
    public ChoiceBox serviceTypeBox;
    public ChoiceBox serviceSequenceBox;
    public ChoiceBox locationTypeBox;
    public DatePicker serviceDateBox;
    public TextField serviceHoursBox;
    public TextField serviceMinutesBox;
    public ChoiceBox serviceTimeTypeBox;
    public ChoiceBox locationTimeSequenceBox;
    public ChoiceBox toLocationBox;
    public ChoiceBox fromLocationBox;
    public DatePicker locationDateBox;
    public TextField locationHoursBox;
    public TextField locationMinutesBox;
    public ChoiceBox locationTimeTypeBox;
    public ComboBox portCallPicker;
    public Text currentIdDisplay;
    public Circle step1indicator;
    public Circle step2indicator;
    public Circle step3indicator;
    public Circle step4indicator;
    public Text step1time;
    public Text step2time;
    public Text step3time;
    public Text step4time;
    public ChoiceBox serviceStateLocationNameChoiceBox;
    public ChoiceBox locationStateToLocationNameChoiceBox;
    public ChoiceBox locationStateFromLocationNameChoiceBox;
    public Button goToDetailedViewButton;
    public ChoiceBox serviceStateToLocationTypeBox;
    public ChoiceBox serviceStateToLocationNameBox;
    public Circle step6indicator;
    public Circle step5indicator;
    public Circle step7indicator;
    public Circle step8indicator;
    public Circle step9indicator;
    public Circle step10indicator;
    public Circle step11indicator;
    public Circle step12indicator;
    private PortCallManager manager;
    private PortCall call;
    private TimeStampManager tsmanager;
    private StatementReader reader;
    

    /**
     * Method to create the options for the drop down menus used in the view.
     * Also defaults the PortCall to the most recently changed.
     */
    public void initialize(){

        serviceTypeBox.setItems(FXCollections.observableArrayList(ServiceObject.values()));
        serviceSequenceBox.setItems(FXCollections.observableArrayList(ServiceTimeSequence.values()));
        serviceTimeTypeBox.setItems(FXCollections.observableArrayList(TimeType.values()));
        locationTypeBox.setItems(FXCollections.observableArrayList(LogicalLocation.values()));
        locationTimeSequenceBox.setItems((FXCollections.observableArrayList(LocationTimeSequence.values())));
        toLocationBox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
        fromLocationBox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
        locationTimeTypeBox.setItems((FXCollections.observableArrayList(TimeType.values())));
        serviceStateToLocationTypeBox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));

        manager = new PortCallManager();
        this.call = manager.getActiveCall();
        reader = new StatementReader(call);
        tsmanager = new TimeStampManager(reader.getAllStatements());
        currentIdDisplay.setText(call.getVessel().getName());

        portCallPicker.setItems((FXCollections.observableArrayList(manager.getIds())));
        portCallPicker.setValue(call.getId());
        updateTimes();
        updateColors();
    }

    /**
     * Method used for changing the call used. Also used for refreshing as a PortCall is a snapshot and not dynamic.
     * @param call The PortCall to be shown.
     */

    public void setCall(PortCall call){
        this.call = call;
        currentIdDisplay.setText(call.getVessel().getName());
        reader.setActiveCall(call);
        tsmanager.setStatements(reader.getAllStatements());
        updateTimes();
        updateColors();
    }

    /**
     * Method for retrieving timestamps for the service states and location states presented in the view.
     */
    public void updateTimes(){
        step1time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step2time.setText((reader.getStatement("Arrival_Vessel_AnchorageArea")));
        step3time.setText((reader.getStatement("Arrival_Vessel_PilotBA")));
        step4time.setText((reader.getStatement("Arrival_Vessel_TugZone")));
        /*step5time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step6time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step7time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step8time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step9time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step10time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step11time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step12time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));*/
    }

    /**
     * Method used to call the logic for updating colors for every circle in the view.
     */
    public void updateColors(){
        step1indicator.setFill(tsmanager.checkStatements("Arrival_Vessel_TrafficArea").getColor());
        step2indicator.setFill(tsmanager.checkStatements("Arrival_Vessel_AnchorageArea").getColor());
        step3indicator.setFill(tsmanager.checkStatements("Arrival_Vessel_PilotBA").getColor());
        step4indicator.setFill(tsmanager.checkStatements("Arrival_Vessel_TugZone").getColor());
        step5indicator.setFill(tsmanager.checkStatements("").getColor());
        step6indicator.setFill(tsmanager.checkStatements("").getColor());

        step7indicator.setFill(tsmanager.checkStatements("").getColor());
        step8indicator.setFill(tsmanager.checkStatements("").getColor());
        step9indicator.setFill(tsmanager.checkStatements("").getColor());
        step10indicator.setFill(tsmanager.checkStatements("").getColor());
        step11indicator.setFill(tsmanager.checkStatements("").getColor());
        step12indicator.setFill(tsmanager.checkStatements("").getColor());

    }

    /**
     * Method for creating a message when pressing the send location state button.
     * Uses the selected options in the corresponding menus and text fields. Uses assertions to ensure no required field is null.
     * @param actionEvent sent when the send location state button is pressed.
     */
    public void sendlocationstate(ActionEvent actionEvent) {
        MessageSender sender = new MessageSender();


        //Retrieve the enums and use them in the menus. Uses assertions to check that all required fields are filled.
        Assert.assertNotNull(locationTimeSequenceBox.getValue(),"Please select a time sequence.");
        LocationTimeSequence timeSequence = (LocationTimeSequence) locationTimeSequenceBox.getValue();
        Assert.assertNotNull(toLocationBox.getValue(),"Please select the to location.");
        LogicalLocation tolocation = (LogicalLocation) toLocationBox.getValue();
        //No assertion as this field is allowed to be null.
        LogicalLocation fromlocation = (LogicalLocation) fromLocationBox.getValue();
        Assert.assertNotNull(locationTimeTypeBox.getValue(),"Please select the time type.");
        TimeType locationtimetype = (TimeType) locationTimeTypeBox.getValue();
        Assert.assertNotNull(locationStateToLocationNameChoiceBox.getValue(),"Please select the to location name.");
        String tolocationName = (String) locationStateToLocationNameChoiceBox.getValue();
        //No assertion as this field is allowed to be null.
        String fromlocationName = (String) locationStateFromLocationNameChoiceBox.getValue();

        //Create the time string
        Assert.assertNotNull(locationDateBox.getValue(),"Please select a date.");
        String time = locationDateBox.getValue().toString();
        Assert.assertNotNull(locationHoursBox.getText(),"Please enter the hour.");
        Assert.assertNotNull(locationMinutesBox.getText(),"Please enter the minutes.");
        time = time + "T" + locationHoursBox.getText() + ":" + locationMinutesBox.getText() + ":00.000Z";

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
        Assert.assertNotNull(serviceTypeBox.getValue(),"Please select a service state.");
        ServiceObject servicetype = (ServiceObject) serviceTypeBox.getValue() ;
        Assert.assertNotNull(serviceSequenceBox.getValue(),"Please select a service sequence.");
        ServiceTimeSequence servicesequence = (ServiceTimeSequence) serviceSequenceBox.getValue();
        Assert.assertNotNull(serviceTimeTypeBox.getValue(),"Please select a time type.");
        TimeType servicetimetype = (TimeType) serviceTimeTypeBox.getValue();
        Assert.assertNotNull(locationTypeBox.getValue(),"Please select a location type.");
        LogicalLocation location = (LogicalLocation) locationTypeBox.getValue();
        Assert.assertNotNull(serviceStateLocationNameChoiceBox.getValue(),"Please select a location name.");
        String locationName = (String) serviceStateLocationNameChoiceBox.getValue();
        //Create the time string
        Assert.assertNotNull(serviceDateBox.getValue(),"Please select a date.");
        String time = serviceDateBox.getValue().toString();
        //Builds a string representing time in the format used by portCDM
        Assert.assertNotNull(serviceHoursBox.getText(),"Please enter the hour.");
        Assert.assertNotNull(serviceMinutesBox.getText(),"Please enter the minutes.");
        time = time + "T" + serviceHoursBox.getText() + ":" + serviceMinutesBox.getText() + ":00.000Z";

        if(servicetype.toString().equals("PILOTAGE")|| servicetype.toString().equals("ESCORT_TOWAGE")|| servicetype.toString().equals("TOWAGE")){
            Assert.assertNotNull(serviceStateToLocationTypeBox.getValue(),"Please select to location type.");
            LogicalLocation target = (LogicalLocation) serviceStateToLocationTypeBox.getValue();
            Assert.assertNotNull(serviceStateToLocationNameBox.getValue(),"Please select to location name.");
            String tolocationname = (String) serviceStateToLocationNameBox.getValue();
            sender.sendServiceState(call,servicetype,servicesequence,location,locationName,target,tolocationname,time,servicetimetype, call.getId());
        }
        else {
            //Send the message
            sender.sendServiceState(call, servicetype, servicesequence,
                    location, locationName, time,
                    servicetimetype, call.getId());
        }

    }

    /**Activated when the user changes portcallID in the drop down menu
     * @param actionEvent automatically generated when the user selects a different option in the drop down menu for portcallID
     */
    public void changecall(ActionEvent actionEvent) {
        setCall(manager.getPortCall((String) portCallPicker.getSelectionModel().getSelectedItem()));
    }

    /**
     * Activated when the user clicks the "refresh" button, refreshes the view and updates the time and colors for the locations/services
     * @param actionEvent  send when the refresh button is pressen
     */
    public void refreshview(ActionEvent actionEvent) {
        setCall(manager.getPortCall((String) portCallPicker.getSelectionModel().getSelectedItem()));
    }

    /**
     * Changes the active view to the detailed view when the user clicks the "go to detailed view" button
     * @param actionEvent  sent when the go to detailed view button is pressed.
     */
    public void goToDetailedView(ActionEvent actionEvent) {
       Stage stage = (Stage) goToDetailedViewButton.getScene().getWindow();
        try {
            stage.setScene(new Scene((AnchorPane)FXMLLoader.load(getClass().getResource("/views/DetailedView.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**When the user selects a location type in any of the location type menus,
     *the corresponding location name menu is set to contain the names of all locations of that type in the port
     * @param actionEvent sent when a location type is chosen in any of the manus containen location types.
     */
    public void locationTypeChosen(ActionEvent actionEvent) {
        ChoiceBox tempBox;
        ChoiceBox source;
        String locationtype;
        if (actionEvent.getSource().equals(locationTypeBox)){locationtype = locationTypeBox.getValue().toString(); tempBox = serviceStateLocationNameChoiceBox;}
        else if (actionEvent.getSource().equals(serviceStateToLocationTypeBox)){locationtype = serviceStateToLocationTypeBox.getValue().toString(); tempBox = serviceStateToLocationNameBox;}
        else if (actionEvent.getSource().equals(toLocationBox)){locationtype = toLocationBox.getValue().toString(); tempBox = locationStateToLocationNameChoiceBox;}
        else if (actionEvent.getSource().equals(fromLocationBox)){locationtype = fromLocationBox.getValue().toString(); tempBox = locationStateFromLocationNameChoiceBox;}
        else{return;}
        if(locationtype.equals("BERTH")){
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getBerths()));
        }
        else if(locationtype.equals("TRAFFIC_AREA")){
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getTrafficAreas()));
        }
        else if(locationtype.equals("ANCHORING_AREA")){
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getAnchoringAreas()));
        }
        else if(locationtype.equals("TUG_ZONE")){
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getTugZones()));
        }
        else if(locationtype.equals("PILOT_BOARDING_AREA")){
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getPilotBAs()));
        }
        else if(locationtype.equals("ETUG_ZONE")){
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.geteTugZones()));
        }
        else if(locationtype.equals("LOC")){
            tempBox.setItems(FXCollections.observableArrayList(LocationManager.getLocs()));
        }
        else if(locationtype.equals("VESSEL")){
            tempBox.setItems(FXCollections.observableArrayList("VESSEL"));

        }



    }
}
