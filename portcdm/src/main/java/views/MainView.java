package views;

import presenters.MainPresenter;

/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public interface MainView {

    void setPresenter(MainPresenter presenter);

    void setShipAgentView(int view_id);
}
