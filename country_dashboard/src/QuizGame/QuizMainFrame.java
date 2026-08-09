package QuizGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

// class uses concepts:
// unit 1: objects and methods
// unit 2: data types and inheritance
// unit 9: GUI events

// uses of these concepts are explained below


// implementing window listener interface to check if window has been closed
// interfaces were introduced in unit 9 as concept of abstraction and polymorphism
// integrating window listener interface into my own class to define the implementation of the interface
public class QuizMainFrame extends JFrame implements WindowListener {
    QuizQuestions quiz;
    // if user has launched the state is 1;
    // data type
    public static int quizWindowState = 0;
    public QuizMainFrame(){
        QuizPanel quizPanel = new QuizPanel();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // use grid bag layout to position items in the center
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill= GridBagConstraints.HORIZONTAL;
        this.setPreferredSize(new Dimension(820,640));
        this.setVisible(true);
        this.add(quizPanel);
        this.pack();
        this.addWindowListener(this);
    }



    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    // allows users to open quiz frame again if closed but cannot open more than once
    @Override
    public void windowClosed(WindowEvent e) {
        quizWindowState = 0;
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
