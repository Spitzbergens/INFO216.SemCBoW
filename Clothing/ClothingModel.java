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
    private int sizeClothing = data.getClothing().size();
    private int sizeFootwear = data.getFootwear().size();




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
                    .addProperty(clothingProperty, clothingItem);


            if (clothingType.get(i).equals("bra") || clothingType.get(i).equals("ball gown")){
                womensClothingResource.addProperty(RDFS.subClassOf, clothingResource);
                clothingData.addProperty(womensClothingResource, clothingItem);
            }
        }

        for (int i = 0; i < sizeFootwear; i++){
            String footwearItem = footwearType.get(i);

            Resource footwearData = rdfsModel.createResource("http://example.com/Footwear#" + footwearItem, shoeResource)
                    .addProperty(shoeProperty, footwearItem);

            shoeResource.addProperty(RDFS.subClassOf, clothingResource);

        }


    }

    public void writeFile(){

        OntModel clothingOntModel = createOntologyModel(OntModelSpec.OWL_MEM, rdfsModel);

        try{
            clothingOntModel.write(new FileOutputStream("Clothing.ttl"), "Turtle");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Model parseModel(){
        createModel();
        return clothingModel;
    }

}
