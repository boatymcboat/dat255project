package view;

import eu.portcdm.messaging.PortCallMessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.MessageSender;
import model.PortCallOverview;

import static view.SizeAndGrid.*;

public class MainViewImpl implements MainView{
    private boolean view1_isCreated = false;
    private boolean view2_isCreated = false;
    private boolean view3_isCreated = false;
    private Scene defaultView;
    private Scene view1;
    private Scene view2;
    private Scene view3;
    private Stage mainStage;



    public MainViewImpl(Stage primaryStage){
        mainStage = primaryStage;
        initiate();
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
                        CreateView_1();
                        mainStage.setScene(view1);
                    }
                    else if (choices.getValue().toString().equals("option3")){
                        CreateView_1();
                        mainStage.setScene(view1);
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
        grid.add(sceneTitle, getBackButtonColumn(), getBackButtonRow()-2);
        final PortCallOverview portcalloverview = new PortCallOverview(0);
        portcalloverview.setup();
        HBox portcalls = new HBox();
        ComboBox availablePortcalls = Create_Drop_Down_Menu(new String[]{"0","1","2","3","4"});
        portcalls.getChildren().add(availablePortcalls);
        availablePortcalls.valueProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue observable, String oldValue, String newValue) {
                portcalloverview.changePortcall(newValue);
            }
        });
        HBox button = new HBox();
        Button message = new Button ("Send a sample message");
        message.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MessageSender sender = new MessageSender();
                PortCallMessage message = sender.createMessage();
                sender.sendMessage(message);
                portcalloverview.update();
            }
        });
        button.getChildren().add(message);
        grid.add(button,    getBackButtonColumn()+1,getBackButtonRow()+1);
        grid.add(portcalls, getBackButtonColumn(),getBackButtonRow()+1);
        grid.add(portcalloverview,getBackButtonColumn(), getBackButtonRow()-1);
        portcalloverview.update();
    }
    //Creates the drop-down menu
    public  ComboBox Create_Drop_Down_Menu(String[] optionsArray){
        ObservableList<String> choices = FXCollections.observableArrayList();
        choices.addAll(optionsArray);
        return new ComboBox(choices);
    }
}
