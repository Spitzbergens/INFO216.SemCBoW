package GUI;

import Models.Weather;
import Queries.WeatherQueries;
import RDF.RDFController;
import YrData.YrModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.apache.jena.rdf.model.Model;

import java.net.URL;
import java.util.*;

public class GUIMainController implements Initializable {

    @FXML
    private VBox boxValues1, boxValues2, boxValues3, boxValues4, boxValues5, iconbox1;

    @FXML
    private HBox dateId1, dateId2, dateId3, dateId4, tempCol1, tempCol2, tempCol3, tempCol4,
    conditionCol1, conditionCol2, conditionCol3, conditionCol4, windCol1, windCol2, windCol3, windCol4,
    windtypeCol1, windtypeCol2, windtypeCol3, windtypeCol4, timeId1, timeId2, timeId3, timeId4;

    @FXML
    private Text dateTextId, tempId;

    @FXML
    private ImageView imageId1, imageId2, imageId3, imageId4;





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
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(0).getDateTime());
        timeId1.getChildren().add(time);
        time.setFill(Color.rgb(58, 58, 58));

        Text temp = new Text("Temperatur: " + list.get(0).getTemperature() + " grader");
        tempCol1.getChildren().add(temp);
        temp.setFill(Color.rgb(58, 58, 58));


        Text condition = new Text("Værforhold: " + list.get(0).getWeatherType());
        conditionCol1.getChildren().add(condition);
        condition.setFill(Color.rgb(58, 58, 58));

        Text wind = new Text("Forventet vind: " + list.get(0).getWind());
        windtypeCol1.getChildren().add(wind);
        wind.setFill(Color.rgb(58, 58, 58));

        Text windspeed = new Text("Vindstyrke: " + list.get(0).getWindSpeed() + "ms");
        windCol1.getChildren().add(windspeed);
        windspeed.setFill(Color.rgb(58, 58, 58));

     setImageIcon(list, 0);

      //  Image image = null;
       // String conditions = list.get(0).getWeatherType();
       // if (list.get(0).getWeatherType().equals("skyet")){
         //   image = new Image("GUI/Icons/rain.png");
       // }if (conditions.equals("lett skyet")){
         //   image = new Image("GUI/Icons/lightcloud.png");
//        }if (conditions.equals())

    }

    public void setInfoToCol2(List<Weather> list){

        Text text = new Text(list.get(1).getDate());
        dateId2.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(1).getDateTime());
        timeId2.getChildren().add(time);
        time.setFill(Color.rgb(58, 58, 58));

        Text temp = new Text("Temperatur " + list.get(1).getTemperature() + " grader");
        tempCol2.getChildren().add(temp);
        temp.setFill(Color.rgb(58, 58, 58));

        Text condition = new Text("Værforhold: " + list.get(1).getWeatherType());
        conditionCol2.getChildren().add(condition);
        condition.setFill(Color.rgb(58, 58, 58));

        Text wind = new Text("Forventet vind: " + list.get(1).getWind());
        windtypeCol2.getChildren().add(wind);
        wind.setFill(Color.rgb(58, 58, 58));

        Text windspeed = new Text("Vindstyrke: " + list.get(1).getWindSpeed() + "ms");
        windCol2.getChildren().add(windspeed);
        windspeed.setFill(Color.rgb(58, 58, 58));

        setImageIcon(list,1);
    }

    public void setInfoToCol3(List<Weather> list){

        Text text = new Text(list.get(2).getDate());
        dateId3.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(2).getDateTime());
        timeId3.getChildren().add(time);
        time.setFill(Color.rgb(58, 58, 58));

        Text temp = new Text("Temperatur " + list.get(2).getTemperature() + " grader");
        tempCol3.getChildren().add(temp);
        temp.setFill(Color.rgb(58, 58, 58));

        Text condition = new Text("Værforhold: " + list.get(2).getWeatherType());
        conditionCol3.getChildren().add(condition);
        condition.setFill(Color.rgb(58, 58, 58));

        Text wind = new Text("Forventet vind: " + list.get(2).getWind());
        windtypeCol3.getChildren().add(wind);
        wind.setFill(Color.rgb(58, 58, 58));

        Text windspeed = new Text("Vindstyrke: " + list.get(2).getWindSpeed() + "ms");
        windCol3.getChildren().add(windspeed);
        windspeed.setFill(Color.rgb(58, 58, 58));

        setImageIcon(list, 2);


    }

    public void setInfoToCol4(List<Weather> list){

        Text text = new Text(list.get(3).getDate());
        dateId4.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(3).getDateTime());
        timeId4.getChildren().add(time);
        time.setFill(Color.rgb(58, 58, 58));

        Text temp = new Text("Temperatur " + list.get(3).getTemperature() + " grader");
        tempCol4.getChildren().add(temp);
        temp.setFill(Color.rgb(58, 58, 58));

        Text condition = new Text("Værforhold: " + list.get(3).getWeatherType());
        conditionCol4.getChildren().add(condition);
        condition.setFill(Color.rgb(58, 58, 58));

        Text wind = new Text("Forventet vind: " + list.get(3).getWind());
        windtypeCol4.getChildren().add(wind);
        wind.setFill(Color.rgb(58, 58, 58));

        Text windspeed = new Text("Vindstyrke: " + list.get(3).getWindSpeed() + "ms");
        windCol4.getChildren().add(windspeed);
        windspeed.setFill(Color.rgb(58, 58, 58));

        setImageIcon(list, 3);
    }

    public Image setImage(String url){
        Image image = new Image(url);
        return image;
    }

    private void setImageIcon(List<Weather> list, int index){

        Image image = null;

        if (list.get(index).getWeatherType().equals("Skyet")) {
         image = setImage("GUI/Icons/rain.png");
        }
        else if (list.get(index).getWeatherType().equals("Lettskyet")) {
           image =  setImage("GUI/Icons/lightcloud.png");
        }
        else if (list.get(index).getWeatherType().equals("Klarvær")) {
           image = setImage("GUI/Icons/Clear");
        }
        else if (list.get(index).getWeatherType().equals("Delvis skyet")) {
           image = setImage("GUI/Icons/partcloud.png");
        }

            switch (index) {
                case 0:
                    imageId1.setImage(image);
                case 1:
                    imageId2.setImage(image);
                case 2:
                    imageId3.setImage(image);
                case 3:
                    imageId4.setImage(image);
            }
        }
    }





