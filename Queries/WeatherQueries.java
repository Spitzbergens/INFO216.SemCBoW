package Queries;

import Models.Weather;
import RDF.RDFController;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.util.*;

public class WeatherQueries {

    public RDFController controller;
    public ArrayList<Weather> weatherList = new ArrayList<>();


    public WeatherQueries(RDFController controller) {
        this.controller = controller;
    }

    public ResultSet getWeeklyWeather() {

        String query = "SELECT ?dateTime ?time ?temperature ?windCondition ?windSpeed ?weatherCondition " +
                "WHERE {" +
                "?date a we:WeatherCondition." +
                "?date we:windType ?windType;" +
                "      we:hasTemperature ?temperature;" +
                "      we:hasObservationTime ?time;" +
                "      we:hasWeatherCondition ?condition; " +
                "      we:hasWind ?windSpeed;" +
                "      schema:inDateTime ?dateTime" +
                "} ORDER BY ?date ?time ";

        return controller.runQuery(query);

    }

    public ResultSet getWeatherByDay(String date) {
        String query = "SELECT DISTINCT ?startTime ?endTime ?temperature ?condition ?windSpeed ?windType ?precipitation ?dateTime\n" +
                "WHERE {\n" +
                "      ?conditions a we:WeatherCondition;\n" +
                "                  we:hasStartTime ?startTime;\n" +
                "                  we:hasEndTime ?endTime;\n" +
                "                  we:hasTemperature ?temperature;\n" +
                "                  we:hasWeatherCondition ?condition;\n" +
                "                  we:hasWind ?windSpeed;\n" +
                "                  we:windType ?windType;\n" +
                "                  schema:inDateTime ?dateTime.\n" +
                "  OPTIONAL {?conditions we:hasPrecipitation ?precipitation}\n" +
                "  FILTER (?dateTime = \"" + date + "\")" +
                "\n" +
                "            \n" +
                "} ORDER BY ?startTime";

        return controller.runQuery(query);
    }

    public ResultSet getWeatherDates() {
        String query = "SELECT DISTINCT ?datetime " +
                "WHERE { " +
                "?date a we:WeatherCondition; " +
                "schema:inDateTime ?datetime." +
                "} ORDER BY ?datetime";
        return controller.runQuery(query);
    }

    public List<Weather> getWeatherListWeek() {

        List<Weather> list = new LinkedList<>();
        ResultSet weekDates = getWeatherDates();
        while (weekDates.hasNext()) {
            QuerySolution qs = weekDates.nextSolution();
            Weather weather = queryToObject(qs.getLiteral("datetime").toString());
            list.add(weather);
        }
        return list;
    }

    public Weather queryToObject(String date) {

        Weather weather = null;
        ResultSet weatherSet = getWeatherByDay(date);
        QuerySolution qs = null;
        if (weatherSet.hasNext()) {
            try {
                qs = weatherSet.nextSolution();

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            weather = new Weather();
            weather.setDateTimeStart(qs.getLiteral("startTime").toString());
            weather.setDateTimeEnd(qs.getLiteral("endTime").toString());
            weather.setTemperature(qs.getLiteral("temperature").getInt());
            weather.setWeatherType(qs.getLiteral("condition").toString());
            weather.setWindSpeed(qs.getLiteral("windSpeed").getFloat());
            weather.setWind(qs.getLiteral("windType").toString());
            weather.setDate(qs.getLiteral("dateTime").toString());
            if (qs.getLiteral("precipitation") != null) {
                weather.setPrecipitation(qs.getLiteral("precipitation").getDouble());
            } else {
                weather.setPrecipitation(0.0);
            }
        }
        return weather;
    }

}

