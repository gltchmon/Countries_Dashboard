package DisplayCountries;

import SidePanelFunctionality.Wishlist.CountryNotes;
import SidePanelFunctionality.Wishlist.WishlistButton;
import SidePanelFunctionality.Wishlist.WishlistCountryPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

// this class showcases the following concepts:

// unit 1: objects and methods
// unit 2: data types, inheritance
// unit 3: encapsulation, conditionals , loops
// unit 7: lists
// unit 9: GUI events

// class that defines layout of main panel and contains functions to change components of main panel
// inheritance by extending the JPanel component class
public class DisplayCountriesPanel extends JPanel {

    // encapsulation to keep variables private only to be used within this class
    private String state;
    private String value;
    WishlistCountryPanel countryPanel;
    public DisplayCountriesPanel(){
        this.state = state;
        this.value = value;
        this.setBackground(Color.white);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        // padding
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setPreferredSize(new Dimension(-5, 50));
        // display Europe countries when initialized
        countriesToDisplay("default",null);
    }


    // function to call correct function depending on menu item
    protected void countriesToDisplay(String state, String value){
        DisplayCountries displayCountries = new DisplayCountries();
        // array list to store the countries of the selected menu items
        ArrayList<CountryButton> result = new ArrayList<>();

        // conditionals used to check which menu item has been selected and calls appropriate function: unit 3
        if(state.equals("default")){
            this.removeAll();
            result = displayCountries.defaultCountries();
        } else if(state.equals("currency")){
            this.removeAll();
            result = displayCountries.currencyCountries(value) ;
        } else if(state.equals("language")){
            this.removeAll();
            result = displayCountries.languageCountries(value);
        } else if(state.equals("continent")) {
            this.removeAll();
            result = displayCountries.continentCountries(value);
        } else if(state.equals("search")){
            this.removeAll();
            result = displayCountries.getSearchedButton(value);
        } else {
            this.removeAll();
            result = displayCountries.alphabetCountries(value);
        }

        // if no results then change text
        if(result == null){
            JLabel noResults = new JLabel("No results...");
            noResults.setFont(new Font("Arial", Font.ITALIC,20));
            noResults.setForeground(Color.gray);
            this.add(noResults);
            this.revalidate();
            this.repaint();
        }else{
            // create button for each country with loops: GUI to add components , loops
            for(CountryButton country: result){
                this.add(country);
                this.revalidate();
                this.repaint();
            }
        }


    }

    // display the wishlist
    protected void displayWishList(){
        this.removeAll();
        // loops to create a panel for each country in the wishlist and display it with buttons
        for(String country: WishlistButton.wishListCountryNames.keySet()){
            // place country panel
            countryPanel = new WishlistCountryPanel(country, WishlistButton.wishListCountryNames.get(country));
            this.add(countryPanel);
        }

    }

    // method to remove country from wishList and remove the notes
    protected void removeCountryFromWishlist(String countryName){
        for(Component component: this.getComponents()){
            if(component instanceof WishlistCountryPanel){
                if(((WishlistCountryPanel) component).countryNameItem.equals(countryName)){
                    this.remove(component);
                    this.revalidate();
                    this.repaint();
                    CountryNotes.countryNotes.remove(countryName);
                }

            }
        }
    }


    // method to change button of specific country use of loop and conditions
    public void editButtonInfo(String countryName){
        for(Component component: this.getComponents()){
            if(component instanceof WishlistCountryPanel){
                if(((WishlistCountryPanel) component).countryNameItem.equals(countryName)){
                    ((WishlistCountryPanel) component).changeButton();
                }

            }
        }
    }


}
