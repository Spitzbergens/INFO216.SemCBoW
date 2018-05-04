package Models;

public class Weather {

    private Integer temperature;
    private Float windSpeed;
    private String wind;
    private String weatherType;
    private String dateTime;
    private String date;
    private Double precipitation;


    public Weather(Integer temperature, Float windSpeed, String wind, String weatherType, String dateTime, String date, Double
                   precipitation) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.wind = wind;
        this.weatherType = weatherType;
        this.dateTime = dateTime;
        this.date = date;
        this.precipitation = precipitation;


    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Weather(){

    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


}
