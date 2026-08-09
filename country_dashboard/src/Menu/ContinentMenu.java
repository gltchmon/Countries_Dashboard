package Menu;

import RestCountries.RestCountriesWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

// unit 1: objects, methods
// unit 2: data types, inheritance
// unit 3: encapsulation, conditionals , loops
// unit 4: Arrays
// unit 7: abstraction lists
// exceptions
// unit 8 : maps, polymorphism

// polymorphism and abstraction
// polymorphism as each menu class is implemented differently with the same interface
// abstraction from interface class
// inheritance of interface
/*here the polymorphism and abstraction concepts were used because the menu items mostly have same functionality so how they function should generally be the same
 * therefore all menu items have the same functionality however how they are implemented differs depending on the item
 * the interface helps enforce behavior of all menu items */
public class ContinentMenu implements MenuInterface{

    // instantiate rcw object to get data
    // encapsulation only keep these variables within the class
    RestCountriesWrapper rcw = new RestCountriesWrapper();
    private String continentData;
    private String[] countryDataArr;

    // map to store the alphabet character and countries in the alphabet as array list
    // uses string array to store the continents in case a country has more than one continent
    public TreeMap<String, String[]> countryContinents = new TreeMap<>();

    public ContinentMenu(){
        // exceptions to get data
        try{
            continentData = rcw.getAllCountries("name,continents");
            countryDataArr = continentData.split("\\{\"name\":");
        } catch (Exception e) {
            e.printStackTrace();
        }
        createDataMap();
    }

    @Override
    public TreeMap<String, Integer> getData() {
        // map to contain continent and amount of countries in continent
        TreeMap<String, Integer> continents = new TreeMap<>();
        // loops to get continents
        for(String[] continent: countryContinents.values()){
            if(continent.length > 1){
                /*loop through array if more than one continent*/
                for(String con : continent){
                    if(continents.containsKey(con)){
                        continents.put(con, continents.get(con) + 1);
                    } else {
                        continents.put(con, 1);
                    }
                }
            } else {
                if(continents.containsKey(continent[0])){
                    continents.put(continent[0], continents.get(continent[0]) + 1);
                } else {
                    continents.put(continent[0], 1);
                }
            }
        }
        return continents;
    }

    // methods implemented from interface
    @Override
    public ArrayList<String> returnResults(String value) {
        // return the results of where country is in array list
        ArrayList<String> result = new ArrayList<>();
        for(String country: countryContinents.keySet()){
            String[] continents = countryContinents.get(country);
            if(continents.length > 1){
                for(String continent: continents){
                    if(continent.equals(value)){
                        result.add(country);
                    }
                }
            } else {
                if(continents[0].equals(value)){
                    result.add(country);
                }
            }
        }
        return result;
    }

    // create hashmap of country and continents
    // methods
    @Override
    public void createDataMap() {
        for( String data: countryDataArr){
            int nameIndexStart = data.indexOf("\"common\":\"") + 10;
            int nameIndexEnd = data.indexOf("\",");
            String countryName = "";
            if(nameIndexStart >= 10 && nameIndexEnd != -1){
                countryName = data.substring(nameIndexStart,nameIndexEnd);
            }
            int continentNameStart = data.indexOf("continents\":[\"")+ 14;
            int continentNameEnd = data.indexOf("\"]");
            if(continentNameStart >= 14 && continentNameEnd != -1){
                String continentNames = data.substring(continentNameStart, continentNameEnd);
                // string array : data types and arrays
                String[] conts = continentNames.split("\",\"");
                countryContinents.put(countryName, conts);
            }
        }
    }
}
