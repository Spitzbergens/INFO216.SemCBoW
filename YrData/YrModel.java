package YrData;

import Models.Weather;
import Queries.WeatherQueries;
import RDF.RDFController;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.json.*;
import org.json.simple.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.jena.rdf.model.ModelFactory.createDefaultModel;
import static org.apache.jena.rdf.model.ModelFactory.createOntologyModel;

public class YrModel {

    Yr yr = new Yr();
    Model model = createDefaultModel();


    private ArrayList<String> temp = yr.getTemprature();
    private ArrayList<String> windSpeedValue = yr.getWindSpeedValue();
    private ArrayList<String> windSpeedName = yr.getWindSpeedName();
    private ArrayList<String> weatherName = yr.getNametag();
    private ArrayList<String> date = yr.getFromtag();
    private ArrayList<String> observedAt = yr.getObservedTag();
    private ArrayList<String> precipitation = yr.getPrecipitation();
    private ArrayList<String> endTime = yr.getToPeriod();
    private ArrayList<String> dateTimeStart = yr.getTimeAndDateStart();
    private ArrayList<String> dateTimeEnd = yr.getTimeAndDateEnd();

    // Brukes kun for å hente størrelsen på listen
    private ArrayList<Integer> idList = yr.getIdList();
    int size = idList.size();


    /**
     * Brukes for testing
     *
     * @param args
     */
    public static void main(String[] args) {
        YrModel model = new YrModel();
        model.createAndParseModel();
        model.writeToFile();

        RDFController controller = new RDFController();
        WeatherQueries queries = new WeatherQueries(controller);
        Model weatherModel = model.createAndParseModel();
        controller.addModel(weatherModel);

        List<Weather> list = queries.getWeatherListWeek();

        System.out.println(list.get(0).getDate() + list.get(0).getDateTimeStart());
        System.out.println(list.get(1).getDate() + list.get(1).getDateTimeStart());






    }


    public void writeToFile() {
        OntModel ontmodel = createOntologyModel(OntModelSpec.OWL_MEM, model);

        try {
            ontmodel.write(new FileOutputStream("weatherModel.ttl"), "Turtle");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Model createAndParseModel() {

        String ontoURI = "https://www.auto.tuwien.ac.at/downloads/thinkhome/ontology/WeatherOntology.owl#";
        model.setNsPrefix("wo", ontoURI);

        String schemaDate = "http://schema.org/Date#";
        model.setNsPrefix("schema", schemaDate);

        Property weatherProperty = model.createProperty(ontoURI + "hasWeatherCondition");
        Property tempProperty = model.createProperty(ontoURI + "hasTemperature");
        Property windSpeedProperty = model.createProperty(ontoURI + "windType");
        Property windSpeedValueProperty = model.createProperty(ontoURI + "hasWind");
        Property observedAtProperty = model.createProperty(ontoURI + "hasStartTime");
        Property dateProperty = model.createProperty(schemaDate + "inDateTime");
        Property precipitationProperty = model.createProperty(ontoURI + "hasPrecipitation");
        Property endsAtProperty = model.createProperty(ontoURI + "hasEndTime");
        Property dateTimeStartProperty = model.createProperty(ontoURI + "inDateTime");
        Resource weatherResource = model.createResource(ontoURI + "WeatherCondition");


        for (int i = 0; i < size; i++) {

            Integer temperatureItem = Integer.parseInt(temp.get(i));
            String windSpeedNameItem = windSpeedName.get(i);
            Float windSpeedValueItem = Float.parseFloat(windSpeedValue.get(i));
            String weatherConditionItem = weatherName.get(i);
            String dateItem = date.get(i);
            String timeItem = observedAt.get(i);
            Double precipitationItem = Double.parseDouble(precipitation.get(i));
            String endTimeItem = endTime.get(i);
            String dateTimeStartItem = dateTimeStart.get(i);

            Resource weatherData = model.createResource(schemaDate + dateTimeStartItem, weatherResource)
                    .addLiteral(tempProperty, temperatureItem)
                    .addProperty(windSpeedProperty, windSpeedNameItem)
                    .addLiteral(windSpeedValueProperty, windSpeedValueItem)
                    .addProperty(weatherProperty, weatherConditionItem)
                    .addProperty(dateProperty, dateItem)
                    .addProperty(observedAtProperty, timeItem)
                    .addLiteral(precipitationProperty, precipitationItem)
                    .addProperty(endsAtProperty, endTimeItem);

        }
        return model;
    }


    public ArrayList<String> getTemp() {
        return temp;
    }

    public void setTemp(ArrayList<String> temp) {
        this.temp = temp;
    }

    public ArrayList<String> getWindSpeedValue() {
        return windSpeedValue;
    }

    public void setWindSpeedValue(ArrayList<String> windSpeedValue) {
        this.windSpeedValue = windSpeedValue;
    }

    public ArrayList<String> getWindSpeedName() {
        return windSpeedName;
    }

    public void setWindSpeedName(ArrayList<String> windSpeedName) {
        this.windSpeedName = windSpeedName;
    }


}
