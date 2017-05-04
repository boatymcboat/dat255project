package sample;

import eu.portcdm.dto.*;
import eu.portcdm.messaging.PortCallMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AppController controller = new AppController(primaryStage);
        controller.initiate();

    }


    public static void main(String[] args) {

        // För att skicka ett meddelande: (Fungerar ej)
        // sendMessage();

        // För att hämta 100 meddelanden:
        // readMessages();

        // För att skriva ut senaste Statement för Arrival_Vessel_PilotBA (hårdkodad vilken portcall det är)
        // System.out.println(getLatestEstimatePilotBA());

        launch(args);

    }

    public static void sendMessage(){
        MessageSender sender = new MessageSender();
        PortCallMessage message = sender.createMessage();
        sender.sendMessage(message);
    }

    public static void readMessages(){
        MessageReader reader = new MessageReader();
        List<PortCallMessage> messages = reader.getMessages();
        for (PortCallMessage message : messages){
            System.out.println(message.getComment());
        }
    }

    public static Statement getLatestEstimatePilotBA(){
        PortCallManager manager = new PortCallManager();
        PortCall call = manager.getActiveCall();
        List<ProcessStep> steps = call.getProcessSteps();
        for (ProcessStep step : steps) {
            //System.out.println("Step: " + step.getDefinitionName());
            List<SubProcess> substeps = step.getSubProcesses();
            for (SubProcess substep : substeps){
                //System.out.println("\tSubstep: " + substep.getDefinitionName());
                List<Event> events = substep.getEvents();
                for (Event event : events){
                    //System.out.println("\t\tEvent: " + event.getDefinitionName());
                    List<State> states = event.getStates();
                    for (State state : states) {
                        //System.out.println("\t\t\tState: " + state.getDefinitionName() + ", id: " + state.getStateDefinitionId());
                        if (state.getStateDefinitionId().equals("Arrival_Vessel_PilotBA")){
                            List<Statement> statements = state.getStatements();
                            Statement statement = statements.get(statements.size()-1);
                            return statement;
                        }

                    }
                }
            }
        }
        return null;
    }
}
