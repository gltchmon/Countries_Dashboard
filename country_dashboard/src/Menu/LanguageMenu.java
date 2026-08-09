package Menu;
import RestCountries.RestCountriesWrapper;

import java.util.ArrayList;
import java.util.TreeMap;

// unit 1: objects, methods
// unit 2: data types, inheritance
// unit 3: encapsulation, conditionals , loops
// unit 4: Arrays
// unit 7: abstraction and lists
// unit 8 : map polymorphism

// polymorphism

// polymorphism and abstraction
// polymorphism as each menu class is implemented differently with the same interface
// abstraction from interface class
// inheritance of interface
/*here the polymorphism and abstraction concepts were used because the menu items mostly have same functionality so their behaviour should generally be the same
 * therefore all menu items have the same functionality however how they are implemented differs depending on the item
 * the interface helps enforce behavior of all menu items
 * also acts as a guide on how each of the menu items should function */
public class LanguageMenu implements MenuInterface {
    // use of object to get rcw data
    RestCountriesWrapper rcw = new RestCountriesWrapper();
    // map to keep country and its languages , uses list to make adding items easier
    // do not have to hardcode array size
    public TreeMap<String, ArrayList<String>> countryLanguages = new TreeMap<>();
    // string data type used to get country data
    private String languages;
    // use string array to split the country data
    // encapsulation of variables so they are only limited to this class
    private String[] countryLangArr;

     public LanguageMenu(){
         // exception to get data
        try{
            languages = rcw.getAllCountries("name,languages");
            countryLangArr = languages.split(",\\{\"name\":");
        } catch (Exception e) {
            e.printStackTrace();
        }
        createDataMap();
    }

    @Override
    // method to get all languages to display as option in menu item
    // return map of language and amount of countries that speak it
    public TreeMap<String, Integer> getData() {
         // use treemap for alphabetical order
        TreeMap<String, Integer> allLanguages =  new TreeMap<>();
        for (ArrayList<String> languages : countryLanguages.values()) {
            for(String language: languages){
                if(language.isEmpty()){
                    continue;
                } else if(!allLanguages.containsKey(language)){
                    allLanguages.put(language, 1);
                } else {
                    allLanguages.put(language, allLanguages.get(language) + 1);
                }
            }
        }
        return allLanguages;
    }

    @Override
    //method to return the result of specific language country based on value passed in
    public ArrayList<String> returnResults(String value) {
        ArrayList<String> countries = new ArrayList<>();
        // loop to find language
        for(String country: countryLanguages.keySet()){
            ArrayList<String> values = countryLanguages.get(country);
            for (String language : values){
                // conditional to find value
                if(language.equals(value)){
                    countries.add(country);
                }
            }
        }
        return countries;
    }

    // method to create map of countries and their languages
     public void createDataMap(){
        for(String country: countryLangArr){
            // datatypes of index and variables introduced in unit 1
            int nameStartIndex = country.indexOf("{\"common\":\"");
            int nameEndIndex = country.indexOf("\",");
            String countryName = country.substring(nameStartIndex + 11, nameEndIndex);
            int languageStartIndex = country.indexOf("languages\":{\"");
            String[] languagesArr;
            ArrayList<String> langs = new ArrayList<>();
            if(languageStartIndex != -1){
                languagesArr = country.substring(languageStartIndex + 12).split(",");
                for(String language : languagesArr){
                    int languageNameStartIndex = language.indexOf(":\"") + 2;
                    int languageNameEndIndex = language.lastIndexOf("\"");
                    String lang = language.substring(languageNameStartIndex, languageNameEndIndex);
                    langs.add(lang);
                }
                countryLanguages.put(countryName, langs);

            } else{
                countryLanguages.put(countryName, new ArrayList<>());
            }

        }

    }
}
