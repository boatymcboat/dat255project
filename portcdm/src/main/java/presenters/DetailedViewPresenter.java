package presenters;

import eu.portcdm.dto.PortCall;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.PortCallManager;
import model.StatementReader;

/**
 * Created by arono on 2017-05-22.
 */
public class DetailedViewPresenter {

    public Text detailedviewtext;
    public Text vesselName;
    public ListView pclist;
    public ListView stateList;
    public Text latestUpdate;

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

    public void sendservicestate(ActionEvent actionEvent) {
    }

    public void sendlocationstate(ActionEvent actionEvent) {
    }


    public void refreshpcs(ActionEvent actionEvent) {
        pcmanager.refreshCalls();
        pclist.setItems(FXCollections.observableArrayList(pcmanager.getIds()));
    }
}
