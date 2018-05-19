package com.company.UI;

import com.company.BetweenClassesRelation.DownloadItemsConnection;
import com.company.BetweenClassesRelation.NewDownloadItemConnection;
import com.company.UI.Body.Body;
import com.company.UI.Body.DownloadItem;
import com.company.UI.Body.DownloadQueue;
import com.company.UI.Body.DownloadsPanel;
import com.company.UI.LeftSideBar.LeftSideBar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UI extends JFrame implements DownloadItemsConnection, NewDownloadItemConnection {
    private BorderLayout UILayout;
    private Toolbar toolbar;
    private LeftSideBar leftSideBar;
    private Body body;
    //--> for between class relation
    private ArrayList<DownloadItem> downloadItems;
    private HashSet<DownloadItem> selectedItems;
    private HashMap<String, DownloadQueue> downloadQueues;
    private int simultaneousDownloads = 1000;

    public UI() {
        super("UI");
        UILayout = new BorderLayout();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        downloadItems = new ArrayList<>();
        selectedItems = new HashSet<>();
        downloadQueues = new HashMap<>();
        downloadQueues.put("main", new DownloadQueue());
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
    public void addToSelectedItem(DownloadItem selectedItem) {
        selectedItems.add(selectedItem);
    }

    @Override
    public void removeSelectedItem(DownloadItem selectedItem) {
        System.out.println("remove");
        System.out.println("size : " + selectedItems.size());
        selectedItems.remove(selectedItem);
        removeFromDownloadItems(selectedItem);
        body.getDownloadsPanel().getMainQueue().operationOnDownloadQueue(selectedItem, "remove");
        body.getDownloadsPanel().remove(selectedItem);
        body.getDownloadsPanel().revalidate();
        body.getDownloadsPanel().repaint();


//        body.getDownloadsPanelScrollbar().revalidate();
//        body.getDownloadsPanel().downloadItemsDetail();
//        body.getDownloadsPanelScrollbar().setPreferredSize(body.getDownloadsPanelScrollbar().getMinimumSize());
    }

    @Override
    public void pauseSelectedItem(DownloadItem selectedItem) {
        body.getDownloadsPanel().getMainQueue().operationOnDownloadQueue(selectedItem, "pause");
        //it seems like JProgressbar doesn't need to revalidate  ------>
//        body.revalidate();
//        body.repaint();
        // <---------

    }

    @Override
    public void resumeSelectedItem(DownloadItem selectedItem) {
        body.getDownloadsPanel().getMainQueue().operationOnDownloadQueue(selectedItem, "resume");
        //it seems like JProgressbar doesn't need to revalidate  ----->
//        body.revalidate();
//        body.repaint();
        // <---------

    }

    @Override
    public void cancelSelectedItem(DownloadItem selectedItem) {
        body.getDownloadsPanel().getMainQueue().operationOnDownloadQueue(selectedItem, "cancel");
        //it seems like JProgressbar doesn't need to revalidate  ----->
//        body.revalidate();
//        body.repaint();
        // <---------
    }

    @Override
    public HashMap<String, DownloadQueue> getDownloadQueues() {
        return this.downloadQueues;
    }

    @Override
    public int getSimultaneousDownloads() {
        return simultaneousDownloads;
    }

    @Override
    public void setSimultaneousDownloads(int simultaneousDownloads) {
        this.simultaneousDownloads = simultaneousDownloads;
    }


    @Override
    public void addToDownloadPanel(DownloadItem newDownloadItem) {
        body.getDownloadsPanel().add(newDownloadItem);
        body.getDownloadsPanel().getMainQueue().operationOnDownloadQueue(newDownloadItem, "add");
        addToDownloadItems(newDownloadItem);
        body.revalidate();
        body.repaint();
    }

    @Override
    public ArrayList<DownloadItem> getDownloadItems() {
        return downloadItems;
    }

    @Override
    public void addToDownloadItems(DownloadItem downloadItem) {
        downloadItems.add(downloadItem);
    }

    @Override
    public void removeFromDownloadItems(DownloadItem downloadItem) {
        downloadItems.remove(downloadItem);
    }

}