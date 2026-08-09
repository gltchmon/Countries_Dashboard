package Main;

import DisplayCountries.MainPanel;
import RestCountries.RestCountriesWrapper;

import javax.swing.*;
// class contains main method as entry to program : Unit 1 topic
public class MyMain {

    // class contains frame component first introduced in unit 6 and further explored in unit 9 GUI
    public static MainFrame frame = new MainFrame();

    // main function as main entry point of program introduced in unit 1
    public static void main(String[] args) throws Exception{
        // using invoke later we can make sure only the event thread runs all GUI operations including frame instantiation
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyMain();
            }
        });

    }
}
