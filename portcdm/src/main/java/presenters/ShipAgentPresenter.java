package presenters;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import views.ShipAgentView;

/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public interface ShipAgentPresenter extends EventHandler<ActionEvent> {

    void updatePortCall();

    void changePortCall(String newPortCall);

    void setView(ShipAgentView view);

    void handle(ActionEvent event);
}
