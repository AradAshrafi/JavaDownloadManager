package com.company.UI.Body;

import javax.swing.*;
import java.awt.*;

public class DownloadItemDetails extends JFrame {
    private DownloadItem downloadItem;
    private GridLayout downloadItemDetailsLayout;

    public DownloadItemDetails(DownloadItem downloadItem) {
        /**
         * just some initialization
         */
        this.downloadItem = downloadItem;
        downloadItemDetailsLayout = new GridLayout(0, 2);
        setLayout(downloadItemDetailsLayout);
        /**
         * getting values from download item and convert them to GUI
         */
        //title
        JLabel titleLabel = new JLabel("title");
        JTextArea title = new JTextArea();
        title.setText(downloadItem.getTitle());
        title.setEditable(false);
        add(titleLabel);
        add(title);
        //percentage
        JLabel percentageLabel = new JLabel("percentage");
        JTextArea percentage = new JTextArea();
        percentage.setEditable(false);
        percentage.setText(downloadItem.getPercentage() + "%");
        add(percentageLabel);
        add(percentage);
        //size
        JLabel sizeLabel = new JLabel("size");
        JTextArea size = new JTextArea();
        size.setText(downloadItem.getSizeOfDownload() + "Kb");
        add(sizeLabel);
        add(size);
        //Date
        JLabel dateLabel = new JLabel("start date");
        JTextArea date = new JTextArea();
        date.setEditable(false);
        size.setText(downloadItem.getDate() + "");
        add(dateLabel);
        add(date);
        //Location
        JLabel locationLabel = new JLabel("Location");
        JTextArea location = new JTextArea();
        size.setText(downloadItem.getLocationOfStorage() + "");
        add(locationLabel);
        add(location);
        //URL
        JLabel urlLabel = new JLabel("URL");
        JTextArea url = new JTextArea();
        size.setText(downloadItem.getUrl() + "");
        add(locationLabel);
        add(location);

        pack();
        setVisible(true);
    }
}
