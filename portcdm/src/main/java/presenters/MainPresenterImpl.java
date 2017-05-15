package presenters;

import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainView;

public class MainPresenterImpl implements MainPresenter{

    private MainView mainView;



    public MainPresenterImpl(){
    }

    public void setView(MainView view) {
        this.mainView = view;
    }

    public void openShipAgentView(int view_id) {
        mainView.setShipAgentView(view_id);
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
