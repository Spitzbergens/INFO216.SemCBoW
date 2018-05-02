package Models;

import java.util.List;

public class Clothing {

    private String clothingName;
    private String fitsSeasons;
    private String fitsCondition;
    private Integer warmthValue;

    public Clothing(String clothingName, Integer warmthValue) {
        this.clothingName = clothingName;

        this.warmthValue = warmthValue;
    }

    public Clothing(){

    }

    public String getFitsSeasons() {
        return fitsSeasons;
    }

    public void setFitsSeasons(String fitsSeasons) {
        this.fitsSeasons = fitsSeasons;
    }

    public String getFitsCondition() {
        return fitsCondition;
    }

    public void setFitsCondition(String fitsCondition) {
        this.fitsCondition = fitsCondition;
    }

    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        this.clothingName = clothingName;
    }

    public Integer getWarmthValue() {
        return warmthValue;
    }

    public void setWarmthValue(Integer warmthValue) {
        this.warmthValue = warmthValue;
    }
}
