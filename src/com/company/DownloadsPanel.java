package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DownloadsPanel extends JPanel {
    private ArrayList<DownloadItem> mainQueue;
    private HashSet<DownloadItem> selectedItems;
    private GridLayout downloadsPanelLayout;

    public void addToQueue(ArrayList queue, DownloadItem item) {
        queue.add(item);
    }


    public DownloadsPanel() {
        mainQueue = new ArrayList<>();
        selectedItems = new HashSet<>();
        downloadsPanelLayout = new GridLayout(0, 1);
        setLayout(downloadsPanelLayout);
        DownloadItem sample1 = new DownloadItem("test", "failed", "https://", 20, "d://");
        DownloadItem sample2 = new DownloadItem("test", "failed", "https://", 20, "d://");

        addToQueue(mainQueue, sample1);
        addToQueue(mainQueue, sample2);
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
                System.out.println("contains beyotch");
                setBackground(Color.red);
            }
            add(item);
        }
        setVisible(true);
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


//    private ArrayList<JProgressBar> downloads
}
