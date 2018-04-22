package RDF;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.query.ResultSet;

import java.io.*;

public class RDFController {

    private Model model;

    private String prefix = "PREFIX schema: <http://schema.org/Date#> \n" +
            "PREFIX owl:   <http://www.w3.org/2002/07/owl#> \n" +
            "PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
            "PREFIX we:    <https://www.auto.tuwien.ac.at/downloads/thinkhome/ontology/WeatherOntology.owl#> \n" +
            "PREFIX xsd:   <http://www.w3.org/2001/XMLSchema#> \n" +
            "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#> ";



    public RDFController(){
        this.model = ModelFactory.createDefaultModel();
    }


    public void addModel(Model model){
        this.model.add(model);
    }

    public ResultSet runSparql(String queryString){
        queryString = prefix + queryString;

        Query query = null;
        try {
           query = QueryFactory.create(queryString);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        return results;
    }

    public void saveModel(String name, Model model){
        FileWriter file = null;

        try{
            file = new FileWriter(name);
        }catch(IOException e){
            e.printStackTrace();
        }
        model.write(file, "Turtle");
    }


}
