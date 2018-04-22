package Queries;

import Models.Weather;
import RDF.RDFController;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.util.ArrayList;

public class WeatherQueries {

    public RDFController controller;
    public ArrayList<Weather> weatherList = new ArrayList<>();


    public WeatherQueries(RDFController controller) {
        this.controller = controller;
    }

    public ResultSet getWeeklyWeather() {

        String query = "SELECT ?date ?time ?temperature ?windCondition ?windSpeed ?weatherCondition " +
                "WHERE {" +
                "?date a we:WeatherCondition." +
                "?date we:windType ?windCondition;" +
                "      we:hasTemperature ?temperature;" +
                "      we:hasObservationTime ?time;" +
                "      we:hasWeatherCondition ?weatherCondition; " +
                "      we:hasWind ?windSpeed ." +
                "} ORDER BY ?date ?time ";

        return controller.runSparql(query);

    }

    public ResultSet getWeatherByDay(String date) {
        String query = "SELECT * " +
                "WHERE { " +
                "schema:" + date + " a we:WeatherCondition; " +
                "we:hasObservationTime ?time; " +
                "we:hasTemperature ?temperature; " +
                "we:hasWeatherCondition ?condition; " +
                "we:hasWind ?windSpeed;" +
                "we:windType ?windType. " +
                "}";
        return controller.runSparql(query);
        
    }

    public Weather queryToObject(String date) {
        // 

        Weather weather = null;
        ResultSet weatherSet = getWeatherByDay(date);
        QuerySolution qs = null;

        if (weatherSet.hasNext()) {
            try {
                qs = weatherSet.nextSolution();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            //weather = new Weather(qs.getLiteral("temperature").toString(), qs.getLiteral("windSpeed").toString(),
            weather = new Weather();
            weather.setDateTime(qs.getLiteral("time").toString());
           //        qs.getLiteral("windType").toString(), qs.getLiteral("condition").toString(), qs.getLiteral("time").toString());
            System.out.println(qs.getLiteral("time").getString());
        }

        //    public Weather(String temperature, String windSpeed, String wind, String weatherType, String dateTime, String condition
        return weather;
    }
}
