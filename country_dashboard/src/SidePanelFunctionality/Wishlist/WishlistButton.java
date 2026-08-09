package SidePanelFunctionality.Wishlist;

import Main.MyMain;
import RestCountries.RestCountriesWrapper;
import Utils.Methods;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.TreeMap;

// this class demonstrates the following concepts:

// unit 1: objects methods
// unit 2: data types
// inheritance
// unit 4: arrays
// unit 5 exceptions
// unit 8 maps

// concepts have been described below

// class keeps track of wishlist and updates the wishlist as needed

// inheritance and GUI events by implementing action listener
public class WishlistButton extends JButton implements ActionListener {

    // prevent being able to double-click by keeping counter
    // this makes sure the travel wishlist is not refreshed when user is already on it
    public static int wishListCounter = 0;
    // keep public and static to keep track of what is on the wishlist each time we open the travel wishlist
    public static TreeMap<String, Image> wishListCountryNames = new TreeMap<>();

    public WishlistButton(String title){
        this.setText(title);
        this.addActionListener(this);
        this.setActionCommand("wishlist");
        this.setBorder(new EmptyBorder(0,5,5,0));
    }

    @Override
    // event listener GUI to place country in wishlist
    public void actionPerformed(ActionEvent e) {
        // clear main panel
        MyMain.frame.changeMainPanel(e.getActionCommand(), null);
    }


    // update the list whenever user adds to wishlist
    public static void updateList(String countryName){
        String data = "";
        // exceptions used to get data and image links to display
        RestCountriesWrapper rcw = new RestCountriesWrapper();
        try{    // rcw object
            data = rcw.getAllCountries("name,flags");
        } catch (Exception e){
            e.printStackTrace();
        }

        String countryInfo = Methods.getCountryData(data, countryName);
        String flagLink = Methods.getFlagLink(countryInfo);

        // use of image objects
        // exception to read  url and respond to error if image not found
        Image image = null;
        try{
            URL url = new URL(flagLink);
            image = ImageIO.read(url);
        } catch (IOException e){
            image = Methods.getDefault();
        }
        // conditional to check if the country is already there so we do not show the country twice
        if(!wishListCountryNames.containsKey(countryName)){
            wishListCountryNames.put(countryName,image);
        }

    }

}
