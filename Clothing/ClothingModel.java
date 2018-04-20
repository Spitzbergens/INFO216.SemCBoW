package Clothing;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDFS;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.apache.jena.rdf.model.ModelFactory.createOntologyModel;

public class ClothingModel {

    ClothingData data = new ClothingData();
    Model clothingModel = ModelFactory.createDefaultModel();
    InfModel rdfsModel = ModelFactory.createRDFSModel(clothingModel);

    private ArrayList<String> clothingType = data.getClothing();
    private ArrayList<String> footwearType = data.getFootwear();
    private ArrayList<String> outerwear = data.getOuterwear();
    private int sizeClothing = data.getClothing().size();
    private int sizeFootwear = data.getFootwear().size();
    private int sizeOuterwear = data.getOuterwear().size();




    public void createModel(){

        String semClothURI = "http://www.semanticweb.org/ontologies/2015/02/semcloth.owl#";
        clothingModel.setNsPrefix("clo", semClothURI);


        String dbrClothes = "http://dbpedia.org/resource/Clothing";
        clothingModel.setNsPrefix("dbr", dbrClothes);

        String dbrFootwear = "http://dbpedia.org/resource/Footwear";
        clothingModel.setNsPrefix("dbr", dbrFootwear);


        Resource clothingResource = rdfsModel.createResource(dbrClothes);
        Resource shoeResource = rdfsModel.createResource(dbrFootwear);


        Property womensClothingResource = rdfsModel.createProperty(semClothURI + "Women's clothing");
        Property shoeProperty = rdfsModel.createProperty(dbrFootwear + "shoeType");
        Property clothingProperty = rdfsModel.createProperty(semClothURI +  "clothingType");

        for (int i = 0; i < this.sizeClothing; i++){
            String clothingItem = clothingType.get(i);

            Resource clothingData = rdfsModel.createResource("http://example.com/Clothing#" + clothingItem, clothingResource)
                    .addProperty(clothingProperty, clothingItem)
                    .addProperty(RDFS.label, clothingItem);

        }

        for (int i = 0; i < sizeFootwear; i++){
            String footwearItem = footwearType.get(i);

            Resource footwearData = rdfsModel.createResource("http://example.com/Footwear#" + footwearItem, shoeResource)
                    .addProperty(shoeProperty, footwearItem);

            shoeResource.addProperty(RDFS.subClassOf, clothingResource);
            shoeResource.addProperty(RDFS.label, footwearItem);

        }


    }

    public void writeFile(){

        OntModel clothingOntModel = createOntologyModel(OntModelSpec.OWL_MEM, rdfsModel);

        try{
            clothingOntModel.write(new FileOutputStream("/Users/Mats/IdeaProjects/INFO216.SemCBOW/src/Models/ClothingModel.ttl"), "Turtle");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Model parseModel(){
        createModel();
        return clothingModel;
    }

}
