package Queries;

import Models.Clothing;
import RDF.RDFController;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.util.LinkedList;
import java.util.List;

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

    /**
     *
     * @param condition, F.eks "Cold", "Hot", "Clear", "Wet", "Dry", "Cloudy"
     * @return
     */
    public ResultSet queryClothingForWeather(String condition){
        String query = "SELECT ?label ?seasonlabel ?conditionLabel ?warmth " +
                "WHERE { " +
                "  ?clothing a owl:NamedIndividual; " +
                "    sc:isSuitableToBeDressedInSeason ?season; " +
                "            rdfs:label ?label; " +
                "            sc:isSuitableToBeDressedOnWeather ?condition; " +
                "            sc:hasWarmth ?warmth. " +
                "  ?season rdfs:label ?seasonlabel. " +
                "  ?condition rdfs:label ?conditionLabel. " +
                "   " +
                "  FILTER REGEX (?conditionLabel, \""+  condition + "\" ) "  +
                "   " +
                "}";

        return controller.runQuery(query);
    }

    public List<Clothing> getClothingByWeather(String weatherCondition){
        List<Clothing> list = new LinkedList<>();
        ResultSet ObjectSet = queryClothingForWeather(weatherCondition);
        while(ObjectSet.hasNext()){
            ObjectSet.next();
            Clothing clothing = queryToObject(weatherCondition);
            list.add(clothing);
        }
        return list;
    }

    public Clothing queryToObject(String weatherCondition){
        Clothing clothing = null;
        ResultSet clothingSet = queryClothingForWeather(weatherCondition);
        QuerySolution qs = null;
        if (clothingSet.hasNext()){
            try{
                qs = clothingSet.nextSolution();
            }catch (NullPointerException e) {
                e.printStackTrace();
            }
            clothing = new Clothing();
            clothing.setClothingName(qs.getLiteral("label").toString());
            clothing.setWarmthValue(qs.getLiteral("warmth").getInt());
            clothing.setFitsCondition(qs.getLiteral("conditionLabel").toString());
            clothing.setFitsSeasons(qs.getLiteral("seasonlabel").toString());
        }
        return clothing;
    }
}
