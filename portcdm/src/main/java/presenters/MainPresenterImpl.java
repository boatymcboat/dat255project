package presenters;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainView;

public class MainPresenterImpl implements MainPresenter, EventHandler<ActionEvent>{

    private MainView mainView;


    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
        mainView.setListener(this);
    }

    public void setView(MainView view) {
        this.mainView = view;
    }

    public void openShipAgentView(int view_id) {
        mainView.setShipAgentView(view_id);
    }

    public void handle(ActionEvent e) {


        if (e.getSource().toString().contains("Start Agent Application")){ //TODO make this more reliable
            mainView.setShipAgentView(1);
        }
        else if (e.getSource().toString().contains("Back")){
            mainView.goBack();
        }

        e.consume();
    }

    /*private class ButtonHandler implements EventHandler {
        public void handle(Event event) {
            if(choices.getValue() != null && !choices.getValue().toString().isEmpty()){
                if (choices.getValue().toString().equals("option1")){
                    if(!view1_isCreated){
                        CreateView_1();
                        view1_isCreated = true;
                    }
                    mainStage.setScene(view1);

                }
                else if (choices.getValue().toString().equals("option2")){
                    CreateView_1();
                    mainStage.setScene(view1);
                }
                else if (choices.getValue().toString().equals("option3")){
                    CreateView_1();
                    mainStage.setScene(view1);
                }
            }
        }
    }*/
}
