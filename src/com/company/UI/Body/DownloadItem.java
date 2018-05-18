package com.company.UI.Body;

import javax.swing.*;
import java.awt.*;

public class DownloadItem extends JPanel {
    /**
     * data fields
     */
    private String title;
    private String status;
    private String url;
    private int percentage;
    private int size;
    private int downloadSpeed;
    private String locationOfStorage;
    private SpringLayout downloadItemLayout;
    /**
     * GUI representation of data fields
     */
    private JLabel downloadItemTitleLabel;
    private JProgressBar downloadItemProgressbar;
    private JTextArea sizeArea, downloadedSizeArea, downloadSpeedArea;

    public DownloadItem() {
    }

    public DownloadItem(String title, String status, String url, int percentage, int size, int downloadSpeed, String locationOfStorage) {
        UIManager.put("ProgressBar.background", Color.white);
        UIManager.put("ProgressBar.foreground", Color.GREEN);
        UIManager.put("ProgressBar.selectionBackground", Color.blue);
        UIManager.put("ProgressBar.selectionForeground", Color.orange);//:-?
        /**
         * read saved data from storage
         */
        this.title = title;
        this.status = status;
        this.url = url;
        this.size = size;
        this.downloadSpeed = downloadSpeed;
        this.percentage = percentage;
        this.locationOfStorage = locationOfStorage;


        /**
         * make GUI components
         */
        downloadItemLayout = new SpringLayout();
        downloadItemTitleLabel = new JLabel();
        downloadItemProgressbar = new JProgressBar();
        sizeArea = new JTextArea();
        downloadedSizeArea = new JTextArea();
        downloadSpeedArea = new JTextArea();

        /**
         * config GUI components
         */
        downloadItemTitleLabel.setText(title);
        downloadItemProgressbar.setStringPainted(true);
        downloadItemProgressbar.setValue(percentage);
        downloadItemProgressbar.setString("status : " + status + "                                                      " + percentage + "%");//:D
        sizeArea.setText(size + "");
        sizeArea.setForeground(Color.blue);
        downloadedSizeArea.setText((size * percentage / 100) + "");
        downloadedSizeArea.setForeground(Color.blue);
        downloadSpeedArea.setText("0 Kb/s");
        downloadSpeedArea.setForeground(Color.blue);;

        /**
         * place components in Layout
         *
         */
        setLayout(downloadItemLayout);
        /**
         * set download item's preferredSize
         */
        setPreferredSize(new Dimension(700, 100));
        add(sizeArea);
        add(downloadedSizeArea);
        add(downloadSpeedArea);
        add(downloadItemTitleLabel);
        add(downloadItemProgressbar);

        /**
         * place them using SpringLayout
         */
        //JLabel downloadItemTitleLabel
        downloadItemLayout.putConstraint(SpringLayout.NORTH, downloadItemTitleLabel, 10, SpringLayout.NORTH, this);
        downloadItemLayout.putConstraint(SpringLayout.WEST, downloadItemTitleLabel, 10, SpringLayout.WEST, this);

        //JProgressbar downloadItemProgressbar
        downloadItemLayout.putConstraint(SpringLayout.NORTH, downloadItemProgressbar, 10, SpringLayout.NORTH, this);
        downloadItemLayout.putConstraint(SpringLayout.WEST, downloadItemProgressbar, 10, SpringLayout.EAST, downloadItemTitleLabel);
        downloadItemLayout.putConstraint(SpringLayout.EAST, downloadItemProgressbar, -10, SpringLayout.EAST, this);
        //JTextArea downloadedSizeArea
        downloadItemLayout.putConstraint(SpringLayout.NORTH, downloadedSizeArea, 10, SpringLayout.SOUTH, downloadItemTitleLabel);
        downloadItemLayout.putConstraint(SpringLayout.WEST, downloadedSizeArea, 10, SpringLayout.WEST, this);
        //JTextArea  size
        downloadItemLayout.putConstraint(SpringLayout.NORTH, sizeArea, 10, SpringLayout.SOUTH, downloadItemTitleLabel);
        downloadItemLayout.putConstraint(SpringLayout.WEST, sizeArea, 10, SpringLayout.EAST, downloadedSizeArea);
        //JTextArea Speed
        downloadItemLayout.putConstraint(SpringLayout.NORTH, downloadSpeedArea, 10, SpringLayout.SOUTH, downloadItemTitleLabel);
        downloadItemLayout.putConstraint(SpringLayout.EAST, downloadSpeedArea, -10, SpringLayout.EAST, this);


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
