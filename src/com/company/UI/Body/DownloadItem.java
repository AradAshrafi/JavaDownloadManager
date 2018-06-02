package com.company.UI.Body;

import com.company.DownloadItemData.DownloadItemData;
import com.company.NetworkOperation.DownloadFromANewURL;
import com.company.UI.BetweenClassesRelation.DownloadItemsConnection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DownloadItem extends JPanel {
    /**
     * data fields
     */
    private String title;
    private String status;
    private int downloadedSize;


    private int size;
    private int downloadSpeed;
    private SpringLayout downloadItemLayout;

    /**
     * secret data that will show in downloadItemDetail after right click on each item
     */
    private String date;
    private String locationOfStorage;
    private String url;

    /**
     * separately take downloadItemData too :D
     * i've decided to do this to be compatible with phase1 design in some parts instead of change those parts
     */
    private DownloadItemData downloadItemData;

    /**
     * GUI representation of data fields
     */
    private JButton openFolderButton;
    private JButton addToQueue;
    private JButton removeFromQueue;
    private JLabel downloadItemTitleLabel;
    private JProgressBar downloadItemProgressbar;
    private JTextArea sizeArea, downloadedSizeArea, downloadSpeedArea;


    public DownloadItem(DownloadItemData downloadItemData, int downloadSpeed, DownloadItemsConnection downloadItemsConnection) {
        UIManager.put("ProgressBar.background", Color.WHITE);
        UIManager.put("ProgressBar.foreground", Color.decode("#78D6AC"));
        UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
        UIManager.put("ProgressBar.selectionForeground", Color.RED);//:-?
        setBackground(Color.white);

        /**
         * update downloadItemData
         */
        this.downloadItemData = downloadItemData;

        /**
         * read saved data from storage
         */
        this.title = downloadItemData.getData().get("title");
        this.status = downloadItemData.getData().get("status");
        this.url = downloadItemData.getData().get("url");
        this.locationOfStorage = downloadItemData.getData().get("locationOfStorage");
        this.date = downloadItemData.getData().get("date");
        this.size = Integer.parseInt(downloadItemData.getData().get("size"));
        this.downloadedSize = Integer.parseInt(downloadItemData.getData().get("downloadedSize"));

        this.downloadSpeed = downloadSpeed;


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
         * config openFolder Button
         */
        /**
         * handling settingsButton and it's image
         * handling addToQueueButton and it's image
         * handling removeFromQueueButton and it's image
         */
        //openFolderButton
        Image originalImg;
        Image newImg;
        ImageIcon newFolderIcon = new ImageIcon("folder.png");
        //--> resizing image
        originalImg = newFolderIcon.getImage();
        newImg = originalImg.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
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
        newImg = originalImg.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        addToQueueIcon = new ImageIcon(newImg);
        //<--
        addToQueue = new JButton(addToQueueIcon);
        addToQueue.setBorder(null);
        Border borderOfAddToQueue = addToQueue.getBorder();
        Border marginOfAddToQueue = new EmptyBorder(0, 0, 0, 0);
        addToQueue.setBorder(new CompoundBorder(borderOfAddToQueue, marginOfAddToQueue));
        addToQueue.setContentAreaFilled(false);

        //removeFromQueue
        ImageIcon removeFromQueueIcon = new ImageIcon("cancel.png");
        //--> resizing image
        originalImg = removeFromQueueIcon.getImage();
        newImg = originalImg.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        removeFromQueueIcon = new ImageIcon(newImg);
        //<--
        removeFromQueue = new JButton(removeFromQueueIcon);
        removeFromQueue.setBorder(null);
        Border borderOfRemoveFromQueue = removeFromQueue.getBorder();
        Border marginOfRemoveFromQueue = new EmptyBorder(0, 0, 0, 0);
        removeFromQueue.setBorder(new CompoundBorder(borderOfRemoveFromQueue, marginOfRemoveFromQueue));
        removeFromQueue.setContentAreaFilled(false);

        /**
         * config GUI components
         */
        downloadItemTitleLabel.setText(title);
        downloadItemProgressbar.setStringPainted(true);
        if (size == 0) {
            downloadItemProgressbar.setValue(0);
        } else {
            downloadItemProgressbar.setValue(downloadedSize * 100 / size);

        }
        downloadItemProgressbar.setString("status : " + status + "                                                      " + (downloadItemProgressbar.getValue()) + "%");//:D
        sizeArea.setText(size + "Kb");
        sizeArea.setForeground(Color.black);
        downloadedSizeArea.setText(downloadedSize + "Kb");
        downloadedSizeArea.setForeground(Color.black);
        downloadSpeedArea.setText("1 Kb/s");
        downloadSpeedArea.setForeground(Color.black);

        /**
         * place components in Layout
         *
         */
        setLayout(downloadItemLayout);

        /**
         * set download item's preferredSize
         */
        setPreferredSize(new Dimension(1050, 150));

        add(downloadItemTitleLabel);
        add(downloadedSizeArea);
        if (!(status.equals("done") || status.equals("failed"))) {
            add(sizeArea);
            add(openFolderButton);
            add(addToQueue);
            add(removeFromQueue);
            add(downloadSpeedArea);
            add(downloadItemProgressbar);
        }

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
        downloadItemLayout.putConstraint(SpringLayout.SOUTH, downloadItemProgressbar, 30, SpringLayout.NORTH, downloadItemProgressbar);

        //JTextArea downloadedSizeArea
        downloadItemLayout.putConstraint(SpringLayout.NORTH, downloadedSizeArea, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.WEST, downloadedSizeArea, 50, SpringLayout.WEST, this);
        //JTextArea size
        downloadItemLayout.putConstraint(SpringLayout.NORTH, sizeArea, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.WEST, sizeArea, 10, SpringLayout.EAST, downloadedSizeArea);
        //JTextArea Speed
        downloadItemLayout.putConstraint(SpringLayout.NORTH, downloadSpeedArea, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.EAST, downloadSpeedArea, -15, SpringLayout.EAST, this);
        //JButton openFolderButton
        downloadItemLayout.putConstraint(SpringLayout.NORTH, openFolderButton, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.EAST, openFolderButton, -10, SpringLayout.WEST, downloadSpeedArea);
        //JButton addToQueue
        downloadItemLayout.putConstraint(SpringLayout.NORTH, addToQueue, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.EAST, addToQueue, -10, SpringLayout.WEST, openFolderButton);
        //JButton removeFromQueue
        downloadItemLayout.putConstraint(SpringLayout.NORTH, removeFromQueue, 10, SpringLayout.SOUTH, downloadItemProgressbar);
        downloadItemLayout.putConstraint(SpringLayout.EAST, removeFromQueue, -10, SpringLayout.WEST, addToQueue);

        /**
         * adding handler to open folder icon
         */
        DownloadItemButtonListener downloadItemButtonListener = new DownloadItemButtonListener(this, downloadItemsConnection);
        openFolderButton.addActionListener(downloadItemButtonListener);
        addToQueue.addActionListener(downloadItemButtonListener);
        removeFromQueue.addActionListener(downloadItemButtonListener);

        if (status.equals("In Progress")) {
            DownloadFromANewURL downloadFromANewURL = new DownloadFromANewURL(this);
            downloadFromANewURL.execute();

        }

        setVisible(true);
    }

    /**
     * implementing ActionListener for buttons
     */
    public class DownloadItemButtonListener implements ActionListener {
        private DownloadItem downloadItem;
        private DownloadItemsConnection downloadItemsConnection;

        public DownloadItemButtonListener(DownloadItem downloadItem, DownloadItemsConnection downloadItemsConnection) {
            this.downloadItem = downloadItem;
            this.downloadItemsConnection = downloadItemsConnection;
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
                AddRemoveFromQueue addRemoveFromQueue = new AddRemoveFromQueue(downloadItemsConnection, downloadItem, "add");
            }
            if (e.getSource() == removeFromQueue) {
                AddRemoveFromQueue addRemoveFromQueue = new AddRemoveFromQueue(downloadItemsConnection, downloadItem, "remove");

            }
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        downloadItemProgressbar.setString("status : " + status + "                                                     " + (downloadedSize / size) + "%");
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

    public int getDownloadedSize() {
        return downloadedSize;
    }

    public int getPercentage() {
        return ((downloadedSize / size) * 100);
    }

    public int getSizeOfDownload() {
        return size;
    }

    public int getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setsize(int size) {
        this.size = size;
    }

    public void setDownloadedSize(int downloadedSize) {
        this.downloadedSize = downloadedSize;
    }

    public String getDate() {
        return date;
    }

    public String getLocationOfStorage() {
        System.out.println(locationOfStorage);
        return locationOfStorage;
    }

    public String getUrl() {
        return url;
    }

    public JProgressBar getDownloadItemProgressbar() {
        return downloadItemProgressbar;
    }

    public DownloadItemData getDownloadItemData() {
        return downloadItemData;
    }

    public void removeFieldsAfterFinishingDownload() {
        remove(sizeArea);
        remove(openFolderButton);
        remove(addToQueue);
        remove(removeFromQueue);
        remove(downloadSpeedArea);
        remove(downloadItemProgressbar);
    }
}
