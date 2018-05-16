package com.company.UI;

import com.company.BetweenClassesRelation.DownloadItemsConnection;
import com.company.BetweenClassesRelation.StaticData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class DownloadsPanel extends JPanel {
    private ArrayList<DownloadItem> mainQueue;
    private GridLayout downloadsPanelLayout;
    private DownloadItemsConnection downloadItemsConnection;
    private HashSet<DownloadItem> selectedItems;

    public void addToQueue(ArrayList queue, DownloadItem item) {
        queue.add(item);
    }

    public DownloadsPanel(UI ui) {
        /**
         * some initialization
         * casting ui to DownloadItemsConnection for some access
         */
        mainQueue = new ArrayList<>();
        downloadItemsConnection = (DownloadItemsConnection) (ui);
        selectedItems = downloadItemsConnection.getSelectedItems();
        downloadsPanelLayout = new GridLayout(0, 1, 0, 20);
        setLayout(downloadsPanelLayout);
        DownloadItem sample1 = new DownloadItem("test", "failed", "https://", 20, StaticData.getLocation());
        DownloadItem sample2 = new DownloadItem("test", "failed", "https://", 20, "d://");
        DownloadItem sample3 = new DownloadItem("test", "failed", "https://", 20, "d://");
        addToQueue(mainQueue, sample1);
        addToQueue(mainQueue, sample2);
        addToQueue(mainQueue, sample3);

        Iterator<DownloadItem> it = mainQueue.iterator();

        /**
         * Adding items to Layout and handle their actionListener
         */
        MouseHandler handler = new MouseHandler();
        while (it.hasNext()) {
            System.out.println("check");
            DownloadItem item = it.next();
            item.addMouseListener(handler);
            if (selectedItems.contains(item)) {
                setBackground(Color.red);
            }
            add(item);
        }
        setVisible(true);
    }

    public void removeFromPanel(DownloadItem downloadItem) {
        mainQueue.remove(downloadItem);
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() instanceof DownloadItem) {
                DownloadItem clickedItem = (DownloadItem) (e.getSource());
                if (selectedItems.contains(clickedItem)) {
                    selectedItems.remove(clickedItem);
                    System.out.println("removed");
                    clickedItem.setBackground(null);
                } else {
                    selectedItems.add(clickedItem);
                    System.out.println("Added");
                    clickedItem.setBackground(Color.red);
                }
            }
            revalidate();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }


}
