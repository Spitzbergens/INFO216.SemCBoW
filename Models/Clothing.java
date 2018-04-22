package Models;

public class Clothing {

    private String clothingName;
    private String warmthValue;

    public Clothing(String clothingName, String warmthValue) {
        this.clothingName = clothingName;
        this.warmthValue = warmthValue;
    }

    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        this.clothingName = clothingName;
    }

    public String getWarmthValue() {
        return warmthValue;
    }

    public void setWarmthValue(String warmthValue) {
        this.warmthValue = warmthValue;
    }
}
