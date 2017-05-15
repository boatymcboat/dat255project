package presenters;

import eu.portcdm.messaging.PortCallMessage;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MessageReader;
import model.MessageSender;
import views.MainView;
import views.MainViewImpl;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        MainView mainView = new MainViewImpl(primaryStage);
        MainPresenter presenter = new MainPresenterImpl();
        mainView.setPresenter(presenter);
        presenter.setView(mainView);

    }


    public static void main(String[] args) {

        // För att skriva ut senaste Statement för Arrival_Vessel_PilotBA (hårdkodad vilken portcall det är)
        /*PortCallManager manager = new PortCallManager();
        PortCall call = manager.getActiveCall();
        System.out.println(call.getVessel());*/
        /*StatementReader sreader = new StatementReader(call);
        System.out.println(sreader.getStatement("Arrival_Vessel_PilotBA")); */

        launch(args);

    }
}
