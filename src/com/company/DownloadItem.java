package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DownloadItem extends JPanel {
    private String title;
    private String status;
    private String url;
    private int percentage;
    private String locationOfStorage;
    private BorderLayout downloadItemLayout;
    private JLabel downloadItemTitleLabel;
    private JProgressBar downloadItemProgressbar;

    public DownloadItem(String title, String status, String url, int percentage, String locationOfStorage) {
        /**
         * read saved data from storage
         */
        this.title = title;
        this.status = status;
        this.url = url;
        this.percentage = percentage;
        this.locationOfStorage = locationOfStorage;

        /**
         * make GUI components
         */
        downloadItemLayout = new BorderLayout();
        downloadItemTitleLabel = new JLabel();
        downloadItemProgressbar = new JProgressBar();

        /**
         * config GUI components
         */
        downloadItemTitleLabel.setText(title);
        downloadItemProgressbar.setStringPainted(true);
        downloadItemProgressbar.setValue(percentage);

        /**
         * place components in Layout
         */
        setLayout(downloadItemLayout);
        add(downloadItemTitleLabel, BorderLayout.WEST);
        add(downloadItemProgressbar, BorderLayout.CENTER);
        setVisible(true);
    }
}
