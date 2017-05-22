package presenters;

import eu.portcdm.dto.PortCall;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import model.PortCallManager;
import model.StatementReader;

/**
 * Created by arono on 2017-05-22.
 */
public class DetailedViewPresenter {

    public Text detailedviewtext;
    public ComboBox detailedviewchoicebox1;
    public ComboBox detailedviewchoicebox2;
    public Button detailedviewrefreshbutton;
    public Text vesselName;

    private PortCallManager pcmanager;
    private StatementReader reader;
    private PortCall call;

    public void initialize(){
        pcmanager = new PortCallManager();
        call = pcmanager.getActiveCall();
        reader = new StatementReader(call);

        detailedviewchoicebox1.setItems(FXCollections.observableArrayList(pcmanager.getIds()));
        detailedviewchoicebox1.setValue(pcmanager.getIds().get(0));
        detailedviewchoicebox2.setItems(FXCollections.observableArrayList(reader.getStateDefinitions()));
        detailedviewchoicebox2.setValue((reader.getStateDefinitions().get(0)));
        vesselName.setText(call.getVessel().getName());
        refresh(new ActionEvent());
    }

    public void refresh(ActionEvent actionEvent) {
        detailedviewtext.setText(reader.getStatements((String) detailedviewchoicebox2.getValue()));
        vesselName.setText(call.getVessel().getName());
    }

    public void changeCall(ActionEvent actionEvent) {
        call = pcmanager.getPortCall((String) detailedviewchoicebox1.getSelectionModel().getSelectedItem());
        reader.setActiveCall(call);
        detailedviewchoicebox2.setItems(FXCollections.observableArrayList(reader.getStateDefinitions()));
        detailedviewchoicebox2.setValue(reader.getStateDefinitions().get(0));
    }

    public void sendservicestate(ActionEvent actionEvent) {
    }

    public void sendlocationstate(ActionEvent actionEvent) {
    }


}
