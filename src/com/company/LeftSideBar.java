package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class LeftSideBar extends JPanel {
    private JMenuBar leftSideBarMenu;
    private JMenu download, help;
    private JMenuItem newDownloadButton, resumeButton, pauseButton, cancelButton, removeButton, settingsButton, exit, about;

    /**
     * creating a Vertical MenuBar
     */
    private class VerticalMenuBar extends JMenuBar {
        private final LayoutManager grid = new GridLayout(0, 1);

        public VerticalMenuBar() {
            setLayout(grid);
        }
    }

    public LeftSideBar() {

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
        newDownloadButton = new JMenuItem("Add a download");
        resumeButton = new JMenuItem("Resume selected download");
        pauseButton = new JMenuItem("Pause selected download");
        cancelButton = new JMenuItem("Cancel selected download");
        removeButton = new JMenuItem("Remove selected download");
        settingsButton = new JMenuItem("Settings");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");

        /**
         *Handling Accelerators
         */
        KeyStroke newDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        KeyStroke resumeDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        KeyStroke pauseDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
        KeyStroke cancelDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
        KeyStroke removeDownloadAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK);
        KeyStroke settingsAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        KeyStroke exitAccelerator = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
//        open.setAction(new MenuAction("Open", null, "Click to Open an Existing File.", KeyStroke.getKeyStroke("control alt P")));
//        open.setAccelerator(KeyStroke.getKeyStroke("control alt P"));
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

        /**
         * add menu bar to JPanel
         */
        add(leftSideBarMenu);
        setBackground(Color.YELLOW);


//        setPreferredSize(new Dimension(150, 300));
//        SpringLayout leftSideBarLayout = new SpringLayout();
//        this.setLayout(leftSideBarLayout);
//
//
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

}
