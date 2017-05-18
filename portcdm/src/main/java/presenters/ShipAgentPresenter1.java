package presenters;

import eu.portcdm.dto.PortCall;
import eu.portcdm.messaging.PortCallMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.MessageSender;
import model.PortCallManager;
import model.StatementReader;
import views.PortCallOverview;
import views.ShipAgentView;

/**
 * Created by hanneslagerroth on 2017-05-18.
 */
public class ShipAgentPresenter1 implements ShipAgentPresenter, EventHandler<ActionEvent> {

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

    }

    public void changePortCall() {

    }

    public void handle(ActionEvent event) {

        MessageSender sender = new MessageSender();
        PortCallMessage message = sender.createMessage();
        sender.sendMessage(message);
    }
}
