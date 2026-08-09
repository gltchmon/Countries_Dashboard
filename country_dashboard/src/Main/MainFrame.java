package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import Tables.TablePanel;
import DisplayCountries.MainPanel;
import Menu.MainMenuBar;
import SidePanelFunctionality.OtherFunctionalityPanel;

// this class demonstrates the following concepts

// unit 1: objects and methods:
/*this class includes many objects that are used to instantiate the panels to put into the frame and other components*/
// unit 2: inheritance
// unit 3: encapsulation
// unit 9: GUI events

// how these concepts have been used is described in the code below
// class to create main frame

// GUI unit 9 and creating frames but was introduced in UNIT 2
// inheriting JFRAME class to inherit the methods and variables needed to create a jframe
// implementing interface (unit 8) windowstate listener to change size of main panel according to window state
public class MainFrame extends JFrame implements WindowStateListener {
    // encapsulation some only need to be used within this class
    private JMenuBar menuBar;
    public MainPanel mainPanel;
    public JPanel container;

    MainFrame() {
        this.setTitle("My Countries");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowStateListener(this);
        // creating menu
        // objects creating the menu bar object to add to frame
        MainMenuBar menuB = new MainMenuBar();
        menuBar = menuB.menuBar;
        this.setJMenuBar(menuBar);
        // layout : GUI , used flow layout to place things side by side
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));

        // make scrollPane: GUI
        // make container scrollable
        container = new JPanel();
        JScrollPane scrollContainer = new JScrollPane(container);
        getContentPane().add(scrollContainer);
        scrollContainer.getVerticalScrollBar().setUnitIncrement(50);
        container.setLayout(new BorderLayout(10, 10));

        // outer container of graphs panel
        JPanel graphsOuterContainer = new JPanel();
        graphsOuterContainer.setPreferredSize(new Dimension(100, 400));
        graphsOuterContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
        container.add(graphsOuterContainer, BorderLayout.NORTH);

        // panel containing graphs
        JPanel graphs = new TablePanel();
        graphsOuterContainer.add(graphs);

        // side panel
        JPanel sidePanel = new JPanel();
        Dimension sideDimension = new Dimension();
        sideDimension.width = 150;
        sidePanel.setPreferredSize(sideDimension);
        container.add(sidePanel, BorderLayout.WEST);
        OtherFunctionalityPanel otherFunctionalityPanel = new OtherFunctionalityPanel();
        sidePanel.add(otherFunctionalityPanel);

        // container for country buttons
        this.mainPanel = new MainPanel();
        container.add(mainPanel, BorderLayout.CENTER);


        this.pack();
        this.setVisible(true);
    }

    // method to update panel based on menu bar
    // gui events
    public void changeMainPanel(String state, String value) {
        MainPanel panel = this.mainPanel;
        // using invoke later to ensure GUI updates are made in the same event dispatching thread instead of the possibility that events may occur in different threads and decreases chance of errors
        // also helpful in case i have any background events running while loading something else
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                panel.changePanelContent(state, value);
            }
        });

        this.repaint();
    }


    @Override
    public void windowStateChanged(WindowEvent e) {
        // data types
        int state = e.getNewState();
        // check window state to see if panel needs to be made bigger or smaller
        if (state == 0) {
            this.mainPanel.setPreferredSize(new Dimension(-4,8000));
        } else{
            this.mainPanel.setPreferredSize(new Dimension(-4,700));
        }
    }

}
