package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


import javafx.geometry.Rectangle2D;

public class UIMain extends Application {

    Text welcomeText = new Text();
    GridPane root = new GridPane();




    @Override
    public void start(Stage primaryStage) {

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();


        root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setVgap(10);
        root.setHgap(10);

        welcomeText.setText("SemCBOW");
        welcomeText.setFill(Color.WHITE);
        root.add(welcomeText, 0, 0);
        setID();
        Scene mainScene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());
        mainScene.getStylesheets().addAll(this.getClass().getResource("Styles.css").toExternalForm());

        primaryStage.setTitle("SemCBOW");
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    public void setID(){

        welcomeText.setId("welcomeText");
        root.setId("root");


    }

}
