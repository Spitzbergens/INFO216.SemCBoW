package GUI;

import Models.Weather;
import Queries.WeatherQueries;
import RDF.RDFController;
import YrData.YrModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.jena.rdf.model.Model;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GUIMainController implements Initializable {

    @FXML
    private VBox boxValues1, boxValues2, boxValues3, boxValues4, boxValues5;

    @FXML
    private HBox dateId;

    @FXML
    private Text dateTextId;



    private YrModel model = new YrModel();
    private RDFController controller = new RDFController();
    private WeatherQueries queries = new WeatherQueries(controller);





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model.createModel();
        model.writeToFile();
        Model weatherModel = model.parse();
        controller.addModel(weatherModel);

    }

    public void setTexttoDate(List<Weather> list){

        for (Weather obj : list) {
            Weather weather = new Weather();
            weather.setDate(obj.getDate());
            weather.setWind(obj.getWind());
            weather.setWindSpeed(obj.getWindSpeed());
            weather.setDateTime(obj.getDateTime());
            weather.setWeatherType(obj.getWeatherType());

            Text text = new Text(weather.getDate());
            dateId.getChildren().add(text);

            dateTextId.setText(weather.getDate());
        }


    }
}
