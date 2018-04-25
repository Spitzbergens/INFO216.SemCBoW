package YrData;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.apache.commons.lang3.StringUtils;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Yr {

    private File file = new File("varsel.xml");
    long difference = new Date().getTime() - file.lastModified();
    private ArrayList<String> weatherType = new ArrayList<String>();
    private ArrayList<String> dateObserved = new ArrayList<String>();
    private ArrayList<String> timeObserved = new ArrayList<>();




    private ArrayList<String> windspeedType = new ArrayList<String>();
    private ArrayList<String> windSpeedValue = new ArrayList<>();
    private ArrayList<String> temperature = new ArrayList<String>();
    private ArrayList<Integer> idList = new ArrayList<Integer>();




    public Yr(){
        getWeatherAPI();
        makeSymbolList();
    }

    public void getWeatherAPI(){
        System.out.println("YR API checking for updates.. ");

        // YRs krav til caching på 10 minutt
        if (difference > 60000 * 10){
            try {
                URL weatherAPI = new URL("http://www.yr.no/sted/Norge/Hordaland/Bergen/Bergen/varsel.xml");
                ReadableByteChannel rbc = Channels.newChannel(weatherAPI.openStream());
                FileOutputStream fos = new FileOutputStream(file);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            } catch (Exception e) {
                System.out.println("Couldn't fint URL to API");
            }
        }else{
            System.out.println("Updated");
        }
    }

    public void makeSymbolList(){
        System.out.println("Constructing list from API");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try {
            dBuilder = dbf.newDocumentBuilder();
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }
        Document doc = null;

        try {
            doc = dBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element nodelist = (Element)doc.getElementsByTagName("tabular").item(0);

        NodeList timeNodeList = nodelist.getElementsByTagName("time");

        for (int i = 0; i < timeNodeList.getLength(); i++) {

            Node node = timeNodeList.item(i);
            Element eElement = (Element) node;

            Node symbolNode = eElement.getElementsByTagName("symbol").item(0);
            Element symbolElement = (Element) symbolNode;

            Node tempNode = eElement.getElementsByTagName("temperature").item(0);
            Element tempElement = (Element) tempNode;

            Node windNode = eElement.getElementsByTagName("windSpeed").item(0);
            Element windTypeElement = (Element) windNode;

            Node windSpeedNode = eElement.getElementsByTagName("windSpeed").item(0);
            Element windspeedElement = (Element) windSpeedNode;

            weatherType.add(symbolElement.getAttribute("name"));
            dateObserved.add(StringUtils.left(eElement.getAttribute("from"), 10));
            timeObserved.add(StringUtils.right(eElement.getAttribute("from"), 8));
            temperature.add(tempElement.getAttribute("value"));
            windspeedType.add(windTypeElement.getAttribute("name"));
            windSpeedValue.add(windspeedElement.getAttribute("mps"));
            idList.add(1+i);

        }
        System.out.println("All OK");
    }


    public ArrayList<String> getNametag() {
        return weatherType;
    }

    /**
     * the fromtab is and ArrayList
     * filled with a ceretain date.
     * The weather cast consists of two times and dates.
     * Time and date from, name of weather, time and date to.
     * to specify the duration of the weather.
     * The time and date TO is the next time and date
     * in line in this array.
     * @return
     */
    public ArrayList<String> getFromtag() {
        return dateObserved;
    }

    /**
     * The windspeed name is an arraylist filled with the
     * names of the windtypes that occur,
     * such as "Orkan", "Storm", etc.
     * @return
     */
    public ArrayList<String> getWindSpeedName() {
        return windspeedType;
    }



    /**
     * The temprature arraylist is an array
     * containting all the temeratures of the day
     * in celsius.
     * @return
     */
    public ArrayList<String> getTemprature() {
        return temperature;
    }


    /**
     * Getter for idList, used for giving values in table a unique values.
     * @return idList
     */
    public ArrayList<Integer> getIdList() {
        return idList;
    }

    /**
     * Getter for english version of nameTag list.
     * Contains translated names of all weather types.
     * To use in ontology.
     * @return nametagEng - ArrayList<String>
     */

    public ArrayList<String> getWindSpeedValue() {
        return windSpeedValue;
    }

    public ArrayList<String> getObservedTag() {
        return timeObserved;
    }
}
