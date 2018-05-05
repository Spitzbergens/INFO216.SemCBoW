package RDF;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class RDFController {

    private Model model;

    private String prefix = "PREFIX schema: <http://schema.org/Date#> " +
            "PREFIX owl:   <http://www.w3.org/2002/07/owl#> " +
            "PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
            "PREFIX we:    <https://www.auto.tuwien.ac.at/downloads/thinkhome/ontology/WeatherOntology.owl#> " +
            "PREFIX xsd:   <http://www.w3.org/2001/XMLSchema#> " +
            "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#> " +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
            "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +
            "PREFIX dbr: <http://dbpedia.org/resource/> " +
            "PREFIX dbo: <http://dbpedia.org/ontology/> " +
            "PREFIX dbp: <http://dbpedia.org/property/> " +
            "PREFIX dbpedia: <http://dbpedia.org/> " +
            "PREFIX xml: <http://www.w3.org/XML/1998/namespace> " +
            "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> " +
            "PREFIX sc: <http://www.semanticweb.org/ontologies/2015/02/semcloth.owl#> " +
            "PREFIX gr: <http://purl.org/goodrelations/v1#>" +
            "PREFIX muto: <http://purl.org/muto/core#> " +
            "PREFIX sioc: <http://rdfs.org/sioc/ns#> " +
            "BASE <http://www.semanticweb.org/ontologies/2015/02/semcloth.owl> ";


    public RDFController() {
        this.model = ModelFactory.createDefaultModel();
    }


    public void addModel(Model model) {
        this.model.add(model);
    }

    public ResultSet runQuery(String queryString) {
        queryString = prefix + queryString;
        Query query = null;
        try {
            query = QueryFactory.create(queryString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        /**
         * Bruk denne for å skrive resultatet av en spørring til terminal.
         * Kommenter ut hvis ikke.
         */
        //ResultSetFormatter.out(results);

        return results;
    }

}
