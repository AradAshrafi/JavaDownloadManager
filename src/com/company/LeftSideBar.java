package com.company;

import com.company.BetweenClassesRelation.DownloadItemsConnection;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;

public class LeftSideBar extends JPanel {
    private JMenuBar leftSideBarMenu;
    private JMenu download, help;
    private JMenuItem processing, completed, queues, newDownloadButton, resumeButton, pauseButton, cancelButton, removeButton, settingsButton, exit, about;
    private JLabel horizontalSeparator;
    private DownloadItemsConnection downloadItemsConnection;

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
         * casting ui to interface to use it's functionality
         */
        downloadItemsConnection = (DownloadItemsConnection) (ui);

        /**
         * create menu bar (Vertical Menubar)
         */
        leftSideBarMenu = new VerticalMenuBar();

        /**
         * create menu titles
         */
        download = new JMenu("Download");
        help = new JMenu("Help");


        /**
         * create menu items
         */
        processing = new JMenuItem("Processing");
        completed = new JMenuItem("Completed");
        queues = new JMenuItem("Queues");
        newDownloadButton = new JMenuItem("Add a download");
        resumeButton = new JMenuItem("Resume selected download");
        pauseButton = new JMenuItem("Pause selected download");
        cancelButton = new JMenuItem("Cancel selected download");
        removeButton = new JMenuItem("Remove selected download");
        settingsButton = new JMenuItem("Settings");
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
        KeyStroke resumeDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        KeyStroke pauseDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
        KeyStroke cancelDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
        KeyStroke removeDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK);
        KeyStroke settingsAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        KeyStroke exitAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
        processing.setAccelerator(processingAccelerator);
        completed.setAccelerator(completedAccelerator);
        queues.setAccelerator(queuesAccelerator);
        newDownloadButton.setAccelerator(newDownloadAccelerator);
        resumeButton.setAccelerator(resumeDownloadAccelerator);
        pauseButton.setAccelerator(pauseDownloadAccelerator);
        cancelButton.setAccelerator(cancelDownloadAccelerator);
        removeButton.setAccelerator(removeDownloadAccelerator);
        settingsButton.setAccelerator(settingsAccelerator);
        exit.setAccelerator(exitAccelerator);

        /**
         * adding menu items to Download object
         */
        download.add(processing);
        download.add(completed);
        download.add(queues);
        download.add(horizontalSeparator);
        download.add(newDownloadButton);
        download.add(removeButton);
        download.add(pauseButton);
        download.add(cancelButton);
        download.add(removeButton);
        download.add(settingsButton);
        download.add(exit);

        /**
         * adding menu items to Help object
         */
        help.add(about);

        /**
         * adding menu objects to menu bar
         */
        leftSideBarMenu.add(download);
        leftSideBarMenu.add(help);
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
        Handler leftSideBarHandler = new Handler(downloadItemsConnection);
        newDownloadButton.addActionListener(leftSideBarHandler);
        resumeButton.addActionListener(leftSideBarHandler);
        pauseButton.addActionListener(leftSideBarHandler);
        cancelButton.addActionListener(leftSideBarHandler);
        removeButton.addActionListener(leftSideBarHandler);
        settingsButton.addActionListener(leftSideBarHandler);
        exit.addActionListener(leftSideBarHandler);

        setLayout(new GridLayout(0, 1));
//        setPreferredSize(new Dimension(150, 300));
//        SpringLayout leftSideBarLayout = new SpringLayout();
//        this.setLayout(leftSideBarLayout);
//        setBackground(Color.RED);
//        JButton Processing = new JButton("Processing");
//        JButton Completed = new JButton("Completed");
//        JButton Queues = new JButton("Queues");
//        this.add(Processing);
//        this.add(Completed);
//        this.add(Queues);
//        leftSideBarLayout.putConstraint(SpringLayout.NORTH, Processing, 0, SpringLayout.NORTH, this);
//        leftSideBarLayout.putConstraint(SpringLayout.WEST, Processing, 0, SpringLayout.WEST, this);
//        leftSideBarLayout.putConstraint(SpringLayout.EAST, Processing, 0, SpringLayout.EAST, this);
//        leftSideBarLayout.putConstraint(SpringLayout.NORTH, Completed, 0, SpringLayout.SOUTH, Processing);
//        leftSideBarLayout.putConstraint(SpringLayout.WEST, Completed, 0, SpringLayout.WEST, this);
//        leftSideBarLayout.putConstraint(SpringLayout.EAST, Completed, 0, SpringLayout.EAST, this);
//        leftSideBarLayout.putConstraint(SpringLayout.NORTH, Queues, 0, SpringLayout.SOUTH, Completed);
//        leftSideBarLayout.putConstraint(SpringLayout.WEST, Queues, 0, SpringLayout.WEST, this);
//        leftSideBarLayout.putConstraint(SpringLayout.EAST, Queues, 0, SpringLayout.EAST, this);
        setVisible(true);
    }

    private class Handler implements ActionListener {
        private HashSet<DownloadItem> selectedItem;

        public Handler(DownloadItemsConnection downloadItemsConnection) {
            this.selectedItem = new HashSet<>();
            this.selectedItem = downloadItemsConnection.getSelectedItems();//data has a static field for HashSets
        }

        @Override
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == newDownloadButton) {
                NewDownloadTab newDownloadTab = new NewDownloadTab(); //:))))
            }
            if (event.getSource() == resumeButton) {

            }
            if (event.getSource() == pauseButton) {

            }
            if (event.getSource() == cancelButton) {

            }
            if (event.getSource() == removeButton) {
                Iterator<DownloadItem> it = selectedItem.iterator();
                while (it.hasNext()) {
                    it.next();
                    System.out.println(it);
                }
            }
            if (event.getSource() == settingsButton) {
                Setting setting = new Setting();
            }
            if (event.getSource() == exit) {
                System.exit(0);
            }
            if (event.getSource() == about) {

            }
        }
    }
}
