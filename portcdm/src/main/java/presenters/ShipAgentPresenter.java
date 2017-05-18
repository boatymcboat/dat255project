package presenters;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public interface ShipAgentPresenter extends EventHandler<ActionEvent> {

    void updatePortCall();

    void changePortCall();

    void handle(ActionEvent event);
}
