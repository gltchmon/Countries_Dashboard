package QuizGame;

import SidePanelFunctionality.OtherFunctionalityPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

// This class demonstrates the following concepts:
// unit 1: objects and methods
// unit 2: datatypes and inheritance
// unit 3: encapsulation, conditionals , loops
// unit 4: arrays
// unit 7: lists
// unit 9: GUI events
// uses of these concepts described below

// inheritance of Jpanel and implementing action listener interface
class QuizPanel extends JPanel implements ActionListener {
    // instantiate object of quiz questions to get quiz questions
    protected QuizQuestions quiz = new QuizQuestions();
    // private variables: encapsulation minimise coupling and only use within this class
    // primitive int datatype
    private int questionNo = 0;
    private int score = 0;

    // encapsulation only allow class to be used within the same package
    protected QuizPanel(){
        // creating GUI panel components
        quiz = new QuizQuestions();
        // using layout (GUI) to position components in the center
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        JLabel title = new JLabel("COUNTRY DATA QUIZ");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBorder(new EmptyBorder(0,0,5,0));
        JLabel subtitle = new JLabel("Test your knowledge about countries around the world!");
        JLabel subtitle2 = new JLabel("It is recommended that you study the information displayed before playing.");
        subtitle2.setForeground(new Color(225, 93, 93));
        subtitle2.setBorder(new EmptyBorder(2,0,10,0));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton start = new JButton("START QUIZ");
        start.addActionListener(this);
        start.setActionCommand("start");
        JButton close = new JButton("EXIT");
        close.setActionCommand("exit");
        close.addActionListener(this);
        buttonStyles(start);
        buttonStyles(close);

        buttonsPanel.add(start);
        buttonsPanel.add(close);

        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonsPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle2.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
        this.add(subtitle);
        this.add(subtitle2);
        //this.add(titlePanel);
        this.add(buttonsPanel);

    }

    // start and display questions
    // method to start the game
    private void startGame(int score,int questionNo){
        // initialise quiz questions here to produce new questions
        this.removeAll();
        // conditional to check if we are at the end of quiz questions
        if(questionNo >= quiz.questions.size()){
            gameEnd();
            return;// call function to display score and end game
        }

        // use of array lists to get the quiz questions
        ArrayList<String> questions = quiz.questions;
        // use of linked list to keep same data type used in quiz questions class
        LinkedList<String> options = quiz.questionsAnswers.get(questions.get(questionNo));
        JLabel question = new JLabel(questions.get(questionNo));
        question.setFont(new Font("Arial", Font.BOLD, 30));
        question.setBorder(new EmptyBorder(0,0,10,0));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        // add progress bar
        // increase width every time you go to next question
        // divided width of progress bar by 8 to get an increase of 87 per question
        ProgressBar progressBar = new ProgressBar(  87*questionNo);
        this.add(progressBar);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressBar.setAlignmentY(Component.CENTER_ALIGNMENT);


        this.add(question);
        makeButtons(options,questions.get(questionNo));
        this.repaint();
        this.revalidate();

    }

    // method to detect game end
    private void gameEnd(){
        JLabel scoreText = new JLabel("Game has ended your score was: " + score + "/" + quiz.questions.size());
        scoreText.setFont(new Font("Arial", Font.BOLD, 30));
        scoreText.setBorder(new EmptyBorder(0,0,4,0));
        scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(scoreText);
        JButton restart = new JButton("Play Again");
        restart.addActionListener(this);
        restart.setActionCommand("restart");
        JButton close = new JButton("Exit");
        buttonStyles(restart);
        buttonStyles(close);
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(restart);
        buttons.add(close);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        close.addActionListener(this);
        close.setActionCommand("exit");
        this.add(buttons);
        this.repaint();
        this.revalidate();
    }

    // make buttons from options
    private void makeButtons(LinkedList<String> options,String question){
        // use array list to create copy to not change original
        ArrayList<String> optionsCopy = new ArrayList<>(5);
        optionsCopy.addAll(options);
        // shuffle so buttons are in different order each time
        Collections.shuffle(optionsCopy);
        JPanel buttons = new JPanel();
        // use of layouts from GUI
        buttons.setLayout(new GridLayout(2,2,5,5));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        // loops to create button for each option and add action command on what the question is
        for(String option: optionsCopy){
            JButton optionButton = new JButton(option);
            buttonStyles(optionButton);
            optionButton.addActionListener(this);
            optionButton.setActionCommand("question:"+question+"option:"+option);
            buttons.add(optionButton);
        }
        this.add(buttons);
    }

    // display result when answered
    private void displayPopUp(int result, String answer){
        // data types
        int option = 0;
        // use of string array to add option to Joption pane
        String[] next = {"Next"};
        if(result == 1){
            score ++;
           option = JOptionPane.showOptionDialog(null, "That is correct!", "Answer result", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,next, null);
        } else{
            option = JOptionPane.showOptionDialog(null, "Incorrect! The correct answer was: "+answer,"Answer result",  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,next, null);
        }
        if(option <= 0){
            questionNo++;
            startGame(score,questionNo);
        }

    }

    // add button decorations: GUI unit 9
    private void buttonStyles(JButton button){
        button.setPreferredSize(new Dimension(190,80));
        button.setFocusable(false);
        button.setBackground(new Color(218, 218, 218));
        button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        button.setFont(new Font("Arial", Font.BOLD,20));
    }

    // GUI EVENTS check if user has started / restarted of exited game
    @Override
    public void actionPerformed(ActionEvent e) {
        // set score to 0 and question no back to 0 when game has started or restarted
        if(e.getActionCommand().equals("start") || e.getActionCommand().equals("restart")){
            startGame(score=0,questionNo=0);
            // get question and option picked see if it matches
        } else if(e.getActionCommand().contains("question")){
            String command = e.getActionCommand();
            String question = command.substring(command.indexOf(":")+1, command.indexOf("option"));
            LinkedList<String> options = quiz.questionsAnswers.get(question);
            if(command.substring(command.lastIndexOf(":")+1).equals(options.getFirst())){
                displayPopUp(1,options.getFirst());
            } else {
                displayPopUp(0,options.getFirst());
            }
        } else if(e.getActionCommand().equals("exit")){
            // close window if exited
            OtherFunctionalityPanel.quizMainFrame.dispatchEvent(new WindowEvent(OtherFunctionalityPanel.quizMainFrame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
