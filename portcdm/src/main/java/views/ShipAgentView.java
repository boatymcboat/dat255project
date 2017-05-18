package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public interface ShipAgentView  {

    void setListener(EventHandler<ActionEvent> listener);

    void updatePortCall();
}
