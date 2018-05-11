package com.company;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private BorderLayout UILayout;
    private Toolbar toolbar;
    private LeftSideBar leftSideBar;
    private Body body;

    public UI() {
        super("UI");
        UILayout = new BorderLayout();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setSize(600, 600);

        /**
         *adding components to UI -- first leftSideBar
         */

        leftSideBar = new LeftSideBar();
        toolbar = new Toolbar();
        body = new Body();

        setLayout(UILayout);
        add(leftSideBar, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);


        pack();
        setVisible(true);


//        UILayout.putConstraint(SpringLayout.WEST,toolbar,);


    }

}
