package Tables;

import Menu.LanguageMenu;

import java.util.LinkedHashMap;
import java.util.TreeMap;

// this class showcases the following concepts:

// unit 1: objects methods
// unit 2: data types, inheritance
// unit 3: encapsulation, conditionals , loops
// unit 4: arrays
// unit 8 : polymorphism, maps

// concepts are described below

// table for languages
// use of polymorphism specifically dynamic polymorphism and inheritance to extend from abstract table data class
/*table classes all have different implementations but use the table data abtract class to inherit its methods and variables */
/*this keeps things concise and avoids repetition of methods and variables across all my classes
 * table data class defines order in which methods are called
 * class defines how each abstract method needs to be implemented  */

// panel for languages table
public class LanguageTable extends TableData {
    // maps used to store the languages and their count
    // linked map used to retain order by VALUES not by keys
    private TreeMap<String, Integer> languages;
    private LinkedHashMap<String,Integer> languagesOrdered;

    protected LanguageTable(String title) {
        // inherit title initialisation from abstract class
        super(title);
    }


    @Override
    // method to get data and order
    protected void getData(){
        languages = new LanguageMenu().getData();
        // order the languages map by value so we can get the largest at the top of the table
        languagesOrdered = Utils.Methods.orderMapByValueInt(languages);
        resultsKey = new String[10];
    }

    @Override
    // string array because Jtables need arrays to create column titles
    protected String[] setColumnNames(){
        return new String[] {"Position", "Language", "Amount"};
    }

    // use of multidimensional array unit 4
    protected Object[][] setData(){
        int i =0;

        // use for loop to get top 10 languages
        for(String key: languagesOrdered.keySet()){
            if(i < 10){
                // use of array to keep array of the keys so we can use as reference to display from the hashmap
                resultsKey[i] = key;
                i++;
            }
        }

        Object [][] data = new Object[10][];
        for(int j = 0; j < resultsKey.length; j++){
            // create the multi dimensional array containing data
            data[j] = new Object[] {String.valueOf(j+1), resultsKey[j], languagesOrdered.get(resultsKey[j])};
        }
        return data;
    }
}
