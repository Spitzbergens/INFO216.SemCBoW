package Clothing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class ClothingData {

    private ArrayList<String> clothing = new ArrayList<>();
    private ArrayList<String> footwear = new ArrayList<>();


    public ClothingData(){
        JSONFileScraper();
    }

    @SuppressWarnings("unchecked")
    public void JSONFileScraper(){

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("/Users/Mats/IdeaProjects/INFO216.SemCBOW/src/Misc/clothing.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray clothingList = (JSONArray) jsonObject.get("clothes");
            JSONArray shoeList = (JSONArray) jsonObject.get("footwear");

            Iterator<String> itrClothing = clothingList.iterator();
            Iterator<String> itrShoe = shoeList.iterator();
            while(itrClothing.hasNext()){
                clothing.add(itrClothing.next());
            }
            while(itrShoe.hasNext()){
                footwear.add(itrShoe.next());
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<String> getClothing() {
        return clothing;
    }

    public ArrayList<String> getFootwear(){
        return footwear;
    }

    public int totalSize(){
        int size;
        size = getClothing().size() + getFootwear().size();
        return size;
    }
}


