package views;

import eu.portcdm.dto.PortCall;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import presenters.ShipAgentPresenter;

import java.io.IOException;

import static views.SizeAndGrid.getBackButtonColumn;
import static views.SizeAndGrid.getBackButtonRow;

/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public class ShipAgentView1 implements ShipAgentView {

    private Button message;
    private PortCallOverview portcalloverview;
    private ComboBox availablePortcalls;


    public ShipAgentView1(){
        message = new Button ("Send a sample message");
        portcalloverview = new PortCallOverview(0);
        availablePortcalls = Create_Drop_Down_Menu(new String[]{"0","1","2","3","4"});

    }

    //Creates the drop-down menu
    public  ComboBox Create_Drop_Down_Menu(String[] optionsArray){
        ObservableList<String> choices = FXCollections.observableArrayList();
        choices.addAll(optionsArray);
        return new ComboBox(choices);
    }

    public void setButtonListener(EventHandler<ActionEvent> listener) {
        message.setOnAction(listener);
    }

    public void setDropDownListener(ChangeListener<String> listener) {
        availablePortcalls.valueProperty().addListener(listener);
    }

    public void setup(GridPane grid) {

        final Text sceneTitle = new Text("Welcome to views 1");
        sceneTitle.setFont(Font.font(26));
        HBox text = new HBox(10);
        text.getChildren().add(sceneTitle);
        grid.add(sceneTitle, getBackButtonColumn(), getBackButtonRow()-2);

        portcalloverview.setup();
        HBox portcalls = new HBox();


        portcalls.getChildren().add(availablePortcalls);


        HBox button = new HBox();

        button.getChildren().add(message);
        grid.add(button,    getBackButtonColumn()+1,getBackButtonRow()+1);
        grid.add(portcalls, getBackButtonColumn(),getBackButtonRow()+1);
        grid.add(portcalloverview,getBackButtonColumn(), getBackButtonRow()-1);

        //TODO: Move the loading of the FXML file to a more suitable location
        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/presenters/messagesender.fxml"));
            grid.add(pane, getBackButtonColumn(),getBackButtonRow()+2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePortCall(PortCallText portCallText) {
        portcalloverview.update(portCallText);
    }
}
