package Menu;

import Main.MyMain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// this class showcases the following concepts:

// unit 1: objects methods
// unit 2: data types, inheritance
// unit 3: encapsulation, conditionals , loops
// unit 7: abstraction and lists
// unit 8 : map polymorphism
// unit 9: GUI and events
// how these concepts have been used is explained in the code

// class to create panel with all the menu items
// create jpanel of menu items and bring to myframe class constructor
// inheritance of JFRAME to place menu bar
// implementing action listner interface to check GUI events interfaces unit 8 events unit 9

// action listener interface is also abstraction as it requires the methods a class must implement
//the action listener interface defines what each class that implements it should do by requiring these methods
public class MainMenuBar extends JFrame implements ActionListener {

    // encapsulation to ensure variables are only used within this class
    public JMenuBar menuBar;
    private JMenu currency;
    private JMenu language;
    private JMenu continent;
    private JMenu alphabet;
    // variable to place results
    // object instantiation of each menu object to place into frame
    CurrencyMenu currencyMenu = new CurrencyMenu();
    LanguageMenu languageMenu = new LanguageMenu();
    ContinentMenu continentMenu = new ContinentMenu();
    AlphabetMenu alphabetMenu = new AlphabetMenu();
    // use array list to keep results of countries needed to be displayed when user  clicks menu item
    ArrayList<String> countriesToDisplay;
    // public due to being called from different package in main frame to be displayed
    public MainMenuBar(){


        menuBar = new JMenuBar();
        this.currency = new JMenu("CURRENCY");
        this.language = new JMenu("LANGUAGE");
        this.continent = new JMenu("CONTINENT");
        this.alphabet = new JMenu("ALPHABET");
        // GUI COMPONENTS placed into frame and styled
        JLabel displayCountriesLabel = new JLabel("DISPLAY COUNTRIES BY:");
        displayCountriesLabel.setBorder(new EmptyBorder(10, 0, 10, 30));
        menuBar.add(displayCountriesLabel);
        menuBar.add(currency);
        menuBar.add(language);
        menuBar.add(continent);
        menuBar.add(alphabet);
        currency.setBorder(new EmptyBorder(10, 20, 10, 20));
        language.setBorder(new EmptyBorder(10, 20, 10, 20));
        continent.setBorder(new EmptyBorder(10, 20, 10, 20));
        alphabet.setBorder(new EmptyBorder(10, 20, 10, 20));
        addItems(currency,currencyMenu);
        addItems(language,languageMenu);
        addContinentItems();
        addAlphabetItems();

    }

    // helper method to generalise how i add items
    private  void  addItems (JMenu menuSec, Object menuInstance ) {
        // GUI: adding menu item components
        JMenu aToC = new JMenu("A - C");
        JMenu dToG = new JMenu("D - G");
        JMenu hToK = new JMenu("H - K");
        JMenu lToO = new JMenu("L - O");
        JMenu pToS = new JMenu("P - S");
        JMenu tToW = new JMenu("T - W");
        JMenu xToZ = new JMenu("X - Z");

        menuSec.add(aToC);
        menuSec.add(dToG);
        menuSec.add(hToK);
        menuSec.add(lToO);
        menuSec.add(pToS);
        menuSec.add(tToW);
        menuSec.add(xToZ);

        // objects: add items according the what type of object they are
        if (menuInstance instanceof CurrencyMenu) {
            for (String curr : currencyMenu.getData().keySet()) {
                //JMenuItem currencyItem = new JMenuItem(curr);
                char firstLetter = curr.charAt(0);
                JMenuItem currencyItem = new JMenuItem(curr);
                // add event listeners to all items
                currencyItem.addActionListener(this);
                currencyItem.setActionCommand("currency:" + curr);

                // put currency items in correct submenu
                if (firstLetter >= 65 && firstLetter <= 67) {
                    aToC.add(currencyItem);
                } else if (firstLetter >= 68 && firstLetter <= 71) {
                    dToG.add(currencyItem);
                } else if (firstLetter >= 72 && firstLetter <= 75) {
                    hToK.add(currencyItem);
                } else if (firstLetter >= 76 && firstLetter <= 79) {
                    lToO.add(currencyItem);
                } else if (firstLetter >= 80 && firstLetter <= 83) {
                    pToS.add(currencyItem);
                } else if (firstLetter >= 84 && firstLetter <= 87) {
                    tToW.add(currencyItem);
                } else {
                    xToZ.add(currencyItem);
                }
            }

        // objects
        } else if (menuInstance instanceof LanguageMenu) {
            for (String lang : languageMenu.getData().keySet()) {
                char firstLetter = lang.charAt(0);
                JMenuItem languageItem = new JMenuItem(lang);
                // add event listeners to all items
                languageItem.addActionListener(this);
                languageItem.setActionCommand("language:" + lang);

                // use conditional to put country in correct menu according to their first letter
                if (firstLetter >= 65 && firstLetter <= 67) {
                    aToC.add(languageItem);
                } else if (firstLetter >= 68 && firstLetter <= 71) {
                    dToG.add(languageItem);
                } else if (firstLetter >= 72 && firstLetter <= 75) {
                    hToK.add(languageItem);
                } else if (firstLetter >= 76 && firstLetter <= 79) {
                    lToO.add(languageItem);
                } else if (firstLetter >= 80 && firstLetter <= 83) {
                    pToS.add(languageItem);
                } else if (firstLetter >= 84 && firstLetter <= 87) {
                    tToW.add(languageItem);
                } else {
                    xToZ.add(languageItem);
                }
            }
        }
    }

    // methods , encapsulation of method only used within this class
    private void addContinentItems(){
        // loop to add the continents
            for (String curr : continentMenu.getData().keySet()){
                JMenuItem continentItem = new JMenuItem(curr);
                continentItem.addActionListener(this);
                continentItem.setActionCommand("continent:" + curr);
                continent.add(continentItem);
            }
    }

    private void addAlphabetItems(){
        // loops to add each letter to menu item
        for (String curr : alphabetMenu.getData().keySet()){
            JMenuItem alphabetItem = new JMenuItem();
            if(curr.equals("-")){ // special character
                alphabetItem = new JMenuItem("Other");
            } else {
                alphabetItem = new JMenuItem(curr);
            }
            alphabetItem.addActionListener(this);
            alphabetItem.setActionCommand("alphabet:" + curr);
            alphabet.add(alphabetItem);

        }
    }

    // GUI events to see what menu item was clicked and how to deal with that
    @Override
    public void actionPerformed(ActionEvent e) {
        // primitive data types int and string used to get index and source
        int endIndex = e.getActionCommand().indexOf(":");
        // find source
        String source = e.getActionCommand().substring(0,endIndex);
        String sourceValue = "";
        if(source.equals("currency")){
            sourceValue = e.getActionCommand().substring(endIndex+1);
            countriesToDisplay = currencyMenu.returnResults(sourceValue);
        } else if(source.equals("language")){
            sourceValue = e.getActionCommand().substring(endIndex+1);
            countriesToDisplay = languageMenu.returnResults(sourceValue);
        } else if(source.equals("continent")){
            sourceValue = e.getActionCommand().substring(endIndex+1);
            countriesToDisplay = continentMenu.returnResults(sourceValue);
        } else if(source.equals("alphabet")){
            sourceValue = e.getActionCommand().substring(endIndex+1);
            countriesToDisplay = alphabetMenu.returnResults(sourceValue);
        }
        MyMain.frame.changeMainPanel(source,sourceValue);
    }
}
