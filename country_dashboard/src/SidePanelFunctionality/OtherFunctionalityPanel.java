package SidePanelFunctionality;

import Main.MyMain;
import QuizGame.QuizMainFrame;
import SidePanelFunctionality.Wishlist.WishlistButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class demonstrates concepts such as:

//unit 1: methods control statements, objects variables
// unit 2: inheritance
// unit 3: encapsulation conditionals
// unit 9: GUI events
// concepts are described below

// inheritance of jpanel and action listener classes/interfaces
// class to display more functionality buttons of application in panel: quiz, wishlist, search etc...
public class OtherFunctionalityPanel extends JPanel implements ActionListener {
    // variable is the object instantiation of wishlist button
    WishlistButton wishlistButton;
    // object instantiation of quiz main frame
    // static to allow frame to be closed/opened from the main frame or when pressing exit
    public static QuizMainFrame quizMainFrame;

    // side panel
    public OtherFunctionalityPanel(){
        // GUI/ Graphics to display and add styles (e.g., bg colour and fonts) to this panel
        this.setLayout(new GridLayout(5,0, 5,10));
        this.setBorder(new EmptyBorder(5,5,5,5));
        this.setBackground(new Color(202, 202, 202, 179));
        JLabel title = new JLabel("MORE", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        JButton home = new JButton("HOME");
        home.addActionListener(this);
        home.setActionCommand("default");
        home.setBorder(new EmptyBorder(0,5,5,5));
        JButton game = new JButton("QUIZ GAME");
        game.setBorder(new EmptyBorder(0,5,5,0));
        game.addActionListener(this);
        game.setActionCommand("quiz");
        JButton search = new JButton("SEARCH FOR COUNTRY");
        search.addActionListener(this);
        search.setActionCommand("search");
        search.setBorder(new EmptyBorder(0,5,5,5));
        wishlistButton = new WishlistButton("TRAVEL WISHLIST");
        wishlistButton.addActionListener(this);
        wishlistButton.setActionCommand("wishlist");
        home.setPreferredSize(new Dimension(home.getWidth(), 40));
        buttonStyling(home);
        buttonStyling(wishlistButton);
        buttonStyling(game);
        buttonStyling(search);

        this.add(title);
        this.add(home);
        this.add(wishlistButton);
        this.add(game);
        this.add(search);
    }

    // unit 9: GUI events to check for button actions // methods
    @Override
    public void actionPerformed(ActionEvent e) {
        // conditionals to check which part  was clicked and display according to button
        if(e.getActionCommand().equals("default")){
            // if the action was not to check the travel wishlist change its state to 0 so that it can be pressed again
            WishlistButton.wishListCounter = 0;
            MyMain.frame.changeMainPanel("default", "Europe");
        } else if(e.getActionCommand().equals("search")){
            //WishlistButton.wishListCounter = 0;
            SearchCountryDialog countryDialog = new SearchCountryDialog();
        } else if(e.getActionCommand().equals("wishlist")){
            WishlistButton.wishListCounter += 1;
        } else if(e.getActionCommand().equals("quiz") && QuizMainFrame.quizWindowState == 0){
            // instantiation of quiz frame object
            OtherFunctionalityPanel.quizMainFrame = new QuizMainFrame();
            QuizMainFrame.quizWindowState = 1;
        } else if(e.getActionCommand().equals("quiz") && QuizMainFrame.quizWindowState == 1){
            JOptionPane.showMessageDialog(null,"Quiz is already running!","Quiz", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // unit 1 : methods
    // encapsulation so that methods are only to be used within this class
    private void buttonStyling(JButton button){
        // styling button: GUI
        button.setBackground(new Color(0,0,0));
        button.setForeground(Color.white);
        button.setFocusable(false);
    }
}
