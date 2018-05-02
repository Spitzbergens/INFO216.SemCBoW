package Models;

import java.util.List;

public class Clothing {

    private String clothingName;
    private List<String> fitsSeasons;
    private List<String> fitsCondition;
    private Integer warmthValue;

    public Clothing(String clothingName, Integer warmthValue) {
        this.clothingName = clothingName;

        this.warmthValue = warmthValue;
    }

    public List<String> getFitsSeasons() {
        return fitsSeasons;
    }

    public void setFitsSeasons(List<String> fitsSeasons) {
        this.fitsSeasons = fitsSeasons;
    }

    public List<String> getFitsCondition() {
        return fitsCondition;
    }

    public void setFitsCondition(List<String> fitsCondition) {
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
