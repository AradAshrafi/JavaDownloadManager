package com.company.UI.Body;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DownloadItem extends JPanel {
    private String title;
    private String status;
    //    public final static String[] statusList = {"Pause", "In Progress", "Canceled", "Failed"};
    private String url;
    private int percentage;
    private String locationOfStorage;
    private BorderLayout downloadItemLayout;
    private JLabel downloadItemTitleLabel;
    private JProgressBar downloadItemProgressbar;

    public DownloadItem() {
    }

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
         * set download item's preferredSize
         */
        setPreferredSize(new Dimension(700, 100));

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
        downloadItemProgressbar.setString("status : " + status + "                                                     " + percentage + "%");//:D


        /**
         * place components in Layout
         */
        setLayout(downloadItemLayout);
        add(downloadItemTitleLabel, BorderLayout.WEST);
        add(downloadItemProgressbar, BorderLayout.CENTER);
        setVisible(true);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        downloadItemProgressbar.setString("status : " + status + "                                                     " + percentage + "%");
    }

    /**
     * for DownloadItemsConnection interface,and it's used for between objects actions
     */
    public void pauseSelectedItem() {
        setStatus("Paused");
//        downloadItemProgressbar.revalidate();
//        downloadItemProgressbar.repaint();
    }

    public void resumeSelectedItem() {
        setStatus("In Progress");
//        downloadItemProgressbar.revalidate();
//        downloadItemProgressbar.repaint();
    }

    public void cancelSelectedItem() {
        setStatus("Canceled");
//        revalidate();
    }

}
