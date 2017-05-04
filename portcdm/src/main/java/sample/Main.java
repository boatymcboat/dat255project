package sample;

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
        AppLayout.Setup_App(primaryStage);

    }


    public static void main(String[] args) {

        // För att skicka ett meddelande: (Fungerar ej)
        // sendMessage();

        // För att hämta 100 meddelanden:
        // readMessages();

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
