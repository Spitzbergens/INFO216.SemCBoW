package Models;

public class Accessories {
    private String accessory;
    private String fitsCondition;
    private String fitsSeason;

    public Accessories(String accessory, String fitsCondition, String fitsSeason) {
        this.accessory = accessory;
        this.fitsCondition = fitsCondition;
        this.fitsSeason = fitsSeason;
    }

    public Accessories() {

    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getFitsCondition() {
        return fitsCondition;
    }

    public void setFitsCondition(String fitsCondition) {
        this.fitsCondition = fitsCondition;
    }

    public String getFitsSeason() {
        return fitsSeason;
    }

    public void setFitsSeason(String fitsSeason) {
        this.fitsSeason = fitsSeason;
    }
}
