package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import java.lang.*;
import java.awt.event.*;
public class AppLayout {

    public static void Setup_App(Stage primaryStage){
        primaryStage.setTitle("Professional Ship Agent Software 2018");


        final Text sceneTitle = new Text("Welcome to the portCDM agent application");
        sceneTitle.setFont(Font.font(26));

        Button btn = new Button("Start Agent Application");

        final ComboBox choices = Create_Drop_Down_Menu(new String []{"option1", "option2", "option3"});
        HBox choiceBox = new HBox(10);
        choiceBox.setAlignment(Pos.BOTTOM_CENTER);
        choiceBox.getChildren().add(choices);
        HBox button =  new HBox (10);
        button.setAlignment(Pos.BOTTOM_CENTER);
        button.getChildren().add(btn);
        GridPane grid = Setup_Grid();
        Scene scene = new Scene(grid, 800, 500);
        grid.add(choiceBox, 0,5);
        grid.add(sceneTitle, 0, 0);
        grid.add(button,0,15);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(choices.getValue() != null && !choices.getValue().toString().isEmpty()){
                    if (choices.getValue().toString().equals("option1")){
                        sceneTitle.setText("You have chosen option1");
                    }
                    else if (choices.getValue().toString().equals("option2")){
                        sceneTitle.setText("You have chosen option2");
                    }
                    else if (choices.getValue().toString().equals("option3")){
                        sceneTitle.setText("You have chosen option3");
                    }
                }
            }
        });
        primaryStage.show();

    }

    public static GridPane Setup_Grid (){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }

    public static ComboBox Create_Drop_Down_Menu(String[] optionsArray){
        ObservableList<String> choices = FXCollections.observableArrayList();
        choices.addAll(optionsArray);
        return new ComboBox(choices);
    }
}
