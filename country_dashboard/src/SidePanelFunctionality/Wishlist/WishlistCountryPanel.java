package SidePanelFunctionality.Wishlist;

import Main.MyMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;


// class demonstrates the following concepts:

// unit 1: objects, methods
// unit 2: data types
// unit 7: abstraction
// unit 9: GUI EVENTS , unit 2: INHERITANCE

//concepts are described in code

// create a panel for each country that is added to wishList
// inheritance of Jpanel class implementation of action listener interface serves as abstraction to define how class should behave when implementing this interface
public class WishlistCountryPanel extends JPanel implements ActionListener {
    // encapsulation of variables depending on how they need to be used
    public String countryNameItem;
    protected JButton addData;
    CountryNotes countryNotesPanel;

    // create panel
    // use of string non primitive data type
    public WishlistCountryPanel(String name, Image countryImage){
        // define class variables unit 1
        this.countryNameItem = name;
        // GUI layouts and styling
        this.setLayout(new BorderLayout(5,5));
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(200, 200));
        // country button
        JLabel countryName = new JLabel(name, JLabel.CENTER);
        countryName.setFont(new Font("Arial", Font.BOLD, 15));
        countryName.setBackground(Color.white);
        countryImage.getScaledInstance(50,50,Image.SCALE_DEFAULT);
        // get image
        JLabel flag = new JLabel(new ImageIcon(countryImage));
        flag.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        // remove button styling
        JButton removeCountry = new JButton("REMOVE FROM LIST");
        removeCountry.setPreferredSize(new Dimension(removeCountry.getWidth(), 30));
        removeCountry.setBackground(new Color(216, 214, 214));
        removeCountry.setFocusable(false);
        removeCountry.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        removeCountry.addActionListener(this);
        removeCountry.setActionCommand(name);

       // every time we render the wishlist we need to check if it contains notes in the hashmap using conditional
        if(CountryNotes.countryNotes.containsKey(name)){
            addData = new JButton("DISPLAY NOTES");
        } else{
            addData = new JButton("ADD NOTES");
        }
        addData.setActionCommand("data:"+name);
        addData.addActionListener(this);
        JPanel buttons = new JPanel(new BorderLayout(0,3));
        addData.setPreferredSize(new Dimension(this.getWidth(), 30));
        addData.setBackground(new Color(216, 214, 214));
        addData.setFocusable(false);
        addData.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttons.add(addData, BorderLayout.NORTH);
        buttons.add(removeCountry, BorderLayout.SOUTH);

        // add data button
        this.add(countryName, BorderLayout.NORTH);
        this.add(flag, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
    }

    @Override
    // use of GUI events to check buttons clicked and how to respond
    public void actionPerformed(ActionEvent e) {
        // add or see notes
        if (e.getActionCommand().contains("data")) {
            String countryName = e.getActionCommand().substring(e.getActionCommand().indexOf(":") + 1);
            // instantiate the country panel as needed
            countryNotesPanel = new CountryNotes(countryName);
        } else{
            // remove country from wishlist
            WishlistButton.wishListCountryNames.remove(e.getActionCommand());
            MyMain.frame.changeMainPanel("wishlist", e.getActionCommand());
        }

    }

    // change button once we have placed data
    public void changeButton(){
        addData.setText("DISPLAY NOTES");
        repaint();
        revalidate();
    }

}
