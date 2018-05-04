package GUI;

import Models.*;
import Clothing.ClothingModel;
import Queries.ClothingQueries;
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
    windtypeCol1, windtypeCol2, windtypeCol3, windtypeCol4, timeId1, timeId2, timeId3, timeId4, precipId1, precipId2,
    precipId3, precipId4, mensRec1, womensRec1, mensRec2, womensRec2, mensRec3, womensRec3, mensRec4, womensRec4,
            accRec1, accRec2, accRec3, accRec4;

    @FXML
    private Text dateTextId, tempId;

    @FXML
    private ImageView imageId1, imageId2, imageId3, imageId4;





    private YrModel yrModel = new YrModel();
    private ClothingModel clothingModel = new ClothingModel();
    private RDFController controller = new RDFController();
    private WeatherQueries weatherQueries = new WeatherQueries(controller);
    private ClothingQueries clothingQueries = new ClothingQueries(controller);
    private ResourceBundle labels = ResourceBundle.getBundle("langProp", Locale.forLanguageTag("no"));




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yrModel.createAndParseModel();
        yrModel.writeToFile();



        Model cloModel = clothingModel.readModel();
        Model weatherModel = yrModel.createAndParseModel();
        controller.addModel(weatherModel);
        controller.addModel(cloModel);
        displayElements(weatherQueries.getWeatherListWeek());


    }

    public void displayElements(List<Weather> list){
        setInfoToCol1(list);
        setInfoToCol2(list);
        setInfoToCol3(list);
        setInfoToCol4(list);
    }

    @SuppressWarnings("Duplicates")
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

        Text precipitation = new Text("Nedbør: " + list.get(0).getPrecipitation() + "mm");
        precipId1.getChildren().add(precipitation);
        precipitation.setFill(Color.rgb(58,58,58));

     setImageIcon(list, 0);

        MensClothing clothing = setMensClothingRecommendation(list, 0);
        WomensClothing womensClothing = setWomensClothingRecommendation(list, 0);
        Accessories accessories = setAccessoriesRecommendation(list, 0);

        Text mensClothingText = null;
        Text womensClothingText = null;

        if (clothing.getGarment().equals(womensClothing.getGarment())) {
            mensClothingText = new Text("Forslag: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
        }else {
            mensClothingText = new Text("Forslag for menn: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
            womensClothingText = new Text("Forslag for kvinner: " + labels.getString(womensClothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(womensClothing.getShoe()).toLowerCase());
            womensRec1.getChildren().add(womensClothingText);
        }

        Text accessoriesText = new Text("Kan være lurt å ta med " + labels.getString(accessories.getAccessory()).toLowerCase());

        mensRec1.getChildren().add(mensClothingText);
        accRec1.getChildren().add(accessoriesText);


    }

    @SuppressWarnings("Duplicates")
    public void setInfoToCol2(List<Weather> list){

        Text text = new Text(list.get(1).getDate());
        dateId2.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(1).getDateTime());
        timeId2.getChildren().add(time);
        time.setFill(Color.rgb(58, 58, 58));

        Text temp = new Text("Temperatur: " + list.get(1).getTemperature() + " grader");
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

        Text precipitation = new Text("Nedbør: " + list.get(1).getPrecipitation() + "mm");
        precipId2.getChildren().add(precipitation);
        precipitation.setFill(Color.rgb(58,58,58));

        setImageIcon(list,1);
        MensClothing clothing = setMensClothingRecommendation(list, 1);
        WomensClothing womensClothing = setWomensClothingRecommendation(list, 1);
        Accessories accessories = setAccessoriesRecommendation(list, 1);

        Text mensClothingText = null;
        Text womensClothingText = null;

        if (clothing.getGarment().equals(womensClothing.getGarment())) {
            mensClothingText = new Text("Forslag: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
        }else {
            mensClothingText = new Text("Forslag for menn: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
            womensClothingText = new Text("Forslag for kvinner: " + labels.getString(womensClothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(womensClothing.getShoe()).toLowerCase());
            womensRec2.getChildren().add(womensClothingText);
        }

        Text accessoriesText = new Text("Kan være lurt å ta med " + labels.getString(accessories.getAccessory()).toLowerCase());

        mensRec2.getChildren().add(mensClothingText);
        accRec2.getChildren().add(accessoriesText);
    }

    @SuppressWarnings("Duplicates")
    public void setInfoToCol3(List<Weather> list){

        Text text = new Text(list.get(2).getDate());
        dateId3.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(2).getDateTime());
        timeId3.getChildren().add(time);
        time.setFill(Color.rgb(58, 58, 58));

        Text temp = new Text("Temperatur: " + list.get(2).getTemperature() + " grader");
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

        Text precipitation = new Text("Nedbør: " + list.get(2).getPrecipitation() + "mm");
        precipId3.getChildren().add(precipitation);
        precipitation.setFill(Color.rgb(58,58,58));

        setImageIcon(list, 2);

        MensClothing clothing = setMensClothingRecommendation(list, 2);
        WomensClothing womensClothing = setWomensClothingRecommendation(list, 2);
        Accessories accessories = setAccessoriesRecommendation(list, 2);

        Text mensClothingText = null;
        Text womensClothingText = null;

        if (clothing.getGarment().equals(womensClothing.getGarment())) {
             mensClothingText = new Text("Forslag: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                 "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
        }else {
             mensClothingText = new Text("Forslag for menn: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
             womensClothingText = new Text("Forslag for kvinner: " + labels.getString(womensClothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(womensClothing.getShoe()).toLowerCase());
            womensRec3.getChildren().add(womensClothingText);
        }

        Text accessoriesText = new Text("Kan være lurt å ta med " + labels.getString(accessories.getAccessory()).toLowerCase());

        mensRec3.getChildren().add(mensClothingText);
        accRec3.getChildren().add(accessoriesText);

    }

    public String getSeasons(List<Weather> wList, int index){

        String seasons = null;
        if (wList.get(index).getDate().substring(5,7).equals("01") || wList.get(index).getDate().substring(5,7).equals("02") || wList.get(index).getDate().substring(5,7).equals("12")){
            seasons = "Winter";
        }else if( wList.get(index).getDate().substring(5,7).equals("04") || wList.get(index).getDate().substring(5,7).equals("05") || wList.get(index).getDate().substring(5,7).equals("03")){
            seasons = "Spring";
        }else if(wList.get(index).getDate().substring(5,7).equals("06") || wList.get(index).getDate().substring(5,7).equals("07") || wList.get(index).getDate().substring(5,7).equals("08")){
            seasons = "Summer";
        }else if(wList.get(index).getDate().substring(5,7).equals("09") || wList.get(index).getDate().substring(5,7).equals("10") || wList.get(index).getDate().substring(5,7).equals("11")){
            seasons = "Autumn";
        }
        return seasons;

    }

    public MensClothing setMensClothingRecommendation(List<Weather> wList, int index){
        MensClothing mensClothing = null;

        if (wList.get(index).getWeatherType().equals("Skyet") || wList.get(index).getWeatherType().equals("Lettskyet")){
            mensClothing = clothingQueries.mensToObject("Cloudy", getSeasons(wList, index));
        }else if(wList.get(index).getWeatherType().equals("Klarvær") || wList.get(index).getWeatherType().equals("Delvis skyet")){
            mensClothing = clothingQueries.mensToObject("Clear", getSeasons(wList, index));
        }else if(wList.get(index).getWeatherType().equals("Regn") || wList.get(index).getWeatherType().equals("Regnbyger") || wList.get(index).getWeatherType().equals("Kraftig regn")){
            mensClothing = clothingQueries.mensToObject("Wet", getSeasons(wList, index));
        }else if(wList.get(index).getTemperature() <= 8){
            mensClothing = clothingQueries.mensToObject("Cold" , getSeasons(wList, index));
        }else if (wList.get(index).getTemperature() >= 15){
            mensClothing = clothingQueries.mensToObject("Hot" , getSeasons(wList, index));
        }
        return mensClothing;
    }
     public WomensClothing setWomensClothingRecommendation(List<Weather> wList, int index){
        WomensClothing womensClothing = null;

         if (wList.get(index).getWeatherType().equals("Skyet") || wList.get(index).getWeatherType().equals("Lettskyet") || wList.get(index).getWeatherType().equals("Delvis skyet")){
             womensClothing = clothingQueries.womensToObject("Cloudy", getSeasons(wList, index));
         }else if(wList.get(index).getWeatherType().equals("Klarvær")){
             womensClothing = clothingQueries.womensToObject("Clear", getSeasons(wList, index));
         }else if(wList.get(index).getWeatherType().equals("Regn") || wList.get(index).getWeatherType().equals("Regnbyger") || wList.get(index).getWeatherType().equals("Kraftig regn") || wList.get(index).getWeatherType().equals("Lett regn")){
             womensClothing = clothingQueries.womensToObject("Wet", getSeasons(wList, index));
         }else if(wList.get(index).getTemperature() <= 8){
             womensClothing = clothingQueries.womensToObject("Cold" , getSeasons(wList, index));
         }else if (wList.get(index).getTemperature() >= 15){
             womensClothing = clothingQueries.womensToObject("Hot" , getSeasons(wList, index));
         }
         return womensClothing;
     }

     public Accessories setAccessoriesRecommendation(List<Weather> wList, int index){
        Accessories accessories = null;
         if (wList.get(index).getWeatherType().equals("Skyet") || wList.get(index).getWeatherType().equals("Lettskyet") || wList.get(index).getWeatherType().equals("Delvis skyet")){
             accessories = clothingQueries.accessoriesToObject("Cloudy", getSeasons(wList, index));
         }else if(wList.get(index).getWeatherType().equals("Klarvær")){
             accessories = clothingQueries.accessoriesToObject("Clear", getSeasons(wList, index));
         }else if(wList.get(index).getWeatherType().equals("Regn") || wList.get(index).getWeatherType().equals("Regnbyger") || wList.get(index).getWeatherType().equals("Kraftig regn") || wList.get(index).getWeatherType().equals("Lett regn")){
             accessories = clothingQueries.accessoriesToObject("Wet", getSeasons(wList, index));
         }else if(wList.get(index).getTemperature() <= 8){
             accessories = clothingQueries.accessoriesToObject("Cold" , getSeasons(wList, index));
         }else if (wList.get(index).getTemperature() >= 15){
             accessories = clothingQueries.accessoriesToObject("Hot" , getSeasons(wList, index));
         }
         return accessories;

     }

    public Clothing setClothingRecommendation(List<Weather> wList,  int index){
        Clothing clothing = null;

            if (wList.get(index).getWeatherType().equals("Skyet") || wList.get(index).getWeatherType().equals("Lettskyet")){
                clothing = clothingQueries.queryToObject("Cloudy");
            }else if(wList.get(index).getWeatherType().equals("Klarvær")){
                clothing = clothingQueries.queryToObject("Clear");
            }else if(wList.get(index).getWeatherType().equals("Regn") || wList.get(index).getWeatherType().equals("Regnbyger") || wList.get(index).getWeatherType().equals("Lett regn")){
                clothing = clothingQueries.queryToObject("Wet");
            }else if(wList.get(index).getWeatherType().equals("Delvis skyet")){
                clothing = clothingQueries.queryToObject("Dry");
            }else if(wList.get(index).getTemperature() <= 10){
                clothing = clothingQueries.queryToObject("Cold");
            }else if(wList.get(index).getTemperature() >= 10){
                clothing = clothingQueries.queryToObject("Hot");
            }
            return clothing;

    }
@SuppressWarnings("Duplicates")
    public void setInfoToCol4(List<Weather> list){

        Text text = new Text(list.get(3).getDate());
        dateId4.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(3).getDateTime());
        timeId4.getChildren().add(time);
        time.setFill(Color.rgb(58, 58, 58));

        Text temp = new Text("Temperatur: " + list.get(3).getTemperature() + " grader");
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

        Text precipitation = new Text("Nedbør: " + list.get(3).getPrecipitation() + "mm");
        precipId4.getChildren().add(precipitation);
        precipitation.setFill(Color.rgb(58,58,58));

        setImageIcon(list, 3);
        MensClothing clothing = setMensClothingRecommendation(list, 3);
        WomensClothing womensClothing = setWomensClothingRecommendation(list, 3);
        Accessories accessories = setAccessoriesRecommendation(list, 3);

        Text mensClothingText = null;
        Text womensClothingText = null;

        if (clothing.getGarment().equals(womensClothing.getGarment())) {
            mensClothingText = new Text("Forslag: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
        }else {
            mensClothingText = new Text("Forslag for menn: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
            womensClothingText = new Text("Forslag for kvinner: " + labels.getString(womensClothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(womensClothing.getShoe()).toLowerCase());
            womensRec4.getChildren().add(womensClothingText);
        }

        Text accessoriesText = new Text("Kan være lurt å ta med " + labels.getString(accessories.getAccessory()).toLowerCase());

        mensRec4.getChildren().add(mensClothingText);
        accRec4.getChildren().add(accessoriesText);
    }

    public Image setImage(String url){
        Image image = new Image(url);
        return image;
    }

    private void setImageIcon(List<Weather> list, int index){

        Image image = null;

        if (list.get(index).getWeatherType().equals("Skyet")) {
         image = setImage("GUI/Icons/cloudy.png");
        }
        else if (list.get(index).getWeatherType().equals("Lettskyet")) {
           image =  setImage("GUI/Icons/lightcloud.png");
        }
        else if (list.get(index).getWeatherType().equals("Klarvær")) {
           image = setImage("GUI/Icons/clear.png");
        }
        else if (list.get(index).getWeatherType().equals("Delvis skyet")) {
           image = setImage("GUI/Icons/partcloud.png");
        }
        else if (list.get(index).getWeatherType().equals("Regn") || list.get(index).getWeatherType().equals("Regnbyger")
                || list.get(index).getWeatherType().equals("Kraftig regn") || list.get(index).getWeatherType().equals("Lett regn")){
            image = setImage("GUI/Icons/rain.png");
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





