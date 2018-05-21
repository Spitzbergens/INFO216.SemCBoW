package ClothingRecommender;

import ClothindData.ClothingModel;
import Models.Accessories;
import Models.MensClothing;
import Models.Weather;
import Models.WomensClothing;
import Queries.ClothingQueries;
import RDF.RDFController;
import org.apache.jena.rdf.model.Model;

import java.util.List;

public class ClothingRec {


    private ClothingModel model = new ClothingModel();
    private  RDFController controller = new RDFController();
    private ClothingQueries clothingQueries = new ClothingQueries(controller);


    public ClothingRec(){
        Model clothingModel = model.readModel();
        controller.addModel(clothingModel);
    }

    @SuppressWarnings("Duplicates")
    public MensClothing setMensClothingRecommendation(List<Weather> list, int index) {
        MensClothing mensClothing = null;

        String isEqual = list.get(index).getWeatherType();

        switch (isEqual){
            case "Skyet":
                mensClothing = clothingQueries.mensToObject("Cloudy", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Delvis skyet":
                mensClothing = clothingQueries.mensToObject("Partly Cloudy", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Klarvær":
            case "Lettskyet":
                mensClothing = clothingQueries.mensToObject("Clear", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lette regnbyger":
            case "Regnbyger":
            case "Kraftige regnbyger":
            case "Lette regnbyger og torden":
            case "Regnbyger og torden":
            case "Kraftige regnbyger og torden":
                mensClothing = clothingQueries.mensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lette sluddbyger":
            case "Sluddbyger":
            case "Kraftige sluddbyger":
            case "Lette sluddbyger og torden":
            case "Sluddbyger og torden":
            case "Kraftige sluddbyger og torden":
                mensClothing = clothingQueries.mensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lette snøbyger":
            case "Snøbyger":
            case "Kraftige snøbyger":
            case "Lette snøbyger og torden":
            case "Snøbyger og torden":
            case "Kraftige snøbyger og torden":
                mensClothing = clothingQueries.mensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lett regn":
            case "Regn":
            case "Kraftig regn":
            case "Lett regn og torden":
            case "Regn og torden":
            case "Kraftig regn og torden":
                mensClothing = clothingQueries.mensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lett sludd":
            case "Sludd":
            case "Kraftig sludd":
            case "Lett sludd og torden":
            case "SLudd og torden":
            case "Kraftig sludd og torden":
                mensClothing = clothingQueries.mensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lett snø":
            case "Snø":
            case "Kraftig snø":
            case "Lett snø og torden":
            case "Snø og torden":
            case "Kraftig snø og torden":
                mensClothing = clothingQueries.mensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Tåke":
                mensClothing = clothingQueries.mensToObject("Cloudy", returnWeatherString(list, index), getSeasons(list, index));
                break;
        }


        return mensClothing;
    }

    private String returnWeatherString(List<Weather> list, int index){
        String temp = null;

        if (list.get(index).getTemperature() <= 5){
            temp = "Cold";
        }else if (list.get(index).getTemperature() >= 6 && list.get(index).getTemperature() <= 12 ){
            temp = "Moderately Cold";
        }else if (list.get(index).getTemperature() >= 13 && list.get(index).getTemperature() <= 18){
            temp = "Moderately Hot";
        }else if (list.get(index).getTemperature() >= 19){
            temp = "Hot";
        }
        return temp;
    }

    @SuppressWarnings("Duplicates")
    public WomensClothing setWomensClothingRecommendation(List<Weather> list, int index) {
        WomensClothing womensClothing = null;

        String isEqual = list.get(index).getWeatherType();

        switch (isEqual){
            case "Skyet":
                womensClothing = clothingQueries.womensToObject("Cloudy", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Delvis skyet":
                womensClothing = clothingQueries.womensToObject("Partly Cloudy", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Klarvær":
            case "Lettskyet":
                womensClothing = clothingQueries.womensToObject("Clear", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lette regnbyger":
            case "Regnbyger":
            case "Kraftige regnbyger":
            case "Lette regnbyger og torden":
            case "Regnbyger og torden":
            case "Kraftige regnbyger og torden":
                womensClothing = clothingQueries.womensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lette sluddbyger":
            case "Sluddbyger":
            case "Kraftige sluddbyger":
            case "Lette sluddbyger og torden":
            case "Sluddbyger og torden":
            case "Kraftige sluddbyger og torden":
                womensClothing = clothingQueries.womensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lette snøbyger":
            case "Snøbyger":
            case "Kraftige snøbyger":
            case "Lette snøbyger og torden":
            case "Snøbyger og torden":
            case "Kraftige snøbyger og torden":
                womensClothing = clothingQueries.womensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lett regn":
            case "Regn":
            case "Kraftig regn":
            case "Lett regn og torden":
            case "Regn og torden":
            case "Kraftig regn og torden":
                womensClothing = clothingQueries.womensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lett sludd":
            case "Sludd":
            case "Kraftig sludd":
            case "Lett sludd og torden":
            case "SLudd og torden":
            case "Kraftig sludd og torden":
                womensClothing = clothingQueries.womensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Lett snø":
            case "Snø":
            case "Kraftig snø":
            case "Lett snø og torden":
            case "Snø og torden":
            case "Kraftig snø og torden":
                womensClothing= clothingQueries.womensToObject("Wet", returnWeatherString(list, index), getSeasons(list, index));
                break;
            case "Tåke":
                womensClothing = clothingQueries.womensToObject("Cloudy", returnWeatherString(list, index), getSeasons(list, index));
                break;
        }
        return womensClothing;
    }

    @SuppressWarnings("Duplicates")
    public Accessories setAccessoriesRecommendation(List<Weather> list, int index) {
        Accessories accessories = null;

        String isEqual = list.get(index).getWeatherType();

        switch (isEqual){
            case "Skyet":
                accessories = clothingQueries.accessoriesToObject("Cloudy", getSeasons(list, index));
                break;
            case "Delvis skyet":
                accessories = clothingQueries.accessoriesToObject("Partly Cloudy", getSeasons(list, index));
                break;
            case "Klarvær":
            case "Lettskyet":
                accessories = clothingQueries.accessoriesToObject("Clear", getSeasons(list, index));
                break;
            case "Lette regnbyger":
            case "Regnbyger":
            case "Kraftige regnbyger":
            case "Lette regnbyger og torden":
            case "Regnbyger og torden":
            case "Kraftige regnbyger og torden":
                accessories = clothingQueries.accessoriesToObject("Wet",  getSeasons(list, index));
                break;
            case "Lette sluddbyger":
            case "Sluddbyger":
            case "Kraftige sluddbyger":
            case "Lette sluddbyger og torden":
            case "Sluddbyger og torden":
            case "Kraftige sluddbyger og torden":
                accessories = clothingQueries.accessoriesToObject("Wet", getSeasons(list, index));
                break;
            case "Lette snøbyger":
            case "Snøbyger":
            case "Kraftige snøbyger":
            case "Lette snøbyger og torden":
            case "Snøbyger og torden":
            case "Kraftige snøbyger og torden":
                accessories = clothingQueries.accessoriesToObject("Wet", getSeasons(list, index));
                break;
            case "Lett regn":
            case "Regn":
            case "Kraftig regn":
            case "Lett regn og torden":
            case "Regn og torden":
            case "Kraftig regn og torden":
                accessories = clothingQueries.accessoriesToObject("Wet", getSeasons(list, index));
                break;
            case "Lett sludd":
            case "Sludd":
            case "Kraftig sludd":
            case "Lett sludd og torden":
            case "SLudd og torden":
            case "Kraftig sludd og torden":
                accessories = clothingQueries.accessoriesToObject("Wet", getSeasons(list, index));
                break;
            case "Lett snø":
            case "Snø":
            case "Kraftig snø":
            case "Lett snø og torden":
            case "Snø og torden":
            case "Kraftig snø og torden":
                accessories= clothingQueries.accessoriesToObject("Wet", getSeasons(list, index));
                break;
            case "Tåke":
                accessories = clothingQueries.accessoriesToObject("Cloudy", getSeasons(list, index));
                break;
        }

        return accessories;

    }

    public String getSeasons(List<Weather> wList, int index) {

        String seasons = null;
        if (wList.get(index).getDate().substring(5, 7).equals("01") || wList.get(index).getDate().substring(5, 7).equals("02") || wList.get(index).getDate().substring(5, 7).equals("12")) {
            seasons = "Winter";
        } else if (wList.get(index).getDate().substring(5, 7).equals("04") || wList.get(index).getDate().substring(5, 7).equals("05") || wList.get(index).getDate().substring(5, 7).equals("03")) {
            seasons = "Spring";
        } else if (wList.get(index).getDate().substring(5, 7).equals("06") || wList.get(index).getDate().substring(5, 7).equals("07") || wList.get(index).getDate().substring(5, 7).equals("08")) {
            seasons = "Summer";
        } else if (wList.get(index).getDate().substring(5, 7).equals("09") || wList.get(index).getDate().substring(5, 7).equals("10") || wList.get(index).getDate().substring(5, 7).equals("11")) {
            seasons = "Autumn";
        }
        return seasons;

    }

}
