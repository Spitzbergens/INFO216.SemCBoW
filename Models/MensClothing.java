package Models;

public class MensClothing {

   private String garment;
   private String fitsCondition;
   private String fitsSeason;
   private String shoe;

    public MensClothing(String garment, String fitsCondition, String fitsSeason, String shoe) {
        this.garment = garment;
        this.fitsCondition = fitsCondition;
        this.fitsSeason = fitsSeason;
        this.shoe = shoe;
    }

    public MensClothing(){

    }

    public String getGarment() {
        return garment;
    }

    public void setGarment(String garment) {
        this.garment = garment;
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

    public String getShoe() {
        return shoe;
    }

    public void setShoe(String shoe) {
        this.shoe = shoe;
    }
}
