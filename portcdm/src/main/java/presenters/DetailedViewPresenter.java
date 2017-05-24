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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LocationManager;
import model.MessageSender;
import model.PortCallManager;
import model.StatementReader;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by arono on 2017-05-22.
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
    public Button sendservicestatebox;
    public ChoiceBox locationtimesqeuencebox;
    public ChoiceBox tolocationbox;
    public ChoiceBox fromlocationbox;
    public DatePicker locationdatebox;
    public TextField locationhoursbox;
    public TextField locationminutesbox;
    public ChoiceBox locationtimetypebox;
    public Button sendlocationbox;
    public ChoiceBox locationstatefromlocationnamecoicebox;
    public ChoiceBox locationstatetolocationnamechoicebox;
    public ChoiceBox servicestatelocationnamechoicebox;
    public Button gotooverviewbutton;
    public ChoiceBox servicestatetolocationtypechoicebox;
    public ChoiceBox servicestatetolocationnamechoicebox;

    private PortCallManager pcmanager;
    private StatementReader reader;
    private PortCall call;

    public void initialize(){
        pcmanager = new PortCallManager();
        call = pcmanager.getActiveCall();
        reader = new StatementReader(call);
        vesselName.setText(call.getVessel().getName());
        latestUpdate.setText(call.getLastUpdate());
        pclist.setItems(FXCollections.observableArrayList(pcmanager.getIds()));
        stateList.setItems(FXCollections.observableArrayList(reader.getStateDefinitions()));

        servicetypebox.setItems(FXCollections.observableArrayList(ServiceObject.values()));
        servicesequencebox.setItems(FXCollections.observableArrayList(ServiceTimeSequence.values()));
        servicetimetypebox.setItems(FXCollections.observableArrayList(TimeType.values()));
        locationtypebox.setItems(FXCollections.observableArrayList(LogicalLocation.values()));
        locationtimesqeuencebox.setItems((FXCollections.observableArrayList(LocationTimeSequence.values())));
        tolocationbox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
        fromlocationbox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));
        locationtimetypebox.setItems((FXCollections.observableArrayList(TimeType.values())));
        servicestatetolocationtypechoicebox.setItems((FXCollections.observableArrayList(LogicalLocation.values())));

        locationtypebox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LogicalLocation>() {
            public void changed(ObservableValue<? extends LogicalLocation> observable, LogicalLocation oldValue, LogicalLocation newValue) {
                String locationtype = newValue.toString();
                if(locationtype.equals("BERTH")){
                    servicestatelocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getBerths()));
                }
                else if(locationtype.equals("TRAFFIC_AREA")){
                    servicestatelocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getTrafficAreas()));
                }
                else if(locationtype.equals("ANCHORING_AREA")){
                    servicestatelocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getAnchoringAreas()));
                }
                else if(locationtype.equals("TUG_ZONE")){
                    servicestatelocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getTugZones()));
                }
                else if(locationtype.equals("PILOT_BOARDING_AREA")){
                    servicestatelocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getPilotBAs()));
                }
                else if(locationtype.equals("ETUG_ZONE")){
                    servicestatelocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.geteTugZones()));
                }
                else if(locationtype.equals("LOC")){
                    servicestatelocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getLocs()));
                }
                else if(locationtype.equals("VESSEL")){
                    servicestatelocationnamechoicebox.setItems(FXCollections.observableArrayList("VESSEL"));

                }
            }
        });

        tolocationbox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LogicalLocation>() {
            public void changed(ObservableValue<? extends LogicalLocation> observable, LogicalLocation oldValue, LogicalLocation newValue) {
                String locationtype = newValue.toString();
                if(locationtype.equals("BERTH")){
                    locationstatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getBerths()));
                }
                else if(locationtype.equals("TRAFFIC_AREA")){
                    locationstatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getTrafficAreas()));
                }
                else if(locationtype.equals("ANCHORING_AREA")){
                    locationstatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getAnchoringAreas()));
                }
                else if(locationtype.equals("TUG_ZONE")){
                    locationstatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getTugZones()));
                }
                else if(locationtype.equals("PILOT_BOARDING_AREA")){
                    locationstatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getPilotBAs()));
                }
                else if(locationtype.equals("ETUG_ZONE")){
                    locationstatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.geteTugZones()));
                }
                else if(locationtype.equals("LOC")){
                    locationstatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getLocs()));
                }
                else if(locationtype.equals("VESSEL")){
                    locationstatetolocationnamechoicebox.setItems(FXCollections.observableArrayList("VESSEL"));

                }
            }
        });

        fromlocationbox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LogicalLocation>() {
            public void changed(ObservableValue<? extends LogicalLocation> observable, LogicalLocation oldValue, LogicalLocation newValue) {
                String locationtype = newValue.toString();
                if(locationtype.equals("BERTH")){
                    locationstatefromlocationnamecoicebox.setItems(FXCollections.observableArrayList(LocationManager.getBerths()));
                }
                else if(locationtype.equals("TRAFFIC_AREA")){
                    locationstatefromlocationnamecoicebox.setItems(FXCollections.observableArrayList(LocationManager.getTrafficAreas()));
                }
                else if(locationtype.equals("ANCHORING_AREA")){
                    locationstatefromlocationnamecoicebox.setItems(FXCollections.observableArrayList(LocationManager.getAnchoringAreas()));
                }
                else if(locationtype.equals("TUG_ZONE")){
                    locationstatefromlocationnamecoicebox.setItems(FXCollections.observableArrayList(LocationManager.getTugZones()));
                }
                else if(locationtype.equals("PILOT_BOARDING_AREA")){
                    locationstatefromlocationnamecoicebox.setItems(FXCollections.observableArrayList(LocationManager.getPilotBAs()));
                }
                else if(locationtype.equals("ETUG_ZONE")){
                    locationstatefromlocationnamecoicebox.setItems(FXCollections.observableArrayList(LocationManager.geteTugZones()));
                }
                else if(locationtype.equals("LOC")){
                    locationstatefromlocationnamecoicebox.setItems(FXCollections.observableArrayList(LocationManager.getLocs()));
                }
                else if(locationtype.equals("VESSEL")){
                    locationstatefromlocationnamecoicebox.setItems(FXCollections.observableArrayList("VESSEL"));

                }
            }
        });
        servicestatetolocationtypechoicebox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LogicalLocation>() {
            public void changed(ObservableValue<? extends LogicalLocation> observable, LogicalLocation oldValue, LogicalLocation newValue) {
                String locationtype = newValue.toString();
                if(locationtype.equals("BERTH")){
                    servicestatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getBerths()));
                }
                else if(locationtype.equals("TRAFFIC_AREA")){
                    servicestatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getTrafficAreas()));
                }
                else if(locationtype.equals("ANCHORING_AREA")){
                    servicestatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getAnchoringAreas()));
                }
                else if(locationtype.equals("TUG_ZONE")){
                    servicestatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getTugZones()));
                }
                else if(locationtype.equals("PILOT_BOARDING_AREA")){
                    servicestatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getPilotBAs()));
                }
                else if(locationtype.equals("ETUG_ZONE")){
                    servicestatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.geteTugZones()));
                }
                else if(locationtype.equals("LOC")){
                    servicestatetolocationnamechoicebox.setItems(FXCollections.observableArrayList(LocationManager.getLocs()));
                }
                else if(locationtype.equals("VESSEL")){
                    servicestatetolocationnamechoicebox.setItems(FXCollections.observableArrayList("VESSEL"));

                }
            }
        });
    }



    public void refresh(MouseEvent mouseEvent) {
        detailedviewtext.setText(reader.getStatements((String) stateList.getSelectionModel().getSelectedItem()));
        vesselName.setText(call.getVessel().getName());
    }

    public void changeCall(MouseEvent mouseEvent) {
        call = pcmanager.getPortCall((String) pclist.getSelectionModel().getSelectedItem());
        vesselName.setText(call.getVessel().getName());
        latestUpdate.setText(call.getLastUpdate());
        reader.setActiveCall(call);
        stateList.setItems(FXCollections.observableArrayList(reader.getStateDefinitions()));
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
            LogicalLocation target = (LogicalLocation) servicestatetolocationtypechoicebox.getValue();
            String tolocationname = (String) servicestatetolocationnamechoicebox.getValue();
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


    public void refreshpcs(ActionEvent actionEvent) {
        pcmanager.refreshCalls();
        pclist.setItems(FXCollections.observableArrayList(pcmanager.getIds()));
    }

    public void goToMainView(ActionEvent actionEvent) {
        Stage stage = (Stage) gotooverviewbutton.getScene().getWindow();
        try {
            stage.setScene(new Scene((ScrollPane) FXMLLoader.load(getClass().getResource("/views/MainView.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
