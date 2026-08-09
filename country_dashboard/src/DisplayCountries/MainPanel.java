package DisplayCountries;

import SidePanelFunctionality.Wishlist.WishlistButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// this class showcases the following concepts: concepts explained in code

// unit 1: objects methods
// unit 2:  inheritance
// unit 3: encapsulation, conditionals
// unit 9: GUI

// main panel includes countries title being displayed + PANEL containing countries buttons
// inheritance of JPANEL class to style and respond to events

public class MainPanel extends JPanel {
    // object instantiation
    DisplayCountriesPanel displayCountriesPanel;

    JLabel displayText;
    public MainPanel(){
        // GUI/GRAPHICS: SETTING LAYOUTS and displaying labels
        this.setLayout(new BorderLayout(10,10));
        this.setBorder(new EmptyBorder(10,10,10,50));
        // add label
        displayText = new JLabel("COUNTRIES: Europe");
        displayText.setFont(new Font("Arial", Font.BOLD, 28));
        this.add(displayText, BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(-5,700));
        // object of displayCountriesPanel panel initialized so that we can display the countries
        this.displayCountriesPanel = new DisplayCountriesPanel();
        // gui adding scroll pane to the panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(displayCountriesPanel);
        this.add(scrollPane, BorderLayout.CENTER);
    }


    // function to change the main panel content
    public void changePanelContent(String state, String value){
        // conditionals to check the state of what the panel content should be and the value (usually country name) of the country
        if(state.equals("wishlist")){
            if(value == null && WishlistButton.wishListCounter <= 1) {
                    displayCountriesPanel.displayWishList();
                    this.displayText.setText("TRAVEL WISHLIST");

            } else if(value != null && value.contains("edit:")){
                    displayCountriesPanel.editButtonInfo(value.substring(value.indexOf(":")+1));
            } else{
                    displayCountriesPanel.removeCountryFromWishlist(value);
            }
        } else{
            displayCountriesPanel.countriesToDisplay(state, value);
            WishlistButton.wishListCounter = 0;
            this.displayText.setText("COUNTRIES: "+ value);
            if(value.equals("English")){
                this.setPreferredSize(new Dimension(-5,1080));
                revalidate();
                repaint();
            } else {
                this.setPreferredSize(new Dimension(-5,700));
                revalidate();
                repaint();
            }
        }

    }
}
