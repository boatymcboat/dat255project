package presenters;

import eu.portcdm.dto.LocationTimeSequence;
import eu.portcdm.dto.PortCall;
import eu.portcdm.messaging.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
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
    public Text step1statetype;
    public Circle step1indicator;
    public Text step2statetype;
    public Circle step2indicator;
    public Text step3statetype;
    public Circle step3indicator;
    public Text step4statetype;
    public Circle step4indicator;
    public Text step5statetype;
    public Circle step5indicator;
    public Text step6statetype;
    public Circle step6indicator;
    public Text step7statetype;
    public Circle step7indicator;
    public Text step8statetype;
    public Circle step8indicator;
    public Text step9statetype;
    public Circle step9indicator;
    public Text step10statetype;
    public Circle step10indicator;
    public Text step11statetype;
    public Circle step11indicator;
    public Text step12statetype;
    public Circle step12indicator;
    public Circle noestimatescircle;
    public Circle actualacquiredcircle;
    public Circle estimatesokcircle;
    public Circle warningcircle;
    public Text step1time;
    public Text step2time;
    public Text step3time;
    public Text step4time;
    public Text step5time;
    public Text step6time;
    public Text step7time;
    public Text step8time;
    public Text step9time;
    public Text step10time;
    public Text step11time;
    public Text step12time;
    public ChoiceBox servicestatelocationnamechoicebox;
    public ChoiceBox locationstatetolocationnamechoicebox;
    public ChoiceBox locationstatefromlocationnamecoicebox;
    public Button refreshbutton;
    public Button gotodetailedviewbutton;
    public ChoiceBox servicestatetolocationtype;
    public ChoiceBox servicestatetolocationname;
    private PortCallManager manager;
    private PortCall call;
    private TimeStampManager tsmanager;
    private StatementReader reader;

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
        servicestatetolocationtype.setItems((FXCollections.observableArrayList(LogicalLocation.values())));

        manager = new PortCallManager();
        this.call = manager.getActiveCall();
        reader = new StatementReader(call);
        tsmanager = new TimeStampManager(reader.getAllStatements());
        CurrentIdDisplay.setText(call.getVessel().getName());

        portcallpicker.setItems((FXCollections.observableArrayList(manager.getIds())));
        portcallpicker.setValue(call.getId());
        updateTimes();
        updateColors();
    }

    public void setCall(PortCall call){
        this.call = call;
        CurrentIdDisplay.setText(call.getVessel().getName());
        reader.setActiveCall(call);
        tsmanager.setStatements(reader.getAllStatements());
        updateTimes();
        updateColors();
    }
    
    public void updateTimes(){
        step1time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step2time.setText((reader.getStatement("Arrival_Vessel_AnchorageArea")));
        step3time.setText((reader.getStatement("Arrival_Vessel_PilotBA")));
        step4time.setText((reader.getStatement("Arrival_Vessel_TugZone")));
        step5time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step6time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step7time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step8time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step9time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step10time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step11time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
        step12time.setText((reader.getStatement("Arrival_Vessel_TrafficArea")));
    }
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
        String tolocationName = (String) locationstatetolocationnamechoicebox.getValue();
        String fromlocationName = (String) locationstatefromlocationnamecoicebox.getValue();

        //Create the time string
        String time = locationdatebox.getValue().toString();
        time = time + "T" + locationhoursbox.getText() + ":" + locationminutesbox.getText() + ":00.000Z";

        //Compare the manually generated string to the timestamp
        System.out.println("Manually generated: " +time);
        System.out.println("Timestamp: " + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        //Send the message
        //TODO: Solve the issue that the manually generated string don't seem to work when used in this method
        sender.sendLocationState(call, timeSequence,
                fromlocation, fromlocationName, tolocation, tolocationName, time, locationtimetype, call.getId());
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
        String locationName = (String) servicestatelocationnamechoicebox.getValue();
        //Create the time string
        String time = servicedatebox.getValue().toString();
        time = time + "T" + servicehoursbox.getText() + ":" + serviceminutesbox.getText() + ":00.000Z";

        //Compare the manually generated string to the timestamp
        System.out.println("Manually generated: " +time);
        System.out.println("Timestamp: " + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        if(servicetype.toString().equals("PILOTAGE")|| servicetype.toString().equals("ESCORT_TOWAGE")|| servicetype.toString().equals("TOWAGE")){
            LogicalLocation target = (LogicalLocation) servicestatetolocationtype.getValue();
            String tolocationname = (String) servicestatetolocationname.getValue();
            sender.sendServiceState(call,servicetype,servicesequence,location,locationName,target,tolocationname,time,servicetimetype, call.getId());
        }
        else {
            //Send the message
            //TODO: Solve the issue that the manually generated string don't seem to work when used in this method
            sender.sendServiceState(call, servicetype, servicesequence,
                    location, locationName, time,
                    servicetimetype, call.getId());
        }

    }

    public void changecall(ActionEvent actionEvent) {
        setCall(manager.getPortCall((String) portcallpicker.getSelectionModel().getSelectedItem()));
    }

    public void refreshview(ActionEvent actionEvent) {
        setCall(manager.getPortCall((String) portcallpicker.getSelectionModel().getSelectedItem()));
    }

    public void goToDetailedView(ActionEvent actionEvent) {
       Stage stage = (Stage) gotodetailedviewbutton.getScene().getWindow();
        try {
            stage.setScene(new Scene((AnchorPane)FXMLLoader.load(getClass().getResource("/views/DetailedView.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void locationTypeChosen(ActionEvent actionEvent) {
        ChoiceBox tempBox;
        ChoiceBox source;
        String locationtype;
        if (actionEvent.getSource().equals(locationtypebox)){locationtype = locationtypebox.getValue().toString(); tempBox = servicestatelocationnamechoicebox;}
        else if (actionEvent.getSource().equals(servicestatetolocationtype)){locationtype = servicestatetolocationtype.getValue().toString(); tempBox = servicestatetolocationname;}
        else if (actionEvent.getSource().equals(tolocationbox)){locationtype = tolocationbox.getValue().toString(); tempBox = locationstatetolocationnamechoicebox;}
        else if (actionEvent.getSource().equals(fromlocationbox)){locationtype = fromlocationbox.getValue().toString(); tempBox = locationstatefromlocationnamecoicebox;}
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
