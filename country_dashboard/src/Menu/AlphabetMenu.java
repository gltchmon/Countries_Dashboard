package Menu;

import RestCountries.RestCountriesWrapper;

import java.util.ArrayList;
import java.util.TreeMap;

// unit 1: objects, methods
// unit 2: data types, inheritance
// unit 3: encapsulation, conditionals , loops
// unit 4: Arrays
// unit 7: abstraction lists
// unit 5: exceptions
// unit 8 : maps, polymorphism


// polymorphism and abstraction
// polymorphism as each menu class is implemented differently with the same interface
// abstraction from interface class
// inheritance of interface
/*here the polymorphism and abstraction concepts were used because the menu items mostly have same functionality so how they act should generally be the same
* therefore all menu items have the same functionality however how they are implemented differs depending on the item
* the interface helps enforce behavior of all menu items
*  * also acts as a guide on how each of the menu items should function */
public class AlphabetMenu implements MenuInterface{

    // instantiate rcw object to get data
    // encapsulation only keep these variables within the class
    private RestCountriesWrapper rcw = new RestCountriesWrapper();
    // use string data type
    private String countryData;
    // arrays to keep the split countries of data
    private String[] countries;
    // map to store the alphabet character and countries in the alphabet as array list
    // arraylist used because i do not know the amount of countries in each alphabet and easy management
    public TreeMap<Character, ArrayList<String>> alphabetCountry = new TreeMap<>();

    public AlphabetMenu(){
        // exceptions used to get data  from rcw as it throws an exception
        try{
            countryData = rcw.getAllCountries("name");
            countries = countryData.split("\\{\"name\":");
        } catch (Exception e) {
            e.printStackTrace();
        }
        createDataMap();
    }

    // methods provided from interface being overridden to add implementation details
    @Override
    public TreeMap<String, Integer> getData() {
        // maps
        TreeMap<String, Integer> letterCountries = new TreeMap<>();
        // loops to get the alphabet and the count of countries
        for(Character letterKey: alphabetCountry.keySet()){
            String letter = String.valueOf(letterKey);
            letterCountries.put(letter, alphabetCountry.get(letterKey).size());
        }
        return letterCountries;
    }

    @Override
    // lists
    public ArrayList<String> returnResults(String value) {
        // use of char data type because i want the first letter of the country to display all countries beginning with that letter
        char letter = value.charAt(0);
        return alphabetCountry.get(letter);
    }

    @Override
    public void createDataMap() {
        // loops conditionals and datatypes such as int and chars and non primitives used to create a map of alphabet and countries
        for(int i = 65; i <= 90; i++){
            alphabetCountry.put( (char) i, new ArrayList<>());
        }

        for(String country: countries){
            if(country.equals("[")){
                continue;
            }
            int nameStartIndex = country.indexOf("\"common\":\"")+ 10;
            int nameEndIndex = country.indexOf("\",");
            String countryName = country.substring(nameStartIndex, nameEndIndex);
            if(alphabetCountry.containsKey(countryName.charAt(0))){
                ArrayList<String> letter = alphabetCountry.get(countryName.charAt(0));
                letter.add(countryName);
                alphabetCountry.put(countryName.charAt(0), letter);
            } else{
                ArrayList<String> letter = new ArrayList<>();
                letter.add(countryName);
                alphabetCountry.put('-',letter);
            }

        }
    }

}
