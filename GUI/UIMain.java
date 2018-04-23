package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


import javafx.geometry.Rectangle2D;

import java.io.IOException;

public class UIMain extends Application {

    @FXML
    private Parent parent;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("GUIMainFXML.fxml"));
        primaryStage.setTitle("Salgssystem");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



    }
    }


