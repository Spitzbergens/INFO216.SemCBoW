package Clothing;

import Queries.ClothingQueries;
import RDF.RDFController;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ClothingModel {

    public ClothingModel(){
        readModel();
    }

    public static void main(String[] args){
        ClothingModel model = new ClothingModel();
        RDFController controller = new RDFController();
        ClothingQueries queries = new ClothingQueries(controller);
        Model clothingModel = model.readModel();
        controller.addModel(clothingModel);
        queries.queryClothingForWeather();


    }

    public Model readModel(){
        Model model = ModelFactory.createDefaultModel();
        model.read("Clothing/semcloth.ttl", "Turtle");
        return model;
    }
}

//"/Users/Mats/IdeaProjects/INFO216.SemCBOW/src/ClothingOntology/semcloth.ttl"), "http://www.semanticweb.org/ontologies/2015/02/semcloth.owl#", "TURTLE"