package DisplayCountries;

import Menu.AlphabetMenu;
import Menu.ContinentMenu;
import Menu.CurrencyMenu;
import Menu.LanguageMenu;
import RestCountries.RestCountriesWrapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

// this class showcases the following concepts:

// unit 1: objects and methods
// unit 2: data types
// unit 3: encapsulation and conditionals
// unit 7 lists
// unit 5: exceptions

// methods to get the relevant data from selected menu item and convert to buttons
class DisplayCountries {

    // encapsulation to keep certain variables within the class
    private RestCountriesWrapper  rcw = new RestCountriesWrapper();
    public String countryFlags;
    private String countryNameData;
    private ArrayList<String> countries = new ArrayList<>();
    // array list keeps list of buttons to be displayed
    private ArrayList <CountryButton> countryButtons = new ArrayList<>();

    protected DisplayCountries(){
        // exception to read rcw class data
        try{
            countryNameData = rcw.getAllCountries("name");
            countryFlags = rcw.getAllCountries("name,flags");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // default function displays countries in Europe
    protected ArrayList<CountryButton> defaultCountries(){
        // using list to get the results of all european countries
        ArrayList<String> countries = new ContinentMenu().returnResults("Europe");
        // loops to add country buttons to a list
        for(String country: countries){
            countryButtons.add(new CountryButton(country));
        }
        return countryButtons;
    }

    // functions to display correct countries based on menu item pressed
    protected ArrayList<CountryButton> currencyCountries(String currency){
        countries = new CurrencyMenu().returnResults(currency);
        if(countries.isEmpty())  return null;
        return getButtons(countries);
    }
    // encapsulation as function is only kept within the same class
    protected ArrayList<CountryButton> languageCountries(String language){
        countries = new LanguageMenu().returnResults(language);
        // conditional to check if there is nothing
        if(countries.isEmpty())  return null;
        return getButtons(countries);
    }

    protected ArrayList<CountryButton> continentCountries(String continent ){
        countries = new ContinentMenu().returnResults(continent);
        if(countries.isEmpty())  return null;
        return getButtons(countries);
    }

    protected ArrayList<CountryButton> alphabetCountries(String letter ){
        countries = new AlphabetMenu().returnResults(letter);
        if(countries.isEmpty())  return null;
        return getButtons(countries);
    }

    private ArrayList<CountryButton> getButtons(ArrayList<String> countries){
        for(String country: countries){
            countryButtons.add(new CountryButton(country));
        }
        return countryButtons;
    }

    protected ArrayList<CountryButton> getSearchedButton(String countryName){
        countryButtons.add(new CountryButton(countryName));
        return countryButtons;
    }


}
