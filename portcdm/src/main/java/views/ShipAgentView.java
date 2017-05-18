package views;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import presenters.ShipAgentPresenter;

/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public interface ShipAgentView  {

    void setButtonListener(EventHandler<ActionEvent> listener);

    void updatePortCall(PortCallText portCallText);

    void setDropDownListener(ChangeListener<String> listener);

    void setup(GridPane grid);
}
