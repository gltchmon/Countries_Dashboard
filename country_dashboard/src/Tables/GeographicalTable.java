package Tables;

import java.util.LinkedHashMap;
import java.util.TreeMap;
// this class showcases the following concepts:

// unit 1: objects methods
// unit 2: data types, inheritance
// unit 3: encapsulation, conditionals , loops
// unit 4: arrays
// unit 8 : polymorphism, maps

// concepts are described below

// table for geographic size
// use of polymorphism specifically dynamic polymorphism
/*table classes all have different implementations but use the table data abtract class to inherit its methods and variables */
/*this keeps things concise and avoids repetition of methods and variables across all my classes
 * table data class defines order in which methods are called
 * class defines how each abstract method needs to be implemented  */

public class GeographicalTable extends TableData {
    // encapsulation of variables ensures they are only used within this class
    // Treemap used to store country and geographic size
    private TreeMap<String, Double> countryAreaMap;
    // linked hashmap used to order the countries to display in table
    private LinkedHashMap<String,Double> countryAreaOrdered;
    // get country data from rcw
    private String countryGeoSize;
    // get all countries geoographic size and name into an array
    private String[] countryGeoSizeArr;

    // super concept of inheriting title introduced in unit 2: inheritance
    protected GeographicalTable(String title) {
        super(title);
    }
    @Override
    protected String[] setColumnNames(){
        // string array because Jtables need arrays to create column titles
        return new String[] {"Position", "Country", "Geographic Size"};
    }

    @Override
    // method to set the data
    /*get the area of each country and order it*/
    // use of multidimensional array unit 4
    protected Object[][] setData(){
        for(String countryData: countryGeoSizeArr){
            String countryName = Utils.Methods.extractCountryName(countryData);
            int areaStartIndex = countryData.indexOf("area") + 6;
            int areaEndIndex = countryData.lastIndexOf("}");
            // getting area using string datatype
            String area = "";
            // conditional to ensure we are getting the correct value
            if(areaStartIndex >= 6 && areaEndIndex != -1){
                area = countryData.substring(areaStartIndex,areaEndIndex);
            }
            countryAreaMap.put(countryName, Double.valueOf(area));
        }
        // order values instead of key
        countryAreaOrdered = Utils.Methods.orderMapByValue(countryAreaMap);

        // int data type used to make sure we do not go over 10
        int i = 0;
        // get top 10 countries from the ordered map
        for(String key: countryAreaOrdered.keySet()){
            if(i < 10){
                // place into results array we use this to display the countries
                resultsKey[i] = key;
                i++;
            }
        }
        // create multi dimensional array to use in the table
        Object [][] data = new Object[10][];
        // loop to create data
        for(int j = 0; j < resultsKey.length; j++){
            data[j] = new Object[] {String.valueOf(j+1), resultsKey[j], countryAreaOrdered.get(resultsKey[j])};
        }
        return data;
    }

    @Override
    protected void getData(){
        // initialise hashmaps here as this method is called first
        countryAreaMap = new TreeMap<>();;
        countryAreaOrdered = new LinkedHashMap<>();
        resultsKey = new String[10];
        // exception to get rcw data and catch exceptions that occur
        try{
            countryGeoSize = rcw.getAllCountries("name,area");
            countryGeoSizeArr = countryGeoSize.split(",\\{\"name\"");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
