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
import javafx.scene.control.ScrollPane;

import static sample.SizeAndGrid.*;

public class AppLayout {
    private static boolean view1_isCreated = false;
    private static boolean view2_isCreated = false;
    private static boolean view3_isCreated = false;
    private static Scene defaultView;
    private static Scene view1;
    private static Scene view2;
    private static Scene view3;
    private static Stage mainStage;

    public static void Setup_App(Stage primaryStage){
        mainStage = primaryStage;
        mainStage.setTitle("Professional Ship Agent Software 2018");

        SetupView.Setup_View();
        GridPane grid = SetupView.getGrid();

        final Text sceneTitle = new Text("Welcome to the portCDM agent application");
        sceneTitle.setFont(Font.font(26));

        Button btn = new Button("Start Agent Application");

        final ComboBox choices = Create_Drop_Down_Menu(new String []{"option1", "option2", "option3"});
        HBox choiceBox = new HBox(10);
        choiceBox.setAlignment(Pos.BOTTOM_CENTER);
        choiceBox.getChildren().add(choices);
        HBox hBoxButton =  new HBox (10);
        hBoxButton.setAlignment(Pos.BOTTOM_CENTER);
        hBoxButton.getChildren().add(btn);

        grid.add(choiceBox, getChoiceBoxColumn(), getChoiceBoxRow());
        grid.add(sceneTitle, getSceneTitleColumn(), getSceneTitleRow());
        grid.add(hBoxButton, gethBoxButtonColumn(), gethBoxButtonRow());

        defaultView = SetupView.getScene();

        mainStage.setScene(defaultView);
        mainStage.setResizable(true);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(choices.getValue() != null && !choices.getValue().toString().isEmpty()){
                    if (choices.getValue().toString().equals("option1")){
                        if(!view1_isCreated){
                            view1 = View_1.Create_View1();
                            view1_isCreated = true;
                        }
                        mainStage.setScene(view1);

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
        mainStage.show();

    }


    public static HBox Back_Button(){
        HBox button = new HBox(10);
        Button btn = new Button("Back");
        button.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                mainStage.setScene(defaultView);
                mainStage.show();
            }
        });
        return button;
    }

    public static ComboBox Create_Drop_Down_Menu(String[] optionsArray){
        ObservableList<String> choices = FXCollections.observableArrayList();
        choices.addAll(optionsArray);
        return new ComboBox(choices);
    }
}
