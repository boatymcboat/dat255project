package presenters;

import eu.portcdm.dto.PortCall;
import eu.portcdm.messaging.PortCallMessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.MessageSender;
import model.PortCallManager;
import model.StatementReader;
import views.PortCallOverview;
import views.PortCallText;
import views.ShipAgentView;

import javax.swing.event.ChangeEvent;


/**
 * Created by hanneslagerroth on 2017-05-18.
 */
public class ShipAgentPresenter1 implements ShipAgentPresenter, ChangeListener<String> {

    private PortCallManager manager;
    private PortCall call;
    private StatementReader sreader;
    private ShipAgentView view;

    public ShipAgentPresenter1(){
          manager = new PortCallManager();
          call = manager.getActiveCall();
          sreader = new StatementReader(call);
    }


    public void setView(ShipAgentView shipAgentView) {
        this.view = shipAgentView;
    }

    public void updatePortCall() {

        PortCallText portCallText = new PortCallText();

        portCallText.setArrivalVessel_TrafficArea(sreader.getStatement("Arrival_Vessel_TrafficArea"));
        portCallText.setArrivalVessel_PilotBA(sreader.getStatement("Arrival_Vessel_PilotBA"));
        portCallText.setArrivalVessel_TugZone(sreader.getStatement("Arrival_Vessel_TugZone"));
        portCallText.setArrivalVessel_Berth(sreader.getStatement("Arrival_Vessel_Berth"));

        portCallText.setCargoOp_Commenced(sreader.getStatement("CargoOp_Commenced"));
        portCallText.setCargoOp_Completed(sreader.getStatement("CargoOp_Completed"));

        portCallText.setDeparture_Vessel_Berth(sreader.getStatement("Departure_Vessel_Berth"));
        portCallText.setDeparture_Tug_Vessel(sreader.getStatement("Departure_Tug_Vessel")); // osäker
        portCallText.setDeparture_Pilot_Vessel(sreader.getStatement("Departure_Pilot_Vessel")); // osäker

        view.updatePortCall(portCallText);
    }


    public void changePortCall(String newPortCall) {
        sreader = new StatementReader(manager.getPortCall(Integer.parseInt(newPortCall)));
        updatePortCall();
    }

    public void handle(ActionEvent event) {
        MessageSender sender = new MessageSender();
        PortCallMessage message = sender.createMessage();
        sender.sendMessage(message);
    }

    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        changePortCall(newValue);
    }
}
