package GUI;

import ClothindData.ClothingModel;
import ClothingRecommender.ClothingRec;
import Models.*;
import Queries.WeatherQueries;
import RDF.RDFController;
import YrData.YrModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.apache.jena.rdf.model.Model;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GUIMainController implements Initializable {


    @FXML
    private HBox dateId1, dateId2, dateId3, dateId4, tempCol1, tempCol2, tempCol3, tempCol4,
            conditionCol1, conditionCol2, conditionCol3, conditionCol4, windCol1, windCol2, windCol3, windCol4,
            windtypeCol1, windtypeCol2, windtypeCol3, windtypeCol4, timeId1, timeId2, timeId3, timeId4, precipId1, precipId2,
            precipId3, precipId4, mensRec1, womensRec1, mensRec2, womensRec2, mensRec3, womensRec3, mensRec4, womensRec4,
            accRec1, accRec2, accRec3, accRec4;

    @FXML
    private ImageView imageId1, imageId2, imageId3, imageId4;


    private YrModel yrModel = new YrModel();
    private ClothingModel clothingModel = new ClothingModel();
    private RDFController controller = new RDFController();
    private WeatherQueries weatherQueries = new WeatherQueries(controller);

   private ClothingRec clothingRec = new ClothingRec();
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

    public void displayElements(List<Weather> list) {
        setInfoToCol1(list);
        setInfoToCol2(list);
        setInfoToCol3(list);
        setInfoToCol4(list);
    }

    @SuppressWarnings("Duplicates")
    public void setInfoToCol1(List<Weather> list) {

        Text text = new Text(list.get(0).getDate());
        dateId1.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(0).getDateTimeStart().substring(0,5) + " til kl. " + list.get(0).getDateTimeEnd().substring(0,5));
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
        precipitation.setFill(Color.rgb(58, 58, 58));

        setImageIcon(list, 0);

        MensClothing mensClothing = clothingRec.setMensClothingRecommendation(list, 0);
        WomensClothing womensClothing = clothingRec.setWomensClothingRecommendation(list, 0);
        Accessories accessories = clothingRec.setAccessoriesRecommendation(list, 0);

        Text mensClothingText = null;
        Text womensClothingText = null;

        if (mensClothing.getGarment().equals(womensClothing.getGarment()) && mensClothing.getShoe().equals(womensClothing.getShoe())) {
            mensClothingText = new Text("Forslag: " + labels.getString(mensClothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(mensClothing.getShoe()).toLowerCase());
        } if(!mensClothing.getGarment().equals(womensClothing.getGarment())) {
            mensClothingText = new Text("Forslag for menn: " + labels.getString(mensClothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(mensClothing.getShoe()).toLowerCase());
            womensClothingText = new Text("Forslag for kvinner: " + labels.getString(womensClothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(womensClothing.getShoe()).toLowerCase());
            womensRec1.getChildren().add(womensClothingText);
        }

        Text accessoriesText = new Text("Kan være lurt å ta med " + labels.getString(accessories.getAccessory()).toLowerCase());

        mensRec1.getChildren().add(mensClothingText);
        accRec1.getChildren().add(accessoriesText);


    }

    @SuppressWarnings("Duplicates")
    public void setInfoToCol2(List<Weather> list) {

        Text text = new Text(list.get(1).getDate());
        dateId2.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(1).getDateTimeStart().substring(0,5) + " til kl. " + list.get(1).getDateTimeEnd().substring(0,5));
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
        precipitation.setFill(Color.rgb(58, 58, 58));

        setImageIcon(list, 1);
        MensClothing clothing = clothingRec.setMensClothingRecommendation(list, 1);
        WomensClothing womensClothing = clothingRec.setWomensClothingRecommendation(list, 1);
        Accessories accessories = clothingRec.setAccessoriesRecommendation(list, 1);


        Text mensClothingText = null;
        Text womensClothingText = null;

        if (clothing.getGarment().equals(womensClothing.getGarment()) && clothing.getShoe().equals(womensClothing.getShoe())) {
            mensClothingText = new Text("Forslag: " + labels.getString(clothing.getGarment()).toLowerCase() +"\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
        } else {
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
    public void setInfoToCol3(List<Weather> list) {

        Text text = new Text(list.get(2).getDate());
        dateId3.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(2).getDateTimeStart().substring(0,5) + " til kl. " + list.get(2).getDateTimeEnd().substring(0,5));
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
        precipitation.setFill(Color.rgb(58, 58, 58));

        setImageIcon(list, 2);



        MensClothing clothing = clothingRec.setMensClothingRecommendation(list, 2);
        WomensClothing womensClothing = clothingRec.setWomensClothingRecommendation(list, 2);
        Accessories accessories = clothingRec.setAccessoriesRecommendation(list, 2);

        Text mensClothingText = null;
        Text womensClothingText = null;


        if (clothing.getGarment().equals(womensClothing.getGarment()) && clothing.getShoe().equals(womensClothing.getShoe())) {
            mensClothingText = new Text("Forslag: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
        } else {
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

    @SuppressWarnings("Duplicates")
    public void setInfoToCol4(List<Weather> list) {

        Text text = new Text(list.get(3).getDate());
        dateId4.getChildren().add(text);
        text.setFill(Color.rgb(58, 58, 58));

        Text time = new Text("Fra kl. " + list.get(3).getDateTimeStart().substring(0, 5) + " Til kl." + list.get(3).getDateTimeEnd().substring(0,5));
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
        precipitation.setFill(Color.rgb(58, 58, 58));

        setImageIcon(list, 3);
        MensClothing clothing = clothingRec.setMensClothingRecommendation(list, 3);
        WomensClothing womensClothing = clothingRec.setWomensClothingRecommendation(list, 3);
        Accessories accessories = clothingRec.setAccessoriesRecommendation(list, 3);

        Text mensClothingText = null;
        Text womensClothingText = null;

        if (clothing.getGarment().equals(womensClothing.getGarment()) && clothing.getShoe().equals(womensClothing.getShoe())) {
            mensClothingText = new Text("Forslag: " + labels.getString(clothing.getGarment()).toLowerCase() + "\n" +
                    "Skotøy: " + labels.getString(clothing.getShoe()).toLowerCase());
        } else {
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

    public Image setImage(String url) {
        Image image = new Image(url);
        return image;
    }

    private void setImageIcon(List<Weather> list, int index) {

        String isEqual = list.get(index).getWeatherType();

        Image image = null;
        switch (isEqual){
            case "Skyet":
                image = setImage("GUI/Icons/dist/png/04.png");
                break;
            case "Delvis skyet":
                image = setImage("GUI/Icons/dist/png/03d.png");
                break;
            case "Klarvær":
                image = setImage("GUI/Icons/dist/png/01d.png");
                break;
            case "Lettskyet":
                image = setImage("GUI/Icons/dist/png/02d.png");
                break;
            case "Lette regnbyger":
                image = setImage("GUI/Icons/dist/png/40d.png");
                break;
            case "Regnbyger":
                image = setImage("GUI/Icons/dist/png/05d.png");
                break;
            case "Kraftige regnbyger":
                image = setImage("GUI/Icons/dist/png/41d.png");
                break;
            case "Lette regnbyger og torden":
                image = setImage("GUI/Icons/dist/png/24d.png");
                break;
            case "Regnbyger og torden":
                image = setImage("GUI/Icons/dist/png/06d.png");
                break;
            case "Kraftige regnbyger og torden":
                image = setImage("GUI/Icons/dist/png/25d.png");
                break;
            case "Lette sluddbyger":
                image = setImage("GUI/Icons/dist/png/42d.png");
                break;
            case "Sluddbyger":
                image = setImage("GUI/Icons/dist/png/07d.png");
                break;
            case "Kraftige sluddbyger":
                image = setImage("GUI/Icons/dist/png/43d.png");
                break;
            case "Lette sluddbyger og torden":
                image = setImage("GUI/Icons/dist/png/26d.png");
                break;
            case "Sluddbyger og torden":
                image = setImage("GUI/Icons/dist/png/20d.png");
                break;
            case "Kraftige sluddbyger og torden":
                image = setImage("GUI/Icons/dist/png/27d.png");
                break;
            case "Snøbyger":
                image = setImage("GUI/Icons/dist/png/08d.png");
                break;
            case "Kraftige snøbyger":
                image = setImage("GUI/Icons/dist/png/45d.png");
                break;
            case "Lette snøbyger og torden":
                image = setImage("GUI/Icons/dist/png/28d.png");
                break;
            case "Snøbyger og torden":
                image = setImage("GUI/Icons/dist/png/21d.png");
                break;
            case "Kraftige snøbyger og torden":
                image = setImage("GUI/Icons/dist/png/29d.png");
                break;
            case "Lett regn":
                image = setImage("GUI/Icons/dist/png/46.png");
                break;
            case "Regn":
                image = setImage("GUI/Icons/dist/png/09.png");
                break;
            case "Kraftig regn":
                image = setImage("GUI/Icons/dist/png/10.png");
                break;
            case "Lett regn og torden":
                image = setImage("GUI/Icons/dist/png/30.png");
                break;
            case "Regn og torden":
                image = setImage("GUI/Icons/dist/png/22.png");
            case "Kraftig regn og torden":
                image = setImage("GUI/Icons/dist/png/11.png");
                break;
            case "Lett sludd":
                image = setImage("GUI/Icons/dist/png/47.png");
                break;
            case "Sludd":
                image = setImage("GUI/Icons/dist/png/12.png");
                break;
            case "Kraftig sludd":
                image = setImage("GUI/Icons/dist/png/48.png");
                break;
            case "Lett sludd og torden":
                image = setImage("GUI/Icons/dist/png/31.png");
                break;
            case "Sludd og torden":
                image = setImage("GUI/Icons/dist/png/23.png");
                break;
            case "Kraftig sludd og torden":
                image = setImage("GUI/Icons/dist/png/32.png");
                break;
            case "Lett snø":
                image = setImage("GUI/Icons/dist/png/49.png");
                break;
            case "Snø":
                image = setImage("GUI/Icons/dist/png/13.png");
                break;
            case "Kraftig snø":
                image = setImage("GUI/Icons/dist/png/50.png");
                break;
            case "Lett snø og torden":
                image = setImage("GUI/Icons/dist/png/33.png");
                break;
            case "Snø og torden":
                image = setImage("GUI/Icons/dist/png/14.png");
                break;
            case "Kraftig snø og torden":
                image = setImage("GUI/Icons/dist/png/34.png");
                break;
            case "Tåke":
                image = setImage("GUI/Icons/dist/png/15.png");
                break;
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





