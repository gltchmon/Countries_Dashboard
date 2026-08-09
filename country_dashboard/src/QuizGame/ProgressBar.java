package QuizGame;

import javax.swing.*;
import java.awt.*;


//unit 1: objects and methods constructors
// unit 2: data types and inheritance
// unit 6: 2D graphics
// unit 9: GUI

// inheritance of JPanel class to create the progress bar
class ProgressBar extends JPanel {
    // primitive data type of width
    int width = 0;
    ProgressBar( int width){
        this.width = width;
        // Use of GUI layout to position progress bar
        this.setPreferredSize(new Dimension(700,60));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    //using 2D graphics to create a progress bar in quiz game
    @Override
    public void paint(Graphics g) {
        // cast g to graphics 2D to see other functionality and methods
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(Color.black);
        g2D.setStroke(new BasicStroke(2));
        g2D.drawRect(0,0,700,30);
        g2D.fillRect(0,0,width,30);


    }
}
