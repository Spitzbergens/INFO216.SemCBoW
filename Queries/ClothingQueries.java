package Queries;

import RDF.RDFController;
import org.apache.jena.query.ResultSet;

public class ClothingQueries {

    private RDFController controller;


    public ClothingQueries(RDFController controller) {
        this.controller = controller;
    }

    public ResultSet queryAll(){
        String query = "SELECT ?s ?o " +
                "WHERE { " +
                "  ?s sc:isSuitableToBeDressedOnWeather ?o " +
                "}";
        return controller.runQuery(query);
    }

    public ResultSet queryClothingForWeather(){
        String query = "SELECT ?label ?seasonlabel ?conditionLabel ?warmth " +
                "WHERE { " +
                "  ?clothing a owl:NamedIndividual; " +
                "    sc:isSuitableToBeDressedInSeason ?season; " +
                "            rdfs:label ?label; " +
                "            sc:isSuitableToBeDressedOnWeather ?condition; " +
                "            sc:hasWarmth ?warmth. " +
                "  ?season rdfs:label ?seasonlabel. " +
                "  ?condition rdfs:label ?conditionLabel.   " +
                "}";

        return controller.runQuery(query);
    }
}
