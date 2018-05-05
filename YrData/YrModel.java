package YrData;

import Models.Weather;
import Queries.WeatherQueries;
import RDF.RDFController;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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


        Weather weather = queries.queryToObject("2018-05-03");
        System.out.println(weather.getDate().substring(5, 7));

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
        Property observedAtProperty = model.createProperty(ontoURI + "hasObservationTime");
        Property dateProperty = model.createProperty(schemaDate + "inDateTime");
        Property precipitationProperty = model.createProperty(ontoURI + "hasPrecipitation");
        Resource weatherResource = model.createResource(ontoURI + "WeatherCondition");


        for (int i = 0; i < size; i++) {

            Integer temperatureItem = Integer.parseInt(temp.get(i));
            String windSpeedNameItem = windSpeedName.get(i);
            Float windSpeedValueItem = Float.parseFloat(windSpeedValue.get(i));
            String weatherConditionItem = weatherName.get(i);
            String dateItem = date.get(i);
            String timeItem = observedAt.get(i);
            Double precipitationItem = Double.parseDouble(precipitation.get(i));

            Resource weatherData = model.createResource(schemaDate + dateItem, weatherResource)
                    .addLiteral(tempProperty, temperatureItem)
                    .addProperty(windSpeedProperty, windSpeedNameItem)
                    .addLiteral(windSpeedValueProperty, windSpeedValueItem)
                    .addProperty(weatherProperty, weatherConditionItem)
                    .addProperty(dateProperty, dateItem)
                    .addProperty(observedAtProperty, timeItem)
                    .addLiteral(precipitationProperty, precipitationItem);


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
