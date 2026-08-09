package Tables;

import Utils.Methods;

import java.util.*;
import java.util.stream.Stream;

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

public class PopulationTable extends TableData {
    // use map to keep store the country and populations
    private Map<String, Integer> countryPopulationMap;
    // encapsulation of variables to make sure they are only used within the class
    private String countryPopulation;
    private String[] countriesArr;
    // linked hashmap to retain order
    private LinkedHashMap<String, Integer> countryPopData;


    protected PopulationTable(String title) {
        super(title);
    }

    // override abstract class methods
    // string array because Jtables need arrays to create column titles
    @Override
    protected String[] setColumnNames(){
        return new String[] {"Position", "Country", "Population"};
    }

    @Override
    // use multidimensional array to set data and to be displayed on  Jtable unit 4 concept
    protected Object[][] setData(){
        // loop to get and extract data from rcw
        for(String countryData: countriesArr){
            String countryName = Methods.extractCountryName(countryData);
            // primitive int to get index
            int populationStartIndex = countryData.indexOf("population\":") + 12;
            int populationEndIndex = countryData.lastIndexOf("}");
            int population = 0;
            // conditional to make sure we have correct information
            if(populationStartIndex >= 12 && populationEndIndex != -1){
                population = Integer.parseInt(countryData.substring(populationStartIndex, populationEndIndex));
            }
            countryPopulationMap.put(countryName, population);
        }
        // sort map by value
        // use stream to be able to order the map by values. Stream processes collections to use filtering sorting and other operations
        /*reference: https://stackoverflow.com/a/29567964*/
        Stream<Map.Entry<String,Integer>> sortByPop = countryPopulationMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        sortByPop.forEach(e -> countryPopData.put(e.getKey(), e.getValue()));

        // place keys of top 10 countries into array
        int counter = 0;
        for(String key: countryPopData.keySet()){
            if(counter < 10){
                resultsKey[counter] = key;
            } else{
                break;
            }
            counter+=1;
        }

        Object [][] data = new Object[10][];
        for(int i = 0; i < resultsKey.length; i++){
            data[i] = new Object[] {String.valueOf(i+1), resultsKey[i], countryPopData.get(resultsKey[i])};
        }

        return data;
    }

    @Override
    protected void getData(){
        // initialise maps here
        countryPopulationMap = new HashMap<>();
        countryPopData = new LinkedHashMap<>();
        // initialise the results array enforce array size because i only need 10 countries
        resultsKey = new String[10];
        // try catch to check for exception that can occur in getting data
        try{
            countryPopulation = rcw.getAllCountries("name,population");
            countriesArr = countryPopulation.split(",\\{\"name\"");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }




}
