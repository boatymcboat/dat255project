package views;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import presenters.ShipAgentPresenter;


/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public interface MainView {

    void setListener(EventHandler<ActionEvent> listener);

    void setTitle(String title);

    //TODO change so that you don't need both view_ied and ShipAgentView
    void setShipAgentView(int view_id, ShipAgentView view);

    void initialize();

    void goBack();
}
