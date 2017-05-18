package views;

import presenters.ShipAgentPresenter;

/**
 * Created by hanneslagerroth on 2017-05-15.
 */
public interface ShipAgentView  {

    void setListener(ShipAgentPresenter listener);

    void updatePortCall();
}
