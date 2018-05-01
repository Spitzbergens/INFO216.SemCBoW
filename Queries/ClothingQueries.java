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
                "  ?s cl:isSuitableToBeDressedOnWeather ?o " +
                "}";
        return controller.runQuery(query);
    }
}
