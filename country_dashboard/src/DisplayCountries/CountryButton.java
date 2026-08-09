package DisplayCountries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// this class showcases the following concepts:

// unit 1: objects methods
// unit 2: data types, inheritance
// unit 9: GUI and events
// unit 8: interfaces
// how these concepts have been used is explained in the code

// class to create a button for each country
// implementing action listener to check for button click: GUI AND EVENTS
// inheritance of JBUTTON class implementing action listener interface
class CountryButton extends JButton implements ActionListener {
    // encapsulation variable only used within class
    // using string datatype because it is the country name...
    private String countryName;

    public CountryButton(String countryName){
        this.countryName = countryName;
        this.setText(countryName);
        this.setFont(new Font("Arial", Font.BOLD,13));
        this.setPreferredSize(new Dimension(200,50));
        this.setFocusable(false);
        this.setMaximumSize(this.getPreferredSize());
        this.addActionListener(this);
        this.setActionCommand(countryName);
        this.setBackground(new Color(227, 227, 227));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    }

    @Override
    // using GUI events to check if country has been clicked to show the modal
    public void actionPerformed(ActionEvent e) {
        // create pop up panel GUI
        String countryName = e.getActionCommand();
        // instantiate country modal when clicked on country to display information of the country
        CountryInfoModal dialog = new CountryInfoModal(countryName);
    }
}
