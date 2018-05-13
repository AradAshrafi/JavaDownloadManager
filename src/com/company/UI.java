package com.company;

import com.company.BetweenClassesRelation.DownloadItemsConnection;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class UI extends JFrame implements DownloadItemsConnection {
    private BorderLayout UILayout;
    private Toolbar toolbar;
    private LeftSideBar leftSideBar;
    private Body body;
    private HashSet<DownloadItem> selectedItems;


    public UI() {
        super("UI");
        UILayout = new BorderLayout();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        selectedItems = new HashSet<>();
//        setSize(600, 600);

        /**
         *adding components to UI -- first leftSideBar
         */

        leftSideBar = new LeftSideBar(this);
        toolbar = new Toolbar(this);
        body = new Body(this);

        setLayout(UILayout);
        add(leftSideBar, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);


        pack();
        setVisible(true);


//        UILayout.putConstraint(SpringLayout.WEST,toolbar,);


    }


    @Override
    public HashSet<DownloadItem> getSelectedItems() {
        return selectedItems;
    }

    @Override
    public void addToSelectedItems(DownloadItem selectedItem) {
        selectedItem.add(selectedItem);
    }

    @Override
    public void removeFromSelectedItems(DownloadItem selectedItem) {
        selectedItem.remove(selectedItem);
    }
}
