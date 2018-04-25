package GUI;

import Models.Weather;
import Queries.WeatherQueries;
import RDF.RDFController;
import YrData.YrModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.apache.jena.rdf.model.Model;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GUIMainController implements Initializable {

    @FXML
    private VBox boxValues1, boxValues2, boxValues3, boxValues4, boxValues5;

    @FXML
    private HBox dateId1, dateId2, dateId3, dateId4, tempCol1, tempCol2, tempCol3, tempCol4,
    conditionCol1, conditionCol2, conditionCol3, conditionCol4, windCol1, windCol2, windCol3, windCol4,
    windtypeCol1, windtypeCol2, windtypeCol3, windtypeCol4, timeId1, timeId2, timeId3, timeId4;

    @FXML
    private Text dateTextId, tempId;





    private YrModel model = new YrModel();
    private RDFController controller = new RDFController();
    private WeatherQueries queries = new WeatherQueries(controller);





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.createAndParseModel();
        model.writeToFile();



        Model weatherModel = model.createAndParseModel();
        controller.addModel(weatherModel);
        displayElements(queries.getWeatherListWeek());

    }

    public void displayElements(List<Weather> list){
        setInfoToCol1(list);
        setInfoToCol2(list);
        setInfoToCol3(list);
        setInfoToCol4(list);
    }

    public void setInfoToCol1(List<Weather> list){

        Text text = new Text(list.get(0).getDate());
        dateId1.getChildren().add(text);
        text.setFill(Color.WHITE);

        Text time = new Text("Fra kl. " + list.get(0).getDateTime());
        timeId1.getChildren().add(time);
        time.setFill(Color.WHITE);

        Text temp = new Text("Temperatur: " + list.get(0).getTemperature() + " grader");
        tempCol1.getChildren().add(temp);
        temp.setFill(Color.WHITE);


        Text condition = new Text("Værforhold: " + list.get(0).getWeatherType());
        conditionCol1.getChildren().add(condition);
        condition.setFill(Color.WHITE);

        Text wind = new Text("Forventet vind: " + list.get(0).getWind());
        windtypeCol1.getChildren().add(wind);
        wind.setFill(Color.WHITE);

        Text windspeed = new Text("Vindstyrke: " + list.get(0).getWindSpeed() + "ms");
        windCol1.getChildren().add(windspeed);
        windspeed.setFill(Color.WHITE);


    }

    public void setInfoToCol2(List<Weather> list){

        Text text = new Text(list.get(1).getDate());
        dateId2.getChildren().add(text);
        text.setFill(Color.WHITE);

        Text time = new Text("Fra kl. " + list.get(2).getDateTime());
        timeId2.getChildren().add(time);
        time.setFill(Color.WHITE);

        Text temp = new Text("Temperatur " + list.get(1).getTemperature() + " grader");
        tempCol2.getChildren().add(temp);
        temp.setFill(Color.WHITE);

        Text condition = new Text("Værforhold: " + list.get(1).getWeatherType());
        conditionCol2.getChildren().add(condition);
        condition.setFill(Color.WHITE);

        Text wind = new Text("Forventet vind: " + list.get(1).getWind());
        windtypeCol2.getChildren().add(wind);
        wind.setFill(Color.WHITE);

        Text windspeed = new Text("Vindstyrke: " + list.get(1).getWindSpeed() + "ms");
        windCol2.getChildren().add(windspeed);
        windspeed.setFill(Color.WHITE);

    }

    public void setInfoToCol3(List<Weather> list){

        Text text = new Text(list.get(2).getDate());
        dateId3.getChildren().add(text);
        text.setFill(Color.WHITE);

        Text time = new Text("Fra kl. " + list.get(2).getDateTime());
        timeId3.getChildren().add(time);
        time.setFill(Color.WHITE);

        Text temp = new Text("Temperatur " + list.get(2).getTemperature() + " grader");
        tempCol3.getChildren().add(temp);
        temp.setFill(Color.WHITE);

        Text condition = new Text("Værforhold: " + list.get(2).getWeatherType());
        conditionCol3.getChildren().add(condition);
        condition.setFill(Color.WHITE);

        Text wind = new Text("Forventet vind: " + list.get(2).getWind());
        windtypeCol3.getChildren().add(wind);
        wind.setFill(Color.WHITE);

        Text windspeed = new Text("Vindstyrke: " + list.get(2).getWindSpeed() + "ms");
        windCol3.getChildren().add(windspeed);
        windspeed.setFill(Color.WHITE);



    }

    public void setInfoToCol4(List<Weather> list){

        Text text = new Text(list.get(3).getDate());
        dateId4.getChildren().add(text);
        text.setFill(Color.WHITE);

        Text time = new Text("Fra kl. " + list.get(3).getDateTime());
        timeId4.getChildren().add(time);
        time.setFill(Color.WHITE);

        Text temp = new Text("Temperatur " + list.get(3).getTemperature() + " grader");
        tempCol4.getChildren().add(temp);
        temp.setFill(Color.WHITE);

        Text condition = new Text("Værforhold: " + list.get(3).getWeatherType());
        conditionCol4.getChildren().add(condition);
        condition.setFill(Color.WHITE);

        Text wind = new Text("Forventet vind: " + list.get(3).getWind());
        windtypeCol4.getChildren().add(wind);
        wind.setFill(Color.WHITE);

        Text windspeed = new Text("Vindstyrke: " + list.get(3).getWindSpeed() + "ms");
        windCol4.getChildren().add(windspeed);
        windspeed.setFill(Color.WHITE);

    }




}
