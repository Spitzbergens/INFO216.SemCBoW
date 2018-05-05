package Queries;

import Models.Weather;
import RDF.RDFController;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        String query = "  SELECT ?time ?temperature ?condition ?windSpeed ?windType ?precipitation ?dateTime\n" +
                "            WHERE {\n" +
                "               schema:" + date + " a we:WeatherCondition;\n" +
                "                            we:hasObservationTime ?time;\n" +
                "                            we:hasTemperature ?temperature;\n" +
                "                            we:hasWeatherCondition ?condition;\n" +
                "                            we:hasWind ?windSpeed;\n" +
                "                            we:windType ?windType;\n" +
                "                            schema:inDateTime ?dateTime.\n" +
                "             OPTIONAL {schema:" + date + " we:hasPrecipitation ?precipitation" +
                "               FILTER (?precipitation > 0.0)}\n" +
                "\n" +
                "              FILTER (?time != \"00:00:00\")\n" +
                "            } ORDER BY ?time";
        return controller.runQuery(query);
    }

    public ResultSet getWeatherDates() {
        String query = "SELECT ?datetime " +
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
            weather.setDateTime(qs.getLiteral("time").toString());
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

