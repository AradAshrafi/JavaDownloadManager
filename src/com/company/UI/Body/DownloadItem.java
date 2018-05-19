package com.company.UI.Body;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DownloadItem extends JPanel {
    /**
     * data fields
     */
    private String title;
    private String status;
    private int percentage;
    private int size;
    private int downloadSpeed;
    private SpringLayout downloadItemLayout;
    /**
     * secret data that will show in downloadItemDetail after right click on each item
     */
    private long date;
    private String locationOfStorage;
    private String url;


    /**
     * GUI representation of data fields
     */
    private JButton openFolderButton;
    private JButton addToQueue;
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
        this.date = System.currentTimeMillis();
        this.locationOfStorage = locationOfStorage;


        /**
         * make GUI components
         */
//        openFolderButton = new JButton(); //its useless due to new another JButton down here
        downloadItemLayout = new SpringLayout();
        downloadItemTitleLabel = new JLabel();
        downloadItemProgressbar = new JProgressBar();
        sizeArea = new JTextArea();
        downloadedSizeArea = new JTextArea();
        downloadSpeedArea = new JTextArea();

        /**
         * config openFolder Button
         */
        /**
         * handling settingsButton and it's image
         * handling addToQueueButton and it's image
         */
        //openFolderButton
        Image originalImg;
        Image newImg;
        ImageIcon newFolderIcon = new ImageIcon("folder.png");
        //--> resizing image
        originalImg = newFolderIcon.getImage();
        newImg = originalImg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        newFolderIcon = new ImageIcon(newImg);
        //<--
        openFolderButton = new JButton(newFolderIcon);
        openFolderButton.setBorder(null);
        Border borderOfOpenFolderButton = openFolderButton.getBorder();
        Border marginOfOpenFolderButton = new EmptyBorder(0, 0, 0, 0);
        openFolderButton.setBorder(new CompoundBorder(borderOfOpenFolderButton, marginOfOpenFolderButton));
        openFolderButton.setContentAreaFilled(false);

        //addToQueue
        ImageIcon addToQueueIcon = new ImageIcon("newDownload.png");
        //--> resizing image
        originalImg = addToQueueIcon.getImage();
        newImg = originalImg.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        addToQueueIcon = new ImageIcon(newImg);
        //<--
        addToQueue = new JButton(addToQueueIcon);
        addToQueue.setBorder(null);
        Border borderOfAddToQueue = addToQueue.getBorder();
        Border marginOfAddToQueue = new EmptyBorder(0, 0, 0, 0);
        addToQueue.setBorder(new CompoundBorder(borderOfAddToQueue, marginOfAddToQueue));
        addToQueue.setContentAreaFilled(false);

        /**
         * config GUI components
         */
        downloadItemTitleLabel.setText(title);
        downloadItemProgressbar.setStringPainted(true);
        downloadItemProgressbar.setValue(percentage);
        downloadItemProgressbar.setString("status : " + status + "                                                      " + percentage + "%");//:D
        sizeArea.setText(size + "Kb");
        sizeArea.setForeground(Color.blue);
        downloadedSizeArea.setText((size * percentage / 100) + "Kb");
        downloadedSizeArea.setForeground(Color.blue);
        downloadSpeedArea.setText("1 Kb/s");
        downloadSpeedArea.setForeground(Color.blue);
        ;

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
        add(openFolderButton);
        add(addToQueue);
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
        downloadItemLayout.putConstraint(SpringLayout.NORTH, downloadedSizeArea, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.WEST, downloadedSizeArea, 50, SpringLayout.WEST, this);
        //JTextArea size
        downloadItemLayout.putConstraint(SpringLayout.NORTH, sizeArea, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.WEST, sizeArea, 10, SpringLayout.EAST, downloadedSizeArea);
        //JTextArea Speed
        downloadItemLayout.putConstraint(SpringLayout.NORTH, downloadSpeedArea, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.EAST, downloadSpeedArea, -10, SpringLayout.EAST, this);
        //JButton openFolderButton
        downloadItemLayout.putConstraint(SpringLayout.NORTH, openFolderButton, 5, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.EAST, openFolderButton, -10, SpringLayout.WEST, downloadSpeedArea);
        //JButton addToQueue
        downloadItemLayout.putConstraint(SpringLayout.NORTH, addToQueue, 5, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.EAST, addToQueue, -10, SpringLayout.WEST, openFolderButton);

        /**
         * adding handler to open folder icon
         */
        OpenFolderButtonListener openFolderButtonListener = new OpenFolderButtonListener(this);


        setVisible(true);


    }

    public class OpenFolderButtonListener implements ActionListener {
        private DownloadItem downloadItem;

        public OpenFolderButtonListener(DownloadItem downloadItem) {
            this.downloadItem = downloadItem;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == openFolderButton) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File(downloadItem.getLocationOfStorage());
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        System.out.println("invalid path");
                    }
                }
            }
            if (e.getSource() == addToQueue) {
                AddToQueue addToQueue = new AddToQueue();
            }
        }
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

    public String getTitle() {
        return title;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getSizeOfDownload() {
        return size;
    }

    public int getDownloadSpeed() {
        return downloadSpeed;
    }

    public long getDate() {
        return date;
    }

    public String getLocationOfStorage() {
        return locationOfStorage;
    }

    public String getUrl() {
        return url;
    }
}
