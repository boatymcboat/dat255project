package views;

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
import presenters.ShipAgentPresenter;

import java.util.EventListener;

import static views.SizeAndGrid.*;

public class MainViewImpl implements MainView{
    private boolean view1_isCreated = false;
    private boolean view2_isCreated = false;
    private boolean view3_isCreated = false;
    private Scene defaultView;
    private Scene view1;
    private Scene view2;
    private Scene view3;
    private Stage mainStage;
    private Button startAgentButton;
    private Button backButton;

    public MainViewImpl(Stage primaryStage){
        mainStage = primaryStage;
        initialize();
    }

    public void initialize(){
        mainStage.show();
        setTitle("Professional Ship Agent Software 2018");
        CreateDefaultView();
        mainStage.setScene(defaultView);
        mainStage.setResizable(true);
    }

    public void goBack() {
        mainStage.setScene(defaultView);
    }

    //Creates the views visible to user upon starting the application
    private void CreateDefaultView(){
        GridPane grid = new GridPane();
        startAgentButton = new Button("Start Agent Application");
        backButton = new Button("Back");

        final Text sceneTitle = new Text("Welcome to the portCDM agent application");
        sceneTitle.setFont(Font.font(26));

        final ComboBox choices = Create_Drop_Down_Menu(new String []{"option1", "option2", "option3"});
        HBox choiceBox = new HBox(10);
        choiceBox.setAlignment(Pos.BOTTOM_CENTER);
        choiceBox.getChildren().add(choices);
        HBox hBoxButton =  new HBox (10);
        hBoxButton.setAlignment(Pos.BOTTOM_CENTER);
        hBoxButton.getChildren().add(startAgentButton);
        grid.add(choiceBox, getChoiceBoxColumn(), getChoiceBoxRow());
        grid.add(sceneTitle, getSceneTitleColumn(), getSceneTitleRow());
        grid.add(hBoxButton, gethBoxButtonColumn(), gethBoxButtonRow());


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


    public void setListener(EventHandler<ActionEvent> listener) {
        backButton.setOnAction(listener);
        startAgentButton.setOnAction(listener);
    }

    public void setTitle(String title) {
        mainStage.setTitle(title);

    }

    //Creates one of the views used by the application
    public void setShipAgentView(int view_id,  EventHandler<ActionEvent> shipAgentPresenter) {
        if (view_id == 1){

            GridPane grid = new GridPane();
            view1 = CreateEmptyView(grid);
            grid.add(Back_Button(), getBackButtonColumn(),getBackButtonRow());
            ShipAgentView shipAgentView = new ShipAgentView1(grid);
            shipAgentView.setListener(shipAgentPresenter);

            mainStage.setScene(view1);
        }
        else if (view_id == 2){
            mainStage.setScene(view1);
        }
        else if (view_id == 3){
            mainStage.setScene(view1);
        }
    }

    //Creates a back button to be used to bring the user back to the default views
    public HBox Back_Button(){
        HBox button = new HBox(10);
        button.getChildren().add(backButton);
        button.setAlignment(Pos.BOTTOM_LEFT);
        return button;
    }



    //Creates the drop-down menu
    public  ComboBox Create_Drop_Down_Menu(String[] optionsArray){
        ObservableList<String> choices = FXCollections.observableArrayList();
        choices.addAll(optionsArray);
        return new ComboBox(choices);
    }
}
