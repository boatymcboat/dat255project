package sample;


import eu.portcdm.dto.PortCall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import java.lang.*;
import java.awt.event.*;
import javafx.scene.control.ScrollPane;

import static sample.SizeAndGrid.*;
public class PortCallOverview extends HBox {

    private Text arrivalt1;
    private Text arrivalt2;
    private Text arrivalt3;
    private Text arrivalt4;

    private Text visitt1;
    private Text visitt2;
    private Text visitt3;

    private Text departuret1;
    private Text departuret2;
    private Text departuret3;

    PortCallManager manager;
    PortCall call;
    StatementReader sreader;

    public PortCallOverview(double spacing) {
        super(spacing);

        manager = new PortCallManager();
        call = manager.getActiveCall();
        sreader = new StatementReader(call);

    } //Constructor

    public void update() {

        arrivalt1.setText(sreader.getStatement("Arrival_Vessel_TrafficArea"));
        arrivalt2.setText(sreader.getStatement("Arrival_Vessel_PilotBA"));
        arrivalt3.setText(sreader.getStatement("Arrival_Vessel_TugZone"));
        arrivalt4.setText(sreader.getStatement("Arrival_Vessel_Berth"));

        visitt1.setText(sreader.getStatement("CargoOp_Commenced"));
        visitt2.setText(sreader.getStatement("CargoOp_Completed"));
        //visitt1.setText(sreader.getStatement("CargoOp_Commenced")); // vet ej

        departuret1.setText(sreader.getStatement("Departure_Vessel_Berth"));
        departuret2.setText(sreader.getStatement("Departure_Tug_Vessel")); // osäker
        departuret3.setText(sreader.getStatement("Departure_Pilot_Vessel")); // osäker

        //System.out.println(sreader.getStatement("Arrival_Vessel_PilotBA")); */
    }

    public void setup() {
        Insets columnInsets = new Insets(25,25,25,25);

        //HBox stages_HBox = new HBox(150);
        this.setStyle("-fx-background-color: khaki");
        VBox arrival_VBox = new VBox(50);
        arrival_VBox.setStyle("-fx-background-color: lightsteelblue");
        arrival_VBox.setPadding(columnInsets);
        VBox visit_VBox = new VBox(50);
        visit_VBox.setStyle("-fx-background-color: darkorange");
        visit_VBox.setPadding(columnInsets);
        VBox departure_VBox = new VBox(50);
        departure_VBox.setStyle("-fx-background-color: greenyellow");
        departure_VBox.setPadding(columnInsets);

        VBox arrivalboxh = new VBox(10);
        arrivalboxh.setAlignment(Pos.CENTER);
        VBox arrivalbox1 = new VBox(10);
        VBox arrivalbox2 = new VBox(10);
        VBox arrivalbox3 = new VBox(10);
        VBox arrivalbox4 = new VBox(10);

        VBox visitboxh = new VBox(10);
        visitboxh.setAlignment(Pos.CENTER);
        VBox visitbox1 = new VBox(10);
        VBox visitbox2 = new VBox(10);
        VBox visitbox3 = new VBox(10);

        VBox departureboxh = new VBox(10);
        departureboxh.setAlignment(Pos.CENTER);
        VBox departurebox1 = new VBox(10);
        VBox departurebox2 = new VBox(10);
        VBox departurebox3 = new VBox(10);

        Text header1 = new Text("Arrival");
        header1.setFont(Font.font(32));
        Text arrival1 = new Text("Arrival Traffic Area");
        arrivalt1 = new Text("Time");
        Text arrival2 = new Text("Arrival Pilot Station");
        arrivalt2 = new Text("Time");
        Text arrival3 = new Text("Arrival Tug Zone");
        arrivalt3 = new Text("Time");
        Text arrival4 = new Text("Arrival Berth");
        arrivalt4 = new Text("Time");

        Text header2 = new Text("Visit");
        header2.setFont(Font.font(32));
        Text visit1 = new Text("Cargo Operation Commenced");
        visitt1 = new Text("Time");
        Text visit2 = new Text("Cargo Operation Completed");
        visitt2 = new Text("Time");
        Text visit3 = new Text("Departure Berth Request");
        visitt3 = new Text("Time");

        Text header3 = new Text("Departure");
        header3.setFont(Font.font(32));
        Text departure1 = new Text("Departure Berth");
        departuret1 = new Text("Time");
        Text departure2 = new Text("Departure Tug Zone");
        departuret2 = new Text("Time");
        Text departure3 = new Text("Departure Pilot Station");
        departuret3 = new Text("Time");

        arrivalboxh.getChildren().add(header1);
        arrivalbox1.getChildren().add(arrival1);
        arrivalbox1.getChildren().add(arrivalt1);
        arrivalbox2.getChildren().add(arrival2);
        arrivalbox2.getChildren().add(arrivalt2);
        arrivalbox3.getChildren().add(arrival3);
        arrivalbox3.getChildren().add(arrivalt3);
        arrivalbox4.getChildren().add(arrival4);
        arrivalbox4.getChildren().add(arrivalt4);

        visitboxh.getChildren().add(header2);
        visitbox1.getChildren().add(visit1);
        visitbox1.getChildren().add(visitt1);
        visitbox2.getChildren().add(visit2);
        visitbox2.getChildren().add(visitt2);
        visitbox3.getChildren().add(visit3);
        visitbox3.getChildren().add(visitt3);

        departureboxh.getChildren().add(header3);
        departurebox1.getChildren().add(departure1);
        departurebox1.getChildren().add(departuret1);
        departurebox2.getChildren().add(departure2);
        departurebox2.getChildren().add(departuret2);
        departurebox3.getChildren().add(departure3);
        departurebox3.getChildren().add(departuret3);

        arrival_VBox.getChildren().add(arrivalboxh);
        arrival_VBox.getChildren().add(arrivalbox1);
        arrival_VBox.getChildren().add(arrivalbox2);
        arrival_VBox.getChildren().add(arrivalbox3);
        arrival_VBox.getChildren().add(arrivalbox4);

        visit_VBox.getChildren().add(visitboxh);
        visit_VBox.getChildren().add(visitbox1);
        visit_VBox.getChildren().add(visitbox2);
        visit_VBox.getChildren().add(visitbox3);

        departure_VBox.getChildren().add(departureboxh);
        departure_VBox.getChildren().add(departurebox1);
        departure_VBox.getChildren().add(departurebox2);
        departure_VBox.getChildren().add(departurebox3);

        this.getChildren().add(arrival_VBox);
        this.getChildren().add(visit_VBox);
        this.getChildren().add(departure_VBox);

    }//setup

}
