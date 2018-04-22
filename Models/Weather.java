package Models;

public class Weather {

    private String temperature;
    private String windSpeed;
    private String wind;
    private String weatherType;
    private String dateTime;
    private String condition;

    public Weather(String temperature, String windSpeed, String wind, String weatherType, String dateTime) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.wind = wind;
        this.weatherType = weatherType;
        this.dateTime = dateTime;

    }

    public Weather(){

    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
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
