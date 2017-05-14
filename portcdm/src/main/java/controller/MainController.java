package controller;

import eu.portcdm.messaging.PortCallMessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.lang.*;

import javafx.scene.control.ScrollPane;
import model.MessageSender;
import model.PortCallOverview;

import static view.SizeAndGrid.*;

public class MainController {
    private boolean view1_isCreated = false;
    private boolean view2_isCreated = false;
    private boolean view3_isCreated = false;
    private Scene defaultView;
    private Scene view1;
    private Scene view2;
    private Scene view3;
    private Stage mainStage;



    public MainController(Stage primaryStage){

    }
}
