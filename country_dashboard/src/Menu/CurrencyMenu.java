package Menu;
import RestCountries.RestCountriesWrapper;

import java.util.*;

// this class demonstrates concepts:

// unit 1: objects, methods
// unit 2: data types, inheritance
// unit 3: encapsulation, conditionals , loops
// unit 4: Arrays
// unit 7: abstraction and lists
// unit 8 : map polymorphism


// polymorphism and abstraction
// polymorphism as each menu class is implemented differently with the same interface
// abstraction from interface class
// inheritance of interface
/*here the polymorphism and abstraction concepts were used because the menu items  have same functionality so how they act should generally be the same
 * therefore all menu items have the same functionality however how they are implemented differs depending on the item
 * the interface helps enforce behavior of all menu items regardless of their direct implementation*/
public class CurrencyMenu implements MenuInterface{
    // use of object to get rcw data
    RestCountriesWrapper rcw = new RestCountriesWrapper();
    // maps to keep the country and its currency
    // public will need to be accessed
    public HashMap<String,String> countryCur = new HashMap<String,String>();
    // encapsulation keep variables only to be used within this class
    private String currenciesData;
    private String currencyCountryData;

    // change later to protected
    public CurrencyMenu(){
        // exception to get data
        try{
            this.currenciesData = rcw.getAllCountries("currencies");
            this.currencyCountryData = rcw.getAllCountries("name,currencies");
        }catch(Exception e){
            e.printStackTrace();
        }

        createDataMap();
    }

    @Override
    // methods
    // this method gets all currencies and their counr=t
    public TreeMap<String,Integer> getData() {
        // data types and arrays using string array
        String[] toArr = currenciesData.split("},");
        // save all types currencies in hashmap and display how many currencies use it
        // create map of currencies : using maps
        // using a treemap is good for keeping alphabetical order of keys so when being displayed it will not need additional sorting
        TreeMap<String, Integer> currencies = new TreeMap<>();
        String curr = "";
        for(String currency: toArr){
            int start = currency.indexOf("\":{\"name\":");
            //conditionals
            if(start == 20){
                curr = currency.substring(17,20);
            } else if (start == 4) {
                curr = currency.substring(1,4);
            } else if (start == 19) {
                curr = currency.substring(16,19);
            }
            if(currencies.containsKey(curr)){
                currencies.put(curr, currencies.get(curr) + 1);
            } else {
                currencies.put(curr, 1);
            }
        }
        return currencies;
    }

    @Override
    // searches by currency and returns result of countries by the passed in currency
    public ArrayList<String> returnResults(String value) {
        // using string list to avoid hard coding array size
        ArrayList<String> countries = new ArrayList<>();
        // loops and conditionals
        for(String country: countryCur.keySet()){
            if(countryCur.get(country).equals(value)){
                countries.add(country);
            }
        }
        return countries;
    }

    // returns hashmap of country and currency
    public void createDataMap(){
        // referring to countryName variable
        String[] arr = currencyCountryData.split("\\{\"name\":\\{");
        // use a map to keep the currency and countries
        HashMap<String, String> countryCurrency = new HashMap<>();
        for(String data: arr){
            int nameIndexStart = data.indexOf(":\"");
            int nameIndexEnd = data.indexOf("\",");
            int currencyIndexStart = data.indexOf("\":{\"name\"");
            if(nameIndexStart != -1 && currencyIndexStart != -1){
                String name = data.substring(nameIndexStart+2, nameIndexEnd);
                String currency = data.substring(currencyIndexStart - 3, currencyIndexStart);
                countryCurrency.put(name,currency);
            }
        }
        this.countryCur = countryCurrency;
    }


}
