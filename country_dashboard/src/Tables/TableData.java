package Tables;
import RestCountries.RestCountriesWrapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// this class showcases the following concepts:

// unit 1: objects methods
// unit 2: data types, inheritance
// unit 3: encapsulation
// unit 4: arrays
// unit 7: abstraction , abstract classes
// unit 9: GUI

/*this is the abstract class for each of the tables here i have defined the variables and methods the classes should use
* Interface was not used for this because there is one method with a predefined body that all classes need to inherit
* however all other abstract methods will differ depending on how they are implemented (e.g., different tables)
* this way i am able to eliminate having to copy the make table method throughout all my classes
* and i can predefine how i want each of my tables to act e.g., calling the methods in the correct order to avoid errors
* and define the data for each of my tables
* inheritance of jpanel is also used because each table is created on a jpanel */

abstract class TableData extends JPanel{

    // unit 3: encapsulation to allow child panels to use these variables
    protected String[] columnNames;
    // multidimensional array used to pass the data into the Jtable
    protected Object[][] data;
    protected String title;
    protected JTable table;
    // arrays to store the countries / languages NAMES and use as reference to display data
    protected String [] resultsKey;
    protected RestCountriesWrapper rcw;

    // encapsulation as each class only implemented within package
    // create table and styles
    protected TableData(String title){
        // initialise variables
        // this constructor defines how each method will be called and how the tables will be styled
        this.title = title;
        this.setLayout(new BorderLayout(10,10));
        this.setBackground(Color.white);
        rcw = new RestCountriesWrapper();
        this.setBorder(new EmptyBorder(5,5,5,5));
        getData();
        JLabel barTitle = new JLabel(title, JLabel.CENTER);
        columnNames = setColumnNames();
        data = setData();
        table = makeTable();
        JScrollPane tableScroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(barTitle, BorderLayout.NORTH);
        this.add(tableScroll,BorderLayout.CENTER);
    }

    // encapsulation
    // use data to put into table
    // use of multi dimensional array because they are needed to create a jtable
    // methods without body defined as abstract because they are not concrete and will differ from table
    protected abstract Object[][] setData();

    protected abstract String[] setColumnNames();

    // get data
    protected abstract void getData();

    // make table method is the same throughout all my classes
    protected JTable makeTable(){
        // GUI component used
        JTable table = new JTable(data,columnNames);
        table.setRowHeight(table.getRowHeight()+8);
        return table;
    }

}