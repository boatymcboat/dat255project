package views;

import eu.portcdm.messaging.PortCallMessage;
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
import model.MessageSender;
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


    public ShipAgentView1(GridPane grid){

        final Text sceneTitle = new Text("Welcome to views 1");
        sceneTitle.setFont(Font.font(26));
        HBox text = new HBox(10);
        text.getChildren().add(sceneTitle);
        grid.add(sceneTitle, getBackButtonColumn(), getBackButtonRow()-2);

        portcalloverview = new PortCallOverview(0);
        portcalloverview.setup();
        HBox portcalls = new HBox();
        ComboBox availablePortcalls = Create_Drop_Down_Menu(new String[]{"0","1","2","3","4"});
        portcalls.getChildren().add(availablePortcalls);
        availablePortcalls.valueProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue observable, String oldValue, String newValue) {
                portcalloverview.changePortcall(newValue);
            }
        });


        HBox button = new HBox();
        message = new Button ("Send a sample message");

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
        updatePortCall();
    }




    //TODO create helper classes for these, to reduce code duplication
    private Scene CreateEmptyView(GridPane grid){
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(grid);
        return new Scene(scrollPane,1200,768);
    }

    //Creates the drop-down menu
    public  ComboBox Create_Drop_Down_Menu(String[] optionsArray){
        ObservableList<String> choices = FXCollections.observableArrayList();
        choices.addAll(optionsArray);
        return new ComboBox(choices);
    }

    public void setListener(EventHandler<ActionEvent> listener) {
        System.out.printf("asdkjhnm");
        message.setOnAction(listener);
    }

    public void updatePortCall() {
        portcalloverview.update();
    }
}
