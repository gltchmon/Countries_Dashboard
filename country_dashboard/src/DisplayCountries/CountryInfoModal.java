package DisplayCountries;

import Menu.LanguageMenu;
import RestCountries.RestCountriesWrapper;
import SidePanelFunctionality.Wishlist.WishlistButton;
import Utils.Methods;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

// this class showcases the following concepts:

// unit 1: objects methods
// unit 2: data types
// unit 3: encapsulation, conditionals , loops
// unit 4: arrays
// unit 5 exceptions
// unit 6: Graphics / unit 9 GUI to display images
// unit 7: lists
// unit 8 maps
// unit 10: Recursion
// how these concepts have been used is explained in the code

// display country information when clicked
class CountryInfoModal {
    // encapsulation variables only used within the class, eliminate coupling or usage outside of class

    // int data type because JOption pane returns a number of what happened
    private int option;
    // string array to keep options of modal
    private String[] options = new String[2];
    private final RestCountriesWrapper rcw = new RestCountriesWrapper();
    private String officialName, commonName,independence, capital ,region,area,population, languages, imagePngLink;
    //maps to contain languages and country containing array list
    // get language menu object to get language tree map of languages
    private final TreeMap<String, ArrayList<String>> countryLanguages = new LanguageMenu().countryLanguages;

    // encapsulation: only used within same package
    protected CountryInfoModal(String countryName){

        // add options to joptiionpane
        options[0] = "Add to wishlist";
        options[1] = "Close";
        // get info
        countryInformation(countryName);
        // create icon from the image link
        ImageIcon icon = getFlag(imagePngLink);
        // graphical user interface
        option = JOptionPane.showOptionDialog(null, displayInfoText(), "Country Information", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon,options, null);
        addToWishlist(countryName);
    }

//method to get info about countries to display
    private void countryInformation(String countryName){
        String countryInfo = "";
        //exceptions to get fields and use it
        try{
            countryInfo = rcw.getAllCountries("name,capital,area,population,languages,flags,region,independent");
        } catch (Exception e){
            e.printStackTrace();
        }
        // arrays to split info array
        String[] countryInfoArr = countryInfo.split("\\{\"flags\"");
        // loops through array and get each country data
        for(String countryData: countryInfoArr){
            //conditional to only find the country info that we need
            if(countryData.contains("\"common\":"+ "\"" +countryName + "\"")){
                countryInfo = countryData;
                break;
            }
        }

        // display image via link
        // data types
        int linkStartIndex = countryInfo.indexOf("png") + 6;
        int linkEndIndex = countryInfo.lastIndexOf("png") + 3;
        imagePngLink = countryInfo.substring(linkStartIndex,linkEndIndex);

        // using variables concept introduced in unit 1 to store the correct values from the data
        // use method to get info
        commonName = Methods.extractInfo("\"common\":\"", "\"official\"", countryInfo);
        officialName = Methods.extractInfo("\"official\":\"", "\"nativeName\"",countryInfo );
        independence = Methods.extractInfo("\"independent\":", "capital", countryInfo);
        region = Methods.extractInfo("\"region\":\"", "\"area\"", countryInfo);
        area = Methods.extractInfo("\"area\":", "population\"", countryInfo );
        // get population
        int populationStartIndex = countryInfo.indexOf("\"population\"") + 13;
        int populationEndIndex = countryInfo.lastIndexOf("}");
        population = countryInfo.substring(populationStartIndex,populationEndIndex);
        capital = Methods.extractInfo("\"capital\":", ",\"region", countryInfo).isEmpty() ? "None" : Methods.extractInfo("\"capital\":[\"",",\"region\"", countryInfo );
        languages = Methods.extractInfo("\"languages\":", ",\"independent\"", countryInfo).isEmpty() ? "No native language" : getLanguages(commonName);



    }

    // recursive function to get country languages
    // this appends each country's language together and returns a string of the languages seperated by commas
    // methods / encapsulation of methods as they are only used within this class
    private String formatLanguage(String[] arr){
        if(arr.length == 1) return arr[0];
        String res = arr[0] + ", " + formatLanguage(Arrays.copyOfRange(arr,1,arr.length));
        return res;
    }

    // helper function to get languages into string
    private String getLanguages(String countryName){
        // lists to get language of country
        ArrayList<String> languages = countryLanguages.get(countryName);
        String[] arr = new String[languages.size()];
        arr = languages.toArray(arr);
        String countryLang = formatLanguage(arr);
        return countryLang;
    }

    // call method to add to wishlist
    private void addToWishlist(String countryName){
        // conditional to check the option selected if it was "add to wishlist then we add"
        if(option== 0){
            WishlistButton.updateList(countryName);
        }
    }



    // method to structure information and put into option pane
    // non primitive datatypes using strings
    public String displayInfoText(){
        return "Common Name: " + commonName + "\n" +
                "Official Name: " + officialName + "\n" +
                "Capital: " + capital + "\n" +
                "Languages: " + languages + "\n" +
                "Independence Status: " + independence + "\n" +
                "Region: " + region + "\n" +
                "Area: " + area + "\n" +
                "Population: " + population + "\n";
    }

    // get country flags
    // exceptions because reading image URL may return I/O exception
    private ImageIcon getFlag(String flagUrl){
        // reference on how to read image url - https://stackoverflow.com/a/23522335
        Image image = null;
        try{
            URL url = new URL(flagUrl);
            image = ImageIO.read(url);
        } catch (IOException e){
            // set to default  image if something has gone wrong to avoid errors if image has not been found
            image = Methods.getDefault();

        }

        // graphics / GUI to display image
        image = image.getScaledInstance(40,30,Image.SCALE_DEFAULT);
        ImageIcon iconImage = new ImageIcon(image);
        return iconImage;
    }



}
