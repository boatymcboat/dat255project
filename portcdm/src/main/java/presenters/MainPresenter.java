package presenters;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPresenter {
    private boolean view1_isCreated = false;
    private boolean view2_isCreated = false;
    private boolean view3_isCreated = false;
    private Scene defaultView;
    private Scene view1;
    private Scene view2;
    private Scene view3;
    private Stage mainStage;



    public MainPresenter(Stage primaryStage){

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
