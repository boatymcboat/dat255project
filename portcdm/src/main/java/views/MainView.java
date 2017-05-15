package views;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;



/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public interface MainView {

    void setListener(EventHandler<ActionEvent> listener);

    void setTitle(String title);

    void setShipAgentView(int view_id);

    void initialize();

}
