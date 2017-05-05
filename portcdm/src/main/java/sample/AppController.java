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

public class AppController {
    private boolean view1_isCreated = false;
    private boolean view2_isCreated = false;
    private boolean view3_isCreated = false;
    private Scene defaultView;
    private Scene view1;
    private Scene view2;
    private Scene view3;
    private Stage mainStage;



    public AppController(Stage primaryStage){
        mainStage = primaryStage;
        mainStage.setTitle("Professional Ship Agent Software 2018");
        CreateDefaultView();
        mainStage.setScene(defaultView);
        mainStage.setResizable(true);
    }
    //Creates the view visible to user upon starting the application
    private void CreateDefaultView(){
        GridPane grid = new GridPane();
        Button btn = new Button("Start Agent Application");

        final Text sceneTitle = new Text("Welcome to the portCDM agent application");
        sceneTitle.setFont(Font.font(26));

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
        //Logic for connecting the button to the choice made in the drop-down menu.
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(choices.getValue() != null && !choices.getValue().toString().isEmpty()){
                    if (choices.getValue().toString().equals("option1")){
                        if(!view1_isCreated){
                            CreateView_1();
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

        defaultView = CreateEmptyView(grid);

    }
    //Creates the GridPane/Scrollpane combination that is the basis for all views in the application
    private Scene CreateEmptyView(GridPane grid){
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(grid);
        return new Scene(scrollPane,1200,768);
    }
    //Shows the application in the window
    public void initiate(){
        mainStage.show();
    }

    //Creates a back button to be used to bring the user back to the default view
    public HBox Back_Button(){
        HBox button = new HBox(10);
        Button btn = new Button("Back");
        button.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                mainStage.setScene(defaultView);
            }
        });
        button.setAlignment(Pos.BOTTOM_LEFT);
        return button;
    }
    //Creates one of the views used by the application
    private void CreateView_1(){
        GridPane grid = new GridPane();
        view1 = CreateEmptyView(grid);
        grid.add(Back_Button(), getBackButtonColumn(),getBackButtonRow());
        final Text sceneTitle = new Text("Welcome to view 1");
        sceneTitle.setFont(Font.font(26));
        HBox text = new HBox(10);
        text.getChildren().add(sceneTitle);
        grid.add(sceneTitle, SizeAndGrid.getSceneTitleColumn(), SizeAndGrid.getSceneTitleRow());
        PortCallOverview portcalloverview = new PortCallOverview(150);
        portcalloverview.setup();

        grid.add(portcalloverview,20, 40);
        portcalloverview.update();
    }
    //Creates the drop-down menu
    public  ComboBox Create_Drop_Down_Menu(String[] optionsArray){
        ObservableList<String> choices = FXCollections.observableArrayList();
        choices.addAll(optionsArray);
        return new ComboBox(choices);
    }
}
