package Utils;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

/*this class uses concepts:
* unit 1: methods objects
* unit 2: data types: use of primitives and non primitives used
* unit3: conditionals encapsulation loops
* unit4: arrays
* unit 5: exceptions, FILE IO
* unit 8: maps  */


// class of helpful methods that may be used more than once across my application
/*eliminate repeated code in my project*/

public class Methods {

    public static String extractCountryName(String countryData){
        // use of int datatype and conditionals to return the country name
        int nameIndexStart = countryData.indexOf(":{\"common\":") + 12;
        int nameIndexEnd = countryData.indexOf("\",");
        String countryName = "";
        if(nameIndexStart >= 12 && nameIndexEnd != -1){
            countryName = countryData.substring(nameIndexStart, nameIndexEnd);
        }
        return countryName;
    }

    // use linked hashmap to order hashmap by value and retain the order
    /*reference of stream usage: https://stackoverflow.com/a/29567964*/
    public static LinkedHashMap<String,Double> orderMapByValue(TreeMap<String, Double> mapToOrder){
        LinkedHashMap<String,Double> result = new LinkedHashMap<>();
        Stream<Map.Entry<String,Double>> sortByPop = mapToOrder.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        sortByPop.forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static LinkedHashMap<String,Integer> orderMapByValueInt(TreeMap<String, Integer> mapToOrder){
        LinkedHashMap<String,Integer> result = new LinkedHashMap<>();
        Stream<Map.Entry<String,Integer>> sortByPop = mapToOrder.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        sortByPop.forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static String getCountryData(String countryInfoData, String countryName){
        String countryInfo = "";
        // use of string array to get country info
        String[] countryInfoArr = countryInfoData.split("\\{\"flags\"");
        // use of loops to  get country data of specific country
        for(String countryData: countryInfoArr){
            if(countryData.contains("\"common\":"+ "\"" +countryName + "\"")){
                countryInfo = countryData;
                break;
            }
        }
        return countryInfo;
    }

    // extract specific info from string data depending on start and end string
    public static String extractInfo(String start, String end, String info){
        int startWordCount = start.length();
        int startIndex = info.indexOf(start);
        startIndex = startIndex + startWordCount;
        int endIndex = info.indexOf(end) - 2;

        return info.substring(startIndex,endIndex);

    }

    // get links of all flags
    public static String getFlagLink(String countryInfo){
        int linkStartIndex = countryInfo.indexOf("png") + 6;
        int linkEndIndex = countryInfo.lastIndexOf("png") + 3;
        return countryInfo.substring(linkStartIndex,linkEndIndex);
    }


    public static Image getDefault(){
        // get default img use of image object and catch IO exception when reading the file
        // read image and create file object of image to be able to create an image object
        Image image = null;
        try{
            image = ImageIO.read(new File("src/images/3674270-200.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }



}
