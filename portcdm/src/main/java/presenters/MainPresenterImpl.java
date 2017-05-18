package presenters;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import views.MainView;
import views.ShipAgentView;
import views.ShipAgentView1;

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

        ShipAgentView shipAgentView = null;

        if (view_id == 1){
            shipAgentView = new ShipAgentView1();
        }

        ShipAgentPresenter shipAgentPresenter = new ShipAgentPresenter1();
        shipAgentPresenter.setView(shipAgentView);
        shipAgentView.setButtonListener(shipAgentPresenter);
        shipAgentView.setDropDownListener((ChangeListener<String>) shipAgentPresenter);


        mainView.setShipAgentView(view_id, shipAgentView);


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
