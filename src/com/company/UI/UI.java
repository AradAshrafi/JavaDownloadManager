package com.company.UI;

import com.company.BetweenClassesRelation.DownloadItemsConnection;
import com.company.BetweenClassesRelation.NewDownloadItemConnection;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class UI extends JFrame implements DownloadItemsConnection, NewDownloadItemConnection {
    private BorderLayout UILayout;
    private Toolbar toolbar;
    private LeftSideBar leftSideBar;
    private Body body;
    //--> for between class relation
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
        selectedItems.remove(selectedItem);
        body.getDownloadsPanel().remove(selectedItem);
        System.out.println("selected Items size : " + selectedItems.size());
        body.revalidate();
    }

    @Override
    public void addToDownloadPanel(DownloadItem newDownloadItem) {
        body.getDownloadsPanel().add(newDownloadItem);
    }


}
