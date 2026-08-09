package Menu;

import RestCountries.RestCountriesWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

// this class showcases the following concepts:

// unit 1:  methods
// unit 7: abstraction and lists
// unit 8 : maps
// how these concepts have been used is explained in the code


// interface for menu items
// interface was used because the menu item implementations will all follow the same structure and contain the same methods as they perform
// the same functionality but in different ways

// I use the concept of abstraction to abstract from implementation details and focus on what the menu items should do
// methods describe main functionality for each menu item

public interface MenuInterface {
    // method to get options for menu items
    //
    TreeMap<String,Integer> getData();

    // method to return results when menu item has been clicked
    // e.g., return string of countries that contain currencies in this
    // may need parameters for selected language/currency/letter
    ArrayList<String> returnResults(String value);

    // create a map containing the countries and data e.g., country continents
    void createDataMap();

}
