package Tables;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// this class showcases the following concepts:

// unit 1: objects
// unit 2: inheritance
// unit 9: graphical user interfaces to create tables and components

// this is the panel to display ALL the tables
// inheritance of jpanel class
public class TablePanel extends JPanel {

    // Table1
    // encapsulation as i do not need this to be used outside this class
    // instantiate each table object
    private final PopulationTable populationTable = new PopulationTable("Highest Country Population");
    private final GeographicalTable geographicalTable = new GeographicalTable("Largest Geographic Sizes");
    private final LanguageTable languageTable = new LanguageTable("Most Spoken Languages");

    // public because it is used in main frame
    public TablePanel(){
        // use gui layout and add panel components
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(1500,300));
        this.setLayout(new GridLayout(0,3, 4, 4));
        // use of graphics to create line border
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackBorder);

        this.add(populationTable);
        this.add(geographicalTable);
        this.add(languageTable);

    }

}
