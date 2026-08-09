package QuizGame;

import RestCountries.RestCountriesWrapper;
import Utils.Methods;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// unit 1: objects and methods
// unit 2: data types
// unit 3: encapsulation conditionals loops
// unit 4: arrays
// unit 5: reading FILE I/0 and exceptions
// unit 6: lists
// unit 8: maps
// unit 9: GUI events

// class to generate questions
 class QuizQuestions {
    // maps
    // encapsulation to minimise coupling and ensure variables stay within class
    private RestCountriesWrapper rcw = new RestCountriesWrapper();
    // data types
    private String countryFlagsData = "";
    // maps: use of linked list to retain order and easy to add items without having to hard code the array size
    HashMap<String, LinkedList<String>> questionsAnswers = new HashMap<>();
    private ArrayList<String> fileQuestions = new ArrayList<>();
    protected ArrayList<String> questions = new ArrayList<>();

    QuizQuestions(){
        // exceptions to get data from rcw class
        try{
            this.countryFlagsData = rcw.getAllCountries("name,flags");
        } catch (Exception e) {
            e.printStackTrace();
        }
        readQuestions();
        formatQuestions();

    }


    // encapsulation to keep method only within this class
    // generate the questions from file
    private void readQuestions(){
        // reading file that contains the questions
        // create file object to contain file path
        File file = new File("src/QuizGame/QuizQuestions");
        // exception to catch file not found error
        try(Scanner reader = new Scanner(file)){
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                fileQuestions.add(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get questions and answers
    private void formatQuestions(){
        // loop to read and add file questions read from the file
        for(String questionData: fileQuestions){
            // separate questions from answers
            String question = questionData.substring(0,questionData.indexOf(":"));
            String[] options = questionData.substring(questionData.indexOf(":")+1).split(",");
            LinkedList<String> optionsList = new LinkedList<String>(Arrays.asList(options));
            questionsAnswers.put(question,optionsList);
            questions.add(question);
    }

    }



}
