package SidePanelFunctionality.Wishlist;

import DisplayCountries.DisplayCountriesPanel;
import Main.MyMain;
import RestCountries.RestCountriesWrapper;
import Utils.Methods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

// concepts used within this class:
// unit 1: objects and methods variables constructors
// unit 2: data types and inheritance
// unit 3: encapsulation and conditionals
// unit 4: arrays
// unit 7: lists abstraction
// unit8: maps
// unit 9: GUI events
// how these concepts have been used is described below

// class to display and edit the country notes in wishlist
// inheriting Jpanel class to add components and implementing mouse listener interface
// uses abstraction because implementing mouse listener interface enforces how this class should behave with the methods required. Users themselves enforce behaviour by writing method body
public class CountryNotes extends JPanel implements MouseListener {
    // encapsulation of variables so they are only used within this class
    private RestCountriesWrapper rcw;
    private HashMap<String, String> countryMaps = new HashMap<>();
    private String countryName;
    private JLabel link;
    private String countryStatus;
    private String tripType;
    private String dateVisited;
    private String comment;
    // use of GUI components and string data type
    private JCheckBox visited;
    private JCheckBox plan;
    private JComboBox<String> tripTypeSelection;
    private JTextField dateInput;
    private JTextArea commentInput;
    private int userInputPane;
    private int displayNotesPane;
    // use of map to get country notes and country
    // static , public encapsulation to allow the wishlist class to check the state without having to instantiate this class and render the panel as needed
    // linked list to retain order and eliminate need to hard code array size
    public static HashMap<String, LinkedList<String>> countryNotes = new HashMap<>();

    // protected encapsulation only to be used within same package
    // constructor to define layout
    protected CountryNotes(String countryName){
        this.countryName = countryName;
        // instantiate rcw object
        rcw = new RestCountriesWrapper();
        // GUI layouts
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        getMaps();

        // if it is not in the hashmap then display the input pane otherwise display notes
        // conditional to check if country contains notes
        if(!(countryNotes.containsKey(countryName))){
            displayDefaultPane();
        } else{
            viewCountryInfo();
            String[] paneOptions = {"EDIT", "OK"};
            displayNotesPane = JOptionPane.showOptionDialog(null,this,"Add notes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,paneOptions,null);
            if(displayNotesPane == 0){
                // remove previous option pane to display the user input
                this.removeAll();
                displayDefaultPane();
                this.repaint();
                this.revalidate();
            }
        }

    }


    // get map links of countries
    // muse of methods to get the link of maps for each country
    private void getMaps(){
        String data = "";
        // exception to get data
        try{
            data = rcw.getAllCountries("name,maps");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // array to split data
        String[] dataArr = data.split("\\{\"name\"");
        String countryName = "";
        String countryMap = "";
        // loop to extract the map and country named
        for(String countryData: dataArr){
            if(!countryData.equals("[")){
                countryName = Methods.extractInfo("common\":\"", "\"official\"", countryData);
                countryMap = Methods.extractInfo("\"googleMaps\":\"", "\"openStreetMaps\"", countryData );
                countryMaps.put(countryName,countryMap);
            }

        }

    }

    // place notes into hashmap
    // can leave things blank
    // method to update hashmap of notes
    private void setNotes(){
        LinkedList <String> notes = new LinkedList<>();
        if(plan.isSelected() || visited.isSelected()){
            countryStatus = plan.isSelected() ? "Plan to go" : "Visited";
        }
        // use require non null to deal with any errors that may occur in case it is null
        tripType = Objects.requireNonNull(tripTypeSelection.getSelectedItem(),"Trip type cannot be null").toString();
        dateVisited = dateInput.getText();
        comment = commentInput.getText();
        notes.add(countryStatus);
        notes.add(tripType);
        notes.add(dateVisited);
        notes.add(comment);
        countryNotes.put(countryName,notes);
        // change button to say display notes instead of edit because now the country has notes so we need to refresh
        MyMain.frame.changeMainPanel("wishlist","edit:"+countryName);

    }

    // call method to display input and set notes into hashmap
    private void displayDefaultPane(){
        defaultCountryRender();
        // use of conditionals to check the option and GUI to display a joption pane
        userInputPane = JOptionPane.showConfirmDialog(null,this,"Add notes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(userInputPane == 0){
            setNotes();
        }
    }

    // display user inputs and text fields on travel wishlist
    private void defaultCountryRender(){
        // add checkboxes
        JPanel radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,3,3));
        visited = new JCheckBox("Visited");
        plan = new JCheckBox("Plan to go");
        ButtonGroup group = new ButtonGroup();
        group.add(visited);
        group.add(plan);
        radioButtonPanel.add(visited);
        radioButtonPanel.add(plan);
        this.add(radioButtonPanel);

        // add drop down
        JPanel tripTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,3,3));
        String[] tripTypeOptions = {"Leisure", "Family", "Business", "School"};
        tripTypeSelection = new JComboBox<>(tripTypeOptions);
        tripTypePanel.add(new JLabel("Type of trip: "));
        tripTypePanel.add(tripTypeSelection);
        this.add(tripTypePanel);

        // add date
        JPanel dateVisitedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,3,3));
        dateVisitedPanel.add(new JLabel("Date visited / Plan to visit: "));
        dateInput = new JTextField();
        dateInput.setPreferredSize(new Dimension(120,20));
        dateVisitedPanel.add(dateInput);
        this.add(dateVisitedPanel);

        // add link to maps
        JPanel countryMapPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,3,3));
        countryMapPanel.add(new JLabel("Country Map: "));
        String countryLink = countryMaps.get(countryName);
        link = new JLabel(countryName + " Map");
        link.addMouseListener(this);
        countryMapPanel.add(link);
        this.add(countryMapPanel);

        // comment
        JPanel commentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,3,3));
        commentPanel.add(new JLabel("Additional Information: "));
        commentInput = new JTextArea(5,30);
        commentPanel.add(commentInput);
        this.add(commentPanel);
    }

    // display the information typed
    private void viewCountryInfo(){
        LinkedList<String> data = countryNotes.get(countryName);
        this.add(new JLabel("Country Status: "+ (data.getFirst() == null ? "Not selected" : data.getFirst())));
        this.add(new JLabel("Trip Type: "+ data.get(1)));
        this.add(new JLabel("Date Visited/Plan to Visit: "+ (data.get(2).isEmpty() ? "Not entered" : data.get(2))));
        this.add(new JLabel("Comment: "+(data.get(3).isEmpty() ? "Not entered": data.get(3))));
        link = new JLabel(countryName + " Map");
        link.addMouseListener(this);
        this.add(link);
    }


    @Override
    // GUI event to highlight text if hovered
    public void mouseClicked(MouseEvent e) {
        // reference to open link when clicked: https://www.codejava.net/java-se/swing/how-to-create-hyperlink-with-jlabel-in-java-swing
        // exception to get link of map if not found then we will display option pane to say country not found
        String countryLink = countryMaps.get(countryName);
        try {
            Desktop.getDesktop().browse(new URI(countryLink));
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showMessageDialog(null, "Could not find map.");
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    // highlight link on hover
    @Override
    public void mouseEntered(MouseEvent e) {
        link.setForeground(Color.blue);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        link.setForeground(Color.black);
    }
}
