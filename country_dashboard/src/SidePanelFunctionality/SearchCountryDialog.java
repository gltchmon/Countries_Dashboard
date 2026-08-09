package SidePanelFunctionality;

import Main.MyMain;
import Menu.ContinentMenu;
import Menu.CurrencyMenu;
import Menu.LanguageMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

// class demonstrates concepts such as:

//unit 1: methods, objects variables
// unit 2: data types inheritance
// unit 3: encapsulation condtionals
// unit 4: arrays
// unit 8: maps

// class to allow user to search for a country
public class SearchCountryDialog{
    // encapsulation this class is only to be used within the same package and methods are kept within this class
    protected SearchCountryDialog(){
        // method to display the input when this class has been instantiated
        displayInput();
    }

    private void displayInput() {
        //GUI to display option pane
        // conditional used to check if there has been blank input to deal with errors
        String countryName = JOptionPane.showInputDialog(null, "Enter country name","Search by name", JOptionPane.PLAIN_MESSAGE );
        if(countryName == null || countryName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No country typed.","Blank Input", JOptionPane.PLAIN_MESSAGE );
        } else {
            countryName = countryName.trim();
            // maps used to get information of the country
            TreeMap<String, String[]> continentCountry = new ContinentMenu().countryContinents;
            // use of arrays to split country by word and be able to capitalise the start of each letter
            String[] countryNameArr = countryName.split(" ");
            // instantiate string builder to append all words together
            StringBuilder country = new StringBuilder();
            // capitalise letter of each word to find the country using loops and conditionals
            int counter = 0;
            for (String word : countryNameArr) {
                if (word.equals("and") || word.equals("of")) {
                    country.append(word.toLowerCase() + " ");
                    continue;
                } else {
                    String firstLetter = String.valueOf(word.charAt(0)).toUpperCase();
                    String fullWord = firstLetter + word.substring(1).toLowerCase();
                    countryNameArr[counter] = fullWord;
                    if(counter + 1 == countryNameArr.length){
                        country.append(fullWord);
                    } else{
                        country.append(fullWord + " ");
                    }
                    counter++;
                }
            }

            if(!continentCountry.containsKey(String.valueOf(country))){
                JOptionPane.showMessageDialog(null, "Country could not be found, please try again","ERROR: Not found!", JOptionPane.ERROR_MESSAGE );
            } else{
                MyMain.frame.changeMainPanel("search", String.valueOf(country));
            }
        }


    }

}
