package com.company.UI.LeftSideBar;

import com.company.BetweenClassesRelation.DownloadItemsConnection;
import com.company.BetweenClassesRelation.NewDownloadItemConnection;
import com.company.UI.Body.DownloadItem;
import com.company.UI.Setting;
import com.company.UI.UI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;

public class LeftSideBar extends JPanel {
    private JMenuBar leftSideBarMenu;
    private JMenu download, help;
    private JMenuItem processing, completed, queues, newDownload, pauseResume, cancel, remove, settings, exit, about;
    private JLabel horizontalSeparator;
    private JButton lookAndFeelManager;


    /**
     * creating a Vertical MenuBar
     */
    private class VerticalMenuBar extends JMenuBar {
        private final LayoutManager grid = new GridLayout(0, 1);

        public VerticalMenuBar() {
            setLayout(grid);

        }
    }

    public LeftSideBar(UI ui) {
        /**
         * create menu bar (Vertical Menubar)
         */
        leftSideBarMenu = new VerticalMenuBar();

        /**
         * create menu titles + Look and Feel manager button
         */
        download = new JMenu("Download");
        help = new JMenu("Help");
        lookAndFeelManager = new JButton("Theme");


        /**
         * create menu items
         */
        processing = new JMenuItem("Processing");
        completed = new JMenuItem("Completed");
        queues = new JMenuItem("Queues");
        newDownload = new JMenuItem("Add a download");

        pauseResume = new JMenuItem("Pause/Resume selected download");
        cancel = new JMenuItem("Cancel selected download");
        remove = new JMenuItem("Remove selected download");
        settings = new JMenuItem("Settings");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");

        /**
         * create separator
         */
        /**
         * handling vertical separator
         */
        ImageIcon horizontalSeparatorIcon = new ImageIcon("horizontalSeparator.png");
        //--> resizing image
        Image originalImg = horizontalSeparatorIcon.getImage();
        Image newImg = originalImg.getScaledInstance(150, 20, java.awt.Image.SCALE_SMOOTH);
        horizontalSeparatorIcon = new ImageIcon(newImg);
        //<--
        horizontalSeparator = new JLabel(horizontalSeparatorIcon);
        Border borderOfSeparator = horizontalSeparator.getBorder();
        Border marginOfSeparator = new EmptyBorder(0, 80, 0, 0);
        horizontalSeparator.setBorder(new CompoundBorder(borderOfSeparator, marginOfSeparator));

        /**
         *Handling Accelerators
         */
        //part 1
        KeyStroke processingAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK);
        KeyStroke completedAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK);
        KeyStroke queuesAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK);
        //part 2
        KeyStroke newDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        KeyStroke pauseResumeDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
        KeyStroke cancelDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
        KeyStroke removeDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK);
        KeyStroke settingsAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        KeyStroke exitAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
        processing.setAccelerator(processingAccelerator);
        completed.setAccelerator(completedAccelerator);
        queues.setAccelerator(queuesAccelerator);
        newDownload.setAccelerator(newDownloadAccelerator);
        pauseResume.setAccelerator(pauseResumeDownloadAccelerator);
        cancel.setAccelerator(cancelDownloadAccelerator);
        remove.setAccelerator(removeDownloadAccelerator);
        settings.setAccelerator(settingsAccelerator);
        exit.setAccelerator(exitAccelerator);

        /**
         * adding menu items to Download object
         */
        download.add(processing);
        download.add(completed);
        download.add(queues);
        download.add(horizontalSeparator);
        download.add(newDownload);
        download.add(remove);
        download.add(pauseResume);
        download.add(cancel);
        download.add(remove);
        download.add(settings);
        download.add(exit);

        /**
         * adding menu items to Help object
         */
        help.add(about);

        /**
         * adding menu objects to menu bar
         * adding look and feel manager tu menu bar
         */
        leftSideBarMenu.add(download);
        leftSideBarMenu.add(help);
        leftSideBarMenu.add(lookAndFeelManager);
        leftSideBarMenu.setBorderPainted(true);
//        Border borderOfDownloadItem = download.getBorder();
//        Border marginOfDownloadItem = new (0, 40, 0, 60);
        download.setBorder((new SoftBevelBorder(BevelBorder.RAISED)));
        help.setBorder((new SoftBevelBorder(BevelBorder.RAISED)));


        /**
         * add menu bar to JPanel
         */
        add(leftSideBarMenu);

        /**
         * handling actionListeners
         */
        MenuItemHandler leftSideBarHandler = new MenuItemHandler(ui);
        newDownload.addActionListener(leftSideBarHandler);
        pauseResume.addActionListener(leftSideBarHandler);
        cancel.addActionListener(leftSideBarHandler);
        remove.addActionListener(leftSideBarHandler);
        settings.addActionListener(leftSideBarHandler);
        exit.addActionListener(leftSideBarHandler);
        lookAndFeelManager.addActionListener(leftSideBarHandler);

        setLayout(new GridLayout(0, 1));
        setVisible(true);
    }

    private class MenuItemHandler implements ActionListener {
        private HashSet<DownloadItem> selectedItems;
        private Container uiContainer;
        private DownloadItemsConnection downloadItemsConnection;
        private NewDownloadItemConnection newDownloadItemConnection;


        public MenuItemHandler(UI ui) {
            /**
             * casting ui to interface to use it's functionality
             * cast it to NewDownloadItem to change body panel
             */
            this.downloadItemsConnection = ui;
            this.newDownloadItemConnection = ui;

            this.selectedItems = downloadItemsConnection.getSelectedItems();//data has a static field for HashSets
            this.uiContainer = ui.getContentPane();

        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource()==processing){

            }
            if(event.getSource()==completed){

            }
            if(event.getSource()==queues){

            }
            if (event.getSource() == newDownload) {
                NewDownloadTab newDownloadTab = new NewDownloadTab(newDownloadItemConnection, downloadItemsConnection); //:))))
            }
            if (event.getSource() == lookAndFeelManager) {
                LookAndFeelManager lookAndFeelManager = new LookAndFeelManager(uiContainer);
            }
            if (event.getSource() == pauseResume) {
                Iterator<DownloadItem> it = selectedItems.iterator();
                DownloadItem selectedToPauseOrResume;
                while (it.hasNext()) {
                    selectedToPauseOrResume = it.next();
                    String status = selectedToPauseOrResume.getStatus();
                    if (!status.equals("In Progress")
                            ) {
                        downloadItemsConnection.pauseSelectedItem(selectedToPauseOrResume);
                    } else if (status.equals("Paused")) {
                        downloadItemsConnection.resumeSelectedItem(selectedToPauseOrResume);
                    }
                }
            }
            if (event.getSource() == cancel) {
                Iterator<DownloadItem> it = selectedItems.iterator();
                DownloadItem selectedToCancel;
                while (it.hasNext()) {
                    selectedToCancel = it.next();
                    downloadItemsConnection.cancelSelectedItem(selectedToCancel);
                }
            }
            if (event.getSource() == remove) {
                Iterator<DownloadItem> it = selectedItems.iterator();
                DownloadItem selectedToDelete;
                while (it.hasNext()) {
                    selectedToDelete = it.next();
                    it.remove();
                    downloadItemsConnection.removeSelectedItem(selectedToDelete);
                }
            }
            if (event.getSource() == settings) {
                Setting setting = new Setting(uiContainer, downloadItemsConnection);
            }
            if (event.getSource() == exit) {
                System.exit(0);
            }
            if (event.getSource() == about) {

            }
        }
    }
}
