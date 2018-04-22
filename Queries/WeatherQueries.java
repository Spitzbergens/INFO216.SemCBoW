package Queries;

import RDF.RDFController;
import org.apache.jena.query.ResultSet;

public class WeatherQueries {

    public RDFController controller;




    public WeatherQueries(RDFController controller){
        this.controller = controller;
    }

    public ResultSet getWeeklyWeather(){

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

    public ResultSet getWeatherByDay(String date){
        String query = "SELECT * " +
                "WHERE { " +
                "schema:" + date + " a we:WeatherCondition;" +
                "           we:hasObservationTime ?time;" + 
                "           we:hasTemperature ?temperature; " +
                "           we:hasWeatherCondition ?condition;" +
                "           we:hasWind ?windSpeed;" +
                "           we:windType ?windType;" +
                "            }";
        return controller.runSparql(query);
    }
}
