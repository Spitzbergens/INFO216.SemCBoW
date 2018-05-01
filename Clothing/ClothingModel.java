package Clothing;

import Queries.ClothingQueries;
import RDF.RDFController;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ClothingModel {

    public ClothingModel(){
        readAndWriteModel();
    }

    public static void main(String[] args){
        ClothingModel model = new ClothingModel();
        RDFController controller = new RDFController();
        ClothingQueries queries = new ClothingQueries(controller);
        Model clothingModel = model.readAndWriteModel();
        controller.addModel(clothingModel);
        queries.queryAll();


    }

    public Model readAndWriteModel(){
        Model model = ModelFactory.createDefaultModel();
        try {
            model.read(new FileInputStream("/Users/Mats/IdeaProjects/INFO216.SemCBOW/src/ClothingOntology/semantic-clothing.ttl"), "http://ex.org/", "TURTLE");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return model;

    }
}
