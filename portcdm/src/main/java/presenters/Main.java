package presenters;

import javafx.application.Application;
import javafx.stage.Stage;
import views.ExampleView;

public class Main extends Application {

    public static void main(String[] args) {

        // För att skriva ut senaste Statement för Arrival_Vessel_PilotBA (hårdkodad vilken portcall det är)
        /*PortCallManager manager = new PortCallManager();
        PortCall call = manager.getActiveCall();
        System.out.println(call.getVessel());*/
        /*StatementReader sreader = new StatementReader(call);
        System.out.println(sreader.getStatement("Arrival_Vessel_PilotBA")); */

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        new ExampleView(primaryStage);

    }
}
