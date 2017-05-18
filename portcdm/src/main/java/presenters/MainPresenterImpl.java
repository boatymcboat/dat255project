package presenters;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import views.MainView;

public class MainPresenterImpl implements MainPresenter, EventHandler<ActionEvent> {

    private MainView mainView;


    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainView.setListener(this);
    }

    public void setView(MainView view) {
        this.mainView = view;
    }

    public void openShipAgentView(int view_id) {

        EventHandler<ActionEvent> shipAgentPresenter = new ShipAgentPresenter1();
        mainView.setShipAgentView(view_id, shipAgentPresenter);
    }

    public void handle(ActionEvent e) {

        if (e.getSource().toString().contains("Start Agent Application")) { //TODO make this more reliable
            openShipAgentView(1);
        } else if (e.getSource().toString().contains("Back")) {
            mainView.goBack();
        }

        e.consume();
    }
}
