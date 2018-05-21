package YrData;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
import java.util.Objects;

public class Yr {

    private File file = new File("varsel.xml");
    long difference = new Date().getTime() - file.lastModified();
    private ArrayList<String> weatherType = new ArrayList<String>();
    private ArrayList<String> dateObserved = new ArrayList<String>();
    private ArrayList<String> timeAndDateStart = new ArrayList<>();
    private ArrayList<String> timeAndDateEnd = new ArrayList<>();
    private ArrayList<String> timeObserved = new ArrayList<>();
    private ArrayList<String> toPeriod = new ArrayList<>();
    private ArrayList<String> windspeedType = new ArrayList<String>();
    private ArrayList<String> windSpeedValue = new ArrayList<>();
    private ArrayList<String> temperature = new ArrayList<String>();
    private ArrayList<String> precipitation = new ArrayList<>();
    private ArrayList<String> precipitationMax = new ArrayList<>();
    private ArrayList<Integer> idList = new ArrayList<Integer>();


    /**
     * Constructor gets the API and constructing the ArrayLists
     */
    public Yr() {
        getAPI();
        constructList();
    }

    // https://www.researchgate.net/post/How_can_one_load_xml_file_inside_of_java_class_file_in_a_netbeans_project

    /**
     * First checking if caching different is above 10 minutes, following Yr's demand on caching.
     * Reading the URL and transfers the content into the readable byte channel and writing an XML-file.
     */
    public void getAPI() {
        System.out.println("YR API checking for updates.. ");


        if (difference > 60000 * 10) {
            try {
                URL weatherAPI = new URL("http://www.yr.no/sted/Norge/Hordaland/Bergen/Bergen/varsel.xml");
                ReadableByteChannel rbc = Channels.newChannel(weatherAPI.openStream());
                FileOutputStream fos = new FileOutputStream(file);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            } catch (Exception e) {
                System.out.println("Couldn't fint URL to API");
            }
        } else {
            System.out.println("Updated");
        }

    }

    /**
     * Constructing Arraylists from the XML-file/API. Creating a new documentBuilder that parses the file.
     * Creating an element of each tag with the name Tabular
     * Creates a nodelist from the "time" tag, which is used to iterate through the document.
     * Creating a node from each relevant tag, and then an own element. And then uses a selection of
     * each tags attributes to be but in their own ArrayLists, which is used to create the model.
     */

    public void constructList() {
        System.out.println("Constructing list from API");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try {
            dBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
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

        Element nodelist = (Element) Objects.requireNonNull(doc).getElementsByTagName("tabular").item(0);

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

            Node precipitationNode = eElement.getElementsByTagName("precipitation").item(0);
            Element precipitationElement = (Element) precipitationNode;

            Node windSpeedNode = eElement.getElementsByTagName("windSpeed").item(0);
            Element windspeedElement = (Element) windSpeedNode;

            weatherType.add(symbolElement.getAttribute("name"));
            dateObserved.add(StringUtils.left(eElement.getAttribute("from"), 10));
            timeObserved.add(StringUtils.right(eElement.getAttribute("from"), 8));
            toPeriod.add(StringUtils.right(eElement.getAttribute("to"), 8));
            timeAndDateStart.add(eElement.getAttribute("from"));
            timeAndDateEnd.add(eElement.getAttribute("from"));
            precipitation.add(precipitationElement.getAttribute("value"));
            precipitationMax.add(precipitationElement.getAttribute("maxvalue"));
            temperature.add(tempElement.getAttribute("value"));
            windspeedType.add(windTypeElement.getAttribute("name"));
            windSpeedValue.add(windspeedElement.getAttribute("mps"));
            idList.add(1 + i);

        }
        System.out.println("All OK");
    }

    /**
     * Getters and settes for each field, which is used to create the model.
     */
    public ArrayList<String> getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(ArrayList<String> precipitation) {
        this.precipitation = precipitation;
    }

    public ArrayList<String> getNametag() {
        return weatherType;
    }

    public ArrayList<String> getFromtag() {
        return dateObserved;
    }

    public ArrayList<String> getWindSpeedName() {
        return windspeedType;
    }

    public ArrayList<String> getTemprature() {
        return temperature;
    }

    public ArrayList<Integer> getIdList() {
        return idList;
    }

    public ArrayList<String> getWindSpeedValue() {
        return windSpeedValue;
    }

    public ArrayList<String> getObservedTag() {
        return timeObserved;
    }

    public ArrayList<String> getToPeriod() {
        return toPeriod;
    }

    public ArrayList<String> getTimeAndDateStart() {
        return timeAndDateStart;
    }

    public ArrayList<String> getTimeAndDateEnd() {
        return timeAndDateEnd;
    }

}

