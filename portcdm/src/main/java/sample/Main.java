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

import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AppController controller = new AppController(primaryStage);
        controller.initiate();

    }


    public static void main(String[] args) {

        // För att skicka ett meddelande: (Fungerar ish)
        // sendMessage();

        // För att hämta 30 meddelanden:
        // readMessages();

        // För att skriva ut senaste Statement för Arrival_Vessel_PilotBA (hårdkodad vilken portcall det är)
        /* PortCallManager manager = new PortCallManager();
        PortCall call = manager.getActiveCall();
        StatementReader sreader = new StatementReader(call);
        System.out.println(sreader.getStatement("Arrival_Vessel_PilotBA")); */

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


}
