package com.company.UI.LeftSideBar;

import javax.swing.*;
import java.awt.*;

public class AboutUs extends JFrame {
    public AboutUs() {
        super("About Us ");
        setLayout(new GridLayout(0, 2, 5, 5));


        /**
         * some general details about program
         */
        //nameOfAuthor
        JLabel nameOfAuthorLabel = new JLabel("Name : ");
        JTextArea nameOfAuthor = new JTextArea();
        nameOfAuthor.setText("Arad Ashrafi");
        nameOfAuthor.setEditable(false);
        add(nameOfAuthorLabel);
        add(nameOfAuthor);

        //numberOfAuthor
        JLabel numberOfAuthorLabel = new JLabel("Student Number : ");
        JTextArea numberOfAuthor = new JTextArea();
        numberOfAuthor.setText("9434005");
        numberOfAuthor.setEditable(false);
        add(numberOfAuthorLabel);
        add(numberOfAuthor);

        //start date
        JLabel startDateLabel = new JLabel("Start Of Project : ");
        JTextArea startDate = new JTextArea();
        startDate.setText("2/14");
        startDate.setEditable(false);
        add(startDateLabel);
        add(startDate);

        //end date
        JLabel endDateLabel = new JLabel("End Of Project : ");
        JTextArea endDate = new JTextArea();
        endDate.setText("2/29");
        endDate.setEditable(false);
        add(endDateLabel);
        add(endDate);


        //how to work
        JLabel howToWorkLabel = new JLabel("How To Work : ");
        JTextArea howToWork = new JTextArea();
        howToWork.setText("it's quite simple :D ");
        howToWork.setEditable(false);
        add(howToWorkLabel);
        add(howToWork);


        pack();
        setVisible(true);
    }
}
