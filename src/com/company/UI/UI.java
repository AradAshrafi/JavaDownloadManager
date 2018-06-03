package com.company.UI;

import com.company.FileOperation.Export;
import com.company.FileOperation.ListQueueJDM;
import com.company.FileOperation.SettingsJDM;
import com.company.FileOperation.ThingsToSaveBeforeClosingTheProgram;
import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;
import com.company.UI.BetweenClassesRelation.NewDownloadItemConnection;
import com.company.UI.BetweenClassesRelation.StaticData;
import com.company.UI.Body.Body;
import com.company.UI.Body.DownloadItem;
import com.company.UI.Body.DownloadQueue;
import com.company.UI.Body.DownloadsPanel;
import com.company.UI.LeftSideBar.LeftSideBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class UI extends JFrame implements DownloadItemsConnection, NewDownloadItemConnection {
    private BorderLayout UILayout;
    private Toolbar toolbar;
    private LeftSideBar leftSideBar;
    private Body body;
    //--> for between class relation
    private HashSet<DownloadItem> selectedItems;
    private HashMap<String, DownloadQueue> downloadQueues;
    private int simultaneousDownloads;

    public UI() {
        super("UI");
        UILayout = new BorderLayout();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        selectedItems = new HashSet<>();
        downloadQueues = new HashMap<>();
        downloadQueues.put("main", new DownloadQueue());

        if (SettingsJDM.getSettings().size() > 0)
            simultaneousDownloads = Integer.parseInt(SettingsJDM.getSettings().get(0));
        else simultaneousDownloads = 50;

//        setSize(600, 600);

        System.out.println(simultaneousDownloads);
        /**
         *adding components to UI -- first leftSideBar
         */

        leftSideBar = new LeftSideBar(this, this);
        toolbar = new Toolbar(this, this);
        body = new Body(this);

        setLayout(UILayout);
        add(leftSideBar, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);

        pack();
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ThingsToSaveBeforeClosingTheProgram.thingsToSaveBeforeClosingTheProgram(simultaneousDownloads, downloadQueues.get("main").getQueue());
            }
        });


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
        downloadQueues.get("main").operationOnDownloadQueue(selectedItem, "remove");
        ListQueueJDM.removeDownload(selectedItem.getDownloadItemData(), "list");

        body.getDownloadsPanel().remove(selectedItem);
        body.getDownloadsPanel().revalidate();
        body.getDownloadsPanel().repaint();


    }

    @Override
    public void pauseSelectedItem(DownloadItem selectedItem) {
        downloadQueues.get("main").operationOnDownloadQueue(selectedItem, "pause");
        //it seems like JProgressbar doesn't need to revalidate  ------>
//        body.revalidate();
//        body.repaint();
        // <---------

    }

    @Override
    public void resumeSelectedItem(DownloadItem selectedItem) {
        downloadQueues.get("main").operationOnDownloadQueue(selectedItem, "resume");
        //it seems like JProgressbar doesn't need to revalidate  ----->
//        body.revalidate();
//        body.repaint();
        // <---------

    }


    @Override
    public HashMap<String, DownloadQueue> getDownloadQueues() {
        return this.downloadQueues;
    }

    public HashMap<String, DownloadQueue> getDownloadQueuesClone() {
        return (HashMap<String, DownloadQueue>) this.downloadQueues.clone();
    }

    @Override
    public void addNewQueue(String key, DownloadItem downloadItem) {
        DownloadQueue newQueue = new DownloadQueue();
        newQueue.getQueue().add(downloadItem);
        downloadQueues.put(key, newQueue);
    }

    @Override
    public void addToCurrentQueue(String key, DownloadItem downloadItem) {
        downloadQueues.get(key).getQueue().add(downloadItem);
    }

    @Override
    public void removeFromCurrentQueue(String key, DownloadItem downloadItem) {
        downloadQueues.get(key).getQueue().remove(downloadItem);
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
        addToCurrentQueue("main", newDownloadItem);
        body.revalidate();
        body.repaint();
    }

    @Override
    public void cancelSelectedItem(DownloadItem selectedItem) {
        removeFromCurrentQueue("main", selectedItem);
        //it seems like JProgressbar doesn't need to revalidate  ----->
//        body.revalidate();
//        body.repaint();
        // <---------
    }


    @Override
    public ArrayList<DownloadItem> getDownloadItems() {
        return downloadQueues.get("main").getQueue();
    }

    @Override
    public Container getUiContainer() {
        return this.getContentPane();
    }

    @Override
    public DownloadsPanel getDownloadsPanel() {
        return body.getDownloadsPanel();
    }

    @Override
    public void reloadUI() {
//        new Runnable(){
//
//        }
        revalidate();
        repaint();
    }
}